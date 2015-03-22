
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class label implements Printable, ActionListener
{
	public static void main(String args[])
	{
		new label(3, 2011, "Hindi");
	}
	
	int[] pageBreak;
	int numLines=0;
	String[][] textLines;
	int[] asn;
	int x=0;
	int x1=0;
	int i=0;
	int chk=0; 
	int m1, y1;
	JButton b,preview, back;
	JFrame f;
        String lang;
	
	
	public label(int m, int y, String lang1)
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
			c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p where b.asn=p.asn and b.dist='By Post' and b.lang='"+lang+"' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") and b.status not in ('STOPPED')");
			while(c1.rs.next())
			{
				x=c1.rs.getInt(1);
				
			}
			c1.st.close();
			c1.con.close();
			
			numLines=x*9;
			
			textLines=new String[numLines][7];
			asn=new int[x];
			
			connect c2=new connect();
			c2.rs=c2.st.executeQuery("select b.asn from basic b, payment p where b.asn=p.asn and b.dist='By Post' and b.lang='"+lang+"' and (p.asn) in (select asn from payment where (endm>"+(m1-1)+" and endy="+y1+") or endy>"+y1+") and b.status not in ('STOPPED') order by subnos, subno");
			while(c2.rs.next())
			{
				asn[i]=c2.rs.getInt(1);
				
				i++;
			}
			
			i=0;
			for(i=0;i<x;i++)
			{
				connect c10=new connect();
				c10.rs=c10.st.executeQuery("select * from basic where asn="+asn[i]);
				c10.rs.next();
				textLines[i][0]="SUB # "+ c10.rs.getString(2)+" "+ c10.rs.getString(3)+" / "+c10.rs.getInt(5)+" / ";
			}
				
			
			c2.st.close();
			c2.con.close();
			
			i=0;
			
			connect c3=new connect();
			for(i=0;i<x;i++)
			{
				c3.rs=c3.st.executeQuery("select * from payment where asn="+asn[i]);
				c3.rs.next();
				textLines[i][0]+=m1+"   "+c3.rs.getInt(10)+"/"+c3.rs.getInt(11);
			}

			c3.st.close();
			c3.con.close();
			
			i=0;
			connect c4=new connect();
			for(i=0;i<x;i++)
			{
				
				c4.rs=c4.st.executeQuery("select * from subdetails where asn="+asn[i]);
				c4.rs.next();

				String s1, s2, s3, s4, s5,s6, s7;
				
				s1=c4.rs.getString(3);
				s2=c4.rs.getString(4);
				s3=c4.rs.getString(5);
				s4=c4.rs.getString(6);
				s5=c4.rs.getString(7);
				s6=c4.rs.getString(8);
				s7=c4.rs.getString(9);
				
				textLines[i][1]="";
				textLines[i][2]="";
				textLines[i][3]="";
				textLines[i][4]="";
				textLines[i][5]="";
				textLines[i][6]="";
				
				
				
				if(s1!=null)
				textLines[i][1]= s1+" ";
				
				if(s2!=null)
				textLines[i][1]+=s2;
				
				if(s3!=null)
   		        textLines[i][2]= s3;
   		        
   		        if(s4!=null)
			    textLines[i][3]= s4;
			    
			    if(s5!=null)
   		        textLines[i][4]= s5;
   		        
   		        if(s6!=null)
  		        textLines[i][5]= s6;
  		        
  		        if(s7!=null)
  		        textLines[i][6]=s7;
  		        

				
  		        int c=Integer.parseInt(c4.rs.getString(10));
  		        if(c>0)
  		        {
  		        	textLines[i][6]+=" - "+c;
  		        }
  		    }
			
			c4.st.close();
			c4.con.close();
			
			i=0;
			
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
        i=0;
        for(int line=start;line<end && i<x; line+=9)
        {
                y+=(2*lineHeight);
				g1.drawString(""+textLines[i][0], 30, y);
				g1.drawString(textLines[i][1], 30, y+lineHeight);
				g1.drawString(textLines[i][2], 30, y+2*lineHeight);
				g1.drawString(textLines[i][3], 30, y+3*lineHeight);
				g1.drawString(textLines[i][4], 30, y+4*lineHeight);
				g1.drawString(textLines[i][5], 30, y+5*lineHeight);
				g1.drawString(textLines[i][6], 30, y+6*lineHeight);

				if((i+1)<x)
				{
					g1.drawString(textLines[i+1][0], 215, y);
					g1.drawString(textLines[i+1][1], 215, y+lineHeight);
					g1.drawString(textLines[i+1][2], 215, y+2*lineHeight);
					g1.drawString(textLines[i+1][3], 215, y+3*lineHeight);
					g1.drawString(textLines[i+1][4], 215, y+4*lineHeight);
					g1.drawString(textLines[i+1][5], 215, y+5*lineHeight);
					g1.drawString(textLines[i+1][6], 215, y+6*lineHeight);
				}
				if((i+2)<x)
				{
					g1.drawString(textLines[i+2][0], 415, y);
					g1.drawString(textLines[i+2][1], 415, y+lineHeight);
					g1.drawString(textLines[i+2][2], 415, y+2*lineHeight);
					g1.drawString(textLines[i+2][3], 415, y+3*lineHeight);
					g1.drawString(textLines[i+2][4], 415, y+4*lineHeight);
					g1.drawString(textLines[i+2][5], 415, y+5*lineHeight);
					g1.drawString(textLines[i+2][6], 415, y+6*lineHeight);
				}
	    		y+=7*lineHeight;
                i+=3;
        }

        return g1;
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