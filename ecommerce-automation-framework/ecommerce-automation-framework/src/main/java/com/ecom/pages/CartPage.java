package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {
    private static final By ITEM_NAMES = By.cssSelector("table.cart td.product a.product-name");
    private static final By REMOVE_CHECKBOXES = By.cssSelector("table.cart input[name='removefromcart']");
    private static final By UPDATE_CART = By.cssSelector("input[name='updatecart']");
    private static final By TERMS = By.id("termsofservice");
    private static final By CHECKOUT = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        open("/cart");
        return this;
    }

    public List<String> getItemNames() {
        return driver.findElements(ITEM_NAMES).stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toList());
    }

    public boolean isProductInCart(String name) {
        return getItemNames().contains(name);
    }

    /** Empties the cart so each test starts from a known state. */
    public CartPage clearCart() {
        List<WebElement> boxes = driver.findElements(REMOVE_CHECKBOXES);
        if (!boxes.isEmpty()) {
            boxes.forEach(WebElement::click);
            click(UPDATE_CART);
        }
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        click(TERMS);
        click(CHECKOUT);
        return new CheckoutPage(driver);
    }
}
