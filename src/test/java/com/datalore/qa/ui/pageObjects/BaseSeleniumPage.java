package com.datalore.qa.ui.pageObjects;

import com.datalore.qa.config.DataProvider;
import com.datalore.qa.config.TestDataAndProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class BaseSeleniumPage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }

    public static WebDriverWait getWait() {
        if (Objects.isNull(wait)) {
            wait = new WebDriverWait(driver, 1000);
        }
        return wait;
    }

    public static void JSclick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }

    /**
     * Get data from properties
     */
    protected TestDataAndProperties getData() {
        return DataProvider.get();
    }
}
