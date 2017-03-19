
import java.util.*;

public class refresh {
    
    public static void main(String args[]) {
        new refresh();
    }
    int i = 0;
    int x = 0;
    int[] asn;
    int mi, md, mf;
    int yi, yd, yf;
    
    public refresh() {
        try {
            GregorianCalendar g = new GregorianCalendar();
            int m = (g.get(Calendar.MONTH) + 1);
            int y = g.get(Calendar.YEAR);
            System.out.println(m+" "+y);
            
            
            //----------------------------1 year----------------------------------------//
            


            
            connect c1 = new connect();
            c1.a = c1.st.executeUpdate("update basic set status='Active' where status not in ('STOPPED')");
            c1.a = c1.st.executeUpdate("update subscribers_primary_details set membership_status='Active' where membership_status not in ('STOPPED')");
            c1.closeAll();

            
            
            connect c4 = new connect();
            c4.a = c4.st.executeUpdate("update basic set basic.status='Freeze' where basic.asn in (select asn from payment where (endm<" + m + " and endy=" + (y - 1) + ") or endy<" + (y - 1) + ") and basic.status not in ('STOPPED')");
            System.out.println("update basic set basic.status='Freeze' where basic.asn in (select asn from payment where (endm<" + m + " and endy=" + (y - 1) + ") or endy<" + (y - 1) + ") and basic.status not in ('STOPPED')");
            System.out.println("freezed : "+c4.a);
            String ending_period = (y-1)+"-"+m+"-1";
            
            c4.a = c4.st.executeUpdate("update subscribers_primary_details set membership_status='Freeze' where ending_period < '"+ending_period+"' and membership_status not in ('STOPPED')");
            System.out.println("update subscribers_primary_details set membership_status='Freeze' where ending_period < '"+ending_period+"' and membership_status not in ('STOPPED')");
            System.out.println("freezed : "+c4.a);
            
            c4.closeAll();
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

            //System.out.println("update basic set basic.status='Deactive' where basic.asn in (select asn from payment where (endm>"+(m-1)+" and endy="+(y-1)+") UNION select asn from payment where (endm<"+md+" and endy="+yd+") )");

            connect c5 = new connect();
            //            System.out.println("update basic set basic.status='Deactive' where basic.asn in (select asn "
            //                        + "from payment where ((endm>" + (m - 1) + " and endy=" + (y - 1) + ")"
            //                        + "|| (endm<" + (md+1) + " and endy=" + yd + ") )) and basic.status not in ('STOPPED')");
            //            System.out.println(yd+  " deactive" +md);
            if (yd != y) {
                c5.a = c5.st.executeUpdate("update basic set basic.status='Deactive' where basic.asn in ( select asn "
                        + "from payment where endm>=" + m + " and endy=" + (y - 1)
                        + " UNION select asn from payment where endm<=" + md + " and endy=" + y + ") and basic.status not in ('STOPPED')");

                //System.out.println(yd+":"+y);
                System.out.println("deactivated : "+c5.a);


                System.out.println("Hello update basic set basic.status='Deactive' where basic.asn in ( select asn "
                        + "from payment where endm>=" + m + " and endy=" + (y - 1)
                        + " UNION select asn from payment where endm<=" + md + " and endy=" + y + ") and basic.status not in ('STOPPED')");

                String period1 =  (y-1)+"-"+m+"-1";
                String period2 = y+"-"+md+"-28";
                c5.a = c5.st.executeUpdate("update subscribers_primary_details set membership_status='Deactive' where  (  "
                        + "ending_period >= '" + period1 + "' and ending_period <= '" + period2 + "' ) and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Deactive' where  (  "
                        + "ending_period >= '" + period1 + "' and ending_period <= '" + period2 + "' ) and membership_status not in ('STOPPED')");
                System.out.println("deactivated : "+c5.a);

            } else if (yd == y) {
                //System.out.println("Hello");
                //System.out.println("update basic set basic.status='Deactive' where basic.asn in (select asn from payment where (endm>=" + m + " and endm<=" + md +") and  endy=" + (y - 1) + " ) and basic.status not in ('STOPPED')");

                c5.a = c5.st.executeUpdate("update basic set basic.status='Deactive' where basic.asn in (select asn from payment where (endm>=" + m + " and endm<=" + md +") and  endy=" + (y - 1) + " ) and basic.status not in ('STOPPED')");
                System.out.println("update basic set basic.status='Deactive' where basic.asn in (select asn from payment where (endm>=" + m + " and endm<=" + md +") and  endy=" + (y - 1) + " ) and basic.status not in ('STOPPED')");
                System.out.println("deactivated : "+c5.a);
                String period1 = (y-1)+"-"+m+"-1";
                String period2 = (y-1)+"-"+md+"-28";
                c5.a = c5.st.executeUpdate("update subscribers_primary_details set membership_status='Deactive' where  ending_period >= '"+period1+"' and ending_period <= '"+period2+"' and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Deactive' where  ending_period >= '"+period1+"' and ending_period <= '"+period2+"' and membership_status not in ('STOPPED')");
                System.out.println("deactivated : "+c5.a);
            }

            System.out.println("deactivated : "+c5.a+"");

            c5.closeAll();
            //System.out.println("B");
            //=======================================inactive last 6 months=============================================

            mi = (m + 6) % 12;
            int mi2 = m-1;

            if(mi2==0)
                mi2=12;

            yi = y;
            //System.out.println(mi +" inactive "+yi);
            if (mi == 0) {
                mi = 12;
            }

            /*
            if (mi > 7) {       //changed to 7 , earlier this was also 6, maybe another reason of problem
            yi--;
            }
            */

            if(mi2==12)
                yi--;

            //            System.out.println(yi+  " inactive" +mi);
            //System.out.println("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<" + m + " and endy=" + y + ") and (endm>" + (mi - 1) + " and endy=" + yi + ")) and basic.status not in ('STOPPED')");

            connect c3 = new connect();

            /*
            if (yi != y) {
            c3.a = c3.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") union select asn from payment where (endm>=" + mi + " and endy=" + yi + ")) and basic.status not in ('STOPPED')");
            }
            */
            //====================================================================
            if (mi2 < mi) {
                //System.out.println("Hello");
            //                c3.a = c3.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm>="+mi+" and endm<=" + mi2 + ") and endy=" + yi + " ) and basic.status not in ('STOPPED')");

                c3.a = c3.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") or (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");
                System.out.println("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") or (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");
                System.out.println("inactivated : "+c3.a);

                String period1 = y+"-"+mi2+"-28";
                String period2 = (y-1)+"-"+mi+"-1";
                String abc = "(endm<=" + mi2 + " and endy=" + y + ") or (endm>=" + mi + " and endy=" + (y-1) ;
                c3.a = c3.st.executeUpdate("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '"+period2+"') and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '"+period2+"') and membership_status not in ('STOPPED')");
                System.out.println("inactivated : "+c3.a);
            }
            //====================================================================

            else if (mi2==12)
            {
                c3.a = c3.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn "
                        + "in (select asn from payment where (endm<=" + mi2 + " and endy=" + (y-1) + ") "
                        + "and (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");

                System.out.println("update basic set basic.status='Inactive' where basic.asn "
                        + "in (select asn from payment where (endm<=" + mi2 + " and endy=" + (y-1) + ") "
                        + "and (endm>=" + mi + " and endy=" + (y-1) + ")) and basic.status not in ('STOPPED')");

                System.out.println("inactivated : "+c3.a);

                String period1 = (y-1)+"-"+mi2+"-28";
                String period2 = (y-1)+"-"+mi+"-1";
                c3.a = c3.st.executeUpdate("update subscribers_primary_details set membership_status='Inactive' "
                        + "where (ending_period <= '"+period1+"' "
                        + "and ending_period >= '"+period2+"') and membership_status not in ('STOPPED')");
                System.out.println("inactivated : "+c3.a);

            }
            //else if (yi == y) {
            else
            {
                c3.a = c3.st.executeUpdate("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") and (endm>=" + mi + " and endy=" + (y) + ")) and basic.status not in ('STOPPED')");
                System.out.println("update basic set basic.status='Inactive' where basic.asn in (select asn from payment where (endm<=" + mi2 + " and endy=" + y + ") and (endm>=" + mi + " and endy=" + (y) + ")) and basic.status not in ('STOPPED')");

                System.out.println("inactivated : "+c3.a);
                String period1 = y+"-"+mi2+"-28";
                String period2 = y+"-"+mi+"-1";
                c3.a = c3.st.executeUpdate("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '" + period2 + "') and membership_status not in ('STOPPED')");
                System.out.println("update subscribers_primary_details set membership_status='Inactive' where (ending_period <= '"+period1+"' and ending_period >= '" + period2 + "') and membership_status not in ('STOPPED')");
                System.out.println("inactivated : "+c3.a);
            }

            //System.out.println(""+c3.a);                                                                 (endm<2 and endy=2010) or endy=2009 and (endm>8 and endy=2009)
            //System.out.println("inactivated : "+c3.a);
            c3.closeAll();

        } catch (Exception e) {
            //System.out.println(e);
            e.printStackTrace();
        }
    }
}
