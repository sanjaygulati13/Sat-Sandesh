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


public class printsubno extends JFrame implements ActionListener
{
	public static void main(String args[])
	{
		new printsubno();
	}

	JLabel subno, head;
	JButton ok, back;
	JComboBox subnot;


	public printsubno()
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
		setTitle("Sub No Details");
		setSize(210,200);
		setResizable(false);
		setLocation(10,10);
		setLayout(null);

		head=new JLabel("ENTER DETAILS");
		subno=new JLabel("Sub No");


		subnot=new JComboBox();
        subnot.addItem("BH");
		subnot.addItem("BD");
		subnot.addItem("CM");
		subnot.addItem("DL");
		subnot.addItem("EN");
		subnot.addItem("HR");
		subnot.addItem("LF");
		subnot.addItem("LH");
		subnot.addItem("MH");
		subnot.addItem("MP");
		subnot.addItem("MS");
                subnot.addItem("NA");
		subnot.addItem("PB");
		subnot.addItem("PJ");
		subnot.addItem("RJ");
		subnot.addItem("UA");
		subnot.addItem("UP");
		subnot.addItem("UR");


		ok=new JButton("OK");

		back=new JButton("Back");


		head.setBounds(50,30,100,20);
		add(head);

		subno.setBounds(30,60,50,20);
		add(subno);


		subnot.setBounds(90,60,50,20);
		add(subnot);


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
			new allsubno(subnot.getSelectedItem().toString());
			this.dispose();

		}

		if(ae.getSource()==back)
		{
			new sams();
			this.dispose();
		}

	}
}

