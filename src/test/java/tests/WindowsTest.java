package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WindowsPage;

public class WindowsTest extends BaseTest {

  @Test
  public void userCanSwitchToNewWindowAndBack() {
    WindowsPage windowsPage = new WindowsPage(driver)
        .openPage();

    Assert.assertTrue(windowsPage.isPageOpened(), "Multiple Windows page should be opened");
    Assert.assertEquals(
        windowsPage.getPageHeaderText(),
        "Opening a new window",
        "Original page header should be correct"
    );

    String newWindowText = windowsPage.openNewWindowAndGetText();

    Assert.assertEquals(newWindowText, "New Window", "New window text should be correct");
    Assert.assertTrue(windowsPage.isPageOpened(), "Original window should be active again");
  }
}