import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class printstate extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new printstate();
	}	
		
	JLabel state, state1;
	JButton ok, back, clr;
	TextFieldWithLimit statet;
		
		
	public printstate()
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
		setTitle("State Record");
		setSize(300,200);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		
		state1=new JLabel("State Details");
		state=new JLabel("Enter State");
		
		statet=new TextFieldWithLimit(3,3);
		
		ok=new JButton("OK");
		clr=new JButton("Clear");
		back=new JButton("Back");
		
				
		state1.setBounds(80,30,100,20);
		add(state1);
		
		state.setBounds(30,70,80,20);
		add(state);
		
		statet.setBounds(110,70,100,20);
		add(statet);
		
		ok.setBounds(30,110,70,20);
		ok.addActionListener(this);
		ok.setMnemonic('O');
		add(ok);
		
		clr.setBounds(120,110,70,20);
		clr.addActionListener(this);
		clr.setMnemonic('C');
		add(clr);
		
		back.setBounds(210,110,70,20);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);
		setVisible(true);
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==ok)
		{
			new allstate(statet.getText());
			this.dispose();
			
		}
		
		
		if(ae.getSource()==clr)
		{
			statet.setText("");
		}
		
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
		}
		
	}	
}

