package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Configuration.Customer;
import Page.HomePage;
import Page.NewCustomerCreatePage;
import Utils.BrowserOps;
import Utils.DataGenerator;
import Utils.HelperMethods;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class NewCustomerPageTest extends TestBase {

    public WebDriver driver;
    private static Customer signUpNewCustomer;

    @BeforeMethod
    public void setup() {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
    }

    //Test: While creating new customer if fields are empty throw field specific warnings
    @Test
    public void getWarningForBlankFields() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        Assert.assertTrue(newCustomerCreatePage.isGetWarningForEmptyFields());
    }

    //Test: Once a successful customer is created submit button is no longer visible
    @Test
    public void adminCreatesNewCustomerSuccessfully() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        helperMethods = new HelperMethods(driver);
        helperMethods.createNewCustomer();
        Assert.assertTrue(newCustomerCreatePage.isSuccessfulSubmit());
    }

    //Test Once reset button is clicked all data gets erased
    @Test
    public void resetButtonRemovesCustomerData() {
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        dataGenerator = new DataGenerator();
        ArrayList<String> custData = dataGenerator.resetCustomerData();
        Assert.assertTrue(newCustomerCreatePage.isFieldBlankReset(custData));
    }


    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
