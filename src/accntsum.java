import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.text.*;

public class accntsum extends JFrame implements ActionListener
{
	JLabel l1,l2;
	TextFieldWithLimit t1,t2,t3, t4, t5, t6;
	JTable tb1;
	JScrollPane sp;
	Container con;
	JButton b1,b2,b3, b4;
	Object col[]={"DATE","COUNTER","AMOUNT"};
	JComboBox subt;
	
	
	public static void main(String args[])
	{
		new accntsum();
	}
	accntsum()
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
		setTitle("Account Book Summary");
		//con.setBackground(Color.cyan);
		l1=new JLabel("Enter Start Date");
		l1.setBounds(40,40,150,20);
		con.add(l1);
	
		t1= new TextFieldWithLimit(2,2);
		t1.setBounds(250, 40,30,20);
		con.add(t1);
		
		t2=new TextFieldWithLimit(2,2);
		t2.setBounds(290, 40,30,20);
		con.add(t2);
		
		t3=new TextFieldWithLimit(4,4);
		t3.setBounds(330, 40,40,20);
		con.add(t3);
		
		l2=new JLabel("Enter End Date");
		l2.setBounds(40,70,150,20);
		con.add(l2);
		
		
		t4= new TextFieldWithLimit(2,2);
		t4.setBounds(250, 70,30,20);
		con.add(t4);
		
		
		
		b1= new JButton("Find");
		b1.setBounds(380,70,80,25);
		b1.setMnemonic('F');
		b1.setToolTipText("Find the required record");
		con.add(b1);
		b1.addActionListener(this);
	
		
		//ImageIcon i=new ImageIcon("close.jpeg");
		b3=new JButton("Back");
		b3.setMnemonic('b');
		b3.setBounds(480,530,90,25);
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
	int d1, d2, m1, m2, y1, y2;
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			b1.setEnabled(false);
			int i=0;
			try
			{
				
				d1=Integer.parseInt(t1.getText());
				m1=Integer.parseInt(t2.getText());
				y1=Integer.parseInt(t3.getText());
				d2=Integer.parseInt(t4.getText());
				m2=m1;
				y2=y1;
				connect c1=new connect();
				c1.rs=c1.st.executeQuery("select p.datd, p.datm, p.daty, o.history, sum(p.amt) from payment p, otherdet o where p.asn=o.asn and p.datd>"+(d1-1)+" and p.datd<"+(d2+1)+" and p.datm="+m1+" and p.daty="+y1+" group by p.datd, o.history ");
				while(c1.rs.next())
				{
					i++;			
				}
				c1.rs.close();
				
				//System.out.println(i);
				
				if(i==0)
					JOptionPane.showMessageDialog(null,"No Records Found","No Records",JOptionPane.ERROR_MESSAGE);
					
				else
				{
					
			
					connect c2=new connect();
			
					c2.rs=c2.st.executeQuery("select p.datd, p.datm, p.daty, o.history, sum(p.amt) from payment p, otherdet o where p.asn=o.asn and p.datd>"+(d1-1)+" and p.datd<"+(d2+1)+" and p.datm="+m1+" and p.daty="+y1+" group by p.datd, o.history order by p.datd");
					Object data[][]= new Object[i+1][3];
					int z=0;
					int j=0;
					int total=0;
					int c=0;
					while(c2.rs.next())
					{
						z=j+1;
						data[j][0]=c2.rs.getInt(1)+"/"+c2.rs.getInt(2)+"/"+c2.rs.getInt(3);
						data[j][1]=c2.rs.getString(4);
						c=c2.rs.getInt(5);
						data[j][2]=c;
						total+=c;
						j++;
					}
					data[j][0]="TOTAL";
					data[j][2]=total;
					tb1=new JTable(data,col);
					sp=new JScrollPane(tb1);
					sp.setBounds(20,100,845,410);
					con.add(sp);
				}
			}
			catch(Exception e)
			{
				//System.out.println("exception"+e);
				JOptionPane.showMessageDialog(null,"ERROR"+e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		/*if(ae.getSource()==b2)
		{
			new modify(Integer.parseInt(t2.getText()));
			this.dispose();
		}*/
		if(ae.getSource()==b3)
		{
			new sams();
			this.dispose();
		}
		
		
		if(ae.getSource()==b4)
		{
			
            try 
            {
              	MessageFormat headerFormat = new MessageFormat("Summary Account Book Details");
              	MessageFormat footerFormat = new MessageFormat(""+d1+"-"+m1+"-"+y1+" TO "+d2+"-"+m1+"-"+y1+"\t ( Page {0} )");
              	tb1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            }	
            catch (PrinterException pe) 
            {
              	System.err.println("Error printing: " + pe.getMessage());
            }
          }

	}
}	