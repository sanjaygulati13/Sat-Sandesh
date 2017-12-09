
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.sql.SQLException;

public class memsummary extends JFrame implements ActionListener, Printable 
{

	public static void main(String args[]) {
		new memsummary(SamsUtilities.getCurrentMonth(), SamsUtilities.getCurrentYear(), "Hindi");
	}
	JLabel hand, post, dist, total1, life, comp, norm, total2;
	JLabel life1, comp1, norm1, total11, stopped_records;
	JLabel head1, head2;
	JButton b1, print1;
	JFrame frameToPrint, j;
	String lang;
	Font f = new Font("ARIAL", Font.BOLD, 12);
	JLabel[] handt, handt1;
	JLabel[] postt, postt1;
	JLabel[] distt, distt1;
	JLabel[] totalt1, totalt11;
	int stopped_records_count=0;

	public memsummary(int m, int y, String lang1) {
		lang = lang1;

		j = new JFrame();

		frameToPrint = j;

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container con = j.getContentPane();
		con.setBackground(Color.WHITE);

		try {
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
			String cn=UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(cn); // Use the native L&F
		} catch (Exception cnf) {
			System.out.println(cnf);
		}

		j.setLayout(null);
		j.setSize(400, 500);
		j.setLocation(10, 10);
		j.setTitle("Member Status Brief");

		hand = new JLabel("By Hand");
		hand.setFont(f);
		hand.setBounds(80, 50, 60, 20);
		j.add(hand);

		post = new JLabel("By post");
		post.setBounds(150, 50, 60, 20);
		post.setFont(f);
		j.add(post);

		dist = new JLabel("By D#");
		dist.setBounds(210, 50, 60, 20);
		dist.setFont(f);
		j.add(dist);

		total1 = new JLabel("Total");
		total1.setBounds(280, 50, 60, 20);
		total1.setFont(f);
		j.add(total1);

		life = new JLabel("Life");
		life.setBounds(20, 80, 60, 20);
		life.setFont(f);
		j.add(life);

		comp = new JLabel("Comp");
		comp.setBounds(20, 110, 60, 20);
		comp.setFont(f);
		j.add(comp);

		norm = new JLabel("Normal");
		norm.setBounds(20, 140, 60, 20);
		norm.setFont(f);
		j.add(norm);

		total2 = new JLabel("Total");
		total2.setBounds(20, 170, 60, 20);
		total2.setFont(f);
		j.add(total2);


		//==================================overall========================================


		head1 = new JLabel("For " + m + "/" + y + "(" + lang + ")");
		head1.setBounds(120, 30, 200, 20);
		head1.setFont(new Font("ARIAL", Font.BOLD, 14));
		j.add(head1);


		head2 = new JLabel("Total");
		head2.setBounds(200, 220, 60, 20);
		head2.setFont(f);
		j.add(head2);

		b1 = new JButton("Back");
		b1.setBounds(100, 420, 80, 25);
		j.add(b1);
		b1.setMnemonic('B');
		b1.addActionListener(this);

		print1 = new JButton("Print");
		print1.setBounds(200, 420, 80, 25);
		j.add(print1);
		print1.setMnemonic('P');
		print1.addActionListener(this);

		life1 = new JLabel("Life");
		life1.setBounds(20, 250, 60, 20);
		life1.setFont(f);
		j.add(life1);

		comp1 = new JLabel("Comp");
		comp1.setBounds(20, 280, 60, 20);
		comp1.setFont(f);
		j.add(comp1);

		norm1 = new JLabel("Normal");
		norm1.setBounds(20, 310, 60, 20);
		norm1.setFont(f);
		j.add(norm1);

		total11 = new JLabel("Total");
		total11.setBounds(20, 340, 60, 20);
		total11.setFont(f);
		j.add(total11);



		//==================================================================================

		int i = 0;
		handt = new JLabel[4];
		postt = new JLabel[4];
		distt = new JLabel[4];
		totalt1 = new JLabel[4];


		handt1 = new JLabel[4];
		postt1 = new JLabel[4];
		distt1 = new JLabel[4];
		totalt11 = new JLabel[4];

		for (i = 0; i < 4; i++) {
			handt[i] = new JLabel();
			handt[i].setFont(f);
			handt[i].setBounds(85, 80 + i * 30, 40, 20);
			j.add(handt[i]);

			postt[i] = new JLabel();
			postt[i].setFont(f);
			postt[i].setBounds(155, 80 + i * 30, 40, 20);
			j.add(postt[i]);

			distt[i] = new JLabel();
			distt[i].setFont(f);
			distt[i].setBounds(215, 80 + i * 30, 40, 20);
			j.add(distt[i]);

			totalt1[i] = new JLabel();
			totalt1[i].setFont(f);
			totalt1[i].setBounds(285, 80 + i * 30, 40, 20);
			j.add(totalt1[i]);



			handt1[i] = new JLabel();
			handt1[i].setFont(f);
			handt1[i].setBounds(85, 250 + i * 30, 40, 20);
			j.add(handt1[i]);

			postt1[i] = new JLabel();
			postt1[i].setFont(f);
			postt1[i].setBounds(155, 250 + i * 30, 40, 20);
			j.add(postt1[i]);

			distt1[i] = new JLabel();
			distt1[i].setFont(f);
			distt1[i].setBounds(215, 250 + i * 30, 40, 20);
			j.add(distt1[i]);

			totalt11[i] = new JLabel();
			totalt11[i].setFont(f);
			totalt11[i].setBounds(285, 250 + i * 30, 40, 20);
			j.add(totalt11[i]);

		}

		try {

			//============================================================by hand per month====================================================
			connect c1 = new connect();
			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where  distribution_type='By Hand' and subscription_period='Life' and language='" + lang + "' and membership_status='Active'");
			c1.rs.next();
			handt[0].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where distribution_type='By Hand' and subscription_period='Comp' and language='" + lang + "' and membership_status='Active' ");
			c1.rs.next();
			handt[1].setText("" + c1.rs.getInt(1));


			c1.rs = c1.st.executeQuery("select count(subscription_number) from subscribers_primary_details where distribution_type='By Hand' and language='" + lang + "' and subscription_period not in('Life' , 'Comp') and membership_status='Active'");
			c1.rs.next();
			handt[2].setText("" + c1.rs.getInt(1));

			//========================================================================= by post======================================
			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where  distribution_type='By Post' and language='" + lang + "' and subscription_period='Life' and membership_status='Active'");
			c1.rs.next();
			postt[0].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='By Post' and subscription_period='Comp' and membership_status='Active'");
			c1.rs.next();
			postt[1].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='By Post' and subscription_period not in('Life' , 'Comp') and membership_status='Active'");
			c1.rs.next();
			postt[2].setText("" + c1.rs.getInt(1));

			//=============================================================== by d#=====================================

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='Distributor' and subscription_period='Life' and membership_status='Active'");
			c1.rs.next();
			distt[0].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='Distributor' and subscription_period='Comp' and membership_status='Active'");
			c1.rs.next();
			distt[1].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='Distributor' and subscription_period not in('Life' , 'Comp') and membership_status='Active'");
			c1.rs.next();
			distt[2].setText("" + c1.rs.getInt(1));



			//==========================================================total bottom==============================================
			handt[3].setText("" + (Integer.parseInt(handt[0].getText()) + Integer.parseInt(handt[1].getText()) + Integer.parseInt(handt[2].getText())));
			postt[3].setText("" + (Integer.parseInt(postt[0].getText()) + Integer.parseInt(postt[1].getText()) + Integer.parseInt(postt[2].getText())));
			distt[3].setText("" + (Integer.parseInt(distt[0].getText()) + Integer.parseInt(distt[1].getText()) + Integer.parseInt(distt[2].getText())));


			//==========================================================total right====================================================
			for (i = 0; i < 4; i++) {
				totalt1[i].setText("" + (Integer.parseInt(handt[i].getText()) + Integer.parseInt(postt[i].getText()) + Integer.parseInt(distt[i].getText())));
			}


			//===========================================================================================================================
			//													TOTAL RECORDS
			//============================================================================================================================

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where  distribution_type='By Hand' and language='" + lang + "' and subscription_period='Life' ");
			c1.rs.next();
			handt1[0].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where distribution_type='By Hand' and language='" + lang + "' and subscription_period='Comp' ");
			c1.rs.next();
			handt1[1].setText("" + c1.rs.getInt(1));


			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where distribution_type='By Hand' and language='" + lang + "' and subscription_period not in('Life' , 'Comp')");
			c1.rs.next();
			handt1[2].setText("" + c1.rs.getInt(1));

			//========================================================================= by post======================================
			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='By Post' and subscription_period='Life' ");
			c1.rs.next();
			postt1[0].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='By Post' and subscription_period='Comp' ");
			c1.rs.next();
			postt1[1].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='By Post' and subscription_period not in('Life' , 'Comp') ");
			c1.rs.next();
			postt1[2].setText("" + c1.rs.getInt(1));

			//=============================================================== by d#=====================================

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='Distributor' and subscription_period='Life' ");
			c1.rs.next();
			distt1[0].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='Distributor' and subscription_period='Comp' ");
			c1.rs.next();
			distt1[1].setText("" + c1.rs.getInt(1));

			c1.rs = c1.st.executeQuery("select count(asn) from subscribers_primary_details where language='" + lang + "' and  distribution_type='Distributor' and subscription_period not in('Life' , 'Comp') ");
			c1.rs.next();
			distt1[2].setText("" + c1.rs.getInt(1));



			//==========================================================total bottom==============================================
			handt1[3].setText("" + (Integer.parseInt(handt1[0].getText()) + Integer.parseInt(handt1[1].getText()) + Integer.parseInt(handt1[2].getText())));
			postt1[3].setText("" + (Integer.parseInt(postt1[0].getText()) + Integer.parseInt(postt1[1].getText()) + Integer.parseInt(postt1[2].getText())));
			distt1[3].setText("" + (Integer.parseInt(distt1[0].getText()) + Integer.parseInt(distt1[1].getText()) + Integer.parseInt(distt1[2].getText())));


			//==========================================================total right====================================================
			for (i = 0; i < 4; i++) {
				totalt11[i].setText("" + (Integer.parseInt(handt1[i].getText()) + Integer.parseInt(postt1[i].getText()) + Integer.parseInt(distt1[i].getText())));
			}
			//===========================================================================================================================





			c1.st.close();
			c1.con.close();


			try {
				connect stopped = new connect();
				stopped.rs = stopped.st.executeQuery("select count(asn) from subscribers_primary_details where membership_status='STOPPED' and language='" + lang + "' ");
				stopped.rs.next();
				stopped_records_count = stopped.rs.getInt(1);
				stopped.rs.close();
				stopped.st.close();
				stopped.con.close();
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}

			stopped_records = new JLabel("Number Of Stopped Records - " + stopped_records_count);
			stopped_records.setFont(new Font("SERIF", Font.BOLD, 16));
			j.add(stopped_records);
			stopped_records.setBounds(70, 370, 680, 25);



		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}


		j.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b1) {
			new sams();
			j.dispose();
		}

		if (ae.getSource() == print1) {



			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);

			//int res = JOptionPane.showConfirmDialog(null,"You want to configure your print ","** PRINTING **", JOptionPane.YES_NO_OPTION);
			//if( res == JOptionPane.YES_OPTION ) {
			//if (res == JOptionPane.YES_OPTION) (
			//	PageFormat format = job.pageDialog(job.defaultPage());
			//PageFormat format = job.pageDialog (job.defaultPage ());
			//} //)


			boolean ok = job.printDialog();
			if (ok) {
				try {
					job.print();
				} catch (PrinterException ex) {
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

			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(pf.getImageableX(), pf.getImageableY());


			//g2d.scale(pf.getImageableWidth()/frameToPrint.getWidth(), pf.getImageableHeight()/frameToPrint.getHeight());

			/* Now print the window and its visible contents */
			frameToPrint.printAll(g);

			/* tell the caller that this page is part of the printed document */
			return PAGE_EXISTS;
		}
}
