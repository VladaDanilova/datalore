Automation tests for Datalore landing page and authentication.  

# How-to run tests:  

## Clone a repository  
Save project directly from the GitHub or use $ git clone  
## Clear the target directory and build the project by running
```
mvn clean install
```
## Generate allure report by running
```
mvn allure:serve
```
# Files structure
This project contains next directories:   
api - contains api tests, config - contains configuration for all tests, ui - contains ui tests and pageObjects.   
The most part of configuration data is located in application.properties under resources. **You will also need to add sensitive.properties with personal data for authentication tests.**

# How else might I have done things?
Tests can be paralleled by using JUnit configuration or Selenium Grid cloud.  
It's possible to use JBehave or Cucumber if it's needed.
