package test_samples.B_Sub_Projects.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "G:\\New folder\\Framework_Original\\src\\test\\java\\test_samples\\B_Sub_Projects\\features\\Clicking_Each_Product.feature"
        ,glue = {"test_samples.B_Sub_Projects.stepdefinitions","test_samples.B_Sub_Projects.runner"}
        , monochrome=true
        , dryRun=false
        ,strict = false
        ,tags="@Sample_1")
public class Test_Run extends AbstractTestNGCucumberTests {
}
