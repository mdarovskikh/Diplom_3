package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import utils.UserDataGenerator;

import static org.junit.Assert.assertTrue;

/**
 * Тесты входа в аккаунт
 * Пользователь создаётся через API в @Before
 */
public class LoginTest extends BaseTest {
    private String email;
    private String password;

    @Before
    public void createUserApi() {
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
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        new LoginPage(driver).login(email, password);
        assertTrue("Не удалось войти через кнопку на главной", mainPage.isConstructorTitleDisplayed());
    }

    /** Вход через кнопку «Личный кабинет» */
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("ПРоверяет вход через кнопку «Личный кабинет»")
    public void loginAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();

        new LoginPage(driver).login(email, password);

        assertTrue("Не удалось войти через «Личный кабинет»", mainPage.isConstructorTitleDisplayed());
    }
    /** Вход через кнопку в форме регистрации */
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет вход через кнопку в форме регистрации")
    public void loginRegisterFormButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();
        loginPage.clickLoginLinkOnRegisterPage();
        loginPage.login(email, password);

        assertTrue("Не удалось войти из формы регистрации", mainPage.isConstructorTitleDisplayed());
    }

    /** Вход через кнопку в форме восстановления пароля */
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Провкряет вход через кнопку в форме восстановления пароля")
    public void loginRestorePasswordFormButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();
        loginPage.clickLoginLinkOnRestorePage();
        loginPage.login(email, password);

        assertTrue("Не удалось войти из формы восстановления", mainPage.isConstructorTitleDisplayed());
    }
}
