package Utils;

import Configuration.Account;
import Configuration.Customer;
import Page.*;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class HelperMethods extends TestBase {

    public WebDriver driver;
    private static Customer signUpNewCustomer;
    private static Account signUpForAccount;
    DataGenerator randomDataGen;
    public static String customerID;
    public static String accountID;

    //public static Customer getSignUpNewCustomer1;
   public   HashMap<String, Object> customerDataMap = new HashMap<>();

   public   HashMap<String, Object> accountDataMap = new HashMap<>();

    //Use this to retrieve data for this Customer // ToDo : Create a HashMap like I did for New Account and save important key Data there
   /* public Customer testCustomer() {
        getSignUpNewCustomer1 = Customer.builder().customerName("James Roy").dateOfBirth(randomDataGen.getDate()).customerAddress("12111 Camden Road")
                .customerCity("London").customerState("Washington").customerMobile("8888888888").customerEmail(randomDataGen.getEmail())
                .customerPin("332244").customerPassword("xyz321").build();

        return getSignUpNewCustomer1;
    }*/

    public HelperMethods(WebDriver driver) {
        this.driver = driver;
    }

    //Use this to login as Admin
    public void adminLogin() {
        loginPage = new LoginPage(driver);
        driver.get(loginPage.navigateToLoginPage());
        loginPage.getValidLogin("mngr265711", "jUqyruj");
    }

    //Use this to create a new Customer
    public String createNewCustomer() {
        randomDataGen = new DataGenerator();
        newCustomerCreatePage = new NewCustomerCreatePage(driver);
        signUpNewCustomer = Customer.builder().customerName("James Roy").dateOfBirth(randomDataGen.getDate()).customerAddress("12111 Camden Road")
                .customerCity("London").customerState("Washington").customerMobile("8888888888").customerEmail(randomDataGen.getEmail())
                .customerPin("332244").customerPassword("xyz321").build();

        newCustomerCreatePage.createACustomer(signUpNewCustomer);
        newCustomerCreatedConfirmationPage = new NewCustomerCreatedConfirmationPage(driver);
        customerID = newCustomerCreatedConfirmationPage.getCustomerID();
        System.out.println("CUSTOMER ID: " + customerID);

        customerDataMap.put("CUSTOMER ID",customerID);
        customerDataMap.put("CUSTOMER Name",signUpNewCustomer.getCustomerName());
        customerDataMap.put("CUSTOMER Email",signUpNewCustomer.getCustomerEmail());
        customerDataMap.put("CUSTOMER Address",signUpNewCustomer.getCustomerAddress());
        customerDataMap.put("CUSTOMER City",signUpNewCustomer.getCustomerCity());
        customerDataMap.put("CUSTOMER Mobile",signUpNewCustomer.getCustomerMobile());
        for(Map.Entry<String,Object> accountInfo: customerDataMap.entrySet()){
            System.out.println(accountInfo.getKey()+ " " + accountInfo.getValue());
        }
        return customerID;
    }

    //Use this method to create a New Checking Account
    public String createNewAccount() {
        //HashMap<String, Object> accountDataMap = new HashMap<>();
        homePage = new HomePage(driver);
        homePage.getNavigateToCreateAccountPage();
        newAccountCreatePage = new NewAccountCreatePage(driver);
        signUpForAccount = Account.builder().customerId(customerID).accountType(newAccountCreatePage.selectCheckingAccount())
                .depositAmount(600).build();
        newAccountCreatePage.createAnAccount(signUpForAccount);

        newAccountCreatedConfirmationPage = new NewAccountCreatedConfirmationPage(driver);
        accountID = newAccountCreatedConfirmationPage.getNewAccountId();
        System.out.println("ACCOUNT ID: " + accountID);

        accountDataMap.put("Account ID",accountID);  // Now I can call this HashMap in a test page and get any specific data I need
        accountDataMap.put("Customer ID",customerID);
        accountDataMap.put("Deposit Amount",signUpForAccount.getDepositAmount());
      /*  for(Map.Entry<String,Object> accountInfo: accountDataMap.entrySet()){
            System.out.println(accountInfo.getKey()+ " " + accountInfo.getValue());
        }*/
        return accountID;
    }
}