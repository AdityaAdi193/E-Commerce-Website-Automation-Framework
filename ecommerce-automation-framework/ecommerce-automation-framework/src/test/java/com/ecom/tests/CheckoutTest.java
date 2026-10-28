package com.ecom.tests;

import com.ecom.base.BaseTest;
import com.ecom.framework.CsvUtils;
import com.ecom.pages.CartPage;
import com.ecom.pages.CheckoutPage;
import com.ecom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return CsvUtils.read("testdata/checkout.csv");
    }

    @Test(dataProvider = "checkoutData", description = "End-to-end: login > search > cart > checkout")
    public void endToEndCheckout(String country, String city, String address, String zip, String phone) {
        loginWithConfiguredUser();
        new CartPage(driver()).open().clearCart();

        new HomePage(driver()).open()
                .search("laptop")
                .openProduct("14.1-inch Laptop")
                .addToCart();

        CheckoutPage checkout = new CartPage(driver()).open().proceedToCheckout();
        String confirmation = checkout.placeOrder(country, city, address, zip, phone);

        Assert.assertTrue(confirmation.contains("successfully processed"),
                "Unexpected confirmation text: " + confirmation);
    }
}
