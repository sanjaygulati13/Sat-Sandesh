
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    public static void center(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        int x1 = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        frame.setLocation(x1, y);
    }
    
    public static Object[] fillSeriesInformation() 
    {
        connect fillSerieConnection = new connect();
        Object[] seriesNameArray = null;
        try
        {
            String query = "select distinct series_name from receipt_book_inventory";
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
}
