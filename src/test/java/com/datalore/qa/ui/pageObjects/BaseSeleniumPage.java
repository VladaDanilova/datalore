package com.datalore.qa.ui.pageObjects;

import com.datalore.qa.config.DataProvider;
import com.datalore.qa.config.TestDataAndProperties;
import org.openqa.selenium.WebDriver;

public class BaseSeleniumPage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }

    /**
     * Get data from properties
     */
    protected TestDataAndProperties getData() {
        return DataProvider.get();
    }
}
