package test_samples.A_SuperProject.cucumber;

import test_samples.A_SuperProject.base.PageObjectBase;
import test_samples.A_SuperProject.utilities.FrameworkException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ObjectBase {
    private TestContext testContext;

    protected Map<String,ArrayList<String>> classParameters = null;

    protected TestContext getTestContext() {
        return testContext;
    }

    public void intialize(TestContext testContext){
        this.testContext = testContext;
    }


    protected   <T extends PageObjectBase>  T intializeWithContext(Class<T> tstCntxtClass) throws FrameworkException {
        try{
            T instance = tstCntxtClass.newInstance();
            instance.intialize(getTestContext());
            instance.compositionObjCreation();
            return instance;
        }catch (IllegalAccessException | InstantiationException e){
            throw new FrameworkException("Unable to create object for class: "+tstCntxtClass.getSimpleName());
        }
    }

    /**
     * Create object for with constructor but without parameters classes
     * @param clazz
     * @param <T>
     * @return
     * @throws FrameworkException
     */
    protected  <T> T intializeClass(Class<T> clazz) throws FrameworkException {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e){
            throw new FrameworkException("Unable to create object for class: "+clazz.getSimpleName());
        }
    }

    public <T> T intializeUtils(Class<T> clazz) throws FrameworkException {
        try {
            TestContextDecorator tcObj = intializeClass(TestContextDecorator.class);
            tcObj.intialize(getTestContext());
            return clazz.getConstructor(TestContextDecorator.class).newInstance(tcObj);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e){
            throw new FrameworkException("Unable to create object for class: "+clazz.getSimpleName());
        }
    }

    /**
     * Create object for with constructor but with parameters classes
     * @param clazz
     * @param <T>
     * @return
     * @throws FrameworkException
     */
    protected Object intializeUtilsParamCons(Class<?> clz, Constructor constructor) throws FrameworkException{
        try {
            ArrayList<String> paramVal = classParameters.get(clz.getSimpleName());
            String[] paramValues = paramVal.toArray(new String[paramVal.size()]);
            Object[] parameterValues = Arrays.stream(paramValues).toArray(Object[]::new);
           return  intializeUtilsParamCons(clz,constructor,parameterValues);
        }catch(IllegalArgumentException e){
            throw new FrameworkException("Unable to create object for class: "+clz.getSimpleName());
        }
    }

    /**
     * Create object for with constructor but with parameters classes
     * @param clazz
     * @param <T>
     * @return
     * @throws FrameworkException
     */
    protected Object intializeUtilsParamCons(Class<?> clz,Constructor constructor, Object[] parameterValues) throws FrameworkException{
        try {
            constructor.setAccessible(true);
          return constructor.newInstance(parameterValues);
        }catch(InstantiationException |IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
            throw new FrameworkException("Unable to create object for class: "+clz.getSimpleName());
        }
    }

    protected <T> T intializeUtilsParamCons(Class<T> enclosingClass, Object[] paramOutrClzVal) throws FrameworkException{
        Object outerObj = null;
        try {
            Constructor[] outerCons = enclosingClass.getDeclaredConstructors();
            for (Constructor constructor : outerCons) {
                Class[] parameterTypes = constructor.getParameterTypes();
                if(paramOutrClzVal.length == parameterTypes.length){
                    outerObj = intializeUtilsParamCons(enclosingClass,constructor,paramOutrClzVal);
                    break;
                }
            }

        }catch( IllegalArgumentException e){
            throw new FrameworkException("Unable to create object for class: "+enclosingClass.getSimpleName());
        }
        return (T)outerObj;
    }


}
