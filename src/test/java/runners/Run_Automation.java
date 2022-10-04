package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "G:\\New folder\\Framework_Original\\src\\test\\resources\\testcases\\airconditioner\\Clicking_Each_Product.feature"
        ,glue = {"stepdefinitions","runners"}
        , plugin = {"pretty:STDOUT", "html:G:\\Sathya_Automation\\reports\\DefaultCucumberReports\\cucumber-html-report"
        , "pretty:G:\\Sathya_Automation\\reports\\DefaultCucumberReports\\cucumber-pretty.txt"
        , "json:G:\\Sathya_Automation\\reports\\DefaultCucumberReports\\cucumber.json"
        , "usage:G:\\Sathya_Automation\\reports\\DefaultCucumberReports\\cucumber-usage.json"
        , "junit:G:\\Sathya_Automation\\reports\\DefaultCucumberReports\\cucumber-results.xml"
        , "rerun:G:\\Sathya_Automation\\reports\\DefaultCucumberReports\\rerun.txt"}
        , monochrome=true
        , dryRun=false
        ,strict = false
        ,tags="@Project_Name__Sathya_Automation")
public class Run_Automation  extends AbstractTestNGCucumberTests {
}
