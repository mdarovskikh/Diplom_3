package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import steps.RegistrationSteps;
import utils.UserDataGenerator;

import static org.junit.Assert.assertTrue;

/**
 * Тесты на регистрацию
 * Создаёт уникального пользователя для каждого теста
 */
public class RegistrationTest extends BaseTest {
    private RegistrationSteps registrationSteps;

    @Before
    public void initSteps() {
        registrationSteps = new RegistrationSteps(driver);
    }

    /**
     * Успешная регистрация нового пользователя
     */
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Новый пользователь может зарегистрироваться с валидными данными")
    public void successfulRegistrationTest() {
        String email = UserDataGenerator.uniqueEmail();
        String name = UserDataGenerator.uniqueName();
        String password = UserDataGenerator.validPassword();

        MainPage mainPage = registrationSteps.openMainPage();
        registrationSteps.goToRegisterPage(mainPage);
        registrationSteps.fillAndSubmitForm(name, email, password);
        registrationSteps.assertLoginPageOpened();
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

        MainPage mainPage = registrationSteps.openMainPage();
        registrationSteps.goToRegisterPage(mainPage);
        registrationSteps.fillAndSubmitForm(name, email, password);
        registrationSteps.assertPasswordErrorShown();
    }
}
