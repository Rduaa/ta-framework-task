package tests;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

  protected WebDriver driver;

  @BeforeMethod
  public void setUp() {
    DriverFactory.createDriver();
    driver = DriverFactory.getDriver();
  }

  @AfterMethod
  public void tearDown() {
    DriverFactory.quitDriver();
  }
}