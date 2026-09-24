package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.Assert.assertTrue;

/**
 * Шаги регистрации
 */
public class RegistrationSteps {
    private final WebDriver driver;
    public RegistrationSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public MainPage openMainPage() {
       MainPage mainPage = new MainPage(driver);
       mainPage.open();
       return mainPage;
    }

    @Step("Перейти на страницу регистрации")
    public void goToRegisterPage(MainPage mainPage) {
        mainPage.clickLoginButton();
        new LoginPage(driver).clickRegisterLink();
    }

    @Step("Заполнить и отправить форму регистрации: {email}")
    public void fillAndSubmitForm(String name, String email, String password) {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
    }

    @Step("Проверить, что открылась страница входа")
    public void assertLoginPageOpened() {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Страница входа не открылась после регистрации",
                loginPage.isLoginPageOpened());
    }

    @Step("Проверить, что отображена ошибка о некорректнои пароле")
    public void assertPasswordErrorShown() {
        RegisterPage registerPage = new RegisterPage(driver);
        assertTrue("Сообщение об ошибке пароля не появилось",
                registerPage.isPasswordErrorDisplayed());
    }
}
