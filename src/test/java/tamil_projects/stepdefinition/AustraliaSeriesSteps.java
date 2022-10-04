package tamil_projects.stepdefinition;

import io.cucumber.java.en.*;
import tamil_projects.pageobjects.*;

public class AustraliaSeriesSteps  {


    @And("^I verify the list of australia matches$")
    public void listOfAustraliaMatches() throws Exception {
        AustraliaSeriesPage ss=new AustraliaSeriesPage();
        ss.writeSeries(0);

    }

}
