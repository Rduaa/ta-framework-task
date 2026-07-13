package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;

public class DriverFactory {

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  private DriverFactory() {
  }

  public static void createDriver() {
    WebDriverManager.chromedriver().setup();

    WebDriver chromeDriver = ThreadGuard.protect(new ChromeDriver());
    chromeDriver.manage().window().maximize();
    chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    driver.set(chromeDriver);
  }

  public static WebDriver getDriver() {
    return driver.get();
  }

  public static void quitDriver() {
    if (driver.get() != null) {
      driver.get().quit();
      driver.remove();
    }
  }
}