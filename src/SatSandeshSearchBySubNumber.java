import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import net.miginfocom.swing.MigLayout;


public class SatSandeshSearchBySubNumber implements ActionListener
{
    JLabel subNumberLabel,asnEntryLabel, phoneNumberLabel;
    JTextField subNumberText,asnText, rcptNumberTextField,addressText;
    JTable searchResultsTable;
    JScrollPane sp;
    //Container con;
    JButton findButton,viewButton,closeButton, resetButton;
    Object col[]={"ASN","FIRST NAME","LAST NAME","DISTRICT","STATE"};
    JComboBox stateCodeDropDown;
    JFrame satSandeshSearchWindow;
    MigLayout mLayout= new MigLayout( "insets 30");
    JComboBox seriesNameDropDown;
    TextFieldWithLimit firstNameText,lastNameText,districtText,phoneNumberText;
    JComboBox stateNameDropDown;
    JLabel l1,l2,l3,l4;
    
    JRadioButton subNumberRadioButton, nameRadioButton,addressRadioButton, rcptNumberRadioButton, phoneNumberRadioButton;
    ButtonGroup typeGroup;
    
    //String[] subCodes = {"BH","BD","CM","DL","EN","HR","LF","LH","MH","MP","MS","NA","PB","PJ","RJ","UK","UP","UR"};
    Object[] subCodes; 
    
    int mode;
    int x,y;
    
    public static void main(String args[])
    {
        new SatSandeshSearchBySubNumber();
    }
    
    SatSandeshSearchBySubNumber()
    {
        
        satSandeshSearchWindow = new JFrame();
        
        try
        {
            satSandeshSearchWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (ClassNotFoundException cnf)
        {
            JOptionPane.showMessageDialog(satSandeshSearchWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException cnf) {
            JOptionPane.showMessageDialog(satSandeshSearchWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException cnf) {
            JOptionPane.showMessageDialog(satSandeshSearchWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedLookAndFeelException cnf) {
            JOptionPane.showMessageDialog(satSandeshSearchWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        satSandeshSearchWindow = new JFrame("Sat Sandesh Search Subscriber");
        satSandeshSearchWindow.setLayout(mLayout);
        satSandeshSearchWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshSearchWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshSearchWindow.setSize((screenSize.width)*9/10,(screenSize.height*4)/5);
        Dimension frameSize = satSandeshSearchWindow.getSize();
        x = (screenSize.width - frameSize.width)  / 2;
        y = (screenSize.height - frameSize.height) / 3;
        satSandeshSearchWindow.setLocation(x, y);
        
        subNumberRadioButton = new JRadioButton("Sub Number");
        subNumberRadioButton.addActionListener(this);
        
        nameRadioButton = new JRadioButton("Name");
        nameRadioButton.addActionListener(this);
        
        addressRadioButton = new JRadioButton("Address");
        addressRadioButton.addActionListener(this);
        
        rcptNumberRadioButton = new JRadioButton("Receipt Number");
        rcptNumberRadioButton.addActionListener(this);
        
        phoneNumberRadioButton = new JRadioButton("Phone Number");
        phoneNumberRadioButton.addActionListener(this);
        
        typeGroup  = new ButtonGroup();
        typeGroup.add(subNumberRadioButton);
        typeGroup.add(nameRadioButton);
        typeGroup.add(addressRadioButton);
        typeGroup.add(rcptNumberRadioButton);
        typeGroup.add(phoneNumberRadioButton);
        
        
        satSandeshSearchWindow.add(subNumberRadioButton);
        
        subCodes = SamsUtilities.getSubCodes();
        
        //con.setBackground(Color.cyan);
        subNumberLabel=new JLabel("Enter Sub No");
        satSandeshSearchWindow.add(subNumberLabel);
        
        stateCodeDropDown=new JComboBox(subCodes);
        satSandeshSearchWindow.add(stateCodeDropDown);
        
        subNumberText= new JTextField("");
        satSandeshSearchWindow.add(subNumberText,"wrap, w 100!");
        
        satSandeshSearchWindow.add(nameRadioButton);
        
        l1=new JLabel("First Name");
        //l1.setBounds(40,40,150,20);
        satSandeshSearchWindow.add(l1);
        
        firstNameText= new TextFieldWithLimit(16,16);
        satSandeshSearchWindow.add(firstNameText);
        
        
        l2=new JLabel("Last Name");
        satSandeshSearchWindow.add(l2);
        
        lastNameText= new TextFieldWithLimit(15,15);
        satSandeshSearchWindow.add(lastNameText, "w 100!");
        
        
        l3=new JLabel("District");
        satSandeshSearchWindow.add(l3);
        
        districtText=new TextFieldWithLimit(32,32);
        satSandeshSearchWindow.add(districtText, "w 100!");
        
        l4=new JLabel("State");
        satSandeshSearchWindow.add(l4);
        
        stateNameDropDown = new JComboBox(SamsUtilities.fillStateNameList());
        satSandeshSearchWindow.add(stateNameDropDown,"wrap, w 140!");
        
        
        satSandeshSearchWindow.add(addressRadioButton);
        addressText = new JTextField();
        satSandeshSearchWindow.add(addressText,"wrap, w 100!");
        
        
        
        satSandeshSearchWindow.add(rcptNumberRadioButton);
        
        seriesNameDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        satSandeshSearchWindow.add(seriesNameDropDown);
        
        rcptNumberTextField = new JTextField("");
        satSandeshSearchWindow.add(rcptNumberTextField, "wrap, w 100!");
        
        
        satSandeshSearchWindow.add(phoneNumberRadioButton);
        
        phoneNumberText = new TextFieldWithLimit(10,10);
        satSandeshSearchWindow.add(phoneNumberText, "wrap, w 100!");
        
        
        findButton= new JButton("Find");
        findButton.setMnemonic('F');
        findButton.setToolTipText("Find the required invoice");
        satSandeshSearchWindow.add(findButton,"wrap");
        findButton.addActionListener(this);
        
        asnEntryLabel=new JLabel("Enter ASN");
        satSandeshSearchWindow.add(asnEntryLabel,"w 100");
        
        asnText= new JTextField();
        satSandeshSearchWindow.add(asnText,"wrap, w 100!");
        
        viewButton=new JButton("View");
        viewButton.setMnemonic('V');
        viewButton.setToolTipText("View the Record");
        satSandeshSearchWindow.add(viewButton);
        viewButton.addActionListener(this);
        
        
        
        resetButton=new JButton("Reset");
        resetButton.setMnemonic('R');
        //b3.setIcon(i);
        satSandeshSearchWindow.add(resetButton);
        resetButton.addActionListener(this);
        
        
        //ImageIcon i=new ImageIcon("close.jpeg");
        closeButton=new JButton("Close");
        closeButton.setMnemonic('C');

        //b3.setIcon(i);
        
        closeButton.setBackground(Color.white);
        closeButton.setToolTipText("close and return to previous page");
        satSandeshSearchWindow.add(closeButton);
        closeButton.addActionListener(this);
        
        satSandeshSearchWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshSearchWindow.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==findButton)
        {
            String stateCode = (String)(stateCodeDropDown.getSelectedItem());
            String subNumber = subNumberText.getText();
            if(mode == 1)
            {
                searchBySubNumberAndFillTable(stateCode,subNumber,searchResultsTable);
            }
            if(mode == 3)
            {
                String address = addressText.getText();
                searchByAddressAndFillTable(address,searchResultsTable);
            }
            
            if(mode == 2)
            {
                searchBySubscriberDetailsAndFIllTable(firstNameText.getText(), lastNameText.getText(), districtText.getText(), stateNameDropDown.getSelectedItem().toString(), searchResultsTable);
            }
            if(mode == 4)
            {
                String seriesName = (String)seriesNameDropDown.getSelectedItem();
                if(seriesName.isEmpty())
                {
                    JOptionPane.showMessageDialog(satSandeshSearchWindow,"Please select series", "Please select series", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                searchByReceiptNumberAndFillTable(seriesName, rcptNumberTextField.getText(), searchResultsTable);
            }
            if(mode == 5)
            {
                String phoneNumber = phoneNumberText.getText();
                if(phoneNumber.isEmpty())
                {
                    JOptionPane.showMessageDialog(satSandeshSearchWindow,"Please put phone number", "Please give phone number", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                searchByPhoneNumberAndFillTable(phoneNumber,searchResultsTable);
            }
            
            Dimension frameSize = satSandeshSearchWindow.getSize();
            int width = frameSize.width;
            int height = frameSize.height;
            
            sp.setBounds(20,270,width - 40,height -300);  
        }
        else if(ae.getSource()==viewButton)
        {
            new SatSandeshModifySubscriptionData(Integer.parseInt(asnText.getText()));
            satSandeshSearchWindow.dispose();
        }
        
        else if(ae.getSource()==resetButton)
        {
            new SatSandeshSearchBySubNumber();
            satSandeshSearchWindow.dispose();
        }
        
        else if(ae.getSource()==closeButton)
        {
            satSandeshSearchWindow.dispose();
            new sams();
        }
        
        else if(ae.getSource() == subNumberRadioButton)
        {
            mode = 1;
        }
        else if(ae.getSource() == nameRadioButton)
        {
            mode = 2;
        }
        else if(ae.getSource() == addressRadioButton)
        {
            mode = 3;
        }
        else if(ae.getSource() == rcptNumberRadioButton)
        {
            mode = 4;
        }
        else if(ae.getSource() == phoneNumberRadioButton)
        {
            mode = 5;
        }
        
    }
    
    public void searchBySubNumberAndFillTable(String stateCode, String subNumber, JTable tb1)
    {
        int i=0;
        try
        {
            connect c1=new connect();
            //c1.rs=c1.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and b.subno="+subNumber+" and b.subnos='"+stateCode+"'");
            c1.rs=c1.st.executeQuery("select asn from subscribers_primary_details where subscription_number="+subNumber+" and subscription_code='"+stateCode+"'");		
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
                
                //c2.rs=c2.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and b.subno="+subNumber+" and b.subnos='"+stateCode+"'");		//select asn from basic where subno="+Integer.parseInt(subNumberText.getText())+" or subnos='"+stateCodeDropDown.getSelectedItem()+"'order by asn");
                c2.rs=c2.st.executeQuery("select asn, first_name, last_name, district, state from subscribers_primary_details where subscription_number="+subNumber+" and subscription_code='"+stateCode+"'");		
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
                //sp.setBounds(20,170,545,410);
                satSandeshSearchWindow.add(sp);
                
                
                for (int x=0; x<5; x++)
                {
                    TableColumn column = tb1.getColumnModel().getColumn(i);
                    //System.out.println(searchResultsTable.getColumnModel().getColumn(i));
                    
                    if (x==0) column.setPreferredWidth(5);
                    if (x==1) column.setPreferredWidth(5);
                    if (x==2) column.setPreferredWidth(5);
                    if (x==3) column.setPreferredWidth(5);
                    if (x==4) column.setPreferredWidth(50);
                    
                }
                
            }
        }
        catch(Exception e)
        {
            System.out.println("exception"+e);
        }
        
        
    }
    
    public void searchByAddressAndFillTable(String address, JTable tb1)
    {
        int i=0;
        try
        {
            connect c1=new connect();
            //c1.rs=c1.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and (s.add1 like '"+address+"%' or s.add2 like '%"+address+"%' or s.add3 like '%"+address+"%' )");
            c1.rs=c1.st.executeQuery("select asn from subscribers_primary_details where (address_line1 like '"+address+"%' or address_line2 like '%"+address+"%' or address_line3 like '%"+address+"%' )");
            while(c1.rs.next())
            {
                i++;
            }
            c1.rs.close();
            
            System.out.println(i);
            
            if(i==0)
                JOptionPane.showMessageDialog(null,"No Records Found","No Records",JOptionPane.ERROR_MESSAGE);
            
            else
            {
                
                connect c2=new connect();
                
                //c2.rs=c2.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and (s.add1 like '"+address+"%' or s.add2 like '%"+address+"%' or s.add3 like '%"+address+"%')");
                c2.rs=c2.st.executeQuery("select asn, first_name, last_name, district, state from subscribers_primary_details where (address_line1 like '"+address+"%' or address_line2 like '%"+address+"%' or address_line3 like '%"+address+"%')");
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
                searchResultTable.setRowSelectionAllowed(false);
                searchResultTable.setColumnSelectionAllowed(false);
                searchResultTable.setCellSelectionEnabled(false);*/
                //tb1.setEnabled(false);
                
                
                /*tb1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
                }*/
                
                //-------------------------------------copy till here --------------------------------------------------//
                sp=new JScrollPane(tb1);
                //sp.setBounds(20,230,545,410);
                //System.out.println(x + " "+ y);
                //String layout = "w "+(x-50)+"!, h "+(y-200)+"!";
                satSandeshSearchWindow.add(sp);
                
            }
        }
        catch(Exception e)
        {
            System.out.println("exception"+e);
        }
    }
    
    public void searchBySubscriberDetailsAndFIllTable(String firstName, String lastName,String district,String state, JTable tb1)
    {
        int i=0;
        
        
        firstName =firstName.trim();
        lastName = lastName.trim();
        district = district.trim();
        state = state.trim();
        
        try
        {
            String percentile = "%%";
            connect c1=new connect();
            //String searchQuery = "select s.asn , s.fname , s.lname , s.dist , s.state from subdetails s, basic b where b.asn=s.asn ";
            String searchQuery = "select asn , first_name , last_name , district , state from subscribers_primary_details where asn>0 ";
            
            if(firstName.length()>0)
                searchQuery += "and first_name like '"+firstName+"%' ";
            //searchQuery += "and s.fname like '"+firstName+"%' ";
            
            if(lastName.length()>0)
                searchQuery += "and last_name like '"+lastName+"%' ";
            //searchQuery += "and s.lname like '"+lastName+"%' ";
            
            
            if(district.length()>0)
                searchQuery += "and district like '"+district+"%' ";
            //searchQuery += "and s.dist like '"+district+"%' ";
            
            
            if(state.length()>0)
                searchQuery += "and state like '"+SamsUtilities.getStateCodeForStateName(state)+"%' ";
            //searchQuery += "and s.state like '"+state+"%' ";
            
            //searchQuery += "order by s.asn";
            searchQuery += "order by asn";
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
                //sp.setBounds(20,220,545,410);   //410
                satSandeshSearchWindow.add(sp);
                c2.closeAll();
                
            }
        }
        catch(Exception e)
        {
            System.out.println("exception"+e);
        }
    }
    
    public void searchByReceiptNumberAndFillTable(String seriesName, String rcptNumber, JTable tb1)
    {
        int i=0;
        try
        {
            
            connect c1=new connect();
            //c1.rs=c1.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and b.series_name = '"+seriesName+"' and b.rcpt="+rcptNumber);
            c1.rs=c1.st.executeQuery("select asn, first_name, last_name, district, state from subscribers_primary_details where series_name = '"+seriesName+"' and receipt_number="+rcptNumber);
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
                
                //c2.rs=c2.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and  b.series_name = '"+seriesName+"' and b.rcpt="+rcptNumber);
                c2.rs=c2.st.executeQuery("select asn, first_name, last_name, district, state from subscribers_primary_details where series_name = '"+seriesName+"' and receipt_number="+rcptNumber);
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
                //sp.setBounds(20,70,545,410);
                satSandeshSearchWindow.add(sp);
                
            }
        }
        catch(Exception e)
        {
            System.out.println("exception"+e);
        }
    }
    
    public void searchByPhoneNumberAndFillTable(String phoneNumber, JTable tb1)
    {
        int i=0;
        try
        {
            
            connect c1=new connect();
            //c1.rs=c1.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and b.series_name = '"+seriesName+"' and b.rcpt="+rcptNumber);
            c1.rs=c1.st.executeQuery("select asn, first_name, last_name, district, state from subscribers_primary_details where phone_number = "+phoneNumber);
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
                
                //c2.rs=c2.st.executeQuery("select b.asn, s.fname, s.lname, s.dist, s.state from basic b, subdetails s where b.asn=s.asn and  b.series_name = '"+seriesName+"' and b.rcpt="+rcptNumber);
                c2.rs=c2.st.executeQuery("select asn, first_name, last_name, district, state from subscribers_primary_details where phone_number="+phoneNumber);
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
                //sp.setBounds(20,70,545,410);
                satSandeshSearchWindow.add(sp);
                
            }
        }
        catch(Exception e)
        {
            System.out.println("exception"+e);
        }
    }
    
    
}	