package test_samples.B_Sub_Projects.stepdefinitions;

import io.cucumber.java.en.Given;
import test_samples.A_SuperProject.cucumber.TestContext;
import test_samples.B_Sub_Projects.common.Sub_Project_Base;
import test_samples.B_Sub_Projects.pages.PageOneChild;


public class Step_Defn_One extends Sub_Project_Base {
    private PageOneChild pageOneChild = getPageObject(PageOneChild.class);

    public Step_Defn_One(TestContext testContext) {
        super(testContext);
    }

    @Given("^I set my testcase id as '(.*?)' local$")
    public void setTestIDAs(String tcID){
        pageOneChild.methodInPageOneChild();
        pageOneChild.methodInPageOneParent();
        getTempVariableClass().testMethodtmpvar();
    }
}
