# Selenium WebDriver Practical Task

## Technology Stack

- Java 17
- Maven
- Selenium WebDriver
- TestNG
- Page Object / Page Factory
- WebDriverManager

## System Under Test

The Internet demo application:

https://the-internet.herokuapp.com/

## Automated Test Scenarios

### Scenario 1: Successful Login and Logout

Steps:

1. Open the Login page.
2. Verify that the Login page is displayed.
3. Enter a valid username.
4. Enter a valid password.
5. Click the Login button.
6. Verify that the Secure Area page is opened.
7. Verify that the successful login message is displayed.
8. Click the Logout button.
9. Verify that the user is redirected to the Login page.
10. Verify that the successful logout message is displayed.

### Scenario 2: JavaScript Alerts Handling

Steps:

1. Open the JavaScript Alerts page.
2. Verify that the JavaScript Alerts page is displayed.
3. Click the JS Alert button.
4. Switch to the alert.
5. Verify the alert text.
6. Accept the alert.
7. Verify the result message.
8. Click the JS Confirm button.
9. Dismiss the confirm alert.
10. Verify the result message after dismissing the confirm alert.

### Scenario 3: Multiple Windows Handling

Steps:

1. Open the Multiple Windows page.
2. Verify that the Multiple Windows page is displayed.
3. Save the current browser window handle.
4. Click the "Click Here" link.
5. Wait until a new window is opened.
6. Switch to the new window.
7. Verify the text in the new window.
8. Close the new window.
9. Switch back to the original window.
10. Verify that the original window is active again.

## Covered Requirements

- Three linear automated scenarios are implemented.
- Different locator strategies are used: id, cssSelector, xpath, tagName, linkText.
- Auto-generated locators are avoided.
- WebDriver API is used for navigation, clicks, alerts, windows, browser management and switching.
- Implicit and explicit waits are used.
- Test scenarios are clear, stable and structured.
- Assertions are used in each test scenario.
- Page Object / Page Factory patterns are used.
- Abstract Page is implemented as a base class for pages.
- Code duplication is minimized.
- Page implementation details are hidden from tests.

## How to Run Tests

Tests can be run from IntelliJ IDEA Maven panel:

```bash
test