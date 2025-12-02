package utils;

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
 * ScreenshotUtil - Utility for taking and managing screenshots
 */
public class ScreenshotUtil {

    /**
     * Take screenshot and save to file
     * 
     * @param driver         WebDriver instance
     * @param screenshotName Name for the screenshot
     * @return Path to saved screenshot
     */
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getScreenshotPath() + fileName;

        try {
            // Create screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get(ConfigReader.getScreenshotPath()));

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);

            Files.copy(source.toPath(), destination.toPath());

            System.out.println("Screenshot saved: " + screenshotPath);
            return screenshotPath;

        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot and attach to Allure report
     * 
     * @param driver         WebDriver instance
     * @param screenshotName Name for the screenshot
     */
    public static void attachScreenshotToAllure(WebDriver driver, String screenshotName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(screenshotName, "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.err.println("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }

    /**
     * Take screenshot on test failure
     * 
     * @param driver   WebDriver instance
     * @param testName Test name
     */
    public static void captureFailureScreenshot(WebDriver driver, String testName) {
        if (ConfigReader.takeScreenshotOnFailure()) {
            String screenshotPath = takeScreenshot(driver, "FAILED_" + testName);
            attachScreenshotToAllure(driver, "Failure Screenshot: " + testName);

            if (screenshotPath != null) {
                System.out.println("Failure screenshot captured: " + screenshotPath);
            }
        }
    }
}
