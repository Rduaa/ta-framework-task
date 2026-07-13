package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SecureAreaPage extends AbstractPage {

  @FindBy(css = "h2")
  private WebElement secureAreaTitle;

  @FindBy(css = ".button.secondary.radius")
  private WebElement logoutButton;

  @FindBy(css = "#flash")
  private WebElement flashMessage;

  public SecureAreaPage(WebDriver driver) {
    super(driver);
  }

  public boolean isPageOpened() {
    return wait.until(ExpectedConditions.visibilityOf(secureAreaTitle)).isDisplayed()
        && secureAreaTitle.getText().equals("Secure Area");
  }

  public String getFlashMessageText() {
    return wait.until(ExpectedConditions.visibilityOf(flashMessage)).getText();
  }

  public LoginPage logout() {
    logoutButton.click();
    return new LoginPage(driver);
  }
}