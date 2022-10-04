package test_samples.A_SuperProject.utilities;


import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    // This belongs to ISuiteListener and will execute before the Suite start
    public void onStart(ISuite result) {
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished
    public void onFinish(ISuite result) {
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext result) {
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext result) {
    }

    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult result) {
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult result) {
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)
    public void onTestStart(ITestResult result) {
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
}