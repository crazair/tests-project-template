package ru.vtb.at.template.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;

import static ru.vtb.at.template.utils.AllureReflection.getStorage;


public class AllureTestLifecycleListener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus().equals(Status.SKIPPED)) {
            getStorage().remove(result.getUuid());
        }
    }
}
