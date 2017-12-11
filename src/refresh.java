
import java.util.*;

public class refresh implements Runnable{
    
    public static void main(String args[]) {
        new refresh();
    }
    int i = 0;
    int x = 0;
    int[] asn;
    int mi, md, mf;
    int yi, yd, yf;
    
    public refresh() {
        //update();
    }
    
    public void update()
    {
        try {
            GregorianCalendar g = new GregorianCalendar();
            int m = (g.get(Calendar.MONTH) + 1);
            int y = g.get(Calendar.YEAR);
            System.out.println(m+" "+y);
            
            
            //----------------------------1 year----------------------------------------//
            
            //receipt book details update for ending period
            /*connect tempConnection = new connect();
            tempConnection.rs = tempConnection.st.executeQuery("select asn from receipt_book_details");
            while(tempConnection.rs.next())
            {
                int asn = tempConnection.rs.getInt(1);
                //System.out.println(asn);
                connect tempConnectionNew = new connect();
                tempConnectionNew.rs = tempConnectionNew.st.executeQuery("select ending_period from subscribers_primary_details where asn = "+asn);
                if(tempConnectionNew.rs.next()){
                    Date endingPeriod = tempConnectionNew.rs.getDate(1);
                    //System.out.println(endingPeriod);
                    tempConnectionNew.a = tempConnectionNew.st.executeUpdate("update receipt_book_details set ending_period = '"+endingPeriod+"' where asn = "+asn);
                    if(tempConnectionNew.a == 0)
                        System.out.println("Failed");
                }
                tempConnectionNew.closeAll();
                
            }
            //tempConnection.a = tempConnection.st.executeUpdate("update subscribers_primary_details set membership_status='Active' where membership_status not in ('STOPPED')");
            tempConnection.closeAll();*/
            
            
            //receipt book details update for dno
            /*connect tempConnection = new connect();
            tempConnection.rs = tempConnection.st.executeQuery("select asn from receipt_book_details");
            connect tempConnectionNew = new connect();
            while(tempConnection.rs.next())
            {
                int asn = tempConnection.rs.getInt(1);
                //System.out.println(asn);
                tempConnectionNew.rs = tempConnectionNew.st.executeQuery("select bulk_despatch_code from subscribers_primary_details where asn = "+asn);
                if(tempConnectionNew.rs.next()){
                    int dNo = tempConnectionNew.rs.getInt(1);
                    //System.out.println(endingPeriod);
                    tempConnectionNew.a = tempConnectionNew.st.executeUpdate("update receipt_book_details set bulk_despatch_code  = "+dNo+" where asn = "+asn);
                    if(tempConnectionNew.a == 0)
                        System.out.println("Failed");
                }
            }
            tempConnectionNew.closeAll();
            //tempConnection.a = tempConnection.st.executeUpdate("update subscribers_primary_details set membership_status='Active' where membership_status not in ('STOPPED')");
            tempConnection.closeAll();*/
            
            
            
            connect c1 = new connect();
            if(false)c1.a = c1.st.executeUpdate("update basic set status='Active' where status not in ('STOPPED')");
            c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Active' where membership_status not in ('STOPPED')");
            
            
            if(false)c1.a = c1.st.executeUpdate("update basic set basic.status='Freeze' where basic.asn in (select asn from payment where (endm<" + m + " and endy=" + (y - 1) + ") or endy<" + (y - 1) + ") and basic.status not in ('STOPPED')");
            if(false)System.out.println("update basic set basic.status='Freeze' where basic.asn in (select asn from payment where (endm<" + m + " and endy=" + (y - 1) + ") or endy<" + (y - 1) + ") and basic.status not in ('STOPPED')");
            if(false)System.out.println("freezed : "+c1.a);
            
            String ending_period = (y-1)+"-"+m+"-1";
            
            c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Freeze' where ending_period < '"+ending_period+"' and membership_status not in ('STOPPED')");
            System.out.println("update subscribers_primary_details set membership_status='Freeze' where ending_period < '"+ending_period+"' and membership_status not in ('STOPPED')");
            System.out.println("freezed : "+c1.a);
            
            
            //--------------------------6 mnth nd 1 year-------------------------------//
            
            md = (m + 5) % 12;
            yd = y;
            if (md == 0) {
                md = 12;
            }
            
            
            //if (md > 7) {           //changed to 7 , earlier it was 6, maybe this is the reason of problems
            if(m > md){                   //new condition used, maybe this can remove the problem
                yd--;
            }
            
            if (yd != y) {
                if(false)c1.a = c1.st.executeUpdate("update basic set basic.status='Deactive' where basic.asn in ( select asn "
                        + "from payment where endm>=" + m + " and endy=" + (y - 1)
                        + " UNION select asn from payment where endm<=" + md + " and endy=" + y + ") and basic.status not in ('STOPPED')");
                
                if(false)System.out.println("deactivated : "+c1.a);
                
                
                if(false)System.out.println("Hello update basic set basic.status='Deactive' where basic.asn in ( select asn "
                        + "from payment where endm>=" + m + " and endy=" + (y - 1)
                        + " UNION select asn from payment where endm<=" + md + " and endy=" + y + ") and basic.status not in ('STOPPED')");
                
                String period1 =  (y-1)+"-"+m+"-1";
                String period2 = y+"-"+md+"-28";
                c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Deactive' where  (  "
                        + "ending_period >= '" + period1 + "' and ending_period <= '" + period2 + "' ) and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Deactive' where  (  "
                        + "ending_period >= '" + period1 + "' and ending_period <= '" + period2 + "' ) and membership_status not in ('STOPPED')");
                System.out.println("deactivated : "+c1.a);
                
            } else if (yd == y) {
                
                if(false)c1.a = c1.st.executeUpdate("update basic set basic.status='Deactive' where basic.asn in (select asn from payment where (endm>=" + m + " and endm<=" + md +") and  endy=" + (y - 1) + " ) and basic.status not in ('STOPPED')");
                if(false)System.out.println("update basic set basic.status='Deactive' where basic.asn in (select asn from payment where (endm>=" + m + " and endm<=" + md +") and  endy=" + (y - 1) + " ) and basic.status not in ('STOPPED')");
                if(false)System.out.println("deactivated : "+c1.a);
                String period1 = (y-1)+"-"+m+"-1";
                String period2 = (y-1)+"-"+md+"-28";
                c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Deactive' where  ending_period >= '"+period1+"' and ending_period <= '"+period2+"' and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Deactive' where  ending_period >= '"+period1+"' and ending_period <= '"+period2+"' and membership_status not in ('STOPPED')");
                System.out.println("deactivated : "+c1.a);
            }
            
            System.out.println("deactivated : "+c1.a+"");
            
            //=======================================inactive last 6 months=============================================
            
            mi = (m + 6) % 12;
            int mi2 = m-1;
            
            if(mi2==0)
                mi2=12;
            
            yi = y;
            if (mi == 0) {
                mi = 12;
            }
            
            
            
            if(mi2==12)
                yi--;
            
            //====================================================================
            if (mi2 < mi) {
                
                if(false)c1.a = c1.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") or (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");
                if(false)System.out.println("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") or (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");
                if(false)System.out.println("inactivated : "+c1.a);
                
                String period1 = y+"-"+mi2+"-28";
                String period2 = (y-1)+"-"+mi+"-1";
                String abc = "(endm<=" + mi2 + " and endy=" + y + ") or (endm>=" + mi + " and endy=" + (y-1) ;
                c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '"+period2+"') and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '"+period2+"') and membership_status not in ('STOPPED')");
                System.out.println("inactivated : "+c1.a);
            }
            //====================================================================
            
            else if (mi2==12)
            {
                if(false)c1.a = c1.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn "
                        + "in (select asn from payment where (endm<=" + mi2 + " and endy=" + (y-1) + ") "
                        + "and (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");
                
                if(false)System.out.println("update basic set basic.status='Inactive' where basic.asn "
                        + "in (select asn from payment where (endm<=" + mi2 + " and endy=" + (y-1) + ") "
                        + "and (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");
                
                if(false)System.out.println("inactivated : "+c1.a);
                
                String period1 = (y-1)+"-"+mi2+"-28";
                String period2 = (y-1)+"-"+mi+"-1";
                c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Inactive' "
                        + "where (ending_period <= '"+period1+"' "
                        + "and ending_period >= '"+period2+"') and membership_status not in ('STOPPED')");
                System.out.println("inactivated : "+c1.a);
                
            }
            else
            {
                if(false)c1.a = c1.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") and (endm>=" + mi + " and endy=" + (y) + ")) and basic.status not in ('STOPPED')");
                if(false)System.out.println("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") and (endm>=" + mi + " and endy=" + (y) + ")) and basic.status not in ('STOPPED')");
                
                System.out.println("inactivated : "+c1.a);
                String period1 = y+"-"+mi2+"-28";
                String period2 = y+"-"+mi+"-1";
                c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '" + period2 + "') and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '" + period2 + "') and membership_status not in ('STOPPED')");
                System.out.println("inactivated : "+c1.a);
            }
            
            c1.closeAll();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        update();
    }
}
