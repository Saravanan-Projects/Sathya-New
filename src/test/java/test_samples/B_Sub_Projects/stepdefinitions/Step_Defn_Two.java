package test_samples.B_Sub_Projects.stepdefinitions;

import io.cucumber.java.en.And;
import test_samples.B_Sub_Projects.common.Sub_Project_Base;
import test_samples.A_SuperProject.cucumber.TestContext;
import test_samples.B_Sub_Projects.pages.PageTwoChild;


public class Step_Defn_Two extends Sub_Project_Base {
    private PageTwoChild pageTwoChild = getPageObject(PageTwoChild.class);
    public Step_Defn_Two(TestContext testContext) {
        super(testContext);
    }

    @And("^I get total number of products listed in '(\\d+)' page(s)? local$")
    public void totalProducts(int pages, String negate) {
        pageTwoChild.methodInPageTwoChild();
        pageTwoChild.methodInPageTwoParent();
    }
}
