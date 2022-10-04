package utilities;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Random_Data {
    private static DateTimeFormatter dtf = null;
    private static Logger logger= Logger.getLogger(Random_Data.class);


    public static String getUniqueDate(String format) {
        String formatted_string = null;
        switch(format) {
            case "HHmmss": dtf = DateTimeFormatter.ofPattern("HHmmss");
                break;
            case "yyMMdd": dtf = DateTimeFormatter.ofPattern("yyMMdd");
                break;
            case "yyyyMMdd": dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                break;
            case "yyyyMMddHHmmss": dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                break;
        }
        LocalDateTime now = LocalDateTime.now();
        formatted_string = dtf.format(now);
        logger.info("Accessed RandomData for "+format);
        return formatted_string;
    }

    public static String randomAlphaNumeric(int length) {
        logger.info("Accessed RandomAlphaNumeric for length "+length);
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomNumber(int length) {
        logger.info("Accessed RandomNumeric for length "+length);
        return RandomStringUtils.randomNumeric(length);
    }
}