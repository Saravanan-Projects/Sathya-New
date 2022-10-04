package test_samples.A_SuperProject.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import test_samples.A_SuperProject.base.UtilityBase;

public class ExtentReportUtil extends UtilityBase {
    public ExtentReports extent;
    public static ExtentTest scenarioDef;
    public static ExtentTest features;
    private ExtentHtmlReporter htmlReporter;
    public String reportFilePath;
    private String scrnShtName;

//    public void extentReport(TestContextDecorator tc){
////        extent =
//    }

    public void extentConfig(){
    }
    public void attachScreenshot(){
    }
    public void stepReport(String keyword, String step) throws ClassNotFoundException {
    }
    public void flushReport(){
    }
    public void deleteOldReport(){
    }
    public void reportRename(){
    }

    public void testMethodEXTREP(){
        System.out.println("TEST METHOD EXTENT REPORT  CLASS");
    }
}
