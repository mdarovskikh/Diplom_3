package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Главная страница c конструктором бургеров
 * Содержит локаторы и методы для работы с разделами «Булки», «Соусы», «Начинки»
 * и кнопки входа в аккаунт
 */
public class MainPage extends BasePage {

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By bunsTab = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Булки']]");
    private final By saucesTab = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Соусы']]");
    private final By fillingsTab = By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Начинки']]");
    private final By constructorTitle = By.xpath("//h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public MainPage open() {
        super.open(BASE_URL);
        return this;
    }

    @Step("Клик по кнопке «Войти в аккаунт»")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Клик по кнопке «Личный кабинет»")
    public void clickAccountButton() {
        click(personalAccountButton);
    }

    @Step("Перейти на вкладку «Булки»")
    public void clickBunsTab() {
        click(bunsTab);
    }

    @Step("Перейти на вкладку «Соусы»")
    public void clickSaucesTab() {
        click(saucesTab);
    }

    @Step("Перейти на вкладку «Начинки»")
    public void clickFillingsTab() {
        click(fillingsTab);
    }

    @Step("Проверить, что отображается заголовок «Соберите бургер»")
    public boolean isConstructorTitleDisplayed() {
        return isElementDisplayed(constructorTitle);
    }

    @Step("Проверить, что вкладка «Булки» активна")
    public boolean isBunsTabActive() {
        return driver.findElement(bunsTab).getAttribute("class").contains("tab_tab_type_current");
    }

    @Step("Проверить, что вкладка «Соусы» активна")
    public boolean isSaucesTabActive() {
        return driver.findElement(saucesTab).getAttribute("class").contains("tab_tab_type_current");
    }

    @Step("Проверить, что вкладка «Начинки» активна")
    public boolean isFillingsTabActive() {
        return driver.findElement(fillingsTab).getAttribute("class").contains("tab_tab_type_current");
    }
}
