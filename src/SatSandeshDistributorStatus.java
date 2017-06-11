import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.text.*;



public class SatSandeshDistributorStatus extends JFrame implements ActionListener
{
    JLabel l1,l2;
    TextFieldWithLimit t1,t2,t3, t4, t5, t6;
    JTable tb1;
    JScrollPane sp;
    Container con;
    JButton b1,showDataButton,backButton, printButton;
    Object col[]={"D#","NAME","DISTRICT","STATE","DIST TYPE","ACTIVE","OTHERS","TOTAL"};
    
    int tot=0, act=0, oth=0;
    int act_num=0, oth_num=0,tot_num=0;
    int month = 0;
    int year = 0;
    JLabel monthLabel, yearLabel, languageLabel;
    JTextField monthText, yearText;
    JComboBox languageDropDown;
    
    public static void main(String args[])
    {
        new SatSandeshDistributorStatus();
    }
    
    public SatSandeshDistributorStatus()
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
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((screenSize.width)*9/10,(screenSize.height*4)/5);
        Dimension frameSize = this.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 3;
        setLocation(x, y);
        
        
        //setSize(900,650);
        //setLocation(200,80);
        //setVisible(true);
        con.setLayout(null);
        setTitle("Distributor Details");
        //con.setBackground(Color.cyan);
        /*l1=new JLabel("MEMBER STATUS FOR DISTRIBUTOR FOR MONTH " + month+"/"+year);
        l1.setBounds(150,40,550,20);
        l1.setFont(new Font("SERIF", Font.BOLD, 18));
        con.add(l1);*/
        
        monthLabel =new JLabel("Month");
        yearLabel =new JLabel("Year");
        languageLabel =new JLabel("Language");
        
        monthText = new JTextField(20);
        yearText = new JTextField(40);
        languageDropDown = new JComboBox();
        
        monthLabel.setBounds(30,30,50,20);
        add(monthLabel);
        
        monthText.setBounds(110,30,50,20);
        add(monthText);
        monthText.setText(""+SamsUtilities.getCurrentMonth());
        
        yearLabel.setBounds(180,30,50,20);
        add(yearLabel);
        
        yearText.setBounds(260,30,50,20);
        add(yearText);
        yearText.setText(""+SamsUtilities.getCurrentYear());
        
        /*languageLabel.setBounds(30,110,80,20);
        add(languageLabel);
        
        languageDropDown.setBounds(110,110,100,20);
        add(languageDropDown);*/
        
        
        showDataButton = new JButton("Show Data");
        showDataButton.setMnemonic('S');
        showDataButton.setBounds(370,30,90,25);
        con.add(showDataButton);
        showDataButton.addActionListener(this);
        
        
        printButton=new JButton("Print");
        printButton.setMnemonic('P');
        printButton.setBounds(200,530,90,25);
        con.add(printButton);
        printButton.addActionListener(this);
        
        
        backButton=new JButton("Back");
        backButton.setMnemonic('b');
        backButton.setBounds(480,530,90,25);
        con.add(backButton);
        backButton.addActionListener(this);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            b1.setEnabled(false);
            
        }
        if(ae.getSource()==showDataButton)
        {
            String monthStr = monthText.getText();
            String yearStr = yearText.getText();
            month = Integer.parseInt(monthStr);
            year = Integer.parseInt(yearStr);
            
            if(month == 0 || month > 12 )
            {
                JOptionPane.showMessageDialog(null,"Please enter month correctly", "Invalid input", JOptionPane.ERROR_MESSAGE);
                monthText.setText("");
                //yearText.setText(""+SamsUtilities.getCurrentYear());
                monthText.requestFocus();
                return;
            }
            if(year == 0 || year < 2005 )
            {
                JOptionPane.showMessageDialog(null,"Please enter year correctly", "Invalid input", JOptionPane.ERROR_MESSAGE);
                yearText.setText("");
                //yearText.setText(""+SamsUtilities.getCurrentYear());
                yearText.requestFocus();
                return;
            }
            
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
                    Object data[][]= new Object[i+1][8];
                    int j=0;
                    
                    while(c2.rs.next())
                    {
                        z[j]=c2.rs.getInt(1);
                        data[j][0]=z[j];
                        data[j][1]=c2.rs.getString(2)+" "+c2.rs.getString(3);
                        data[j][2]=c2.rs.getString(11);
                        data[j][3]=c2.rs.getString(12);
                        data[j][4]=c2.rs.getString(14);
                        j++;
                    }
                    
                    j=0;
                    
                    //System.out.println("C : "+c);
                    
                    String ending_period = year + "-"+ month+"-28";
                    for(i=0;i<c;i++)
                    {
                        act_num=0;
                        oth_num=0;
                        tot_num=0;
                        String query = "select count(b.asn) from basic b, despcode d where b.status='Active' and b.dno="+z[i]+" group by d.dno order by d.dno";
                        String mainTableQuery = "select count(asn) from subscribers_primary_details where bulk_despatch_code = "+z[i]+" and ending_period >= '"+ending_period+"' group by bulk_despatch_code order by bulk_despatch_code";
                        c2.rs=c2.st.executeQuery(mainTableQuery);
                        if(c2.rs.next())
                        {
                            
                            act_num=c2.rs.getInt(1);
                            data[j][5]=" "+act_num;
                            //j++;
                        }
                        else
                            data[j][5]=" 0";
                        
                        //					j=0;
                        query = "select count(b.asn) from basic b, despcode d where b.status not in ('Active') and b.dno="+z[i]+" group by d.dno order by d.dno";
                        mainTableQuery = "select count(asn) from subscribers_primary_details where bulk_despatch_code = "+z[i]+" and ending_period <'"+ending_period+"' group by bulk_despatch_code order by bulk_despatch_code";
                        
                        c2.rs=c2.st.executeQuery(mainTableQuery);
                        if(c2.rs.next())
                        {
                            
                            oth_num=c2.rs.getInt(1);
                            data[j][6]=""+oth_num;
                            //j++;
                        }
                        else
                            data[j][6]=" 0";
                        
                        //				j=0;
                        query = "select count(b.asn) from basic b, despcode d where b.dno="+z[i]+" group by d.dno order by d.dno";
                        mainTableQuery = "select count(asn) from subscribers_primary_details where bulk_despatch_code = "+z[i]+" group by bulk_despatch_code order by bulk_despatch_code";
                        c2.rs=c2.st.executeQuery(mainTableQuery);
                        if(c2.rs.next())
                        {
                            
                            tot_num=c2.rs.getInt(1);
                            data[j][7]=""+tot_num;
                            //j++;
                        }
                        else
                            data[j][7]=" 0";
                        
                        tot+=tot_num;
                        act+=act_num;
                        oth+=oth_num;
                        
                        j++;
                    }
                    data[j][2]="TOTAL";
                    data[j][5]=""+act;
                    data[j][6]=""+oth;
                    data[j][7]=""+tot;
                    
                    tb1=new JTable(data,col);
                    sp=new JScrollPane(tb1);
                    tb1.setFont(new Font("Serif", Font.PLAIN, 16));
                    
                    //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    tb1.getColumnModel().getColumn(0).setPreferredWidth(50);
                    tb1.getColumnModel().getColumn(1).setPreferredWidth(280);
                    tb1.getColumnModel().getColumn(2).setPreferredWidth(100);
                    tb1.getColumnModel().getColumn(3).setPreferredWidth(50);
                    tb1.getColumnModel().getColumn(4).setPreferredWidth(50);
                    tb1.getColumnModel().getColumn(5).setPreferredWidth(50);
                    tb1.getColumnModel().getColumn(6).setPreferredWidth(50);
                    tb1.getColumnModel().getColumn(7).setPreferredWidth(50);
                    tb1.setRowHeight(25);
                    
                    Dimension frameSize = this.getSize();
                    int x = (frameSize.width);
                    int y = (frameSize.height);
                    sp.setBounds(20,100,x-40,y-250);
                    con.add(sp);
                    showDataButton.setEnabled(false);
                    
                    /*l1=new JLabel("MEMBER STATUS FOR DISTRIBUTOR FOR MONTH " + month+"/"+year);
                    l1.setBounds(150,60,550,20);
                    l1.setFont(new Font("SERIF", Font.BOLD, 16));
                    con.add(l1);*/
                    this.setTitle("MEMBER STATUS FOR DISTRIBUTOR FOR MONTH " + month+"/"+year);
                }
            }
            catch(Exception e)
            {
                System.out.println("exception"+e);
                e.printStackTrace();
            }
            
        }
        if(ae.getSource()==backButton)
        {
            new sams();
            this.dispose();
        }
        
        if(ae.getSource()==printButton)
        {
            
            try
            {
                MessageFormat headerFormat = new MessageFormat("Sat Sandesh Distributor Status for m/o " + SamsUtilities.getCurrentMonth()+"/"+SamsUtilities.getCurrentYear()); // \t (Page {0})");
                MessageFormat footerFormat = new MessageFormat("- Page {0} -");
                tb1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            }
            catch (PrinterException pe)
            {
                System.err.println("Error printing: " + pe.getMessage());
            }
        }
        
    }
}	