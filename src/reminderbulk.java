import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class reminderbulk extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new reminderbulk();
	}	
		
	JLabel monthf, montht, yeart, lang, dcode;
	JButton ok, back, clr;
	TextFieldWithLimit  monthft, monthtt, yeartt;
	JComboBox dcodet , langt;
		
		
	public reminderbulk()
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
		setTitle("Reminder--Dist Code");
		setSize(400,400);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		
		dcode=new JLabel("D#");
		
		
		monthf=new JLabel("Month From");
				
		montht=new JLabel("Month To");
		yeart=new JLabel("Year");
		
		lang=new JLabel("Language");
		
		dcodet=new JComboBox();
		langt=new JComboBox();
		
		monthft=new TextFieldWithLimit(2,2);
		monthtt=new TextFieldWithLimit(2,2);
		yeartt=new TextFieldWithLimit(4,4);
		
		
		ok=new JButton("OK");
		clr=new JButton("Clear");
		back=new JButton("Back");
		
		try
		{
			connect c13=new connect();
			c13.rs=c13.st.executeQuery("select dno from despcode");
			while(c13.rs.next())
			{
				dcodet.addItem(""+c13.rs.getInt(1));
			}
			
			c13.st.close();
			c13.con.close();
			
		}
		catch(Exception e)
		{
			
		}
		langt.addItem("Hindi");
		langt.addItem("English");
		langt.addItem("Punjabi");
		langt.addItem("Urdu");
		
		dcode.setBounds(30,70,30,20);
		add(dcode);
		
		monthf.setBounds(30,110,70,20);
		add(monthf);
		
		montht.setBounds(30,150,70,20);
		add(montht);
		
		yeart.setBounds(30,190,70,20);
		add(yeart);
		
		lang.setBounds(30,230,80,20);
		add(lang);
		
		dcodet.setBounds(130,70,50,20);
		add(dcodet);
		
		
		monthft.setBounds(130,110,50,20);
		add(monthft);
		
		monthtt.setBounds(130,150,50,20);
		add(monthtt);
		
		yeartt.setBounds(130,190,50,20);
		add(yeartt);
	
		langt.setBounds(130,230,100,20);
		add(langt);
		
		ok.setBounds(30,270,70,25);
		ok.addActionListener(this);
		ok.setMnemonic('O');
		add(ok);
		
		clr.setBounds(120,270,70,25);
		clr.addActionListener(this);
		clr.setMnemonic('C');
		add(clr);
		
		back.setBounds(210,270,70,25);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);
		
		setVisible(true);
		

	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==ok)
		{
				
				new reminderd(Integer.parseInt((String)dcodet.getSelectedItem()),Integer.parseInt(monthft.getText()),Integer.parseInt(yeartt.getText()),Integer.parseInt(monthtt.getText()),Integer.parseInt(yeartt.getText()));
				this.dispose();

		}
		
		if(ae.getSource()==clr)
		{
			dcodet.setSelectedIndex(1);
			monthft.setText("");
			monthtt.setText("");
			yeartt.setText("");
			
		}
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();	
		}
		
		
	}	
}

