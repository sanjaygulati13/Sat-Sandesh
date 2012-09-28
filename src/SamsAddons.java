
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SG
 */
public class SamsAddons {
    
    static GregorianCalendar Calendar;
    private static String date, month, year, CurrentDate;
    
    public static String getCurrentDate()
    {
        Calendar = new GregorianCalendar();
        date = "" + Calendar.get(Calendar.DATE);
        month = "" + (Calendar.get(Calendar.MONTH) + 1);
        year = "" + Calendar.get(Calendar.YEAR);

        if(date.length()==1)
            date="0"+date;
        if(month.length()==1)
            month="0"+month;

        CurrentDate=date+"/"+month+"/"+year;
        
        return CurrentDate;
    }
    
    public static String getCurrentSqlDate()
    {
        Calendar = new GregorianCalendar();
        date = "" + Calendar.get(Calendar.DATE);
        month = "" + (Calendar.get(Calendar.MONTH) + 1);
        year = "" + Calendar.get(Calendar.YEAR);

        if(date.length()==1)
            date="0"+date;
        if(month.length()==1)
            month="0"+month;

        CurrentDate=year+"-"+month+"-"+date;
        
        return CurrentDate;
    }
    
    
}
