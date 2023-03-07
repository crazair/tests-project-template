package ru.vtb.at.template.listeners;

import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import static ru.vtb.at.template.asserts.SoftAssert.IS_SOFT;

public class StepLifecycleListener implements io.qameta.allure.listener.StepLifecycleListener {

    @Override
    public void afterStepStop(StepResult result) {
        if (result.getStatus() == Status.PASSED) {
            if (!result.getSteps().isEmpty()) {
                result.getSteps().stream().filter(step -> IS_SOFT.equals(step.getDescription()))
                        .forEach(stepResult -> {
                            stepResult.setStatus(Status.FAILED);
                            stepResult.setDescription("");
                            result.setStatus(Status.FAILED);
                        });
            }
            if (IS_SOFT.equals(result.getDescription())) {
                result.setDescription("");
                result.setStatus(Status.FAILED);
            }
        }
    }
}
