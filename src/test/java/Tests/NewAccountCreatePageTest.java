package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Configuration.Account;
import Configuration.Customer;
import Page.HomePage;
import Page.NewAccountCreatePage;
import Page.NewCustomerCreatePage;
import Utils.BrowserOps;
import Utils.HelperMethods;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class NewAccountCreatePageTest extends TestBase {

    public WebDriver driver;
    private static Account createNewCheckingAccount;
    private static Customer signUpNewCustomer;
    public static String customerID;
    public static HashMap<String, Object> testAccountDataMap = new HashMap<>();

    @BeforeMethod
    public void setup() throws InterruptedException {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
        /*helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        helperMethods = new HelperMethods(driver);
        customerID=helperMethods.createNewCustomer();*/

       /* homePage.getNavigateToCreateAccountPage();
        newAccountCreatePage = new NewAccountCreatePage(driver);
        createNewCheckingAccount = Account.builder().customerId(customerID).accountType(newAccountCreatePage.selectCheckingAccount())
                .depositAmount(60).build();*/
    }

    //Test: Admin can create a new account without warning
    @Test
    public void createNewAccountSuccessfully() {
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        helperMethods = new HelperMethods(driver);
        customerID = helperMethods.createNewCustomer();
        helperMethods = new HelperMethods(driver);
        helperMethods.createNewAccount();
    }

    //Test: Admin to get warning if fields are left empty
    @Test
    public void getWarningForEmptyFields() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.getNavigateToCreateAccountPage();
        newAccountCreatePage = new NewAccountCreatePage(driver);
        Assert.assertTrue(newAccountCreatePage.getAccountCreationWarnings());
    }

    //Test: Admin to get warning if initial balance is less than 500 or special character used
    @Test
    public void getDepositFieldWarning() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.getNavigateToCreateAccountPage();
        newAccountCreatePage = new NewAccountCreatePage(driver);
        newAccountCreatePage.depositAmountCharacterCheck("2002");
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
