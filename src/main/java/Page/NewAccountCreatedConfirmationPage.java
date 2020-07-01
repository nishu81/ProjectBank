package Page;

import Utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewAccountCreatedConfirmationPage {

    private WebDriver driver;
    Waits waits;

    public NewAccountCreatedConfirmationPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//tr//td[text()='Account ID']/following-sibling::td")
    private WebElement accountID;

    public String getNewAccountId(){
        String custAccountID=accountID.getText();
        return custAccountID;
    }
}
