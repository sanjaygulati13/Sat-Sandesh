import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.print.*;

public class remstatus extends JFrame implements ActionListener, Printable
{
	public static void main(String args[])
	{
		new remstatus(12,2009);
	}
	
	JFrame j, frameToPrint;
	
	JLabel[] m_2=new JLabel[10];
	JLabel[] m_1=new JLabel[10];
	JLabel[] m_c=new JLabel[10];
	JLabel[] m1=new JLabel[10];
	JLabel[] m2=new JLabel[10];
	JLabel[] total1=new JLabel[10];
	JLabel[] statet=new JLabel[10];
	
	JLabel[] hand=new JLabel[10];
	JLabel[] post=new JLabel[10];
	JLabel[] dist=new JLabel[10];
	JLabel[] total2=new JLabel[10];
	
	JButton b1, print1;
	
	
	
	JLabel l1, foot1, foot2;
	Font f1=new Font("ARIAL", Font.BOLD, 16);
	Font f=new Font("SANS SERIF", Font.BOLD, 12);

	String state[]={"DL","HAR","MAH","MP","PJB","RAJ","UA","UP","MS","TOTAL"};
	String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	int m22, m21, mc, m11, m12;
	int y22, y21, yc, y11, y12; 
		
	JLabel l15, l2, l3, l4 , l5, l6, l7, l8, l9, l10, l11, l12 , l13 , l14;
	
	public remstatus(int m, int y)
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
        j=new JFrame();
        
        Container con=j.getContentPane();
        con.setBackground(Color.WHITE);
		j.setLocation(10,10);
		j.setSize(900,650);
		j.setLayout(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setTitle("Reminder Status for "+m+"/"+y);
		j.setResizable(false);
        j.setFont(new Font("Serif", Font.BOLD,18));
		
		frameToPrint=j;
		
		m22=(m+10)%12;
		y22=y;
		if(m22==0)
			m22=12;
		if(m22>10)
			y22=y-1;
			
		m21=(m+11)%12;
		y21=y;
		if(m21==0)
			m21=12;
		if(m21>11)
			y21=y-1;
			
		
		mc=m;
		yc=y;
		
		m11=(m+1)%12;
		y11=y;
		if(m11==0)
			m11=12;
		if(m11<2)
			y11=y+1;
		
		
		m12=(m+2)%12;
		y12=y;
		if(m12==0)
			m12=12;
		if(m12<3)
			y12=y+1;
		
		b1=new JButton("Back");
		j.add(b1);
		b1.setMnemonic('b');
		b1.addActionListener(this);
		b1.setBounds(400,560,80,25);
		
		print1=new JButton("Print");
		j.add(print1);
		print1.setMnemonic('P');
		print1.addActionListener(this);
		print1.setBounds(250,560,80,25);
		
		
		
		foot1=new JLabel("Table 1 gives the status of members (state wise) whose period has expired in the month feed by the user & 2 months earlier & 2 months after that month.");
		foot1.setBounds(30,480,800,20);
		j.add(foot1);
		
		foot2=new JLabel("Table 2 gives the status of total members of Table 1 according to the mode of receiving selected by them.");
		foot2.setBounds(30, 510, 800,20);
		j.add(foot2);
		
		l1=new JLabel("REMINDER STATUS FOR "+month[m-1]+" ' "+y);
		l1.setBounds(j.getWidth()/2-150, 30,300,30 );
		l1.setFont(f1);
		j.add(l1);
		//===========================================++++++++++++++++++++++++++++++++++++++++===================================================
		
		l2=new JLabel("STATE");
		l2.setBounds(30 ,100,50,20 );
		l2.setFont(f);
		j.add(l2);
		
		l3=new JLabel(""+month[(m22-1)%12]+"' "+(y22%100));
		l3.setBounds(100,100,60,20 );
		l3.setFont(f);
		j.add(l3);
		
		
		l4=new JLabel(""+month[(m21-1)%12]+"' "+(y21%100));
		l4.setBounds(170 ,100,60,20 );
		l4.setFont(f);
		j.add(l4);
		
		l5=new JLabel(""+month[(m-1)%12]+"' "+(y%100));
		l5.setBounds(240 ,100,60,20 );
		l5.setFont(f);
		j.add(l5);
		
		l6=new JLabel(""+month[(m11-1)%12]+"' "+(y11%100));
		l6.setBounds(310 ,100,60,20 );
		l6.setFont(f);
		j.add(l6);
		
		l7=new JLabel(""+month[(m12-1)%12]+"' "+(y12%100));
		l7.setBounds(380 ,100,60,20 );
		l7.setFont(f);
		j.add(l7);
		
		l8=new JLabel("TOTAL");
		l8.setBounds(450 ,100,60,20 );
		l8.setFont(f);
		j.add(l8);
		
		
		l9=new JLabel("By Hand");
		l9.setBounds(550 ,100,60,20 );
		l9.setFont(f);
		j.add(l9);
		
		l10=new JLabel("By Post");
		l10.setBounds(620 ,100,60,20 );
		l10.setFont(f);
		j.add(l10);
		
		l11=new JLabel("Dist");
		l11.setBounds(690 ,100,60,20 );
		l11.setFont(f);
		j.add(l11);
		
		l12=new JLabel("TOTAL");
		l12.setBounds(760 ,100,60,20 );
		l12.setFont(f);
		j.add(l12);
		
		
		l13=new JLabel("TABLE 1");
		l13.setBounds(150 ,70,150,20 );
		l13.setFont(f1);
		j.add(l13);
		
		l14=new JLabel("TABLE 2");
		l14.setBounds(650 ,70,150,20 );
		l14.setFont(f1);
		j.add(l14);
		
		
		for(int i=0; i<10;i++)
		{
			statet[i]=new JLabel(state[i]);
			statet[i].setBounds(30,150+30*i,50,20);
			j.add(statet[i]);
			
			m_2[i]=new JLabel();
			m_2[i].setBounds(100, 150+30*i, 50,20);
			j.add(m_2[i]);
			
			m_1[i]=new JLabel();
			m_1[i].setBounds(170, 150+30*i, 50,20);
			j.add(m_1[i]);
			
			m_c[i]=new JLabel();
			m_c[i].setBounds(240, 150+30*i, 50,20);
			j.add(m_c[i]);
			
			m1[i]=new JLabel();
			m1[i].setBounds(310, 150+30*i, 50,20);
			j.add(m1[i]);
			
			m2[i]=new JLabel();
			m2[i].setBounds(380, 150+30*i, 50,20);
			j.add(m2[i]);
	
			total1[i]=new JLabel();
			total1[i].setBounds(450, 150+30*i, 50,20);
			j.add(total1[i]);
			
			hand[i]=new JLabel();
			hand[i].setBounds(550, 150+30*i, 50,20);
			j.add(hand[i]);
			
			post[i]=new JLabel();
			post[i].setBounds(620, 150+30*i, 50,20);
			j.add(post[i]);
			
			dist[i]=new JLabel();
			dist[i].setBounds(690, 150+30*i, 50,20);
			j.add(dist[i]);
			
			total2[i]=new JLabel();
			total2[i].setBounds(760, 150+30*i, 50,20);
			j.add(total2[i]);
			
			
	
		}
		
		
		
			
		
		try
		{
			for(int i=0;i<8;i++)
			{
			
				connect c1=new connect();
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+m22+" and endy="+y22+") and s.state='"+state[i]+"'");
				c1.rs.next();
				m_2[i].setText(""+c1.rs.getInt(1));
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+m12+" and endy="+y12+") and s.state='"+state[i]+"'");
				c1.rs.next();
				m_1[i].setText(""+c1.rs.getInt(1));
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(mc)+" and endy="+yc+") and s.state='"+state[i]+"'");
				c1.rs.next();
				m_c[i].setText(""+c1.rs.getInt(1));
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m11)+" and endy="+y11+") and s.state='"+state[i]+"'");
				c1.rs.next();
				m1[i].setText(""+c1.rs.getInt(1));
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m12)+" and endy="+y12+") and s.state='"+state[i]+"'");
				c1.rs.next();
				m2[i].setText(""+c1.rs.getInt(1));
				
				total1[i].setText(""+(Integer.parseInt(m_2[i].getText())+Integer.parseInt(m_1[i].getText())+Integer.parseInt(m_c[i].getText())+Integer.parseInt(m1[i].getText())+Integer.parseInt(m2[i].getText())));
				
				
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state='"+state[i]+"' and b.dist='By Hand' ");
				c1.rs.next();
				hand[i].setText(""+c1.rs.getInt(1));
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state='"+state[i]+"' and b.dist='By Post'");
				c1.rs.next();
				post[i].setText(""+c1.rs.getInt(1));
				
				c1.rs=c1.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state='"+state[i]+"' and b.dist='Distributor'");
				c1.rs.next();
				dist[i].setText(""+c1.rs.getInt(1));
				
				total2[i].setText(""+(Integer.parseInt(hand[i].getText())+Integer.parseInt(post[i].getText())+Integer.parseInt(dist[i].getText())));
				
				
				c1.st.close();
				c1.con.close();
			}
			
			connect c2=new connect();
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m22)+" and endy="+y22+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA')");
			c2.rs.next();
			m_2[8].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m21)+" and endy="+y21+") and s.state  not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA')");
			c2.rs.next();
			m_1[8].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA')");
			c2.rs.next();
			m_c[8].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m11)+" and endy="+y11+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA')");
			c2.rs.next();
			m1[8].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m12)+" and endy="+y12+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA')");
			c2.rs.next();
			m2[8].setText(""+c2.rs.getInt(1));
			
			
			total1[8].setText(""+(Integer.parseInt(m_2[8].getText())+Integer.parseInt(m_1[8].getText())+Integer.parseInt(m_c[8].getText())+Integer.parseInt(m1[8].getText())+Integer.parseInt(m2[8].getText())));
			
			
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA') and b.dist='By Hand' ");
			c2.rs.next();
			hand[8].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA') and b.dist='By Post'");
			c2.rs.next();
			post[8].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and s.state not in ('DL','HAR','PJB','RAJ','MP','MAH','UP','UA') and b.dist='Distributor'");
			c2.rs.next();
			dist[8].setText(""+c2.rs.getInt(1));
				
			
			total2[8].setText(""+(Integer.parseInt(hand[8].getText())+Integer.parseInt(post[8].getText())+Integer.parseInt(dist[8].getText())));
			//++++++++++++++++++++++++++++++++++++++++++++++++++==============================================+++++++++++++++++++++++++++++++++++++
			
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m22)+" and endy="+y22+")");
			c2.rs.next();
			m_2[9].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m21)+" and endy="+y21+")");
			c2.rs.next();
			m_1[9].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") ");
			c2.rs.next();
			m_c[9].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m11)+" and endy="+y11+")");
			c2.rs.next();
			m1[9].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m12)+" and endy="+y12+") ");
			c2.rs.next();
			m2[9].setText(""+c2.rs.getInt(1));
			
			total1[9].setText(""+(Integer.parseInt(m_2[9].getText())+Integer.parseInt(m_1[9].getText())+Integer.parseInt(m_c[9].getText())+Integer.parseInt(m1[9].getText())+Integer.parseInt(m2[9].getText())));
			
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and b.dist='By Hand'");
			c2.rs.next();
			hand[9].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and b.dist='By Post'");
			c2.rs.next();
			post[9].setText(""+c2.rs.getInt(1));
				
			c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, payment p, subdetails s where b.asn=p.asn and s.asn=p.asn and p.asn in (select asn from payment where endm="+(m)+" and endy="+y+") and b.dist='Distributor'");
			c2.rs.next();
			dist[9].setText(""+c2.rs.getInt(1));
			
			total2[9].setText(""+(Integer.parseInt(hand[9].getText())+Integer.parseInt(post[9].getText())+Integer.parseInt(dist[9].getText())));
			
			c2.st.close();
			c2.con.close();
			
		}
		catch(Exception e)
		{
				e.printStackTrace();
				
		}
		
		
		j.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
            this.dispose();
			new sams();
			
		}
		
		if(e.getSource()==print1)
		{
			
			
			
			PrinterJob job = PrinterJob.getPrinterJob();
         	job.setPrintable(this);
         	
         	int res = JOptionPane.showConfirmDialog(null,"You want to configure your print ","** PRINTING **", JOptionPane.YES_NO_OPTION); 
			if( res == JOptionPane.YES_OPTION ) { 
			//if (res == JOptionPane.YES_OPTION) ( 
			//	PageFormat format = job.pageDialog(job.defaultPage()); 
				PageFormat format = job.pageDialog (job.defaultPage ()); 
				} //) 

         	
         	boolean ok = job.printDialog();
         	if (ok) 
         	{
            	try 
            	{
                  	job.print();
             	}
             	catch (PrinterException ex) 
             	{
              		/* The job did not successfully complete */
             	}
         	}
			
		}
		
		
	}
	
	 public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {

		pf.setOrientation(PageFormat.LANDSCAPE);
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
         
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
		
		
		g2d.scale(pf.getImageableWidth()/frameToPrint.getWidth(), pf.getImageableHeight()/frameToPrint.getHeight());
		
        /* Now print the window and its visible contents */
        frameToPrint.printAll(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
	
	
}