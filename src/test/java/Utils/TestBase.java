package Utils;

import Browsers.LaunchBrowser;
import Page.*;

public class TestBase {

    protected LaunchBrowser launchBrowser;
    protected BrowserOps browserOps;
    protected DataGenerator dataGenerator;
    protected Waits waits;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected SearchCustomerIDToEdit searchCustomerIDToEdit;
    protected EditCustomerDataPage editCustomerDataPage;
    protected NewAccountCreatePage newAccountCreatePage;
    protected NewAccountCreatedConfirmationPage newAccountCreatedConfirmationPage;
    protected DeleteAccountPage deleteAccountPage;
    protected HelperMethods helperMethods;
    protected NewCustomerCreatePage newCustomerCreatePage;
    protected NewCustomerCreatedConfirmationPage newCustomerCreatedConfirmationPage;
    protected DepositAmountPage depositAmountPage;
    protected DepositConfirmationPage depositConfirmationPage;
}
