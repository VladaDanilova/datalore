package com.datalore.qa.api.common;

import com.datalore.qa.config.DataProvider;
import com.datalore.qa.config.TestDataAndProperties;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class TestBase {

    @BeforeAll
    static void beforeAll() throws Exception {
        // enable logging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Just resets {@code RestAssured} config to defaults.
     *
     * @throws Exception
     */
    @AfterAll
    static void afterAll() throws Exception {
        RestAssured.reset();
    }

    /**
     * Get data from properties
     */
    protected TestDataAndProperties getData() {
        return DataProvider.get();
    }

    public String getAuthPayload(String email, String password) {
        return "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
    }
}
