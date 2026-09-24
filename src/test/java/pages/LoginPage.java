package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница входа в аккаунт
 * Содержит поля email, пароль, кнопку «Войти» и ссылки на регистрацию / восстановление пароля
 */
/**
 * Страница входа в аккаунт.
 * Все публичные методы — шаги с @Step для Allure.
 */
public class LoginPage extends BasePage {

    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    private final By loginLinkOnRegisterPage = By.xpath("//a[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        type(emailField, email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    @Step("Клик по кнопке «Войти»")
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step("Войти с email={email}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Перейти на страницу регистрации")
    public void clickRegisterLink() {
        click(registerLink);
    }

    @Step("Перейти на страницу восстановления пароля")
    public void clickRestorePasswordLink() {
        click(restorePasswordLink);
    }

    @Step("Перейти по ссылке «Войти» со страницы регистрации")
    public void clickLoginLinkOnRegisterPage() {
        click(loginLinkOnRegisterPage);
    }

    @Step("Перейти по ссылке «Войти» со страницы восстановления пароля")
    public void clickLoginLinkOnRestorePage() {
        click(loginLinkOnRegisterPage);
    }

    @Step("Проверить, что открыта страница входа")
    public boolean isLoginPageOpened() {
        return isElementDisplayed(loginButton);
    }
}