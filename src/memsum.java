import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class memsum extends JFrame implements ActionListener 
{
	
	public static void main(String args[])
	{
		new memsum();
	}

	JLabel l1,l2,l3, l4;
	JTextField t1,t2;
	JButton b1, b2;
    JComboBox langt;
	
	public memsum()
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
        
		setSize(300,250);
		setLocation(10,10);
		setLayout(null);
		setTitle("Member Status Summary");
		l1=new JLabel("Enter Month");
		add(l1);
		l1.setBounds(100,30,100,20);
		l2=new JLabel("Month");
		add(l2);
		l2.setBounds(50,60,50,20);
		t1=new JTextField(10);
		add(t1);
		t1.setBounds(110,60,50,20);
		l3=new JLabel("Year");
		add(l3);
		l3.setBounds(50,90,50,20);

        l4=new JLabel("Language");
		add(l4);
        l4.setBounds(50,120,50,20);

		t2=new JTextField(10);
		add(t2);
		t2.setBounds(110,90,50,20);

        langt=new JComboBox();
        langt.addItem("Hindi");
		langt.addItem("English");
		langt.addItem("Punjabi");
		langt.addItem("Urdu");

        langt.setBounds(110,120,100,20);
        add(langt);

		b1=new JButton("Submit");
		add(b1);
		b1.setBounds(40,150,80,25);
		b1.addActionListener(this);
		
		b2=new JButton("Back");
		add(b2);
		b2.setBounds(130,150,80,25);
		b2.addActionListener(this);
				
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource()==b1)
		{
			new memsummary((Integer.parseInt(t1.getText())),(Integer.parseInt(t2.getText())), (String)langt.getSelectedItem());
			this.dispose();
		}
		if(ae.getSource()==b2)
		{
			new sams();
			this.dispose();
			
		}
				
	}

}
