import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.print.*;

public class SatSandeshAccountBookDetails extends JFrame implements ActionListener
{
    JLabel l1,l2;
    TextFieldWithLimit t1,t2,t3, t4, t5, t6;
    JTable tb1;
    JScrollPane sp;
    Container con;
    JButton findButton,backButton, printButton;
    Object col[]={"SNO","SUB NO","D#","SERIES","RCPT","AMOUNT","DATE","END PERIOD","NAME","STATE"};
    JComboBox subt;
    
    
    public static void main(String args[])
    {
        new SatSandeshAccountBookDetails();
    }
    SatSandeshAccountBookDetails()
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
        setSize(950,650);
        setLocation(200,80);
        //setVisible(true);
        con.setLayout(null);
        setTitle("Account Book Details");
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
        
        /*
        t5=new TextFieldWithLimit(2,2);
        t5.setBounds(290, 70,30,20);
        con.add(t5);
        
        t6=new TextFieldWithLimit(4,4);
        t6.setBounds(330, 70,40,20);
        con.add(t6);
        
        */
        
        findButton= new JButton("Find");
        findButton.setBounds(380,70,80,25);
        findButton.setMnemonic('F');
        findButton.setToolTipText("Find the required record");
        con.add(findButton);
        findButton.addActionListener(this);
        
        /*l2=new JLabel("Enter Sub No.");
        l2.setBounds(50,490,200,20);
        con.add(l2);*/
        
        /*t2= new TextField(null);
        t2.setBounds(280,490,100,20);
        con.add(t2);
        */
        
        //ImageIcon i=new ImageIcon("close.jpeg");
        backButton=new JButton("Close");
        backButton.setMnemonic('C');
        backButton.setBounds(480,530,90,25);
        //b3.setIcon(i);
        
        
        con.add(backButton);
        backButton.addActionListener(this);
        
        
        printButton=new JButton("Print");
        printButton.setMnemonic('P');
        printButton.setBounds(200,530,90,25);
        
        
        con.add(printButton);
        printButton.addActionListener(this);
        
        SamsUtilities.center(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    int d1, d2, m1, m2, y1, y2;
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==findButton)
        {
            findButton.setEnabled(false);
            int i=0;
            try
            {
                
                d1=Integer.parseInt(t1.getText());
                m1=Integer.parseInt(t2.getText());
                y1=Integer.parseInt(t3.getText());
                d2=Integer.parseInt(t4.getText());
                
                m2=m1;
                y2=y1;
                /*String countQuery = "select b.asn,b.subnos, b.subno, b.rcpt,p.amt, p.datd, p.datm, p.daty, s.fname, s.lname, s.state from basic b, subdetails s , payment p where b.asn=s.asn and p.asn=b.asn and (p.datd>"+(d1-1)+" and p.datd<"+(d2+1)+") and (p.datm>"+(m1-1)+" and p.datm<"+(m2+1)+") and (p.daty>"+(y1-1)+" and p.daty<"+(y2+1)+" ) order by p.datd, b.rcpt";
                
                System.out.println(countQuery);
                connect c1=new connect();
                c1.rs=c1.st.executeQuery(countQuery);
                while(c1.rs.next())
                {
                    i++;
                }
                c1.closeAll();*/
                
                String startDate = y1+"-"+m1+"-"+d1;
                String endDate = y1+"-"+m1+"-"+d2;
                String countQuery = "select count(asn) from receipt_book_details where date_issued between '"+startDate+"' and '"+endDate+"'";
                //System.out.println(countQuery);
                connect c1=new connect();
                c1.rs=c1.st.executeQuery(countQuery);
                if(c1.rs.next()) i = c1.rs.getInt(1);
                
                c1.closeAll();
                System.out.println("Num Records: " + i);
                
                if(i==0)
                    JOptionPane.showMessageDialog(null,"No Records Found","No Records",JOptionPane.ERROR_MESSAGE);
                
                else
                {
                    Object data[][]= new Object[i+1][col.length];
                    connect c2=new connect();
                    connect subscriberDetailsFetchConnection = new connect();
                    String dataQuery ="select asn,series_name,receipt_number,amount,date_issued,ending_period from receipt_book_details where date_issued between '"+startDate+"' and '"+endDate+"'";
                    //System.out.println(dataQuery);
                    //c2.rs=c2.st.executeQuery("select b.subnos, b.subno, b.dno , b.rcpt, p.amt, p.datd, p.datm, p.daty, p.endm,p.endy, s.fname, s.lname, s.state from basic b, subdetails s , payment p where b.asn=s.asn and p.asn=b.asn and (p.datd>"+(d1-1)+" and p.datd<"+(d2+1)+") and (p.datm>"+(m1-1)+" and p.datm<"+(m2+1)+") and (p.daty>"+(y1-1)+" and p.daty<"+(y2+1)+" ) order by p.datd, b.rcpt");
                    c2.rs = c2.st.executeQuery(dataQuery);
                    int serialNumber=0;
                    int j=0;
                    int total=0;
                    while(c2.rs.next())
                    {
                        serialNumber=j+1;
                        
                        int asn = c2.rs.getInt(1);
                        String query = "select subscription_code, subscription_number, bulk_despatch_code, ending_period, first_name, last_name, state from subscribers_primary_details where asn = "+asn;
                        //System.out.println(query);
                        subscriberDetailsFetchConnection.rs = subscriberDetailsFetchConnection.st.executeQuery(query);
                        if(subscriberDetailsFetchConnection.rs.next())
                        {
                            data[j][0] = serialNumber;
                            data[j][1]=subscriberDetailsFetchConnection.rs.getString(1)+" "+subscriberDetailsFetchConnection.rs.getString(2);
                            data[j][2]=subscriberDetailsFetchConnection.rs.getInt(3);
                            data[j][3] = c2.rs.getString(2);
                            data[j][4]=c2.rs.getInt(3);
                            int amt=c2.rs.getInt(4);
                            total+=amt;
                            data[j][5]=amt;
                            data[j][6]=c2.rs.getDate(5);
                            data[j][7]= c2.rs.getDate(6);
                            data[j][8]=subscriberDetailsFetchConnection.rs.getString(5)+" "+subscriberDetailsFetchConnection.rs.getString(6);
                            data[j][9]=subscriberDetailsFetchConnection.rs.getString(7);
                            j++;
                        }
                    }
                    
                    data[j][0]="TOTAL";
                    data[j][3]=total;
                    
                    tb1=new JTable(data,col);
                    sp=new JScrollPane(tb1);
                    sp.setBounds(20,100,845,410);
                    con.add(sp);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("exception"+e);
            }
        }
        /*if(ae.getSource()==b2)
        {
        new modify(Integer.parseInt(t2.getText()));
        this.dispose();
        }*/
        if(ae.getSource()==backButton)
        {
            this.dispose();
            new sams();
        }
        
        
        if(ae.getSource()==printButton)
        {
            try {
                MessageFormat headerFormat = new MessageFormat("Detailed Account Book Details"); // \t (Page {0})");
                MessageFormat footerFormat = new MessageFormat(""+d1+"-"+m1+"-"+y1+" TO "+d2+"-"+m1+"-"+y1+"\t ( Page {0} )");
                tb1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
                System.err.println("Error printing: " + pe.getMessage());
            }
        }
        
    }
}	