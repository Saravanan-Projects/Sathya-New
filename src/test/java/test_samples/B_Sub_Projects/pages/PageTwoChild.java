package test_samples.B_Sub_Projects.pages;

public class PageTwoChild extends PageTwoParent {
    @Override
    public void compositionObjCreation() {
        super.compositionObjCreation();
    }

    public void methodInPageTwoChild(){
        System.out.println(" Method in PageTwoChild");
    }
}
