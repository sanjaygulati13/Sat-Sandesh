import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class recdel extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new recdel();
	}	
		
	JLabel subno, month;
	JButton ok, back;
	JTextField subnot, montht1, montht2;
		
		
	public recdel()
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
		setTitle("Mark Return Back");
		setSize(270,200);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		
		
		subno=new JLabel("Sub no");
		month=new JLabel("Month");
		
		subnot=new JTextField(20);
		montht1=new JTextField(10);
		montht2=new JTextField(10);
		
		ok=new JButton("OK");
		back=new JButton("Back");
		
				
		subno.setBounds(30,30,50,20);
		add(subno);
		
		month.setBounds(30,70,50,20);
		add(month);
		
		subnot.setBounds(110,30,100,20);
		add(subnot);
		
		montht1.setBounds(110,70,30,20);
		add(montht1);
		
		montht2.setBounds(150,70,50,20);
		add(montht2);
		
		ok.setBounds(30,130,70,20);
		ok.addActionListener(this);
		ok.setMnemonic('O');
		add(ok);
		
				
		back.setBounds(130,130,70,20);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==ok)
		{
			subnot.setText("");
			montht1.setText("");
			montht2.setText("");
		}
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
		}
		
	}	
}

