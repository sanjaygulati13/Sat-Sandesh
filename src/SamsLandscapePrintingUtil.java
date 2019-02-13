
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import javax.swing.JOptionPane;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author sanjay
 */
public class SamsLandscapePrintingUtil implements Printable {
    
    int NumberOfRecords = 0;
    int[] pageBreak;
    String[][] textLines;
    int chk = 0;
    int numLines;
    String header;
    int[] asnSet;
    int linesPerPage = 0;
    int numRecordsInDb = 0;
    int index  = 0;
    
    public String getHeader()
    {
        return header;
    }
    
    public void setHeader(String header_)
    {
        header = header_;
    }
    
    public void setAsnSet(int[] asnSet_)
    {
        //System.out.println("Length: " + asnSet_.length);
        asnSet = asnSet_;
    }
    
    public void initLinesLandscape(int linesPerPage)
    {
        
        //int linesPerPage = 100;
        //String StartDate = "00-00-0000";
        //int index = 0;
        int asnSetIndex = 0;
        int y = 0;
        try
        {
            
            connect databaseConnection=new connect();
            //String query = "select count(asn) from subscribers_primary_details where entry_date >= '"+StartDate+"'";
            //databaseConnection.rs=databaseConnection.st.executeQuery(query);
            //if(databaseConnection.rs.next())
            //    numRecordsInDb = databaseConnection.rs.getInt(1);
            
            numRecordsInDb = asnSet.length;
            NumberOfRecords = numRecordsInDb;
            
            //System.out.println(NumberOfRecords);
            numLines = numRecordsInDb*2;
            //--------------------------------^^numlines for records only==============================
            
            //int page=(numLines/linesPerPage) + (numLines%linesPerPage);
            int page=(numLines/linesPerPage) + (((numLines%linesPerPage)>0)?1:0);
            System.out.println("Num pages: " + page);
            
            int head = page * 3 ;
            numLines = ( numRecordsInDb + head ) * 2;
            y = numLines%linesPerPage;
            
            if( y < 4 ) head+=3;
            
            numRecordsInDb+=head;
            numLines=numRecordsInDb*2;
            
            page=(numLines/linesPerPage);
            
            //======================================================================================================
            
            numRecordsInDb += (((page)/2) + ((page)%2));
            
            if((numLines%55)>53)
                numRecordsInDb++;
            
            //====================================================================================================
            numLines = numRecordsInDb*2;
            
            int present = 0;
            //System.out.println("NumLines : "+ numLines);
            textLines = new String[numLines][15];
            int[] asn=new int[numRecordsInDb];
            
            asnSetIndex = 0;
            
            for(index=0 ; index < numRecordsInDb ; index++)
            {
                //System.out.println("index : "+index);
                if(index==0)
                {
                    //query = "select asn from subscribers_primary_details where entry_date >= '"+StartDate+"' order by state, first_name, last_name";
                    
                    //databaseConnection.rs=databaseConnection.st.executeQuery(query);
                    while(asnSetIndex < asnSet.length)
                    {
                        //System.out.println(asn[index]);
                        if(index%(linesPerPage/2)==0 && index<numRecordsInDb)
                        {
                            asn[index]=0;
                            //System.out.println(asn[index]);
                            index++;
                            
                        }
                        if(index%(linesPerPage/2)==1 && index<numRecordsInDb)
                        {
                            asn[index]=0;
                            //System.out.println(asn[index]);
                            index++;
                        }
                        if(index%(linesPerPage/2)==2 && index<numRecordsInDb)
                        {
                            asn[index]=0;
                            //System.out.println(asn[index]);
                            index++;
                        }
                        if(index%(linesPerPage/2)>2 && index<numRecordsInDb )
                        {
                            //asn[index]=databaseConnection.rs.getInt(1);
                            //System.out.println(asn.length+ " --> " + index + " :: " + asnSet.length + " --> " + asnSetIndex + " :: " + numRecordsInDb);
                            asn[index]=asnSet[asnSetIndex];
                            asnSetIndex++;
                            //System.out.println("a ["+index+"] := "+asn[index]);
                            index++;
                        }
                        
                        present=index;
                        
                    }
                }
                
                if(index<numRecordsInDb)
                    asn[index]=0;
                //System.out.println("a ["+index+"] := "+asn[index]);
            }
            
            
            
            index=0;
            String query = "";
            asnSetIndex = 0;
            for(index=0;index < numRecordsInDb ; index++)
            {
                if(index<present)
                {
                    
                    if(index%(linesPerPage/2)==0)
                    {
                        textLines[index][0]="";
                        textLines[index][1]="";
                        textLines[index][2]="";
                        textLines[index][3]="";
                        textLines[index][4]="";
                        textLines[index][5]="";
                        textLines[index][6]="";
                        textLines[index][7]="";
                        textLines[index][8]="";
                        textLines[index][9]="";
                        textLines[index][10]="";
                        textLines[index][11]="";
                        textLines[index][12]="";
                        textLines[index][13]="";
                        textLines[index][14]="";
                        
                    }
                    else if(index%(linesPerPage/2)==1)
                    {
                        textLines[index][0]="";
                        textLines[index][1]="";
                        textLines[index][2]="";
                        textLines[index][3]="";
                        textLines[index][4]="";
                        textLines[index][5]="";
                        textLines[index][6]="";
                        textLines[index][7]="";
                        /*textLines[index][5]="  SAT-SANDESH  ";
                        textLines[index][6]="  (HINDI)  ";
                        textLines[index][7]="  INDEX REGISTER AS ON  "+d+"-"+m+"-"+y1;
                        */
                        textLines[index][8]="";
                        textLines[index][9]="";
                        textLines[index][10]="";
                        textLines[index][11]="";
                        textLines[index][12]="";
                        textLines[index][13]="";
                        textLines[index][14]="";
                    }
                    else if(index%(linesPerPage/2)==2)
                    {
                        textLines[index][0]="ASN";
                        textLines[index][1]="Sub No";
                        textLines[index][2]="End Period";
                        textLines[index][3]="";//Year";
                        textLines[index][4]="D#";
                        textLines[index][5]="F Name";
                        textLines[index][6]="L Name";
                        textLines[index][7]="ADDRESS";
                        textLines[index][8]="";
                        textLines[index][9]="";
                        textLines[index][10]="District";
                        textLines[index][11]="State";
                        textLines[index][12]="Pin";
                        textLines[index][13]="";
                        textLines[index][14]="";
                    }
                    
                    else if((index%(linesPerPage/2))>2)
                    {
                        
                        query = "select asn, subscription_code, subscription_number, ending_period, bulk_despatch_code,receipt_number, series_name from subscribers_primary_details where asn="+asn[index];
                        //System.out.println(query);
                        databaseConnection.rs=databaseConnection.st.executeQuery(query);
                        databaseConnection.rs.next();
                        
                        int rcptNum = databaseConnection.rs.getInt(6);
                        String seriesName = databaseConnection.rs.getString(7);
                        java.util.Date endDate = databaseConnection.rs.getDate(4);
                        
                        textLines[index][0]=""+databaseConnection.rs.getInt(1);
                        textLines[index][1]=""+databaseConnection.rs.getString(2)+databaseConnection.rs.getString(3);
                        textLines[index][2]=""+(endDate.getMonth()+1)+"/"+(endDate.getYear()+1900);
                        if(seriesName.length() < 5) seriesName = "RN-"+seriesName;
                        textLines[index][3]="("+seriesName + "/"+rcptNum+")";//+(endDate.getYear()+1900);
                        textLines[index][4]="";
                        int d=databaseConnection.rs.getInt(5);
                        if(d>0) textLines[index][4]=""+d;
                        
                        
                        
                    }
                }
                else if(index>=present)
                {
                    
                    textLines[index][0]="";
                    textLines[index][1]="";
                    textLines[index][2]="";
                    textLines[index][3]="";
                    textLines[index][4]="";
                    textLines[index][5]="";
                    textLines[index][6]="";
                    textLines[index][7]="";
                    textLines[index][8]="";
                    textLines[index][9]="";
                    textLines[index][10]="";
                    textLines[index][11]="";
                    textLines[index][12]="";
                    textLines[index][13]="";
                    textLines[index][14]="";
                    
                }
            }
            
            
            index=0;
            
            for(index=0;index<numRecordsInDb;index++)
            {
                if(index<present)
                {
                    
                    if(index%(linesPerPage/2)>2)
                    {
                        query = "select first_name,last_name,address_line1,address_line2,address_line3,district,state,pin_code from subscribers_primary_details where asn="+asn[index];
                        
                        databaseConnection.rs=databaseConnection.st.executeQuery(query);
                        databaseConnection.rs.next();
                        String firstName, lastName, address1, address2, address3, district,stateName;
                        int pinCode = 0;
                        
                        firstName= databaseConnection.rs.getString(1);
                        lastName= databaseConnection.rs.getString(2);
                        address1= databaseConnection.rs.getString(3);
                        address2= databaseConnection.rs.getString(4);
                        address3= databaseConnection.rs.getString(5);
                        district= databaseConnection.rs.getString(6);
                        stateName=databaseConnection.rs.getString(7);
                        pinCode = Integer.parseInt(databaseConnection.rs.getString(8));
                        
                        textLines[index][5]  = "";
                        textLines[index][6]  = "";
                        textLines[index][7]  = "";
                        textLines[index][8]  = "";
                        textLines[index][9]  = "";
                        textLines[index][10] = "";
                        textLines[index][11] = "";
                        
                        if(firstName!=null)
                            textLines[index][5]=firstName;
                        
                        if(lastName!=null)
                            textLines[index][6]=lastName;
                        
                        if(address1!=null)
                            textLines[index][7]=address1 + " " + address2;
                        
                        //if(address2!=null)
                        //    textLines[index][8]=address2;
                        
                        if(address3!=null)
                            textLines[index][9]=address3;
                        
                        if(district!=null)
                            textLines[index][10]=district;
                        
                        if(stateName!=null)
                            textLines[index][11]=stateName;
                        
                        textLines[index][12] = "" +((pinCode>0)?pinCode:"");
                        
                    }
                }
            }
            

            for(index = 0; index < numRecordsInDb ; index++)
            {
                if(index < present)
                {
                    if(index%(linesPerPage/2)>2)
                    {
                        query = "select phone_number, email from subscribers_primary_details where asn="+asn[index];
                        databaseConnection.rs=databaseConnection.st.executeQuery(query);
                        databaseConnection.rs.next();
                        String phoneNumber, email;
                        
                        phoneNumber= databaseConnection.rs.getString(1);
                        email= databaseConnection.rs.getString(2);
                        
                        textLines[index][13]="";
                        textLines[index][14]="";
                        
                        if(phoneNumber!=null && phoneNumber.length()>6)
                            textLines[index][13]="(T No.-"+phoneNumber+")";
                        
                        
                        if(email != null && email.length()>5)                        
                            textLines[index][14]="(e-id:"+email+")";textLines[index][14]="(e-id:"+email+")";
                        
                    }
                }
            }
            
            databaseConnection.closeAll();
            index = 0;
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR"+e,"ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }
    
    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics g, String text, int pageWidth, Font font, int y) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int headerWidth = metrics.stringWidth(text);
        
        int x = (pageWidth - headerWidth) / 2;
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
        
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        
        Font f1=new Font("SERIF", Font.PLAIN, 7);
        FontMetrics metric=graphics.getFontMetrics(f1);
        int lineHeight=metric.getHeight();
        
        int a[]=new int[13];
        
        //asn , subno, endm , endy, dno, fname, lname, add1, add2, add3, dist, state, pin
        
        a[1]=metric.stringWidth("99999   ");
        a[2]=15+a[1]+metric.stringWidth("DDDDDD  ");		//after asn
        a[3]=10+a[2]+metric.stringWidth("DD");				//
        a[4]=10+a[3]+metric.stringWidth("9999 ");
        a[5]=10+a[4]+metric.stringWidth("99 ");
        
        a[6]=10+a[5]+metric.stringWidth("AMIT AMIT AMITPPDD");
        a[7]=10+a[6]+metric.stringWidth("PAUL PAUL PAULPDDD");
        a[8]=10+a[7]+metric.stringWidth("GURU HARKISHAN NAGAR GURU HARKISDDDDD");
        a[9]=10+a[8]+metric.stringWidth("GURU HARKISHAN NAGAR GURU HARKISDDDDD");
        
        a[10]=10+a[9]+metric.stringWidth("NEW DELHI NEW DELHI ND");
        a[11]=10+a[10]+metric.stringWidth("MAH ");
        
        
        
        if(pageBreak==null)
        {   
            linesPerPage=(int)(pageFormat.getImageableHeight()/lineHeight);
            initLinesLandscape(linesPerPage);
            int numBreaks=(numLines/linesPerPage);
            pageBreak=new int[numBreaks];
            for(int b=0;b<numBreaks;b++)
            {
                pageBreak[b]=(b+1)*linesPerPage;
            }
        }
        //System.out.println(linesPerPage + " :: linesPerPage");
        
        if(pageIndex > pageBreak.length)
        {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d=(Graphics2D)graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        
        int y=0;
        
        graphics.setFont(new Font("SERIF", Font.PLAIN, 8));
        
        
        int start=0;
        if(pageIndex==0)
            start=0;
        else
            start=pageBreak[pageIndex-1];
        
        int end=0;
        
        if(pageIndex==pageBreak.length)
            end=numLines;
        else
        {
            if(linesPerPage%2==0)
                end=pageBreak[pageIndex];
            else if(linesPerPage%2==1)
                end=pageBreak[pageIndex]-1;
        }
        
        if(chk%2==1 && (end-start)!=lineHeight)
        {
            
            graphics.drawLine(3,3*lineHeight,3,(end-start)*lineHeight);		//vertical lines
            graphics.drawLine(a[1]+8,3*lineHeight,a[1]+8,(end-start)*lineHeight);
            graphics.drawLine(a[2]-2,3*lineHeight,a[2]-2,(end-start)*lineHeight);
            //graphics.drawLine(a[3]-2,3*lineHeight,a[3]-2,(end-start)*lineHeight);
            graphics.drawLine(a[4]-2,3*lineHeight,a[4]-2,(end-start)*lineHeight);
            graphics.drawLine(a[5]-2,3*lineHeight,a[5]-2,(end-start)*lineHeight);
            //g.drawLine(a[6]-2,3*lineHeight,a[6]-2,(end-start)*lineHeight);
            graphics.drawLine(a[7]-2,3*lineHeight,a[7]-2,(end-start)*lineHeight);
            graphics.drawLine(a[9]-2,3*lineHeight,a[9]-2,(end-start)*lineHeight);
            graphics.drawLine(a[10]-2,3*lineHeight,a[10]-2,(end-start)*lineHeight);
            graphics.drawLine(a[11]-2,3*lineHeight,a[11]-2,(end-start)*lineHeight);
            graphics.drawLine((int)pageFormat.getImageableWidth(),3*lineHeight,(int)pageFormat.getImageableWidth(),(end-start)*lineHeight);
            
            
            //int index = 0;
            for(int line=start; line<end; line+=2)
            {
                if(index%(linesPerPage/2)==1)
                {
                    Font headerFont = new Font("SERIF", Font.BOLD, 10);
                    graphics.setFont(headerFont);
                    
                    
                    String commonPageString = " ( - "+(pageIndex+1)+" - )\t("+SamsUtilities.getCurrentDateString()+")  (TR: "+NumberOfRecords+")";
                    String tempheader = header + commonPageString;
                    
                    int pageWidth = (int)pageFormat.getImageableWidth();
                    //FontMetrics metrics = graphics.getFontMetrics(headerFont);
                    //int headerWidth = metrics.stringWidth(tempheader);
                    
                    drawCenteredString(graphics, tempheader, pageWidth, headerFont,y);
                    //int x = (pageWidth - headerWidth) / 2;
                    //graphics.drawString(tempheader,x,y);
                    //graphics.drawString("SAT SANDESH - INDEX REGISTER (Suppl) w.e.f. "+d+"-"+m+"-"+y1, (int)pageFormat.getWidth()/2-120,y);
                    //graphics.drawString("( - "+(pageIndex+1)+" - )", (int)pageFormat.getWidth()/2+150,y);
                    //graphics.drawString("("+SamsUtilities.getCurrentDateString()+")  (TR: "+NumberOfRecords+")",(int)pageFormat.getWidth()/2+200 , y);
                }
                
                graphics.setFont(new Font("SERIF", Font.PLAIN, 8));
                
                if(index%(linesPerPage/2)>1)
                {
                    graphics.drawLine(3,y+lineHeight,(int)pageFormat.getWidth()+1,y+lineHeight); //horizontal lines
                    
                    graphics.drawString(" "+textLines[index][0], 5, y);
                    
                    graphics.drawString(" "+textLines[index][1], 10+a[1], y);
                    
                    graphics.drawString(" "+textLines[index][2], a[2], y);
                    
                    graphics.setFont(new Font("SERIF", Font.PLAIN, 6));
                    graphics.drawString(" "+textLines[index][3], a[2], y+lineHeight-2);
                    graphics.setFont(new Font("SERIF", Font.PLAIN, 8));
                    
                    graphics.drawString(" "+textLines[index][4], a[4], y);
                    
                    graphics.drawString(" "+textLines[index][5], a[5], y);
                    
                    graphics.drawString(" "+textLines[index][6], a[6], y);
                    
                    graphics.setFont(new Font("SERIF", Font.PLAIN, 7));
                    graphics.drawString(" "+textLines[index][7], a[7], y-1);
                    
                    graphics.drawString(" "+textLines[index][8], a[8], y-1);
                    
                    graphics.drawString(" "+textLines[index][9], a[7], y+lineHeight-2);
                    
                    graphics.setFont(new Font("SERIF", Font.PLAIN, 8));
                    graphics.drawString(" "+textLines[index][10], a[9], y);
                    
                    graphics.drawString(" "+textLines[index][13], a[9], y+lineHeight-2);
                    
                    graphics.drawString(" "+textLines[index][11], a[10], y);
                    
                    graphics.drawString(" "+textLines[index][12], a[11], y);
                    
                }
                y+=2*lineHeight;
                index++;
            }
            //g.drawString(" Page Number "+(pageIndex+1), 300, ((int)pageFormat.getImageableHeight())-10);
            
        }
        else
        {
            
            for(int line=start;line<end; line+=8)
            {
                
            }
            
        }
        chk++;
        
        return PAGE_EXISTS;
        
    }
    
}
