# Selenium Automation Framework – Automation Exercise

## Project Overview

This project is an end-to-end web automation testing framework developed using **Java, Selenium WebDriver, TestNG, Cucumber BDD, and Maven**.

The framework follows the **Page Object Model (POM)** design pattern to maintain clean, reusable, and maintainable test automation code.

The tests are performed on [Automation Exercise](https://automationexercise.com/), covering major e-commerce functionalities, including user registration, login, product browsing, shopping cart operations, checkout, and payment.

## Technologies & Tools

- **Programming Language:** Java 17
- **Automation Tool:** Selenium WebDriver 4.25.0
- **Test Framework:** TestNG 7.10.2
- **BDD Framework:** Cucumber 7.18.1
- **Build Tool:** Maven
- **Driver Management:** WebDriverManager 5.9.2
- **IDE:** IntelliJ IDEA
- **Browsers:** Google Chrome, Firefox, Microsoft Edge
- **Design Pattern:** Page Object Model (POM)

## Framework Architecture

The project uses a modular architecture to separate test logic, page interactions, and framework configuration.

```text
src
├── main
│   └── java
│       └── com
│           └── automation
│               ├── config
│               ├── driver
│               ├── pages
│               └── utils
│
└── test
    ├── java
    │   └── com
    │       └── automation
    │           ├── hooks
    │           ├── runners
    │           ├── stepdefinitions
    │           └── context
    │
    └── resources
        ├── config
        │   └── config.properties
        └── features
            ├── login.feature
            ├── registration.feature
            ├── products.feature
            ├── cart.feature
            └── checkout.feature
```

*Note: The structure above illustrates the framework organization; adjust package and feature filenames to match your actual repository.*

## Automated Test Scenarios

### 1. User Registration
- Register a new user.
- Validate registration details.
- Verify registration using an existing email.
- Verify account creation and account information.

### 2. Login & Logout
- Login with valid credentials.
- Login with incorrect credentials.
- Validate error messages.
- Logout successfully.

### 3. Products
- View all products.
- View product details.
- Search for products.
- Validate search results.
- Handle invalid search results.

### 4. Shopping Cart
- Add products to the cart.
- View cart contents.
- Validate product quantities and prices.
- Test empty cart behavior.

### 5. Checkout & Payment
- Proceed to checkout.
- Validate checkout without logging in.
- Complete checkout and payment.
- Test invalid payment information.

### 6. Contact Us
- Submit the contact form.
- Upload a file.
- Validate successful form submission.

### 7. Test Cases
- Navigate to the Test Cases page.
- Verify that the page loads successfully.

## Framework Features

- Page Object Model (POM) for reusable page interactions.
- Cucumber BDD for readable feature files and scenarios.
- TestNG for test execution and management.
- WebDriverManager for browser driver management.
- Configurable browser selection.
- Configurable headless execution.
- Reusable test context and driver management.
- API-based account setup and cleanup.
- Automatic screenshots when scenarios fail.
- HTML, JSON, and XML test reports.

## Prerequisites

Make sure the following are installed:

- Java JDK 17
- Apache Maven
- IntelliJ IDEA or another Java IDE
- Google Chrome, Firefox, or Microsoft Edge
- Git

## How to Run the Project

**1. Clone the repository**

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

**2. Navigate to the project directory**

```bash
cd YOUR_PROJECT_DIRECTORY
```

**3. Run the automated tests**

```bash
mvn clean test
```

**4. Run tests in headless mode**

```bash
mvn clean test -Dheadless=true
```

**5. Run tests using Firefox**

```bash
mvn clean test -Dbrowser=firefox
```

## Test Reports

After execution, test reports are generated in:

```text
target/
├── cucumber-reports/
│   ├── cucumber.html
│   ├── cucumber.json
│   └── cucumber.xml
│
└── surefire-reports/
```

The HTML report provides an overview of executed scenarios, their status, and test results.

Screenshots are captured for failed scenarios to help with debugging.

## Application Under Test

**Website:** https://automationexercise.com/

Automation Exercise is an e-commerce practice website designed for software testing and automation practice.

## Author

Developed as a Java Selenium Web Automation Testing Project.

**Technologies:** Java | Selenium | TestNG | Cucumber | Maven | POM
