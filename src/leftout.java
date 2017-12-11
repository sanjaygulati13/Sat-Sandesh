 /**
  * @(#)leftout.java
  * @author
  * @version 1.00 2009/10/25
  */
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.print.PrinterJob;
import java.util.Calendar;
import javax.swing.*;

public class leftout extends JFrame implements ActionListener, Printable
{
    public static void main(String arrgs[])
    {
        new leftout();
        
    }
    
    
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int x=0;
    int x1=0;
    int i=0;
    int chk=0;
    JFrame f;
    
    JLabel l1, l2;
    
    JTextField t1;
    
    JComboBox seriesNameDropDown;
    
    JButton b1, b2, resetButton;
    int m1;
    
    public leftout()
    {
        try
        {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            System.out.println(cnf);
        }
        setTitle("Print Leftouts");
        setLocation(10,10);
        setSize(600,300);
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1=new JLabel("Enter Rcpt No");
        l1.setBounds(30,30,100,20);
        add(l1);
        
        seriesNameDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        seriesNameDropDown.setBounds(30, 70, 90, 20);
        this.add(seriesNameDropDown);
        
        t1=new JTextField(100);
        t1.setBounds(140,30,430,80);
        add(t1);
        
        b1=new JButton("Print Label");
        b1.setBounds(60,120,110,20);
        b1.addActionListener(this);
        add(b1);
        b1.setMnemonic('p');
        
        b2=new JButton("Back");
        b2.setBounds(350,120,120,20);
        b2.addActionListener(this);
        add(b2);
        b2.setMnemonic('b');
        
        resetButton = new JButton("Reset");
        resetButton.setBounds(190,120,120,20);
        resetButton.addActionListener(this);
        add(resetButton);
        resetButton.setMnemonic('r');
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==b1)
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
        
        if(ae.getSource()==b2)
        {
            this.dispose();
            new sams();
        }
        if(ae.getSource()==resetButton)
        {
            t1.setText("");
            seriesNameDropDown.setSelectedIndex(0);
        }
        
    }
    
    public void initLines()
    {
        try
        {
            String rcpts=t1.getText();
            rcpts=rcpts.trim();
            String[] rcpt=rcpts.split(",");
            
            String seriesName = (String)seriesNameDropDown.getSelectedItem();
            if(seriesName.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please select series", "Please select series", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            asn=new int[rcpt.length];
            int i1=0;
            connect c=new connect();
            while(i1 < rcpt.length)
            {
                c.rs=c.st.executeQuery("select asn from subscribers_primary_details where series_name = '"+seriesName+"' and receipt_number="+rcpt[i1]);
                //c.rs=c.st.executeQuery("select asn from basic where series_name = '"+seriesName+"' and rcpt="+rcpt[i1]);
                while(c.rs.next())
                {
                    asn[i1]=c.rs.getInt(1);
                    //System.out.println(asn[i1]);
                }
                i1++;
            }
            
            /*connect c1=new connect();
            * c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p where b.asn=p.asn and b.dist='By Post')");
            * while(c1.rs.next())
            * {
            * x=c1.rs.getInt(1);
            *
            * }*/
            c.st.close();
            c.con.close();
            x=rcpt.length;
            numLines=x*9;
            
            textLines=new String[numLines][7];
            
            /*
            * connect c2=new connect();
            * c2.rs=c2.st.executeQuery("select b.asn from basic b, payment p where b.asn=p.asn and b.dist='By Post' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") order by subnos, subno");
            * while(c2.rs.next())
            * {
            * asn[i1]=c2.rs.getInt(1);
            *
            * i1++;
            * }
            */
            //i1=0;
            connect c10=new connect();
            for(i1=0;i1<x;i1++)
            {
                
                c10.rs=c10.st.executeQuery("select subscription_code, subscription_number, receipt_number from subscribers_primary_details where asn="+asn[i1]);
                //c10.rs=c10.st.executeQuery("select subnos,subno,rcpt from basic where asn="+asn[i1]);
                c10.rs.next();
                textLines[i1][0]="SUB # "+ c10.rs.getString(1)+" "+ c10.rs.getString(2)+" / "+c10.rs.getInt(3)+" / ";
            }
            c10.closeAll();
            
            
            //c2.st.close();
            //c2.con.close();
            
            //i1=0;
            
            Calendar cal = Calendar.getInstance();
            m1=cal.get(Calendar.MONTH)+1;
            
            
            
            
            connect c3=new connect();
            for(i1=0;i1<x;i1++)
            {
                c3.rs=c3.st.executeQuery("select ending_period from subscribers_primary_details where asn="+asn[i1]);
                //c3.rs=c3.st.executeQuery("select * from payment where asn="+asn[i1]);
                c3.rs.next();
                java.util.Date endDate = c3.rs.getDate(1);
                textLines[i1][0] += m1+"   "+(endDate.getMonth()+1)+"/"+(endDate.getYear()+1900);
                //textLines[i1][0]+=m1+"   "+c3.rs.getInt(10)+"/"+c3.rs.getInt(11);
            }
            c3.closeAll();
            
            //i1=0;
            connect c4=new connect();
            for(i1=0;i1<x;i1++)
            {

                c4.rs=c4.st.executeQuery("select first_name,last_name,address_line1,address_line2,address_line3,district,state,pin_code from subscribers_primary_details where asn="+asn[i1]);
                //c4.rs=c4.st.executeQuery("select fname,lname,add1,add2,add3,dist,state,pin from subdetails where asn="+asn[i1]);
                c4.rs.next();
                
                String s1, s2, s3, s4, s5,s6, s7;
                
                s1=c4.rs.getString(1);
                s2=c4.rs.getString(2);
                s3=c4.rs.getString(3);
                s4=c4.rs.getString(4);
                s5=c4.rs.getString(5);
                s6=c4.rs.getString(6);
                s7=c4.rs.getString(7);
                
                textLines[i1][1]="";
                textLines[i1][2]="";
                textLines[i1][3]="";
                textLines[i1][4]="";
                textLines[i1][5]="";
                textLines[i1][6]="";
                
                
                
                if(s1!=null)
                    textLines[i1][1]= s1+" ";
                
                if(s2!=null)
                    textLines[i1][1]+=s2;
                
                if(s3!=null)
                    textLines[i1][2]= s3;
                
                if(s4!=null)
                    textLines[i1][3]= s4;
                
                if(s5!=null)
                    textLines[i1][4]= s5;
                
                if(s6!=null)
                    textLines[i1][5]= s6;
                
                if(s7!=null)
                    textLines[i1][6]=s7;
                
                
                
                int c1=Integer.parseInt(c4.rs.getString(8));
                if(c1>0)
                {
                    textLines[i1][6]+=" - "+c1;
                }
            }
            
            c4.closeAll();
            
            //i1=0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        
    }
    
    @Override
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
        
        
        int start; //=0;
        if(pageIndex==0)
            start=0;
        else
            start=pageBreak[pageIndex-1];
        
        int end; //=0;
        
        if(pageIndex==pageBreak.length)
            end=numLines;
        else
            end=pageBreak[pageIndex];
        
        if(chk%2==1 && (end-start)!=lineHeight)
        {
            
            for(int line=start;line<end && i<x; line+=9)
            {
                y+=(2*lineHeight);
                g.drawString(""+textLines[i][0], 30, y);
                g.drawString(textLines[i][1], 30, y+lineHeight);
                g.drawString(textLines[i][2], 30, y+2*lineHeight);
                g.drawString(textLines[i][3], 30, y+3*lineHeight);
                g.drawString(textLines[i][4], 30, y+4*lineHeight);
                g.drawString(textLines[i][5], 30, y+5*lineHeight);
                g.drawString(textLines[i][6], 30, y+6*lineHeight);
                
                if((i+1)<x)
                {
                    g.drawString(textLines[i+1][0], 215, y);
                    g.drawString(textLines[i+1][1], 215, y+lineHeight);
                    g.drawString(textLines[i+1][2], 215, y+2*lineHeight);
                    g.drawString(textLines[i+1][3], 215, y+3*lineHeight);
                    g.drawString(textLines[i+1][4], 215, y+4*lineHeight);
                    g.drawString(textLines[i+1][5], 215, y+5*lineHeight);
                    g.drawString(textLines[i+1][6], 215, y+6*lineHeight);
                }
                if((i+2)<x)
                {
                    g.drawString(textLines[i+2][0], 415, y);
                    g.drawString(textLines[i+2][1], 415, y+lineHeight);
                    g.drawString(textLines[i+2][2], 415, y+2*lineHeight);
                    g.drawString(textLines[i+2][3], 415, y+3*lineHeight);
                    g.drawString(textLines[i+2][4], 415, y+4*lineHeight);
                    g.drawString(textLines[i+2][5], 415, y+5*lineHeight);
                    g.drawString(textLines[i+2][6], 415, y+6*lineHeight);
                }
                y+=7*lineHeight;
                i+=3;
                
            }
            
        }
        else
        {
            
            for(int line=start;line<end && i<x; line+=8)
            {
                
            }
            
        }
        chk++;
        
        return PAGE_EXISTS;
    }
    
}