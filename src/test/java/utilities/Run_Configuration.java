package utilities;

import org.apache.log4j.Logger;

public class Run_Configuration {

    File_Read_Write.XML_Read XMLObj = null;
    public String strSathyaURL;
    public String strUserName;
    public String strPassword;
    private static Logger logger= Logger.getLogger(Run_Configuration.class);

    public Run_Configuration() {
        XMLObj = new File_Read_Write("AutomationConfig.xml").new XML_Read();
        strSathyaURL = XMLObj.getValues("ApplnURL");
        strUserName = XMLObj.getValues("Username");
        strPassword = XMLObj.getValues("Password");
        logger.info("Application URL: "+strSathyaURL);
        logger.info("Username: "+strUserName);
        logger.info("Password: "+strPassword);
    }
}
