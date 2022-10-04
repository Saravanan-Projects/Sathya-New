package test_samples.B_Sub_Projects.pages;

import test_samples.A_SuperProject.base.PageObjectBase;

public abstract class PageOneParent extends PageObjectBase {
    String testParent =null;
    public void compositionObjCreation() {
        testParent = getObjectMapping().getStrValues("");
    }

    public void methodInPageOneParent(){
        System.out.println(" Method in PageOneParent: "+testParent);
//        getTempVariableClass().testMethodtmpvar();

    }
}
