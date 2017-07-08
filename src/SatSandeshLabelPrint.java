
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class SatSandeshLabelPrint implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new SatSandeshLabelPrint(7, 2017, "Hindi");
    }
    
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int x=0;
    int x1=0;
    int currentLabel=0;
    int chk=0;
    int m1, y1;
    JButton b,preview, back;
    JFrame f;
    String lang;
    
    
    public SatSandeshLabelPrint(int m, int y, String lang1)
    {
        m1=m;
        y1=y;
        lang=lang1;
        
        f= new JFrame("Print Labels");
        try
        {
            f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            System.out.println(cnf);
            new except(cnf, this.getClass().toString());
        }
        
        
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(450,100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e){System.exit(0);}});
        b=new JButton("Print labels");
        b.addActionListener(this);
        
        preview=new JButton("Print Preview");
        preview.addActionListener(this);
        
        
        back=new JButton("Back");
        back.addActionListener(this);
        f.add(b);
        b.setBounds(10,10,100,25);
        b.setMnemonic('P');
        
        //f.add(preview);
        preview.setBounds(150,10,100,25);
        preview.setMnemonic('V');
        
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(290,10,100,25);
        //f.pack();
        
        
        
        
    }
    
    public void initLines()
    {
        try
        {
            
            connect c1=new connect();
            String sqlQuery = "select count(b.asn) from basic b, payment p where b.asn=p.asn and b.dist='By Post' "
                    + "and b.lang='"+lang+"' and (p.asn) in (select asn from payment where "
                    + "(endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") "
                    + "and b.status not in ('STOPPED')";
            //System.out.println(sqlQuery);
            String date;
            Date referenceDate = new Date((y1-1900),m1-1,28);
            {
                Calendar c = Calendar.getInstance();
                c.setTime(referenceDate);
                c.add(Calendar.MONTH, -1);
                //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                date = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
            }
            
            String newSqlQuery = "select count(asn) from subscribers_primary_details where distribution_type = 'By Post' "
                    + "and language = '"+lang+"' and ending_period > '"+date+"' and membership_status not in ('STOPPED')";
            
            //System.out.println(newSqlQuery);
            /*c1.rs=c1.st.executeQuery(sqlQuery);
            if(c1.rs.next())
                x=c1.rs.getInt(1);  
            */
            c1.rs=c1.st.executeQuery(newSqlQuery);
            if(c1.rs.next()){
                //System.out.println("Orig: " +x+ " <--> new: " + c1.rs.getInt(1));  
                x = c1.rs.getInt(1);
            }
            
            numLines=x*9;
            
            textLines=new String[numLines][7];
            asn=new int[x];
            
            //connect c2=new connect();
            sqlQuery = "select b.asn from basic b, payment p where b.asn=p.asn "
                    + "and b.dist='By Post' and b.lang='"+lang+"' "
                    + "and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") "
                    + "and b.status not in ('STOPPED') order by subnos, subno";
            
            newSqlQuery = "select asn from subscribers_primary_details where distribution_type = 'By Post' "
                    + "and language = '"+lang+"' and ending_period > '"+date+"' and membership_status not in ('STOPPED')"
                    + "order by subscription_code, subscription_number";
            c1.rs=c1.st.executeQuery(newSqlQuery);
            while(c1.rs.next())
            {
                asn[currentLabel]=c1.rs.getInt(1);
                currentLabel++;
            }
            
            currentLabel=0;
            //connect c10=new connect();
            for(currentLabel=0;currentLabel<x;currentLabel++)
            {
                //sqlQuery = "select * from basic where asn="+asn[currentLabel];
                newSqlQuery = "select subscription_code, subscription_number, receipt_number from subscribers_primary_details where asn = " + asn[currentLabel];
                c1.rs=c1.st.executeQuery(newSqlQuery);
                c1.rs.next();
                //textLines[currentLabel][0]="SUB # "+ c1.rs.getString(2)+" "+ c1.rs.getString(3)+" / "+c1.rs.getInt(5)+" / ";
                textLines[currentLabel][0]="SUB # "+ c1.rs.getString(1)+" "+ c1.rs.getInt(2)+" / "+c1.rs.getInt(3)+" / ";
            }
            
            currentLabel=0;
            
            //connect c3=new connect();
            for(currentLabel=0;currentLabel<x;currentLabel++)
            {
                //c1.rs=c1.st.executeQuery("select * from payment where asn="+asn[currentLabel]);
                c1.rs=c1.st.executeQuery("select ending_period from subscribers_primary_details where asn="+asn[currentLabel]);
                c1.rs.next();
                Date endDate = c1.rs.getDate(1);
                textLines[currentLabel][0] += m1 + "   " + (endDate.getMonth()+1) + "/" + (endDate.getYear()+1900);
            }
            
            currentLabel=0;
            //connect c4=new connect();
            for(currentLabel=0;currentLabel<x;currentLabel++)
            {
                
                //c1.rs = c1.st.executeQuery("select * from subdetails where asn="+asn[currentLabel]);
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
                
                
                
                int c=Integer.parseInt(c1.rs.getString(8));    //pin
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
            
            for(int line=start;line<end && currentLabel<x; line+=9)
            {
                y+=(2*lineHeight);
                g.drawString(""+textLines[currentLabel][0], 30, y);
                g.drawString(textLines[currentLabel][1], 30, y+lineHeight);
                g.drawString(textLines[currentLabel][2], 30, y+2*lineHeight);
                g.drawString(textLines[currentLabel][3], 30, y+3*lineHeight);
                g.drawString(textLines[currentLabel][4], 30, y+4*lineHeight);
                g.drawString(textLines[currentLabel][5], 30, y+5*lineHeight);
                g.drawString(textLines[currentLabel][6], 30, y+6*lineHeight);
                
                if((currentLabel+1)<x)
                {
                    g.drawString(textLines[currentLabel+1][0], 215, y);
                    g.drawString(textLines[currentLabel+1][1], 215, y+lineHeight);
                    g.drawString(textLines[currentLabel+1][2], 215, y+2*lineHeight);
                    g.drawString(textLines[currentLabel+1][3], 215, y+3*lineHeight);
                    g.drawString(textLines[currentLabel+1][4], 215, y+4*lineHeight);
                    g.drawString(textLines[currentLabel+1][5], 215, y+5*lineHeight);
                    g.drawString(textLines[currentLabel+1][6], 215, y+6*lineHeight);
                }
                if((currentLabel+2)<x)
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
            
            for(int line=start;line<end && currentLabel<x; line+=8)
            {
                
            }
            
        }
        chk++;
        
        return PAGE_EXISTS;
    }
    
    Graphics g1;
    public Graphics initGraphics()
    {
        int y=0;
        
        Font f1=new Font("SERIF", Font.PLAIN, 8);
        //FontMetrics metric=.getFontMetrics(f1);
        int lineHeight=10;             //metric.getHeight();
        int start=0;
        int end=numLines;
        initLines();
        currentLabel=0;
        for(int line=start;line<end && currentLabel<x; line+=9)
        {
            y+=(2*lineHeight);
            g1.drawString(""+textLines[currentLabel][0], 30, y);
            g1.drawString(textLines[currentLabel][1], 30, y+lineHeight);
            g1.drawString(textLines[currentLabel][2], 30, y+2*lineHeight);
            g1.drawString(textLines[currentLabel][3], 30, y+3*lineHeight);
            g1.drawString(textLines[currentLabel][4], 30, y+4*lineHeight);
            g1.drawString(textLines[currentLabel][5], 30, y+5*lineHeight);
            g1.drawString(textLines[currentLabel][6], 30, y+6*lineHeight);
            
            if((currentLabel+1)<x)
            {
                g1.drawString(textLines[currentLabel+1][0], 215, y);
                g1.drawString(textLines[currentLabel+1][1], 215, y+lineHeight);
                g1.drawString(textLines[currentLabel+1][2], 215, y+2*lineHeight);
                g1.drawString(textLines[currentLabel+1][3], 215, y+3*lineHeight);
                g1.drawString(textLines[currentLabel+1][4], 215, y+4*lineHeight);
                g1.drawString(textLines[currentLabel+1][5], 215, y+5*lineHeight);
                g1.drawString(textLines[currentLabel+1][6], 215, y+6*lineHeight);
            }
            if((currentLabel+2)<x)
            {
                g1.drawString(textLines[currentLabel+2][0], 415, y);
                g1.drawString(textLines[currentLabel+2][1], 415, y+lineHeight);
                g1.drawString(textLines[currentLabel+2][2], 415, y+2*lineHeight);
                g1.drawString(textLines[currentLabel+2][3], 415, y+3*lineHeight);
                g1.drawString(textLines[currentLabel+2][4], 415, y+4*lineHeight);
                g1.drawString(textLines[currentLabel+2][5], 415, y+5*lineHeight);
                g1.drawString(textLines[currentLabel+2][6], 415, y+6*lineHeight);
            }
            y+=7*lineHeight;
            currentLabel+=3;
        }
        
        return g1;
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
        
        if(ae.getSource()==b)
        {
            b.setEnabled(false);
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
            b.setEnabled(true);
        }
        
        if(ae.getSource()==preview)
        {
            Graphics g=initGraphics();
            new Preview(g);
            f.dispose();
        }
        
        if(ae.getSource()==back)
        {
            new labelsubno();
            f.dispose();
        }
        
    }
    
}