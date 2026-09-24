package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

/**
 * Шаги авторизации
 */
public class LoginSteps {
    private final WebDriver driver;
    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        return mainPage;
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public void clickLoginButtonOnMainPage(MainPage mainPage) {
        mainPage.clickLoginButton();
    }

    @Step("Клик по кнопке «Личный кабинет»")
    public void clickAccountButton(MainPage mainPage) {
        mainPage.clickLoginButton();
    }

    @Step("Перейти к логину из формы регистрации")
    public void goToLoginFromRegisterForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        loginPage.clickLoginLink();
    }

    @Step("Перейти к логину из формы восстановления пароля")
    public void goToLoginFromRestoreForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();
        loginPage.clickLoginLink();
    }

    @Step("Войти с email={email}")
    public void login(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Step("Проверить, что авторизация выполнена и конструктор отображается")
    public void assertLoginSuccess(MainPage mainPage) {
        assertTrue("Логин не выполнен — конструктор не открылся",
                mainPage.isConstructorTitleDisplayed());
    }
}
