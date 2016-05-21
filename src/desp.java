import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class desp extends JFrame implements ActionListener
{
	
	public static void main(String args[])
	{
		new desp();
	}	
		
	JLabel nam, phno, despcd, email, add1, rem, hist, dist, stat, pin, distributionTypeLabel;
	JTextField remt, histt, distt;
	TextFieldWithLimit namt, lnamt, phnot, despcdt, addt11, addt12, addt13, statt, pint, emailt;
        JComboBox distributionTypeDropDown;
	JButton sav, clr, back;
		
	public desp()
	{
		
    	try 
        {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } 
        catch (Exception cnf) 
        {
        	//System.out.println(cnf);
        }
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		setTitle("Despatch Code Details");
		setSize(800,500);
		//this.pack();
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		//setUndecorated(true);
	
		nam=new JLabel("Name");
		phno=new JLabel("Phone No");
		despcd=new JLabel("Desp Code");
                distributionTypeLabel = new JLabel("<html>Dist Type</html>");
		email=new JLabel("email id");
		add1=new JLabel("Address");
		rem=new JLabel("Remarks");
		hist=new JLabel("History");
		dist=new JLabel("District");
		stat=new JLabel("State");
		pin=new JLabel("Pin Code");
		
		
		
		namt=new TextFieldWithLimit(16,16);
		lnamt=new TextFieldWithLimit(15,15);
		phnot=new TextFieldWithLimit(13,12);
		despcdt=new TextFieldWithLimit(4,3);
		addt11=new TextFieldWithLimit(33,32);
		addt12=new TextFieldWithLimit(33,32);
		addt13=new TextFieldWithLimit(33,32);
		statt=new TextFieldWithLimit(4,3);
		pint=new TextFieldWithLimit(7,6);
		
		histt=new JTextField(20);
		emailt=new TextFieldWithLimit(32,32);
		distt=new JTextField(20);
		remt=new JTextField(20);
		
		sav=new JButton("Save");
		clr=new JButton("Clear");
		back=new JButton("Back");
                
                distributionTypeDropDown = new JComboBox();
                distributionTypeDropDown.addItem("By Post");
                distributionTypeDropDown.addItem("By Hand");
		
		nam.setBounds(20,30,50,20);
		add(nam);
		
		phno.setBounds(400,30,80,20);
		add(phno);
		
		despcd.setBounds(20,70,130,20);
		add(despcd);
                
                distributionTypeLabel.setBounds(180,70,80,20);
                add(distributionTypeLabel);
                
                distributionTypeDropDown.setBounds(260,70,120,20);
                add(distributionTypeDropDown);
		
		email.setBounds(400,70,50,20);
		add(email);
		
		add1.setBounds(20,110,80,20);
		add(add1);
		
		rem.setBounds(20,230,80,20);
		add(rem);
		
		hist.setBounds(400,230,80,20);
		add(hist);
		
		dist.setBounds(20,270,90,20);
		add(dist);
		
		stat.setBounds(400,270,50,20);
		add(stat);
		
		pin.setBounds(20,310,90,20);
		add(pin);
		
		//textfields
		
		namt.setBounds(100,30,130,20);
		add(namt);
		
		lnamt.setBounds(250,30,130,20);
		add(lnamt);
		
		phnot.setBounds(480,30,120,20);
		add(phnot);
		
		despcdt.setBounds(100,70,50,20);
		add(despcdt);
		
		emailt.setBounds(480,70,240,20);
		add(emailt);
		
		addt11.setBounds(100,110,240,20);
		add(addt11);
		
		addt12.setBounds(100,140,240,20);
		add(addt12);
		
		addt13.setBounds(100,170,240,20);
		add(addt13);
		
		remt.setBounds(100,230,240,20);
		add(remt);
		
		histt.setBounds(480,230,240,20);
		add(histt);
		
		distt.setBounds(100,270,200,20);
		add(distt);
		
		statt.setBounds(480,270,40,20);
		add(statt);
		
		pint.setBounds(100,310,60,20);
		pint.setText("0");
		add(pint);
		
		sav.setBounds(170,360,100,25);
		sav.addActionListener(this);
		sav.setMnemonic('S');
		add(sav);
		
		
		clr.setBounds(300,360,100,25);
		clr.addActionListener(this);
		clr.setMnemonic('C');
		add(clr);
		
		
		back.setBounds(430,360,100,25);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);
		setVisible(true);
		

	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==sav)
		{
			
			String name1,lname1, phone, email, add1, add2, add3, remarks, history, district, state;
                        String distributionTypeText;
			int dno, pinno;
			//System.out.println("hello");
			
			dno=Integer.parseInt(despcdt.getText());
			name1=namt.getText();
			lname1=lnamt.getText();
			phone=phnot.getText();
			email=emailt.getText();
			add1=addt11.getText();
			add2=addt12.getText();
			add3=addt13.getText();
			remarks=remt.getText();
			history=histt.getText();
			district=distt.getText();
			state=statt.getText();
			pinno=Integer.parseInt(pint.getText());
                        distributionTypeText = (String)(distributionTypeDropDown.getSelectedItem());
			//System.out.println(""+dno+name1+phone+email+add1+add2+add3+remarks+history+district+state+pinno);
			
			try
			{
				int k=0;
				connect c3=new connect();
				c3.rs=c3.st.executeQuery("select dno from despcode where dno="+despcdt.getText());
				while(c3.rs.next())
				{
					k++;
				}
				c3.st.close();
				c3.con.close();
				
				if(k==0)
				{
					connect c1=new connect();
					c1.a=c1.st.executeUpdate("insert into despcode values("+dno+",'"+name1+"','"+lname1+"','"+phone+"','"+email+"','"+add1+"','"+add2+"','"+add3+"','"+remarks+"','"+history+"','"+district+"','"+state+"',"+pinno+",'"+distributionTypeText+"')");
					//System.out.println(""+c1.a);
					if(c1.a==1)
					{
						new desp();
						this.dispose();
					}
					c1.st.close();	
					c1.con.close();
					connect c2=new connect();
					c2.a=c2.st.executeUpdate("create table d"+dno+" (asn int)");
					c2.st.close();
					c2.con.close();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "D# entry Already Exist", "ERROR", JOptionPane.ERROR_MESSAGE);
					namt.setText("");
					lnamt.setText("");
					phnot.setText("");
					despcdt.setText("");
					emailt.setText("");
					addt11.setText("");
					addt12.setText("");
					addt13.setText("");
					histt.setText("");
					remt.setText("");
					distt.setText("");
					statt.setText("");
					pint.setText("");
                                        distributionTypeDropDown.setSelectedIndex(0);
			
				}
			}
			catch(Exception e)
			{
				new except(e,this.getClass().toString());
			}
			
			
			
		}
		
		if(ae.getSource()==clr)
		{
			namt.setText("");
			lnamt.setText("");
			phnot.setText("");
			despcdt.setText("");
			emailt.setText("");
			addt11.setText("");
			addt12.setText("");
			addt13.setText("");
			histt.setText("");
			remt.setText("");
			distt.setText("");
			statt.setText("");
			pint.setText("");
                        distributionTypeDropDown.setSelectedIndex(0);
			
			
		}
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
			
		}
	}
}
