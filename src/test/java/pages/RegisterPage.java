package pages;

import io.qameta.allure.Step;
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

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        type(nameField, name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        type(emailField, email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    @Step("Клик по кнопке «Зарегистрироваться»")
    public void clickRegisterButton() {
        click(registerButton);
    }

    @Step("Заполнить и отправить форму регистрации: {email}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Проверить, что отображается ошибка «Некорректный пароль»")
    public boolean isPasswordErrorDisplayed() {
        return isElementDisplayed(passwordError);
    }

    @Step("Перейти по ссылке «Войти»")
    public void clickLoginLink() {
        click(loginLink);
    }
}
