package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Configuration.Account;
import Configuration.Customer;
import Configuration.Deposit;
import Page.DepositConfirmationPage;
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

public class DepositAmountPageTest extends TestBase {

    public WebDriver driver;
    private static Account createNewCheckingAccount;
    private static Customer signUpNewCustomer;
    private static Deposit createDeposit;
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

    // Test Admin can deposit to an existing account
    @Test
    public void makeSuccessfulDeposit() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.getNavigateTDepositPage();
        helperMethods = new HelperMethods(driver);
        helperMethods.depositFundToAccount();
        depositConfirmationPage = new DepositConfirmationPage(driver);
        Assert.assertTrue(depositConfirmationPage.isDepositConfirmationID());
        helperMethods = new HelperMethods(driver);

        //ToDo Notes: Error 1: I had to make the Maps in Helper Method static otherwise values were coming null
        //2: Had to declare as Double instead of Integer.pasreInt
       double expectedTotal= ((Double.parseDouble(String.valueOf(helperMethods.depositDataMap.get("Initial Deposit Amount")))+  Double.parseDouble(String.valueOf(helperMethods.accountDataMap.get("Deposit Amount")))));
       double actualTotal= Double.parseDouble(String.valueOf(helperMethods.depositDataMap.get("Balance Amount")));

        Assert.assertEquals(expectedTotal,actualTotal);
    }

    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
