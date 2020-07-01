package Page;

import Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class EditCustomerDataPage {

    private static WebDriver driver;
    Waits waits;
   public static final HashMap<String, String> dataAsis = new HashMap<>();

    public EditCustomerDataPage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='pinno']")
    private WebElement customerPin;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement customerCity;

    @FindBy(xpath = "//input[@name='sub']")
    private WebElement submitButton;

    //Capture Customer Data as Is
    public static HashMap<String, String> custDataAsIs() {

        List<WebElement> custKeys = driver.findElements(By.xpath("//tbody//tr[position()>3 and position()<13]/td[1]"));
        List<WebElement> custValues = driver.findElements(By.xpath("//tbody//tr[position()>3 and position()<13]/td[1]/following::td[1]/input | //tbody//tr[position()>3 and position()<13]/td[1]/following::td[1]/textarea"));

        for (int i = 0, j = 0; i < custKeys.size() && j < custValues.size(); i++, j++) {
            String s =  custValues.get(j).getAttribute("value"); // Ternary value concept if value length is 0 then use getText()
            dataAsis.put(custKeys.get(i).getText(), s.equals("") ? custValues.get(j).getText() : s);
        }
        /*System.out.println("DATA AS IS");
        for (Map.Entry<String, String> mm : dataAsis.entrySet()) {
            System.out.println(mm.getKey() + " " + mm.getValue());
        }*/
        return dataAsis;
    }

    //Method to update  City and PIN of the Customer
    public void editCustomerFields(String city, String pin) {
        //customerCity.click();
        customerCity.clear();
        customerCity.sendKeys(city);
        //customerPin.click();
        customerPin.clear();
        customerPin.sendKeys(pin);
        submitButton.click();
    }



    //Capture Customer Data after update We put them in HashSet and expect Keys State and Pin to have different value
    public Set<String> custDataModified() {
        HashMap<String, String> dataModified = new HashMap<>();
        List<WebElement> custKeys = driver.findElements(By.xpath("//table[@id='customer']//tr[position()>3 and position()<14]/td[1]"));
        List<WebElement> custValue = driver.findElements(By.xpath("//table[@id='customer']//tr[position()>3 and position()<14]/td[1]/following::td[1]"));

        for (int i = 0, j = 0; i < custKeys.size() && j < custValue.size(); i++, j++) {
            dataModified.put(custKeys.get(i).getText(), custValue.get(j).getText());
        }

        System.out.println("AS IS DATA ");
        for (Map.Entry<String, String> mm : dataAsis.entrySet()) {
            System.out.println(mm.getKey() + " " + mm.getValue());
        }
        Set<String> hs = new HashSet<>();
        for (Map.Entry<String, String> map1 : custDataAsIs().entrySet()) {
            String map2Value = dataModified.get(map1.getKey());
            if (!Objects.equals(map1.getValue(), map2Value)) {
                hs.add(map1.getKey());
            }
        }
        System.out.println(" changed DATA ");
        for (Map.Entry<String, String> mm : dataModified.entrySet()) {
            System.out.println(mm.getKey() + " " + mm.getValue());
        }
        return hs;
    }

}
