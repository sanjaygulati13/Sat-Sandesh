import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.print.*;

public class duplicate extends JFrame implements ActionListener
{
	JLabel l1,l2;
	TextFieldWithLimit t1,t2,t3, t4, t5, t6;
	JTable tb1;
	JScrollPane sp;
	Container con;
	JButton b1,b2,b3, b4;
	Object col[]={"ASN","SUB NO"};
	JComboBox subt;


	public static void main(String args[])
	{
		new duplicate();
	}
	duplicate()
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
		con=getContentPane();
		setSize(900,600);
		setLocation(200,80);
		//setVisible(true);
		con.setLayout(null);
		setTitle("Duplicate Sub No");
        int i=0;
			try
			{

				connect c1=new connect();
				c1.rs=c1.st.executeQuery("select subnos from basic group by subno, subnos having count(subno)>1 ;");
				while(c1.rs.next())
				{
					i++;
				}
				c1.rs.close();


				if(i==0)
					JOptionPane.showMessageDialog(null,"No Records Found","No Records",JOptionPane.ERROR_MESSAGE);
				else
				{

					connect c2=new connect();

					c2.rs=c2.st.executeQuery("select asn, subnos, subno from basic group by subno, subnos having count(subno)>1 ");
					Object data[][]= new Object[i][2];
					int z=0;
					int j=0;
					while(c2.rs.next())
					{
						z=j+1;
						data[j][0]=c2.rs.getInt(1);
                        String no=""+c2.rs.getInt(3);
                        int l=0;
                        //System.out.println(no+"  "+no.length());
                        if(no.length()<5)
                        {
                            while(l<(6-no.length()))
                            {
                                no="0"+no;
                                l++;
                            }
                        }
						data[j][1]=c2.rs.getString(2)+"\t  \t"+no;
						j++;
					}

					tb1=new JTable(data,col);
					sp=new JScrollPane(tb1);
					sp.setBounds(20,100,845,410);
					con.add(sp);
				}
			}
			catch(Exception e)
			{
				System.out.println("exception"+e);
			}
		
		l1=new JLabel("DUPLICATE SUB NUMBERS");
        l1.setFont(new Font("SERIF",Font.BOLD, 36));
		l1.setBounds(160,40,650,40);
		con.add(l1);

        //ImageIcon i=new ImageIcon("close.jpeg");
		b3=new JButton("Close");
		b3.setMnemonic('C');
		b3.setBounds(480,530,90,25);
		//b3.setIcon(i);


		con.add(b3);
		b3.addActionListener(this);


		b4=new JButton("Print");
		b4.setMnemonic('P');
		b4.setBounds(200,530,90,25);


		con.add(b4);
		b4.addActionListener(this);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b3)
		{
			new sams();
			this.dispose();
		}


			if(ae.getSource()==b4)
			{

            try {
              MessageFormat headerFormat = new MessageFormat("Duplicate Sub No list \t (Page {0})");
              MessageFormat footerFormat = new MessageFormat("- {0} -");
              tb1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
              System.err.println("Error printing: " + pe.getMessage());
            }
          }

	}
}