import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class printalldno implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new printalldno();
    }
    
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int totalNumberOfLabels=0;
    int x1=0;
    int currentLabel=0;
    int chk=0;
    JButton b, back;
    JFrame f;
    
    
    public printalldno()
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
        
        f= new JFrame("Print ALL D# Details");
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300,100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e){System.exit(0);}});
        b=new JButton("Print");
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
            c1.rs=c1.st.executeQuery("select * from despcode");
            while(c1.rs.next())
            {
                totalNumberOfLabels++;
                numLines+=10;
            }
            c1.st.close();
            c1.con.close();
            
            
            
            textLines=new String[numLines][6];
            asn=new int[totalNumberOfLabels];
            
            connect c2=new connect();
            c2.rs=c2.st.executeQuery("select dno from despcode");
            while(c2.rs.next())
            {
                asn[currentLabel]=c2.rs.getInt(1);
                currentLabel++;
            }
            
            currentLabel=0;
            
            
            textLines=new String[totalNumberOfLabels][8];
            connect c3=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++){
                c3.rs=c3.st.executeQuery("select * from despcode where dno="+asn[currentLabel]);
                c3.rs.next();
                textLines[currentLabel][0]="Distributor Code : "+c3.rs.getInt(1);
                textLines[currentLabel][1]=c3.rs.getString(2)+" "+c3.rs.getString(3);
                textLines[currentLabel][2]=c3.rs.getString(6);
                textLines[currentLabel][3]=c3.rs.getString(7);
                textLines[currentLabel][4]=c3.rs.getString(8);
                textLines[currentLabel][5]=c3.rs.getString(11)+" ("+c3.rs.getString(12)+")";
                int z =+ c3.rs.getInt(13);
                if(z > 0) textLines[currentLabel][5]+=z;
                textLines[currentLabel][6]=c3.rs.getString(5);
                textLines[currentLabel][7]=c3.rs.getString(4);
                
                
            }
            
            currentLabel=0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
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
            
            //for(int line=start ; line < end && currentLabel < totalNumberOfLabels; line+=6)
            for(int numLabels = 0 ;  numLabels < 21 && currentLabel < totalNumberOfLabels; numLabels += 3)
            //for(int line=start;line<end && currentLabel<totalNumberOfLabels; line+=10)
            {
                y+=(2*lineHeight);
                g.drawString(""+textLines[currentLabel][0], 30, y);
                g.drawString(textLines[currentLabel][1], 30, y+lineHeight);
                g.drawString(textLines[currentLabel][2], 30, y+2*lineHeight);
                g.drawString(textLines[currentLabel][3], 30, y+3*lineHeight);
                g.drawString(textLines[currentLabel][4], 30, y+4*lineHeight);
                g.drawString(textLines[currentLabel][5], 30, y+5*lineHeight);
                g.drawString(textLines[currentLabel][6], 30, y+6*lineHeight);
                g.drawString(textLines[currentLabel][7], 30, y+7*lineHeight);
                
                if((currentLabel+1)<totalNumberOfLabels)
                {
                    g.drawString(textLines[currentLabel+1][0], 215, y);
                    g.drawString(textLines[currentLabel+1][1], 215, y+lineHeight);
                    g.drawString(textLines[currentLabel+1][2], 215, y+2*lineHeight);
                    g.drawString(textLines[currentLabel+1][3], 215, y+3*lineHeight);
                    g.drawString(textLines[currentLabel+1][4], 215, y+4*lineHeight);
                    g.drawString(textLines[currentLabel+1][5], 215, y+5*lineHeight);
                    g.drawString(textLines[currentLabel+1][6], 215, y+6*lineHeight);
                    g.drawString(textLines[currentLabel+1][7], 215, y+7*lineHeight);
                    
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
                    g.drawString(textLines[currentLabel+2][7], 415, y+7*lineHeight);
                }
                y+=8*lineHeight;
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