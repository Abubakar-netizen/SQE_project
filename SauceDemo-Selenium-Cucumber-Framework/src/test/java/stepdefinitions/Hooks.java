package stepdefinitions;

import io.cucumber.java.*;
import io.qameta.allure.Allure;
import utils.DriverManager;
import utils.RedisHelper;
import utils.ScreenshotUtil;

/**
 * Hooks - Before and After hooks for test execution
 */
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("========================================");

        // Initialize driver
        DriverManager.initializeDriver();

        Allure.step("Test Setup Completed");
    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if test failed
        if (scenario.isFailed()) {
            System.out.println("Scenario FAILED: " + scenario.getName());
            try {
                ScreenshotUtil.captureFailureScreenshot(
                        DriverManager.getDriver(),
                        scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("Scenario PASSED: " + scenario.getName());
        }

        // Quit driver
        DriverManager.quitDriver();

        System.out.println("========================================");
        System.out.println("Finished Scenario: " + scenario.getName());
        System.out.println("========================================\n");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("========================================");
        System.out.println("All Tests Completed");
        System.out.println("========================================");

        // Close Redis connection
        try {
            RedisHelper.close();
        } catch (Exception e) {
            System.err.println("Error closing Redis: " + e.getMessage());
        }
    }
}
