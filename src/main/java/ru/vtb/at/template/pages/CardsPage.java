package ru.vtb.at.template.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class CardsPage {

    @Step("Получаем список карточных продуктов")
    public ElementsCollection cardProducts() {
        return $$x("//ul[contains(@class,'simple-tab__heads-list')]/li");
    }

    @Step("Получаем инфо по карточному продукту")
    public ElementsCollection cardProductInfo() {
        return $$x("//ul[contains(@class, 'marker-list')]//li").filter(Condition.visible);
    }
}
