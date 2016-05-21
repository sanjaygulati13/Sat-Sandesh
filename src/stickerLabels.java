
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import javax.swing.*;

public class stickerLabels implements Printable, ActionListener
{
    public static void main(String args[])
    {
        new stickerLabels(2, 2016, "Hindi");
    }
    
    int[] pageBreak;
    int numLines=0;
    String[][] textLines;
    int[] asn;
    int totalNumberOfLabels=0;
    int x1=0;
    int currentLabel=0;
    int chk=0;
    int m1, y1;
    JButton printStickerButton,preview, back;
    JFrame f;
    String lang;
    
    
    public stickerLabels(int m, int y, String lang1)
    {
        m1=m;
        y1=y;
        lang=lang1;
        
        f= new JFrame("Print Stickers");
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
        printStickerButton=new JButton("<HTML>Print Stickers</HTML>");
        printStickerButton.addActionListener(this);
        
        preview=new JButton("Print Preview");
        preview.addActionListener(this);
        
        
        back=new JButton("Back");
        back.addActionListener(this);
        f.add(printStickerButton);
        printStickerButton.setBounds(10,10,180,25);
        printStickerButton.setMnemonic('P');
        
        //f.add(preview);
        preview.setBounds(150,10,100,25);
        preview.setMnemonic('V');
        
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(210,10,180,25);
        //f.pack();
        
        
        
        
    }
    /*
    
    public void initLines()
    {
    try
    {
    
    connect c1=new connect();
    c1.rs=c1.st.executeQuery("select count(printStickerButton.asn) from basic printStickerButton, payment p where printStickerButton.asn=p.asn and printStickerButton.dist='By Post' and printStickerButton.lang='"+lang+"' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") and printStickerButton.status not in ('STOPPED')");
    while(c1.rs.next())
    {
    totalNumberOfLabels=c1.rs.getInt(1);
    
    }
    c1.st.close();
    c1.con.close();
    
    numLines=totalNumberOfLabels*9;
    
    textLines=new String[numLines][7];
    asn=new int[totalNumberOfLabels];
    
    connect c2=new connect();
    c2.rs=c2.st.executeQuery("select printStickerButton.asn from basic printStickerButton, payment p where printStickerButton.asn=p.asn and printStickerButton.dist='By Post' and printStickerButton.lang='"+lang+"' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") and printStickerButton.status not in ('STOPPED') order by subnos, subno");
    while(c2.rs.next())
    {
    asn[currentLabel]=c2.rs.getInt(1);
    
    currentLabel++;
    }
    
    currentLabel=0;
    for(currentLabel=0;currentLabel<x;currentLabel++)
    {
    connect c10=new connect();
    c10.rs=c10.st.executeQuery("select * from basic where asn="+asn[currentLabel]);
    c10.rs.next();
    textLines[currentLabel][0]="SUB # "+ c10.rs.getString(2)+" "+ c10.rs.getString(3)+" / "+c10.rs.getInt(5)+" / ";
    }
    
    
    c2.st.close();
    c2.con.close();
    
    currentLabel=0;
    
    connect c3=new connect();
    for(currentLabel=0;currentLabel<x;currentLabel++)
    {
    c3.rs=c3.st.executeQuery("select * from payment where asn="+asn[currentLabel]);
    c3.rs.next();
    textLines[currentLabel][0]+=m1+"   "+c3.rs.getInt(10)+"/"+c3.rs.getInt(11);
    }
    
    c3.st.close();
    c3.con.close();
    
    currentLabel=0;
    connect c4=new connect();
    for(currentLabel=0;currentLabel<x;currentLabel++)
    {
    
    c4.rs=c4.st.executeQuery("select * from subdetails where asn="+asn[currentLabel]);
    c4.rs.next();
    
    String s1, s2, s3, s4, s5,s6, stateName;
    
    s1=c4.rs.getString(3);
    s2=c4.rs.getString(4);
    s3=c4.rs.getString(5);
    s4=c4.rs.getString(6);
    s5=c4.rs.getString(7);
    s6=c4.rs.getString(8);
    stateName=c4.rs.getString(9);
    
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
    
    if(stateName!=null)
    textLines[currentLabel][6]=stateName;
    
    
    
    int pinCode=Integer.parseInt(c4.rs.getString(10));
    if(pinCode>0)
    {
    textLines[currentLabel][6]+=" - "+pinCode;
    }
    }
    
    c4.st.close();
    c4.con.close();
    
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
    for(int printStickerButton=0;printStickerButton<numBreaks;printStickerButton++)
    {
    pageBreak[printStickerButton]=(printStickerButton+1)*linesPerPage;
    }
    }
    
    if(pageIndex > pageBreak.length)
    {
    return NO_SUCH_PAGE;
    }
    
    Graphics2D g2d=(Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());
    
    int currentLine=0;
    
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
    currentLine+=(4*lineHeight);
    g.drawString(""+textLines[currentLabel][0], 30, currentLine);
    g.drawString(textLines[currentLabel][1], 30, currentLine+lineHeight);
    g.drawString(textLines[currentLabel][2], 30, currentLine+2*lineHeight);
    g.drawString(textLines[currentLabel][3], 30, currentLine+3*lineHeight);
    g.drawString(textLines[currentLabel][4], 30, currentLine+4*lineHeight);
    g.drawString(textLines[currentLabel][5], 30, currentLine+5*lineHeight);
    g.drawString(textLines[currentLabel][6], 30, currentLine+6*lineHeight);
    
    if((currentLabel+1)<x)
    {
    g.drawString(textLines[currentLabel+1][0], 215, currentLine);
    g.drawString(textLines[currentLabel+1][1], 215, currentLine+lineHeight);
    g.drawString(textLines[currentLabel+1][2], 215, currentLine+2*lineHeight);
    g.drawString(textLines[currentLabel+1][3], 215, currentLine+3*lineHeight);
    g.drawString(textLines[currentLabel+1][4], 215, currentLine+4*lineHeight);
    g.drawString(textLines[currentLabel+1][5], 215, currentLine+5*lineHeight);
    g.drawString(textLines[currentLabel+1][6], 215, currentLine+6*lineHeight);
    }
    if((currentLabel+2)<x)
    {
    g.drawString(textLines[currentLabel+2][0], 415, currentLine);
    g.drawString(textLines[currentLabel+2][1], 415, currentLine+lineHeight);
    g.drawString(textLines[currentLabel+2][2], 415, currentLine+2*lineHeight);
    g.drawString(textLines[currentLabel+2][3], 415, currentLine+3*lineHeight);
    g.drawString(textLines[currentLabel+2][4], 415, currentLine+4*lineHeight);
    g.drawString(textLines[currentLabel+2][5], 415, currentLine+5*lineHeight);
    g.drawString(textLines[currentLabel+2][6], 415, currentLine+6*lineHeight);
    }
    currentLine+=6*lineHeight;
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
    int currentLine=0;
    
    Font f1=new Font("SERIF", Font.PLAIN, 8);
    //FontMetrics metric=.getFontMetrics(f1);
    int lineHeight=10;             //metric.getHeight();
    int start=0;
    int end=numLines;
    initLines();
    currentLabel=0;
    for(int line=start;line<end && currentLabel<x; line+=9)
    {
    currentLine+=(4*lineHeight);
    g1.drawString(""+textLines[currentLabel][0], 30, currentLine);
    g1.drawString(textLines[currentLabel][1], 30, currentLine+lineHeight);
    g1.drawString(textLines[currentLabel][2], 30, currentLine+2*lineHeight);
    g1.drawString(textLines[currentLabel][3], 30, currentLine+3*lineHeight);
    g1.drawString(textLines[currentLabel][4], 30, currentLine+4*lineHeight);
    g1.drawString(textLines[currentLabel][5], 30, currentLine+5*lineHeight);
    g1.drawString(textLines[currentLabel][6], 30, currentLine+6*lineHeight);
    
    if((currentLabel+1)<x)
    {
    g1.drawString(textLines[currentLabel+1][0], 215, currentLine);
    g1.drawString(textLines[currentLabel+1][1], 215, currentLine+lineHeight);
    g1.drawString(textLines[currentLabel+1][2], 215, currentLine+2*lineHeight);
    g1.drawString(textLines[currentLabel+1][3], 215, currentLine+3*lineHeight);
    g1.drawString(textLines[currentLabel+1][4], 215, currentLine+4*lineHeight);
    g1.drawString(textLines[currentLabel+1][5], 215, currentLine+5*lineHeight);
    g1.drawString(textLines[currentLabel+1][6], 215, currentLine+6*lineHeight);
    }
    if((currentLabel+2)<x)
    {
    g1.drawString(textLines[currentLabel+2][0], 415, currentLine);
    g1.drawString(textLines[currentLabel+2][1], 415, currentLine+lineHeight);
    g1.drawString(textLines[currentLabel+2][2], 415, currentLine+2*lineHeight);
    g1.drawString(textLines[currentLabel+2][3], 415, currentLine+3*lineHeight);
    g1.drawString(textLines[currentLabel+2][4], 415, currentLine+4*lineHeight);
    g1.drawString(textLines[currentLabel+2][5], 415, currentLine+5*lineHeight);
    g1.drawString(textLines[currentLabel+2][6], 415, currentLine+6*lineHeight);
    }
    currentLine+=6*lineHeight;
    currentLabel+=3;
    }
    
    return g1;
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
    
    
    if(ae.getSource()==printStickerButton)
    {
    
    PrinterJob job=PrinterJob.getPrinterJob();
    job.setPrintable(this);
    boolean ok=job.printDialog();
    
    if(ok)
    {
    try
    {
    job.print();
    JOptionPane.showMessageDialog(f, "Print complete");
    }
    catch(PrinterException pe)
    {
    System.out.println(pe);
    pe.printStackTrace();
    
    }
    
    }
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
    
    }*/
    void initLines()
    {
        try
        {
            
            connect c1=new connect();
            c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p where b.asn=p.asn and b.dist='By Post' and b.lang='"+lang+"' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") and b.status not in ('STOPPED')");
            while(c1.rs.next())
            {
                totalNumberOfLabels=c1.rs.getInt(1);
                
            }
            c1.st.close();
            c1.con.close();
            
            numLines = (totalNumberOfLabels*6)/3; //num labels * num lines per label / num labels in a row
            
            textLines=new String[totalNumberOfLabels][6];
            asn=new int[totalNumberOfLabels];
            
            connect c2=new connect();
            c2.rs=c2.st.executeQuery("select b.asn from basic b, payment p where b.asn=p.asn and b.dist='By Post' and b.lang='"+lang+"' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") and b.status not in ('STOPPED') order by subnos, subno");
            while(c2.rs.next())
            {
                asn[currentLabel]=c2.rs.getInt(1);
                currentLabel++;
            }
            
            currentLabel=0;
            connect c10=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                c10.rs=c10.st.executeQuery("select * from basic where asn="+asn[currentLabel]);
                c10.rs.next();
                textLines[currentLabel][0]="SUB # "+ c10.rs.getString(2)+" "+ c10.rs.getString(3)+" / "+c10.rs.getInt(5)+" / ";
            }
            c10.closeAll();
            
            
            c2.closeAll();
            
            currentLabel=0;
            
            connect c3=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                c3.rs=c3.st.executeQuery("select * from payment where asn="+asn[currentLabel]);
                c3.rs.next();
                textLines[currentLabel][0]+=m1+"   "+c3.rs.getInt(10)+"/"+c3.rs.getInt(11);
            }
            
            c3.closeAll();
            
            currentLabel=0;
            connect c4=new connect();
            for(currentLabel=0;currentLabel<totalNumberOfLabels;currentLabel++)
            {
                
                c4.rs=c4.st.executeQuery("select * from subdetails where asn="+asn[currentLabel]);
                c4.rs.next();
                
                String s1, s2, s3, s4, s5,s6, stateName;
                
                s1=c4.rs.getString(3);
                s2=c4.rs.getString(4);
                s3=c4.rs.getString(5);
                s4=c4.rs.getString(6);
                s5=c4.rs.getString(7);
                s6=c4.rs.getString(8);
                stateName=c4.rs.getString(9);
                
                textLines[currentLabel][1]="";
                textLines[currentLabel][2]="";
                textLines[currentLabel][3]="";
                textLines[currentLabel][4]="";
                textLines[currentLabel][5]="";
                //textLines[currentLabel][6]="";
                
                //System.out.println("s1 "+s1);
                //System.out.println("s2 "+s2);
                //System.out.println("s3 "+s3);
                //System.out.println("s4 "+s4);
                //System.out.println("s5 "+s5);
                //System.out.println("s6 "+s6);
                //System.out.println("stateName "+stateName);
                
                
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
                
                //if(stateName!=null)
                //    textLines[currentLabel][6]=stateName;
                
                
                
                int pinCode =Integer.parseInt(c4.rs.getString(10));
                if(pinCode > 0)
                {
                    textLines[currentLabel][5] += " - " + pinCode;
                }
                
                if(stateName !=null)
                    textLines[currentLabel][5] += " (" +stateName + ")";
            }
            
            c4.st.close();
            c4.con.close();
            
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
        
        
        //System.out.println(pf.getImageableHeight()+ " " + pf.getImageableWidth()+ " " +pf.getHeight()+ " " +pf.getWidth() + " " + pf.getPaper().getHeight()  );
        //System.out.println(lineHeight);
        if(pageBreak == null)
        {
            System.out.println("##########\n##########\n############\n############");
            initLines();
            //int s=(numLines%3);
            //if(s>0)
            //    numLines=numLines+(3-s);
            //numLines/=3;
            
            
            int linesPerPage=(int)(pf.getImageableHeight()/lineHeight);
            //int linesPerPage = 72;
            
            //int numBreaks=(numLines/linesPerPage);
            int numBreaks = totalNumberOfLabels/24;
            //System.out.println(linesPerPage + " " + numLines + " " + numBreaks);
            pageBreak=new int[numBreaks];
            for(int b=0; b<numBreaks ;b++)
            {
                pageBreak[b]=(b+1)*linesPerPage;
            }
        }
        //System.out.println((int)(pf.getImageableHeight()/lineHeight) + " " + numLines + " " + (numLines/((int)(pf.getImageableHeight()/lineHeight))));
        
        if(pageIndex > pageBreak.length)
        {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        //int currentLine=-1;
        //float conversionFactor = (279.0f/297.0f);
        //int currentLine = (int)(41*conversionFactor);
        int currentLine = (int)(lineHeight*4);
        //int currentLine = (int)(lineHeight*4); //GOLD
        
        System.out.println("current :: " + currentLine + " " + lineHeight);
        
        g.setFont( new Font("SERIF", Font.PLAIN, 8));
        
        int start=0;
        if(pageIndex==0)
            start=0;
        else
            start=pageBreak[pageIndex-1];
        
        int end=0;
        
        if(pageIndex==pageBreak.length)
            end = numLines;
        else
            end=pageBreak[pageIndex];
        
        //System.out.println(lineHeight + " " + (1.1)*lineHeight);
        if(chk%2==1 && (end-start)!=lineHeight)
        {
            //TODO
            //lineHeight -> 8
            //This is hardcoded, need to be checked
            
            int firstGap = 30;
            int secondGap = 230;
            int thirdGap = 420;
            //for(int line=start ; line < end && currentLabel < totalNumberOfLabels; line+=6)
            for(int numLabels = 0 ;  numLabels < 24 && currentLabel < totalNumberOfLabels; numLabels += 3)
            {
                //currentLine+=(4*lineHeight);
                g.drawString(""+textLines[currentLabel][0], firstGap, currentLine);
                g.drawString(textLines[currentLabel][1], firstGap, currentLine+lineHeight);
                g.drawString(textLines[currentLabel][2], firstGap, currentLine+2*lineHeight);
                g.drawString(textLines[currentLabel][3], firstGap, currentLine+3*lineHeight);
                g.drawString(textLines[currentLabel][4], firstGap, currentLine+4*lineHeight);
                g.drawString(textLines[currentLabel][5], firstGap, currentLine+5*lineHeight);
                //g.drawString(textLines[currentLabel][6], firstGap, currentLine+6*lineHeight);
                
                if((currentLabel+1) < totalNumberOfLabels)
                {
                    g.drawString(textLines[currentLabel+1][0], secondGap, currentLine);
                    g.drawString(textLines[currentLabel+1][1], secondGap, currentLine+lineHeight);
                    g.drawString(textLines[currentLabel+1][2], secondGap, currentLine+2*lineHeight);
                    g.drawString(textLines[currentLabel+1][3], secondGap, currentLine+3*lineHeight);
                    g.drawString(textLines[currentLabel+1][4], secondGap, currentLine+4*lineHeight);
                    g.drawString(textLines[currentLabel+1][5], secondGap, currentLine+5*lineHeight);
                    //g.drawString(textLines[currentLabel+1][6], secondGap, currentLine+6*lineHeight);
                }
                if((currentLabel+2) < totalNumberOfLabels)
                {
                    g.drawString(textLines[currentLabel+2][0], thirdGap, currentLine);
                    g.drawString(textLines[currentLabel+2][1], thirdGap, currentLine+lineHeight);
                    g.drawString(textLines[currentLabel+2][2], thirdGap, currentLine+2*lineHeight);
                    g.drawString(textLines[currentLabel+2][3], thirdGap, currentLine+3*lineHeight);
                    g.drawString(textLines[currentLabel+2][4], thirdGap, currentLine+4*lineHeight);
                    g.drawString(textLines[currentLabel+2][5], thirdGap, currentLine+5*lineHeight);
                    //g.drawString(textLines[currentLabel+2][6], thirdGap, currentLine+6*lineHeight);
                }
                currentLine += 6*lineHeight;
                currentLabel+=3;
                
                currentLine += (int)(3*lineHeight);
                //currentLine += (int)(3*lineHeight);  //GOLD
                
                System.out.println(currentLine + " : currentLine");
                
            }
            
            
        }
        else
        {
            for(int line=start; line<end && currentLabel < totalNumberOfLabels; line+=6)
            {
                
            }
            
        }
        chk++;
        
        return PAGE_EXISTS;
    }
    
    Graphics g1;
    public Graphics initGraphics()
    {
        
        System.out.println("=========================HEREE=========================");
        int y=0;
        
        Font f1=new Font("SERIF", Font.PLAIN, 8);
        //FontMetrics metric=.getFontMetrics(f1);
        int lineHeight=10;             //metric.getHeight();
        int start=0;
        int end=numLines;
        initLines();
        currentLabel=0;
        for(int line=start;line<end && currentLabel<totalNumberOfLabels; line+=9)
        {
            y+=(2*lineHeight);
            g1.drawString(""+textLines[currentLabel][0], 30, y);
            g1.drawString(textLines[currentLabel][1], 30, y+lineHeight);
            g1.drawString(textLines[currentLabel][2], 30, y+2*lineHeight);
            g1.drawString(textLines[currentLabel][3], 30, y+3*lineHeight);
            g1.drawString(textLines[currentLabel][4], 30, y+4*lineHeight);
            g1.drawString(textLines[currentLabel][5], 30, y+5*lineHeight);
            g1.drawString(textLines[currentLabel][6], 30, y+6*lineHeight);
            
            if((currentLabel+1)<totalNumberOfLabels)
            {
                g1.drawString(textLines[currentLabel+1][0], 215, y);
                g1.drawString(textLines[currentLabel+1][1], 215, y+lineHeight);
                g1.drawString(textLines[currentLabel+1][2], 215, y+2*lineHeight);
                g1.drawString(textLines[currentLabel+1][3], 215, y+3*lineHeight);
                g1.drawString(textLines[currentLabel+1][4], 215, y+4*lineHeight);
                g1.drawString(textLines[currentLabel+1][5], 215, y+5*lineHeight);
                g1.drawString(textLines[currentLabel+1][6], 215, y+6*lineHeight);
            }
            if((currentLabel+2)<totalNumberOfLabels)
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
        
        
        if(ae.getSource()==printStickerButton)
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