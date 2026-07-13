package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ScreenshotUtils;

import java.lang.reflect.Method;

public abstract class BaseTest {

  private static final Logger LOGGER =
      LogManager.getLogger(BaseTest.class);

  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp(Method method) {
    LOGGER.info("Starting test: {}", method.getName());

    DriverFactory.createDriver();
    driver = DriverManager.getDriver();

    LOGGER.info("WebDriver has been initialized");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      LOGGER.error(
          "Test failed: {}",
          result.getName(),
          result.getThrowable()
      );

      if (DriverManager.hasDriver()) {
        ScreenshotUtils.takeScreenshot(
            DriverManager.getDriver(),
            result.getName()
        );
      }
    } else if (result.getStatus() == ITestResult.SUCCESS) {
      LOGGER.info("Test passed: {}", result.getName());
    } else {
      LOGGER.warn("Test skipped: {}", result.getName());
    }

    LOGGER.info("Starting test cleanup");

    DriverManager.quitDriver();

    LOGGER.info("WebDriver has been closed");
  }
}