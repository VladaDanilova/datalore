package com.datalore.qa.api.auth;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.datalore.qa.api.common.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;

public class UserAuthenticationTest extends TestBase {

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

    @ParameterizedTest
    @CsvSource({
            "test@email.com, passw0rd123",
            " , passw0rd",
            "test@email.com, ",
            " , "
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