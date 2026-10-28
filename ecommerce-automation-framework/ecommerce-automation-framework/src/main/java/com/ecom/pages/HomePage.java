package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By SEARCH_BOX = By.id("small-searchterms");
    private static final By SEARCH_BUTTON = By.cssSelector("input.search-box-button");
    private static final By ACCOUNT_LINK = By.cssSelector(".header-links a.account");
    private static final By LOGOUT_LINK = By.cssSelector(".header-links a.ico-logout");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        open("/");
        return this;
    }

    public SearchResultsPage search(String term) {
        type(SEARCH_BOX, term);
        click(SEARCH_BUTTON);
        return new SearchResultsPage(driver);
    }

    public boolean isLoggedIn() {
        return isDisplayed(LOGOUT_LINK);
    }

    public String getLoggedInUser() {
        return text(ACCOUNT_LINK);
    }

    public void logout() {
        click(LOGOUT_LINK);
    }
}
