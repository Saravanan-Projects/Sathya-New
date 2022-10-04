package test_samples.A_SuperProject.cucumber;

import org.testng.asserts.SoftAssert;
import test_samples.A_SuperProject.base.PageObjectBase;
import test_samples.A_SuperProject.utilities.*;

import java.util.Map;

public class TestContext {
    private TempVariableClass tempVariableClass;
    private GifAssembler gifAssembler;
    private Screenshot screenshot;
    private Map<Class<? extends PageObjectBase>,Object> pageMap;
    private SoftAssert softAssert;
    private ExtentReportUtil extentReportUtil;
    private GridSection gridSection;
    private ObjectMapping objectMapping;
    private RandomData randomData;
    private RunConfiguration runConfiguration;


    Map<Class<? extends PageObjectBase>, Object> getPageMap() {
        return pageMap;
    }

    void setPageMap(Map<Class<? extends PageObjectBase>, Object> pageMap) {
        this.pageMap = pageMap;
    }

    TempVariableClass getTempVariableClass() {
        return tempVariableClass;
    }

    void setTempVariableClass(TempVariableClass tempVariableClass) {
        this.tempVariableClass = tempVariableClass;
    }

    GifAssembler getGifAssembler() {
        return gifAssembler;
    }

    void setGifAssembler(GifAssembler gifAssembler) {
        this.gifAssembler = gifAssembler;
    }

    Screenshot getScreenshot() {
        return screenshot;
    }

    void setScreenshot(Screenshot screenshot) {
        this.screenshot = screenshot;
    }

    SoftAssert getSoftAssert() {
        return softAssert;
    }

    void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }

    ExtentReportUtil getExtentReportUtil() {
        return extentReportUtil;
    }

    void setExtentReportUtil(ExtentReportUtil extentReportUtil) {
        this.extentReportUtil = extentReportUtil;
    }

    GridSection getGridSection() {
        return gridSection;
    }

    void setGridSection(GridSection gridSection) {
        this.gridSection = gridSection;
    }

    ObjectMapping getObjectMapping() {
        return objectMapping;
    }

    void setObjectMapping(ObjectMapping objectMapping) {
        this.objectMapping = objectMapping;
    }

    RandomData getRandomData() {
        return randomData;
    }

    void setRandomData(RandomData randomData) {
        this.randomData = randomData;
    }

    RunConfiguration getRunConfiguration() {
        return runConfiguration;
    }

    void setRunConfiguration(RunConfiguration runConfiguration) {
        this.runConfiguration = runConfiguration;
    }
}
