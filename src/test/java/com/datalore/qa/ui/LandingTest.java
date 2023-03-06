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
     * Login with correct data and check that user is logged in
     */
    @Test
    public void login() {
        assertTrue(landingPage
                .login(getData().userEmail(), getData().userPassword())
                .openAvatarMenu()
                .getLogoutElement().isDisplayed()); //check Logout is displayed
    }

    /**
     * Login with correct data but with different cases and spaces in the email and check that user is logged in
     */
    @Test
    public void loginWithDifCasesSpacesEmail() {
        assertTrue(landingPage
                .login("     "+getData().userEmailDifferentCases()+"        ", getData().userPassword())
                .openAvatarMenu()
                .getLogoutElement().isDisplayed()); //check Logout is displayed
    }

    /**
     * Login with correct email but with different cases in the password
     */
    @Test
    public void loginWithDifCasesPassword() {
        landingPage.tryLogin(getData().userEmail(), getData().userPasswordDifferentCases());
        assertTrue(landingPage.checkErrorMsg().contains("One or both of your email/password was incorrect"));
    }

    /**
     * Login with correct email but incorrect password (with spaces)
     */
    @Test
    public void loginWrongPassword() {
        landingPage.tryLogin(getData().userEmail(), "    "+getData().userPassword()+"     ");
        assertTrue(landingPage.checkErrorMsg().contains("One or both of your email/password was incorrect"));
    }

    /**
     * Login with correct password but incorrect email
     */
    @Test
    public void loginWrongEmail() {
        landingPage.tryLogin("test@test.com", getData().userPassword());
        assertTrue(landingPage.checkErrorMsg().contains("One or both of your email/password was incorrect"));
    }

    /**
     * Login with correct password in email field and email in password field
     */
    @Test
    public void loginMixedFields() {
        landingPage.tryLogin(getData().userPassword(), getData().userEmail());
        assertTrue(landingPage.checkErrorInput().contains("Email is invalid"));
    }

    /**
     * Login with not existed data and check error dialog is displayed
     */
    @ParameterizedTest
    @CsvSource({
            "test@email.com, passw0rd123",
            "test@email.com, пароль746529",
            "email~!#$%^*()_✨✅@mail.com, ~!#$%^*()_✨✅",
            "аdmin@test.ru, пароль" //a is not a latin letter
    })
    public void loginWithNotExistedData(String email, String password) {
        landingPage.tryLogin(email, password);
        assertTrue(landingPage.checkErrorMsg().contains("One or both of your email/password was incorrect"));
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
     * Login with incorrect email and check that error message in the form is displayed
     */
    @ParameterizedTest
    @CsvFileSource(resources = "incorrectEmails.csv", numLinesToSkip = 0)
    public void loginWithIncorrectData(String email) {
        landingPage.tryLogin(email, getData().userPassword());
        assertTrue(landingPage.checkErrorInput().contains("Email is invalid"));
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
        assertTrue(landingPage.checkErrorInput().contains("Password strength"));
    }

    /**
     * Create account (assert with error message because creation is disabled)
     * That's why here is scenario with existing account
     */
    @Test
    public void tryCreateExistingAccount() {
        landingPage.tryCreateAccount("     "+getData().userEmailDifferentCases()+"        ", getData().userPassword());
        assertTrue(landingPage.checkErrorMsg().contains("User with this email is already registered."));
    }

    /**
     * Create account with incorrect data and check that error message in the form is displayed
     */
    @ParameterizedTest
    @CsvFileSource(resources = "incorrectDataCreateAccount.csv", numLinesToSkip = 0)
    public void tryCreateAccountWithIncorrectData(String email, String password) {
        landingPage.tryCreateAccount(email, password);
        assertTrue(landingPage.getErrorInput().isDisplayed());
    }

    /**
     * Check that user can go to the creatе form and back to the log in form
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

    /**
     * Check header link: open create account form and then main page by clicking header
     */
    @Test
    public void checkHeaderLink() {
        landingPage.clickCreateAccount();
        landingPage.clickHeader();
        assertTrue(landingPage.getLoginBtn().isDisplayed()); // log in form should be opened
    }

    /**
     * Check all titles are displayed
     */
    @Test
    public void checkTitles() {
        assertTrue(landingPage.getLandingTitle().getText().contains("Datalore\nis a collaborative\ndata science\nplatform"));
        assertTrue(landingPage.getLandingSubtitle().getText().contains("Get support"));
        assertTrue(landingPage.getDataloreHeader().isDisplayed());
    }

}
