import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class accdet extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new accdet();
	}	
		
	JLabel from, to, rcpt;
	JButton ok, back, clr;
	JTextField fromt, tot;
		
		
	public accdet()
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
		setTitle("Receipt Book Details");
		setSize(300,300);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);
		
		rcpt=new JLabel("Receipt Book");
		from=new JLabel("From");
		to=new JLabel("To");
		
		fromt=new JTextField(20);
		tot=new JTextField(20);
		
		ok=new JButton("OK");
		clr=new JButton("Clear");
		back=new JButton("Back");
		
				
		rcpt.setBounds(80,30,100,20);
		add(rcpt);
		
		from.setBounds(30,70,50,20);
		add(from);
		
		to.setBounds(30,110,50,20);
		add(to);
		
		fromt.setBounds(110,70,100,20);
		add(fromt);
		
		tot.setBounds(110,110,100,20);
		add(tot);
		
		ok.setBounds(30,170,70,25);
		ok.addActionListener(this);
		ok.setMnemonic('O');
		add(ok);
		
		clr.setBounds(120,170,70,25);
		clr.addActionListener(this);
		clr.setMnemonic('C');
		add(clr);
		
		back.setBounds(210,170,70,25);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);

		setVisible(true);

	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==ok)
		{
			new chkacct(Integer.parseInt(fromt.getText()),Integer.parseInt(tot.getText()));
			this.dispose();
		}
		
		if(ae.getSource()==clr)
		{
			
			fromt.setText("");
			tot.setText("");
			
		}
		
		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
			
		}
		
	}	
}

