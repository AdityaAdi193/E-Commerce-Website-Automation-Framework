E-Commerce Website Automation Framework

Test automation framework for an e-commerce website using Java, Selenium WebDriver, TestNG and Maven.

What it does
Automates login, product search, add to cart and checkout
Uses Page Object Model (POM)
Data-driven tests using CSV files
Generates an HTML report and takes screenshots on failure
Tech Stack
Java 17
Selenium WebDriver 4
TestNG
Maven
ExtentReports
Project Structure
src/main/java/com/ecom
    framework/   -> config, driver, wait, screenshot and CSV utilities
    pages/       -> page classes (Login, Search, Product, Cart, Checkout)
src/test/java/com/ecom
    base/        -> BaseTest
    listeners/   -> reporting and screenshot listener
    tests/       -> test classes
src/test/resources/testdata -> CSV test data
testng.xml
pom.xml
Prerequisites
JDK 17 or higher
Maven 3.8 or higher
Chrome browser
How to Run
Clone the repository
   git clone https://github.com/<your-username>/ecommerce-automation-framework.git
Register an account at https://demowebshop.tricentis.com
Add your email and password in src/main/resources/config.properties
Run the tests
   mvn clean test
Reports
HTML report: test-output/extent-report/ExtentReport.html
Screenshots: test-output/screenshots/
