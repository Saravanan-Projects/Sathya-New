package pages;

import common.Product;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import runners.Hooks;

abstract class Product_Base_Page implements Product {
    private static By productName = Hooks.ObjMap.getLocator("Product_Name");
    private static By productPrice = Hooks.ObjMap.getLocator("Product_Price");
    private static By productDiscount = Hooks.ObjMap.getLocator("Product_Discount");
    private static By clickFeatures = Hooks.ObjMap.getLocator("Click_Features");
    private static By AddToCartBtn = Hooks.ObjMap.getLocator("Add_To_Cart_Btn");
    private static Logger logger= Logger.getLogger(Product_Base_Page.class);

    protected String getValues(String parameter){
        String Runtime_Text = null;
        switch (parameter){
            case "PRODUCT_NAME": Runtime_Text = Hooks.WebObj.Find_Element_Get_Text(Hooks.driver, productName); break;
            case "PRODUCT_PRICE": Runtime_Text = Hooks.WebObj.Attribute_Get_Value(Hooks.driver, productPrice,
                    "content"); break;
            case "PRODUCT_DISCOUNT": Runtime_Text = Hooks.WebObj.Find_Element_Get_Text(Hooks.driver, productDiscount); break;
            case "ADD_TO_CART_FACILITY": Runtime_Text = Hooks.WebObj.Find_Element(Hooks.driver, AddToCartBtn); break;
        }
        logger.info("Got "+parameter+" and value its "+Runtime_Text+" successfully");
        return Runtime_Text;
    }

    protected final void   clickingFeatures(){
        logger.info("Clicking Product features");
        Hooks.WebObj.Find_Element_Click(Hooks.driver, clickFeatures);
    }
}
