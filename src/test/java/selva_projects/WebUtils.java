package selva_projects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtils {

	public WebDriver setDriverProperty() {
		System.setProperty("webdriver.chrome.driver", "G:\\Older\\IT_Software\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public void loadAppln(WebDriver driver, String url) {
		driver.get(url);
	}

	public void windowMax(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void findElementEnterData(WebDriver driver, By by, String data) {
		WebElement ele = driver.findElement(by);
		ele.sendKeys(data);
	}

	public void findElementClick(WebDriver driver, By by) {

		WebElement ele = driver.findElement(by);
		ele.click();
	}

	public void waitThread(int milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitExplicit(WebDriver driver, By by, int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds).getSeconds());
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	public void actionMouseClick(WebDriver driver, By by) {
        Actions bulider = new Actions(driver);
        WebElement webElement = driver.findElement(by);
        bulider.moveToElement(webElement).perform();
        bulider.click(webElement).perform();
	}

	public void jsMouseOver(WebDriver driver, By by) {
		WebElement webElement = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"+
				"evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else "+
				"if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
		js.executeScript(mouseOverScript, webElement);
	}
}