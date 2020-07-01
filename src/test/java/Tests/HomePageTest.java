package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Page.HomePage;
import Page.LoginPage;
import Utils.BrowserOps;
import Utils.HelperMethods;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
    }

    //Side menu should have 15 options
    @Test
    public void validateSideMenuForHomePage() {
        homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        homePage.getHomePageSideMenuOptions();
    }

    //Manager logged in is mngr265711
    @Test
    public void getManagerId(){
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isManagerLoggedIn("mngr265711"));
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
