package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertsPage extends AbstractPage {

  private static final String ALERTS_PAGE_URL = BASE_URL + "/javascript_alerts";

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
    driver.navigate().to(ALERTS_PAGE_URL);
    wait.until(ExpectedConditions.visibilityOf(pageHeader));
    return this;
  }

  public boolean isPageOpened() {
    return pageHeader.isDisplayed()
        && pageHeader.getText().equals("JavaScript Alerts");
  }

  public String clickJsAlertAndGetText() {
    jsAlertButton.click();

    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    String alertText = alert.getText();
    alert.accept();

    return alertText;
  }

  public String clickJsConfirmAndDismiss() {
    jsConfirmButton.click();

    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    String alertText = alert.getText();
    alert.dismiss();

    return alertText;
  }

  public String getResultMessageText() {
    return wait.until(ExpectedConditions.visibilityOf(resultMessage)).getText();
  }
}