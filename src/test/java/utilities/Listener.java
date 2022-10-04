package utilities;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.Logger;
import org.testng.*;
import runners.Hooks;

import static utilities.ExtentReportUtil.features;

public class Listener implements ITestListener {
    public static ExtentReportUtil extentObj;
    private static Logger logger = Logger.getLogger(Listener.class);
    // This belongs to ISuiteListener and will execute before the Suite start
    public void onStart(ISuite result) {
        logger.info("About to begin executing Suite " + result.getName());
        Reporter.log("About to begin executing Suite " + result.getName(), true);
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished
    public void onFinish(ISuite result) {
        logger.info("About to end executing Suite " + result.getName());
        Reporter.log("About to end executing Suite " + result.getName(), true);
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext result) {
        logger.info("About to begin executing Test " + result.getName());
        Reporter.log("About to begin executing Test " + result.getName(), true);
        extentObj = new ExtentReportUtil();
        extentObj.extentReport();
        Hooks.extentObj = extentObj;
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext result) {
        logger.info("Completed executing test " + result.getName());
        Reporter.log("Completed executing test " + result.getName(), true);
        extentObj.flushReport();
        extentObj.reportRename();
        Hooks.WebObj.Quit_Browser(Hooks.driver);
        Hooks.openReport();
    }

    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Scenario is PASSED");
        features.log(Status.PASS, MarkupHelper.createLabel(" PASSED ", ExtentColor.GREEN));
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult result) {
        logger.info("Test Scenario is FAILED");
        features.log(Status.FAIL, MarkupHelper.createLabel(" FAILED ", ExtentColor.RED));
        features.fail(result.getThrowable());
        extentObj.attachScreenshot();
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)
    public void onTestStart(ITestResult result) {
        logger.info("The execution of the main test starts now");
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult result) {
        logger.info("Test Scenario is SKIPPED");
        features.log(Status.SKIP, MarkupHelper.createLabel(" SKIPPED ", ExtentColor.BLUE));
        features.skip(result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
}