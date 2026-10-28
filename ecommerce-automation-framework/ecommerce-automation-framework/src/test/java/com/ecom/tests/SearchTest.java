package com.ecom.tests;

import com.ecom.base.BaseTest;
import com.ecom.framework.CsvUtils;
import com.ecom.pages.HomePage;
import com.ecom.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @DataProvider(name = "searchData")
    public Object[][] searchData() {
        return CsvUtils.read("testdata/search.csv");
    }

    @Test(dataProvider = "searchData", description = "Product search returns expected number of results")
    public void searchProduct(String term, String minResults) {
        SearchResultsPage results = new HomePage(driver()).open().search(term);
        int count = results.getResultCount();
        int expectedMin = Integer.parseInt(minResults);

        if (expectedMin == 0) {
            Assert.assertEquals(count, 0, "No results expected for: " + term);
        } else {
            Assert.assertTrue(count >= expectedMin,
                    "Expected at least " + expectedMin + " result(s) for '" + term + "' but got " + count);
        }
    }
}
