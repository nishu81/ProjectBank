package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Configuration.Customer;
import Page.HomePage;
import Page.NewCustomerCreatePage;
import Page.NewCustomerCreatedConfirmationPage;
import Utils.BrowserOps;
import Utils.HelperMethods;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.TreeMap;

public class NewCustomerCreatedConfirmationPageTest extends TestBase {

    public WebDriver driver;
    private static Customer signUpNewCustomer;

    @BeforeMethod
    public void setup() {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
    }

    //Test: Verify test data used while creating
    @Test
    public void customerDataMatchWithCreatedCustomer() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        helperMethods = new HelperMethods(driver);
        helperMethods.createNewCustomer();
        Assert.assertTrue(newCustomerCreatePage.isSuccessfulSubmit());
        newCustomerCreatedConfirmationPage = new NewCustomerCreatedConfirmationPage(driver);
        TreeMap<String, String> testData = newCustomerCreatedConfirmationPage.getNewCustomerConfirmationData();
        Set<String> keySets = testData.keySet();
        for (String str : keySets) {
            if (str.equals("Address")) {
                Assert.assertEquals(testData.get(str),helperMethods.customerDataMap.get("CUSTOMER Address"));
            }
            if (str.equals("City")) {
                Assert.assertEquals(testData.get(str),helperMethods.customerDataMap.get("CUSTOMER City"));
            }
            if (str.equals("Customer Name")) {
                Assert.assertEquals(testData.get(str),helperMethods.customerDataMap.get("CUSTOMER Name"));
            }
            if (str.equals("Mobile No.")) {
                Assert.assertEquals(testData.get(str),helperMethods.customerDataMap.get("CUSTOMER Mobile"));
            }
        }
    }


    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
