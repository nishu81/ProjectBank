package Page;

import Utils.Waits;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchCustomerIDToEdit {

    private WebDriver driver;
    Waits waits;

    public SearchCustomerIDToEdit(WebDriver driver){
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@name='cusid']")
    private WebElement customerIdField;

    @FindBy(xpath="//input[@name='cusid']/following::label[text()='Customer ID is required']")
    private WebElement customerIdFieldBlankError;

    @FindBy(xpath="//input[@type='submit']")
    private WebElement submitButton;

    public void getCustomer(String custID){
        customerIdField.sendKeys(custID);
      //List<WebElement> blankWarning=driver.findElements(By.xpath("//input[@name='cusid']/following::label[text()='Customer ID is required']")).size()==0;
        submitButton.click();
        waits.waitIsInvisible(submitButton);
    }
}
