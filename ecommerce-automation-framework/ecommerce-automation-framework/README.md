# E-Commerce Website Automation Framework
Java 17 | Selenium WebDriver 4 | TestNG | Maven | Page Object Model | ExtentReports

## Prerequisites
1. JDK 17+            (java -version)
2. Apache Maven 3.8+  (mvn -version)
3. Google Chrome (or Firefox / Edge). Selenium Manager downloads the driver automatically.
4. An IDE (IntelliJ IDEA / Eclipse) - optional.

## Setup
1. Register a free account at https://demowebshop.tricentis.com/register
2. Put the credentials in src/main/resources/config.properties
   (or pass -Demail=... -Dpassword=... on the command line).

## Run
    mvn clean test
    mvn clean test -Dbrowser=firefox -Dheadless=true
    mvn clean test -Demail=me@mail.com -Dpassword=Secret123

## Output
- HTML report:  test-output/extent-report/ExtentReport.html
- Screenshots:  test-output/screenshots/   (captured on every failure, also embedded in report)

## Structure
    pages/        Page Objects (Home, Login, SearchResults, Product, Cart, Checkout)
    framework/    ConfigReader, DriverFactory (ThreadLocal), WaitUtils, ScreenshotUtils, CsvUtils
    base/         BaseTest (driver setup/teardown)
    listeners/    TestListener + ExtentManager (reporting, screenshots)
    tests/        LoginTest, SearchTest, CartTest, CheckoutTest (data-driven)
    resources/testdata/*.csv   test data
