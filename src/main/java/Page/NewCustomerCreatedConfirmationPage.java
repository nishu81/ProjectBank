package Page;

import Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NewCustomerCreatedConfirmationPage {

    private WebDriver driver;
    Waits waits;

    public NewCustomerCreatedConfirmationPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//table[@id='customer']//tr/td[text()='Customer ID']/following::td[1]")
    private WebElement customerID;

    public TreeMap<String, String> getNewCustomerConfirmationData() {
        TreeMap<String, String> customerConfirmationMap = new TreeMap<>();
        List<WebElement> custKeys = driver.findElements(By.xpath("//table[@id='customer']//tr[position()>3 and position()<14]/td[1]"));
        List<WebElement> custValue = driver.findElements(By.xpath("//table[@id='customer']//tr[position()>3 and position()<14]/td[1]/following::td[1]"));

        for (int i = 0, j = 0; i < custKeys.size() && j < custValue.size(); i++, j++) {
            customerConfirmationMap.put(custKeys.get(i).getText(), custValue.get(j).getText());
        }
        return customerConfirmationMap;
    }

    public String getCustomerID() {
        return customerID.getText();
    }
}
