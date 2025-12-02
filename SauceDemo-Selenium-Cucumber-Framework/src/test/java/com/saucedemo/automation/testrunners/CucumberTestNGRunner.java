package com.saucedemo.automation.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * CucumberTestNGRunner - Main test runner for Cucumber BDD tests
 * Integrates Cucumber with TestNG framework and Allure reporting
 * Configures feature files, step definitions, and reporting plugins
 */
@CucumberOptions(features = "src/test/resources/features", glue = {
        "com.saucedemo.automation.stepdefinitions" }, plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber-json-report.json",
                "junit:target/cucumber-reports/cucumber-junit-report.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }, tags = "@smoke or @regression", monochrome = true, dryRun = false, publish = false)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

    /**
     * Enable parallel execution of scenarios
     * Set parallel = true for concurrent test execution
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
