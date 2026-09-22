package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница регистрации нового пользователя
 */
public class RegisterPage extends BasePage {
    private final By nameField = By.xpath("//input[@name='name']");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    public void enterName(String name) {
        type(nameField, name);
    }
    public void enterEmail(String email) {
        type(emailField, email);
    }
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    public void clickRegisterButton() {
        click(registerButton);
    }
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
    public boolean isPasswordErrorDisplayed() {
        return isElementDisplayed(passwordError);
    }
    public void clickLoginLink() {
        click(loginLink);
    }
}
