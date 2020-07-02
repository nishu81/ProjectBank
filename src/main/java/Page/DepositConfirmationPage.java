package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepositConfirmationPage {

    private WebDriver driver;

    public DepositConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr/td[text()='Transaction ID']/following-sibling::td")
    private WebElement transactionID;

    @FindBy(xpath = "//tr//td[text()='Current Balance']/following-sibling::td")
    private WebElement balanceAmount;

    public HashMap<String, String> depositConfirmationPageMapData() {

        HashMap<String, String> depoMap = new HashMap<String, String>();
        List<WebElement> depoKeys = driver.findElements(By.xpath("//table[@id='deposit']//tr[position()>3 and position()<24]/td[1]"));
        List<WebElement> depoVals = driver.findElements(By.xpath("//table[@id='deposit']//tr[position()>3 and position()<24]/td[1]/following-sibling::td"));
        for (int i = 0, j = 0; i < depoKeys.size() && j < depoVals.size(); i++, j++) {
            depoMap.put(depoKeys.get(i).getText(), depoVals.get(j).getText());
        }

        for(Map.Entry<String,String> mm : depoMap.entrySet()){
            System.out.println(mm.getKey()+" "+ mm.getValue());
        }
        return depoMap;
    }

    public int depositConfirmationID(){
        String conf= transactionID.getText();
        return Integer.parseInt(conf);
    }

    public Boolean isDepositConfirmationID(){
        String conf= transactionID.getText();
       if( Integer.parseInt(conf)>0){
           return true;
       }
       else{
           return false;
       }
    }

    public int depositBalanceAmount(){
        String amnt= balanceAmount.getText();
        return Integer.parseInt(amnt);
    }
}
