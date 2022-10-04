package pages;

import common.Product;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import runners.Hooks;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;

public class AC_Product_Page extends Product_Base_Page  {
    private static By brandName = Hooks.ObjMap.getLocator("Brand_Name");
    private static By ACType =  Hooks.ObjMap.getLocator("AC_Type");
    private static By starRating = Hooks.ObjMap.getLocator("Star_Rating");
    private static By ACCapacity = Hooks.ObjMap.getLocator("AC_Capacity");
    private static Logger logger= Logger.getLogger(AC_Product_Page.class);

    public List<String> getACValues(List<String> columns){
        String Runtime_Text = null;
        List<String> ACValues = new ArrayList<String>();
        clickingFeatures();
        for(String parameter : columns){
            switch (parameter){
                case "PRODUCT_NAME": Runtime_Text = super.getValues("PRODUCT_NAME"); break;
                case "PRODUCT_PRICE": Runtime_Text = super.getValues("PRODUCT_PRICE"); break;
                case "PRODUCT_DISCOUNT": Runtime_Text =super.getValues("PRODUCT_DISCOUNT"); break;
                case "ADD_TO_CART_FACILITY": Runtime_Text = super.getValues("ADD_TO_CART_FACILITY"); break;
                case "BRAND_NAME": Runtime_Text = Hooks.WebObj.Find_Element_Get_Text(Hooks.driver, brandName); break;
                case "AC_TYPE": Runtime_Text =  Hooks.WebObj.Find_Element_Get_Text(Hooks.driver, ACType); break;
                case "STAR_RATING": Runtime_Text =  Hooks.WebObj.Find_Element_Get_Text(Hooks.driver, starRating); break;
                case "AC_CAPACITY": Runtime_Text =  Hooks.WebObj.Find_Element_Get_Text(Hooks.driver, ACCapacity); break;
            }
            ACValues.add(Runtime_Text);
            logger.info("Got AC Values: "+parameter+" : "+Runtime_Text);
        }

        return ACValues;
    }
}
