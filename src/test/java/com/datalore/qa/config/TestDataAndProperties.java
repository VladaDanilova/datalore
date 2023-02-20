package com.datalore.qa.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:env",
        "system:properties",
        "classpath:application.properties",
        "classpath:sensitive.properties"}) // sensitive - DO NOT put into Git/VCS.
public interface TestDataAndProperties extends Config {

    int implicitlyWait();
    int pageLoadTimeout();
    int waitTimeout();

    String basePath();
    String authPath();
    String userEmail();
    String userPassword();

}
