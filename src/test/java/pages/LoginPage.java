package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница входа в аккаунт
 * Содержит поля email, пароль, кнопку «Войти» и ссылки на регистрацию / восстановление пароля
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
    public void enterEmail(String email) {
        type(emailField, email);
    }
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    public void clickLoginButton() {
        click(loginButton);
    }
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
    public void clickRegisterLink() {
        click(registerLink);
    }
    public void clickRestorePasswordLink() {
        click(restorePasswordLink);
    }
    public void clickLoginLinkOnRegisterPage() {
        click(loginLinkOnRegisterPage);
    }
    public void clickLoginLinkOnRestorePage() {
        click(loginLinkOnRegisterPage);
    }

    /**
     * Проверяет, что мы находимся на странице входа
     * Признак наличие кнопки «Войти» на странице
     */
    public boolean isLoginPageOpened() {
        return isElementDisplayed(loginButton);
    }
}
