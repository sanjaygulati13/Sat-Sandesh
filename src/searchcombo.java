import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class searchcombo extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,l5, l6;
    TextFieldWithLimit firstNameText,lastNameText,districtText, stateText, t5, t6, t7, t8;
    JTable tb1;
    JScrollPane sp;
    Container con;
    JButton b1,b2,b3,reset;
    Object col[]={"ASN","NAME","L NAME","DISTRICT","STATE"};
    JComboBox subt;
    
    
    public static void main(String args[])
    {
        new searchcombo();
    }
    searchcombo()
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
        setSize(600,730);
        setLocation(300,20);
        //setVisible(true);
        con.setLayout(null);
        setTitle("Search by Subscriber Details");
        //con.setBackground(Color.cyan);
        
        l1=new JLabel("Enter First Name");
        l1.setBounds(40,40,150,20);
        con.add(l1);
        
        firstNameText= new TextFieldWithLimit(16,16);
        firstNameText.setText("");
        firstNameText.setBounds(250, 40,150,20);
        con.add(firstNameText);
        
        
        l2=new JLabel("Enter Last Name");
        l2.setBounds(40,70,150,20);
        con.add(l2);
        
        lastNameText= new TextFieldWithLimit(15,15);
        lastNameText.setText("");
        lastNameText.setBounds(250,70,150,20);
        con.add(lastNameText);
        
        
        l3=new JLabel("Enter District");
        l3.setBounds(40,100,150,20);
        con.add(l3);
        
        districtText=new TextFieldWithLimit(32,32);
        districtText.setText("");
        districtText.setBounds(250,100,230,20);
        con.add(districtText);
        
        l4=new JLabel("Enter State");
        l4.setBounds(40,130,150,20);
        con.add(l4);
        
        stateText= new TextFieldWithLimit(15,15);
        stateText.setText("");
        stateText.setBounds(250,130,150,20);
        con.add(stateText);
        
        //l6=new JLabel("Enter D#");
        //l6.setBounds(40,160,150,20);
        //con.add(l6);
        
        //t8= new TextFieldWithLimit(2,2);
        //t8.setBounds(250,160,150,20);
        //con.add(t8);
        
        b1= new JButton("Find");
        b1.setBounds(320,190,80,20);
        b1.setMnemonic('F');
        b1.setToolTipText("Find the required record");
        con.add(b1);
        b1.addActionListener(this);
        
        l2=new JLabel("Enter ASN");
        l2.setBounds(50,640,200,20);
        con.add(l2);
        
        t5= new TextFieldWithLimit(20,20);
        t5.setBounds(280,640,100,20);
        con.add(t5);
        
        b2=new JButton("View");
        b2.setMnemonic('V');
        b2.setBounds(410,640,100,20);
        b2.setToolTipText("View the Record");
        con.add(b2);
        b2.addActionListener(this);
        
        
        reset=new JButton("Reset");
        reset.setMnemonic('R');
        reset.setBounds(350,670,90,20);
        //b3.setIcon(i);
        con.add(reset);
        reset.addActionListener(this);
        
        
        //ImageIcon i=new ImageIcon("close.jpeg");
        b3=new JButton("Close");
        b3.setMnemonic('C');
        b3.setBounds(480,670,90,20);
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
            searchBySubscriberDetailsAndFIllTable(firstNameText.getText(), lastNameText.getText(), districtText.getText(), stateText.getText(), tb1);
            
        }
        if(ae.getSource()==b2)
        {
            new SatSandeshModifySubscriptionData(Integer.parseInt(t5.getText()));
            this.dispose();
        }
        
        if(ae.getSource()==reset)
        {
            new searchcombo();
            this.dispose();
        }
        
        if(ae.getSource()==b3)
        {
            new sams();
            this.dispose();
        }
    }
    
    public void searchBySubscriberDetailsAndFIllTable(String firstName, String lastName,String district,String state, JTable tb1)
    {
        int i=0;
        
        
        firstName=firstNameText.getText().trim();
        lastName=lastNameText.getText().trim();
        district=districtText.getText().trim();
        state=stateText.getText().trim();
        
        try
        {    
            String percentile = "%%";
            connect c1=new connect();
            String searchQuery = "select s.asn , s.fname , s.lname , s.dist , s.state from subdetails s, basic b where b.asn=s.asn ";
            
            if(firstName.length()>0)
                searchQuery += "and s.fname like '"+firstName+"%' ";
            
            if(lastName.length()>0)
                searchQuery += "and s.lname like '"+lastName+"%' ";
            
            
            if(district.length()>0)
                searchQuery += "and s.dist like '"+district+"%' ";
            
            
            if(state.length()>0)
                searchQuery += "and s.state like '"+state+"%' ";
            
            searchQuery += "order by s.asn";
            //System.out.println(searchQuery);
            
            i=0;
            c1.rs=c1.st.executeQuery(searchQuery);
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
                
                c2.rs=c2.st.executeQuery(searchQuery);
                //System.out.println(searchQuery);
                Object data[][]= new Object[i][5];
                for(int k1=0;k1<i;k1++)
                {
                    for(int j1=0;j1<5;j1++)
                        data[k1][j1]="";
                }
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
                sp.setBounds(20,220,545,410);   //410
                con.add(sp);
                c2.st.close();
                c2.con.close();
            }
        }
        catch(Exception e)
        {
            System.out.println("exception"+e);
        }
    }
}	