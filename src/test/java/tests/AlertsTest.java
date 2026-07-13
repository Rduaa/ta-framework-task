package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

public class AlertsTest extends BaseTest {

  @Test
  public void userCanHandleJavaScriptAlerts() {
    AlertsPage alertsPage = new AlertsPage(driver)
        .openPage();

    Assert.assertTrue(alertsPage.isPageOpened(), "JavaScript Alerts page should be opened");

    String jsAlertText = alertsPage.clickJsAlertAndGetText();

    Assert.assertEquals(jsAlertText, "I am a JS Alert", "JS Alert text should be correct");
    Assert.assertEquals(
        alertsPage.getResultMessageText(),
        "You successfully clicked an alert",
        "Result message after accepting JS Alert should be correct"
    );

    String jsConfirmText = alertsPage.clickJsConfirmAndDismiss();

    Assert.assertEquals(jsConfirmText, "I am a JS Confirm", "JS Confirm text should be correct");
    Assert.assertEquals(
        alertsPage.getResultMessageText(),
        "You clicked: Cancel",
        "Result message after dismissing JS Confirm should be correct"
    );
  }
}