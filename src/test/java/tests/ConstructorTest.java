package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import steps.ConstructorSteps;


/**
 * Тесты для раздела «Конструктор»
 * Проверяют переключение вкладок «Булки», «Соусы», «Начинки»
 */
public class ConstructorTest extends BaseTest {
    private ConstructorSteps constructorSteps;
    @Before
    public void initSteps() {
        constructorSteps = new ConstructorSteps(driver);
    }
    @Test
    @DisplayName("Вкладка «Булки»")
    @Description("Переход на вкладку «Булки»")
    public void switchToBunsTabTest() {
        MainPage mainPage = constructorSteps.openMainPage();
        constructorSteps.switchToTab(mainPage, "Булки");
        constructorSteps.assertTabActive(mainPage, "Булки");
    }

    @Test
    @DisplayName("Вкладка «Соусы»")
    @Description("Переход на вкладку «Соусы»")
    public void switchToSaucesTabTest() {
        MainPage mainPage = constructorSteps.openMainPage();
        constructorSteps.switchToTab(mainPage, "Соусы");
        constructorSteps.assertTabActive(mainPage, "Соусы");
    }

    @Test
    @DisplayName("Вкладка «Начинки»")
    @Description("Переход на вкладку «Начинки»")
    public void switchToFillingsTabTest() {
        MainPage mainPage = constructorSteps.openMainPage();
        constructorSteps.switchToTab(mainPage, "Начинки");
        constructorSteps.assertTabActive(mainPage, "Начинки");
    }
}
