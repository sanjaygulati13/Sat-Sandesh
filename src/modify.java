import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//done till sub no, nd status


public class modify extends JFrame implements ActionListener, ItemListener
{
	public static void main(String args[])
	{
		new modify(10410);
	}	
	
	JLabel  subd1, asn1, sub1, status1, rec1, dist1, d1, subt1, lang1;				//subscription details
	JLabel pay1, payt1, chdd1, dat1, am1, starm1,stary1, end1;						//payment details
	JLabel mem1, tit1, nam1, lnam1, add1, dis1, stat1, pin1;						//member details
	JLabel oth1, tel1, hist1, email1, ret1, rem1; 									//other details
	
	Font f=new Font("ARIAL", Font.BOLD, 12);
	JPanel p1, p2, p3, p4,p5;
	
	
	JTextField asnt1, statust1 /*, distt1, subtt1 , langt1*/;						//subscription details
	private final JComboBox dt1, distt1, subt22, subtt1, langt1;
	TextFieldWithLimit subt21, rect1 ;
		
		
	JTextField /*paytt1,*/ chddt1;											//payment details
	TextFieldWithLimit datt1, datt2, datt3, amt1,starty1, startm1, endt1;
	JComboBox paytt1;
		
																
	TextFieldWithLimit titt1, namt1, lnamt1, statt1, pint1,addt11,addt21,addt31,dist21;	//member details
	
	
	JTextField   rett1;											//other details
	TextArea remt1;
	TextFieldWithLimit telt1,emailt1;
	JComboBox histt1;
	
	JButton renew, mod, clear, back;
	int x, amount;
	String str;
	int flag=0;
				
	public modify(int num)
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		setTitle("Modify Record");
		setSize(1000,690);
		//this.setSize(this.getToolkit().getScreenSize());
		setResizable(false);
		setLocation(10,10);
		setLayout(new GridLayout(5,1));	
		
		
		p1=new JPanel();
		p1.setLayout(null);
		p2=new JPanel();
		p2.setLayout(null);
		p3=new JPanel();
		p3.setLayout(null);
		p4=new JPanel();
		p4.setLayout(null);
		p5=new JPanel();
		p5.setLayout(null);
		
		
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
		subt22=new JComboBox();
		
		
		dt1=new JComboBox();
		subtt1=new JComboBox();
		langt1=new JComboBox();
		dt1.addItemListener(this);

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
            new except(e, this.getClass().toString());
            e.printStackTrace();
        }
		
		paytt1=new JComboBox();
		
		chddt1=new JTextField(9);
		datt1=new TextFieldWithLimit(2,2);
		datt2=new TextFieldWithLimit(2,2);
		datt3=new TextFieldWithLimit(4,4);
		amt1=new TextFieldWithLimit(4,4);
		startm1=new TextFieldWithLimit(2,2);
		starty1=new TextFieldWithLimit("",4,4);
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
		histt1=new JComboBox();
		emailt1=new TextFieldWithLimit(32,32);
		rett1=new JTextField(5);
		remt1=new TextArea(85,3);
		
		//renew=new JButton("Modify");
		mod=new JButton("Modify");
		//clear=new JButton("Clear");
		back=new JButton("Back");
		
		histt1.addItem("Kirpal Bagh");
		histt1.addItem("Kirpal Ashram");
		histt1.addItem("Sawan Ashram");
//		histt1.addItem("Dharshan Dham");
//		histt1.addItem("Tours/Function");
//		
		subt22.setDoubleBuffered(true);
        subt22.setOpaque(true);

		subt22.addItem("BH");
		subt22.addItem("BD");
		subt22.addItem("CM");
		subt22.addItem("DL");
		subt22.addItem("EN");
		subt22.addItem("HR");
		subt22.addItem("LF");
		subt22.addItem("LH");
		subt22.addItem("MH");
		subt22.addItem("MP");
		subt22.addItem("MS");
		subt22.addItem("PB");
		subt22.addItem("PJ");
		subt22.addItem("RJ");
		subt22.addItem("UA");
		subt22.addItem("UP");
		subt22.addItem("UR");
		
		subt22.addItemListener(this);
		
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
		
		//_______________________________________________________________________________________

		p1.add(subd1);
		subd1.setBounds(30,10,200,30);
		
		p1.add(asn1);
		asn1.setBounds(30,45,30,20);
		
		p1.add(asnt1);
		asnt1.setBounds(140,45,60,20);
		int d=0;
		try
		{
			connect c1=new connect();
			c1.rs=c1.st.executeQuery("select * from basic where asn="+num);
			c1.rs.next();
			subt22.setSelectedItem(c1.rs.getString(2));
			subt21.setText(c1.rs.getString(3));
			statust1.setText(c1.rs.getString(4));
			rect1.setText(""+c1.rs.getInt(5));
			String s1=""+c1.rs.getString(6);
			if(s1.equals("BY POST") || s1.equals("by post"))
				s1="By Post";
			if(s1.equals("BY HAND") || s1.equals("by hand"))
				s1="By Hand";
			if(s1.equals("DISTRIBUTOR") || s1.equals("distributor"))	
				s1="Distributor";
			
			distt1.setSelectedItem(s1);
			d=c1.rs.getInt(7);
			dt1.setSelectedItem(""+d);
			subtt1.setSelectedItem(c1.rs.getString(8));
			langt1.setSelectedItem(c1.rs.getString(9));	
			
			
			c1.st.close();
			c1.con.close();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "ERROR"+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		x=num;
		
		asnt1.setText("SN"+x);
		asnt1.setEnabled(false);
		asnt1.setFont(f);
		
		p1.add(sub1);
		sub1.setBounds(280,45,80,20);
		
		p1.add(subt22);
		subt22.addItemListener(this);
		subt22.setBounds(330,45,50,20);
		//subt22.setEnabled(false);
		
		
		p1.add(subt21);
		//subt21.setEnabled(false);
		subt21.setBounds(380,45,60,20);
		
		p1.add(status1);
		status1.setBounds(480,45,40,20);
		
		p1.add(statust1);
		statust1.setEnabled(false);
		statust1.setFont(f);
		statust1.setBounds(600,45,80,20);
		
		p1.add(rec1);
		rec1.setBounds(750,45,100,20);
		
		p1.add(rect1);
		rect1.setBounds(860,45,60,20);
		//rect1.setEnabled(false);
		rect1.setFont(f);
				
		p1.add(dist1);
		dist1.setBounds(30,75,100,20);
		
		p1.add(distt1);
		distt1.addItemListener(this);
		distt1.setBounds(140,75,100,20);
				
		p1.add(d1);
		d1.setBounds(280,75,40,20);
		
		p1.add(dt1);
		if(d==0)
		dt1.setEnabled(false);
		dt1.setFont(f);
		
		dt1.setBounds(330,75,50,20);
		
		p1.add(lang1);
		lang1.setBounds(480,75,110,20);
		
		p1.add(langt1);
		langt1.setBounds(600,75,80,20);
		langt1.addItemListener(this);
		langt1.setEnabled(false);
		langt1.setFont(f);
		
		p1.add(subt1);
		subt1.setBounds(750,75,100,20);
		
		p1.add(subtt1);
		subtt1.setBounds(860,75,100,20);
		subtt1.addItemListener(this);
		subtt1.setEnabled(false);
		subtt1.setFont(f);
				
		//_______________________________________________________________________________________	
		try
		{	
		connect c10=new connect();
		c10.rs=c10.st.executeQuery("select * from payment where asn="+x);
		c10.rs.next();
		
		paytt1.setSelectedItem(c10.rs.getString(2));
		chddt1.setText(""+c10.rs.getInt(3));
		datt1.setText(""+c10.rs.getInt(4));
		datt2.setText(""+c10.rs.getInt(5));
		datt3.setText(""+c10.rs.getInt(6));
		amt1.setText(""+c10.rs.getInt(7));
		startm1.setText(""+c10.rs.getInt(8));
		starty1.setText(""+c10.rs.getInt(9));
		endt1.setText(""+c10.rs.getInt(10)+"/"+c10.rs.getInt(11));
		//startm1.setText(""+(c10.rs.getInt(10)+1));
		//starty1.setText(""+c10.rs.getInt(11));
			
		
		c10.st.close();
		c10.con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "ERROR"+e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		p2.add(pay1);
		pay1.setBounds(30,0,200,30);
		
		p2.add(payt1);
		payt1.setBounds(30,45,130,20);
		
		
		p2.add(paytt1);
		paytt1.setBounds(130,45,100,20);
		paytt1.addItemListener(this);
		paytt1.setEnabled(false);
		paytt1.setFont(f);
		
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
		//datt1.setEnabled(false);
		datt1.setFont(f);
		
		p2.add(datt2);
		datt2.setBounds(500,45,25,20);
		//datt2.setEnabled(false);
		datt2.setFont(f);
		
		p2.add(datt3);
		datt3.setBounds(530,45,40,20);
		//datt3.setEnabled(false);
		datt3.setFont(f);
		
		p2.add(am1);
		am1.setBounds(580,45,50,20);
		
		p2.add(amt1);
		amt1.setBounds(640,45,40,20);
		amt1.setEnabled(false);
		amt1.setFont(f);
		//amt1.setText("100");
		
		p2.add(starm1);
		starm1.setBounds(700,45,100,20);
		
		p2.add(stary1);
		stary1.setBounds(830,45,10,20);
		
		p2.add(startm1);
		startm1.setBounds(800,45,25,20);
		startm1.setEnabled(false);
		startm1.setFont(f);
		
		p2.add(starty1);
		starty1.setBounds(845,45,50,20);
		starty1.setEnabled(false);
		starty1.setFont(f);
		
		
		 p2.add(end1);
		 end1.setBounds(30,75,100,20);	
		 	
		 p2.add(endt1);
		 endt1.setBounds(130,75,80,20);
		 endt1.setEnabled(false);
		 endt1.setFont(f);
		
		//_______________________________________________________________________________________
		
		try
		{
			connect c2=new connect();
			c2.rs=c2.st.executeQuery("select * from subdetails where asn="+x);

			while(c2.rs.next())
			{
				titt1.setText(c2.rs.getString(2));
				namt1.setText(c2.rs.getString(3));
				lnamt1.setText(c2.rs.getString(4));
				addt11.setText(c2.rs.getString(5));
				addt21.setText(c2.rs.getString(6));
				addt31.setText(c2.rs.getString(7));
				dist21.setText(c2.rs.getString(8));
				statt1.setText(c2.rs.getString(9));
				pint1.setText(""+c2.rs.getInt(10));
	
					
				
			}
			
			c2.st.close();
			c2.con.close();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "ERROR"+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		p3.add(mem1);
		mem1.setBounds(30,0,200,30);
		
		p3.add(tit1);
		tit1.setBounds(30,45,50,20);
		
		p3.add(titt1);
		//titt1.setEnabled(false);
		titt1.setBounds(80,45,30,20);
	
		p3.add(nam1);
		nam1.setBounds(120,45,40,20);
		
		p3.add(namt1);
		namt1.setBounds(160,45,145,20);
		
		p3.add(lnam1);
		lnam1.setBounds(310,45,40,20);
		
		p3.add(lnamt1);
		lnamt1.setBounds(350,45,145,20);
		
		
		add1.setBounds(520,45,60,20);
        p3.add(add1);
		
		
		addt11.setBounds(600,45,240,20);
        p3.add(addt11);
        
		p3.add(addt21);
		addt21.setBounds(600,70,240,20);

		p3.add(addt31);
		addt31.setBounds(600,95,240,20);

		
		
		p3.add(dis1);
		dis1.setBounds(30,115,60,20);
		
		p3.add(dist21);
		//dist21.setEnabled(false);
		dist21.setBounds(90,115,240,20);
				
		p3.add(stat1);
		
		stat1.setBounds(340,115,40,20);
		
		p3.add(statt1);
		//statt1.setEnabled(false);
		statt1.setFont(f);
		statt1.setBounds(375,115,40,20);
		
		p3.add(pin1);
		pin1.setBounds(420,115,50,20);
		
		p3.add(pint1);
		//pint1.setEnabled(false);
		pint1.setBounds(480,115,50,20);
		
		//_______________________________________________________________________________________
				
				
		try
		{
			
			connect c3=new connect();
			c3.rs=c3.st.executeQuery("select * from otherdet where asn="+x);
			
			while(c3.rs.next())
			{
				telt1.setText(c3.rs.getString(2));
				histt1.setSelectedItem(c3.rs.getString(3));
				emailt1.setText(c3.rs.getString(4));
				rett1.setText(""+c3.rs.getString(5));
				remt1.setText(c3.rs.getString(6));
			}
			
			c3.st.close();
			c3.con.close();
			
		}	
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "ERROR"+e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
			
				
		p4.add(oth1);
		oth1.setBounds(30,10,200,30);
			
		p4.add(tel1);
		tel1.setBounds(30,45,80,20);
		
		p4.add(telt1);
		//telt1.setEnabled(false);
		telt1.setBounds(110,45,110,20);
		
		p4.add(hist1);
		hist1.setBounds(240,45,60,20);
		
		p4.add(histt1);
		//histt1.setEnabled(false);
		histt1.setFont(f);
		histt1.setBounds(300,45,150,20);

		p4.add(email1);
		email1.setBounds(30,105,60,20);
		//setBounds(470,45,60,20);
		
		
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
		
		remt1.setBounds(550,45,420,80);
		
		//_______________________________________________________________________________________
		
		
		p5.add(mod);
		mod.setMnemonic('M');
		mod.setBounds(220,10,100,30);
		
		
		p5.add(back);
		back.setMnemonic('B');
		back.setBounds(460,10,100,30);
		
		mod.addActionListener(this);
		back.addActionListener(this);
		
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
		if(ae.getSource()==mod)
		{
			try
			{
				
				int asn, rcpt,subno, dno;
				String sno1, subno1, dist, subt, lang,status;
				
				//basic info
								
				asn=x;
				subno1=(String)subt22.getSelectedItem();
				subno=Integer.parseInt(subt21.getText());
				dist=(String)distt1.getSelectedItem();
				dno=Integer.parseInt((String)dt1.getSelectedItem());
				
				rcpt=Integer.parseInt(rect1.getText());
				
				/*
				
				subt=subtt1.getSelectedItem();
				lang=langt1.getSelectedItem();
				status=statust1.getText();
					
				*/
				//payment details
				
				//int chno, amt, dat1, dat2, dat3, startm, starty, endm, endy;
				//String payt;
                                  
                                int dat1, dat2, dat3;
				
                                
                                /*
				chno=Integer.parseInt(chddt1.getText());
				amt=Integer.parseInt(amt1.getText());
				*/
				//payt=paytt1.getSelectedItem();
				dat1=Integer.parseInt(datt1.getText());
				dat2=Integer.parseInt(datt2.getText());
				dat3=Integer.parseInt(datt3.getText());
				/*
				startm=Integer.parseInt(startm1.getText());
				starty=Integer.parseInt(starty1.getText());
				
				int period=0, period1=0, endm1=0, endy1=0;
				
				try
				{
					
					connect c9=new connect();
					c9.rs=c9.st.executeQuery("select * from amountdet where duration='"+subtt1.getSelectedItem()+"'");
					while(c9.rs.next())
					{
						period=c9.rs.getInt(4);
						period1=c9.rs.getInt(5);
					}
					
					
					
					
					c9.rs=c9.st.executeQuery("select * from payment where asn="+asn);
					while(c9.rs.next())
					{
						endm1=c9.rs.getInt(10);
						endy1=c9.rs.getInt(11);
					}
					
					System.out.println("end "+endm1+endy1);
					
					c9.st.close();
					c9.con.close();
					
				}
				catch(Exception e)
				{
					
				}
				
				String str1, str2, str3;
				GregorianCalendar g= new GregorianCalendar();
				str1=String.valueOf(g.get(Calendar.DATE));
				str2=String.valueOf(g.get(Calendar.MONTH)+1);
				str3=String.valueOf(g.get(Calendar.YEAR));
				
				System.out.println("Date "+str1+"/"+str2+str3);
				
				int year=Integer.parseInt(str3);
				int month=Integer.parseInt(str2);
				
				System.out.println(" current YM "+year+"-"+month);
				
				System.out.println(endy1>=year);
				System.out.println(endm1>=month);
				
				if((endy1==year && endm1>month) || endy1>year)
				{
					startm=endm1;
					starty=endy1;
					System.out.println("New starting period "+startm+"-"+starty);
					
				}
				
				endm=((startm-1)+period1)%12;
				if(endm==0)
					endm=12;
				if(endm<12)
					endy=starty+period;
				else
					endy=starty+period-1;
				*/
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
				//System.out.println(phone);
				history=(String)histt1.getSelectedItem();
				email=emailt1.getText();
				return1=Integer.parseInt(rett1.getText());
				remarks=remt1.getText();
				//System.out.println(remarks);
				String newline=System.getProperty("line.seperator");
				
				remarks=remarks.replaceAll("'", ",");
				//System.out.println(remarks);
				
				
				//database query for basic fragment
				
				connect c14=new connect();
				c14.a=c14.st.executeUpdate("update basic set subnos='"+subno1+"', subno="+subno+" ,rcpt="+rcpt+", dist='"+dist+"', dno="+dno+" where asn="+asn);
				
				if(c14.a==1)
					flag=flag+1;
				c14.st.close();
				c14.con.close();
				
				
				//database query for payment fragment
				
				connect paymentConn=new connect();
				//c5.a=c5.st.executeUpdate("update payment set payt='"+payt+"', chno="+chno+", datd="+dat1+", datm="+dat2+", daty="+dat3+", amt="+amt+", startm="+startm+", starty="+starty+", endm="+endm+", endy="+endy+" where asn="+asn);
                                paymentConn.a=paymentConn.st.executeUpdate("update payment set datd="+dat1+", datm="+dat2+", daty="+dat3+" where asn="+asn);
				if(paymentConn.a==1)
					flag=flag+1;
				paymentConn.st.close();
				paymentConn.con.close();
				
				
				//database query for subscription details fragment
				
				connect c4=new connect();
				c4.a=c4.st.executeUpdate("update subdetails set title='"+title+"', fname='"+fname+"', lname='"+lname+"', add1='"+add1+"', add2='"+add2+"',add3='"+add3+"',dist='"+dist2+"', state='"+state+"',pin="+pin+" where asn="+asn);
				
				if(c4.a==1)
					flag=flag+1;
					
				c4.st.close();
				c4.con.close();
				
				//database query for other details fragment
				
				connect c5=new connect();
				//System.out.println("update otherdet set phone='"+phone+"', history='"+history+"', email='"+email+"', remarks='"+remarks+"' where asn="+asn);
				c5.a=c5.st.executeUpdate("update otherdet set phone='"+phone+"', history='"+history+"', email='"+email+"', remarks='"+remarks+"' where asn="+asn); 
				//System.out.println(c5.a);
				
				if(c5.a==1)
					flag=flag+1;
				c5.st.close();
				c5.con.close();
			
				if(flag==4)
				{
					JOptionPane.showMessageDialog(this, "RECORD MODIFIED SUCCESSFULLY", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					flag=0;
				}
				
				new sams();
				this.dispose();
	
			}
			catch(Exception e)
			{
				//System.out.println(e);
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "ERROR"+e, "ERROR", JOptionPane.ERROR_MESSAGE);
	
			}
		}

		if(ae.getSource()==back)
		{
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
				statt1.setText(""+c14.rs.getString(2));
				statt1.setEnabled(false);
				c14.st.close();
				c14.con.close();
			}
			catch(Exception e1)
			{

			}

		}
		if(ie.getSource()==distt1)
		{
			if(distt1.getSelectedItem()=="Distributor")
			{
				dt1.setEnabled(true);
				dt1.setSelectedIndex(1);
			}
			
			if(distt1.getSelectedItem()=="By Hand"||distt1.getSelectedItem()=="By Post")
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
				
				if(subtt1.getSelectedItem()=="Urdu"||subtt1.getSelectedItem()=="Punjabi")
				{
					amt1.setEnabled(true);
					amt1.setText("");
				}
			}
			
			catch(Exception e)
			{
				
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
		
		if(ie.getSource()==subt22)
		{
            dist21.setEnabled(true);
			if(subt22.getSelectedItem()=="DL")
			{
				statt1.setText("DL");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");

			}


			if(subt22.getSelectedItem()=="HR")
			{
				statt1.setText("HAR");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}

			if(subt22.getSelectedItem()=="MH")
			{
				statt1.setText("MAH");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}

			if(subt22.getSelectedItem()=="MP")
			{
				statt1.setText("MP");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}


			if(subt22.getSelectedItem()=="PB")
			{
				statt1.setText("PJB");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}

			if(subt22.getSelectedItem()=="RJ")
			{	statt1.setText("RAJ");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}

			if(subt22.getSelectedItem()=="UA")
			{
				statt1.setText("UA");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}

			if(subt22.getSelectedItem()=="UP")
			{
				statt1.setText("UP");
				statt1.setEnabled(false);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");
			}



			if(subt22.getSelectedItem()=="LF")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");

			}

			if(subt22.getSelectedItem()=="BH")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(false);
                dist21.setText("");

			}

			if(subt22.getSelectedItem()=="MS")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");

			}

			if(subt22.getSelectedItem()=="LH")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(false);
                dist21.setText("");

			}

			if(subt22.getSelectedItem()=="CM")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Post");
				distt1.setEnabled(false);
                dist21.setText("");

			}

			if(subt22.getSelectedItem()=="EN")
			{
				statt1.setText("");
				statt1.setEnabled(true);
				distt1.setSelectedItem("By Hand");
				distt1.setEnabled(true);
                dist21.setText("");

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
                dist21.setText("");

			}
		}		
	}
}

