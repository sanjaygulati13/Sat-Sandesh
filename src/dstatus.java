import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.text.*;



public class dstatus extends JFrame implements ActionListener
{
	JLabel l1,l2;
	TextFieldWithLimit t1,t2,t3, t4, t5, t6;
	JTable tb1;
	JScrollPane sp;
	Container con;
	JButton b1,b2,b3, b4;
	Object col[]={"D#","NAME","DISTRICT","STATE","ACTIVE","OTHERS","TOTAL"};

    int tot=0, act=0, oth=0;
    int act_num=0, oth_num=0,tot_num=0;
	
	
	public static void main(String args[])
	{
		new dstatus();
	}
	
	public dstatus()
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
		setTitle("Distributor Details");
		//con.setBackground(Color.cyan);
		l1=new JLabel("MEMBER STATUS FOR DISTRIBUTOR FOR CURRENT MONTH");
		l1.setBounds(150,40,550,20);
		l1.setFont(new Font("SERIF", Font.BOLD, 18));
		con.add(l1);
		
		b4=new JButton("Print");
		b4.setMnemonic('P');
		b4.setBounds(200,530,90,25);
		con.add(b4);
		b4.addActionListener(this);	
		
		int i=0;
			try
			{
				
				connect c1=new connect();
				c1.rs=c1.st.executeQuery("select count(dno) from despcode");
				c1.rs.next();
				i=c1.rs.getInt(1);			
				c1.st.close();
				c1.con.close();
				
				//System.out.println(i);
				
				if(i==0)
					JOptionPane.showMessageDialog(null,"No Records Found","No Records",JOptionPane.ERROR_MESSAGE);
					
				else
				{
					int[] z;
					int c=0;
					connect c2=new connect();
					c2.rs=c2.st.executeQuery("select count(dno) from despcode");
					c2.rs.next();
					c=c2.rs.getInt(1);
					z=new int[c];
					
					c2.rs=c2.st.executeQuery("select * from despcode");
					Object data[][]= new Object[i+1][7];
					int j=0;
                    
					while(c2.rs.next())
					{
						z[j]=c2.rs.getInt(1);
						data[j][0]=z[j];
						data[j][1]=c2.rs.getString(2)+" "+c2.rs.getString(3);
						data[j][2]=c2.rs.getString(11);
						data[j][3]=c2.rs.getString(12);
						j++;
					}
					
					j=0;

                    System.out.println("C : "+c);
					
					for(i=0;i<c;i++)
					{
                        act_num=0;
                        oth_num=0;
                        tot_num=0;
						c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, despcode d where b.status='Active' and b.dno="+z[i]+" group by d.dno order by d.dno");
						while(c2.rs.next())
						{
                            
                            act_num=c2.rs.getInt(1);
							data[j][4]=" "+act_num;
                            
							//j++;
						}
					
					
					
	//					j=0;
						c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, despcode d where b.status not in ('Active') and b.dno="+z[i]+" group by d.dno order by d.dno");
						while(c2.rs.next())
						{
                            
                            oth_num=c2.rs.getInt(1);
							data[j][5]=""+oth_num;
                            
                            
						//j++;
						}
						
		//				j=0;
						c2.rs=c2.st.executeQuery("select count(b.asn) from basic b, despcode d where b.dno="+z[i]+" group by d.dno order by d.dno");
						while(c2.rs.next())
						{
                            
							tot_num=c2.rs.getInt(1);
							data[j][6]=""+tot_num;
                            
                            
							//j++;
						}

                        tot+=tot_num;
                        act+=act_num;
                        oth+=oth_num;

						j++;
					}
                    data[j][2]="TOTAL";
                    data[j][4]=""+act;
                    data[j][5]=""+oth;
                    data[j][6]=""+tot;
					
					tb1=new JTable(data,col);
					sp=new JScrollPane(tb1);
					sp.setBounds(20,100,845,410);
					con.add(sp);
				}
			}
			catch(Exception e)
			{
				System.out.println("exception"+e);
				e.printStackTrace();
			}
		
		
		b3=new JButton("Back");
		b3.setMnemonic('b');
		b3.setBounds(480,530,90,25);
		con.add(b3);
		b3.addActionListener(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			b1.setEnabled(false);
			
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
              	MessageFormat headerFormat = new MessageFormat("Page {0}");
              	MessageFormat footerFormat = new MessageFormat("- {0} -");
              	tb1.print(JTable.PrintMode.FIT_WIDTH);//, headerFormat, footerFormat);
            }	
            catch (PrinterException pe) 
            {
              	System.err.println("Error printing: " + pe.getMessage());
            }
          }

	}
}	