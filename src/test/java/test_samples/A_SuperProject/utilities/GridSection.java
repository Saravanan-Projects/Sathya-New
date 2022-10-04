package test_samples.A_SuperProject.utilities;

import test_samples.A_SuperProject.base.UtilityBase;
import utilities.File_Read_Write;

import java.util.Map;

public class GridSection extends UtilityBase {
    private File_Read_Write.Excel_Read EXLReadObj = null;
    private Map<String, Integer> columns = null;

    public GridSection(){

    }

    private void getHeaderList(){

    }

    void getExcelDetails(File_Read_Write.Excel_Read EXLReadObj){

    }

    int getHeaderIndex(String columnName){
      return 0;
    }
}
