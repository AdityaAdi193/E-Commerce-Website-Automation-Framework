package com.ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage {
    private static final By PRODUCT_TITLES = By.cssSelector(".product-item .product-title a");
    private static final By NO_RESULT_MSG = By.cssSelector(".search-results .result");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getResultCount() {
        wait.either(PRODUCT_TITLES, NO_RESULT_MSG);
        return driver.findElements(PRODUCT_TITLES).size();
    }

    public ProductPage openProduct(String productName) {
        click(By.xpath("//h2[contains(@class,'product-title')]/a[normalize-space()='" + productName + "']"));
        return new ProductPage(driver);
    }
}
