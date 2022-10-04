package selva_projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipKartTask1 {
	WebDriver driver = null;
	WebUtils webUtils = null;

	FlipKartTask1() {
		webUtils = new WebUtils();
	}

	public void openApplication() {
		driver = webUtils.setDriverProperty();
		webUtils.loadAppln(driver,"http://flipkart.com");
		webUtils.windowMax(driver);
	}

	public void enterCredentials() {
		webUtils.findElementEnterData(driver, By.xpath("(//input[@type='text'])[2]"), "7418069515");
		webUtils.findElementEnterData(driver, By.xpath("//input[@type='password']"), "123456abcd");
		webUtils.findElementClick(driver, By.xpath("(//button[@type='submit'])[2]"));
	}

	public void productNavigation() {
		webUtils.waitThread(10000);
		webUtils.waitExplicit(driver, By.xpath("//*[text()='Appliances']"), 30);
		webUtils.findElementClick(driver, By.xpath("//*[text()='Appliances']"));
		webUtils.waitExplicit(driver, By.xpath("//a[@class='_2-1PJ_']"), 30);
		webUtils.findElementClick(driver, By.xpath("//a[@class='_2-1PJ_']"));
		webUtils.waitExplicit(driver, By.xpath("//div[@class='_4rR01T']"), 30);
		webUtils.findElementClick(driver, By.xpath("//div[@class='_4rR01T']"));
	}

	public void loggingOut() {
		webUtils.waitExplicit(driver, By.xpath("//div[text()='My Account']"), 30);
		webUtils.jsMouseOver(driver, By.xpath("//div[text()='My Account']"));
		webUtils.findElementClick(driver, By.xpath("//div[text()='Logout']"));
	}
	

	public static void main(String[] args) {

		FlipKartTask1 flipKartTask1 = new FlipKartTask1();

		flipKartTask1.openApplication();
		flipKartTask1.enterCredentials();
		//flipKartTask1.productNavigation();
		flipKartTask1.loggingOut();

	}
}