package test_samples.A_SuperProject.utilities;

import org.apache.log4j.Logger;
import test_samples.A_SuperProject.base.UtilityBase;
import utilities.File_Read_Write;

public class RunConfiguration extends UtilityBase {

    File_Read_Write.XML_Read XMLObj = null;
    public String strSathyaURL;
    public String strUserName;
    public String strPassword;
    private static Logger logger= Logger.getLogger(RunConfiguration.class);

    public RunConfiguration() {
        XMLObj = new File_Read_Write("AutomationConfig.xml").new XML_Read();
        strSathyaURL = XMLObj.getValues("ApplnURL");
        strUserName = XMLObj.getValues("Username");
        strPassword = XMLObj.getValues("Password");
        logger.info("Application URL: "+strSathyaURL);
        logger.info("Username: "+strUserName);
        logger.info("Password: "+strPassword);
    }
}
