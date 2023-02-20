package com.datalore.qa.ui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Home page
 */
public class HomePage {

    @FindBy(xpath = "//*[text()='Logout']")
    private WebElement logoutElement;

    // TBD
}
