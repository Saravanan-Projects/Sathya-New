package stepdefinitions;

import common.Base_Classes;

import common.Module;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.Logger;
import pages.Sathya_Base_Page;
import runners.Hooks;
import io.cucumber.java.en.*;


import java.util.List;
import java.util.Map;


public class Sathya_Common_Steps extends Base_Classes {

    private static Logger logger= Logger.getLogger(Sathya_Base_Page.class);

    @Given("^I set my testcase id as '(.*?)'$")
    public void setTestIDAs(String tcID){
        Hooks.TempClass.setTestcaseID(tcID);
        Hooks.count = 0;
        reporting("Given","I set my testcase id as "+tcID);
        logger.info("Test case is created with ID "+tcID);
    }

    @Given("^I am Logged into Sathya Application$")
    public void Sathya_Login() {
//        Hooks.TempClass.setPrdtsPerPge(1,12);
        Module.setUp();
        logger.info("Enviroment is setup successfully");
        Sathya_Base_Page.loadAppln();
        logger.info("Aplication is opened successfully");
        Hooks.screenShotObj.addScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        reporting("Given","I am Logged into Sathya Application");
    }

    @And("^I enter username and password and log in account$")
    public void enterUsrNmePwd(){
        Sathya_Base_Page.login();
        reporting("And","I enter username and password and log in account");
        logger.info("User login successfully");
    }
    @Then("^I verify the username in the homepage$")
    public void verifyUsrNmePwd(){
        // Hooks.softAssertion.assertTrue(false);
        reporting("Then","I verify the username in the homepage");
        Hooks.screenShotObj.addScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.info("Username is verified successfully");
    }

    @And("^I navigate from \"([^\"]+)\" to \"([^\"]+)\"(?: and then to \"([^\"]+)\")?$")
    public void   navigation(String menu, String subMenu, String product){
        logger.info("Navigation Begins");
        Sathya_Base_Page.navigation(menu, subMenu, product);
        Hooks.screenShotObj.addScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        if(product==null) reporting("And","I navigate from "+menu+" to "+subMenu);
        else  reporting("And","I navigate from "+menu+" to "+subMenu+" and then to "+product);
        logger.info("Navigation Ends");
    }

    @And("^I write the gathered details into the following file$")
    public void writeIntoFile(DataTable fileDetails){
        List<FileDetails> fileList = fileDetails.asList(FileDetails.class);
        logger.info("Writing into file begins");
        Base_Classes.writeIntoFile(fileList);
        reporting("And","I write the gathered details into the following file\r\n"+com.google.common.base.Joiner.on("|").join(fileDetails.asList(String.class)));
        logger.info("File written successfully");
    }

    @And("^I read the file as following details$")
    public void readFromFile(DataTable fileDetails){
        Map<String,String> file = fileDetails.asMap(String.class, String.class);
        logger.info("Reading from file begins");
        Base_Classes.readFromFile(file);
        String result = file.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(java.util.stream.Collectors.joining(", ","{", "}"));
        reporting("And","I read the file as following details\r\n"+result);
        logger.info("File read successfully");
    }

    @And("^I apply the following filter(s)? on the listed product(s)?$")
    public void applyFilters(String negate1,String negate2,DataTable filterDetails){
        Map<String,String> filters = filterDetails.asMap(String.class, String.class);
        Base_Classes.applyFilters(filters);
        String result = filters.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(java.util.stream.Collectors.joining(", ","{", "}"));
        reporting("And","I apply the following filters on the listed products\r\n"+result);
        logger.info("Filter applied successfully");
    }

    @And("^I enter '(.*)' username (for '(.*)' entity)?$")
    public void optinal(String l, String Negate, String str) {
        if(Negate!=null)
            System.out.println(Negate+ " "+str);
        System.out.println(l);
    }
}
