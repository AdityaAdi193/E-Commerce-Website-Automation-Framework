package com.ecom.framework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtils {
    private static final String DIR = "test-output/screenshots";

    private ScreenshotUtils() {}

    /** Saves a PNG under test-output/screenshots and returns its path. */
    public static String capture(WebDriver driver, String testName) {
        try {
            Files.createDirectories(Paths.get(DIR));
            String stamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            String safe = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path target = Paths.get(DIR, safe + "_" + stamp + ".png");
            Path src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(src, target, StandardCopyOption.REPLACE_EXISTING);
            return target.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not save screenshot", e);
        }
    }

    /** Base64 string, used to embed the image directly in the Extent report. */
    public static String asBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
