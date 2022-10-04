package stepdefinitions;

import common.Base_Classes;
import io.cucumber.java.en.And;
import org.apache.log4j.Logger;


import java.util.List;

public class Air_Conditioner_Common_Steps extends Base_Classes {

    private static Logger logger  = Logger.getLogger(Air_Conditioner_Common_Steps.class);

    @And("^I get total number of products listed in '(\\d+)' page(s)?$")
    public void totalProducts(int pages, String negate) {
        Base_Classes.totalProducts(ACModPgeObj,pages);
        reporting("And","I get total number of products listed in "+pages+ "pages");
        logger.info("I get total number of products listed in "+pages+ "pages");
    }

    @And("^I navigate into each products and get the following details")
    public void eachProduct(List<String> columns) throws InterruptedException {
        Base_Classes.eachProduct(ACModPgeObj,ACPrdPgeObj,columns);
        reporting("And","I navigate into each products and get the following details");
        logger.info("I navigate into each products and get the following details");
    }
}
