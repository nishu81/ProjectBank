package Page;

import Utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage {

    private WebDriver driver;
    Waits waits;

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='accountno']")
    private WebElement accountNoToDelete;

    @FindBy(xpath = "//input[@name='AccSubmit']")
    private WebElement submitButton;


    public Boolean deleteAccount(String accntNo) {
        String successfulDelete = "";
        accountNoToDelete.sendKeys(accntNo);
        submitButton.click();
        String deleteWarning = driver.switchTo().alert().getText();
        if (deleteWarning.contains("Do you really want to delete this Account?")) {
            driver.switchTo().alert().accept();
            successfulDelete = driver.switchTo().alert().getText();
        }
        if (successfulDelete.contains("Account Deleted Sucessfully")) {
            driver.switchTo().alert().accept();
            return true;
        } else {
            return false;
        }
    }

    public Boolean searchForDeletedAccount(String accountNo){
        String accountDeleted = "";
        accountNoToDelete.sendKeys(accountNo);
        submitButton.click();
        String deleteWarning = driver.switchTo().alert().getText();
        if (deleteWarning.contains("Do you really want to delete this Account?")) {
            driver.switchTo().alert().accept();
            accountDeleted = driver.switchTo().alert().getText();
        }
        if (accountDeleted.contains("Account does not exist")) {
            return true;
        } else {
            return false;
        }
    }
}
