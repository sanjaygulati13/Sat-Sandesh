
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
public class SatSandeshDespatchRemindersList extends SamsLandscapePrintingUtil implements ActionListener
{
    public static void main(String args[])
    {
        new SatSandeshDespatchRemindersList(1,2009,12,2009);
    }
    
    int present;
    
    //int[] pageBreak;
    //int numLines=0;
    //String[][] textLines;
    int[] asn;
    int x=0;
    int x1=0;
    int currentLabel=0;
    //int chk=0;
    int m1, y1, m2, y2;
    JButton printButton, back;
    JFrame f;
    int dno;
    //int linesPerPage;
    //String dist,state;
    //int NumberOfRecords=0;
    
    
    public SatSandeshDespatchRemindersList(int m, int y, int m3, int y3)
    {
        
        m1=m;
        y1=y;
        m2=m3;
        y2=y3;
        
        //System.out.println(m1+" "+y1+" "+m2+" "+y2);
        
        try
        {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
            f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
        }
        catch (Exception cnf)
        {
            System.out.println(cnf);
        }
        
        f= new JFrame("Print Reminders");
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300,100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e){System.exit(0);}});
        printButton=new JButton("Print Reminders");
        printButton.addActionListener(this);
        back=new JButton("Back");
        back.addActionListener(this);
        f.add(printButton);
        printButton.setBounds(10,10,120,25);
        printButton.setMnemonic('P');
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(150,10,120,25);
        //f.pack();
        SamsUtilities.center(f);
        
    }
    
    
    /*
    public void initLines()
    {
        try
        {
            
            connect c1=new connect();
            String sqlQuery = "select count(b.asn) from basic b, payment p where b.asn=p.asn  "
                    + "and (b.dist='By Post' or b.dist='By Hand') and "
                    + "( p.endm>"+(m1-1)+" and p.endy="+y1+")  "
                    + "and (p.endm <"+(m2+1)+" and p.endy="+y2+")";
            
            String dateStart, dateEnd;
            Date referenceStartDate = new Date((y1-1900),m1-1,28);
            Date referenceEndDate = new Date((y2-1900),m2-1,1);
            {
                Calendar c = Calendar.getInstance();
                c.setTime(referenceStartDate);
                c.add(Calendar.MONTH, -1);
                //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                dateStart = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
                c.setTime(referenceEndDate);
                c.add(Calendar.MONTH, 1);
                //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                dateEnd = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
            }
            
            String newSqlQuery = "select count(asn) from subscribers_primary_details where "
                    + "(distribution_type = 'By Post' or distribution_type ='By Hand') and "
                    + "ending_period > '"+dateStart+"' and ending_period < '"+dateEnd+"'";
            
            //System.out.println(sqlQuery);
            //System.out.println(newSqlQuery);
            //c1.rs=c1.st.executeQuery(sqlQuery);
            //if(c1.rs.next())
            //    totalNumberOfLabels = c1.rs.getInt(1);
            
            
            c1.rs=c1.st.executeQuery(newSqlQuery);
            if(c1.rs.next()){
                //System.out.println("Orig: " +totalNumberOfLabels+ " <--> new: " + c1.rs.getInt(1));
                totalNumberOfLabels = c1.rs.getInt(1);
            }
            
            
            NumberOfRecords=totalNumberOfLabels;
            numLines=totalNumberOfLabels*2;
            //System.out.println("totalNumberOfLabels : "+totalNumberOfLabels);
            
            int y=(numLines%linesPerPage);
            //System.out.println("y : "+y);
            numLines=totalNumberOfLabels*2;
            
            //--------------------------------^^numlines for records only==============================
            int page=(numLines/linesPerPage);
            if(numLines%linesPerPage>0)
                page++;
            int head=page*3;
            numLines=(totalNumberOfLabels+head)*2;
            y=numLines%linesPerPage;
            
            if(y<4)
                head+=3;
            totalNumberOfLabels+=head;
            numLines=totalNumberOfLabels*2;
            
            
            page=(numLines/linesPerPage);
            
            //======================================================================================================
            
            
            int half2=(page)/2;
            int half=(page)%2;
            if(half>0)
                half2++;
            totalNumberOfLabels+=half2;
            
            if((numLines%55)>53)
                totalNumberOfLabels++;
            
            //====================================================================================================
            
            numLines=totalNumberOfLabels*2;
            
            textLines=new String[numLines][15];
            asn=new int[totalNumberOfLabels];
            
            //connect c2=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                
                if(currentLabel==0)
                {
                    //c2.rs=c2.st.executeQuery("select b.asn from basic b, payment p where b.asn=p.asn and (b.dist='By Post' or b.dist='By Hand') and ( p.endm>"+(m1-1)+" and p.endy="+y1+")  and (p.endm <"+(m2+1)+" and p.endy="+y2+") order by p.endm asc, p.endy asc, b.subnos, b.subno");
                    sqlQuery = "select b.asn from basic b, payment p where b.asn=p.asn "
                            + "and (b.dist='By Post' or b.dist='By Hand') "
                            + "and ( p.endm>"+(m1-1)+" and p.endy="+y1+")  "
                            + "and (p.endm <"+(m2+1)+" and p.endy="+y2+") "
                            + "order by p.endm asc, p.endy asc, b.subnos, b.subno";
                    
                    newSqlQuery = "select asn from subscribers_primary_details where "
                            + "(distribution_type = 'By Post' or distribution_type ='By Hand') and "
                            + "ending_period > '"+dateStart+"' and ending_period < '"+dateEnd+"'"
                            + "order by ending_period asc, subscription_code, subscription_number";
                    
                    c1.rs=c1.st.executeQuery(newSqlQuery);
                    while(c1.rs.next())
                    {
                        if(currentLabel%(linesPerPage/2)==0 && currentLabel<totalNumberOfLabels)
                        {
                            asn[currentLabel]=0;
                            
                            currentLabel++;
                            
                        }
                        if(currentLabel%(linesPerPage/2)==1 && currentLabel<totalNumberOfLabels)
                        {
                            asn[currentLabel]=0;
                            
                            currentLabel++;
                        }
                        if(currentLabel%(linesPerPage/2)==2 && currentLabel<totalNumberOfLabels)
                        {
                            asn[currentLabel]=0;
                            
                            currentLabel++;
                        }
                        if(currentLabel%(linesPerPage/2)>2 && currentLabel<totalNumberOfLabels)
                        {
                            asn[currentLabel]=c1.rs.getInt(1);
                            
                            currentLabel++;
                        }
                        
                        present=currentLabel;
                        
                    }
                }
                
            }
            
            currentLabel=0;
            
            //connect c3=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                if(currentLabel<present)
                {
                    if(currentLabel%(linesPerPage/2)==0)
                    {
                        textLines[currentLabel][0]="";
                        textLines[currentLabel][1]="";
                        textLines[currentLabel][2]="";
                        textLines[currentLabel][3]="";
                        textLines[currentLabel][4]="";
                        textLines[currentLabel][5]="";
                        textLines[currentLabel][6]="";
                        textLines[currentLabel][7]="";
                        textLines[currentLabel][8]="";
                        textLines[currentLabel][9]="";
                        textLines[currentLabel][10]="";
                        textLines[currentLabel][11]="";
                        textLines[currentLabel][12]="";
                        textLines[currentLabel][13]="";
                        textLines[currentLabel][14]="";
                        
                    }
                    else if(currentLabel%(linesPerPage/2)==1)
                    {
                        //c3.rs=c3.st.executeQuery("select district, state from despcode where dno="+dno);
                        //c3.rs.next();
                        //dist=c3.rs.getString(1);
                        //state=c3.rs.getString(2);
                        
                        textLines[currentLabel][0]="";
                        textLines[currentLabel][1]="";
                        textLines[currentLabel][2]="";
                        textLines[currentLabel][3]="";
                        textLines[currentLabel][4]="";
                        textLines[currentLabel][5]="";
                        textLines[currentLabel][6]="";
                        textLines[currentLabel][7]="";
                        textLines[currentLabel][8]="";
                        textLines[currentLabel][9]="";
                        textLines[currentLabel][10]="";
                        textLines[currentLabel][11]="";
                        textLines[currentLabel][12]="";
                        textLines[currentLabel][13]="";
                        textLines[currentLabel][14]="";
                    }
                    else if(currentLabel%(linesPerPage/2)==2)
                    {
                        textLines[currentLabel][0]="ASN";
                        textLines[currentLabel][1]="Sub No";
                        textLines[currentLabel][2]="M";
                        textLines[currentLabel][3]="Year";
                        textLines[currentLabel][4]="D#";
                        textLines[currentLabel][5]="F Name";
                        textLines[currentLabel][6]="L Name";
                        textLines[currentLabel][7]="ADDRESS";
                        textLines[currentLabel][8]="";
                        textLines[currentLabel][9]="";
                        textLines[currentLabel][10]="District";
                        textLines[currentLabel][11]="State";
                        textLines[currentLabel][12]="Pin";
                        textLines[currentLabel][13]="";
                        textLines[currentLabel][14]="";
                    }
                    
                    else if((currentLabel%(linesPerPage/2))>2)
                    {
                        
                        //sqlQuery = "select b.asn, b.subnos, b.subno, p.endm, p.endy, b.dno from basic b, payment p where b.asn=p.asn and b.asn="+asn[currentLabel];
                        newSqlQuery = "select asn, subscription_code, subscription_number, "
                                + "ending_period, bulk_despatch_code from subscribers_primary_details "
                                + "where asn="+asn[currentLabel];
                        c1.rs=c1.st.executeQuery(newSqlQuery);
                        c1.rs.next();
                        
                        Date endDate = c1.rs.getDate(4);
                        int despatchCode = c1.rs.getInt(5);
                        
                        textLines[currentLabel][0]=""+c1.rs.getInt(1);
                        textLines[currentLabel][1]=""+c1.rs.getString(2)+c1.rs.getString(3);
                        textLines[currentLabel][2]=""+(endDate.getMonth()+1);
                        textLines[currentLabel][3]=""+(endDate.getYear()+1900);
                        textLines[currentLabel][4]="" + ((despatchCode > 0)?despatchCode:"");
                        
                        //if(despatchCode > 0)
                        //    textLines[currentLabel][4] = "" + despatchCode;
                    }
                }
                else if(currentLabel>=present)
                {
                    
                    textLines[currentLabel][0]="";
                    textLines[currentLabel][1]="";
                    textLines[currentLabel][2]="";
                    textLines[currentLabel][3]="";
                    textLines[currentLabel][4]="";
                    textLines[currentLabel][5]="";
                    textLines[currentLabel][6]="";
                    textLines[currentLabel][7]="";
                    textLines[currentLabel][8]="";
                    textLines[currentLabel][9]="";
                    textLines[currentLabel][10]="";
                    textLines[currentLabel][11]="";
                    textLines[currentLabel][12]="";
                    textLines[currentLabel][13]="";
                    textLines[currentLabel][14]="";
                    
                }
            }
            
            currentLabel=0;
            //connect c4=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                if(currentLabel<present)
                {
                    
                    if(currentLabel%(linesPerPage/2)>2)
                    {
                        //c4.rs=c4.st.executeQuery("select * from subdetails where asn="+asn[currentLabel]);
                        //c4.rs=c4.st.executeQuery("select * from subdetails where asn="+asn[currentLabel]);
                        c1.rs = c1.st.executeQuery("select first_name, last_name, address_line1, "
                                + "address_line2, address_line3, district, state, "
                                + "pin_code from subscribers_primary_details where asn="+asn[currentLabel]);
                        c1.rs.next();
                        String s1, s2, s3, s4, s5, s6,s7;
                        
                        s1= c1.rs.getString(1);
                        s2= c1.rs.getString(2);
                        s3= c1.rs.getString(3);
                        s4= c1.rs.getString(4);
                        s5= c1.rs.getString(5);
                        s6= c1.rs.getString(6);
                        s7= c1.rs.getString(7);
                        
                        textLines[currentLabel][5]="";
                        textLines[currentLabel][6]="";
                        textLines[currentLabel][7]="";
                        textLines[currentLabel][8]="";
                        textLines[currentLabel][9]="";
                        textLines[currentLabel][10]="";
                        textLines[currentLabel][11]="";
                        
                        if(s1!=null)
                            textLines[currentLabel][5]=s1;
                        
                        if(s2!=null)
                            textLines[currentLabel][6]=s2;
                        
                        if(s3!=null)
                            textLines[currentLabel][7]=s3;
                        
                        if(s4!=null)
                            textLines[currentLabel][8]=s4;
                        
                        if(s5!=null)
                            textLines[currentLabel][9]=s5;
                        
                        if(s6!=null)
                            textLines[currentLabel][10]=s6;
                        
                        if(s7!=null)
                            textLines[currentLabel][11]=s7;
                        
                        textLines[currentLabel][12]="";
                        int c=Integer.parseInt(c1.rs.getString(8));
                        if(c>0)
                        {
                            textLines[currentLabel][12]+=c;
                        }
                    }
                }
            }
            //System.out.println(" : "+textLines[currentLabel-1][1]);
            
            
            
            //connect c5=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                if(currentLabel<present)
                {
                    
                    if(currentLabel%(linesPerPage/2)>2)
                    {
                        //c1.rs=c1.st.executeQuery("select phone, email from otherdet where asn="+asn[currentLabel]);
                        c1.rs=c1.st.executeQuery("select phone_number, email from subscribers_primary_details where asn="+asn[currentLabel]);
                        c1.rs.next();
                        String s1, s2;
                        
                        s1= c1.rs.getString(1);
                        s2= c1.rs.getString(2);
                        
                        textLines[currentLabel][13]="";
                        textLines[currentLabel][14]="";
                        
                        if(s1!=null && s1.length()>6)
                        {
                            textLines[currentLabel][13]="(T No.-"+s1+")";
                        }
                        
                        
                        if(s2!=null && s2.length()>5)
                        {
                            textLines[currentLabel][14]="(e-id:"+s2+")";textLines[currentLabel][14]="(e-id:"+s2+")";
                        }
                        
                        
                    }
                }
            }
            c1.closeAll();
            
            currentLabel=0;
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR"+e,"ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }
    
    public int print(Graphics g, PageFormat pf, int pageIndex)
    {
        Font f1=new Font("SERIF", Font.PLAIN, 7);
        FontMetrics metric=g.getFontMetrics(f1);
        int lineHeight=metric.getHeight();
        
        int a[]=new int[13];
        
        //asn , subno, endm , endy, dno, fname, lname, add1, add2, add3, dist, state, pin
        
        a[1]=metric.stringWidth("99999 ");
        a[2]=15+a[1]+metric.stringWidth("DDDDDDD");		//after asn
        a[3]=10+a[2]+metric.stringWidth("DD");				//
        a[4]=10+a[3]+metric.stringWidth("9999 ");
        a[5]=10+a[4]+metric.stringWidth("99 ");
        
        a[6]=10+a[5]+metric.stringWidth("AMIT AMIT AMITPPDD");
        a[7]=10+a[6]+metric.stringWidth("PAUL PAUL PAULPDDD");
        a[8]=10+a[7]+metric.stringWidth("GURU HARKISHAN NAGAR GURU HARKISDDD");
        a[9]=10+a[8]+metric.stringWidth("GURU HARKISHAN NAGAR GURU HARKISDDD");
        
        a[10]=10+a[9]+metric.stringWidth("NEW DELHI NEW DELHI NDD ");
        a[11]=10+a[10]+metric.stringWidth("MAH ");
        
        
        if(pageBreak==null)
        {
            
            linesPerPage=(int)(pf.getImageableHeight()/lineHeight);
            initLines();
            int numBreaks=(numLines/linesPerPage);
            pageBreak=new int[numBreaks];
            for(int b=0;b<numBreaks;b++)
            {
                pageBreak[b]=(b+1)*linesPerPage;
            }
        }
        
        if(pageIndex > pageBreak.length)
        {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        int y=0;
        
        g.setFont(new Font("SERIF", Font.PLAIN, 8));
        
        
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
            
            g.drawLine(3,3*lineHeight,3,(end-start)*lineHeight);		//vertical lines
            g.drawLine(a[1]+10,3*lineHeight,a[1]+10,(end-start)*lineHeight);
            g.drawLine(a[2]-2,3*lineHeight,a[2]-2,(end-start)*lineHeight);
            g.drawLine(a[3]-2,3*lineHeight,a[3]-2,(end-start)*lineHeight);
            //g.drawLine(a[4]-2,3*lineHeight,a[4]-2,(end-start)*lineHeight);
            g.drawLine(a[5]-7,3*lineHeight,a[5]-7,(end-start)*lineHeight);
            //g.drawLine(a[6]-2,3*lineHeight,a[6]-2,(end-start)*lineHeight);
            g.drawLine(a[7]-2,3*lineHeight,a[7]-2,(end-start)*lineHeight);
            g.drawLine(a[9]-2,3*lineHeight,a[9]-2,(end-start)*lineHeight);
            g.drawLine(a[10]-2,3*lineHeight,a[10]-2,(end-start)*lineHeight);
            g.drawLine(a[11]-2,3*lineHeight,a[11]-2,(end-start)*lineHeight);
            g.drawLine((int)pf.getImageableWidth(),3*lineHeight,(int)pf.getImageableWidth(),(end-start)*lineHeight);
            
            for(int line=start; line<end; line+=2)
            {
                
                if(currentLabel%(linesPerPage/2)==1)
                {
                    g.setFont(new Font("SERIF", Font.BOLD, 10));
                    g.drawString("LIST OF REMINDERS OF SAT-SANDESH MEMBERS ",(int)pf.getWidth()/2-360,y);
                    g.drawString("( - "+(pageIndex+1)+" - )", (int)pf.getWidth()/2+270,y);
                    g.drawString("("+SamsUtilities.getCurrentDateString()+")  (TR: "+NumberOfRecords+")",(int)pf.getWidth()/2+310 , y);
                }
                
                
                g.setFont(new Font("SERIF", Font.PLAIN, 8));
                if(currentLabel%(linesPerPage/2)>1)
                    g.drawLine(3,y+lineHeight,(int)pf.getWidth()+1,y+lineHeight); //horizontal lines
                
                g.drawString(" "+textLines[currentLabel][0], 5, y);
                
                g.drawString(" "+textLines[currentLabel][1], 13+a[1], y);
                
                g.drawString(" "+textLines[currentLabel][2], a[2], y);
                
                g.drawString(" "+textLines[currentLabel][3], a[3], y);
                
                //g.drawString(" "+textLines[currentLabel][4], a[4], y);
                
                g.drawString(" "+textLines[currentLabel][5], a[5]-5, y);
                
                g.drawString(" "+textLines[currentLabel][6], a[6], y);
                
                g.drawString(" "+textLines[currentLabel][7], a[7], y);
                
                g.drawString(" "+textLines[currentLabel][8], a[8], y);
                
                g.drawString(" "+textLines[currentLabel][9], a[7], y+lineHeight-2);
                
                g.drawString(" "+textLines[currentLabel][10], a[9], y);
                
                g.drawString(" "+textLines[currentLabel][13], a[9], y+lineHeight-2);
                
                g.drawString(" "+textLines[currentLabel][11], a[10], y);
                
                g.drawString(" "+textLines[currentLabel][12], a[11], y);
                
                y+=2*lineHeight;
                
                currentLabel++;
            }
            
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
    */
    
     void initAsnSet()
    {
        connect dbConnection=new connect();
        try{
            String query;
            String dateStart, dateEnd;
            Date referenceStartDate = new Date((y1-1900),m1-1,28);
            Date referenceEndDate = new Date((y2-1900),m2-1,1);
            {
                Calendar c = Calendar.getInstance();
                c.setTime(referenceStartDate);
                c.add(Calendar.MONTH, -1);
                //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                dateStart = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
                c.setTime(referenceEndDate);
                c.add(Calendar.MONTH, 1);
                //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                dateEnd = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
            }
            
             query = "select count(asn) from subscribers_primary_details where "
                    + "(distribution_type = 'By Post' or distribution_type ='By Hand') and "
                    + "ending_period > '"+dateStart+"' and ending_period < '"+dateEnd+"' and subscription_code not in ('NA')";
            dbConnection.rs=dbConnection.st.executeQuery(query);
            if(dbConnection.rs.next()) x = dbConnection.rs.getInt(1);
            
            asn=new int[x];            
            query = "select asn from subscribers_primary_details where "
                            + "(distribution_type = 'By Post' or distribution_type ='By Hand') and "
                            + "ending_period > '"+dateStart+"' and ending_period < '"+dateEnd+"'  and subscription_code not in ('NA')"
                            + "order by ending_period asc, subscription_code, subscription_number";

            dbConnection.rs=dbConnection.st.executeQuery(query);
            int idx = 0;
            while(dbConnection.rs.next())
            {
                asn[idx++] = dbConnection.rs.getInt(1);
                //System.out.println(asn[idx-1]);
            }
            setAsnSet(asn);
            String header_ = "LIST OF REMINDERS OF SAT-SANDESH MEMBERS ";
            setHeader(header_);
            dbConnection.closeAll();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            dbConnection.closeAll();
        }
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==printButton)
        {
            printButton.setEnabled(false);
            PrinterJob job=PrinterJob.getPrinterJob();
            job.setPrintable(this);
            
            PageFormat pf = job.defaultPage();
            pf.setOrientation(PageFormat.LANDSCAPE);
            pf = job.pageDialog(pf);
            
            boolean ok=job.printDialog();
            
            if(ok)
            {
                try
                {
                    initAsnSet();
                    if(asnSet.length == 0) {
                        JOptionPane.showMessageDialog(null, "No records found","ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    job.print();
                }
                catch(PrinterException pe)
                {
                    JOptionPane.showMessageDialog(null, "ERROR"+pe.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                printButton.setEnabled(false);
                
            }
            else
                printButton.setEnabled(true);
        }
        
        if(ae.getSource()==back)
        {
            f.dispose();
            new remindersub();
        }
        
    }
    
}