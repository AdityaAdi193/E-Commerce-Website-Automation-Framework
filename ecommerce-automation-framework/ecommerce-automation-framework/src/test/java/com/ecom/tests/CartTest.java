package com.ecom.tests;

import com.ecom.base.BaseTest;
import com.ecom.framework.CsvUtils;
import com.ecom.pages.CartPage;
import com.ecom.pages.HomePage;
import com.ecom.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @DataProvider(name = "cartData")
    public Object[][] cartData() {
        return CsvUtils.read("testdata/cart.csv");
    }

    @Test(dataProvider = "cartData", description = "Search a product and add it to the cart")
    public void addProductToCart(String searchTerm, String productName) {
        loginWithConfiguredUser();
        new CartPage(driver()).open().clearCart();

        ProductPage product = new HomePage(driver()).open()
                .search(searchTerm)
                .openProduct(productName)
                .addToCart();
        Assert.assertTrue(product.getSuccessMessage().contains("added to your shopping cart"));

        CartPage cart = new CartPage(driver()).open();
        Assert.assertTrue(cart.isProductInCart(productName), productName + " should be in the cart");
    }
}
