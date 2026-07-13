package pages;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

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
    driver.navigate().to(LOGIN_PAGE_URL);
    wait.until(
        ExpectedConditions.visibilityOf(usernameInput)
    );
    return this;
  }

  public boolean isPageOpened() {
    return usernameInput.isDisplayed()
        && passwordInput.isDisplayed();
  }

  public SecureAreaPage login(User user) {
    usernameInput.clear();
    usernameInput.sendKeys(user.getUsername());

    passwordInput.clear();
    passwordInput.sendKeys(user.getPassword());

    loginButton.click();

    return new SecureAreaPage(driver);
  }

  public String getFlashMessageText() {
    return wait.until(
        ExpectedConditions.visibilityOf(flashMessage)
    ).getText();
  }
}