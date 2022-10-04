package tamil_projects.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "G:\\New folder\\Framework_Original\\src\\test\\java\\tamil_projects\\features\\Australia_Matches_Alone.feature"
        ,glue = {"tamil_projects.stepdefinition"}
        , monochrome=true
        , dryRun=false
        ,strict = false
        ,tags="@Australiaseries")
public class Run_Automation  extends AbstractTestNGCucumberTests {
}
