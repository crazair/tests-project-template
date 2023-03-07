package ru.vtb.at.template.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.vtb.at.template.utils.AllureReflection.getStorage;


public class JUnitListener implements AfterEachCallback {

    private static final ThreadLocal<Boolean> initialized = ThreadLocal.withInitial(() -> false);

    @Override
    public void afterEach(ExtensionContext context) {
        StringBuilder message = new StringBuilder();
        StringBuilder trace = new StringBuilder();
        AtomicInteger number = new AtomicInteger();
        getStorage().getTestResult(Allure.getLifecycle().getCurrentTestCase().get()).get().getSteps().stream()
                .filter(step -> Status.FAILED.equals(step.getStatus()))
                .map(this::getAllureSteps)
                .flatMap(Collection::stream)
                .forEach(step -> {
                    StatusDetails statusDetails = step.getStatusDetails();
                    if (statusDetails != null) {
                        message.append("\n").append(number.incrementAndGet()).append(". ").append(statusDetails.getMessage());
                        trace.append(statusDetails.getTrace()).append("\n");
                    }
                });
        if (message.length() != 0) throw new AssertionFailedError(message.toString(), new Throwable(trace.toString()));
    }

    private List<StepResult> getAllureSteps(StepResult step) {
        List<StepResult> steps = new ArrayList<>(Collections.emptyList());
        steps.add(step);
        if (!step.getSteps().isEmpty()) steps.addAll(step.getSteps().stream()
                .map(this::getAllureSteps)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
        return steps;
    }

    public static Boolean browserIsInitialized() {
        return initialized.get();
    }

    public static void browserIsInitialized(Boolean browserIsInitialized) {
        initialized.set(browserIsInitialized);
    }
}
