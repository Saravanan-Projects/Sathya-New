package test_samples.A_SuperProject.cucumber;


import org.testng.asserts.SoftAssert;
import test_samples.A_SuperProject.base.PageObjectBase;
import test_samples.A_SuperProject.utilities.*;

import java.util.Map;

public class TestContextDecorator extends ObjectBase {

    public final TempVariableClass getTempVariableClass(){
        return getTestContext().getTempVariableClass();
    }

    public final void setTempVariableClass(TempVariableClass tempVariableClass){
        getTestContext().setTempVariableClass(tempVariableClass);
    }

    public final GifAssembler getGifAssembler(){
        return getTestContext().getGifAssembler();
    }

    public final void setGifAssembler(GifAssembler gifAssembler){
        getTestContext().setGifAssembler(gifAssembler);
    }

    public final Screenshot getScreenshot(){
        return getTestContext().getScreenshot();
    }

    public final void setScreenshot(Screenshot screenshot){
        getTestContext().setScreenshot(screenshot);
    }

    public final void setSoftAssert(SoftAssert softAssert){
            getTestContext().setSoftAssert(softAssert);
    }

    public final SoftAssert getSoftAssert(){
        return getTestContext().getSoftAssert();
    }

    public final Map<Class<? extends PageObjectBase>,Object> getPageMap(){
        return getTestContext().getPageMap();
    }

    public final void setPageMap(Map<Class<? extends PageObjectBase>,Object> pageMap){
        getTestContext().setPageMap(pageMap);
    }

    public final ExtentReportUtil getExtentReportUtil(){
        return getTestContext().getExtentReportUtil();
    }

    public final void setExtentReportUtil(ExtentReportUtil extentReportUtil){
        getTestContext().setExtentReportUtil(extentReportUtil);
    }

    public final GridSection getGridSection(){
        return getTestContext().getGridSection();
    }

    public final void setGridSection(GridSection gridSection){
        getTestContext().setGridSection(gridSection);
    }

    public final ObjectMapping getObjectMapping(){
        return getTestContext().getObjectMapping();
    }

    public final void setObjectMapping(ObjectMapping objectMapping){
        getTestContext().setObjectMapping(objectMapping);
    }

    public final RandomData getRandomData(){
        return getTestContext().getRandomData();
    }

    public final void setRandomData(RandomData randomData){
        getTestContext().setRandomData(randomData);
    }

    public final RunConfiguration getRunConfiguration(){
        return getTestContext().getRunConfiguration();
    }

    public final void setRunConfiguration(RunConfiguration runConfiguration){
        getTestContext().setRunConfiguration(runConfiguration);
    }
}
