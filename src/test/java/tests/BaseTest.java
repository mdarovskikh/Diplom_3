package tests;

import api.UserApiClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

import java.io.File;

/**
 * Базовый класс для всех UI-тестов
 * По умолчанию запускается в Google Chrome. Для Яндекс.Браузера:  mvn test -Dbrowser=yandex
 * YandexDriver берётся из {@code src/test/resources/yandexdriver.exe}
 * Путь к самому Яндекс.Браузеру можно переопределить: mvn test -Dbrowser=yandex -Dyandex.browser.path="D:/Yandex/Browser/browser.exe"
 * Класс отвечает за:
 *  - инициализацию WebDriver (Chrome или Яндекс.Браузер)
 *  - делает скриншот при падении теста (прикрепляется к Allure отчету)
 *  - удаление тестовых данных через API после теста
 */
public class BaseTest {
    private static final String CHROME_BROWSER = "chrome";
    private static final String YANDEX_BROWSER_PATH = ConfigReader.get("yandex.browser.path");
    private static final String YANDEX_DRIVER_RESOURCE = "yandexdriver.exe";
    protected WebDriver driver;
    protected UserApiClient userApiClient = new UserApiClient();
    protected String createdUserAccessToken;

    /**
     * Инициализация браузера перед каждым тестом
     */
    @Before
    public void setUp() {
        String browser = System.getProperty("browser", CHROME_BROWSER);
        if ("yandex".equalsIgnoreCase(browser)) {
            String yandexPath = System.getProperty("yandex.browser.path", YANDEX_BROWSER_PATH);
            driver = createYandexDriver(yandexPath);
        } else {
            driver = createChromeDriver();
        }
        driver.manage().window().maximize();
    }

    /**
     * Создаёт ChromeDriver с автоматической установкой драйвера через WebDriverManager
     * Это браузер по умолчанию
     */
    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    /**
     * Создаёт драйвер для Яндекс.Браузера, используя официальный YandexDriver
     */
    private WebDriver createYandexDriver(String yandexBrowserPath) {
        // 1. Проверяем, что yandexdriver.exe доступен в classpath
        File driverBinary = resolveResource(YANDEX_DRIVER_RESOURCE);
        if (driverBinary == null || !driverBinary.exists()) {
            throw new IllegalStateException(
                    "YandexDriver не найден по пути src/test/resources/" + YANDEX_DRIVER_RESOURCE + "\n" +
                            "Скачайте его с https://github.com/yandex/YandexDriver/releases " +
                            "и положите в src/test/resources/"
            );
        }

        // 2. Проверяем, что сам Яндекс.Браузер установлен
        File browserBinary = new File(yandexBrowserPath);
        if (!browserBinary.exists()) {
            throw new IllegalStateException(
                    "Яндекс.Браузер не найден по пути: " + yandexBrowserPath + "\n" +
                            "Укажите корректный путь: mvn test -Dbrowser=yandex " +
                            "-Dyandex.browser.path=\"полный/путь/к/browser.exe\""
            );
        }

        // 3. Указываем Selenium, что нужно использовать YandexDriver
        System.setProperty("webdriver.chrome.driver", driverBinary.getAbsolutePath());

        // 4. Создаём ChromeDriver с указанием бинарника Яндекс.Браузера
        ChromeOptions options = new ChromeOptions();
        options.setBinary(browserBinary);
        return new ChromeDriver(options);
    }

    /**
     * Возвращает File по имени ресурса из classpath (src/test/resources → target/test-classes).
     */
    private File resolveResource(String resourceName) {
        try {
            java.net.URL url = getClass().getClassLoader().getResource(resourceName);
            if (url == null) {
                return null;
            }
            return new File(url.toURI());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JUnit Rule: если тест упал, делаем скриншот и прикрепляем к отчету Allure
     */
    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };
    /**
 * Скриншот текущего состояния страницы.
 */
    @Attachment(value = "Screenshot on failure: {screenshotName}", type = "image/png")
    private byte[] takeScreenshot(String screenshotName) {
        if (driver == null) {
            return new byte[0];
        } try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
    /**
     * Очистка после каждого теста через API
     */
    @After
    public void tearDown() {
        if (createdUserAccessToken != null) {
            userApiClient.deleteUser(createdUserAccessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
