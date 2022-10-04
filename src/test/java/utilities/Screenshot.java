package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import runners.Hooks;

public class Screenshot {
    private static String ScenarioName;
    private static String ModuleName;
    private static GifAssembler gifAssembler;
    private static Logger logger= Logger.getLogger(Screenshot.class);

    public Screenshot(String ScenarioName, String ModuleName){
        this.ScenarioName = ScenarioName;
        this.ModuleName = ModuleName;
        gifAssembler = new GifAssembler();
    }

    public byte[] getScreenshot(){
        byte[] screenshot = ((TakesScreenshot)Hooks.driver).getScreenshotAs(OutputType.BYTES);
        logger.info("Screenshot is accessed");
        return screenshot;
    }

    public void addScreenShot(String details){
        String Formatted_string = Random_Data.getUniqueDate("yyyyMMddHHmmss");
        int teststep = Hooks.teststep();
        String scrnShtNme = ModuleName+"_"+ScenarioName+"_"+Hooks.TempClass.getTestcaseID()+
                "_TS_"+teststep+"_"+Formatted_string;
        byte[] screenshot = getScreenshot();
        gifAssembler.addFrame(scrnShtNme,screenshot);
        logger.info("Screenshot is taken successfully");
    }

    public GifAssembler getGifAssembler(){
        return  this.gifAssembler;
    }
}
