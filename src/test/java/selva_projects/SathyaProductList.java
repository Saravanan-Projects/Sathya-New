package selva_projects;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import runners.Hooks;
import common.Module;

import java.util.List;

public class SathyaProductList {
    public static void main(String... a){
        Hooks.selvaSupport();
        Module.setUp();
        Hooks.WebObj.Navigate_To_URL(Hooks.driver,"https://www.sathya.store/");
        List<WebElement> panelLst = Hooks.driver.findElements(By.xpath("//h2[text()=\"WHAT'S HOT\" or text()=\"WHAT'S NEW\"]/..//div[contains(@class,'carousel-item')]"));
        for(int i =1; i<panelLst.size();i++){
            try{

            }catch(NoSuchElementException e){

            }
        }

        List<WebElement> prductLst = Hooks.driver.findElements(By.xpath("//h2[text()=\"WHAT'S HOT\" or text()=\"WHAT'S NEW\"]/..//div[contains(@class,'carousel-item')]//div[@class='product-details']"));
        System.out.println(prductLst.size());
        for(int i =1; i<prductLst.size()+1;i++){
            String prdtTitle = Hooks.driver.findElement(By.xpath("(//h2[text()=\"WHAT'S HOT\" or text()=\"WHAT'S NEW\"]/..//div[contains(@class,'carousel-item')]//div[@class='product-details'])["+i+"]/h2/a")).getText();
            System.out.println(prdtTitle);
        }

//        Hooks.WebObj.Close_Browser(Hooks.driver);
    }
}
