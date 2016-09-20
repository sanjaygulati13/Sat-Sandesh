import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class remstatus1 extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new remstatus1();
	}	
		
	JLabel month, year, lang;
	JButton ok, back;
	JTextField montht, yeart;

		
		
	public remstatus1()
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
		setTitle("Reminder Status");
		setSize(300,200);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		
		month=new JLabel("Month");
		year=new JLabel("Year");
		
		
		montht=new JTextField(20);
		yeart=new JTextField(40);
		
		
		ok=new JButton("OK");
		back=new JButton("Back");
		
		month.setBounds(30,30,50,20);
		add(month);
		
		year.setBounds(30,70,50,20);
		add(year);
		
		montht.setBounds(110,30,50,20);
		add(montht);
                montht.setText(""+SamsUtilities.getCurrentMonth());
		
		yeart.setBounds(110,70,50,20);
		add(yeart);
                yeart.setText(""+SamsUtilities.getCurrentYear());
		
		
		ok.setBounds(30,120,70,25);
		ok.addActionListener(this);
		ok.setMnemonic('O');
		add(ok);
		
		back.setBounds(120,120,70,25);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);
		
		setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==ok)
		{
			int month1=Integer.parseInt(montht.getText());
			int year1=Integer.parseInt(yeart.getText());
			
			new remstatus(month1,year1);
			this.dispose();
		}
		
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
			
		}
		
	}	
}

