package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Object_Mapping {
    public static File_Read_Write.Props_Read_Write FRWObj;
    public static File_Read_Write.Props_Read_Write CmnObj;
    private static Logger logger= Logger.getLogger(Object_Mapping.class);

    public Object_Mapping(String objectFile) {
        CmnObj = new File_Read_Write("src/test/resources/testartifacts/Common_Identifier.properties").new Props_Read_Write();
        FRWObj = new File_Read_Write("src/test/resources/testartifacts/"+objectFile).new Props_Read_Write(CmnObj);
        logger.info("Locator Property File is loaded successfully");
    }

    public By getLocator(String strElement) {
        String locator = FRWObj.getValues(strElement);
        String locatorType = locator.split("->")[0];
        String locatorValue = locator.split("->")[1];
        switch(locatorType) {
            case "ID": return By.id(locatorValue);
            case "LINK": return By.linkText(locatorValue);
            case "XPATH": return By.xpath(locatorValue);
            case "CSS": return By.cssSelector(locatorValue);
            case "NAME": return By.name(locatorValue);
            case "TAGNME": return By.tagName(locatorValue);
        }
        logger.info("Found Locator: "+strElement);
        return null;
    }

    public String getStrValues(String strValue) {
        logger.info("Found Locator: "+strValue);
        String propValues = FRWObj.getValues(strValue);
        return propValues;
    }
}
