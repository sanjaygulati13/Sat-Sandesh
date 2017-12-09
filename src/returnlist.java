import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class returnlist implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new returnlist();
    }
    
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
    
    public returnlist()
    {
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
        
        f= new JFrame("Print Return Back List");
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300,100);
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
        
    }
    
    public void initLines()
    {
        try
        {
            
            connect c1=new connect();
            c1.rs=c1.st.executeQuery("select count(asn) from subscribers_primary_details where membership_status='STOPPED' ");
            while(c1.rs.next())
            {
                x=c1.rs.getInt(1);
            }
            c1.st.close();
            c1.con.close();
            
            numLines=x;
            x+=(numLines/linesPerPage)*3;
            
            
            int y=(numLines%linesPerPage);
            if(y>0)
                x+=3;
            
            numLines=x;
            
            //System.out.println("x : "+x);
            
            textLines=new String[numLines][12];
            asn=new int[x];
            
            connect c2=new connect();
            c2.rs=c2.st.executeQuery("select asn from  subscribers_primary_details "
                    + "where membership_status='STOPPED' order by ending_period, first_name, last_name ");
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
                    textLines[i][5]="";
                    textLines[i][6]="";
                    textLines[i][7]="";
                    textLines[i][8]="";
                    textLines[i][9]="";
                    textLines[i][10]="";
                    textLines[i][11]="";
                    
                }
                else if(i%linesPerPage==1)
                {
                    textLines[i][0]="";
                    textLines[i][1]="";
                    textLines[i][2]="";
                    textLines[i][3]="";
                    textLines[i][4]="";
                    textLines[i][5]="";
                    textLines[i][6]="";
                    textLines[i][7]="";
                    textLines[i][8]="";
                    textLines[i][9]="";
                    textLines[i][10]="";
                    textLines[i][11]="";
                    
                    
                }
                
                else if(i%linesPerPage==2)
                {
                    textLines[i][0]=" ASN ";
                    textLines[i][1]=" Sub No ";
                    textLines[i][2]="";
                    textLines[i][3]="  First Name";
                    textLines[i][4]="  Last Name";
                    textLines[i][5]=" District";
                    textLines[i][6]="State";
                    textLines[i][7]=" Month";
                    textLines[i][8]=" Recv Date";
                    textLines[i][9]=" PO Remarks";
                    textLines[i][10]=" Other Remarks";
                    textLines[i][11]="  Action";
                    
                    
                    
                }
                else if(i%linesPerPage>2)
                {
                    
                    c3.rs=c3.st.executeQuery("select asn, subscription_code, subscription_number, "
                            + "first_name, last_name, district, state, return_issue_month ,"
                            + "return_back_stop_date, return_back_reason, return_back_other_remarks "
                            + "from subscribers_primary_details where asn="+asn[i]);
                    c3.rs.next();
                    textLines[i][0]=""+c3.rs.getInt(1);
                    textLines[i][1]=""+c3.rs.getString(2);
                    textLines[i][2]=""+c3.rs.getString(3);
                    textLines[i][3]="";
                    textLines[i][4]="";
                    
                    
                    
                    String s1, s2;
                    s1=c3.rs.getString(4);
                    s2=c3.rs.getString(5);
                    
                    if(s1!=null)
                        textLines[i][3]+=s1;
                    if(s2!=null)
                        textLines[i][4]+=s2;
                    
                    String ret=c3.rs.getString(8);
                    textLines[i][5]=""+c3.rs.getString(6);
                    textLines[i][6]=""+c3.rs.getString(7);
                    textLines[i][7]=""+ret;
                    textLines[i][8]=""+c3.rs.getString(9);
                    textLines[i][9]=""+c3.rs.getString(10);
                    textLines[i][10]=""+c3.rs.getString(11);
                    
                    textLines[i][11]="";
                    
                    if(ret.equals("STOPPED"))
                        textLines[i][11]="STOPPED";
                    
                    
                }
            }
            c3.st.close();
            c3.con.close();
            
            i=0;
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR"+e,"ERROR", JOptionPane.ERROR_MESSAGE);
            new except(e,this.getClass().toString());
            e.printStackTrace();
        }
        
    }
    
    public int print(Graphics g, PageFormat pf, int pageindex)
    {
        Font f1=new Font("SANS SERIF", Font.PLAIN, 7);
        FontMetrics metric=g.getFontMetrics(f1);
        int lineHeight=metric.getHeight();
        
        int asn_length=metric.stringWidth("99999   ");
        int subno_length=15+asn_length+metric.stringWidth("DDDDDD  ");		//after asn
        int fname_length=10+subno_length+metric.stringWidth("AMIT AMIT AMITPPDD");
        int lname_length=10+fname_length+metric.stringWidth("PAUL PAUL PAULPDDD");
        int district_length=10+lname_length+metric.stringWidth("NEW DELHI NEW DELHI ND");
        int state_length=10+district_length+metric.stringWidth("MAH ");
        int month_length=10+state_length+metric.stringWidth("STOPPED  ");
        int date_length=10+month_length+metric.stringWidth("29/12/2009  ");
        int poremarks_length=10+date_length+metric.stringWidth("INCOMPLETE ADDRESS LEFT RESIDEN");
        int othremarks_length=10+poremarks_length+metric.stringWidth("INCOMPLETE ADDRESS LEFT RESIDEN");
        int action_length=10+othremarks_length+metric.stringWidth("29/12/2009  ");
        
        
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
            for(int b=0;b<numBreaks;b++)
            {
                pageBreak[b]=(b+1)*linesPerPage;
            }
        }
        
        if(pageindex > pageBreak.length)
        {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        int y=0;
        
        g.setFont( new Font("SANS SERIF", Font.PLAIN, 8));
        
        
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
        
        
        
        int z=(int)pf.getImageableWidth()/12;
        
        
        if(chk%2==1 && (end-start)!=lineHeight)
        {
            g.drawString("LIST OF MEMBERS OF SAT SANDESH (RETURN BACK / STOPPED) " , (int)pf.getWidth()/2-150,y+lineHeight);
            g.drawString("( - "+(pageindex+1)+" - )", (int)pf.getImageableWidth()-50,y+lineHeight);
            
            
            for(int line=start;line<end && i<x; line++)
            {
                
                
                g.drawLine(13,1*lineHeight,13,(end-start)*lineHeight);		//vertical lines
                g.drawLine(13+asn_length,1*lineHeight,13+asn_length,(end-start)*lineHeight);
                g.drawLine(10+subno_length-2,1*lineHeight,10+subno_length-2,(end-start)*lineHeight);
                g.drawLine(10+fname_length-2,1*lineHeight,10+fname_length-2,(end-start)*lineHeight);
                g.drawLine(10+lname_length-2,1*lineHeight,10+lname_length-2,(end-start)*lineHeight);
                g.drawLine(10+district_length-2,1*lineHeight,10+district_length-2,(end-start)*lineHeight);
                //g.drawLine(a[6]-2,1*lineHeight,a[6]-2,(end-start)*lineHeight);
                g.drawLine(10+state_length-2,1*lineHeight,10+state_length-2,(end-start)*lineHeight);
                g.drawLine(10+month_length-2,1*lineHeight,10+month_length-2,(end-start)*lineHeight);
                g.drawLine(10+date_length-2,1*lineHeight,10+date_length-2,(end-start)*lineHeight);
                g.drawLine(10+poremarks_length-2,1*lineHeight,10+poremarks_length-2,(end-start)*lineHeight);
                g.drawLine(10+othremarks_length-2,1*lineHeight,10+othremarks_length-2,(end-start)*lineHeight);
                g.drawLine((int)pf.getImageableWidth(),1*lineHeight,(int)pf.getImageableWidth(),(end-start)*lineHeight);
                
                
//                if(line==start+2)
//                {
//                    g.setFont(new Font("SERIF", Font.BOLD, 8));
//                }
//                else
//                {
//g.setFont(f1)
//}

g.drawLine(13,y+lineHeight+2,(int)pf.getWidth(),y+lineHeight+2); //horizontal lines



g.drawString(""+textLines[i][0], 15, y);
g.drawString(""+textLines[i][1], 15+asn_length, y);
g.drawString(""+textLines[i][2], 15+asn_length+15, y);
g.drawString(""+textLines[i][3], 15+subno_length, y);
g.drawString(""+textLines[i][4], 15+fname_length, y);
g.drawString(""+textLines[i][5], 15+lname_length, y);
g.drawString(""+textLines[i][6], 15+district_length, y);
g.drawString(""+textLines[i][7], 15+state_length, y);
g.drawString(""+textLines[i][8], 15+month_length, y);
g.drawString(""+textLines[i][9], 15+date_length, y);
g.drawString(""+textLines[i][10], 15+poremarks_length, y);
g.drawString(""+textLines[i][11], 15+othremarks_length, y);




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
    
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==b)
        {
            
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
                    job.print();
                }
                catch(PrinterException pe)
                {
                    JOptionPane.showMessageDialog(null, "ERROR"+pe,"ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }
        }
        
        if(ae.getSource()==back)
        {
            new sams();
            f.dispose();
        }
        
    }
    
}