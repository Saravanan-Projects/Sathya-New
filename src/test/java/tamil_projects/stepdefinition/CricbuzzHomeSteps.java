package tamil_projects.stepdefinition;

import io.cucumber.java.en.*;
import tamil_projects.pageobjects.AustraliaSeriesPage;
import tamil_projects.pageobjects.CricbuzzHomePage;

public class CricbuzzHomeSteps extends CricbuzzHomePage {


    @Given("^I launch the URL$")
    public void home_Page() throws Exception {

        launchBrowser();

        Thread.sleep(1000);

    }

    @And("^I click the series button")
    public void click_series_button() throws InterruptedException {
        CricbuzzHomePage home = new CricbuzzHomePage();
        home.clickSeries();
        Thread.sleep(1000);
    }


    @Then("^I close the browser$")
    public void closeBrowser() {

        driver.quit();
    }
}
