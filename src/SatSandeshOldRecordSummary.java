import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

public class SatSandeshOldRecordSummary implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new SatSandeshOldRecordSummary(1);
    }
    private final static int POINTS_PER_INCH = 72;
    
    
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int x=0;
    int x1=0;
    int i=0;
    int chk=0;
    String m1;
    JButton b, back;
    JFrame f;
    int linesPerPage;
    String typeStr = "Freeze";
    public SatSandeshOldRecordSummary(int type)
    {
        switch(type)
        {
            case 1:
                typeStr = "Freeze";
                break;
            case 2:
                typeStr = "Deactive";
                break;
            case 3:
                typeStr = "Inactive";
                break;
            default:
                break;
        }
        
        //System.out.println(type);
        f= new JFrame("Print "+typeStr+" Records");
        try
        {
            f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            cnf.printStackTrace();
            System.out.println(cnf);
        }
        
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300,85);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e){System.exit(0);}});
        b=new JButton("Print List");
        b.addActionListener(this);
        back=new JButton("Back");
        back.addActionListener(this);
        f.add(b);
        b.setBounds(10,10,100,25);
        b.setMnemonic('P');
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(150,10,100,25);
        SamsUtilities.center(f);
        
    }
    
    public void initLines()
    {
        try
        {
            
            connect c1=new connect();
            //c1.rs=c1.st.executeQuery("select count(asn) from basic where status='"+typeStr+"' and subnos not in ('NA') ");
            c1.rs=c1.st.executeQuery("select count(asn) from subscribers_primary_details where membership_status='"+typeStr
                    +"' and subscription_code not in ('NA') ");
            if(c1.rs.next())
                x=c1.rs.getInt(1);
            
            //System.out.println(typeStr+" summary records : "+x);
            
            c1.closeAll();
            //JOptionPane.showMessageDialog(null, "NO RECORDS FOUND"+x ,"NO RECORDS", JOptionPane.INFORMATION_MESSAGE);
            
            if(x==0)
            {
                JOptionPane.showMessageDialog(null, "NO RECORDS FOUND" ,"NO RECORDS", JOptionPane.INFORMATION_MESSAGE);
                new sams();
                f.dispose();
            }
            
            numLines=x;
            x+=(numLines/linesPerPage)*3;
            
            
            int y=(numLines%linesPerPage);
            if(y>0)
                x+=3;
            
            numLines=x;
            
            //System.out.println("x : "+x);
            
            textLines=new String[numLines][5];
            asn=new int[x];
            
            connect c2=new connect();
            //c2.rs=c2.st.executeQuery("select b.asn from basic b, payment p, subdetails s where b.asn=p.asn and b.asn=s.asn and b.status='Freeze' and b.subnos not in ('NA')  order by p.endy, p.endm, s.fname, s.lname ");
            c2.rs=c2.st.executeQuery("select asn from subscribers_primary_details where membership_status='"+typeStr+
                    "' and subscription_code not in ('NA')  order by ending_period, first_name, last_name ");
            while(c2.rs.next())
            {
                if(i%linesPerPage==0 && i<x)
                {
                    asn[i]=0;
                    i++;
                }
                if(i%linesPerPage==1 && i<x)
                {
                    asn[i]=0;
                    
                    i++;
                }
                if(i%linesPerPage==2 && i<x)
                {
                    asn[i]=0;
                    i++;
                }
                if(i%linesPerPage>2 && i<x)
                {
                    asn[i]=c2.rs.getInt(1);
                    i++;
                }
            }
            
            c2.st.close();
            c2.con.close();
            
            
            i=0;
            connect c3=new connect();
            for(i=0;i<x;i++)
            {
                
                if(i%linesPerPage==0)
                {
                    textLines[i][0]="";
                    textLines[i][1]="";
                    textLines[i][2]="";
                    textLines[i][3]="";
                    textLines[i][4]="";
                    
                }
                else if(i%linesPerPage==1)
                {
                    textLines[i][0]="";
                    textLines[i][1]="";
                    textLines[i][2]="";
                    textLines[i][3]="";
                    textLines[i][4]="";
                    
                }
                else if(i%linesPerPage==2)
                {
                    textLines[i][0]=" ASN ";
                    textLines[i][1]=" Sub No ";
                    textLines[i][2]="  First Name  ";
                    textLines[i][3]="  Last Name   ";
                    textLines[i][4]=" Ending Period ";
                    
                }
                else if(i%linesPerPage>2)
                {
                    
                    c3.rs=c3.st.executeQuery("select asn, subscription_code, subscription_number, first_name, last_name, ending_period "
                            + "from subscribers_primary_details where asn="+asn[i]);
                    c3.rs.next();
                    textLines[i][0]=""+c3.rs.getInt(1);
                    textLines[i][1]=""+c3.rs.getString(2)+"  "+c3.rs.getString(3);
                    
                    textLines[i][2]="";
                    textLines[i][3]="";
                    
                    String s1, s2;
                    s1=c3.rs.getString(4);
                    s2=c3.rs.getString(5);
                    
                    if(s1!=null)
                        textLines[i][2]+=s1;
                    if(s2!=null)
                        textLines[i][3]+=s2;
                    
                    java.util.Date endDate = c3.rs.getDate(6);
                    textLines[i][4] = " "+(endDate.getMonth()+1)+"/"+(endDate.getYear()+1900);
                    //textLines[i][4]=" "+c3.rs.getInt(6)+"/"+c3.rs.getInt(7);
                    
                }
            }
            c3.st.close();
            c3.con.close();
            
            i=0;
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR"+e,"ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }
    
    public int print(Graphics g, PageFormat pf, int pageindex)
    {
        paper.setSize(8.27 * POINTS_PER_INCH, 11.69 * POINTS_PER_INCH);
        paper.setImageableArea(.25 * POINTS_PER_INCH, .25 * POINTS_PER_INCH,7.77 * POINTS_PER_INCH, 11.19 * POINTS_PER_INCH);
        pf.setPaper(paper);
        
        Font f1=new Font("SERIF", Font.PLAIN, 7);
        FontMetrics metric=g.getFontMetrics(f1);
        int lineHeight=metric.getHeight();
        
        //pf.setOrientation(PageFormat.LANDSCAPE);
        if(pageBreak==null)
        {
            
            linesPerPage=(int)(pf.getImageableHeight()/lineHeight);
            initLines();
            
            int numBreaks=(numLines/linesPerPage);
            //System.out.println("numLines : "+numLines);
            //numLines+=(numBreaks+1)*2;
            //System.out.println("numLines : "+numLines);
            numBreaks=(numLines/linesPerPage);
            pageBreak=new int[numBreaks];
            for(int b1=0;b1<numBreaks;b1++)
            {
                pageBreak[b1]=(b1+1)*linesPerPage;
            }
        }
        
        if(pageindex > pageBreak.length)
        {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        int y=0;
        
        g.setFont( new Font("SERIF", Font.PLAIN, 8));
        
        
        int start=0;
        if(pageindex==0)
            start=0;
        else
            start=pageBreak[pageindex-1];
        
        int end=0;
        
        if(pageindex==pageBreak.length)
            end=numLines;
        else
            end=pageBreak[pageindex];
        
        
        
        int z=(int)pf.getImageableWidth()/6;
        
        
        if(chk%2==1 && (end-start)!=lineHeight)
        {
            
            GregorianCalendar cal=new GregorianCalendar();
            int month=(cal.get(Calendar.MONTH)+1);
            int year=cal.get(Calendar.YEAR);
            
            g.setFont(new Font("Sans Serif", Font.BOLD, 9));
            if(typeStr.equals("Freeze")){
                int mf=(month-1)%12;
                //int mf=month;
                int yf=year-1;
                if(mf==0)
                    mf=12;
                if(mf==12)
                    yf--;
                g.drawString("Summary List Of Freeze Sat Sandesh Members Whose Period Has Ended Before "+mf+"-"+yf
                        , (int)pf.getWidth()/2-200,y+lineHeight-3);
            }
            else if(typeStr.equals("Deactive"))
            {
                int md=(month+6)%12;
                int yd=year;
                if(md==0)
                    md=12;
                if(md>6)
                    yd--;
                
                g.drawString("Summary List Of Deactive Sat Sandesh Members Whose Period Has Ended B/W "+(month-1)+"-"+(year-1)+" & "+(md-1)+"-"+yd
                        , (int)pf.getWidth()/2-230,y+lineHeight-3);
            }
            else if(typeStr.equals("Inactive"))
            {
                int mi=(month+6)%12;
                int yi=year;
                if(mi==0)
                    mi=12;
                if(mi>6)
                    yi--;

                g.drawString("Summary List Of Inactive Sat Sandesh Members Whose Period Has Ended B/W "+mi+"-"+yi+" & "+(month-1)+"-"+year
                        , (int)pf.getWidth()/2-230,y+lineHeight-3);                
            }
            
            for(int line=start;line<end && i<x; line++)
            {
                if(line==start+2)
                {
                    g.setFont(new Font("Sans Serif", Font.BOLD, 9));
                }
                else
                {
                    g.setFont(f1);
                }
                g.drawString(""+textLines[i][0], 25, y);
                g.drawString(""+textLines[i][1], 25+z, y);
                g.drawString(""+textLines[i][2], 25+2*z, y);
                g.drawString(""+textLines[i][3], 25+3*z, y);
                g.drawString(""+textLines[i][4], 25+4*z, y);
                
                if(line>start+1)
                {
                    
                    g.drawLine(23, y+2, 23+4*z+90, y+2);
                    
                    
                    g.drawLine(23, y-2, 23, y+lineHeight-2);
                    g.drawLine(23+z, y-2, 23+z, y+lineHeight-2);
                    g.drawLine(23+2*z, y-2, 23+2*z, y+lineHeight-2);
                    g.drawLine(23+3*z, y-2, 23+3*z, y+lineHeight-2);
                    g.drawLine(23+4*z, y-2, 23+4*z, y+lineHeight-2);
                    g.drawLine(23+4*z+90, y-2, 23+4*z+90, y+lineHeight-2);
                }
                
                y+=lineHeight;
                //System.out.println("i value : "+i);
                i++;
                
                
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
    
    PageFormat pf;
    Paper paper;
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==b)
        {
            b.setEnabled(false);
            
            PrinterJob job=PrinterJob.getPrinterJob();
            job.setPrintable(this);
            
            paper=new Paper();
            paper.setSize(8.27 * POINTS_PER_INCH, 11.69 * POINTS_PER_INCH);
            paper.setImageableArea(.25 * POINTS_PER_INCH, .25 * POINTS_PER_INCH,7.77 * POINTS_PER_INCH, 11.19 * POINTS_PER_INCH);
            
            pf=new PageFormat();
            pf.setPaper(paper);
            pf.setOrientation(PageFormat.PORTRAIT);
            
            PageFormat pf1 = job.validatePage(pf);
            
            
            //pf.setOrientation(PageFormat.LANDSCAPE);
            //pf = job.pageDialog(pf);
            
            boolean ok=job.printDialog();
            
            if(ok)
            {
                try
                {
                    job.print();
                }
                catch(PrinterException pe)
                {
                    JOptionPane.showMessageDialog(null, "ERROR"+pe,"ERROR", JOptionPane.ERROR_MESSAGE);
                    pe.printStackTrace();
                }
                
            }
        }
        
        if(ae.getSource()==back)
        {
            //new sams();
            new SatSandeshOldSubscriberStatusWindow();
            f.dispose();
        }
        
    }
    
}
