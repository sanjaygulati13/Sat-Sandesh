
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
    
    //static GregorianCalendar Calendar;
    //private static String date, month, year, CurrentDate;
    
    public static String getCurrentDate()
    {
        GregorianCalendar Calendar;
        String date, month, year, CurrentDate;
        Calendar = new GregorianCalendar();
        date = "" + Calendar.get(GregorianCalendar.DATE);
        month = "" + (Calendar.get(GregorianCalendar.MONTH) + 1);
        year = "" + Calendar.get(GregorianCalendar.YEAR);

        if(date.length()==1)
            date="0"+date;
        if(month.length()==1)
            month="0"+month;

        CurrentDate=date+"/"+month+"/"+year;
        
        return CurrentDate;
    }
    
    public static String getCurrentSqlDate()
    {
        GregorianCalendar Calendar;
        String date, month, year, CurrentDate;
        Calendar = new GregorianCalendar();
        date = "" + Calendar.get(GregorianCalendar.DATE);
        month = "" + (Calendar.get(GregorianCalendar.MONTH) + 1);
        year = "" + Calendar.get(GregorianCalendar.YEAR);

        if(date.length()==1)
            date="0"+date;
        if(month.length()==1)
            month="0"+month;

        CurrentDate=year+"-"+month+"-"+date;
        
        return CurrentDate;
    }
    
    public static String getSqlDateForDate(int date_i, int month_i, int year_i)
    {
        //GregorianCalendar Calendar;
        String date, month, year, CurrentDate;
        //Calendar = new GregorianCalendar();
        date = "" + date_i;
        month = "" +month_i;
        year = "" + year_i;

        if(date.length()==1)
            date="0"+date;
        if(month.length()==1)
            month="0"+month;

        CurrentDate=year+"-"+month+"-"+date;
        
        return CurrentDate;
    }
    
    
}
