package pages;

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
    public MainPage open() {
        super.open(BASE_URL);
        return this;
    }
    public void clickLoginButton() {
        click(loginButton);
    }
    public void clickAccountButton() {
        click(personalAccountButton);
    }
    public void clickBunsTab() {
        click(bunsTab);
    }
    public void clickSaucesTab() {
        click(saucesTab);
    }
    public void clickFillingsTab() {
        click(fillingsTab);
    }
    public boolean isConstructorTitleDisplayed() {
        return isElementDisplayed(constructorTitle);
    }
    public boolean isBunsTabActive() {
        return driver.findElement(bunsTab).getAttribute("class").contains("tab_tab_type_current");
    }
    public boolean isSaucesTabActive() {
        return driver.findElement(saucesTab).getAttribute("class").contains("tab_tab_type_current");
    }
    public boolean isFillingsTabActive() {
        return driver.findElement(fillingsTab).getAttribute("class").contains("tab_tab_type_current");
    }
}
