package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import runners.Hooks;

import java.util.List;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public interface Module {
    Logger logger= Logger.getLogger(Module.class);
    void clickPage(String direction);
    List<WebElement> productsList(By totalPrdts);
    int totalPrdts();
    void clickEachPrdt(int index);
    boolean moduleFilters(String filters, List<String> values);

    static void setUp() {
        String browser = Hooks.EnvObj.getValues("BROWSER_NAME");
        logger.info(browser+" browser is opening");
        switch(browser) {
            case "Chrome":
                WebDriverManager.getInstance(CHROME).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("enable-automation");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-browser-side-navigation");
                options.addArguments("--disable-gpu");
                options.addArguments("--ignore-ssl-errors=yes");
                options.addArguments("--ignore-certificate-errors");
                Hooks.driver=new ChromeDriver(options);
                break;
            case "FireFox":
                WebDriverManager.getInstance(FIREFOX).setup();
                Hooks.driver=new FirefoxDriver();
                break;
        }
        logger.info(browser+" browser is opened successfully");
        Hooks.WebObj.Delete_Cookies(Hooks.driver);
    }

    static <Any> Any childWinAxn(String option) {
        switch (option) {
            case "GetParentID":
                return (Any) Hooks.WebObj.getWindowID(Hooks.driver);
            case "GetWindowIDs":
                return (Any) Hooks.WebObj.getWindowIDs(Hooks.driver);
            case "switchToParent":
                Hooks.WebObj.switchWindow(Hooks.driver, Hooks.TempClass.getPrntWinID()); break;
            case "switchToChild":
                Hooks.WebObj.switchWindow(Hooks.driver, Hooks.TempClass.getChildWinID()); break;
            case "CloseChildWindow" : Hooks.WebObj.Close_Browser(Hooks.driver); break;
        }
        logger.info(" Window Action "+option+" executed successfully");
        return null;
    }
}
