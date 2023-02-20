package com.datalore.qa.ui.stepsDefinitions;

import org.jbehave.core.annotations.Given;

public class LandingSteps extends BaseSteps {

    @Given("user opens Login page")
    public void openLoginPage() throws Throwable {
        getDriver().get(getData().basePath());
    }




}
