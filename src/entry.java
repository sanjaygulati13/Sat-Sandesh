import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;



public class entry extends JFrame implements ActionListener, ItemListener, Runnable
{
	public static void main(String args[])
	{
		entry e=new entry();
	}	
	
	JLabel  subd1, asn1, sub1, status1, rec1, dist1, d1, subt1, lang1;				//subscription details
	JLabel pay1, payt1, chdd1, dat1, am1, starm1,stary1, end1;						//payment details
	JLabel mem1, tit1, nam1, lnam1, add1, dis1, stat1, pin1;						//member details
	JLabel oth1, tel1, hist1, email1, ret1, rem1; 									//other details
	int endm2, endy2;
	Thread t=null;
	
	Font f=new Font("ARIAL", Font.BOLD, 12);
	
	JPanel p1, p2, p3, p4,p5;
	
	
	JTextField asnt1, statust1 /*, distt1, subtt1 , langt1*/;						//subscription details
	JComboBox distt1,  subtt1, langt1, dt1;
	TextFieldWithLimit subt21, rect1;	
	JComboBox subt22;
		
		
	JTextField /*paytt1,*/ chddt1;											//payment details
	TextFieldWithLimit datt1, datt2, datt3, amt1, endt1;//starty1, startm1,
	JComboBox paytt1, starty1, startm1;
		
																
	TextFieldWithLimit titt1, namt1, lnamt1, statt1, pint1,addt11,addt21,addt31,dist21;	//member details
	
	
	JTextField  rett1;											//other details
	TextArea remt1;
	TextFieldWithLimit telt1,emailt1;
	JComboBox histt1;
	
	JButton sav, mod, clear, back;
	int x, amount;
	String str;
	int flag=0;
	int s2=0;

     protected void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);
    }

	public entry()
	{

		
    	try 
        {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } 
        catch (Exception cnf) 
        {
        	JOptionPane.showMessageDialog(this, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
		flag=0;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("New Subscription");
		setSize(1050,690);
		//this.setSize(this.getToolkit().getScreenSize());
		setResizable(false);
		setLocation(10,10);
		setLayout(new GridLayout(5,1));	
		center();

		p1=new JPanel(null);
		//p1.setLayout(null);
		p2=new JPanel(null);
		//p2.setLayout(null);
		p3=new JPanel(null);
		//p3.setLayout(null);
		p4=new JPanel(null);
		//p4.setLayout(null);
		p5=new JPanel(null);
		//p5.setLayout(null);
		
		
		asn1=new JLabel("ASN");
		sub1=new JLabel("SUB No");
		status1=new JLabel("Status");
		rec1=new JLabel("Reciept No");
		dist1=new JLabel("Distribution type");
		d1=new JLabel("D#");
		subt1=new JLabel("Subscription Type");
		lang1=new JLabel("Language");
		subd1=new JLabel("SUBSCRIPTION DETAILS");
		pay1=new JLabel("PAYMENT AND PERIOD DETAILS");
		payt1=new JLabel("Payment Type");
		chdd1=new JLabel("CH/ DD/ MO No");
		dat1=new JLabel("DATE");
		am1=new JLabel("Amount");
		starm1=new JLabel("Starting Period");
		stary1=new JLabel("/");
		end1=new JLabel("Ending Period");
		mem1=new JLabel("SUBSCRIBER DETAILS");
		tit1=new JLabel("Title");
		nam1=new JLabel("Name");
		lnam1=new JLabel("LName");
		add1=new JLabel("Address");
		dis1=new JLabel("District");
		stat1=new JLabel("State");
		pin1=new JLabel("Pin Code");
		oth1=new JLabel("OTHER DETAILS");
		tel1=new JLabel("Telephone");
		hist1=new JLabel("Counter");
		email1=new JLabel("e-mail");
		ret1=new JLabel("Return Back");
		rem1=new JLabel("Remarks");
		
		asnt1=new JTextField(8);
		subt21=new TextFieldWithLimit(5,5);
		statust1=new JTextField(20);
		rect1=new TextFieldWithLimit(5,5);
		distt1=new JComboBox();

        Object[] items={"BH","BD","CM","DL","EN","HR","LF","LH","MH","MP","MS","PB","PJ","RJ","UA","UP","UR"};
        subt22=new JComboBox(items);
        subt22.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        //subt22.setAutoscrolls(true);
        //subt22.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);       
        //subt22.setOpaque(true);
        //subt22.setDoubleBuffered(true);
		//subt22.addItemListener(this);
		//subt22.setPopupVisible(true);

		dt1=new JComboBox();
		subtt1=new JComboBox();
		langt1=new JComboBox();
		paytt1=new JComboBox();
		
		
		chddt1=new JTextField(9);
		datt1=new TextFieldWithLimit(2,2);
		datt2=new TextFieldWithLimit(2,2);
		datt3=new TextFieldWithLimit(4,4);
		amt1=new TextFieldWithLimit(4,4);
		//startm1=new TextFieldWithLimit(2,2);
		//starty1=new TextFieldWithLimit("",4,4);
		endt1=new TextFieldWithLimit(10,10);
		titt1=new TextFieldWithLimit(3,3);
		namt1=new TextFieldWithLimit(16,16);
		lnamt1=new TextFieldWithLimit(15,15);
		addt11=new TextFieldWithLimit(32,32);
		addt21=new TextFieldWithLimit(32,32);
		addt31=new TextFieldWithLimit(32,32);
		dist21=new TextFieldWithLimit(22,22);
		statt1=new TextFieldWithLimit(3,3);
		pint1=new TextFieldWithLimit(6,6);
		telt1=new TextFieldWithLimit(12,12);
		
		emailt1=new TextFieldWithLimit(32,32);
		rett1=new JTextField(5);
		remt1=new TextArea(85,3);
		
		histt1=new JComboBox();
		
		histt1.addItem("Kirpal Bagh");
		histt1.addItem("Kirpal Ashram");
		histt1.addItem("Sawan Ashram");
//		histt1.addItem("Darshan Dham");
//		histt1.addItem("Tours/Function");
		
		
		sav=new JButton("Save");
		back=new JButton("Back");

		//-----------------------------------------modififcations------------------------------------------//
		
		try
		{
			dt1.addItem("0");
			connect c13=new connect();
			c13.rs=c13.st.executeQuery("select dno from despcode");
			while(c13.rs.next())
			{
				dt1.addItem(""+c13.rs.getInt(1));
			}
			
			c13.st.close();
			c13.con.close();
			
		}
		catch(Exception e)
		{
			
		}
		dt1.addItemListener(this);
		
		startm1=new JComboBox();
		starty1=new JComboBox();
		
		for(int s=1;s<13;s++)
		{
			startm1.addItem(""+s);
		}
		
		GregorianCalendar g=new GregorianCalendar();
		s2=g.get(Calendar.YEAR);
		
		for(int s1=(s2-2);s1<(s2+10);s1++)
		{
			starty1.addItem(""+s1);
		}
		
		starty1.setSelectedItem(""+s2);
		int s3=g.get(Calendar.MONTH)+1;
		
		startm1.setSelectedItem(""+s3);
		startm1.addItemListener(this);
		starty1.addItemListener(this);
		
		//------------------------------------------------end------------------------------------------------//
		
		
		distt1.addItem("By Hand");
		distt1.addItem("By Post");
		distt1.addItem("Distributor");
		
		
		
		subtt1.addItem("1 year");
		subtt1.addItem("2 year");
		subtt1.addItem("2 year plus");
		subtt1.addItem("3 year");
		subtt1.addItem("3 year plus");
		subtt1.addItem("5 year");
		subtt1.addItem("5 year plus");
		subtt1.addItem("Life");
		subtt1.addItem("Comp");
			
		
		langt1.addItem("Hindi");
		langt1.addItem("English");
		langt1.addItem("Urdu");
		langt1.addItem("Punjabi");

		paytt1.addItem("Cash");
		paytt1.addItem("CH/DD/MO");

		p1.add(subd1);
		subd1.setBounds(30,10,200,30);
		
		p1.add(asn1);
		asn1.setBounds(30,45,30,20);
		
		p1.add(asnt1);
		asnt1.setBounds(140,45,60,20);
		try
		{
			connect c1=new connect();
			c1.rs=c1.st.executeQuery("select * from asn");

			while(c1.rs.next())
			{
				x=c1.rs.getInt(1);
			}
			
			c1.st.close();
			c1.con.close();
		}
		catch(Exception e1)
		{
			
		}
		
		
		asnt1.setText("SN"+x);
		asnt1.setEnabled(false);
		asnt1.setFont(f);
		
		statust1.setText("Active");
		statust1.setEnabled(false);
		statust1.setFont(f);
			
		
		p1.add(sub1);
		sub1.setBounds(280,45,80,20);
		
		
		subt22.addItemListener(this);
		subt22.setBounds(330,45,50,20);
        p1.add(subt22);
		
		
		subt21.setBounds(380,45,60,20);
        p1.add(subt21);
				
		p1.add(status1);
		status1.setBounds(480,45,40,20);
		
		p1.add(statust1);
		statust1.setBounds(600,45,80,20);
		
		p1.add(rec1);
		rec1.setBounds(750,45,100,20);
		
		p1.add(rect1);
		rect1.setBounds(870,45,40,20);
				
		p1.add(dist1);
		dist1.setBounds(30,75,100,20);
		
		p1.add(distt1);
		distt1.addItemListener(this);
		distt1.setBounds(140,75,100,20);
		
		p1.add(d1);
		d1.setBounds(280,75,40,20);
		
		p1.add(dt1);
		dt1.setEnabled(false);
		dt1.setFont(f);
		dt1.setSelectedItem("0");
		dt1.setBounds(330,75,50,20);
		
		p1.add(lang1);
		lang1.setBounds(480,75,110,20);
		
		p1.add(langt1);
		langt1.setBounds(600,75,80,20);
		langt1.addItemListener(this);
		
		p1.add(subt1);
		subt1.setBounds(750,75,110,20);
		
		p1.add(subtt1);
		subtt1.setBounds(870,75,100,20);
		subtt1.addItemListener(this);
		
		p2.add(pay1);
		pay1.setBounds(30,0,200,30);
		
		p2.add(payt1);
		payt1.setBounds(30,45,130,20);
		
		
		p2.add(paytt1);
		paytt1.setBounds(130,45,100,20);
		paytt1.addItemListener(this);
		
		p2.add(chdd1);
		chdd1.setBounds(250,45,100,20);
		
		p2.add(chddt1);
		chddt1.setBounds(350,45,60,20);
		chddt1.setEnabled(false);
		chddt1.setFont(f);
		chddt1.setText("0");
		
		p2.add(dat1);
		dat1.setBounds(430,45, 30,20);
		
		p2.add(datt1);
		datt1.setBounds(470,45,25,20);
		
		p2.add(datt2);
		datt2.setBounds(500,45,25,20);
		
		p2.add(datt3);
		datt3.setBounds(530,45,40,20);
		
		
		p2.add(am1);
		am1.setBounds(580,45,50,20);
		
		p2.add(amt1);
		amt1.setBounds(640,45,40,20);
		amt1.setEnabled(false);
		
		amt1.setFont(f);
		amt1.setText("100");
		
		p2.add(starm1);
		starm1.setBounds(700,45,100,20);
		
		p2.add(stary1);
		stary1.setBounds(840,45,10,20);
		
		p2.add(startm1);
		startm1.setBounds(790,45,45,20);
		
		p2.add(starty1);
		starty1.setBounds(865,45,60,20);
		
		//------------------------------------------------start------------------------------------------------//
		
		  p2.add(end1);
		  end1.setBounds(30,75,100,20);
			
		  p2.add(endt1);
		  endt1.setBounds(130,75,70,20);
		  endt1.setEnabled(false);
		  endt1.setFont(f);
		//------------------------------------------------end------------------------------------------------//
		
		p3.add(mem1);
		mem1.setBounds(30,0,200,30);
		
		p3.add(tit1);
		tit1.setBounds(30,45,50,20);
		
		p3.add(titt1);
		titt1.setBounds(80,45,30,20);
		
		p3.add(nam1);
		nam1.setBounds(120,45,40,20);
		
		p3.add(namt1);
		namt1.setBounds(160,45,145,20);
		
		p3.add(lnam1);
		lnam1.setBounds(310,45,40,20);
		
		p3.add(lnamt1);
		lnamt1.setBounds(350,45,145,20);
		
		p3.add(add1);
		add1.setBounds(520,45,60,20);
		
		p3.add(addt11);
		addt11.setBounds(600,45,240,20);
		p3.add(addt21);
		addt21.setBounds(600,70,240,20);
		p3.add(addt31);
		addt31.setBounds(600,95,240,20);
		
		p3.add(dis1);
		dis1.setBounds(30,110,60,20);
		
		p3.add(dist21);
		dist21.setBounds(90,110,240,20);
				
		p3.add(stat1);
		stat1.setBounds(340,110,40,20);
		
		p3.add(statt1);
		statt1.setBounds(380,110,40,20);
		
		p3.add(pin1);
		pin1.setBounds(430,110,50,20);
		
		p3.add(pint1);
		pint1.setText("0");
		pint1.setBounds(480,110,50,20);
				
		p4.add(oth1);
		oth1.setBounds(30,10,200,30);
			
		p4.add(tel1);
		tel1.setBounds(30,45,80,20);
		
		p4.add(telt1);
		telt1.setBounds(110,45,110,20);
		
		p4.add(hist1);
		hist1.setBounds(240,45,60,20);
		
		p4.add(histt1);
		histt1.setBounds(300,45,150,20);

		p4.add(email1);
		//emailt1.setEnabled(false);
		email1.setBounds(30,105,50,20);
		
		
		
		p4.add(emailt1);
		//emailt1.setEnabled(false);
		emailt1.setBounds(110,105,380,20);
		//setBounds(550,45,240,20);
		
		p4.add(ret1);
		ret1.setBounds(30,75,90,20);
		
		p4.add(rett1);
		//rett1.setEnabled(false);
		rett1.setText("0");
		rett1.setBounds(110,75,80,20);
		
		p4.add(rem1);
		rem1.setBounds(470,45,60,20);
		
		p4.add(remt1);
		
		remt1.setBounds(550,45,440,80);
		
		p5.add(sav);
		sav.setBounds(340,10,100,30);
		sav.setMnemonic('S');
		
		/*p5.add(mod);
		mod.setMnemonic('M');
		mod.setBounds(220,10,100,30);
		
		p5.add(clear);
		clear.setMnemonic('C');
		clear.setBounds(340,10,100,30);
		*/
		p5.add(back);
		back.setMnemonic('B');
		back.setBounds(460,10,100,30);
		
		sav.addActionListener(this);
		//mod.addActionListener(this);
		//clear.addActionListener(this);
		back.addActionListener(this);
		
		//p4.add(histt1);
		
		add(p1);
		add(p2); 
		add(p3);
		add(p4);
		add(p5);


		setVisible(true);

	}

	
	
	public void actionPerformed(ActionEvent ae)
	{
		//database updation
		if(ae.getSource()==sav)
		{
			try
			{
				flag=0;
				int asn, rcpt,subno, dno;
				String sno1, subno1, dist, subt, lang,status;
				
				//basic info
								
				asn=x;
				
				rcpt=Integer.parseInt(rect1.getText());
				dno=Integer.parseInt((String)dt1.getSelectedItem());
				subno1=(String)subt22.getSelectedItem();
				subno=Integer.parseInt(subt21.getText());
				dist=(String)distt1.getSelectedItem() ;
				subt=(String)subtt1.getSelectedItem();
				lang=(String)langt1.getSelectedItem();
				status=statust1.getText();
					
				
				//payment details
				
				int chno, amt, dat1, dat2, dat3, startm, starty, endm, endy;
				String payt;
				
				chno=Integer.parseInt(chddt1.getText());
				amt=Integer.parseInt(amt1.getText());
				
				payt=(String)paytt1.getSelectedItem();
				dat1=Integer.parseInt(datt1.getText());
				dat2=Integer.parseInt(datt2.getText());
				dat3=Integer.parseInt(datt3.getText());
				
				startm=Integer.parseInt((String)startm1.getSelectedItem());
				starty=Integer.parseInt((String)starty1.getSelectedItem());
				
				/*
				String x1=endt1.getText();
				endm=Integer.parseInt(x1.substring(0,x1.lastIndexOf('/')-1));
				endy=Integer.parseInt(x1.substring(x1.lastIndexOf('/'),x1.length()));
				
				
				int period=0, period1=0;
				try
				{
					
					connect c9=new connect();
					c9.rs=c9.st.executeQuery("select * from amountdet where duration='"+subtt1.getSelectedItem()+"'");
					while(c9.rs.next())
					{
						period=c9.rs.getInt(4);
						period1=c9.rs.getInt(5);
					}
					
					c9.st.close();
					c9.con.close();
				}
				catch(Exception e)
				{
					
				}
				
				endm=((startm-1)+period1)%12;
				if(endm==0)
					endm=12;
				if(endm<12)
					endy=starty+period;
				else
					endy=starty+period-1;
				*/
				
				endm=endm2;
				endy=endy2;
				//subscriber details
				
				String title, fname, lname, add1, add2, add3, dist2, state;
				int pin;
				
				title=titt1.getText();
				fname=namt1.getText();
				lname=lnamt1.getText();
				add1=addt11.getText();
				add2=addt21.getText();
				add3=addt31.getText();
				dist2=dist21.getText();
				state=statt1.getText();
				pin=Integer.parseInt(pint1.getText());
			
				//other details
				
				int return1;
				String phone, history, email, remarks;
				
				
				phone=telt1.getText();
				history=(String)histt1.getSelectedItem();
				email=emailt1.getText();
				return1=Integer.parseInt(rett1.getText());
				remarks=remt1.getText();
				
				
				//database query for basic fragment
				
				connect c2=new connect();
				c2.a=c2.st.executeUpdate("insert into basic values("+asn+",'"+subno1+"',"+subno+",'"+status+"',"+rcpt+",'"+dist+"',"+dno+",'"+subt+"','"+lang+"')");
				
				if(c2.a==1)
					flag=flag+1;
				c2.st.close();
				c2.con.close();
				
				if(dno>0)
				{
					connect c10=new connect();
					c10.a=c10.st.executeUpdate("insert into d"+dno+" values ("+asn+")");
					c10.st.close();
					c10.con.close();
				}
				
				//database query for payment fragment
				
				connect c3=new connect();
				c3.a=c3.st.executeUpdate("insert into payment values("+asn+",'"+payt+"',"+chno+","+dat1+","+dat2+","+dat3+","+amt+","+startm+","+starty+","+endm+","+endy+" , '"+SamsAddons.getCurrentSqlDate()+"')");
				if(c3.a==1)
					flag=flag+1;
				c3.st.close();
				c3.con.close();
				
				//database query for subscription details fragment
				
				connect c4=new connect();
				c4.a=c4.st.executeUpdate("insert into subdetails values ("+asn+",'"+title+"','"+fname+"','"+lname+"','"+add1+"','"+add2+"','"+add3+"','"+dist2+"','"+state+"',"+pin+")");
				if(c4.a==1)
					flag=flag+1;
				c4.st.close();
				c4.con.close();
				
				//database query for other details fragment
				
				connect c5=new connect();
				c5.a=c5.st.executeUpdate("insert into otherdet values("+asn+",'"+phone+"','"+history+"','"+email+"',"+return1+",'"+remarks+"','','','')");
				
				if(c5.a==1)
					flag=flag+1;
				c5.st.close();
				c5.con.close();
				
				
			
				if(flag==4)
				{
					int z=0;
					z=x+1;
					connect c6=new connect();
					c6.a=c6.st.executeUpdate("update asn set asn="+z+" where asn="+x);
					c6.st.close();
					c6.con.close();
					asnt1.setText("SN"+z);
					
					flag=0;
				}
				

                setVisible(false);
				this.dispose();

				
				t=new Thread(this);
				//t.start();
				Thread.sleep(3000);
				
				new entry();
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
	
			}
		}
		
		//clear button
		if(ae.getSource()==clear)
		{
					subt21.setText("");
					statust1.setText("Active");
					rect1.setText("");
					distt1.setSelectedItem("By Hand");
					dt1.setEnabled(false);
					dt1.setSelectedItem("0");
					subtt1.setSelectedItem("1 year");
					langt1.setSelectedItem("Hindi");
					paytt1.setSelectedItem("Cash");
					chddt1.setText("0");
					datt1.setText("");
					datt2.setText("");
					datt3.setText("");
					amt1.setText("100");
					startm1.setSelectedItem("1");
					starty1.setSelectedItem("s2");
					endt1.setText("");
					titt1.setText("");
					namt1.setText("");
					lnamt1.setText("");
					dist21.setText("");
					statt1.setText("");
					pint1.setText("0");
					telt1.setText("");
					histt1.setSelectedIndex(1);
					emailt1.setText("");
					rett1.setText("0");
					rett1.setEnabled(false);
					remt1.setText("");
					addt11.setText("");
					addt21.setText("");
					addt31.setText("");
			
		}
		
		if(ae.getSource()==back)
		{
            setVisible(false);
			new sams();
			this.dispose();
			
		}
		
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==dt1)
		{
			try
			{
				connect c14=new connect();
				c14.rs=c14.st.executeQuery("select district, state from despcode where dno="+Integer.parseInt((String)dt1.getSelectedItem()));
				c14.rs.next();
				dist21.setText(""+c14.rs.getString(1));
				dist21.setEnabled(false);
				dist21.setFont(f);
				statt1.setText(""+c14.rs.getString(2));
				statt1.setEnabled(false);
				statt1.setFont(f);
				c14.st.close();
				c14.con.close();
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "ERROR : "+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if(ie.getSource()==distt1)
		{
			if(distt1.getSelectedItem()=="Distributor")
			{
				dt1.setEnabled(true);
				//dt1.select("1");
				//dt1.select(1);
			}
			
			if(distt1.getSelectedItem()=="By Hand" || distt1.getSelectedItem()=="By Post")
			//else
			{
				dt1.setEnabled(false);
				dt1.setSelectedItem("0");
			}
			
		}
		
		if(ie.getSource()==subtt1)
		{
			try
			{
				connect c7=new connect();
				c7.rs=c7.st.executeQuery("select * from amountdet where language='"+langt1.getSelectedItem()+"' and duration='"+subtt1.getSelectedItem()+"'");
								
				while(c7.rs.next())
				{
					amount=c7.rs.getInt(3);
				}
				
				amt1.setText(""+amount);
			
				c7.st.close();
				c7.con.close();
				
				if(subtt1.getSelectedItem()=="Urdu"&&subtt1.getSelectedItem()=="Punjabi")
				{
					amt1.setEnabled(true);
					amt1.setText("");
				}
			}
			
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if(ie.getSource()==langt1)
		{
			try
			{
				connect c8=new connect();
				
				c8.rs=c8.st.executeQuery("select * from amountdet where language='"+langt1.getSelectedItem()+"'");
				
				subtt1.removeAll();
				while(c8.rs.next())
				{
					subtt1.addItem(c8.rs.getString(2));
				}
				
				c8.st.close();
				c8.con.close();
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);				
			}
		}
		
		if(ie.getSource()==paytt1)
		{
			if(paytt1.getSelectedItem()=="Cash")
			{
				chddt1.setText("0");
				chddt1.setEnabled(false);
			}
			
			if(paytt1.getSelectedItem()=="CH/DD/MO")
			{
				chddt1.setText("");
				chddt1.setEnabled(true);
			}
		}
		
		if(ie.getSource()==startm1 || ie.getSource()==starty1)
		{
			int startm2=Integer.parseInt((String)startm1.getSelectedItem());
			int starty2=Integer.parseInt((String)starty1.getSelectedItem());
				
			int period2=0, period12=0;
			try
			{
				
				connect c22=new connect();
				c22.rs=c22.st.executeQuery("select * from amountdet where duration='"+subtt1.getSelectedItem()+"'");
				while(c22.rs.next())
				{
					period2=c22.rs.getInt(4);
					
					period12=c22.rs.getInt(5);
					
					//System.out.println("year : "+period2+" month : "+period12);
				}
				
				c22.st.close();
				c22.con.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);				
			}
			
			endm2=((startm2-1)+period12)%12;
			//endy2;
			int flag=0;
			if(endm2==0)
				endm2=12;
			if(endm2>0 && endm2 <period12)
				flag++;
			if(endm2<12)
				endy2=starty2+period2+flag;
			else
				endy2=starty2+period2-1+flag;
			
			if(endm2==12 && period12>0)
			{
				endy2++;
			}
			
			
			endt1.setText(""+endm2+"/"+endy2);
			
		}
		
		if(ie.getSource()==subt22)
		{
			if(subt22.getSelectedItem()=="DL")
			{
				statt1.setText("DL");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
				
			}
			
				
			if(subt22.getSelectedItem()=="HR")
			{
				statt1.setText("HAR");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
			
			if(subt22.getSelectedItem()=="MH")
			{
				statt1.setText("MAH");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
			
			if(subt22.getSelectedItem()=="MP")
			{
				statt1.setText("MP");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
			
			
			if(subt22.getSelectedItem()=="PB")
			{
				statt1.setText("PJB");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
				
			if(subt22.getSelectedItem()=="RJ")
			{	statt1.setText("RAJ");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
				
			if(subt22.getSelectedItem()=="UA")
			{
				statt1.setText("UA");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
				
			if(subt22.getSelectedItem()=="UP")
			{
				statt1.setText("UP");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
			}
				
				
				
			if(subt22.getSelectedItem()=="LF")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
				
			}
				
			if(subt22.getSelectedItem()=="BH")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(false);
				
			}
				
			if(subt22.getSelectedItem()=="MS")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
				
			}
				
			if(subt22.getSelectedItem()=="LH")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(false);
				
			}
			
			if(subt22.getSelectedItem()=="CM")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
				
			}
			
			if(subt22.getSelectedItem()=="EN")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
                                langt1.setSelectedItem("English");
				distt1.setEnabled(true);
				
			}
			
			if(subt22.getSelectedItem()=="UR")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(true);
				
			}
				
			if(subt22.getSelectedItem()=="BD")
			{
			
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("Distributor");
				distt1.setEnabled(false);
				
			}
				
			if(subt22.getSelectedItem()=="PJ")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(true);
				
			}
					
		}		
		
	}
	
	public void run()
	{
		try
		{
			//t.sleep(3000);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}