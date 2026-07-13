package tests;

import config.ConfigManager;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

public class LoginTest extends BaseTest {

  @Test
  public void userCanLoginAndLogoutSuccessfully() {
    User user = new User(
        ConfigManager.get("username"),
        ConfigManager.get("password")
    );

    LoginPage loginPage = new LoginPage(driver)
        .openPage();

    Assert.assertTrue(
        loginPage.isPageOpened(),
        "Login page should be opened"
    );

    SecureAreaPage secureAreaPage = loginPage.login(user);

    Assert.assertTrue(
        secureAreaPage.isPageOpened(),
        "Secure Area page should be opened"
    );

    Assert.assertTrue(
        secureAreaPage.getFlashMessageText()
            .contains("You logged into a secure area!"),
        "Success login message should be displayed"
    );

    LoginPage pageAfterLogout = secureAreaPage.logout();

    Assert.assertTrue(
        pageAfterLogout.isPageOpened(),
        "Login page should be opened after logout"
    );

    Assert.assertTrue(
        pageAfterLogout.getFlashMessageText()
            .contains("You logged out of the secure area!"),
        "Logout success message should be displayed"
    );
  }
}