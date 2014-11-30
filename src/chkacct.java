import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

public class chkacct extends JFrame implements ActionListener
{
	
	public static void main(String args[])
	{
		new chkacct(1001,1100);
	}
	
	int s,e;
	int k=0;
	int i=0;
	int[] rcpt;
	int j=0;
	JLabel[] l;
	JButton b;
	
	public chkacct(int x1, int x2)
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
        
		s=x1;
		e=x2;
		setLocation(10,10);
		setTitle("Unused Rcpt NO");
		setLayout(null);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel l1=new JLabel("UNUSED RECEIPT NOs");
		add(l1);
		l1.setBounds(290,50,150,30);
		int flag=0;
		try
		{
			connect c1=new connect();
			for(i=s;i<=e;i++)
			{
				c1.rs=c1.st.executeQuery("select asn from basic where rcpt="+i);
                flag=0;
				while(c1.rs.next() && flag==0)
				{
                //System.out.println("free : "+c1.rs.getInt(1));
					k++;
                    flag=1;
				}
			}
			c1.st.close();
			c1.con.close();
			//System.out.println("k : "+k);
			int free=(e-s+1)-k;
			//System.out.println("free : "+free);
			rcpt=new int[free+1];
			
			j=0;
			connect c2=new connect();
			for(i=s;i<=e;i++)
			{
				k=0;
				c2.rs=c2.st.executeQuery("select asn from basic where rcpt="+i);
                flag=0;
				while(c2.rs.next())
				{
					k++;
                    flag=1;
				}
				if(k==0)
				{
					rcpt[j]=i;
					//System.out.println(rcpt[j]);
					j++;
				}
				
			}
			c2.st.close();
			c2.con.close();
			
			l=new JLabel[free];
			
			int i1=free/15;
			if(free%15>0)
				i1++;
				
			
			for(i=0;i<free;i++)
			{
				l[i]=new JLabel(""+rcpt[i]);
				l[i].setBounds(30+(i%15)*50,100+(i/15)*30,40,20);
				add(l[i]);
			}
			
			b=new JButton("back");
			b.setBounds(330,150+(i/15)*30,80,20);
			add(b);
			b.addActionListener(this);
			b.setMnemonic('B');
			
			setVisible(true);
			setSize(780,230+(i/15)*30);
		}
		catch(Exception e)
		{
			System.out.println(""+e);
            e.printStackTrace();
            new except(e, this.getClass().toString());
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b)
		{
			new accdet();
			this.dispose();
		}
			
	}

		
		

	
}