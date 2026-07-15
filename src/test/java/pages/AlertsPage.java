package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertsPage extends AbstractPage {

  private static final Logger LOGGER =
      LogManager.getLogger(AlertsPage.class);

  private static final String ALERTS_PAGE_URL =
      BASE_URL + "/javascript_alerts";

  @FindBy(tagName = "h3")
  private WebElement pageHeader;

  @FindBy(xpath = "//button[text()='Click for JS Alert']")
  private WebElement jsAlertButton;

  @FindBy(xpath = "//button[text()='Click for JS Confirm']")
  private WebElement jsConfirmButton;

  @FindBy(id = "result")
  private WebElement resultMessage;

  public AlertsPage(WebDriver driver) {
    super(driver);
  }

  public AlertsPage openPage() {
    LOGGER.info("Opening JavaScript Alerts page: {}", ALERTS_PAGE_URL);

    try {
      driver.navigate().to(ALERTS_PAGE_URL);

      wait.until(
          ExpectedConditions.visibilityOf(pageHeader)
      );

      LOGGER.info("JavaScript Alerts page has been opened");

      return this;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to open JavaScript Alerts page: {}",
          ALERTS_PAGE_URL,
          exception
      );

      throw exception;
    }
  }

  public boolean isPageOpened() {
    LOGGER.debug(
        "Checking whether JavaScript Alerts page is opened"
    );

    try {
      String headerText = wait.until(
          ExpectedConditions.visibilityOf(pageHeader)
      ).getText();

      boolean pageOpened =
          "JavaScript Alerts".equals(headerText);

      LOGGER.debug(
          "JavaScript Alerts page opened status: {}",
          pageOpened
      );

      return pageOpened;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "JavaScript Alerts page is not displayed correctly",
          exception
      );

      return false;
    }
  }

  public String clickJsAlertAndGetText() {
    LOGGER.info("Opening JavaScript alert");

    try {
      wait.until(
          ExpectedConditions.elementToBeClickable(
              jsAlertButton
          )
      ).click();

      Alert alert = wait.until(
          ExpectedConditions.alertIsPresent()
      );

      String alertText = alert.getText();

      LOGGER.debug(
          "JavaScript alert text: {}",
          alertText
      );

      alert.accept();

      LOGGER.info("JavaScript alert has been accepted");

      return alertText;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to open or accept JavaScript alert",
          exception
      );

      throw exception;
    }
  }

  public String clickJsConfirmAndDismiss() {
    LOGGER.info("Opening JavaScript confirmation alert");

    try {
      wait.until(
          ExpectedConditions.elementToBeClickable(
              jsConfirmButton
          )
      ).click();

      Alert alert = wait.until(
          ExpectedConditions.alertIsPresent()
      );

      String alertText = alert.getText();

      LOGGER.debug(
          "JavaScript confirmation alert text: {}",
          alertText
      );

      alert.dismiss();

      LOGGER.info(
          "JavaScript confirmation alert has been dismissed"
      );

      return alertText;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to open or dismiss JavaScript confirmation alert",
          exception
      );

      throw exception;
    }
  }

  public String getResultMessageText() {
    LOGGER.debug("Getting JavaScript alert result message");

    try {
      String message = wait.until(
          ExpectedConditions.visibilityOf(resultMessage)
      ).getText();

      LOGGER.debug(
          "JavaScript alert result message: {}",
          message
      );

      return message;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to get JavaScript alert result message",
          exception
      );

      throw exception;
    }
  }
}