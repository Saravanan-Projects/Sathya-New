package test_samples.B_Sub_Projects.runner;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import org.reflections.Reflections;
import test_samples.A_SuperProject.base.PageObjectBase;
import test_samples.A_SuperProject.base.StepDefinitionBase;
import test_samples.A_SuperProject.cucumber.TestContext;
import test_samples.A_SuperProject.utilities.FrameworkException;

import java.lang.reflect.Modifier;
import java.util.*;

public class Hooks extends StepDefinitionBase {
    String ModuleName = null;
    public Hooks(TestContext testContext) {
        super(testContext);
    }

    @Before
    public void setUP(Scenario scenario) throws FrameworkException {
        String TesterName = null;
        Collection<String> sourceTagNames = scenario.getSourceTagNames();
        for(String name: sourceTagNames) {
            String[] tags = name.split("@");
            for(String desired_tags: tags) {
                if(desired_tags.contains("Module_Name__")) {
                    ModuleName = desired_tags.split("__")[1];
                } else if(desired_tags.contains("Tester_Name__")){
                    TesterName =  desired_tags.split("__")[1];
                }
            }
        }
        Map<String,ArrayList<String>> classParameters = new HashMap<String,ArrayList<String>>(){{
           this.put("ObjectMapping",new ArrayList<String>(){{
               this.add("Product_Identifier.properties");
           }});
           this.put("Screenshot",new ArrayList<String>(){{
               this.add(scenario.getName());
               this.add(ModuleName);
           }});
        }};
        utilsObjectCreation(classParameters);
        Map<Class<? extends PageObjectBase>,Object> pageMap =  new HashMap<>();
        Reflections reflections = new Reflections("test_samples.B_Sub_Projects.pages");
        List<Class<? extends PageObjectBase>> pageList = new ArrayList<>(reflections.getSubTypesOf(PageObjectBase.class));
        for (Class<? extends PageObjectBase> page : pageList){
            if(!Modifier.toString(page.getModifiers()).contains("abstract")){
                pageMap.put(page,intializeWithContext(page));
            }
        }
        setPageMap(pageMap);
    }
}
