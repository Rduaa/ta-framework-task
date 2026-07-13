package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SecureAreaPage extends AbstractPage {

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
    wait.until(
        ExpectedConditions.urlContains("/secure")
    );

    return wait.until(
        ExpectedConditions.textToBe(
            SECURE_AREA_TITLE,
            "Secure Area"
        )
    );
  }

  public String getFlashMessageText() {
    return wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            FLASH_MESSAGE
        )
    ).getText();
  }

  public LoginPage logout() {
    wait.until(
        ExpectedConditions.elementToBeClickable(
            logoutButton
        )
    ).click();

    wait.until(
        ExpectedConditions.urlContains("/login")
    );

    return new LoginPage(driver);
  }
}