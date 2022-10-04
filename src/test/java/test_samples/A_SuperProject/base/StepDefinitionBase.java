package test_samples.A_SuperProject.base;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import  test_samples.A_SuperProject.cucumber.*;
import test_samples.A_SuperProject.utilities.*;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class StepDefinitionBase extends TestContextDecorator {
    public StepDefinitionBase(TestContext testContext){
        intialize(testContext);
    }

    public final <T extends PageObjectBase> T getPageObject(Class<T> clazz){
        return (T) getPageMap().get(clazz);
    }

    protected void utilsObjectCreation(Map<String,ArrayList<String>> classParameters) throws FrameworkException {
        this.classParameters = classParameters;
        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("test_samples.A_SuperProject.utilities"))));
        Set<Class<? extends UtilityBase>> allClasses = reflections.getSubTypesOf(UtilityBase.class);
        for (Class<?> utilClass : allClasses) {
            Constructor[] constructors = utilClass.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                Class[] parameterTypes = constructor.getParameterTypes();
                if(parameterTypes.length ==0){
                    Object object = intializeClass(utilClass);
                    putUtilsObjDefConsInTstCntxt(object);
                    break;
                }
                else{
                    putUtilsObjParamConsInTstCntxt(utilClass,constructor);
                    break;
                }
            }
        }
    }

    /**
     *  Put with constructor but without parameters Utilities Object in Test Context
     * @param object
     * @throws FrameworkException
     */
    private void putUtilsObjDefConsInTstCntxt(Object object) throws FrameworkException{
        Method[] methods = TestContextDecorator.class.getMethods();
        for(Method method: methods){
            if(method.getName().contains("set"+object.getClass().getSimpleName())){
                try{
                    TestContextDecorator tcObj = intializeClass(TestContextDecorator.class);
                    tcObj.intialize(getTestContext());
                    method.setAccessible(true);
                    method.invoke(tcObj,object);
                    break;
                }
                catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
                    throw new FrameworkException("Unable to create object for class: "+object.getClass().getSimpleName());
                }
            }
        }
    }

    /**
     *  Put with  constructor but with parameters Utilities Object in Test Context
     * @param object
     * @throws FrameworkException
     */
    private void putUtilsObjParamConsInTstCntxt(Class clz, Constructor constructor)  throws FrameworkException {
        try{
            Object object = intializeUtilsParamCons(clz, constructor);
            putUtilsObjDefConsInTstCntxt(object);
        }
        catch(IllegalArgumentException e){
            throw new FrameworkException("Unable to create object for class: "+clz.getSimpleName());
        }
    }
}
