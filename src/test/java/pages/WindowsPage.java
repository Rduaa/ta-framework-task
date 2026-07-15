package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class WindowsPage extends AbstractPage {

  private static final Logger LOGGER =
      LogManager.getLogger(WindowsPage.class);

  private static final String WINDOWS_PAGE_URL =
      BASE_URL + "/windows";

  @FindBy(tagName = "h3")
  private WebElement pageHeader;

  @FindBy(linkText = "Click Here")
  private WebElement clickHereLink;

  public WindowsPage(WebDriver driver) {
    super(driver);
  }

  public WindowsPage openPage() {
    LOGGER.info(
        "Opening Windows page: {}",
        WINDOWS_PAGE_URL
    );

    try {
      driver.navigate().to(WINDOWS_PAGE_URL);

      wait.until(
          ExpectedConditions.visibilityOf(pageHeader)
      );

      LOGGER.info("Windows page has been opened");

      return this;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to open Windows page: {}",
          WINDOWS_PAGE_URL,
          exception
      );

      throw exception;
    }
  }

  public boolean isPageOpened() {
    LOGGER.debug(
        "Checking whether Windows page is opened"
    );

    try {
      String headerText = wait.until(
          ExpectedConditions.visibilityOf(pageHeader)
      ).getText();

      boolean pageOpened =
          "Opening a new window".equals(headerText);

      LOGGER.debug(
          "Windows page opened status: {}",
          pageOpened
      );

      return pageOpened;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Windows page is not displayed correctly",
          exception
      );

      return false;
    }
  }

  public String getPageHeaderText() {
    LOGGER.debug("Getting Windows page header text");

    try {
      String headerText = wait.until(
          ExpectedConditions.visibilityOf(pageHeader)
      ).getText();

      LOGGER.debug(
          "Windows page header text: {}",
          headerText
      );

      return headerText;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to get Windows page header text",
          exception
      );

      throw exception;
    }
  }

  public String openNewWindowAndGetText() {
    LOGGER.info("Opening a new browser window");

    String originalWindow = driver.getWindowHandle();

    try {
      LOGGER.debug(
          "Original window handle: {}",
          originalWindow
      );

      wait.until(
          ExpectedConditions.elementToBeClickable(
              clickHereLink
          )
      ).click();

      wait.until(
          ExpectedConditions.numberOfWindowsToBe(2)
      );

      Set<String> windowHandles =
          driver.getWindowHandles();

      String newWindow = windowHandles.stream()
          .filter(windowHandle ->
              !windowHandle.equals(originalWindow))
          .findFirst()
          .orElseThrow(() ->
              new IllegalStateException(
                  "New browser window was not found"
              )
          );

      LOGGER.debug(
          "New window handle: {}",
          newWindow
      );

      driver.switchTo().window(newWindow);

      LOGGER.info("Switched to the new browser window");

      String newWindowText = wait.until(
          ExpectedConditions.visibilityOf(pageHeader)
      ).getText();

      LOGGER.debug(
          "New browser window text: {}",
          newWindowText
      );

      driver.close();

      LOGGER.info("New browser window has been closed");

      driver.switchTo().window(originalWindow);

      LOGGER.info(
          "Switched back to the original browser window"
      );

      return newWindowText;
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to work with the new browser window",
          exception
      );

      switchBackToOriginalWindow(originalWindow);

      throw exception;
    }
  }

  private void switchBackToOriginalWindow(
      String originalWindow
  ) {
    try {
      Set<String> windowHandles =
          driver.getWindowHandles();

      if (windowHandles.contains(originalWindow)) {
        driver.switchTo().window(originalWindow);

        LOGGER.info(
            "Switched back to the original browser window after an error"
        );
      }
    } catch (RuntimeException exception) {
      LOGGER.error(
          "Unable to switch back to the original browser window",
          exception
      );
    }
  }
}