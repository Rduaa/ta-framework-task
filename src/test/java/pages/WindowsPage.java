package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class WindowsPage extends AbstractPage {

  private static final String WINDOWS_PAGE_URL = BASE_URL + "/windows";

  @FindBy(tagName = "h3")
  private WebElement pageHeader;

  @FindBy(linkText = "Click Here")
  private WebElement clickHereLink;

  public WindowsPage(WebDriver driver) {
    super(driver);
  }

  public WindowsPage openPage() {
    driver.navigate().to(WINDOWS_PAGE_URL);
    wait.until(ExpectedConditions.visibilityOf(pageHeader));
    return this;
  }

  public boolean isPageOpened() {
    return pageHeader.isDisplayed()
        && pageHeader.getText().equals("Opening a new window");
  }

  public String getPageHeaderText() {
    return wait.until(ExpectedConditions.visibilityOf(pageHeader)).getText();
  }

  public String openNewWindowAndGetText() {
    String originalWindow = driver.getWindowHandle();

    clickHereLink.click();

    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    Set<String> windowHandles = driver.getWindowHandles();

    for (String windowHandle : windowHandles) {
      if (!windowHandle.equals(originalWindow)) {
        driver.switchTo().window(windowHandle);
        break;
      }
    }

    String newWindowText = wait.until(ExpectedConditions.visibilityOf(pageHeader)).getText();

    driver.close();
    driver.switchTo().window(originalWindow);

    return newWindowText;
  }
}