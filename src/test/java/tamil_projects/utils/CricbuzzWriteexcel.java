package tamil_projects.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CricbuzzWriteexcel {
    static WebDriver driver;
    public static File src;
    public static FileInputStream fis;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static FileOutputStream fos;
    public static XSSFRow row1;
    public static XSSFRow row0;

    public static String filePath = "G:\\New folder\\Framework_Original\\src\\test\\java\\tamil_projects\\testdata\\DTM_Cricbuzz.xlsx";
    static String header = "Cricket Schedule - INTERNATIONAL";
    public static String countryToVerify = "Australia";

    /**
     * Writedata - method is used to write schedule match details into excel
     *
     * @param date  is the scheduled date
     * @param venue is the scheduled match
     * @param teams is the scheduled teams
     * @param time  is the scheduled time
     * @throws Exception the exception
     */
    public static void writedata(List<WebElement> date,List<WebElement> venue,List<WebElement> teams,List<WebElement> time) throws Exception {

        src = new File(filePath);
        fis = new FileInputStream(src);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.createSheet("Scenario1");

        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        int i = 1;
        XSSFRow row0=sheet.createRow(0);
        sheet.getRow(0).createCell(0).setCellValue(header);
        sheet.getRow(0).getCell(0).setCellStyle(style);

        for (int j = 0; j <date.size(); j++)
        {

            String scheduledate = date.get(j).getText();



            if (scheduledate.contains("SAT")) {

                System.out.println("AM SATURDAY -OUT OF SCOPE");
            }

            else {
                row1 = sheet.createRow(i);
                row1 = sheet.createRow(i + 1);

                sheet.getRow(i).createCell(0).setCellValue(scheduledate);
                sheet.getRow(i).getCell(0).setCellStyle(style);
                sheet.getRow(i).getCell(0).setCellStyle(style);
                String scheduleteam = teams.get(j).getText();
                String schedulevenue = venue.get(j).getText();
                String scheduletime = time.get(j).getText();
                sheet.getRow(i + 1).createCell(0).setCellValue(scheduleteam);
                sheet.getRow(i + 1).getCell(0).setCellStyle(style);
                sheet.getRow(i + 1).createCell(1).setCellValue(schedulevenue);
                sheet.getRow(i + 1).createCell(2).setCellValue(scheduletime);
                i++;
                i++;

            }
        }

        fos = new FileOutputStream(src);
        workbook.write(fos);
        workbook.close();
        //4,56,8,9
    }

    /**
     * Write australia series method is used to retrieve all the australia matches
     * and write in excel
     *
     * @param matchWithMonth      is the australia series month
     * @throws Exception the exception
     */

    public static void writeAustraliaSeries(Map<String, ArrayList<String>> matchWithMonth, ArrayList<String> monthList) throws Exception {

        src = new File(filePath);
        fos = new FileOutputStream(src);
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Scenario3");

        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        int b = 0;
        row0 = sheet.createRow(b);
        sheet.getRow(0).createCell(0).setCellValue("Month");
        sheet.getRow(0).createCell(1).setCellValue("Series Name");
        sheet.getRow(0).getCell(0).setCellStyle(style);
        sheet.getRow(0).getCell(1).setCellStyle(style);


        //Set<String> monthNames = matchWithMonth.keySet();
        for(String monthName: monthList){
            sheet.createRow(++b);
            sheet.getRow(b).createCell(0).setCellValue(monthName);
            ArrayList<String> tourDetails = matchWithMonth.get(monthName);
            for(String teamDetails: tourDetails){
                sheet.getRow(b).createCell(1).setCellValue(teamDetails);
                sheet.createRow(++b);
            }
        }

        fos = new FileOutputStream(src);
        workbook.write(fos);
        workbook.close();

    }

}
