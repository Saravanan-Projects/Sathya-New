package utilities;

import java.util.*;

public class Temp_Variable_Class {
    private  String TestcaseID;
    private Map<Integer,ArrayList<String>> allPrdtsDetails = new Hashtable<Integer,ArrayList<String>>();
    private Map<Integer,Integer> prdtsPerPge = new HashMap<Integer, Integer>();
    private ArrayList<String> eachPrdtDetails = new ArrayList<String>();
    private int totalProducts;
    private List<ArrayList<String>> prdtsDetails[];
    private boolean flag;
    private int pages;
    private String prntWinID;
    private String childWinID;
    private int columnSize;
    private String fileLines = "";

    //Mutator Methods
    public void setAllPrdtsDetails(int key, ArrayList value) { this.allPrdtsDetails.put(key, value); }

    public void setEachPrdtDetails(String value){ this.eachPrdtDetails.add(value); }

    private void setPrdtsDetailsSize(){ prdtsDetails = new ArrayList[this.totalProducts+1];  }

    public void setPrdtDetails(int index, ArrayList values) {  this.prdtsDetails[index]= values; }

    public void set_Flag(boolean value) {
        this.flag = value;
    }

    public void setTestcaseID(String TestcaseID){ this.TestcaseID = TestcaseID; }

    public void setTotalProducts(int totalProducts){
        this.totalProducts = totalProducts;
        setPrdtsDetailsSize();
    }

    public void setPages(int pages){ this.pages = pages; }

    public void setPrdtsPerPge(int key, int value){ this.prdtsPerPge.put(key,value); }

    public void setPrntWinID(String prntWinID){ this.prntWinID = prntWinID; }

    public void setChildWinID(String childWinID){ this.childWinID = childWinID; }

    public void setColumnSize(int columnSize){ this.columnSize = columnSize; }

    public void setFileLines(String line){ this.fileLines += line; }

    //Accessor Methods
    public ArrayList getAllPrdtsDetails(int key){ return this.allPrdtsDetails.get(key); }

    public ArrayList getEachPrdtDetails(){ return this.eachPrdtDetails; }

    public ArrayList getPrdtsDetails(int index) { return (ArrayList) this.prdtsDetails[index]; }

    public boolean get_Flag() { return this.flag; }

    public String getTestcaseID(){ return this.TestcaseID; }

    public int gettotalProducts(){ return this.totalProducts; }

    public int getPages(){ return  this.pages; }

    public int getPrdtsPerPge(int key){ return this.prdtsPerPge.get(key); }

    public String getPrntWinID(){ return  this.prntWinID; }

    public String getChildWinID(){ return  this.childWinID; }

    public int getColumnSize(){ return this.columnSize; }

    public void clearEachPrdtDetails(){ eachPrdtDetails = new ArrayList<String>(); }

    public String getFileLines(){ return this.fileLines; }
}
