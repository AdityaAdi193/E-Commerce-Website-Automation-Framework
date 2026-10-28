package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By EMAIL = By.id("Email");
    private static final By PASSWORD = By.id("Password");
    private static final By LOGIN_BUTTON = By.cssSelector("input.login-button");
    private static final By ERROR = By.cssSelector(".validation-summary-errors");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        open("/login");
        return this;
    }

    public HomePage loginAs(String email, String password) {
        type(EMAIL, email);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
        return new HomePage(driver);
    }

    public String getErrorMessage() {
        return text(ERROR);
    }
}
