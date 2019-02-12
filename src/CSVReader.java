import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class CSVReader {
    
    public static void main(String[] args) {
        new CSVReader();
    }
    
    public CSVReader() {
        
        String csvFile = "prerna_new.csv";
        String outSqlFile = "prerna_new_out.sql";
        String outCsvFile = "prerna_new_out.csv";
        
        BufferedReader br = null;
        BufferedWriter bw = null;
        BufferedWriter bwcsv = null;
        String line = "";
        String cvsSplitBy = ",";
        
        connect abc = new connect();
        
        try {
            
            abc.rs = abc.st.executeQuery("select asn from asn");
            int x  = 0;
            int orig_asn = 0;
            if(abc.rs.next())
                x = abc.rs.getInt(1);
            
            orig_asn = x;
            
            int ms=0,up=0,dl=0,hr=0,mh=0,pb=0,rj=0,uk=0,mp=0;
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='MS'");
            if(abc.rs.next())
                ms = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='DL'");
            if(abc.rs.next())
                dl = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='HR'");
            if(abc.rs.next())
                hr = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='MH'");
            if(abc.rs.next())
                mh = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='MP'");
            if(abc.rs.next())
                mp = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='PB'");
            if(abc.rs.next())
                pb = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='RJ'");
            if(abc.rs.next())
                rj = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='UK'");
            if(abc.rs.next())
                uk = abc.rs.getInt(1);
            abc.rs = abc.st.executeQuery("select max(subscription_number) from subscribers_primary_details where subscription_code='UP'");
            if(abc.rs.next())
                up = abc.rs.getInt(1);
            
            
            int line_num = 0;
            br = new BufferedReader(new FileReader(csvFile));
            bw = new BufferedWriter(new FileWriter(outSqlFile));
            bwcsv = new BufferedWriter(new FileWriter(outCsvFile));
            while ((line = br.readLine()) != null) {
                
                String[] data = line.split(cvsSplitBy);
                
                
                if(line_num > 0){
                    for (int i = 0; i < data.length; i++)
                    {
                        data[i] = (data[i].replace("%%", "-")).trim();
                        data[i] = (data[i].replace("'", "")).trim();
                    }
                    // use comma as separator
                    data[0] = ""+x++;
                    /*{
                        if(data[23].equals("DL")){
                            data[1] = "DL";
                            data[2] = ""+(++dl);
                        }
                        else if(data[23].equals("HAR")){
                            data[1] = "HR";
                            data[2] = ""+(++hr);
                        }
                        else if(data[23].equals("MAH")){
                            data[1] = "MH";
                            data[2] = ""+(++mh);
                        }
                        else if(data[23].equals("MP")){
                            data[1] = "MP";
                            data[2] = ""+(++mp);
                        }
                        else if(data[23].equals("PJB")){
                            data[1] = "PB";
                            data[2] = ""+(++pb);
                        }
                        else if(data[23].equals("RAJ")){
                            data[1] = "RJ";
                            data[2] = ""+(++rj);
                        }
                        else if(data[23].equals("UK")){
                            data[1] = "UK";
                            data[2] = ""+(++uk);
                        }
                        else if(data[23].equals("UP")){
                            data[1] = "UP";
                            data[2] = ""+(++up);
                        }
                        else{
                            data[1] = "MS";
                            data[2] = ""+(++ms);
                        }
                    }*/
                    //System.out.println("UNKNOWN: " + data[23]);
                    
                    data[3] = "Active"; //membership_Status
                    if(data[4].isEmpty())data[4] = "0";   //pin code if not provided
                    data[5] = "By Post";    //distribution_type
                    data[6] = "0";          //bulk_despatch_code
                    
                    //subscription_period based on amount
                    {
                        //TODO
                    }
                    
                    data[11] = "0";         //instrument_number
                    data[12]="2018-01-01";  //receipt_date
                    
                    if(data[24].isEmpty())data[24] = "0";   //pin code if not provided
                    
                    data[26] = "Kirpal Ashram";
                    data[28] = "99999";
                    data[29] = "New";
                    data[30] = ""+SamsUtilities.getCurrentSqlDate();
                    data[36] = "Sumit";
                    
                    
                    
                    
                    /*for (int i = 0; i < data.length; i++)
                    {
                    data[i] = (data[i].replace("%%", ":")).trim();
                    if( i >=18 && i <=22 )
                    {
                    //if(data[i].length()> 31)
                    {
                    //System.out.print(i + " --> " + data[i].length() + " --> ");
                    System.out.printf("%33s",data[i].trim()+"|");
                    //System.out.print(data[i].trim()+",");
                    //System.out.println();
                    }
                    }
                    else
                    System.out.printf("%20s",data[i].trim()+"|");
                    //System.out.print(data[i].trim()+",");
                    }
                    //System.out.printf("%20s",data[16].trim()+"|");
                    System.out.println();*/
                    
                    String query = "insert into subscribers_primary_details values("
                            +data[0]+",'"
                            +data[1]+"',"
                            +data[2]+",'"
                            +data[3]+"',"
                            +data[4]+",'"
                            +data[5]+"',"
                            +data[6]+",'"
                            +data[7]+"','"
                            +data[8]+"','"
                            +data[9]+"','"
                            +data[10]+"',"
                            +data[11]+",'"
                            +data[12]+"',"
                            +data[13]+",'"
                            +data[14]+"','"
                            +data[15]+"','"
                            +data[16]+"','"
                            +data[17]+"','"
                            +data[18]+"','"
                            +data[19]+"','"
                            +data[20]+"','"
                            +data[21]+"','"
                            +data[22]+"','"
                            +data[23]+"',"
                            +data[24]+",'"
                            +data[25]+"','"
                            +data[26]+"','"
                            +data[27]+"',"
                            +data[28]+",'"
                            +data[29]+"','"
                            +data[30]+"','"
                            +data[31]+"','"
                            +data[32]+"',"
                            + "'',"
                            + "'',"
                            + "'','"
                            +data[36]+"');";
                    //System.out.println(query);
                    bw.write(query);
                    bw.newLine();
                    bw.flush();
                }
                line_num++;
                for (int i = 0; i < data.length; i++)
                {
                    data[i] = (data[i].replace("%%", "-")).trim();
                    bwcsv.write(data[i]+",");
                }
                bwcsv.newLine();
                bwcsv.flush();
            }
            String asnUpdate = "update asn set asn = "+x+" where asn = "+orig_asn+";";
            //System.out.println(asnUpdate);
            bw.write(asnUpdate);
            bw.flush();
            bw.close();
            System.out.println("Created file: "+outSqlFile +" in directory: "+ System.getProperty("user.dir"));
            System.out.println("Created CSV file: "+outCsvFile +" in directory: "+ System.getProperty("user.dir"));
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}