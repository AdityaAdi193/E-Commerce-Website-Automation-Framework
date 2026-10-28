package com.ecom.base;

import com.ecom.framework.ConfigReader;
import com.ecom.framework.DriverFactory;
import com.ecom.pages.HomePage;
import com.ecom.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver().get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }

    protected HomePage loginWithConfiguredUser() {
        return new LoginPage(driver()).open()
                .loginAs(ConfigReader.get("email"), ConfigReader.get("password"));
    }
}
