package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

/**
 * Тесты для раздела «Конструктор»
 * Проверяют переключение вкладок «Булки», «Соусы», «Начинки»
 */
public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Вкладка «Булки»")
    @Description("Переход на вкладку «Булки»")
    public void switchToBunsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickBunsTab();

        assertTrue("Вкладка «Булки» не активна", mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Вкладка «Соусы»")
    @Description("Переход на вкладку «Соусы»")
    public void switchToSaucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();

        assertTrue("Вкладка «Соусы» не активна", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Вкладка «Начинки»")
    @Description("Переход на вкладку «Начинки»")
    public void switchToFillingsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsTab();

        assertTrue("Вкладка «Начинки» не активна", mainPage.isFillingsTabActive());
    }
}
