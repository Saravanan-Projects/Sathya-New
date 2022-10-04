package test_samples.A_SuperProject.base;

import test_samples.A_SuperProject.cucumber.TestContextDecorator;
import test_samples.A_SuperProject.utilities.FrameworkException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilityBase extends TestContextDecorator {
    protected <T> T intializeInnerClsUtilsParamCons(Object enclosingObj, Class<T> innerClass, T[] paramInnerClzVal) throws FrameworkException {
        Object innerObj = null;
        try{
            Constructor[] constructors = innerClass.getConstructors();
            for (Constructor constructor : constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                if(paramInnerClzVal!=null) {
                    if (paramInnerClzVal.length+1 == parameterTypes.length){
                        List<Object> arguments = new ArrayList<Object>(){{
                            this.add(enclosingObj);
                            this.addAll(Arrays.asList(paramInnerClzVal));
                        }};
                        innerObj = intializeUtilsParamCons(enclosingObj.getClass(),constructor,arguments.toArray());
                        break;
                    }
                }else if(paramInnerClzVal==null){
                    if(parameterTypes.length-1==0){
                        innerObj =  constructor.newInstance(enclosingObj);
                        break;
                    }
                }
            }
        }catch( IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException e){
            e.printStackTrace();
            throw new FrameworkException("Unable to create object for class: "+innerObj.getClass().getSimpleName());
        }
        return (T)innerObj;
    }
}
