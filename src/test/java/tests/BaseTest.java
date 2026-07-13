package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    DriverFactory.createDriver();
    driver = DriverManager.getDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    DriverManager.quitDriver();
  }
}