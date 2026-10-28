package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    private static final By BILLING_SELECT = By.id("billing-address-select");
    private static final By COUNTRY = By.id("BillingNewAddress_CountryId");
    private static final By CITY = By.id("BillingNewAddress_City");
    private static final By ADDRESS1 = By.id("BillingNewAddress_Address1");
    private static final By ZIP = By.id("BillingNewAddress_ZipPostalCode");
    private static final By PHONE = By.id("BillingNewAddress_PhoneNumber");

    private static final By BILLING_CONTINUE = By.cssSelector("#billing-buttons-container input.new-address-next-step-button");
    private static final By SHIPPING_ADDR_CONTINUE = By.cssSelector("#shipping-buttons-container input.new-address-next-step-button");
    private static final By SHIPPING_METHOD_CONTINUE = By.cssSelector("#shipping-method-buttons-container input.shipping-method-next-step-button");
    private static final By PAYMENT_METHOD_CONTINUE = By.cssSelector("#payment-method-buttons-container input.payment-method-next-step-button");
    private static final By PAYMENT_INFO_CONTINUE = By.cssSelector("#payment-info-buttons-container input.payment-info-next-step-button");
    private static final By CONFIRM = By.cssSelector("#confirm-order-buttons-container input.confirm-order-next-step-button");
    private static final By SUCCESS_TITLE = By.cssSelector(".order-completed .title strong");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /** Walks all one-page-checkout steps (COD payment) and returns the confirmation heading. */
    public String placeOrder(String country, String city, String address, String zip, String phone) {
        wait.clickable(BILLING_CONTINUE);

        // A saved address shows a dropdown; otherwise the "new address" form is displayed.
        if (driver.findElements(BILLING_SELECT).isEmpty()) {
            new Select(wait.visible(COUNTRY)).selectByVisibleText(country);
            type(CITY, city);
            type(ADDRESS1, address);
            type(ZIP, zip);
            type(PHONE, phone);
        }
        click(BILLING_CONTINUE);

        // "Ship to same address" may skip the shipping-address step, so handle both flows.
        wait.either(SHIPPING_ADDR_CONTINUE, SHIPPING_METHOD_CONTINUE);
        if (isDisplayed(SHIPPING_ADDR_CONTINUE)) {
            click(SHIPPING_ADDR_CONTINUE);
        }
        click(SHIPPING_METHOD_CONTINUE);
        click(PAYMENT_METHOD_CONTINUE);
        click(PAYMENT_INFO_CONTINUE);
        click(CONFIRM);
        return text(SUCCESS_TITLE);
    }
}
