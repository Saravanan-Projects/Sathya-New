package test_samples.A_SuperProject.utilities;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.*;
import org.w3c.dom.Document;
import test_samples.A_SuperProject.base.UtilityBase;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Properties;

public class FileReadWrite {
    private File file = null;
    private FileInputStream in = null;
    private static Logger logger;
    {
        String currentClass;
        Class<?> enclosingClass = getClass().getEnclosingClass();
        if (enclosingClass != null) currentClass = enclosingClass.getName();
        else currentClass = getClass().getName();
        logger= Logger.getLogger(currentClass);
    }
    public FileReadWrite(String fileName) {
        try {
            logger.info("Reading file "+fileName);
            file = new File(System.getProperty("user.dir")+"/"+fileName);
            in = new FileInputStream(file);
        }
        catch(IOException e) {
            logger.info("Unable to read file "+fileName);
            logger.fatal("Exception: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unable to read file "+fileName);
        }
    }
    public FileReadWrite(){ }

    //To Read Property File
    public class PropsReadWrite{
        private Properties props;

        public PropsReadWrite() {
            try {
                props = new Properties();
                props.load(in);
                in.close();
                logger.info("Reading Property File");
            }
            catch(IOException e) {
                logger.info("Unable to read property file");
                logger.fatal("Exception: "+e.getMessage());
                throw new RuntimeException("Unable to read property file");
            }
        }

        public PropsReadWrite(FileReadWrite.PropsReadWrite JntProps) {
            try {
                props = new Properties();
                props.load(in);
                in.close();
                props.putAll(JntProps.props);
                logger.info("Reading Parent Property File");
            }
            catch(IOException e) {
                logger.info("Unable to read parent property file");
                logger.fatal("Exception: "+e.getMessage());
                throw new RuntimeException("Unable to read parent property file");
            }
        }

        public String getValues(String keyElement) {
            logger.info("Reading KeyElement: "+keyElement);
            String keyValue  = props.getProperty(keyElement);
            return keyValue;
        }
    }

    //To Read XML file
    public class XMLRead{
        private DocumentBuilderFactory dbFactory = null;
        private Document doc = null;
        private DocumentBuilder dBuilder= null;

        public XMLRead() {
            try {
                logger.info("Reading XML file");
                dbFactory = DocumentBuilderFactory.newInstance();
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(file);
            }
            catch(Exception e) {
                logger.info("Unable to read XML file");
                logger.fatal("Exception: "+e.getMessage());
                throw new RuntimeException("Unable to read XML file");
            }
        }


        public String getValues(String tagName) {
            logger.info("Reading tag: "+tagName);
            String value =doc.getElementsByTagName(tagName).item(0).getTextContent();
            return value;
        }
    }

    //To Read text file
    public class TextRead{
        private FileReader fr = null;
        private BufferedReader br = null;

        public TextRead() {
            try {
                logger.info("Reading Text file");
                fr = new FileReader(file);
                br = new BufferedReader(fr);
            }
            catch(IOException e) {
                logger.info("Unable to Text file");
                logger.fatal("Exception: "+e.getMessage());
                throw new RuntimeException("Unable to read text file");
            }
        }

        public String getLine() {
            String line = null;
            try {
                line = br.readLine();
            }
            catch(IOException e) {
                logger.fatal("Exception: "+e.getMessage());
                throw new RuntimeException("Unable to read line in text file");
            }
            return line;
        }
    }

    //To Read & Write Excel file
    protected class ExcelReadWrite {
        protected XSSFWorkbook wbk = null;
        protected XSSFSheet sht = null;
        protected XSSFRow row = null;
        protected XSSFCell cell = null;
    }

        //To Read Excel file
        public class ExcelRead extends ExcelReadWrite{


            public ExcelRead() {
                try {
                    logger.info("Reading Excel file");
                    wbk = new XSSFWorkbook(in);
                }
                catch(IOException e) {
                    logger.info("Unable to read Excel file");
                    logger.fatal("Excel_Read_WriteException: "+e.getMessage());
                    throw new RuntimeException("Unable to read Excel file");
                }
            }

            public void getSheetByName(String sheetName){
                wbk.forEach(sht->{
                    if(!sht.getSheetName().equals(sheetName)){
                        logger.fatal("No Such Sheet Exist in Excel File: "+sheetName);
                        throw new RuntimeException("No Such Sheet Exist in Excel File");
                    }
                });
                sht = wbk.getSheet(sheetName);
            }

            public void getSheet(int sheetNum) {
                sht = wbk.getSheetAt(sheetNum);
                logger.info("Accessing sheet in Excel file");
            }

            public int getNumRows() {
                logger.info("Getting number of rows in Excel sheet");
                return sht.getPhysicalNumberOfRows();
            }

            public int getNumCols() {
                logger.info("Getting number of cell in Excel sheet");
                row = sht.getRow(0);
                return row.getPhysicalNumberOfCells();
            }

            public String getData(int r, int c) {
                String data = sht.getRow(r).getCell(c).toString();
                logger.info("Reading data row: "+r+" column:"+ c+" data: "+data);
                return data;

            }
        }

        //To Write Excel file
        public class ExcelWrite extends ExcelReadWrite{
            private FileOutputStream out = null;
            private XSSFCellStyle style =null;
            private XSSFFont font = null;
            private boolean FontStyleFlag = false;

            public ExcelWrite() {
                logger.info("Workbook is created");
                wbk = new XSSFWorkbook();
            }

            public void toCreateSheet(String data) {
                sht = wbk.createSheet(data);
                logger.info("Sheet is created as "+data);
            }

            public void rowCreation(int i) {
                row = sht.createRow(i);
                logger.info("Row created "+i);
            }

            public void cellCreationAndWrite(int i, String data) {
                cell = row.createCell(i);
                cell.setCellValue(data);
                sht.autoSizeColumn(cell.getColumnIndex());
                if (FontStyleFlag) cell.setCellStyle(style);
                cell = null;
                logger.info("Cell created:" +i+" data entered: "+data);
            }

            public void setColTitleBold(boolean flag) {
                style = wbk.createCellStyle();
                font = wbk.createFont();
                if(flag) font.setBold(true);
                else font.setBold(false);
                style.setFont(font);
                FontStyleFlag = flag;
                logger.info("Setting Column Title in Bold");
            }

            public void fileCreation(String filePath) {
                try {
                    out = new FileOutputStream(new File(System.getProperty("user.dir")+"/"+filePath+".xlsx"));
                    wbk.write(out);
                    out.close();
                    logger.info("Excel file created successfully in "+filePath+".xlsx");
                }
                catch(IOException e) {
                    logger.info("Unable to create Excel file");
                    logger.fatal("Exception: "+e.getMessage());
                    throw new RuntimeException("Unable to create Excel file");
                }
            }
        }
}
