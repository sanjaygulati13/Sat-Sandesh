import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;


public class searchadd extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JTextField t1,t2,t3;
	JTable tb1;
	JScrollPane sp;
	Container con;
	JButton b1,b2,b3,reset;
	Object col[]={"ASN","NAME","L NAME","DISTRICT","STATE"};
	JComboBox subt;
	
	
	public static void main(String args[])
	{
		new searchadd();
	}
	searchadd()
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
		setSize(600,650);
		setLocation(300,80);
		//setVisible(true);
		con.setLayout(null);
		setTitle("Search by Address");
		//con.setBackground(Color.cyan);
		l1=new JLabel("Enter Address");
		l1.setBounds(40,40,150,20);
		con.add(l1);
	
		t1= new JTextField("");
		t1.setBounds(250, 40,150,20);
		con.add(t1);
		
		
		b1= new JButton("Find");
		b1.setBounds(410,40,80,20);
		b1.setMnemonic('F');
		b1.setToolTipText("Find the required record");
		con.add(b1);
		b1.addActionListener(this);
	
		l2=new JLabel("Enter ASN");
		l2.setBounds(50,490,200,20);
		con.add(l2);
		
		t2= new JTextField(null);
		t2.setBounds(280,490,100,20);
		con.add(t2);
		
		b2=new JButton("View");
		b2.setMnemonic('V');
		b2.setBounds(410,490,100,20);
		b2.setToolTipText("View the Record");
		con.add(b2);
		b2.addActionListener(this);


                reset=new JButton("Reset");
		reset.setMnemonic('R');
		reset.setBounds(350,530,90,20);
		//b3.setIcon(i);
		con.add(reset);
		reset.addActionListener(this);

		
		
		
		//ImageIcon i=new ImageIcon("close.jpeg");
		b3=new JButton("Close");
		b3.setMnemonic('C');
		b3.setBounds(480,530,90,20);
		//b3.setIcon(i);
		b3.setBackground(Color.white);
		b3.setToolTipText("close n return to previous page");
		con.add(b3);
		b3.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			int i=0;
			try
			{
				connect c1=new connect();
				c1.rs=c1.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and (s.add1 like '"+t1.getText()+"%' or s.add2 like '%"+t1.getText()+"%' or s.add3 like '%"+t1.getText()+"%' )");	
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
			
					c2.rs=c2.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and (s.add1 like '"+t1.getText()+"%' or s.add2 like '%"+t1.getText()+"%' or s.add3 like '%"+t1.getText()+"%')");	
					Object data[][]= new Object[i][5];
					int z=0;
					int j=0;
					while(c2.rs.next())
					{
						z=j+1;
						data[j][0]=c2.rs.getInt(1);
						data[j][1]=c2.rs.getString(2);
						data[j][2]=c2.rs.getString(3);
						data[j][3]=c2.rs.getString(4);
						data[j][4]=c2.rs.getString(5);
						j++;
					}
				
					tb1=new JTable(data,col);
					//--------------------------------table column width--------------------------------------------//
					/*
					tb1.setRowSelectionAllowed(false);
					tb1.setColumnSelectionAllowed(false);
					tb1.setCellSelectionEnabled(false);*/
					tb1.setEnabled(false);

					
					tb1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					for(int col1=1; col1<5;col1++)
					{
						
						TableColumn col = tb1.getColumnModel().getColumn(col1);
						if(col1==0)
							col.setPreferredWidth(30);
    					//int width = 100;
    					else if(col1==1)
    						col.setPreferredWidth(130);
    					else if(col1==2)
    						col.setPreferredWidth(130);
    					else if(col1==3)
    						col.setPreferredWidth(130);
    					else if(col1==4)
    						col.setPreferredWidth(50);
					}
					
					//-------------------------------------copy till here --------------------------------------------------//
					sp=new JScrollPane(tb1);
					sp.setBounds(20,70,545,410);
					con.add(sp);
				}
			}
			catch(Exception e)
			{
				System.out.println("exception"+e);
			}
		}
		if(ae.getSource()==b2)
		{
			new modify(Integer.parseInt(t2.getText()));
			this.dispose();
		}

                if(ae.getSource()==reset)
		{
			new searchadd();
			this.dispose();
		}

		if(ae.getSource()==b3)
		{
			new sams();
			this.dispose();
		}
	}
}	