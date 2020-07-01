package Tests;

import Browsers.Browsers;
import Browsers.LaunchBrowser;
import Configuration.Customer;
import Page.*;
import Utils.BrowserOps;
import Utils.HelperMethods;
import Utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class EditCustomerDataPageTest extends TestBase {

    public WebDriver driver;
    private static Customer signUpNewCustomer;
    public static String customerID;

    @BeforeMethod
    public void setup() {
        launchBrowser = new LaunchBrowser();
        driver = launchBrowser.getSite(Browsers.CHROME);
        helperMethods = new HelperMethods(driver);
        helperMethods.adminLogin();
        homePage = new HomePage(driver);
        homePage.clickToGetNewCustomerOption();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        helperMethods = new HelperMethods(driver);
        customerID=helperMethods.createNewCustomer();
        Assert.assertTrue(newCustomerCreatePage.isSuccessfulSubmit());
        newCustomerCreatedConfirmationPage = new NewCustomerCreatedConfirmationPage(driver);
        driver.get("http://demo.guru99.com/V4/manager/EditCustomer.php");
    }

    //Using customer edit we can edit customer data where Admin will update Pin and City value
    @Test
    public void modifyNameAndCityFieldsEditData() throws InterruptedException {
        searchCustomerIDToEdit = new SearchCustomerIDToEdit(driver);
        searchCustomerIDToEdit.getCustomer(customerID);
        editCustomerDataPage = new EditCustomerDataPage(driver);
        editCustomerDataPage.custDataAsIs();
        editCustomerDataPage.editCustomerFields("Hopkins","868947");
        Set<String> newData=editCustomerDataPage.custDataModified();
        Assert.assertTrue(newData.contains("PIN"));
        Assert.assertTrue(newData.contains("City"));
    }


    @AfterMethod
    public void tearDown() {
        browserOps = new BrowserOps(driver);
        browserOps.closeBrowser();
    }
}
