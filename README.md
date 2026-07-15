# Test Automation Framework Practical Task

This project is a Selenium WebDriver test automation framework created with Java, Maven and TestNG.

The framework demonstrates layered architecture, Page Object and Page Factory patterns, configurable
browser and environment selection, logging, screenshots on failure and automated execution through
GitHub Actions.

## Technology Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- Log4j2
- GitHub Actions
- Page Object
- Page Factory

## System Under Test

The Internet demo application:

https://the-internet.herokuapp.com/

## Project Structure

```text
.github/
└── workflows/
    └── ta-framework-tests.yml

src/
└── test/
    ├── java/
    │   ├── config/
    │   │   └── ConfigManager.java
    │   ├── driver/
    │   │   ├── Browser.java
    │   │   ├── DriverFactory.java
    │   │   └── DriverManager.java
    │   ├── model/
    │   │   └── User.java
    │   ├── pages/
    │   │   ├── AbstractPage.java
    │   │   ├── LoginPage.java
    │   │   ├── SecureAreaPage.java
    │   │   ├── AlertsPage.java
    │   │   └── WindowsPage.java
    │   ├── tests/
    │   │   ├── BaseTest.java
    │   │   ├── LoginTest.java
    │   │   ├── AlertsTest.java
    │   │   └── WindowsTest.java
    │   └── utils/
    │       └── ScreenshotUtils.java
    └── resources/
        ├── config/
        │   ├── local.properties
        │   └── qa.properties
        ├── suites/
        │   ├── smoke.xml
        │   └── regression.xml
        └── log4j2.xml
```

## Framework Architecture

### Core Layer

The Core Layer contains technical framework components:

- WebDriver creation and management
- Browser selection
- Environment configuration
- Explicit waits
- Logging configuration
- Screenshot creation

### Business Layer

The Business Layer contains:

- Page Objects
- Page Factory elements
- Business model objects
- User actions such as login, logout, alert handling and window switching

### Test Layer

The Test Layer contains:

- TestNG tests
- Assertions
- Test setup and cleanup
- Smoke and Regression suites

## Automated Test Scenarios

### Successful Login and Logout

The test:

1. Opens the Login page.
2. Verifies that the Login page is displayed.
3. Enters valid user credentials.
4. Submits the login form.
5. Verifies that the Secure Area page is opened.
6. Verifies the successful login message.
7. Logs out.
8. Verifies that the Login page is opened again.
9. Verifies the successful logout message.

### JavaScript Alerts Handling

The test:

1. Opens the JavaScript Alerts page.
2. Verifies that the page is displayed.
3. Opens a JavaScript alert.
4. Verifies the alert text.
5. Accepts the alert.
6. Verifies the result message.
7. Opens a JavaScript confirmation alert.
8. Verifies the confirmation text.
9. Dismisses the confirmation.
10. Verifies the result message.

### Multiple Windows Handling

The test:

1. Opens the Multiple Windows page.
2. Verifies that the page is displayed.
3. Saves the original window handle.
4. Opens a new browser window.
5. Switches to the new window.
6. Verifies the text in the new window.
7. Closes the new window.
8. Switches back to the original window.
9. Verifies that the original page is displayed.

## Environment Configuration

Environment files are located in:

```text
src/test/resources/config
```

### Local Environment

```properties
base.url=https://the-internet.herokuapp.com
browser=chrome
headless=false
explicit.timeout=10
page.load.timeout=30
```

### QA Environment

```properties
base.url=https://the-internet.herokuapp.com
browser=firefox
headless=true
explicit.timeout=15
page.load.timeout=40
```

The environment is selected using:

```text
-Denvironment=local
```

or:

```text
-Denvironment=qa
```

The default environment is `local`.

## Test Suites

### Smoke Suite

The Smoke Suite contains the critical login and logout scenario:

```text
src/test/resources/suites/smoke.xml
```

### Regression Suite

The Regression Suite contains all automated scenarios:

```text
src/test/resources/suites/regression.xml
```

## Running Tests

### Run the Default Suite

```bash
mvn clean test
```

### Run Smoke Tests

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/smoke.xml
```

### Run Regression Tests

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/regression.xml
```

### Run Tests in the QA Environment

```bash
mvn clean test -Denvironment=qa
```

### Override Browser and Headless Mode

```bash
mvn clean test \
  -Denvironment=local \
  -Dbrowser=firefox \
  -Dheadless=true \
  -DsuiteXmlFile=src/test/resources/suites/smoke.xml
```

### PowerShell Example

```powershell
& "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2025.2.6.2\plugins\maven\lib\maven3\bin\mvn.cmd" clean test "-Denvironment=local" "-Dbrowser=firefox" "-Dheadless=true" "-DsuiteXmlFile=src/test/resources/suites/smoke.xml"
```

## Supported Run Parameters

| Parameter      | Example               | Description                      |
|----------------|-----------------------|----------------------------------|
| `environment`  | `local` or `qa`       | Selects the properties file      |
| `browser`      | `chrome` or `firefox` | Overrides the configured browser |
| `headless`     | `true` or `false`     | Controls browser visibility      |
| `suiteXmlFile` | Path to XML suite     | Selects the TestNG suite         |

System properties passed through Maven override values from the environment properties file.

## Wait Strategy

The framework uses explicit waits through `WebDriverWait`.

Implicit wait is disabled to avoid conflicts between implicit and explicit waits.

## Logging

Log4j2 is used to log important framework and test actions.

The framework uses:

- `INFO` for important business actions
- `DEBUG` for technical details
- `ERROR` for failures and exceptions
- `WARN` for skipped tests and configuration warnings

Logs are written to:

```text
target/logs/automation.log
```

Logs are also displayed in the console.

Rolling log files are created daily or when the current log reaches the configured size limit.

## Screenshots

When a test fails, the framework automatically creates a screenshot before closing the browser.

Screenshots are stored in:

```text
target/screenshots
```

The screenshot filename contains the failed test name and timestamp.

## Test Reports

Maven Surefire reports are stored in:

```text
target/surefire-reports
```

## GitHub Actions

The workflow is located at:

```text
.github/workflows/ta-framework-tests.yml
```

The workflow runs automatically on:

- Push to the `main` branch
- Pull requests to the `main` branch
- Manual workflow execution

GitHub Actions performs the following steps:

1. Checks out the repository.
2. Configures Java 17.
3. Restores the Maven dependency cache.
4. Runs the Regression Suite in Chrome headless mode.
5. Adds test results to the GitHub Actions job summary.
6. Uploads reports, logs and screenshots as artifacts.

The workflow uploads:

```text
target/surefire-reports
target/logs
target/screenshots
```

The artifact name is:

```text
regression-test-artifacts
```

## Covered Requirements

- WebDriverManager supports multiple browsers.
- Chrome and Firefox are supported.
- Page Object and Page Factory patterns are implemented.
- An abstract base page is implemented.
- A business model object is used for user credentials.
- At least two environment property files are provided.
- Smoke and Regression TestNG suites are provided.
- Browser, environment, headless mode and suite can be selected at runtime.
- Explicit waits are used.
- Screenshots are created automatically after test failures.
- Log4j2 logs important actions using INFO, DEBUG, WARN and ERROR levels.
- Logs are written to the console and rolling files.
- GitHub Actions automatically runs Regression tests.
- Test reports, logs and screenshots are saved as workflow artifacts.