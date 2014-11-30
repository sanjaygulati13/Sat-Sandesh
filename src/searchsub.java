import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;


public class searchsub extends JFrame implements ActionListener
{
	JLabel l1,l2;
	JTextField t1,t2,t3;
	JTable tb1;
	JScrollPane sp;
	Container con;
	JButton b1,b2,b3, reset;
	Object col[]={"ASN","NAME","L NAME","DISTRICT","STATE"};
	JComboBox subt;
	
	
	public static void main(String args[])
	{
		new searchsub();
	}
	searchsub()
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
		setSize(600,600);
		setLocation(300,80);
		//setVisible(true);
		con.setLayout(null);
		setTitle("Search by Sub No");
		//con.setBackground(Color.cyan);
		l1=new JLabel("Enter Sub No");
		l1.setBounds(40,40,150,20);
		con.add(l1);
		
		subt=new JComboBox();
		
		subt.addItem("BH");
		subt.addItem("BD");
		subt.addItem("CM");
		subt.addItem("DL");
		subt.addItem("EN");
		subt.addItem("HR");
		subt.addItem("LF");
		subt.addItem("LH");
		subt.addItem("MH");
		subt.addItem("MP");
		subt.addItem("MS");
                subt.addItem("NA");
		subt.addItem("PB");
		subt.addItem("PJ");
		subt.addItem("RJ");
		subt.addItem("UA");
		subt.addItem("UP");
		subt.addItem("UR");
                
		
		subt.setBounds(190,40,50,20);
		con.add(subt);	
	
		t1= new JTextField("");
		t1.setBounds(250, 40,60,20);
		con.add(t1);
		
		
		b1= new JButton("Find");
		b1.setBounds(320,40,80,20);
		b1.setMnemonic('F');
		b1.setToolTipText("Find the required invoice");
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
				c1.rs=c1.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and b.subno="+Integer.parseInt(t1.getText())+" and b.subnos='"+(String)subt.getSelectedItem()+"'");	
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
			
					c2.rs=c2.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and b.subno="+Integer.parseInt(t1.getText())+" and b.subnos='"+(String)subt.getSelectedItem()+"'");		//select asn from basic where subno="+Integer.parseInt(t1.getText())+" or subnos='"+subt.getSelectedItem()+"'order by asn");
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
					sp=new JScrollPane(tb1);
					sp.setBounds(20,70,545,410);
					con.add(sp);
					
					
					for (int x=0; x<5; x++) 
					{
  						TableColumn column = tb1.getColumnModel().getColumn(i);
  						//System.out.println(tb1.getColumnModel().getColumn(i));
  						
  						if (x==0) column.setPreferredWidth(5);
  						if (x==1) column.setPreferredWidth(5);
  					  	if (x==2) column.setPreferredWidth(5);
    					if (x==3) column.setPreferredWidth(5);
    					if (x==4) column.setPreferredWidth(50);
    					//if (i==5) column.setPreferredWidth(10);
    					//if (i==6) column.setPreferredWidth(10);
    					//if (i==7) column.setPreferredWidth(10);
    					//if (i==8) column.setPreferredWidth(10);
    					//if (i==9) column.setPreferredWidth(10);
					}

					
					
					
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
			new searchsub();
			this.dispose();
		}

		if(ae.getSource()==b3)
		{
			new sams();
			this.dispose();
		}

	}
}	