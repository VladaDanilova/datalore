package com.datalore.qa.ui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object of Datalore's Login page
 */
public class LandingPage {

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginBtn;

    @FindBy(xpath = "//button[text()='Forgot your password?']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[text()='Check your email']")
    private WebElement checkEmailTxt;

    @FindBy(xpath = "//button[text()='Create an account']")
    private WebElement createAccountLink;

    @FindBy(xpath = "//button[text()='Create account']")
    private WebElement createBtn;

    @FindBy(xpath = "//*[name()='svg' and contains(@class, 'input')]")
    private WebElement showPasswordBtn;

    @FindBy(xpath = "//input[@placeholder='Password' and @type='text']")
    private WebElement passwordIsShowed;

    @FindBy(xpath = "//a[text()='Support']")
    private WebElement supportLink;

    @FindBy(xpath = "//a[text()='Documentation']")
    private WebElement documentationLink;

    @FindBy(xpath = "//a[text()='Community forum']")
    private WebElement communityLink;

    @FindBy(xpath = "//a[text()='Blog']")
    private WebElement blogLink;

    public void tryLogin(String email, String password) {
        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();
    }

    public HomePage login(String email, String password) {
        tryLogin(email, password);
        return new HomePage();
    }

}
