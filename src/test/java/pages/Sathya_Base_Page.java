package pages;

import common.Module;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static io.github.bonigarcia.wdm.DriverManagerType.*;
import runners.Hooks;
import cucumber.api.DataTable;

import java.util.List;

abstract public class Sathya_Base_Page implements Module {
    private static By logInBtn =  Hooks.ObjMap.getLocator("Log_In_Button");;
    private static By username = Hooks.ObjMap.getLocator("Email_ID");
    private static By password = Hooks.ObjMap.getLocator("Password");
    private static By robotChkBx =  Hooks.ObjMap.getLocator("Robot_Check_Box");;
    private static By clkLogIn = Hooks.ObjMap.getLocator("Clk_Log_In_Btn");
    private static  String menu = Hooks.ObjMap.getStrValues("Menu");
    private static  String subMenu = Hooks.ObjMap.getStrValues("SubMenu");
    private static  String subMenuPrdt = Hooks.ObjMap.getStrValues("SubMenuPrdt");
    private static By brandFilter = Hooks.ObjMap.getLocator("Brand_Filter");
    private static By priceFilter = Hooks.ObjMap.getLocator("Price_Filter");
    private static By starRatingFilter = Hooks.ObjMap.getLocator("Star_Rating_Filter");
    private static  String priceRadioBtn = Hooks.ObjMap.getStrValues("Price_Radio_Btn");
    private static  String brandChkBox = Hooks.ObjMap.getStrValues("Brand_Chk_Box");
    private static  String starRatingChkBox = Hooks.ObjMap.getStrValues("Star_Rating_chk_Box");
    private static By priceFrmTxtBx = Hooks.ObjMap.getLocator("Price_Frm_TxtBx");
    private static By priceToTxtBx = Hooks.ObjMap.getLocator("Price_To_TxtBx");
    private static By priceRangeBtn = Hooks.ObjMap.getLocator("Price_Range_Btn");

    private static By totalPrdts = Hooks.ObjMap.getLocator("Total_Product");
    private static By pageFrwd = Hooks.ObjMap.getLocator("Page_Forward");
    private static By pageBckwd = Hooks.ObjMap.getLocator("Page_Backward");
    private static String allPrdt = Hooks.ObjMap.getStrValues("All_Product");
    private static By allPrdtTitle = null;
    private static By allPrdtPrice = null;
    private static String eachPrdtEmergency = null;

    private static Logger logger= Logger.getLogger(Sathya_Base_Page.class);

    public static void loadAppln(){
        Hooks.WebObj.Navigate_To_URL(Hooks.driver, Hooks.RunConfig.strSathyaURL);
        logger.info("Application URL is loaded succesfully");
    }

    public static void login(){
        logger.info("Clicking Login Button");
        Hooks.WebObj.Find_Element_Click(Hooks.driver, Sathya_Base_Page.logInBtn);
        Hooks.WebObj.Find_Element_And_SendKeys(Hooks.driver,Sathya_Base_Page.username,Hooks.RunConfig.strUserName);
        Hooks.WebObj.Find_Element_And_SendKeys(Hooks.driver,Sathya_Base_Page.password,Hooks.RunConfig.strPassword);
        logger.info("Username and password entered successfully");
        Hooks.WebObj.Find_Element_Click(Hooks.driver, Sathya_Base_Page.robotChkBx);
        Hooks.screenShotObj.addScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        Hooks.WebObj.Find_Element_Click(Hooks.driver,Sathya_Base_Page.clkLogIn);
    }

    public static void navigation(String menu, String subMenu, String product){
        String locator = Sathya_Base_Page.menu.replace("%value%",menu);
        Hooks.WebObj.Action_Mouse_Move(Hooks.driver,Hooks.WebObj.getBy("XPATH",locator));
        if(product==null){
            locator = Sathya_Base_Page.subMenu.replaceFirst("%value%",subMenu);
            logger.info("Navigated "+menu+" to "+subMenu+" successfully");
        }
        else{
            locator = Sathya_Base_Page.subMenuPrdt.replaceFirst("%value%",subMenu).replace("%value%",product);
            logger.info("Navigated "+menu+" to "+subMenu+" and then to "+product+" successfully");
        }
        Hooks.WebObj.JS_Click(Hooks.driver,Hooks.WebObj.getBy("XPATH",locator));
    }

    public static void reportOpening(String webPage){
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Hooks.driver=new ChromeDriver(options);
        logger.info("Navigating to report location");
        Hooks.WebObj.Navigate_To_URL(Hooks.driver, webPage);
    }

    public static void commonFilters(String filter,  List<String> values){
        for (String value: values) {
            value = value.trim();
            switch (filter){
                case "PRICE": checkCollapsedAndClick(priceFilter, priceRadioBtn.replace("%value%",value));
                    break;
                case "BRAND": checkCollapsedAndClick(brandFilter, brandChkBox.replace("%value%",value));
                    break;
                case "STAR_RATING": checkCollapsedAndClick(starRatingFilter, starRatingChkBox.replace("%value%",value));
                    break;
                case "PRICE_TEXT_BOX":
                    String[] priceRange = value.split("to");
                    Hooks.WebObj.Find_Element_And_SendKeys(Hooks.driver,priceFrmTxtBx,priceRange[0].trim());
                    Hooks.WebObj.Find_Element_And_SendKeys(Hooks.driver,priceToTxtBx,priceRange[1].trim());
                    Hooks.WebObj.Find_Element_Click(Hooks.driver,priceRangeBtn);
                    break;
            }
        }
    }

    protected final static void checkCollapsedAndClick(By filterBy, String locator){
        try{
            String checkCollapse = Hooks.WebObj.Attribute_Get_Value(Hooks.driver,filterBy,"class");
            if(checkCollapse.contains("collapsed")){
                Hooks.WebObj.Find_Element_Click(Hooks.driver,filterBy);
            }
            Hooks.WebObj.JS_Click(Hooks.driver,Hooks.WebObj.getBy("XPATH",locator));
        }catch (NoSuchElementException e){
            throw  new RuntimeException("No Filter Option Exist");
        }
    }

    @Override
    public void clickPage(String direction){
        By pgePosition = null;
        if(direction.equals("forward")){
            pgePosition=pageFrwd;
        }else if (direction.equals("backward")){
            pgePosition=pageBckwd;
        }
        logger.info("Clicking "+direction);
        Hooks.WebObj.Find_Element_Click(Hooks.driver,pgePosition);
    }

    @Override
    public List<WebElement> productsList(By totalPrdts) {
        List<WebElement>  productsList= null;
        productsList= Hooks.WebObj.webElementsList(Hooks.driver,totalPrdts);
        return productsList;
    }

    @Override
    public int totalPrdts() {
        List<WebElement>  totalProducts = productsList(totalPrdts);
        logger.info("Total Products : "+totalProducts.size());
        return totalProducts.size();
    }

    public void clickEachPrdt(int index){
        By by = Hooks.WebObj.getBy("XPATH", allPrdt.replace("%value%", Integer.toString(index)));
        logger.info("Clicking Product");
        boolean status = Hooks.WebObj.Action_Keyboard_Event(Hooks.driver, by, "NEW_TAB");
        if (status){
            emergencyClick();
            List<WebElement>  allPrdtsTitle = Hooks.WebObj.webElementsList(Hooks.driver, allPrdtTitle);
            List<WebElement>  allPrdtsPrice = Hooks.WebObj.webElementsList(Hooks.driver, allPrdtPrice);
            String prdtTitle = allPrdtsTitle.get(index-1).getText();
            String prdtPrice = allPrdtsPrice.get(index-1).getText();
            prdtPrice = prdtPrice.replace(" ","").substring(1);
            String emergencyXPATH = eachPrdtEmergency.replace("%index%", Integer.toString(index)).replaceFirst("%value%",prdtPrice).replace("%value%",prdtTitle);
            By emergencyBy = Hooks.WebObj.getBy("XPATH",emergencyXPATH);
            Hooks.WebObj.openEmergencyWin(Hooks.driver, emergencyBy);
        }
    }

    private void emergencyClick(){
        allPrdtTitle = Hooks.ObjMap.getLocator("All_Prdt_Title");
        allPrdtPrice = Hooks.ObjMap.getLocator("All_Prdt_Price");
        eachPrdtEmergency = Hooks.ObjMap.getStrValues("Emergency_Prdt_Click");
        logger.info("Emergency Product Opening Invoked");
    }
}
