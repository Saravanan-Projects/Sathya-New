package test_samples.B_Sub_Projects.common;


import common.Module;
import common.Product;
import test_samples.A_SuperProject.base.StepDefinitionBase;
import test_samples.A_SuperProject.cucumber.TestContext;

import java.util.List;
import java.util.Map;

public class Sub_Project_Base extends StepDefinitionBase {


    public Sub_Project_Base(TestContext testContext) {
        super(testContext);
    }

//    public Base_Classes(boolean flag){
//        super();
//    }

    //Nested Class
    protected class FileDetails {
        public String file_name = null;
        public String file_type = null;
        public String sheet_name = null;

        public FileDetails(String fileName, String fileType, String sheetName){
            this.file_name = fileName;
            this.file_type = fileType;
            this.sheet_name = sheetName;
        }
    }

    private void readExcelFile(Map<String,String> file){

    }

    private void readTextFile(Map<String,String> file){

    }

    protected void totalProducts(Module modObj,int pages){

    }

    protected void eachProduct(Module modObj,Product pageObj, List<String> columns) {

    }

    protected void writeIntoFile( List<FileDetails> fileDetails){

    }

    protected void applyFilters( Map<String,String> filters){

    }

    protected void readFromFile( Map<String,String> file){

    }

    protected final void reporting(String keyword, String step){

    }
}
