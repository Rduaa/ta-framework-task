package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SecureAreaPage extends AbstractPage {

  private static final Logger LOGGER =
      LogManager.getLogger(SecureAreaPage.class);

  private static final By SECURE_AREA_TITLE =
      By.cssSelector("h2");

  private static final By FLASH_MESSAGE =
      By.id("flash");

  @FindBy(css = ".button.secondary.radius")
  private WebElement logoutButton;

  public SecureAreaPage(WebDriver driver) {
    super(driver);
  }

  public boolean isPageOpened() {
    LOGGER.debug(
        "Checking whether Secure Area page is opened"
    );

    try {
      wait.until(
          ExpectedConditions.urlContains("/secure")
      );

      boolean pageOpened = wait.until(
          ExpectedConditions.textToBe(
              SECURE_AREA_TITLE,
              "Secure Area"
          )
      );

      LOGGER.debug(
          "Secure Area page opened status: {}",
          pageOpened
      );

      return pageOpened;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Secure Area page is not displayed correctly",
          exception
      );

      return false;
    }
  }

  public String getFlashMessageText() {
    LOGGER.debug("Getting Secure Area flash message");

    try {
      String message = wait.until(
          ExpectedConditions.visibilityOfElementLocated(
              FLASH_MESSAGE
          )
      ).getText();

      LOGGER.debug(
          "Secure Area flash message: {}",
          message
      );

      return message;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to get Secure Area flash message",
          exception
      );

      throw exception;
    }
  }

  public LoginPage logout() {
    LOGGER.info("Logging out from Secure Area");

    try {
      wait.until(
          ExpectedConditions.elementToBeClickable(
              logoutButton
          )
      ).click();

      wait.until(
          ExpectedConditions.urlContains("/login")
      );

      LOGGER.info(
          "Logout has been completed successfully"
      );

      return new LoginPage(driver);
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to log out from Secure Area",
          exception
      );

      throw exception;
    }
  }
}