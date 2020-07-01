package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.LoginPage;
import Utils.BrowserOps;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        loginPage = new LoginPage(driver);
        driver.get(loginPage.navigateToLoginPage());
    }

    //A valid user can login successfully
    @Test
    public void adminValidLogin() {
        loginPage = new LoginPage(driver);
        loginPage.getValidLogin("mngr265711", "jUqyruj");
    }

    //When No login data given but submit button is clicked then Alert window is shown
    @Test
    public void alertShownForBlankField() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isGetWarningBlankLogin());
    }

    //When No login data given  but fields are clicked and then left field specific warning shown
    @Test
    public void errorShownForBlankFieldClicked() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isGetErrorMessageBlankFields());
    }

    //Reset button will clear any data entered in user name / password fields
    @Test
    public void resetToClearDta() {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isResetToRemoveText("bob","jones321"));
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }

}
