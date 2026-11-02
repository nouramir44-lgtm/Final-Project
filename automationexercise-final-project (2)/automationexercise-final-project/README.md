# Web Automation Final Project – AutomationExercise.com

Automated tests for [https://automationexercise.com](https://automationexercise.com) using
**Java 17, Selenium WebDriver 4, TestNG, Cucumber (BDD) and Maven**, built with the
**Page Object Model (POM)**. They cover registration, login, product browsing, cart and checkout,
and form submissions.

## Tools used

Java 17 · Selenium WebDriver 4 · TestNG · Cucumber BDD · Maven · Page Object Model ·
WebDriverManager · Cucumber Reports (HTML / JSON / JUnit XML)

## Scenario coverage

| # | Scenario (project brief) | Feature file | Expected result |
|---|--------------------------|--------------|-----------------|
| 1 | User can register successfully | `01_registration.feature` | "ACCOUNT CREATED!" message appears |
| 2 | Registered user can log in successfully | `02_login.feature` | "Logged in as `<username>`" |
| 3 | Add products to cart and complete checkout | `05_cart_checkout.feature` | Product added message; "Your order has been placed successfully!" |
| 4 | Contact Us form | `06_contact_us.feature` | "Success! Your details have been submitted successfully." |
| 5 | Test Cases page | `07_test_cases.feature` | Test cases load successfully |
| 6 | All products and product details | `04_products.feature` | Correct product details appear |
| 7 | Logout | `03_logout.feature` | Redirect to the Login/Signup page |
| 8 | Login fails with incorrect data | `02_login.feature` | "Your email or password is incorrect!" |

### Negative scenarios

| Negative scenario | Feature file |
|-------------------|--------------|
| Signup with existing email | `01_registration.feature` |
| Login with incorrect password | `02_login.feature` |
| Checkout with empty cart | `05_cart_checkout.feature` |
| Invalid Contact Us submission | `06_contact_us.feature` |
| Invalid search text | `04_products.feature` |
| Invalid payment info | `05_cart_checkout.feature` |
| Attempt checkout without login | `05_cart_checkout.feature` |

## Project structure

```text
src
├── main/java/com/automationexercise
│   ├── config      ConfigReader (config.properties + -D overrides)
│   ├── driver      DriverFactory (Chrome default, Firefox/Edge optional)
│   ├── model       User, PaymentDetails, ContactMessage
│   ├── pages       BasePage + one page object per page
│   └── utils       AdHandler, TestDataFactory, UserApi
└── test
    ├── java/com/automationexercise
    │   ├── hooks       Hooks (browser start/stop, screenshots, account cleanup)
    │   ├── runners     TestRunner (TestNG + Cucumber)
    │   ├── steps       step definitions, TestContext, AccountSetup
    │   └── tests       plain TestNG tests (no browser)
    └── resources
        ├── config      config.properties
        └── features    *.feature files
```

## Prerequisites

JDK 17+, Maven 3.8+, Google Chrome (WebDriverManager downloads the matching ChromeDriver).

## Run

```bash
# Headed Chrome (default)
mvn clean test

# Headless
mvn clean test -Dheadless=true

# Only smoke scenarios / only negative scenarios
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@negative"

# Other browser
mvn clean test -Dbrowser=firefox
```

Defaults live in `src/test/resources/config/config.properties`.

## Reports

- `target/cucumber-reports/cucumber.html`
- `target/cucumber-reports/cucumber.json`
- `target/cucumber-reports/cucumber.xml`
- `target/surefire-reports`

Failed scenarios attach a screenshot to the Cucumber report.

## Demo video

`<ADD DEMO VIDEO LINK HERE BEFORE SUBMISSION>`

## Design notes

- **Unique data:** every run generates a unique name and email, so tests never depend on existing accounts.
- **Fast setup and cleanup:** preconditions ("a registered user exists") create the account through the site's
  REST API (`/api/createAccount`) and fall back to the UI if the API is unreachable. After each scenario the
  account is deleted via `/api/deleteAccount`.
- **Dependency injection:** step classes share a per-scenario `TestContext` through Cucumber PicoContainer.
- **Invalid payment / invalid contact form:** the site does not validate card or email content on the server, so
  these negative scenarios use the browser's HTML5 validation on required fields (empty card data, malformed email)
  and assert the form is not submitted.
- **Order confirmation:** the green "Your order has been placed successfully!" alert is shown briefly, so the
  check passes when either the alert or the "ORDER PLACED!" page appears.
- **Dummy data only:** card details are test values.

### Popup / advertisement handling

`AdHandler` (used by `BasePage`) is best-effort and never fails a test when nothing is present. It:

- accepts unexpected JavaScript alerts;
- closes unexpected popup windows/tabs and returns to the main window;
- removes common Google/advertising iframes and overlays, including `#google_vignette` interstitials;
- is re-run, with retries, when a click is intercepted.

## Clean code guidelines followed

- Meaningful, descriptive names
- Page Object Model for maintainability
- Single-responsibility methods
- Consistent formatting and organized structure
