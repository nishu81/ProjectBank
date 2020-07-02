package Utils;

import Configuration.Account;
import Configuration.Customer;
import Configuration.Deposit;
import Page.*;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class HelperMethods extends TestBase {

    public WebDriver driver;
    private static Customer signUpNewCustomer;
    private static Account signUpForAccount;
    private static Deposit createDeposit;
    DataGenerator randomDataGen;
    public static String customerID;
    public static String accountID;

    //These following Maps will hold all the data during creation so that we can call them and verify any data
    public HashMap<String, Object> customerDataMap = new HashMap<>();

    public static HashMap<String, Object> accountDataMap = new HashMap<>();

    public static HashMap<String, Object> depositDataMap = new HashMap<>(); // without making this Static Test was not getting the values


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

        customerDataMap.put("CUSTOMER ID", customerID);
        customerDataMap.put("CUSTOMER Name", signUpNewCustomer.getCustomerName());
        customerDataMap.put("CUSTOMER Email", signUpNewCustomer.getCustomerEmail());
        customerDataMap.put("CUSTOMER Address", signUpNewCustomer.getCustomerAddress());
        customerDataMap.put("CUSTOMER City", signUpNewCustomer.getCustomerCity());
        customerDataMap.put("CUSTOMER Mobile", signUpNewCustomer.getCustomerMobile());
       /* for (Map.Entry<String, Object> accountInfo : customerDataMap.entrySet()) {
            System.out.println(accountInfo.getKey() + " " + accountInfo.getValue());
        }*/
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

        accountDataMap.put("Account ID", accountID);  // Now I can call this HashMap in a test page and get any specific data I need
        accountDataMap.put("Customer ID", customerID);
        accountDataMap.put("Deposit Amount", signUpForAccount.getDepositAmount());
      /*  for(Map.Entry<String,Object> accountInfo: accountDataMap.entrySet()){
            System.out.println(accountInfo.getKey()+ " " + accountInfo.getValue());
        }*/
        return accountID;
    }

    //Use this method to make a deposit
    public  void depositFundToAccount() throws InterruptedException {
        depositAmountPage = new DepositAmountPage(driver);
        createDeposit = Deposit.builder().accountNo(Integer.parseInt(accountID)).depAmount(11500).depDescription("Making a deposit to account").build();
        depositAmountPage.createDeposit(createDeposit);

        /*for (Map.Entry<String, Object> accountInfo : depositDataMap.entrySet()) {
            System.out.println(accountInfo.getKey() + " " + accountInfo.getValue());
        }*/

        depositConfirmationPage = new DepositConfirmationPage(driver);

        depositDataMap.put("Account ID", accountID);
        depositDataMap.put("Initial Deposit Amount", createDeposit.getDepAmount());
        depositDataMap.put("Deposit Conf ID", depositConfirmationPage.depositConfirmationID());
        depositDataMap.put("Balance Amount", depositConfirmationPage.depositBalanceAmount());

    }

}