package runners;

import java.io.File;
import java.util.Collection;

import com.aventstack.extentreports.gherkin.model.Feature;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import common.WebUtils;
import pages.Sathya_Base_Page;
import utilities.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static utilities.ExtentReportUtil.features;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Run_Configuration RunConfig;
    public static Object_Mapping ObjMap;
    public static Temp_Variable_Class TempClass;
    public static WebUtils WebObj;
    public static String ScenarioName;
    public static String FeatureName;
    public static String TesterName;
    public static String ScenarioDescription;
    public static String ScreenshotPath;
    public static String ModuleName;
    public static File_Read_Write.Props_Read_Write EnvObj;
    public static int count = 0;
    public static Screenshot screenShotObj;
    protected static GifAssembler gifAssembler;
    public static ExtentReportUtil extentObj;
    public static SoftAssert softAssertion;
    private static String currentClass;
    private static Logger  logger = Logger.getLogger(Hooks.class);

    public static void selvaSupport(){
        EnvObj = new File_Read_Write("EnvironmentSettings.properties").new Props_Read_Write();
        WebObj = new WebUtils(EnvObj.getValues("UNTIL_TIMEOUT"), EnvObj.getValues("SLEEP_IN"));
    }
    @Before
    public void setUP(Scenario scenario) {
        System.setProperty("user.dir",System.getProperty("user.dir"));
        PropertyConfigurator.configure(System.getProperty("user.dir")+File.separator +"src/main/resources/log4j.properties");

        EnvObj = new File_Read_Write("EnvironmentSettings.properties").new Props_Read_Write();

        ScenarioName = scenario.getName();
        FeatureName = scenario.getId().split(";")[0].replace("-"," ");
        logger.info("Executing Scenario: "+ScenarioName);
        ScenarioDescription = ScenarioName.replaceAll("\\s","");
        ScreenshotPath = System.getProperty("user.dir")+"/output/"+ScenarioName;
        logger.info("ScreenShot Path: "+ScreenshotPath);
        File file = new File(ScreenshotPath);
        if(!file.exists()){
            file.mkdirs();
        }
        TempClass = new Temp_Variable_Class();
        RunConfig = new Run_Configuration();
        softAssertion= new SoftAssert();
        WebObj = new WebUtils(EnvObj.getValues("UNTIL_TIMEOUT"), EnvObj.getValues("SLEEP_IN"));
        ObjMap = new Object_Mapping("Product_Identifier.properties");
        logger.info("Object Creation: "+TempClass+","+RunConfig+","+softAssertion+","+WebObj+","+ObjMap);
        features =  extentObj.extent.createTest(Feature.class,ScenarioName);
        ExtentReportUtil.scenarioDef = ExtentReportUtil.features.createNode(ScenarioName);
        Collection<String> sourceTagNames = scenario.getSourceTagNames();
        for(String name: sourceTagNames) {
            String[] tags = name.split("@");
            for(String desired_tags: tags) {
                if(desired_tags.contains("Module_Name__")) {
                    ModuleName = desired_tags.split("__")[1];
                } else if(desired_tags.contains("Tester_Name__")){
                    TesterName =  desired_tags.split("__")[1];
                }
            }
        }
        screenShotObj = new Screenshot(ScenarioName,ModuleName);
        logger.info("Object Creation: "+TempClass+", "+RunConfig+", "+softAssertion+", "+
                WebObj+", "+ObjMap+", "+features+", "+ExtentReportUtil.scenarioDef+", "+screenShotObj);
        extentObj.deleteOldReport();
        extentObj.extentConfig();
    }
    
    @After
    public void tearDown(){
        try {
            gifAssembler = Hooks.screenShotObj.getGifAssembler();
            logger.info("PDF File Creation Started");
            Hooks.gifAssembler.generate_screenshotWithPDF();
            logger.info("PDF File Creation Finished Successfully");
            softAssertion.assertAll();
            logger.info("Exception raised through softassertion(if any)");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int teststep(){
        count++;
        return count;
    }

    public static void openReport(){
        logger.info("Report is opening");
        Sathya_Base_Page.reportOpening(extentObj.reportFilePath+Hooks.ScenarioName+".html");
        logger.info("Report is opened sucessfully");
    }
}
