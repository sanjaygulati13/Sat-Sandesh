import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class moddesp extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new moddesp();
	}	
	
	JLabel code;
	JComboBox codet;
	JButton ok, back;
	
	public moddesp()
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
		setTitle("Modify Details");
		setSize(190,140);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		
		code=new JLabel("D#");
		
		codet=new JComboBox();
		
		try
		{
			connect c13=new connect();
			c13.rs=c13.st.executeQuery("select dno from despcode");
			while(c13.rs.next())
			{
				codet.addItem(""+c13.rs.getInt(1));
			}
			
			c13.st.close();
			c13.con.close();
			
		}
		catch(Exception e)
		{
			
		}
		
		ok=new JButton("OK");
		ok.addActionListener(this);
		
		back=new JButton("Back");
		back.addActionListener(this);
		
		code.setBounds(40,30,30,20);
		add(code);
		
		codet.setBounds(80,30,50,20);
		add(codet);
		
		ok.setBounds(20,70,70,20);
		
		ok.setMnemonic('O');
		add(ok);
		
		back.setBounds(100,70,70,20);
		back.setMnemonic('B');
		add(back);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==ok)
		{
			
				new modd(Integer.parseInt((String)codet.getSelectedItem()));
				this.dispose();
			
		}
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
		}
		
		
	}
	
}