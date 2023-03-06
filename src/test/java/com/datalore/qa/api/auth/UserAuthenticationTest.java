package com.datalore.qa.api.auth;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.datalore.qa.api.common.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;

/**
 * @author vdanilova
 * API tests for datalore auth
 */

public class UserAuthenticationTest extends TestBase {
    /**
     * Check that login page is opened
     */
    @Test
    public void getLoginPage() {
        given()
                .when()
                .get(getData().basePath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(containsString("<body"), containsString("data-route=\"LOGIN\""));
    }

    /**
     * Login with correct data
     */
    @Test
    public void loginWithCorrectData() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .body(getAuthPayload(getData().userEmail(), getData().userPassword()))
                .when()
                .post(getData().authPath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(containsString("OK"));
    }

    /**
     * Try to Login with incorrect data
     */
    @ParameterizedTest
    @CsvSource({
            "тест@почта.ру,пароль",
            ",passw0rd",
            "test,",
            ",",
            "LoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanusLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdol, orsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanciduntLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneansLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneanLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAeneananLoremipsumdolorsitametconsectetueradipiscingelitAeneancommodoligulaegetdolorAenean",
            "test @test.com,passw0rd",
            " @   .com,passw0rd",
            "test@email.com,passw0rd123",
            "test@email.com,"
    })
    public void loginWithIncorrectData(String email, String password) {
        given()
                .contentType(ContentType.JSON)
                .with()
                .body(getAuthPayload(email, password))
                .when()
                .post(getData().authPath())
                .then()
                .assertThat()
                .body(containsString("INCORRECT"));
    }

    /**
     * Try to Login without email field (Bad request)
     */
    @Test
    public void loginWithoutEmail() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .body(getEmailPayload(getData().userEmail()))
                .when()
                .post(getData().authPath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Try to Login without password field (Bad request)
     */
    @Test
    public void loginWithoutPassword() {
        given()
                .contentType(ContentType.JSON)
                .with()
                .body(getPasswordPayload(getData().userPassword()))
                .when()
                .post(getData().authPath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}