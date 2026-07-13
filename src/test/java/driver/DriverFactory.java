package driver;

import config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;

public final class DriverFactory {

  private DriverFactory() {
  }

  public static void createDriver() {
    Browser browser = Browser.from(ConfigManager.get("browser"));
    boolean headless = ConfigManager.getBoolean("headless");

    WebDriver rawDriver = switch (browser) {
      case CHROME -> createChromeDriver(headless);
      case FIREFOX -> createFirefoxDriver(headless);
    };

    WebDriver protectedDriver = ThreadGuard.protect(rawDriver);

    protectedDriver.manage()
        .timeouts()
        .pageLoadTimeout(
            Duration.ofSeconds(
                ConfigManager.getInt("page.load.timeout")
            )
        );

    protectedDriver.manage()
        .timeouts()
        .implicitlyWait(Duration.ZERO);

    if (!headless) {
      protectedDriver.manage().window().maximize();
    }

    DriverManager.setDriver(protectedDriver);
  }

  private static WebDriver createChromeDriver(boolean headless) {
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");

    if (headless) {
      options.addArguments("--headless=new");
      options.addArguments("--window-size=1920,1080");
    }

    return new ChromeDriver(options);
  }

  private static WebDriver createFirefoxDriver(boolean headless) {
    WebDriverManager.firefoxdriver().setup();

    FirefoxOptions options = new FirefoxOptions();

    if (headless) {
      options.addArguments("-headless");
      options.addArguments("--width=1920");
      options.addArguments("--height=1080");
    }

    return new FirefoxDriver(options);
  }

  public static WebDriver getDriver() {
    return DriverManager.getDriver();
  }

  public static void quitDriver() {
    DriverManager.quitDriver();
  }
}