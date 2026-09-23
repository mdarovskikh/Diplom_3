package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.UserDataGenerator;

import static org.junit.Assert.assertTrue;

/**
 * Тесты на регистрацию
 * Создаёт уникального пользователя для каждого теста
 */
public class RegistrationTest extends BaseTest {
    /**
     * Успешная регистрация нового пользователя.
     */
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Новый пользователь может зарегистрироваться с валидными данными")
    public void successfulRegistrationTest() {
        String email = UserDataGenerator.uniqueEmail();
        String name = UserDataGenerator.uniqueName();
        String password = UserDataGenerator.validPassword();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        new pages.LoginPage(driver).clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);

        LoginPage loginPage = new LoginPage(driver);
        assertTrue("После регистрации не открылась страница входа",
                loginPage.isLoginPageOpened());
    }

    /**
     * Ошибка при некорректном коротком пароле
     */
    @Test
    @DisplayName("Ошибка при пароле короче 6 символов")
    @Description("Если пароль меньше 6 символов, появляется сообщение об ошибке")
    public void shortPasswordErrorTest() {
        String email = UserDataGenerator.uniqueEmail();
        String name = UserDataGenerator.uniqueName();
        String password = UserDataGenerator.invalidPassword();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        new pages.LoginPage(driver).clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(name, email, password);
        assertTrue("Сообщение об ошибке пароля не появилось", registerPage.isPasswordErrorDisplayed());
    }
}
