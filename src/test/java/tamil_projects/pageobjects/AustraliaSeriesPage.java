package tamil_projects.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tamil_projects.pageobjects.CricbuzzHomePage;
import tamil_projects.utils.CricbuzzWriteexcel;

import java.util.*;


public class AustraliaSeriesPage extends CricbuzzHomePage {

    @FindBy(xpath="//div[@class='cb-sch-lst-itm']//span[contains(text(),'Australia')]/ancestor::div[@class='cb-col-100 cb-col ng-scope']/div[@class='cb-col-16 cb-col text-bold cb-mnth ']")
    private List<WebElement> month;

    @FindBy(xpath="//div[@class='cb-sch-lst-itm']//span[contains(text(),'Australia')]/../..")
    private List<WebElement> matchdetail;



    public AustraliaSeriesPage()
    {

        PageFactory.initElements(driver, this);
    }

    public void writeSeries() throws Exception {
        Map<String, ArrayList<String>> matchWithMonth = new HashMap<>();
        ArrayList<String> monthList = new ArrayList<>();
        LinkedList<WebElement> matchdetailLL = new LinkedList<WebElement>();
        matchdetailLL.addAll(matchdetail);
         System.out.println("am inside method");
        for(int i = 0;i<month.size();i++){
            String monthName = month.get(i).getText();
            monthList.add(monthName);
            ArrayList<String> tourDetails = new ArrayList<>();
            for(int j = 0;j<matchdetailLL.size();j++){
                String teamDetails = matchdetailLL.get(j).getText();
              if(teamDetails.contains(monthName.substring(0,3))){
                  tourDetails.add(teamDetails);
                  matchdetailLL.remove(j);
                  j=-1;
              }
            }
            matchWithMonth.put(monthName,tourDetails);
        }
        System.out.println(matchWithMonth);
        CricbuzzWriteexcel.writeAustraliaSeries(matchWithMonth,monthList);
    }

    public void writeSeries(char a) throws Exception {
        Map<String, ArrayList<String>> matchWithMonth = new HashMap<>();
        ArrayList<String> monthList = new ArrayList<>();
        ListIterator<WebElement> lstIter = matchdetail.listIterator();
        System.out.println("am inside method");
        for(int i = 0;i<month.size();i++){
            String monthName = month.get(i).getText();
            monthList.add(monthName);
            ArrayList<String> tourDetails = new ArrayList<>();
            boolean lstIterNxtFlag = lstIter.hasNext();
            boolean lstIterPerFlag = lstIter.hasPrevious();
            while(lstIterNxtFlag||lstIterPerFlag){
                String teamDetails =null;
                if (lstIterNxtFlag){
                    teamDetails= lstIter.next().getText();
                    lstIterNxtFlag = lstIter.hasNext();
                }
                else if(lstIterPerFlag){
                    teamDetails= lstIter.previous().getText();
                    lstIterPerFlag = lstIter.hasPrevious();
                }
                if(teamDetails.contains(monthName.substring(0,3))){
                    tourDetails.add(teamDetails);
                    lstIter.remove();
                }
            }
            matchWithMonth.put(monthName,tourDetails);
        }
        System.out.println(matchWithMonth);
        CricbuzzWriteexcel.writeAustraliaSeries(matchWithMonth,monthList);
    }

    public void writeSeries(int a) throws Exception {
        Map<String, ArrayList<String>> matchWithMonth = new HashMap<>();
        ArrayList<String> monthList = new ArrayList<>();
        ListIterator<WebElement> lstIter = matchdetail.listIterator();
        System.out.println("am inside method");
        for(int i = 0;i<month.size();i++){
            String monthName = month.get(i).getText();
            monthList.add(monthName);
            ArrayList<String> tourDetails = new ArrayList<>();
            while(lstIter.hasNext()){
                String teamDetails= lstIter.next().getText();
                if(teamDetails.contains(monthName.substring(0,3))){
                    tourDetails.add(teamDetails);
                    lstIter.remove();
                }
            }
            while(lstIter.hasPrevious()){
                String teamDetails= lstIter.previous().getText();
                if(teamDetails.contains(monthName.substring(0,3))){
                    tourDetails.add(teamDetails);
                    lstIter.remove();
                }
            }
            matchWithMonth.put(monthName,tourDetails);
        }
        System.out.println(matchWithMonth);
        CricbuzzWriteexcel.writeAustraliaSeries(matchWithMonth,monthList);
    }


}