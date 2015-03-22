
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjay
 */
public class SamsUtilities {
    
    public static int getCurrentDate()
    {
        GregorianCalendar cal=new GregorianCalendar();
        
        int currDate = cal.get(Calendar.DATE);
        return currDate;
        
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
}
