package com.ecom.pages;

import com.ecom.framework.ConfigReader;
import com.ecom.framework.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    protected void open(String path) {
        driver.get(ConfigReader.get("base.url") + path);
    }

    protected void click(By locator) {
        wait.clickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement e = wait.visible(locator);
        e.clear();
        if (text != null && !text.isEmpty()) e.sendKeys(text);
    }

    protected String text(By locator) {
        return wait.visible(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
