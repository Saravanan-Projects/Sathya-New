package tamil_projects.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.FindBy;

public class CricbuzzHomePage {
    public static WebDriver driver;
    static JavascriptExecutor js = null;

    @FindBy(id = "seriesDropDown")
    public WebElement series;

    public CricbuzzHomePage() {

        PageFactory.initElements(driver, this);

    }

    /**
     * Launch browser is used to set the path for chrome driver and launch the url
     *
     * @throws InterruptedException the interrupted exception
     */

    public void launchBrowser() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "G:\\Older\\IT_Software\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();

        driver.get("https://www.cricbuzz.com/");

        driver.manage().window().maximize();

        Thread.sleep(1000);
    }
    public void clickSeries() throws InterruptedException {

        series.click();
        for (int i = 0; i <= 2; i++)

        {
            js = (JavascriptExecutor) driver;
            Thread.sleep(500);
            js.executeScript("window.scrollBy(0,10000)");
            Thread.sleep(1000);
        }

    }
}
