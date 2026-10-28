package com.ecom.tests;

import com.ecom.base.BaseTest;
import com.ecom.framework.ConfigReader;
import com.ecom.framework.CsvUtils;
import com.ecom.pages.HomePage;
import com.ecom.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return CsvUtils.read("testdata/login.csv");
    }

    @Test(dataProvider = "loginData", description = "Login with valid and invalid credentials")
    public void login(String email, String password, String expected) {
        // "VALID" in the CSV is replaced by the credentials from config.properties / -D flags
        String user = "VALID".equals(email) ? ConfigReader.get("email") : email;
        String pass = "VALID".equals(password) ? ConfigReader.get("password") : password;

        LoginPage loginPage = new LoginPage(driver()).open();
        HomePage home = loginPage.loginAs(user, pass);

        if ("SUCCESS".equals(expected)) {
            Assert.assertTrue(home.isLoggedIn(), "User should be logged in");
            Assert.assertEquals(home.getLoggedInUser(), user);
        } else {
            Assert.assertTrue(loginPage.getErrorMessage().contains(expected),
                    "Expected error containing: " + expected);
        }
    }
}
