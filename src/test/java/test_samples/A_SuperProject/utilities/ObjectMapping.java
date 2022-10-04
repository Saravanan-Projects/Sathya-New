package test_samples.A_SuperProject.utilities;

import test_samples.A_SuperProject.base.UtilityBase;

import java.util.Arrays;

public class ObjectMapping extends UtilityBase{
    private   FileReadWrite.PropsReadWrite FRWCmnObj;
    private   FileReadWrite.PropsReadWrite FRWPageObj;
    private FileReadWrite FRWPrmryObj,FRWSecdryObj;

    public ObjectMapping(String objectFile) throws FrameworkException {
        FRWPrmryObj = intializeUtilsParamCons(FileReadWrite.class, Arrays.stream(new String[] {"src/test/java/test_samples/B_Sub_Projects" +
                "/testartifacts/Common_Identifier.properties"}).toArray(Object[]::new));
        FRWCmnObj = intializeInnerClsUtilsParamCons(FRWPrmryObj,FileReadWrite.PropsReadWrite.class,null);
        FRWSecdryObj =  intializeUtilsParamCons(FileReadWrite.class,  Arrays.stream(("src/test/java/" +
                "test_samples/B_Sub_Projects/testartifacts/"+objectFile).split("\0")).toArray(Object[]::new));
        FRWPageObj = intializeInnerClsUtilsParamCons(FRWSecdryObj,FileReadWrite.PropsReadWrite.class,new FileReadWrite.PropsReadWrite[]{FRWCmnObj});
    }

    public String getLocator(String strElement) {
        return  "ObjectMapping.getLocator";
    }

    public String getStrValues(String strValue) {
        return  "ObjectMapping.getStrValues";
    }
}
