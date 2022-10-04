package common;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtils {
    public static int untilTimeout = 0;
    public static int sleepIn = 0;
    private static Logger logger= Logger.getLogger(WebUtils.class);

    public WebUtils(String timeout, String sleepSec) {
        untilTimeout = Integer.parseInt(timeout);
        sleepIn = Integer.parseInt(sleepSec);
        logger.info("Setting Timeout limit as "+untilTimeout);
        logger.info("Setting Sleeping time as "+sleepIn);
    }

    //  Browser based methods

    public void Maximize_Browser(WebDriver driver)
    {
        driver.manage().window().maximize();
    }

    public void Close_Browser(WebDriver driver)
    {
        driver.close();
        logger.info("Closing current tab");
    }

    public void Quit_Browser(WebDriver driver)
    {
        driver.quit();
        logger.info("Quiting browser");
    }

    public void Delete_Cookies(WebDriver driver)
    {
        driver.manage().deleteAllCookies();
        logger.info("Cookies deleted");
    }

    public void Navigate_To_URL(WebDriver driver, String URL)
    {
        logger.info("Loading Application URL: "+URL);
        driver.navigate().to(URL);
    }

    public String getWindowTitle(WebDriver driver){ String title = driver.getTitle(); return title; }

    public String getWindowURL(WebDriver driver){ String URL = driver.getCurrentUrl(); return URL;}

    public String getWindowID(WebDriver driver){
        String parentWinHandle = driver.getWindowHandle();
        logger.info("Getting Parent Window Handle ID "+parentWinHandle);
        return parentWinHandle;
    }

    public Set<String> getWindowIDs(WebDriver driver){
        Set<String> winHandles = driver.getWindowHandles();
        return winHandles;
    }

    public void switchWindow(WebDriver driver, String handle){
        driver.switchTo().window(handle);
        WaitType.PAGE_LOAD.Explicit_Wait(driver, null);
        logger.info("Window Switched successfully");
    }

    public void openNewTab(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        logger.info("New Tab opened");
    }

    public void openEmergencyWin (WebDriver driver, By by){
        WaitType.VISIBLIBITY.Explicit_Wait(driver, by);
        Action_Keyboard_Event(driver,by,"NEW_TAB");
    }

    //WebElement based methods

    public void Find_Element_And_SendKeys(WebDriver driver, By by, String sendkeys)
    {
        WaitType.VISIBLIBITY.Explicit_Wait(driver, by);
        WebElement webElement = driver.findElement(by);
        webElement.clear();
        webElement.sendKeys(sendkeys);
        logger.info("Successfully Message sent to textbox");
    }

    public void Find_Element_Click(WebDriver driver, By by)
    {
        WaitType.CLICKABLE.Explicit_Wait(driver, by);
        WebElement webElement = driver.findElement(by);
        JS_Scroll_By_Element(driver, by);
        webElement.click();
        logger.info("Webelement is clicked");
    }

    public void Find_Element_Click_And_Enter_Value(WebDriver driver, By by, String keysToSend) {
        WaitType.PRESENCE_ONE.Explicit_Wait(driver, by);
        JS_Scroll_By_Element(driver, by);
        List<WebElement> webElements = driver.findElements(by);
        for(int i = 0; i < webElements.size(); i++) {
            WebElement webElement = webElements.get(i);
            if(Element_Displayed(driver, by)) {
                webElement.clear();
                webElement.click();
                webElement.sendKeys(keysToSend);
                break;
            }
        }
        logger.info("Successfully Message sent to textbox after multiple try");
    }

    public String Find_Element(WebDriver driver, By by){
        String flag = null;
        try{
            driver.findElement(by);
            flag = "Yes";
            logger.info("Webelement presence located");
        }
        catch (Exception e){
            flag = "No";
            logger.info("Webelement presence unable to located");
        }
        return flag;
    }

    public String Find_Element_Get_Text(WebDriver driver, By by) {
        String Runtime_Text = null;
        try{
            WebElement webElement = driver.findElement(by);
            WaitType.VISIBLIBITY.Explicit_Wait(driver, by);
            Runtime_Text = webElement.getText();
        }  catch (NoSuchElementException e){
            Runtime_Text = "Nil";
        }
        logger.info("Got Text from webelement");
        return Runtime_Text;
    }

    public List<WebElement> webElementsList(WebDriver driver, By by){
        List<WebElement> listWE = driver.findElements(by);
        return listWE;
    }

    public void Select_Drop_Down(WebDriver driver, By by, String dropDownVal) {
        WebElement webElement = driver.findElement(by);
        Select Drop_Down_Select =  new Select(webElement);
        Drop_Down_Select.selectByVisibleText(dropDownVal);
        logger.info("Desired value selected in dropdown box");
    }

    public void Find_Drop_Down_Select_Value(WebDriver driver, By by, String locator, String dropDownVal) {
        WaitType.PRESENCE_MORE.Explicit_Wait(driver, by);
        List<WebElement> webElements = driver.findElements(by);
        for(int i = 0; i < webElements.size(); i++) {
            WebElement webElement = webElements.get(i);
            if(webElement.isDisplayed()) {
                webElement.click();
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                String placeValue = locator.replace("%value%", dropDownVal);
                WaitType.PRESENCE_MORE.Explicit_Wait(driver, By.xpath(placeValue));
                Find_Element_Click(driver, By.xpath(placeValue));
                break;
            }
        }
        logger.info("Desired value selected in dropdown box after multiple try");
    }

    public void Find_Drop_Down_Select_Exact_Value(WebDriver driver, By by, String locator, String dropDownVal) {
        WaitType.PRESENCE_MORE.Explicit_Wait(driver, by);
        List<WebElement> webElements = driver.findElements(by);
        for(int i = 0; i < webElements.size(); i++) {
            WebElement webElement = webElements.get(i);
            if(webElement.isDisplayed()) {
                webElement.click();
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                String placeValue = locator.replace("%value%", dropDownVal);
                WaitType.PRESENCE_MORE.Explicit_Wait(driver, by);
                List<WebElement> dropelements = driver.findElements(By.xpath(placeValue));
                for(WebElement dropelement : dropelements) {
                    if(dropelement.isDisplayed()) {
                        dropelement.click();;
                    }
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        logger.info("Desired value selected in dropdown box after multiple try");
    }

    //Actions Class based WebElements

    public void Action_Mouse_Click(WebDriver driver, By by) {
        Actions bulider = new Actions(driver);
        WebElement webElement = driver.findElement(by);
        bulider.moveToElement(webElement).perform();
        WaitType.CLICKABLE.Explicit_Wait(driver, by);
        bulider.click(webElement).perform();
        logger.info("Webelement clicked by action class");
    }

    public void Action_Double_Click(WebDriver driver, By by) {
        WaitType.CLICKABLE.Explicit_Wait(driver, by);
        Actions bulider = new Actions(driver);
        List<WebElement> webElements = driver.findElements(by);
        for(int i = 0; i < webElements.size(); i++) {
            WebElement webElement = webElements.get(i);
            if(webElement.isDisplayed()) {
                bulider.moveToElement(webElement).doubleClick().build().perform();
                break;
            }
        }
        logger.info("Webelement double clicked by action class");
    }

    public void Action_Mouse_Move(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        Actions bulider = new Actions(driver);
        bulider.moveToElement(webElement).perform();
        logger.info("Mouse over performed by action class");
    }

    public void Action_Mouse_Move_And_Mouse_Click(WebDriver driver, By by) {
        Actions bulider = new Actions(driver);
        WebElement webElement = driver.findElement(by);
        WaitType.CLICKABLE.Explicit_Wait(driver, by);
        bulider.moveToElement(webElement).click().build().perform();
        logger.info("Mouse over and click performed by action class");
    }

    public  boolean  Action_Keyboard_Event(WebDriver driver, By by, String type){
        Keys key = null;
        WebElement webElement = null;
        boolean flag;
        switch (type){
            case "NEW_WINDOW": key = Keys.getKeyFromUnicode('\uE008');
                          break;
            case "NEW_TAB": key = Keys.getKeyFromUnicode('\uE009');
                           break;
            default:
                logger.info("No Such Key Exist");
                throw new RuntimeException("No Such Key Exist");
        }
        Actions bulider = new Actions(driver);
        try{
            JS_Scroll_By_Element(driver, by);
            webElement = driver.findElement(by);
            bulider.moveToElement(webElement).keyDown(key).click().keyUp(key).build().perform();
            flag = false;
        }catch (NoSuchElementException e){
            logger.info("Unable to click webelement at "+type);
            logger.info("Exception: "+e.getMessage());
            flag = true;
        }
        return flag;
    }

    //Attribute based methods

    public String Attribute_Get_Value(WebDriver driver, By by, String attributeName) {
        String Runtime_Text = null;
        try{
            WaitType.PRESENCE_MORE.Explicit_Wait(driver, by);
            Runtime_Text = driver.findElement(by).getAttribute(attributeName);
        }catch (NoSuchElementException e){
            Runtime_Text = "Nil";
            logger.info("Text not exist");
        }
        return Runtime_Text;
    }

    //Displayed, Enabled, Editable

    public boolean Element_Displayed(WebDriver driver, By by) {
        logger.info("Checking webelement is displayed");
        WebElement webElement = driver.findElement(by);
        if(webElement.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean Element_Enabled(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        logger.info("Checking webelement is enabled");
        if(webElement.isEnabled())
            return true;
        else
            return false;
    }

    public boolean Element_Editable(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        logger.info("Checking webelement is editable");
        try {
            webElement.clear();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    //JavaScript based Webelements

    public void JS_Click(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
        logger.info("Webelement clicked by JavascriptExecutor");
    }

    public void JS_Double_Click(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String mouseOverScript = "var evt = document.createEvent('MouseEvents');"+
                "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"+
                "arguments[0].dispatchEvent(evt);";
        js.executeScript(mouseOverScript, webElement);
        logger.info("Webelement double clicked by JavascriptExecutor");
    }

    public void JS_Right_Click(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String mouseOverScript = "var evt = document.createEvent('MouseEvents');"+
                "evt.initMouseEvent('contextmenu',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"+
                "arguments[0].dispatchEvent(evt);";
        js.executeScript(mouseOverScript, webElement);
        logger.info("Webelement context clicked by JavascriptExecutor");
    }

    public void JS_Mouse_Over(WebDriver driver, By by) {
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"+
                "evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else "+
                "if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
        js.executeScript(mouseOverScript, webElement);
        logger.info("Mouse over performed by JavascriptExecutor");
    }

    public void JS_Drop_Down(WebDriver driver, By by, String locator, String dropDownVal) {
        String placeValue = locator.replace("%value%", dropDownVal);
        WaitType.PRESENCE_ONE.Explicit_Wait(driver, by);
        Find_Element_Click(driver, by);
        WaitType.PRESENCE_ONE.Explicit_Wait(driver, By.xpath(placeValue));
        WaitType.CLICKABLE.Explicit_Wait(driver, By.xpath(placeValue));
        JS_Click(driver, By.xpath(placeValue));
        logger.info("Desired value is selected by JavascriptExecutor");
    }

    public void JS_Click_And_Enter_Value(WebDriver driver, By by, String text) {
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
        js.executeScript("arguments[0].value=", "'"+text+"'",webElement);
        logger.info("Successfully Message sent to textbox by JavascriptExecutor");
    }

    public void JS_Scroll_By_Pixels(WebDriver driver,String uptoPixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+uptoPixels+")");
        logger.info("Webpage scrolled by pixels upto "+uptoPixels);
    }

    public void JS_Scroll_By_Element(WebDriver driver,By by) {
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",webElement);
        logger.info("Webpage scrolled by webelement");
    }

    public void JS_Set_Attribute(WebDriver driver, By by, String attributeName, String attributeValue){
        WebElement webElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);\"",webElement, attributeName,
                attributeValue);
        logger.info("Value setted through webelement attribute");
    }

    private static ExpectedCondition<Boolean> JS_Page_Wait(){
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        return pageLoadCondition;
    }

    //Other common utilities
    
    private enum WaitType {
        VISIBLIBITY, CLICKABLE, PRESENCE_ONE,PRESENCE_MORE, PAGE_LOAD;

        private void Explicit_Wait(WebDriver driver, By by) {
            WebDriverWait webDriverWait = new WebDriverWait(driver, untilTimeout, sleepIn);
            WebElement webElement = null;
            switch (this) {
                case VISIBLIBITY:
                    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
                    break;
                case CLICKABLE:
                    webElement = driver.findElement(by);
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
                    break;
                case PRESENCE_MORE:
                    webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
                    break;
                case PRESENCE_ONE:
                    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
                    break;
                case PAGE_LOAD:
                    ExpectedCondition<Boolean> pageLoadCondition = JS_Page_Wait();
                    webDriverWait.until(pageLoadCondition);
                    break;
            }
            logger.info("Wait Performed: "+this.name());
        }
    }

    public By getBy(String locatorType, String locatorValue) {
        switch (locatorType) {
            case "ID":
                return By.id(locatorValue);
            case "LINK":
                return By.linkText(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "TAGNME":
                return By.tagName(locatorValue);
        }
        return null;
    }
}
