package com.datalore.qa.ui;

import com.datalore.qa.ui.pageObjects.LandingPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;

/**
 * @author vdanilova
 * Tests with Selenium WebDriver for datalore landing page
 */
public class LandingTest extends BaseSeleniumTest {

    LandingPage landingPage;

    @BeforeEach
    public void setUpPages() {
       landingPage = new LandingPage();
    }

    /**
     * Login with correct data and check that user if logged in
     */
    @Test
    public void login() {
        assertTrue(landingPage
                .login(getData().userEmail(), getData().userPassword())
                .openAvatarMenu()
                .getLogoutElement().isDisplayed()); //check Logout is displayed
    }

    /**
     * Login with not existed data and check error dialog is displayed
     */
    @ParameterizedTest
    @CsvSource({
            "test@email.com, passw0rd123",
            "test@email.com, пароль746529",
            "email~!#$%^*()_✨✅@mail.com, ~!#$%^*()_✨✅"
    })
    public void loginWithNotExistedData(String email, String password) {
        landingPage.tryLogin(email, password);
        assertTrue(landingPage.getErrorMsg().isDisplayed());
    }

    /**
     * Login with incorrect data and check that error message in the form is displayed
     */
    @ParameterizedTest
    @CsvFileSource(resources = "incorrectDataLogin.csv", numLinesToSkip = 0)
    public void loginWithIncorrectData(String email, String password) {
        landingPage.tryLogin(email, password);
        assertTrue(landingPage.getErrorInput().isDisplayed());
    }

    /**
     * Check "Show password" button
     */
    @Test
    public void showPassword() {
        landingPage.fillPassword(getData().userPassword());
        landingPage.showPassword();
        assertTrue(landingPage.getPasswordIsShowed().isDisplayed());
    }

    /**
     * Check forgot password link
     */
    @Test
    public void forgotPassword() {
        landingPage.fillEmail("test@email.com");
        landingPage.clickForgotPasswordLink();
        assertTrue(landingPage.getCheckEmailTxt().isDisplayed());
    }

    /**
     * Create account with weak password
     */
    @ParameterizedTest
    @CsvFileSource(resources = "weakPasswordsSingUp.csv", numLinesToSkip = 0)
    public void tryCreateAccountWithWeakPassword(String email, String password) {
        landingPage.tryCreateAccount(email, password);
        assertTrue(landingPage.getErrorInput().isDisplayed());
    }

    /**
     * Create account (assert with error message because creation is disabled)
     * That's why here is scenario with existing account
     */
    @Test
    public void tryCreateExistingAccount() {
        landingPage.tryCreateAccount(getData().userEmail(), getData().userPassword());
        assertTrue(landingPage.getErrorMsg().isDisplayed());
    }

    /**
     * Check that user can go to the creat form and back to the log in form
     */
    @Test
    public void goBackToLogin() {
        landingPage.clickCreateAccount();
        assertTrue(landingPage.getCreateBtn().isDisplayed());
        landingPage.clickBackToLogin();
        assertTrue(landingPage.getLoginBtn().isDisplayed());
    }

    /**
     * Check info links
     */
    @Test
    public void checkLinks() {
        assertTrue(landingPage.getBlogLink().getAttribute("href").contains(getData().blogURL()));
        assertTrue(landingPage.getCommunityLink().getAttribute("href").contains(getData().communityURL()));
        assertTrue(landingPage.getSupportLink().getAttribute("href").contains(getData().supportURL()));
        assertTrue(landingPage.getDocumentationLink().getAttribute("href").contains(getData().docURL()));
    }

}
