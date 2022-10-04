package utilities;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class GridSection {
    private File_Read_Write.Excel_Read EXLReadObj = null;
    private Map<String, Integer> columns = null;
    private static Logger logger= Logger.getLogger(GridSection.class);

    public GridSection(){
        columns = new HashMap<String, Integer>();
    }

    private void getHeaderList(){
        int colCount = EXLReadObj.getNumCols();
        for(int i=0; i<colCount; i++)
            columns.put(EXLReadObj.getData(0,i),i);
    }

    void getExcelDetails(File_Read_Write.Excel_Read EXLReadObj){
        this.EXLReadObj = EXLReadObj;
        getHeaderList();
    }

    int getHeaderIndex(String columnName){
        logger.info("Getting Data for column: "+columnName);
        boolean flag = false;
        String  key = null;
       for(String keyDup: columns.keySet()){
           if(keyDup.equals(columnName)){
               flag = true;
               break;
           }
           else{
               flag = false;
               key = keyDup;
           }
        }
       if(!flag) {
           logger.fatal("No such column is Exist: "+ key);
           throw new RuntimeException();
       }
       return columns.get(columnName);
    }
}
