import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import javax.swing.*;

public class SatSandeshDespatchRemindersLabel implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new SatSandeshDespatchRemindersLabel(1,2009,12,2009);
    }
    
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int totalNumberOfLabels=0;
    int x1=0;
    int currentLabel=0;
    int chk=0;
    int m1, y1, m2, y2;
    JButton b, back;
    JFrame f;
    
    
    public SatSandeshDespatchRemindersLabel(int m, int y, int m3, int y3)
    {
        m1=m;
        y1=y;
        m2=m3;
        y2=y3;
        
        try
        {
            f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            
            UIManager.setLookAndFeel(cn); // Use the native L&F
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
        b=new JButton("Print Reminders");
        b.addActionListener(this);
        back=new JButton("Back");
        back.addActionListener(this);
        f.add(b);
        b.setBounds(10,10,100,20);
        b.setMnemonic('P');
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(150,10,100,20);
        //f.pack();
        
        
    }
    
    public void initLines()
    {
        try
        {
            
            connect c1=new connect();
            String sqlQuery = "select count(b.asn) from basic b, payment p where b.asn=p.asn  "
                    + "and (b.dist='By Post' or b.dist='By Hand') and ( p.endm>"+(m1-1)+" "
                    + "and p.endy="+y1+")  and (p.endm <"+(m2+1)+" and p.endy="+y2+")";
            
            
            String dateStart, dateEnd;
            java.util.Date referenceStartDate = new java.util.Date((y1-1900),m1-1,28);
            java.util.Date referenceEndDate = new java.util.Date((y2-1900),m2-1,1);
            {
                Calendar c = Calendar.getInstance();
                c.setTime(referenceStartDate);
                c.add(Calendar.MONTH, -1);
                System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                dateStart = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
                c.setTime(referenceEndDate);
                c.add(Calendar.MONTH, 1);
                System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                dateEnd = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
            }
            
            String newSqlQuery = "select count(asn) from subscribers_primary_details where "
                    + "(distribution_type = 'By Post' or distribution_type ='By Hand') and "
                    + "ending_period > '"+dateStart+"' and ending_period < '"+dateEnd+"'";
            
            System.out.println(sqlQuery);
            System.out.println(newSqlQuery);
            
            c1.rs=c1.st.executeQuery(newSqlQuery);
            if(c1.rs.next())
                totalNumberOfLabels=c1.rs.getInt(1);
            
            
            c1.rs=c1.st.executeQuery(newSqlQuery);
            if(c1.rs.next()){
                System.out.println("Orig: " +totalNumberOfLabels+ " <--> new: " + c1.rs.getInt(1));
                totalNumberOfLabels = c1.rs.getInt(1);
            }
            
            
            
            
            numLines=totalNumberOfLabels*9;
            
            textLines=new String[numLines][7];
            asn=new int[totalNumberOfLabels];
            
            //connect c1=new connect();
            //c1.rs=c1.st.executeQuery("select b.asn from basic b, payment p where b.asn=p.asn and (b.dist='By Post' or b.dist='By Hand') and ( p.endm>"+(m1-1)+" and p.endy="+y1+")  and (p.endm <"+(m2+1)+" and p.endy="+y2+") order by p.endm asc, p.endy asc, b.subnos, b.subno");
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
                asn[currentLabel]=c1.rs.getInt(1);
                currentLabel++;
            }
            
            currentLabel=0;
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                //connect c10=new connect();
                //c10.rs=c10.st.executeQuery("select * from basic where asn="+asn[currentLabel]);
                c1.rs=c1.st.executeQuery("select subscription_code, subscription_number from subscribers_primary_details where asn="+asn[currentLabel]);
                c1.rs.next();
                textLines[currentLabel][0]="SUB # "+ c1.rs.getString(1)+" "+ c1.rs.getString(2)+" / REMI ";
            }
            
            
            currentLabel=0;
            
            //connect c1=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                //c1.rs=c1.st.executeQuery("select * from payment where asn="+asn[currentLabel]);
                c1.rs=c1.st.executeQuery("select ending_period from subscribers_primary_details where asn="+asn[currentLabel]);
                c1.rs.next();
                java.util.Date endDate = c1.rs.getDate(1);
                textLines[currentLabel][0] += "   " + (endDate.getMonth()+1) + "/" + (endDate.getYear()+1900);
                //textLines[currentLabel][0]+="   "+c1.rs.getInt(10)+"/"+c1.rs.getInt(11);
            }
            
            currentLabel=0;
            //connect c1=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                
                //c1.rs=c1.st.executeQuery("select * from subdetails where asn="+asn[currentLabel]);
                c1.rs = c1.st.executeQuery("select first_name, last_name, address_line1, address_line2, address_line3, district, state, pin_code from subscribers_primary_details where asn="+asn[currentLabel]);
                c1.rs.next();
                
                String s1, s2, s3, s4, s5,s6, s7;
                
                s1=c1.rs.getString(1);  //fname
                s2=c1.rs.getString(2);  //lname
                s3=c1.rs.getString(3);  //add1
                s4=c1.rs.getString(4);  //add2
                s5=c1.rs.getString(5);  //add3
                s6=c1.rs.getString(6);  //dist
                s7=c1.rs.getString(7);  //state
                
                textLines[currentLabel][1]="";
                textLines[currentLabel][2]="";
                textLines[currentLabel][3]="";
                textLines[currentLabel][4]="";
                textLines[currentLabel][5]="";
                textLines[currentLabel][6]="";
                
                
                
                if(s1!=null)
                    textLines[currentLabel][1]= s1+" ";
                
                if(s2!=null)
                    textLines[currentLabel][1]+=s2;
                
                if(s3!=null)
                    textLines[currentLabel][2]= s3;
                
                if(s4!=null)
                    textLines[currentLabel][3]= s4;
                
                if(s5!=null)
                    textLines[currentLabel][4]= s5;
                
                if(s6!=null)
                    textLines[currentLabel][5]= s6;
                
                if(s7!=null)
                    textLines[currentLabel][6]=s7;
                
                
                
                int c=Integer.parseInt(c1.rs.getString(8));
                if(c>0)
                {
                    textLines[currentLabel][6]+=" - "+c;
                }
            }
            c1.closeAll();
            currentLabel=0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        
    }
    
    public int print(Graphics g, PageFormat pf, int pageIndex)
    {
        Font f1=new Font("SERIF", Font.PLAIN, 8);
        FontMetrics metric=g.getFontMetrics(f1);
        int lineHeight=metric.getHeight();
        
        if(pageBreak==null)
        {
            initLines();
            int s=(numLines%3);
            if(s>0)
                numLines=numLines+(3-s);
            numLines/=3;
            
            
            int linesPerPage=(int)(pf.getImageableHeight()/lineHeight);
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
        
        g.setFont( new Font("SERIF", Font.PLAIN, 9));
        
        
        int start=0;
        if(pageIndex==0)
            start=0;
        else
            start=pageBreak[pageIndex-1];
        
        int end=0;
        
        if(pageIndex==pageBreak.length)
            end=numLines;
        else
            end=pageBreak[pageIndex];
        
        if(chk%2==1 && (end-start)!=lineHeight)
        {
            
            for(int line=start;line<end && currentLabel<totalNumberOfLabels; line+=9)
            {
                y+=(2*lineHeight);
                g.drawString(""+textLines[currentLabel][0], 30, y);
                g.drawString(textLines[currentLabel][1], 30, y+lineHeight);
                g.drawString(textLines[currentLabel][2], 30, y+2*lineHeight);
                g.drawString(textLines[currentLabel][3], 30, y+3*lineHeight);
                g.drawString(textLines[currentLabel][4], 30, y+4*lineHeight);
                g.drawString(textLines[currentLabel][5], 30, y+5*lineHeight);
                g.drawString(textLines[currentLabel][6], 30, y+6*lineHeight);
                
                if((currentLabel+1)<totalNumberOfLabels)
                {
                    g.drawString(textLines[currentLabel+1][0], 225, y);
                    g.drawString(textLines[currentLabel+1][1], 225, y+lineHeight);
                    g.drawString(textLines[currentLabel+1][2], 225, y+2*lineHeight);
                    g.drawString(textLines[currentLabel+1][3], 225, y+3*lineHeight);
                    g.drawString(textLines[currentLabel+1][4], 225, y+4*lineHeight);
                    g.drawString(textLines[currentLabel+1][5], 225, y+5*lineHeight);
                    g.drawString(textLines[currentLabel+1][6], 225, y+6*lineHeight);
                }
                if((currentLabel+2)<totalNumberOfLabels)
                {
                    g.drawString(textLines[currentLabel+2][0], 415, y);
                    g.drawString(textLines[currentLabel+2][1], 415, y+lineHeight);
                    g.drawString(textLines[currentLabel+2][2], 415, y+2*lineHeight);
                    g.drawString(textLines[currentLabel+2][3], 415, y+3*lineHeight);
                    g.drawString(textLines[currentLabel+2][4], 415, y+4*lineHeight);
                    g.drawString(textLines[currentLabel+2][5], 415, y+5*lineHeight);
                    g.drawString(textLines[currentLabel+2][6], 415, y+6*lineHeight);
                }
                y+=7*lineHeight;
                currentLabel+=3;
                
            }
            
        }
        else
        {
            
            for(int line=start;line<end && currentLabel<totalNumberOfLabels; line+=8)
            {
                
            }
            
        }
        chk++;
        
        return PAGE_EXISTS;
    }
    
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==b)
        {
            
            PrinterJob job=PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean ok=job.printDialog();
            
            if(ok)
            {
                try
                {
                    job.print();
                }
                catch(PrinterException pe)
                {
                    System.out.println(pe);
                    pe.printStackTrace();
                    
                }
                
            }
        }
        
        if(ae.getSource()==back)
        {
            new remindersub();
            f.dispose();
        }
        
    }
    
}