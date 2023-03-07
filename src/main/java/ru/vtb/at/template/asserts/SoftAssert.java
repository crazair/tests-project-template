package ru.vtb.at.template.asserts;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.commons.util.StringUtils;
import org.junit.platform.commons.util.UnrecoverableExceptions;
import org.openqa.selenium.OutputType;
import org.opentest4j.AssertionFailedError;
import org.opentest4j.MultipleFailuresError;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.vtb.at.template.listeners.JUnitListener.browserIsInitialized;

public class SoftAssert {

    public static final String IS_SOFT = "#SOFT#";

    @Step("Soft Assert")
    public static void softAssert(byte expected, byte actual) {
        if (expected != actual) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(byte expected, byte actual, String message) {
        if (expected != actual) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(byte expected, byte actual, Supplier<String> messageSupplier) {
        if (expected != actual) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(char expected, char actual) {
        if (expected != actual) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(char expected, char actual, String message) {
        if (expected != actual) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(char expected, char actual, Supplier<String> messageSupplier) {
        if (expected != actual) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(double expected, double actual) {
        if (expected != actual) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(double expected, double actual, String message) {
        if (!doublesAreEqual(expected, actual)) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(double expected, double actual, Supplier<String> messageSupplier) {
        if (!doublesAreEqual(expected, actual)) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(double expected, double actual, double delta) {
        softAssert(expected, actual, delta, (String) null);
    }

    @Step("Soft Assert")
    public static void softAssert(double expected, double actual, double delta, String message) {
        if (!doublesAreEqual(expected, actual, delta)) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(double expected, double actual, double delta, Supplier<String> messageSupplier) {
        if (!doublesAreEqual(expected, actual, delta)) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(float expected, float actual) {
        if (!floatsAreEqual(expected, actual)) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(float expected, float actual, String message) {
        if (!floatsAreEqual(expected, actual)) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(float expected, float actual, Supplier<String> messageSupplier) {
        if (!floatsAreEqual(expected, actual)) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(float expected, float actual, float delta) {
        if (!floatsAreEqual(expected, actual, delta)) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(float expected, float actual, float delta, String message) {
        if (!floatsAreEqual(expected, actual, delta)) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(float expected, float actual, float delta, Supplier<String> messageSupplier) {
        if (!floatsAreEqual(expected, actual, delta)) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(short expected, short actual) {
        if (expected != actual) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(short expected, short actual, String message) {
        if (expected != actual) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(short expected, short actual, Supplier<String> messageSupplier) {
        if (expected != actual) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(int expected, int actual) {
        if (expected != actual) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(int expected, int actual, String message) {
        if (expected != actual) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(int expected, int actual, Supplier<String> messageSupplier) {
        if (expected != actual) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(long expected, long actual) {
        if (expected != actual) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(long expected, long actual, String message) {
        if (expected != actual) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(long expected, long actual, Supplier<String> messageSupplier) {
        if (expected != actual) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(Object expected, Object actual) {
        if (!objectsAreEqual(expected, actual)) {
            failNotEqual(expected, actual, (String) null);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(Object expected, Object actual, String message) {
        if (!objectsAreEqual(expected, actual)) {
            failNotEqual(expected, actual, message);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(Object expected, Object actual, Supplier<String> messageSupplier) {
        if (!objectsAreEqual(expected, actual)) {
            failNotEqual(expected, actual, messageSupplier);
        }
    }

    private static void allureFailStep(AssertionError ex) {
        AllureLifecycle allure = Allure.getLifecycle();
        allure.updateStep(it -> it.withStatus(Status.FAILED).withDescription(IS_SOFT));
        allure.addAttachment("StackTrace",
                "text/plain",
                "log",
                arraysStackTrace(ex).getBytes(StandardCharsets.UTF_8)
        );
    }

    private static void allureFailStep(String message, AssertionError ex) {
        AllureLifecycle allure = Allure.getLifecycle();
        allure.updateStep(it -> it.setStatusDetails(new StatusDetails()
                        .withTrace(arraysStackTrace(ex))
                        .withMessage(message)
                ).withStatus(Status.FAILED).withDescription(IS_SOFT)
        );
        allure.addAttachment("StackTrace",
                "text/plain",
                "log",
                arraysStackTrace(ex).getBytes(StandardCharsets.UTF_8)
        );
        if (browserIsInitialized()) allure.addAttachment(allure.getCurrentTestCaseOrStep().get(),
                "image/png",
                "png",
                Selenide.screenshot(OutputType.BYTES)
        );
    }

    private static String arraysStackTrace(AssertionError ex) {
        return Arrays.stream(ex.getStackTrace()).map(SoftAssert::toString).collect(Collectors.joining("\n"));
    }

    @Step("Soft Assert")
    public static void softAssertAll(Executable... executables) {
        try {
            assertAll(executables);
        } catch (MultipleFailuresError ex) {
            allureFailStep(ex);
        }
    }

    @Step("Soft Assert")
    public static void softAssert(Executable executable) {
        try {
            assertAll(executable);
        } catch (MultipleFailuresError ex) {
            allureFailStep(ex);
        }
    }

    private static void fail(String message) {
        try {
            throw new AssertionFailedError(message);
        } catch (AssertionError ex) {
            allureFailStep(message, ex);
        }
    }

    private static void fail(String message, Object expected, Object actual) {
        try {
            throw new AssertionFailedError(message, expected, actual);
        } catch (AssertionError ex) {
            allureFailStep(message, ex);
        }

    }

    private static void failNotEqual(Object expected, Object actual, String message) {
        fail(format(expected, actual, message), expected, actual);
    }

    private static void failNotEqual(Object expected, Object actual, Supplier<String> messageSupplier) {
        fail(format(expected, actual, nullSafeGet(messageSupplier)), expected, actual);
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }

    private static String buildPrefix(String message) {
        return (StringUtils.isNotBlank(message) ? message + " ==> " : "");
    }

    private static String getCanonicalName(Class<?> clazz) {
        try {
            String canonicalName = clazz.getCanonicalName();
            return (canonicalName != null ? canonicalName : clazz.getName());
        } catch (Throwable t) {
            UnrecoverableExceptions.rethrowIfUnrecoverable(t);
            return clazz.getName();
        }
    }

    private static String format(Object expected, Object actual, String message) {
        return buildPrefix(message) + formatValues(expected, actual);
    }

    private static String formatValues(Object expected, Object actual) {
        String expectedString = toString(expected);
        String actualString = toString(actual);
        if (expectedString.equals(actualString)) {
            return String.format("expected: %s but was: %s", formatClassAndValue(expected, expectedString),
                    formatClassAndValue(actual, actualString));
        }
        return String.format("expected: <%s> but was: <%s>", expectedString, actualString);
    }

    private static String formatClassAndValue(Object value, String valueString) {
        String classAndHash = getClassName(value) + toHash(value);
        // if it's a class, there's no need to repeat the class name contained in the valueString.
        return (value instanceof Class ? "<" + classAndHash + ">" : classAndHash + "<" + valueString + ">");
    }

    private static String toString(Object obj) {
        if (obj instanceof Class) {
            return getCanonicalName((Class<?>) obj);
        }
        return StringUtils.nullSafeToString(obj);
    }

    private static String toHash(Object obj) {
        return (obj == null ? "" : "@" + Integer.toHexString(System.identityHashCode(obj)));
    }

    private static String getClassName(Object obj) {
        return (obj == null ? "null"
                : obj instanceof Class ? getCanonicalName((Class<?>) obj) : obj.getClass().getName());
    }

    private static boolean floatsAreEqual(float value1, float value2, float delta) {
        assertValidDelta(delta);
        return floatsAreEqual(value1, value2) || Math.abs(value1 - value2) <= delta;
    }

    private static void assertValidDelta(float delta) {
        if (Float.isNaN(delta) || delta < 0.0) {
            failIllegalDelta(String.valueOf(delta));
        }
    }

    private static void assertValidDelta(double delta) {
        if (Double.isNaN(delta) || delta < 0.0) {
            failIllegalDelta(String.valueOf(delta));
        }
    }

    private static boolean floatsAreEqual(float value1, float value2) {
        return Float.floatToIntBits(value1) == Float.floatToIntBits(value2);
    }

    private static boolean doublesAreEqual(double value1, double value2, double delta) {
        assertValidDelta(delta);
        return doublesAreEqual(value1, value2) || Math.abs(value1 - value2) <= delta;
    }

    private static boolean doublesAreEqual(double value1, double value2) {
        return Double.doubleToLongBits(value1) == Double.doubleToLongBits(value2);
    }

    private static boolean objectsAreEqual(Object obj1, Object obj2) {
        if (obj1 == null) {
            return (obj2 == null);
        }
        return obj1.equals(obj2);
    }

    private static void failIllegalDelta(String delta) {
        fail("positive delta expected but was: <" + delta + ">");
    }
}
