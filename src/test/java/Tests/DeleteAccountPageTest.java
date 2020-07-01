package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Configuration.Account;
import Configuration.Customer;
import Page.DeleteAccountPage;
import Page.HomePage;
import Page.NewCustomerCreatePage;
import Utils.BrowserOps;
import Utils.HelperMethods;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAccountPageTest extends TestBase {

    public WebDriver driver;
    private static Account createNewCheckingAccount;
    private static Customer signUpNewCustomer;
    public static String customerID;
    public static String accountID;

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        helperMethods = new HelperMethods(driver);
        customerID = helperMethods.createNewCustomer();
        helperMethods = new HelperMethods(driver);
        accountID = helperMethods.createNewAccount();
    }

    //Test: Delete an account and then try to find it again
    @Test
    public void adminCanDeleteAccount() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.getNavigateToDeleteAccountPage();
        deleteAccountPage = new DeleteAccountPage(driver);
        Assert.assertTrue(deleteAccountPage.deleteAccount(accountID));
        homePage.getNavigateToDeleteAccountPage();
        Assert.assertTrue(deleteAccountPage.searchForDeletedAccount(accountID));
        Thread.sleep(10000);
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}