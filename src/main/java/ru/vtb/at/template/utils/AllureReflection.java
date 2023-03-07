package ru.vtb.at.template.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.internal.AllureStorage;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class AllureReflection {

    @SneakyThrows
    public static AllureStorage getStorage() {
        AllureLifecycle allure = Allure.getLifecycle();
        Field storage = AllureLifecycle.class.getDeclaredField("storage");
        storage.setAccessible(true);
        return (AllureStorage) storage.get(allure);
    }
}
