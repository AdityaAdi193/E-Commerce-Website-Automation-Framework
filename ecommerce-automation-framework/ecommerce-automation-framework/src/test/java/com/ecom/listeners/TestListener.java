package com.ecom.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.ecom.framework.DriverFactory;
import com.ecom.framework.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Writes pass/fail/skip to the Extent report and captures a screenshot on failure. */
public class TestListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        Object[] params = result.getParameters();
        String name = result.getMethod().getMethodName() + (params.length > 0 ? " [" + params[0] + "]" : "");
        TEST.set(ExtentManager.get().createTest(name));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String path = ScreenshotUtils.capture(driver, result.getMethod().getMethodName());
            String base64 = ScreenshotUtils.asBase64(driver);
            TEST.get().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
            TEST.get().info("Screenshot saved: " + path);
        } else {
            TEST.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().skip(result.getThrowable() != null ? result.getThrowable().toString() : "Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.get().flush();
    }
}
