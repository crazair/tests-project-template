package ru.vtb.at.template.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vtb.at.template.pages.CardsPage;
import ru.vtb.at.template.pages.FirstPage;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.vtb.at.template.asserts.SoftAssert.softAssert;
import static ru.vtb.at.template.asserts.SoftAssert.softAssertAll;

@Epic("VTB portal")
@Feature("Main menu and Card Products")
@DisplayName("UI Демо")
class DemoUITest extends BaseUITest {

    @Test
    @TmsLink(value = "1")
    @DisplayName("Проверка отображения элементов меню главной страницы")
    void userCanSeeMainPageMenu() {
        FirstPage firstPage = open("/", FirstPage.class);
        assertEquals("Банк ВТБ (ПАО) (Объединенные ВТБ , ВТБ24 и Банк Москвы)", title());
        firstPage.menuItems().shouldHave(texts("Кредиты", "Карты", "Ипотека", "Автокредиты", "Вклады и счета", "Инвестиции"));
    }

    @Test
    @TmsLink(value = "2")
    @DisplayName("Проверка списка карточных продуктов")
    void userCanNavigateToCardsPage() {
        FirstPage firstPage = open("/", FirstPage.class);
        CardsPage cardsPage = firstPage.goToCards();
        cardsPage.cardProducts().shouldHave(exactTexts("Кредитные", "Дебетовые", "Зарплатные", "Пенсионные", "Карты жителя"));
    }

    @Test
    @TmsLink(value = "3")
    @DisplayName("Проверка блока инфо о Зарплатной карте")
    void userCanChooseSalaryCardAndSeeInfo() {
        FirstPage firstPage = open("/", FirstPage.class);
        CardsPage cardsPage = firstPage.goToCards();
        cardsPage.cardProducts().find(text("Зарплатные")).scrollTo().click();
        cardsPage.cardProductInfo()
                .shouldHave(texts("Бесплатный выпуск и обслуживание",
                        "Бесплатное снятие наличных в любых банкоматах",
                        "Бесплатные переводы по реквизитам в ВТБ Онлайн"));
    }

    @Test
    @TmsLink(value = "4")
    @DisplayName("Мягкие проверки")
    void testSoftAsserts() {
        FirstPage firstPage = open("/", FirstPage.class);
        softAssertAll(
                () -> firstPage.menuItems().shouldHave(texts("Кредиты", "Карты", "Ипотека", "Автокредиты", "Вклады и счета", "Инвестиции")),
                () -> firstPage.menuItems().shouldHave(texts("НЕ Кредиты", "НЕ Карты", "НЕТ Ипотека"))
        );
        softAssert("одиночная проверка", "одиночная мягкая проверка", "текст об ошибке одиночной мягкой проверки");
    }
}
