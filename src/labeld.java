import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class labeld implements Printable, ActionListener
{
	public static void main(String args[])
	{
		new labeld(1);
	}
	
	int[] pageBreak;
	int numLines=20;
	String[] textLines;
	int[] asn;
	int dno;
	JButton b, back;
	JFrame f;
	
	
	public labeld(int d)
	{
		
		dno=d;
		
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
		
        f= new JFrame("Print DNO Details ");
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
        b.setBounds(10,10,100,25);
        b.setMnemonic('P');
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(150,10,100,25);
        //f.pack();
        
        
        
        
	}
	
	public void initLines()
	{
		try
		{
			
			textLines=new String[8];
			connect c1=new connect();
			c1.rs=c1.st.executeQuery("select * from despcode where dno="+dno);
			c1.rs.next();
			textLines[0]="Distributor Code : "+c1.rs.getInt(1);
			textLines[1]=c1.rs.getString(2)+" "+c1.rs.getString(3);
			textLines[2]=c1.rs.getString(6);
			textLines[3]=c1.rs.getString(7);
			textLines[4]=c1.rs.getString(8);
			textLines[5]=c1.rs.getString(5);
			textLines[6]=c1.rs.getString(4);
			textLines[7]=c1.rs.getString(11)+" ("+c1.rs.getString(12)+")";
			int z=+c1.rs.getInt(13);
			if(z>0)
				textLines[7]+=z;
			
		}
		
		
		
		
		catch(Exception e)
		{
			System.out.println(e);
			//e.printStackTrace();
		}
		
	}
	
	public int print(Graphics g, PageFormat pf, int pageIndex)
	{
		Font f1=new Font("SERIF", Font.PLAIN, 26);
		FontMetrics metric=g.getFontMetrics(f1);
		int lineHeight=metric.getHeight();
		
		if(pageBreak==null)
		{
			initLines(); 
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
		
		g.setFont( new Font("SERIF", Font.PLAIN, 26));
		
		
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
		
			for(int line=start;line<end; line+=10)
			{
				y+=(2*lineHeight);
				g.drawString(""+textLines[0], 80, y);
				g.drawString(textLines[1], 80, y+lineHeight);
				g.drawString(textLines[2], 80, y+2*lineHeight);
				g.drawString(textLines[3], 80, y+3*lineHeight);
				g.drawString(textLines[4], 80, y+4*lineHeight);
				g.drawString(textLines[5], 80, y+5*lineHeight);
				g.drawString(textLines[6], 80, y+6*lineHeight);
				g.drawString(textLines[7], 80, y+7*lineHeight);
				y+=8*lineHeight;
				
				
			}
		
			
		return PAGE_EXISTS;
	}
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==b)
		{
		
		// create print preview
		/*JPrintPreview printPreview = new JPrintPreview();
		Printable printable = createPrintable();
		printPreview.setPrintData(printable);
		printPreview.showFrame(null);
*/

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
			new bulkadd();
			f.dispose();
		}
		
	}
	
}