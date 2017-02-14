import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class alldno implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new alldno(15);
    }
    int present=0;
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int x=0;
    int x1=0;
    int i=0;
    int chk=0;
    //int m1, y1;
    String m1;
    JButton b, back;
    JFrame f;
    int dno;
    int linesPerPage;
    int NumberOfRecords=0;
    String distributionTypeText;
    
    public alldno(int dno1)
    {
        dno=dno1;
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
        
        f= new JFrame("Print List");
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300,90);
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
            c1.rs=c1.st.executeQuery("select count(asn) from basic where dno="+dno+" and subnos not in ('NA')");
            while(c1.rs.next())
            {
                x=c1.rs.getInt(1);
            }
            
            NumberOfRecords=x;
            numLines=x*2;
            //System.out.println("x : "+x);
            
            int y=(numLines%linesPerPage);
            
            //System.out.println("y : "+y);
            
            c1.st.close();
            c1.con.close();
            
            numLines=x*2;
            
            //--------------------------------^^numlines for records only==============================
            
            
            int page=(numLines/linesPerPage);
            
            
            if(numLines%linesPerPage>0)
                page++;
            
            
            
            int head=page*3;
            
            numLines=(x+head)*2;
            
            y=numLines%linesPerPage;
            
            if(y<4)
                head+=3;
            
            
            
            x+=head;
            numLines=x*2;
            
            
            page=(numLines/linesPerPage);
            
            //======================================================================================================
            
            
            int half2=(page)/2;
            int half=(page)%2;
            if(half>0)
                half2++;
            x+=half2;
            
            if((numLines%55)>53)
                x++;
            
            //====================================================================================================
            
            
            
            
            numLines=x*2;
            
            textLines=new String[numLines][15];
            asn=new int[x];
            
            //System.out.println("x : "+x);
            connect c2=new connect();
            for(i=0;i<x;i++)
            {
                
                if(i==0)
                {
                    
                    c2.rs=c2.st.executeQuery("select b.asn from basic b, subdetails s where b.asn=s.asn and b.dno="+dno+"  and b.subnos not in ('NA') order by s.state, s.fname, s.lname");
                    while(c2.rs.next())
                    {
                        if(i%(linesPerPage/2)==0 && i<x)
                        {
                            asn[i]=0;
                            //System.out.println(asn[i]);
                            i++;
                            
                        }
                        if(i%(linesPerPage/2)==1 && i<x)
                        {
                            asn[i]=0;
                            //System.out.println(asn[i]);
                            i++;
                        }
                        if(i%(linesPerPage/2)==2 && i<x)
                        {
                            asn[i]=0;
                            //System.out.println(asn[i]);
                            i++;
                        }
                        if(i%(linesPerPage/2)>2 && i<x)
                        {
                            asn[i]=c2.rs.getInt(1);
                            //System.out.println("a ["+i+"] := "+asn[i]);
                            i++;
                        }
                        
                        present=i;
                        
                    }
                }
                
                //System.out.println(i);
                if(i<x)
                    asn[i]=0;
                //System.out.println("a ["+i+"] := "+asn[i]);
            }
            c2.st.close();
            c2.con.close();
            
            i=0;
            connect c3=new connect();
            for(i=0;i<x;i++)
            {
                if(i<present)
                {
                    
                    if(i%(linesPerPage/2)==0)
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
                        textLines[i][12]="";
                        textLines[i][13]="";
                        textLines[i][14]="";
                        
                    }
                    else if(i%(linesPerPage/2)==1)
                    {
                        textLines[i][0]="";
                        textLines[i][1]="";
                        textLines[i][2]="";
                        textLines[i][3]="";
                        textLines[i][4]="";
                        textLines[i][5]="";
                        textLines[i][6]="";
                        textLines[i][7]="";
                        /*textLines[i][5]="  SAT-SANDESH  ";
                        textLines[i][6]="  (HINDI)  ";
                        textLines[i][7]="  INDEX REGISTER AS ON  "+d+"-"+m+"-"+y1;
                        */
                        textLines[i][8]="";
                        textLines[i][9]="";
                        textLines[i][10]="";
                        textLines[i][11]="";
                        textLines[i][12]="";
                        textLines[i][13]="";
                        textLines[i][14]="";
                    }
                    else if(i%(linesPerPage/2)==2)
                    {
                        textLines[i][0]="ASN";
                        textLines[i][1]="Sub No";
                        textLines[i][2]="M";
                        textLines[i][3]="Year";
                        textLines[i][4]="D#";
                        textLines[i][5]="F Name";
                        textLines[i][6]="L Name";
                        textLines[i][7]="ADDRESS";
                        textLines[i][8]="";
                        textLines[i][9]="";
                        textLines[i][10]="District";
                        textLines[i][11]="State";
                        textLines[i][12]="Pin";
                        textLines[i][13]="";
                        textLines[i][14]="";
                    }
                    
                    else if((i%(linesPerPage/2))>2)
                    {
                        
                        c3.rs=c3.st.executeQuery("select b.asn, b.subnos, b.subno, p.endm, p.endy, b.dno from basic b, payment p where b.asn=p.asn and b.asn="+asn[i]);
                        c3.rs.next();
                        textLines[i][0]=""+c3.rs.getInt(1);
                        textLines[i][1]=""+c3.rs.getString(2)+c3.rs.getString(3);
                        textLines[i][2]=""+c3.rs.getInt(4);
                        textLines[i][3]=""+c3.rs.getInt(5);
                        textLines[i][4]="";
                        int d=c3.rs.getInt(6);
                        if(d>0)
                            textLines[i][4]=""+d;
                    }
                }
                else if(i>=present)
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
                    textLines[i][12]="";
                    textLines[i][13]="";
                    textLines[i][14]="";
                    
                }
            }
            c3.st.close();
            c3.con.close();
            
            i=0;
            connect c4=new connect();
            for(i=0;i<x;i++)
            {
                if(i<present)
                {
                    
                    if(i%(linesPerPage/2)>2)
                    {
                        c4.rs=c4.st.executeQuery("select * from subdetails where asn="+asn[i]);
                        c4.rs.next();
                        String s1, s2, s3, s4, s5, s6,s7;
                        
                        s1= c4.rs.getString(3);
                        s2= c4.rs.getString(4);
                        s3= c4.rs.getString(5);
                        s4= c4.rs.getString(6);
                        s5= c4.rs.getString(7);
                        s6= c4.rs.getString(8);
                        s7=c4.rs.getString(9);
                        
                        textLines[i][5]="";
                        textLines[i][6]="";
                        textLines[i][7]="";
                        textLines[i][8]="";
                        textLines[i][9]="";
                        textLines[i][10]="";
                        textLines[i][11]="";
                        
                        if(s1!=null)
                            textLines[i][5]=s1;
                        
                        if(s2!=null)
                            textLines[i][6]=s2;
                        
                        if(s3!=null)
                            textLines[i][7]=s3;
                        
                        if(s4!=null)
                            textLines[i][8]=s4;
                        
                        if(s5!=null)
                            textLines[i][9]=s5;
                        
                        if(s6!=null)
                            textLines[i][10]=s6;
                        
                        if(s7!=null)
                            textLines[i][11]=s7;
                        
                        textLines[i][12]="";
                        int c=Integer.parseInt(c4.rs.getString(10));
                        if(c>0)
                        {
                            textLines[i][12]+=c;
                        }
                    }
                }
            }
            //System.out.println(" : "+textLines[i-1][1]);
            c4.st.close();
            c4.con.close();
            
            
            
            connect c5=new connect();
            for(i=0;i<x;i++)
            {
                if(i<present)
                {
                    
                    if(i%(linesPerPage/2)>2)
                    {
                        c5.rs=c5.st.executeQuery("select phone, email from otherdet where asn="+asn[i]);
                        c5.rs.next();
                        String s1, s2;
                        
                        s1= c5.rs.getString(1);
                        s2= c5.rs.getString(2);
                        
                        textLines[i][13]="";
                        textLines[i][14]="";
                        
                        if(s1!=null && s1.length()>6)
                        {
                            textLines[i][13]="(T No.-"+s1+")";
                        }
                        
                        
                        if(s2!=null && s2.length()>5)
                        {
                            textLines[i][14]="(e-id:"+s2+")";textLines[i][14]="(e-id:"+s2+")";
                        }
                        
                        
                    }
                }
            }
            c5.st.close();
            c5.con.close();
            
            i=0;
            
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
        
        a[1]=metric.stringWidth("99999   ");
        a[2]=15+a[1]+metric.stringWidth("DDDDDD ");		//after asn
        a[3]=10+a[2]+metric.stringWidth("DD");				//
        a[4]=10+a[3]+metric.stringWidth("9999 ");
        a[5]=10+a[4]+metric.stringWidth("99 ");
        
        a[6]=10+a[5]+metric.stringWidth("AMIT AMIT AMITPPDD");
        a[7]=10+a[6]+metric.stringWidth("PAUL PAUL PAULPDDD");
        a[8]=10+a[7]+metric.stringWidth("GURU HARKISHAN NAGAR GURU HARKISDDDD");
        a[9]=10+a[8]+metric.stringWidth("GURU HARKISHAN NAGAR GURU HARKISDDDD");
        
        a[10]=10+a[9]+metric.stringWidth("NEW DELHI NEW DELHI ND");
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
            g.drawLine(3,3*lineHeight,3,(int)pf.getHeight()-5*lineHeight);		//vertical lines
            g.drawLine(a[1]+8,3*lineHeight,a[1]+8,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[2]-2,3*lineHeight,a[2]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[3]-2,3*lineHeight,a[3]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[4]-2,3*lineHeight,a[4]-2,(int)pf.getHeight()-5*lineHeight);
            //g.drawLine(a[5]-2,3*lineHeight,a[5]-2,(int)pf.getHeight()-5*lineHeight);
            //g.drawLine(a[6]-2,3*lineHeight,a[6]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[7]-2,3*lineHeight,a[7]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[9]-2,3*lineHeight,a[9]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[10]-2,3*lineHeight,a[10]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine(a[11]-2,3*lineHeight,a[11]-2,(int)pf.getHeight()-5*lineHeight);
            g.drawLine((int)pf.getImageableWidth(),3*lineHeight,(int)pf.getImageableWidth(),(int)pf.getHeight()-5*lineHeight);
            
            
            
            for(int line=start; line<end; line+=2)
            {
                if(i%(linesPerPage/2)==1)
                {
                    g.setFont(new Font("SERIF", Font.BOLD, 9));
                    
                    String dist="", stat="";
                    try
                    {
                        
                        connect getstate=new connect();
                        getstate.rs=getstate.st.executeQuery("select district, state, distributionType from despcode where dno="+dno);
                        getstate.rs.next();
                        dist=getstate.rs.getString(1);
                        stat=getstate.rs.getString(2);
                        distributionTypeText = getstate.rs.getString(3);
                        
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    String distributionType = (distributionTypeText.equals("By Post"))?"DP":"DH";
                    //g.drawString("LIST OF ALL SAT-SANDESH MEMBERS SERVICED BY DISTRIBUTOR CODE # "+dno+" ( "+dist+", "+stat+" )" , (int)pf.getWidth()/2-300,y);
                    g.drawString("LIST OF ALL SAT-SANDESH MEMBERS FOR M/O "+SamsUtilities.getCurrentMonth()+"/"+SamsUtilities.getCurrentYear()+" FOR DISTRIBUTOR CODE # "+dno+" ( "+dist+" , "+stat+" ) ("+distributionType+")", (int)pf.getWidth()/2-300,y);
                    g.drawString("( - "+(pageIndex+1)+" - )", (int)pf.getWidth()/2+200,y);
                    g.drawString("("+SamsUtilities.getCurrentDateString()+")  (TR: "+NumberOfRecords+")",(int)pf.getWidth()/2+240 , y);
                    
                }
                
                
                g.setFont(new Font("SERIF", Font.PLAIN, 8));
                if(i%(linesPerPage/2)>1)
                {
                    g.drawLine(3,y+lineHeight,(int)pf.getWidth()+1,y+lineHeight); //horizontal lines
                    
                    g.drawString(" "+textLines[i][0], 5, y);
                    
                    g.drawString(" "+textLines[i][1], 10+a[1], y);
                    
                    g.drawString(" "+textLines[i][2], a[2], y);
                    
                    g.drawString(" "+textLines[i][3], a[3], y);
                    
                    //g.drawString(" "+textLines[i][4], a[4], y);
                    
                    g.drawString(" "+textLines[i][5], a[5]-10, y);
                    
                    g.drawString(" "+textLines[i][6], a[6], y);
                    
                    g.drawString(" "+textLines[i][7], a[7], y);
                    
                    g.drawString(" "+textLines[i][8], a[8], y);
                    
                    g.drawString(" "+textLines[i][9], a[7], y+lineHeight-2);
                    
                    
                    
                    g.drawString(" "+textLines[i][10], a[9], y);
                    
                    g.drawString(" "+textLines[i][13], a[9], y+lineHeight-2);
                    
                    g.drawString(" "+textLines[i][11], a[10], y);
                    
                    g.drawString(" "+textLines[i][12], a[11], y);
                    
                    
                }
                y+=2*lineHeight;
                
                i++;
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
                    JOptionPane.showMessageDialog(null, "ERROR"+pe.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }
                
                b.setEnabled(false);
                
            }
        }
        
        if(ae.getSource()==back)
        {
            new sams();
            f.dispose();
        }
        
    }
    
}