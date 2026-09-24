package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

/**
 * Шаги раздела «Конструктор»
 */
public class ConstructorSteps {
    private final WebDriver driver;
    public ConstructorSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        return mainPage;
    }

    @Step("Перейти на вкладку «{tabName}»")
    public void switchToTab(MainPage mainPage, String tabName) {
        switch (tabName) {
            case "Булки":
                mainPage.clickBunsTab();
                break;
            case "Соусы":
                mainPage.clickSaucesTab();
                break;
            case "Начинки":
                mainPage.clickFillingsTab();
                break;
            default:
                throw new IllegalArgumentException("Неизвестная вкладка: " + tabName);
        }
    }

    @Step("Проверить, что вкладка «{tabName}» активна")
    public void assertTabActive(MainPage mainPage, String tabName) {
        boolean active;
        switch (tabName) {
            case "Булки":
                active = mainPage.isBunsTabActive();
                break;
            case "Соусы":
                active = mainPage.isSaucesTabActive();
                break;
            case "Начинки":
                active = mainPage.isFillingsTabActive();
                break;
            default:
                active = false;
        }
        assertTrue("Вкладка «" + tabName + "» не активна", active);
    }
}
