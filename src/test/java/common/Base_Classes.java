package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.Air_Conditioner_Page;
import pages.AC_Product_Page;
import pages.Sathya_Base_Page;
import runners.Hooks;
import sun.plugin.javascript.navig.Array;
import utilities.File_Read_Write;

import java.util.*;

public class Base_Classes{
    protected static WebDriver driver;
    protected static Air_Conditioner_Page ACModPgeObj;
    protected static AC_Product_Page ACPrdPgeObj;
    private static  GridSection grid;
    private static Logger logger = Logger.getLogger(Base_Classes.class);

    public Base_Classes() {
        driver = Hooks.driver;
        grid = new GridSection();
        ACModPgeObj = PageFactory.initElements(driver, Air_Conditioner_Page.class);
        ACPrdPgeObj = PageFactory.initElements(driver, AC_Product_Page.class);
        logger.info("Object Creation: "+ACModPgeObj+", "+ACPrdPgeObj);
    }

    public Base_Classes(boolean flag) {
        logger.info("Configuring DataTable is done");
    }

    //Nested Class
    protected class FileDetails {
        public String file_name = null;
        public String file_type = null;
        public String sheet_name = null;

        public FileDetails(String fileName, String fileType, String sheetName){
            this.file_name = fileName;
            this.file_type = fileType;
            this.sheet_name = sheetName;
            logger.info("FileDetails class instantiated successfully for datatable");
        }
    }

    private static void readExcelFile(Map<String,String> file){
        logger.info("Reading Excel begins: "+file.get("FILE_NAME")+".xlsx");
        File_Read_Write.Excel_Read EXLReadObj = new File_Read_Write("src/test/resources/testdata/"
                +file.get("FILE_NAME")+".xlsx").new Excel_Read();
        logger.info("Reading Sheet: "+file.get("SHEET_NAME"));
        EXLReadObj.getSheetByName(file.get("SHEET_NAME"));
        grid.getExcelDetails(EXLReadObj);
        String[] columns = file.get("COLUMNS").split(",") ;
        logger.info("Reading Columns: "+ Arrays.asList(columns));
        int totalRows = EXLReadObj.getNumRows();
        for(int i=1; i<totalRows; i++){
            for(int j=0; j<columns.length; j++){
                int colIndex = grid.getHeaderIndex(columns[j].trim());
                Hooks.TempClass.setEachPrdtDetails(EXLReadObj.getData(i, colIndex));
            }
            Hooks.TempClass.setAllPrdtsDetails(i-1,Hooks.TempClass.getEachPrdtDetails());
            Hooks.TempClass.clearEachPrdtDetails();
        }
       logger.info("Excel file read successfully");
    }

    private static void readTextFile(Map<String,String> file){
        String line;
        logger.info("Reading text file begins: "+file.get("FILE_NAME")+".txt");
        File_Read_Write.Text_Read TXTReadObj = new File_Read_Write("src/test/resources/messageformats/"
                +file.get("FILE_NAME")+".txt").new Text_Read();
        while ((line=TXTReadObj.getLine()) != null){
            Hooks.TempClass.setFileLines(line+"\n");
        }
        System.out.println(Hooks.TempClass.getFileLines());
        logger.info("text file read successfully");
    }

    protected static void totalProducts(Module modObj,int pages){
        int total = 0;
        for (int i = 1; i <= pages; i++) {
            Hooks.TempClass.setPrdtsPerPge(i, modObj.totalPrdts());
            total +=  Hooks.TempClass.getPrdtsPerPge(i);
            if(i!=pages) modObj.clickPage("forward");
            System.out.println(Hooks.TempClass.getPrdtsPerPge(i)+" Products in Page: "+ i);
        }
        Hooks.TempClass.setPages(pages);
        Hooks.TempClass.setTotalProducts(total);
        for (int i = 1; i < pages; i++) {
            modObj.clickPage("backward");
        }
        System.out.println("Total Products in " +pages+" pages : "+total);
        logger.info("Calculated the total products successfully");
    }

    protected static void eachProduct(Module modObj,Product pageObj, List<String> columns) {
        int totPge = Hooks.TempClass.getPages();
        String parentID = Module.childWinAxn("GetParentID");
        Hooks.TempClass.setPrntWinID(parentID);
        Hooks.TempClass.setPrdtDetails(0, new ArrayList(columns));
        for(int i = 1, k = 1;i<=totPge;i++){
            int totPrdtsPerPge = Hooks.TempClass.getPrdtsPerPge(i);
            for(int j = 1; j <= totPrdtsPerPge; j++){
                modObj.clickEachPrdt(j);
                Set<String> winHandles = Module.childWinAxn("GetWindowIDs");
                Hooks.screenShotObj.addScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
                for (String handle: winHandles) {
                    if (!handle.equals(Hooks.TempClass.getPrntWinID())){
                        Hooks.TempClass.setChildWinID(handle);
                        Module.childWinAxn("switchToChild");
                        Hooks.screenShotObj.addScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
                        List<String> ACValues = pageObj.getACValues(columns);
                        Hooks.TempClass.setPrdtDetails(k++, (ArrayList) ACValues);
                        Module.childWinAxn("CloseChildWindow");
                    }
                }
                Module.childWinAxn("switchToParent");
            }
            if(i!=Hooks.TempClass.getPages()) modObj.clickPage("forward");
        }
    }

    protected static void writeIntoFile( List<FileDetails> fileDetails){
        File_Read_Write.Excel_Write EXLWriteObj = null;
        FileDetails fileObj = fileDetails.get(0);
        if(fileObj.file_type.contains("EXCEL")){
            EXLWriteObj = new File_Read_Write().new Excel_Write();
            EXLWriteObj.toCreateSheet(fileObj.sheet_name);
            for(int i = 0; i <= Hooks.TempClass.gettotalProducts();i++) {
                EXLWriteObj.rowCreation(i);
                int j = 0;
                if(i==0)EXLWriteObj.setColTitleBold(true);
                else  EXLWriteObj.setColTitleBold(false);
                for (Object n: Hooks.TempClass.getPrdtsDetails(i)) {
                    EXLWriteObj.cellCreationAndWrite(j++,n.toString());
                }
            }
            EXLWriteObj.fileCreation("src/test/resources/testdata/"+fileObj.file_name);
        }
    }

    protected static void applyFilters( Map<String,String> filters){
        Set<String> keys = filters.keySet();
        List<String> keyList = new ArrayList<String>(keys);
        boolean flag = false;
        List<String> values = new ArrayList<>();
        for (int i = 0; i < filters.size(); i++){
            String key = keyList.get(i);
            if(key.contains("PRICE")) values.add(filters.get(keyList.get(i)));
            else values.addAll(Arrays.asList(filters.get(keyList.get(i)).split(",")));
            switch (Hooks.ModuleName){
                case "Airconditioner": flag = ACModPgeObj.moduleFilters(key,values);
                    break;
                case "Refrigerator":
                    break;
            }
            if(!flag)  Sathya_Base_Page.commonFilters(key,values);
            values.clear();
        }
    }

    protected static void readFromFile( Map<String,String> file){
        if(file.get("FILE_TYPE").contains("EXCEL")){
           readExcelFile(file);
        } else if(file.get("FILE_TYPE").contains("TEXT")){
            readTextFile(file);
        }
    }

    protected final void reporting(String keyword, String step){
        try
        {
            Hooks.extentObj.stepReport(keyword, step);
        }catch (Exception e){
            logger.fatal("Reporting File Not Created"+e.getMessage());
            e.printStackTrace();
        }

    }
}
