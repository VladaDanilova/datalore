package com.datalore.qa.ui.pageObjects;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object of Datalore's Login page
 */
@Getter
public class LandingPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginBtn;

    @FindBy(xpath = "//button[text()='Forgot your password?']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[text()='Check your email ']")
    private WebElement checkEmailTxt;

    @FindBy(xpath = "//button[text()='Create an account']")
    private WebElement createAccountLink;

    @FindBy(xpath = "//button[text()='Create account']")
    private WebElement createBtn;

    @FindBy(xpath = "//button[text()='Log in here!']")
    private WebElement loginLink;

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

    @FindBy(xpath = "//div[@class='alert_message alert_message-error']")
    private WebElement errorMsg;

    @FindBy(xpath = "//div[@data-test='error-message']")
    private WebElement errorInput;

    @FindBy(xpath = "//a[text()='Datalore']")
    private WebElement dataloreHeader;

    @FindBy(xpath = "//h1[text()='Log in']")
    private WebElement logInHeader;

    @FindBy(xpath = "//h1[text()='Sign up']")
    private WebElement signUpHeader;

    @FindBy(xpath = "//h1[contains(@class, ' landing__title')]")
    private WebElement landingTitle;

    @FindBy(xpath = "//h3[contains(@class, ' landing__subtitle')]")
    private WebElement landingSubtitle;

    public LandingPage() {
        driver.get(getData().basePath());
        PageFactory.initElements(driver,this);
    }

    public void tryLogin(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLogin();
    }

    public HomePage login(String email, String password) {
        tryLogin(email, password);
        return new HomePage();
    }

    public void fillPassword(String data) {
        passwordInput.clear();
        passwordInput.sendKeys(data);
    }

    public void fillEmail(String data) {
        emailInput.clear();
        emailInput.sendKeys(data);
    }

    public void showPassword() {
        showPasswordBtn.click();
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void clickBackToLogin() {
        loginLink.click();
    }

    public void clickCreateAccount() {
        createAccountLink.click();
    }

    public void tryCreateAccount(String email, String password) {
        clickCreateAccount();
        fillEmail(email);
        fillPassword(password);
        createBtn.click();
    }

    public String checkErrorMsg() {
        getWait().until(ExpectedConditions.visibilityOf(errorMsg));
        return errorMsg.getText();
    }

    public void clickHeader() {
        dataloreHeader.click();
    }

    public String checkErrorInput() {
        return errorInput.getText();
    }

}
