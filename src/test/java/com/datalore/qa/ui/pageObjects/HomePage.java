package com.datalore.qa.ui.pageObjects;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object of Home page
 */
@Getter
public class HomePage extends BaseSeleniumPage {

    @FindBy(xpath = "//*[text()='Logout']")
    private WebElement logoutElement;

    @FindBy(xpath = "//span[contains(@class, 'menu')]/span[contains(@class, 'avatar')]")
    private WebElement avatarMenu;

    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    public HomePage openAvatarMenu() {
        avatarMenu.click();
        return this;
    }

    // TBD
}
