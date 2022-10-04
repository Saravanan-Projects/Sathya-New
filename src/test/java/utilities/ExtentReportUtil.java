package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import runners.Hooks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ExtentReportUtil {
    public ExtentReports extent;
    public static ExtentTest scenarioDef;
    public static ExtentTest features;
    private   ExtentHtmlReporter htmlReporter;
    public String reportFilePath;
    private String scrnShtName;
    private static Logger logger= Logger.getLogger(ExtentReportUtil.class);

    public void extentReport(){
        reportFilePath = System.getProperty("user.dir") +"/reports/ExtentReports/";
        extent = new ExtentReports();
        htmlReporter = new ExtentHtmlReporter(reportFilePath+"Test.html");
        extent.attachReporter(htmlReporter);
        logger.info("Reporting File Created");
    }

    public void extentConfig(){
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("USER",System.getProperty("user.name"));
        extent.setSystemInfo("BROWSER",Hooks.EnvObj.getValues("BROWSER_NAME"));

        htmlReporter.config().setDocumentTitle(Hooks.ScenarioName);
        htmlReporter.config().setReportName(Hooks.TesterName);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent.attachReporter(htmlReporter);
        logger.info("Report Configuration is done");
    }

    public void attachScreenshot(){
        scrnShtName = Random_Data.getUniqueDate("HHmmss");
        try{
            logger.info("Attaching screenshot");
            String fileName = reportFilePath+scrnShtName+".png";
            File file = new File(fileName);
            byte[] screenshot = Hooks.screenShotObj.getScreenshot();
            ByteArrayInputStream bis = new ByteArrayInputStream(screenshot);
            BufferedImage bufferedImage = ImageIO.read(bis);
            ImageIO.write(bufferedImage, "png",  file);
            scenarioDef.fail("details").addScreenCaptureFromPath(fileName);
        }catch (Exception e){
            logger.fatal("Unable to attach screen shot. Exception: "+e.getMessage());
            e.printStackTrace();
        }

    }

    public void stepReport(String keyword, String step) throws ClassNotFoundException {
        GherkinKeyword gherkinKeyword = new GherkinKeyword(keyword);;
        scenarioDef.createNode(gherkinKeyword, step);
        logger.info("Step report created");
    }

    public void flushReport(){
        extent.flush();
    }

    public void deleteOldReport(){
        File oldFile = new File(reportFilePath+Hooks.ScenarioName+".html");
        oldFile.delete();
        logger.info("Old Report file is deleted successfully "+ Hooks.ScenarioName+".html");
    }

    public void reportRename(){
        File oldFile = new File(reportFilePath+"Test.html");
        File newFile = new File(reportFilePath+Hooks.ScenarioName+".html");
        oldFile.renameTo(newFile);
        logger.info("Report file renamed successfully");
    }
}
