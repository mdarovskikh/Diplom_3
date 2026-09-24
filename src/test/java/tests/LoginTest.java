package tests;

import api.UserApiClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import steps.LoginSteps;
import utils.UserDataGenerator;

/**
 * Тесты входа в аккаунт
 * Пользователь создаётся через API в @Before
 */
public class LoginTest extends BaseTest {
    private UserApiClient userApiClient;
    private LoginSteps loginSteps;
    private String email;
    private String password;

    @Before
    public void setUpUser() {
        userApiClient = new UserApiClient();
        loginSteps = new LoginSteps(driver);

        email = UserDataGenerator.uniqueEmail();
        password = UserDataGenerator.validPassword();
        String name = UserDataGenerator.uniqueName();

        createdUserAccessToken = userApiClient.createUser(email, password, name);
    }

    /** Вход через кнопку «Войти в аккаунт» на главной странице */
    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Description("Проверяет вход на главной странице через кнопку «Войти в аккаунт»")
    public void loginMainPageButtonTest() {
        MainPage mainPage = loginSteps.openMainPage();
        loginSteps.clickLoginButtonOnMainPage(mainPage);
        loginSteps.login(email, password);
        loginSteps.assertLoginSuccess(mainPage);
    }

    /** Вход через кнопку «Личный кабинет» */
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("ПРоверяет вход через кнопку «Личный кабинет»")
    public void loginAccountButtonTest() {
        MainPage mainPage = loginSteps.openMainPage();
        loginSteps.clickAccountButton(mainPage);
        loginSteps.login(email, password);
        loginSteps.assertLoginSuccess(mainPage);
    }
    /** Вход через кнопку в форме регистрации */
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет вход через кнопку в форме регистрации")
    public void loginRegisterFormButtonTest() {
        MainPage mainPage = loginSteps.openMainPage();
        loginSteps.clickAccountButton(mainPage);
        loginSteps.goToLoginFromRegisterForm();
        loginSteps.login(email, password);
        loginSteps.assertLoginSuccess(mainPage);
    }

    /** Вход через кнопку в форме восстановления пароля */
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Провкряет вход через кнопку в форме восстановления пароля")
    public void loginRestorePasswordFormButtonTest() {
        MainPage mainPage = loginSteps.openMainPage();
        loginSteps.clickAccountButton(mainPage);
        loginSteps.goToLoginFromRestoreForm();
        loginSteps.login(email, password);
        loginSteps.assertLoginSuccess(mainPage);
    }
}
