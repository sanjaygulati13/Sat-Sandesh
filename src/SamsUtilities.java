
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjay
 */
public class SamsUtilities {
    
    static String userName = "";
    static final String okButtonName = "OK";
    static final String cancelButtonName = "Cancel";
    static final String printButtonName = "Print";
    static final String clearButtonName = "Clear";
    static Object[] stateNameArray = null;
    static Object[] stateCodeArray = null;
    static Object[] subCodeArray = null;
    
    static HashMap<String, Pair<String, String>> stateDetailsHash;
    static HashMap<String, String> stateNameToStateCodeHash;
    static HashMap<String, String> stateCodeToStateNameHash;
    //static HashMap<String, String> stateCodeToStateNameMap;
    
    public static String getOkButtonName()
    {
        return okButtonName;
    }
    
    public static String getCancelButtonName()
    {
        return cancelButtonName;
    }
    
    public static String getPrintButtonName()
    {
        return printButtonName;
    }
    
    public static String getClearButtonName()
    {
        return clearButtonName;
    }
    
    public static int getCurrentDate()
    {
        GregorianCalendar cal=new GregorianCalendar();
        
        int currDate = cal.get(Calendar.DATE);
        return currDate;
        
    }
    
    public static void setUserName(String user){
        userName = user;
    }
    
    public static String getUserName(){
        return userName;
    }
    
    public static int getCurrentMonth()
    {
        GregorianCalendar cal=new GregorianCalendar();
        
        int currMonth = (cal.get(Calendar.MONTH)+1);
        return currMonth;
    }
    
    public static int getCurrentYear()
    {
        GregorianCalendar cal=new GregorianCalendar();
        
        int currYear = cal.get(Calendar.YEAR);
        return currYear;
        
    }
    public static void center(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x1 = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x1, y);
    }
    
    public static Object[] getSubCodes()
    {
        if(subCodeArray == null)
            fillStateDetailsInMap();
        
        return subCodeArray;
    }
    
    private static void fillStateDetailsInMap()
    {
        connect fillStateDetailsConnection = new connect();
        try
        {
            String query = "select sub_code, state_code, state_name from state_details order by sub_code ASC";
            String countQuery = "select count(sub_code) from state_details";
            String manualCountQuery = "select count(sub_code) from state_details where sub_code='Manual'";
            
            fillStateDetailsConnection.rs = fillStateDetailsConnection.st.executeQuery(countQuery);
            fillStateDetailsConnection.rs.next();
            int ArrayCount = fillStateDetailsConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            
            fillStateDetailsConnection.rs = fillStateDetailsConnection.st.executeQuery(manualCountQuery);
            fillStateDetailsConnection.rs.next();
            int manualEntryCount = fillStateDetailsConnection.rs.getInt(1);
            
            stateDetailsHash = new HashMap<String, Pair<String, String>>();
            stateNameToStateCodeHash = new HashMap<String, String>();
            stateCodeToStateNameHash = new HashMap<String, String>();
            subCodeArray = new Object[ArrayCount-manualEntryCount+1];
            subCodeArray[0] = "";
            
            fillStateDetailsConnection.rs = fillStateDetailsConnection.st.executeQuery(query);
            int i = 1;
            //System.out.println(fillStateDetailsConnection.rs.);
            while (fillStateDetailsConnection.rs.next()) {
                String sub_code = fillStateDetailsConnection.rs.getString(1);
                String state_code = fillStateDetailsConnection.rs.getString(2);
                String state_name = fillStateDetailsConnection.rs.getString(3);
                
                if(state_code.isEmpty() == false){
                    stateNameToStateCodeHash.put(state_name, state_code);
                    stateCodeToStateNameHash.put(state_code, state_name);
                }
                if(sub_code.equals("Manual")) continue;
                //System.out.println(sub_code + " " + state_code + " " + state_name);
                subCodeArray[i] = sub_code;
                stateDetailsHash.put(sub_code,new Pair<String, String>(state_code, state_name));
                //stateDetailsHash.put(sub_code,state_name);
                ++i;
            }
            
            fillStateDetailsConnection.closeAll();
        } catch (Exception exc) {
            exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillStateDetailsConnection.closeAll();
        }
        
    }
    
    public static String getStateCodeForStateName(String stateName)
    {
        if(stateName.isEmpty()) return "";
        if(stateNameToStateCodeHash.isEmpty())
            fillStateDetailsInMap();
        
        return stateNameToStateCodeHash.get(stateName);
    }
    
    public static String getStateCodeForSubCode(String subCode)
    {
        if(subCode.isEmpty()) return "";
        if(stateDetailsHash.isEmpty())
            fillStateDetailsInMap();
        
        Pair<String, String> retPair = stateDetailsHash.get(subCode);
        return retPair.getFirst();
    }
    
    public static String getStateNameForSubCode(String subCode)
    {
        if(subCode.isEmpty()) return "";
        if(stateDetailsHash.isEmpty())
            fillStateDetailsInMap();
        
        Pair<String, String> retPair = stateDetailsHash.get(subCode);
        if(retPair == null) return "";
        else
            return retPair.getFirst();
    }
    
    public static String getStateNameForStateCode(String subCode)
    {
        if(subCode.isEmpty()) return "";
        if(stateCodeToStateNameHash.isEmpty())
            fillStateDetailsInMap();
        
        return stateCodeToStateNameHash.get(subCode);
    }
    
    public static Object[] fillSeriesInformation() 
    {
        connect fillSerieConnection = new connect();
        Object[] seriesNameArray = null;
        try
        {
            String query = "select distinct series_name from receipt_book_inventory order by series_name DESC";
            String countQuery = "select count(distinct series_name) from receipt_book_inventory";
            
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(countQuery);
            fillSerieConnection.rs.next();
            int ArrayCount = fillSerieConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            seriesNameArray = new Object[ArrayCount + 1];
            seriesNameArray[0] = "";
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillSerieConnection.rs.next()) {
                seriesNameArray[i] = fillSerieConnection.rs.getString(1);
                i++;
            }
            
            fillSerieConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillSerieConnection.closeAll();
        }
        return seriesNameArray;
    
    }
    
    public static Object[] fillStateNameList() 
    {
        connect fillStateNameConnection = new connect();
        
        if(stateNameArray != null ){
            return stateNameArray;
        }
        try
        {
            String query = "select state_name from state_details  where sub_code='Manual'  order by state_name ASC";
            String countQuery = "select count(state_name) from state_details  where sub_code='Manual' ";
            
            fillStateNameConnection.rs = fillStateNameConnection.st.executeQuery(countQuery);
            fillStateNameConnection.rs.next();
            int ArrayCount = fillStateNameConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            stateNameArray = new Object[ArrayCount + 1];
            stateNameArray[0] = "";
            fillStateNameConnection.rs = fillStateNameConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillStateNameConnection.rs.next()) {
                String stateName = fillStateNameConnection.rs.getString(1);
                //System.out.println(stateName);
                stateNameArray[i] = stateName;
                i++;
            }
            
            
            fillStateNameConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillStateNameConnection.closeAll();
        }
        return stateNameArray;
    
    }
    
    public static Object[] fillStateCodeList() 
    {
        connect fillStateCodeConnection = new connect();
        
        if(stateCodeArray != null ){
            return stateCodeArray;
        }
        try
        {
            String query = "select state_code from state_details where sub_code='Manual' order by state_code ASC";
            String countQuery = "select count(state_code) from state_details where sub_code='Manual'";
            //String emptyCountQuery = "select count(state_code) from state_details where state_code=''";
            
            fillStateCodeConnection.rs = fillStateCodeConnection.st.executeQuery(countQuery);
            fillStateCodeConnection.rs.next();
            int ArrayCount = fillStateCodeConnection.rs.getInt(1);
            
            
            stateCodeArray = new Object[ArrayCount + 1];
            stateCodeArray[0] = "";
            fillStateCodeConnection.rs = fillStateCodeConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillStateCodeConnection.rs.next()) {
                String code = fillStateCodeConnection.rs.getString(1);
                if(code.isEmpty())continue;
                stateCodeArray[i] = code;
                i++;
            }
            
            fillStateCodeConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillStateCodeConnection.closeAll();
        }
        return stateCodeArray;
    
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
    	super();
    	this.first = first;
    	this.second = second;
    }

    public int hashCode() {
    	int hashFirst = first != null ? first.hashCode() : 0;
    	int hashSecond = second != null ? second.hashCode() : 0;

    	return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
    	if (other instanceof Pair) {
    		Pair otherPair = (Pair) other;
    		return 
    		((  this.first == otherPair.first ||
    			( this.first != null && otherPair.first != null &&
    			  this.first.equals(otherPair.first))) &&
    		 (	this.second == otherPair.second ||
    			( this.second != null && otherPair.second != null &&
    			  this.second.equals(otherPair.second))) );
    	}

    	return false;
    }

    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }

    public A getFirst() {
    	return first;
    }

    public void setFirst(A first) {
    	this.first = first;
    }

    public B getSecond() {
    	return second;
    }

    public void setSecond(B second) {
    	this.second = second;
    }
}
