package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private static final By PRODUCT_NAME = By.cssSelector(".product-name h1");
    private static final By ADD_TO_CART = By.cssSelector("input.add-to-cart-button");
    private static final By SUCCESS_BAR = By.cssSelector("#bar-notification.success");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return text(PRODUCT_NAME);
    }

    public ProductPage addToCart() {
        click(ADD_TO_CART);
        return this;
    }

    public String getSuccessMessage() {
        return text(SUCCESS_BAR);
    }
}
