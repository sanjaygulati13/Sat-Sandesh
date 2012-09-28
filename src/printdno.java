/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanjay
 */
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;


public class printdno extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new printdno();
	}

	JLabel dno, head;
	JButton ok, back;
	JComboBox dnot;


	public printdno()
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
		setTitle("D# Details");
		setSize(210,200);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);

		head=new JLabel("ENTER DETAILS");
		dno=new JLabel("Dno");


		dnot=new JComboBox();
        dnot.addItem("0");
        try
		{
			connect c13=new connect();
			c13.rs=c13.st.executeQuery("select dno from despcode");
			while(c13.rs.next())
			{
				dnot.addItem(""+c13.rs.getInt(1));
			}

			c13.st.close();
			c13.con.close();

		}
		catch(Exception e)
		{

		}

		ok=new JButton("OK");
		
		back=new JButton("Back");


		head.setBounds(50,30,100,20);
		add(head);

		dno.setBounds(30,60,50,20);
		add(dno);

		
		dnot.setBounds(90,60,50,20);
		add(dnot);


		ok.setBounds(30,90,70,25);
		ok.addActionListener(this);
		ok.setMnemonic('O');
		add(ok);


		back.setBounds(110,90,70,25);
		back.addActionListener(this);
		back.setMnemonic('B');
		add(back);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{

		if(ae.getSource()==ok)
		{
			new alldno(Integer.parseInt(dnot.getSelectedItem().toString()));
			this.dispose();

		}

		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
		}

	}
}

