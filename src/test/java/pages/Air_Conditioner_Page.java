package pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import runners.Hooks;

public class Air_Conditioner_Page extends Sathya_Base_Page {
    private static By ACCapacityFilter = Hooks.ObjMap.getLocator("AC_Capacity_Filter");
    private static  String ACCapacityFilterChkBx = Hooks.ObjMap.getStrValues("AC_Capacity_Chk_Box");
    private static Logger logger= Logger.getLogger(Air_Conditioner_Page.class);

    @Override
    public boolean moduleFilters(String filters, List<String> values){
        logger.info("Applying AC Filter: "+ filters+" with values:"+values);
        boolean flag = false;
        for (String value: values) {
            value = value.trim();
            switch (filters) {
                case "AC_CAPACITY":
                    flag = true;
                    checkCollapsedAndClick(ACCapacityFilter, ACCapacityFilterChkBx.replace("%value%", value));
                    break;
            }
        }
        return flag;
    }
}
