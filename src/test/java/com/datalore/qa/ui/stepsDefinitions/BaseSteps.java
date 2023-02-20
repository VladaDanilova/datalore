package com.datalore.qa.ui.stepsDefinitions;

import com.datalore.qa.config.TestDataAndProperties;
import com.datalore.qa.ui.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.datalore.qa.config.DataProvider;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base class to contain common methods for step definitions.
 */
abstract class BaseSteps {
    private WebDriver driver = Hooks.driver;
    private WebDriverWait wait;

    protected WebDriver getDriver() {
        return Hooks.driver;
    }

    protected WebDriverWait getWait() {
        if (Objects.isNull(this.wait)) {
            this.wait = new WebDriverWait(driver, DataProvider.get().waitTimeout());
        }

        return wait;
    }

    protected TestDataAndProperties getData() {
        return DataProvider.get();
    }

    protected void assertCurrentPageUrl(String expectedUrl, String messageOnFail) {
        assertThat(getDriver().getCurrentUrl())
                .as(messageOnFail)
                .contains(expectedUrl);
    }
}
