package pages;

import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

  protected static final String BASE_URL =
      ConfigManager.get("base.url");

  protected final WebDriver driver;
  protected final WebDriverWait wait;

  protected AbstractPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(
        driver,
        Duration.ofSeconds(
            ConfigManager.getInt("explicit.timeout")
        )
    );

    PageFactory.initElements(driver, this);
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public String getPageTitle() {
    return driver.getTitle();
  }
}