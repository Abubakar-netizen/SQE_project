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



