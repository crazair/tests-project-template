package ru.vtb.at.template.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vtb.at.template.Props;

import static ru.vtb.at.template.listeners.JUnitListener.browserIsInitialized;

abstract class BaseUITest {

    private static final Logger LOG = LoggerFactory.getLogger(BaseUITest.class);
    protected static Props props = ConfigFactory.create(Props.class);

    @BeforeAll
    private static void setup() {
        if (browserIsInitialized()) return;
        if (!StringUtils.isBlank(props.selenoidUrl())) {
            LOG.info("Tests will be running in Selenoid " + props.selenoidUrl());
            Configuration.remote = props.selenoidUrl();
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        }
        Configuration.baseUrl = props.vtbUrl();
        Configuration.startMaximized = true;
        Configuration.timeout = props.timeout();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        browserIsInitialized(true);
    }

    @AfterEach
    void afterTest() {
        Selenide.closeWebDriver();
    }
}
