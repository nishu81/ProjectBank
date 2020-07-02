package Page;

import Configuration.Deposit;
import Utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepositAmountPage {

    private WebDriver driver;
    Waits waits;

    public DepositAmountPage(WebDriver driver) {
        this.driver = driver;
        waits  = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='accountno']")
    private WebElement accountNumber;

    @FindBy(xpath = "//input[@name='ammount']")
    private WebElement depositAmount;

    @FindBy(xpath = "//input[@name='desc']")
    private WebElement depositDescription;

    @FindBy(xpath = "//input[@name='AccSubmit']")
    private WebElement buttonSubmit;

    public DepositAmountPage createDeposit(Deposit makeAccountDeposit) {
        accountNumber.sendKeys(String.valueOf(makeAccountDeposit.getAccountNo()));
        depositAmount.sendKeys(String.valueOf(makeAccountDeposit.getDepAmount()));
        depositDescription.sendKeys(makeAccountDeposit.getDepDescription());
        buttonSubmit.click();
        waits.waitIsInvisible(buttonSubmit);
        return this;
    }
}
