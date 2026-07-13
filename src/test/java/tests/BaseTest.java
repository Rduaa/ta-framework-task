package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

  private static final Logger LOGGER =
      LogManager.getLogger(BaseTest.class);

  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    LOGGER.info("Starting test setup");

    DriverFactory.createDriver();
    driver = DriverManager.getDriver();

    LOGGER.info("WebDriver has been initialized");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    LOGGER.info("Starting test cleanup");

    DriverManager.quitDriver();

    LOGGER.info("WebDriver has been closed");
  }
}