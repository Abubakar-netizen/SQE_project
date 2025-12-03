# SQE\_project

\# UI Test Automation Framework



\[!\[Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)

\[!\[Selenium](https://img.shields.io/badge/Selenium-4.15.0-green.svg)](https://www.selenium.dev/)

\[!\[Cucumber](https://img.shields.io/badge/Cucumber-7.14.0-brightgreen.svg)](https://cucumber.io/)

\[!\[TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)

\[!\[Allure](https://img.shields.io/badge/Allure-2.24.0-yellow.svg)](https://docs.qameta.io/allure/)

##  Project Overview

This is a comprehensive *UI Test Automation Framework* built using *Selenium + Java + Cucumber (BDD)* for testing the [SauceDemo](https://www.saucedemo.com) web application.

### Key Features

 *18 Automated Test Cases* covering login, products, cart, checkout, and validation  
 *Page Object Model (POM)* design pattern for maintainability  
 *Behavior-Driven Development (BDD)* with Cucumber and Gherkin  
 *Allure Reporting* with screenshots on failure  
 *Data-Driven Testing* with Excel, MySQL Database, and Redis  
 *Multi-Browser Support* (Chrome, Firefox, Edge)  
 *Parallel Execution* capability with TestNG  
 *CI/CD Ready* with Maven build automation  

---

ui-test-framework/
│
├── src/
│   ├── main/java/
│   │   ├── pages/              # Page Object Model classes
│   │   │   ├── BasePage.java
│   │   │   ├── LoginPage.java
│   │   │   └── ...
│   │   │
│   │   └── utils/              # Utility classes
│   │       ├── ConfigReader.java
│   │       ├── DriverManager.java
│   │       ├── ExcelReader.java
│   │       └── ...
│   │
│   ├── main/resources/
│   │   └── config.properties   # Configuration file
│   │
│   └── test/
│       ├── java/
│       │   ├── runners/
│       │   │   └── TestRunner.java
│       │   │
│       │   └── stepdefinitions/
│       │       ├── LoginSteps.java
│       │       └── ...
│       │
│       └── resources/
│           ├── features/       # Gherkin feature files
│           │   ├── login.feature
│           │   └── ...
│           │
│           └── allure.properties
│
├── test-data/                  # External data sources
├── pom.xml                     # Maven configuration
├── testng.xml                  # TestNG configuration
└── README.md


---

##  Getting Started

### Prerequisites

Before running the tests, ensure you have the following installed:

- *Java JDK 11+* - [Download](https://www.oracle.com/java/technologies/downloads/)
- *Maven 3.6+* - [Download](https://maven.apache.org/download.cgi)
- *Google Chrome* (or Firefox/Edge)
- *MySQL 8.0+* (optional, for database tests)
- *Redis* (optional, for Redis tests)

### Installation

1. *Clone the repository*
   bash
   git clone <repository-url>
   cd SauceDemo-Selenium-Cucumber-Framework
   

2. *Install Maven dependencies*
   bash
   mvn clean install
   

3. *Configure Settings*
   Edit src/main/resources/config.properties to set your environment:
   properties
   app.url=https://www.saucedemo.com
   browser=chrome
   implicit.wait=10
   

---
##  Running Tests

### Run All Tests
bash
mvn clean test


### Run Specific Tags
You can run specific subsets of tests using Cucumber tags:
bash
# Run only smoke tests
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run regression tests
mvn clean test -Dcucumber.filter.tags="@regression"


---

## 📝 Writing and Executing Test Cases
### 1. Create a Feature File
Create a new .feature file in src/test/resources/features/. Use Gherkin syntax to describe the scenario.

*Example*: login.feature
gherkin
Feature: Login Functionality

  @smoke
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the products page


### 2. Create Page Objects
Create a class in src/main/java/pages/ to represent the web page. Use the Page Object Model pattern.

*Example*: LoginPage.java
java
public class LoginPage extends BasePage {
    
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}

### 3. Create Step Definitions
Create a class in src/test/java/stepdefinitions/ to map Gherkin steps to Java code.

*Example*: LoginSteps.java
java
public class LoginSteps {
    private LoginPage loginPage = new LoginPage(DriverManager.getDriver());

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        DriverManager.getDriver().get(ConfigReader.getProperty("app.url"));
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("I click the login button")
    public void i_click_login() {
        loginPage.clickLogin();
    }
}


### 4. Run the Test
Run the specific tag to verify your new test:
bash
mvn clean test -Dcucumber.filter.tags="@smoke"
## Design Decisions

### Why Selenium + Java?
- *Robustness*: Java provides strong typing and a vast ecosystem.
- *Community*: Selenium has the largest community support for web automation.

### Why Cucumber (BDD)?
- *Collaboration*: Gherkin syntax allows non-technical stakeholders (PO, QA, Dev) to understand test scenarios.
- *Living Documentation*: Feature files serve as up-to-date documentation of system behavior.

### Why Page Object Model (POM)?
- *Maintainability*: Locators are kept separate from test logic. If a UI element changes, you only update the Page class, not every test.
- *Reusability*: Page methods (e.g., login()) can be reused across multiple scenarios.

### Why TestNG?
- *Parallel Execution*: Native support for running tests in parallel.
- *Annotations*: Powerful control over test lifecycle (@BeforeMethod, @AfterSuite).

---

##  Data Integration & Reporting

### Integrating Data Sources
The framework supports multiple data sources. Helpers are located in src/main/java/utils/.

1.  *Excel*: Use ExcelReader.java to read from test-data/testdata.xlsx.
    java
    String data = ExcelReader.getCellData("Sheet1", 1, 0);
    
2.  *Database*: Use DatabaseHelper.java to query MySQL.
    java
    ResultSet rs = DatabaseHelper.executeQuery("SELECT * FROM users");
    
3.  *Redis*: Use RedisHelper.java for caching/session validation.
    java
    String value = RedisHelper.get("session_key");
    



