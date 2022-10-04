package test_samples.B_Sub_Projects.pages;

public class PageOneChild extends PageOneParent {
    String test = null;
    @Override
    public void compositionObjCreation() {
        super.compositionObjCreation();
        test = getObjectMapping().getLocator("");
    }

    public void methodInPageOneChild(){
        System.out.println(" Method in PageOneChild:"+ test);
        getTempVariableClass().testMethodtmpvar();
    }
}
