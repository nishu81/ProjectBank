package Page;

import Configuration.Account;
import Utils.Waits;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class NewAccountCreatePage {

    private WebDriver driver;
    Waits waits;

    public NewAccountCreatePage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='cusid']")
    private WebElement customerID;

    @FindBy(xpath = "//select[@name='selaccount']")
    private WebElement selectAccount;

    @FindBy(xpath = "//input[@name='inideposit']")
    private WebElement initialDeposit;

    @FindBy(xpath = "//input[@name='inideposit']/..//following::label[text()='Initial Deposit must not be blank']")
    private WebElement initialDepositBlankWarning;

    @FindBy(xpath = "//input[@name='cusid']/..//following::label[text()='Customer ID is required']")
    private WebElement customerIdBlankWarning;

    @FindBy(xpath = "//input[@name='button2']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name='reset']")
    private WebElement resetButton;

    public Select selectCheckingAccount() {
        Select account = new Select(selectAccount);
        account.selectByValue("Savings");

        return account;
    }


    public NewAccountCreatePage createAnAccount(Account accountConfiguration) {
        customerID.sendKeys(accountConfiguration.getCustomerId());
        if (driver.findElements(By.xpath("//input[@name='cusid']/..//following::label[text()='Customer ID is required']")).size() > 0) {
            Assert.fail("Customer field cannot be empty");
        } // ToDo : Amod this part does not work, When I leave Account ID empty test still passes
        accountConfiguration.getAccountType();
        initialDeposit.sendKeys(String.valueOf(accountConfiguration.getDepositAmount()));
        if (driver.findElements(By.xpath("//input[@name='inideposit']/..//following::label[text()='Initial Deposit must not be blank']")).size() > 0) {
            Assert.fail("Amount field cannot be empty");
        }
        submitButton.click();
        return this;
    }

    //Fields cannot be empty or special character cannot be used
    public Boolean getAccountCreationWarnings() {
        boolean displayedWarning = true;
        customerID.click();
        customerID.sendKeys(Keys.TAB);
        //Assert.assertTrue(customerIdBlankWarning.isDisplayed());
        initialDeposit.click();
        initialDeposit.sendKeys(Keys.TAB);
        //  Assert.assertTrue(initialDepositBlankWarning.isDisplayed());
        if (customerIdBlankWarning.isDisplayed() && initialDepositBlankWarning.isDisplayed()) {
            displayedWarning = true;
        } else {
            displayedWarning = false;
        }
        return displayedWarning;
    }

    // Admin to get warning if deposit amount is less than 500 or special character used
    public void depositAmountCharacterCheck(String customerIDVal) throws InterruptedException {
        customerID.sendKeys(customerIDVal);
        initialDeposit.sendKeys(String.valueOf(50.75));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='inideposit']/..//label")).isDisplayed());
        initialDeposit.clear();
        Thread.sleep(5000);
        initialDeposit.sendKeys(String.valueOf(100));
        submitButton.click();
        String balanceWarning =driver.switchTo().alert().getText();
        if(balanceWarning.contains("Intial deposite must be Rs. 500 or more")){
            driver.switchTo().alert().accept();
        }

    }

}
