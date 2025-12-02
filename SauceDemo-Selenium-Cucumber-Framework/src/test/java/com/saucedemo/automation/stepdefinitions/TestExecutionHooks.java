package com.saucedemo.automation.stepdefinitions;

import io.cucumber.java.*;
import io.qameta.allure.Allure;
import com.saucedemo.automation.utilities.WebDriverFactory;
import com.saucedemo.automation.utilities.ScreenshotCaptureUtility;

/**
 * TestExecutionHooks - Cucumber hooks for test setup and teardown
 * Manages WebDriver lifecycle and screenshot capture on test failure
 * Executes before and after each test scenario
 */
public class TestExecutionHooks {

    /**
     * Setup hook - Executes before each scenario
     * Initializes WebDriver and logs scenario start
     * 
     * @param scenario Cucumber scenario object
     */
    @Before
    public void setupBeforeScenario(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Test Scenario: " + scenario.getName());
        System.out.println("========================================");

        // Initialize WebDriver
        WebDriverFactory.initializeWebDriver();

        Allure.step("Test setup completed successfully");
    }

    /**
     * Teardown hook - Executes after each scenario
     * Captures screenshot on failure and quits WebDriver
     * 
     * @param scenario Cucumber scenario object
     */
    @After
    public void teardownAfterScenario(Scenario scenario) {
        // Capture screenshot if scenario failed
        if (scenario.isFailed()) {
            System.out.println("Test Scenario FAILED: " + scenario.getName());
            try {
                ScreenshotCaptureUtility.captureScreenshotOnTestFailure(
                        WebDriverFactory.getWebDriver(),
                        scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("Test Scenario PASSED: " + scenario.getName());
        }

        // Quit WebDriver
        WebDriverFactory.quitWebDriver();

        System.out.println("========================================");
        System.out.println("Finished Test Scenario: " + scenario.getName());
        System.out.println("========================================\n");
    }

    /**
     * After all tests hook - Executes once after all scenarios
     * Performs final cleanup operations
     */
    @AfterAll
    public static void cleanupAfterAllTests() {
        System.out.println("========================================");
        System.out.println("All Test Scenarios Completed");
        System.out.println("========================================");
    }
}
