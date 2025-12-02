package com.saucedemo.automation.utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotCaptureUtility - Utility for capturing and managing test
 * screenshots
 * Provides methods for taking screenshots and attaching them to Allure reports
 * Automatically saves screenshots on test failures
 */
public class ScreenshotCaptureUtility {

    /**
     * Capture screenshot and save to file
     * 
     * @param driver         WebDriver instance
     * @param screenshotName Name for the screenshot file
     * @return Path to saved screenshot file
     */
    public static String captureAndSaveScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = ConfigurationReader.getScreenshotDirectoryPath() + fileName;

        try {
            // Create screenshots directory if doesn't exist
            Files.createDirectories(Paths.get(ConfigurationReader.getScreenshotDirectoryPath()));

            // Capture screenshot
            TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
            File sourceFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath);

            Files.copy(sourceFile.toPath(), destinationFile.toPath());

            System.out.println("Screenshot saved successfully: " + screenshotPath);
            return screenshotPath;

        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot and attach to Allure report
     * 
     * @param driver         WebDriver instance
     * @param screenshotName Name for the screenshot attachment
     */
    public static void attachScreenshotToAllureReport(WebDriver driver, String screenshotName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(screenshotName, "image/png",
                    new ByteArrayInputStream(screenshotBytes), "png");
        } catch (Exception e) {
            System.err.println("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }

    /**
     * Capture screenshot when test fails
     * Saves screenshot to file and attaches to Allure report
     * 
     * @param driver   WebDriver instance
     * @param testName Name of the failed test
     */
    public static void captureScreenshotOnTestFailure(WebDriver driver, String testName) {
        if (ConfigurationReader.isScreenshotOnFailureEnabled()) {
            String screenshotPath = captureAndSaveScreenshot(driver, "FAILED_" + testName);
            attachScreenshotToAllureReport(driver, "Test Failure Screenshot: " + testName);

            if (screenshotPath != null) {
                System.out.println("Failure screenshot captured: " + screenshotPath);
            }
        }
    }
}
