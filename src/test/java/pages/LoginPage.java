package pages;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

  private static final Logger LOGGER =
      LogManager.getLogger(LoginPage.class);

  private static final String LOGIN_PAGE_URL =
      BASE_URL + "/login";

  @FindBy(id = "username")
  private WebElement usernameInput;

  @FindBy(id = "password")
  private WebElement passwordInput;

  @FindBy(css = "button[type='submit']")
  private WebElement loginButton;

  @FindBy(css = "#flash")
  private WebElement flashMessage;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public LoginPage openPage() {
    LOGGER.info("Opening login page: {}", LOGIN_PAGE_URL);

    try {
      driver.navigate().to(LOGIN_PAGE_URL);

      wait.until(
          ExpectedConditions.visibilityOf(usernameInput)
      );

      LOGGER.info("Login page has been opened");

      return this;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to open login page: {}",
          LOGIN_PAGE_URL,
          exception
      );

      throw exception;
    }
  }

  public boolean isPageOpened() {
    LOGGER.debug("Checking whether login page is opened");

    try {
      boolean pageOpened =
          wait.until(
              ExpectedConditions.visibilityOf(usernameInput)
          ).isDisplayed()
              && wait.until(
              ExpectedConditions.visibilityOf(passwordInput)
          ).isDisplayed();

      LOGGER.debug(
          "Login page opened status: {}",
          pageOpened
      );

      return pageOpened;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Login page is not displayed correctly",
          exception
      );

      return false;
    }
  }

  public SecureAreaPage login(User user) {
    LOGGER.info(
        "Logging in with username: {}",
        user.getUsername()
    );

    try {
      LOGGER.debug("Entering username");

      usernameInput.clear();
      usernameInput.sendKeys(user.getUsername());

      LOGGER.debug("Entering password");

      passwordInput.clear();
      passwordInput.sendKeys(user.getPassword());

      LOGGER.debug("Clicking login button");

      wait.until(
          ExpectedConditions.elementToBeClickable(loginButton)
      ).click();

      LOGGER.info(
          "Login form has been submitted for username: {}",
          user.getUsername()
      );

      return new SecureAreaPage(driver);
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to log in with username: {}",
          user.getUsername(),
          exception
      );

      throw exception;
    }
  }

  public String getFlashMessageText() {
    LOGGER.debug("Getting login page flash message");

    try {
      String message = wait.until(
          ExpectedConditions.visibilityOf(flashMessage)
      ).getText();

      LOGGER.debug(
          "Login page flash message: {}",
          message
      );

      return message;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to get login page flash message",
          exception
      );

      throw exception;
    }
  }
}