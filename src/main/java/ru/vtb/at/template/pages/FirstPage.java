package ru.vtb.at.template.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class FirstPage {

    @Step("Получаем список элементов меню")
    public ElementsCollection menuItems() {
        return $$(".navigation__second__menu .navigation__second__item").filter(Condition.visible);
    }

    @Step("В главном меню выбираем \"Карты\"")
    public CardsPage goToCards() {
        $x("//a[@href='/personal/karty/']").click();
        return page(CardsPage.class);
    }

}
