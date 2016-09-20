
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Savepoint;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;


public class SatSandeshBulkSubscription implements ActionListener, ItemListener, FocusListener
{
    public static void main(String args[])
    {
        SatSandeshBulkSubscription e=new SatSandeshBulkSubscription();
    }
    
    int globalAsn;
    
    JFrame satSandeshBulkSubscriptionWindow;
    JLabel  distributionNumberLabel, seriesLabel, receiptNumberLabel, dateLabel, startingPeriodLabel, nameLabel ;
    JLabel numberOfRecordsLabel, amountLabel, subscriptionTypeLabel, endingPeriodLabel, counterLabel,subIssueCounterLabel;
    
    JButton addDataButton, cancelButton, saveButton;
    
    JTextField receiptNumberText, dateText,monthText, yearText;
    JTextField numberOfRecordsText, amountText, endingPeriodText,subIssueCounterText;
    JComboBox distributionNumberDropDown, seriesDropDown , startingPeriodMonthDropDown, startingPeriodYearDropDown, languageDropDown, subscriptionDurationDropDown, counterDropDown;
    
    JRadioButton nameRadioButton;
    
    int endingMonth, endingYear;
    
    JTable bulkEntryTable;
    JScrollPane scrollPane;
    
    Object col[]={"S No","ASN","Sub code","Sub Number", "First name","Last Name"};
    
    MigLayout mLayout= new MigLayout( "insets 30");
    
    public SatSandeshBulkSubscription()
    {
        
        satSandeshBulkSubscriptionWindow = new JFrame();
        
        try
        {
            satSandeshBulkSubscriptionWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (ClassNotFoundException cnf)
        {
            JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException cnf) {
            JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException cnf) {
            JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedLookAndFeelException cnf) {
            JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        satSandeshBulkSubscriptionWindow = new JFrame("Sat Sandesh Bulk Subscription");
        satSandeshBulkSubscriptionWindow.setLayout(mLayout);
        satSandeshBulkSubscriptionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshBulkSubscriptionWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshBulkSubscriptionWindow.setSize((screenSize.width)*9/10,(screenSize.height*4)/5);
        Dimension frameSize = satSandeshBulkSubscriptionWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 3;
        satSandeshBulkSubscriptionWindow.setLocation(x, y);
        
        
        distributionNumberLabel = new JLabel("<html>D#</html>");
        receiptNumberLabel = new JLabel("<html>Receipt No.</html>");
        dateLabel = new JLabel("<html>Date</html>");
        startingPeriodLabel = new JLabel("<html>Starting period</html>");
        nameLabel = new JLabel("<html>Name</html>");
        
        numberOfRecordsLabel  = new JLabel("<html>No of Records</html>");
        amountLabel = new JLabel("<html>Amount</html>");
        subscriptionTypeLabel = new JLabel("<html>Subscription Type</html>");
        endingPeriodLabel  = new JLabel("<html>Ending Period</html>");
        seriesLabel = new JLabel("<html><b>-</b></html>");
        counterLabel=new JLabel("<html>Counter</html>");
        subIssueCounterLabel = new JLabel("<html>Sub Counter</html>");
        
        //asn1=new JLabel("ASN");
        //sub1=new JLabel("SUB No");
        //status1=new JLabel("Status");
        
        
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        receiptNumberText = new TextFieldWithLimit(5,5);
        dateText = new TextFieldWithLimit(2,2);
        monthText=new TextFieldWithLimit(2,2);
        yearText=new TextFieldWithLimit(4,4);
        startingPeriodMonthDropDown = new JComboBox();
        startingPeriodYearDropDown = new JComboBox();
        subIssueCounterText = new JTextField();
        
        monthText.setText(""+SamsUtilities.getCurrentMonth());
        yearText.setText(""+SamsUtilities.getCurrentYear());
        
        for( int month =1; month <= 12 ; month++ )
            startingPeriodMonthDropDown.addItem(""+month);
        
        
        int currYear = SamsUtilities.getCurrentYear();
        
        for( int year=(currYear-2) ; year<(currYear+10) ; year++)
        {
            startingPeriodYearDropDown.addItem(""+year);
        }
        
        
        startingPeriodYearDropDown.setSelectedItem(""+currYear);
        int currMonth = SamsUtilities.getCurrentMonth();
        
        startingPeriodMonthDropDown.setSelectedItem(""+currMonth);
        startingPeriodMonthDropDown.addItemListener(this);
        startingPeriodYearDropDown.addItemListener(this);
        
        
        numberOfRecordsText  = new TextFieldWithLimit(5,5);
        amountText = new TextFieldWithLimit(4,5);
        amountText.setText("100");
        endingPeriodText  = new TextFieldWithLimit(7,7);
        endingPeriodText.setEnabled(false);
        
        
        //Object[] items={"BH","BD","CM","DL","EN","HR","LF","LH","MH","MP","MS","PB","PJ","RJ","UK","UP","UR"};
        //subNumberCodeDropDown=new JComboBox(items);
        //subNumberCodeDropDown.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        
        subscriptionDurationDropDown=new JComboBox();
        
        //histt1=new JComboBox();
        //histt1.addItem("Kirpal Bagh");
        //histt1.addItem("Kirpal Ashram");
        //histt1.addItem("Sawan Ashram");
        //histt1.addItem("Darshan Dham");
        //histt1.addItem("Tours/Function");
        Object[] languages={"Hindi", "English", "Urdu", "Punjabi"};
        languageDropDown = new JComboBox(languages);
        languageDropDown.addItemListener(this);
        
        receiptNumberText.addFocusListener(this);
        
        counterDropDown=new JComboBox();
        counterDropDown.addItem("");
        counterDropDown.addItem("Kirpal Bagh");
        counterDropDown.addItem("Kirpal Ashram");
        counterDropDown.addItem("Sawan Ashram");
        
        addDataButton = new JButton("Add Data");
        cancelButton =new JButton("Back");
        saveButton = new JButton("Save/OK");
        
        addDataButton.setMnemonic('a');
        cancelButton.setMnemonic('b');
        saveButton.setMnemonic('s');
        
        //-----------------------------------------modififcations------------------------------------------//
        distributionNumberDropDown = new JComboBox();
        try
        {
            distributionNumberDropDown.addItem(" ");
            connect c13=new connect();
            c13.rs=c13.st.executeQuery("select dno from despcode order by dno");
            while(c13.rs.next())
            {
                distributionNumberDropDown.addItem(""+c13.rs.getInt(1));
            }
            c13.st.close();
            c13.con.close();
        }
        catch(Exception e){
            //e.printStackTrace();
        }
        distributionNumberDropDown.addItemListener(this);
        
        
        //------------------------------------------------end------------------------------------------------//
        
        subscriptionDurationDropDown.addItem("1 year");
        subscriptionDurationDropDown.addItem("2 year");
        subscriptionDurationDropDown.addItem("2 year plus");
        subscriptionDurationDropDown.addItem("3 year");
        subscriptionDurationDropDown.addItem("3 year plus");
        subscriptionDurationDropDown.addItem("5 year");
        subscriptionDurationDropDown.addItem("5 year plus");
        subscriptionDurationDropDown.addItem("Life");
        subscriptionDurationDropDown.addItem("Comp");
        subscriptionDurationDropDown.addItemListener(this);
        
        //------------------------------------------------end------------------------------------------------//
        
        
        
        addDataButton.addActionListener(this);
        cancelButton.addActionListener(this);
        saveButton.addActionListener(this);
        
        satSandeshBulkSubscriptionWindow.add(distributionNumberLabel);
        satSandeshBulkSubscriptionWindow.add(distributionNumberDropDown);
        
        satSandeshBulkSubscriptionWindow.add(receiptNumberLabel);
        satSandeshBulkSubscriptionWindow.add(seriesDropDown);
        satSandeshBulkSubscriptionWindow.add(seriesLabel);
        satSandeshBulkSubscriptionWindow.add(receiptNumberText);
        
        satSandeshBulkSubscriptionWindow.add(dateLabel);
        satSandeshBulkSubscriptionWindow.add(dateText);
        satSandeshBulkSubscriptionWindow.add(monthText);
        satSandeshBulkSubscriptionWindow.add(yearText);
        
        
        
        //satSandeshBulkSubscriptionWindow.add(nameLabel, "wrap 30px");
        
        satSandeshBulkSubscriptionWindow.add(numberOfRecordsLabel);
        satSandeshBulkSubscriptionWindow.add(numberOfRecordsText,"wrap 30px");
        
        
        satSandeshBulkSubscriptionWindow.add(subscriptionTypeLabel);
        satSandeshBulkSubscriptionWindow.add(languageDropDown);
        satSandeshBulkSubscriptionWindow.add(subscriptionDurationDropDown);
        satSandeshBulkSubscriptionWindow.add(amountLabel);
        satSandeshBulkSubscriptionWindow.add(amountText);
        
        satSandeshBulkSubscriptionWindow.add(startingPeriodLabel);
        satSandeshBulkSubscriptionWindow.add(startingPeriodMonthDropDown);
        satSandeshBulkSubscriptionWindow.add(startingPeriodYearDropDown);
        
        satSandeshBulkSubscriptionWindow.add(endingPeriodLabel);
        satSandeshBulkSubscriptionWindow.add(endingPeriodText);
        satSandeshBulkSubscriptionWindow.add(counterLabel);
        satSandeshBulkSubscriptionWindow.add(counterDropDown,"wrap 30px");
        
        satSandeshBulkSubscriptionWindow.add(subIssueCounterLabel);
        satSandeshBulkSubscriptionWindow.add(subIssueCounterText,"w 100!");
        
        satSandeshBulkSubscriptionWindow.add(addDataButton);
        satSandeshBulkSubscriptionWindow.add(cancelButton);
        satSandeshBulkSubscriptionWindow.add(saveButton, "wrap 30px");
        saveButton.setEnabled(false);
        
        satSandeshBulkSubscriptionWindow.setVisible(true);
        
    }
    
    
    int numItems = 0;
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        
        //database updation
        if(ae.getSource() == addDataButton){
            
            if(numberOfRecordsText.getText().isEmpty()){
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "Please enter number of records to be added", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            numItems = Integer.parseInt(numberOfRecordsText.getText());
            //System.out.println(numItems);
            
            Object data[][]= new Object[numItems][6];
            
            
            String dNumString = (String)(distributionNumberDropDown.getSelectedItem());
            int distributionNumber = Integer.parseInt(dNumString);
            if(distributionNumber < 1){
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "Please select D No. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int currentAsn = 0;
            int currentSubNumber = 0;
            String firstName, lastName;
            
            try
            {
                connect c1=new connect();
                c1.rs=c1.st.executeQuery("select asn from asn");
                c1.rs.next();
                currentAsn = c1.rs.getInt(1);
                //c1.closeAll();
                
                c1.rs=c1.st.executeQuery("select max(subno) from basic where subnos='BD'");
                c1.rs.next();
                currentSubNumber = c1.rs.getInt(1);
                //System.out.println(currentSubNumber);
                
                c1.rs=c1.st.executeQuery("select fname,lname from despcode where dno="+distributionNumber);
                c1.rs.next();
                firstName = c1.rs.getString(1);
                lastName = c1.rs.getString(2);
                c1.closeAll();
                //System.out.println(firstName + " " + lastName);
                
            }
            catch(Exception e1)
            {
                firstName = "";
                lastName = "";
                e1.printStackTrace();
            }
            globalAsn = currentAsn;
            
            for(int i = 0 ; i < numItems ; i++){
                data[i][0] = i+1;
                data[i][1] = "SN"+(currentAsn+i);
                data[i][2] = "BD";
                
                ++currentSubNumber;
                try
                {
                    connect c1=new connect();
                    while(true){
                        
                        c1.rs=c1.st.executeQuery("select subno from basic where subnos='BD' and subno = "+currentSubNumber );
                        if(c1.rs.next())
                            ++currentSubNumber;
                        else break;
                    }
                    c1.closeAll();
                    //System.out.println(currentSubNumber);
                    
                    
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
                data[i][3] = ""+(currentSubNumber);
                data[i][4] = firstName;
                data[i][5] = lastName;
            }
            
            
            bulkEntryTable = new JTable(data,col){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return (column==3 || column==4) ? true : false;
                }
            };
            
            bulkEntryTable.setFont(new Font("Serif", Font.PLAIN, 15));
            
            //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            bulkEntryTable.getColumnModel().getColumn(0).setMaxWidth(130);
            bulkEntryTable.getColumnModel().getColumn(1).setMaxWidth(100);
            bulkEntryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            bulkEntryTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            bulkEntryTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            
            
            bulkEntryTable.setRowHeight(28);
            bulkEntryTable.setShowGrid(true);
            bulkEntryTable.setShowHorizontalLines(true);
            bulkEntryTable.setShowVerticalLines(true);
            bulkEntryTable.setColumnSelectionAllowed(true);
            bulkEntryTable.setRowSelectionAllowed(true);
            bulkEntryTable.setFocusCycleRoot(true);
            bulkEntryTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            
            scrollPane =new JScrollPane(bulkEntryTable);
            
            Dimension frameSize = satSandeshBulkSubscriptionWindow.getSize();
            scrollPane.setBounds(30,200,frameSize.width - 60, frameSize.height - 380);
            //scrollPane.setSize(frameSize.width - 60, frameSize.height - 480);
            //scrollPane.setLocation(30, 200);
            
            satSandeshBulkSubscriptionWindow.add(scrollPane,"span 5, wrap 20px");
            //saveButton.setBounds(frameSize.width/2-40, frameSize.height-40,80,30);
            saveButton.setEnabled(true);
            addDataButton.setEnabled(false);
            
        }
        
        if(ae.getSource() == saveButton)
        {
            String[] firstNames = new String[numItems];
            String[] lastNames = new String[numItems];
            int[] subNumbers = new int[numItems];
            int[] asnNumbers = new int[numItems];
            TableModel model = (TableModel) (bulkEntryTable.getModel());
            
            String dNumString = (String)(distributionNumberDropDown.getSelectedItem());
            
            int distributionNumber = Integer.parseInt(dNumString);
            
            String phone, email, addressPart1, addressPart2, addressPart3, remarks, history, district, state, pin, subscriptionType;
            
            try{
                connect connectionObject = new connect();
                connectionObject.rs=connectionObject.st.executeQuery("select * from despcode where dno="+distributionNumber);
                connectionObject.rs.next();
                phone = connectionObject.rs.getString(4);
                email = connectionObject.rs.getString(5);
                addressPart1 = connectionObject.rs.getString(6);
                addressPart2 = connectionObject.rs.getString(7);
                addressPart3 = connectionObject.rs.getString(8);
                remarks = connectionObject.rs.getString(9);
                district = connectionObject.rs.getString(11);
                state = connectionObject.rs.getString(12);
                pin = connectionObject.rs.getString(13);
                //subscriptionType = connectionObject.rs.getString(14);
                connectionObject.closeAll();
            }
            catch(Exception e)
            {
                phone = "";
                email = "";
                addressPart1 = "";
                addressPart2 = "";
                addressPart3 = "";
                remarks = "";
                //history = "";
                district = "";
                state = "";
                pin = "";
                //subscriptionType = "";
                
            }
            
            
            
            //boolean allFine = true;
            
            for(int i = 0 ; i < numItems; i++ )
            {
                //System.out.print((String) model.getValueAt(i, 1));
                //System.out.print(globalAsn);
                asnNumbers[i] = (globalAsn + i);
                //System.out.print(" " +asnNumbers[i]);
                
                //System.out.print(" "+model.getValueAt(i, 2).toString());
                String subNumber;
                try{
                    subNumber = model.getValueAt(i, 3).toString();
                }
                catch(Exception e)
                {
                    subNumber = "";
                }
                
                //System.out.print(subNumber);
                
                if(subNumber.isEmpty() == false)
                    subNumbers[i] = Integer.parseInt(subNumber);
                else{
                    bulkEntryTable.changeSelection(i, 3, false, false);
                    JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "Please enter sub number in row "+(i+1), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                //check for duplicate sub number
                try{
                    
                    int subNum;
                    String subNumberCode = "BD";
                   
                    
                        subNum  = Integer.parseInt(subNumber);
                        String countQuery = "select count(asn) from basic where subnos = '"+subNumberCode+"' and subno = "+subNum;
                        //String sqlQuery = "select count(asn) from basic where subnos = '"+subNumberCode+"' and subno = "+subNum;
                        //System.out.println(countQuery);
                        connect fillSeriesConnection = new connect();
                        
                        fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(countQuery);
                        fillSeriesConnection.rs.next();
                        int ArrayCount = fillSeriesConnection.rs.getInt(1);
                        if(ArrayCount > 0)
                        {
                            bulkEntryTable.changeSelection(i, 3, false, false);
                            JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "Sub number "+subNumber+" already issued. Please re fill it in row "+(i+1), "ERROR", JOptionPane.ERROR_MESSAGE);
                            fillSeriesConnection.closeAll();
                            return;
                        }
                        fillSeriesConnection.closeAll();
                        
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                
                //System.out.print(" "+subNumbers[i]);
                try{
                    firstNames[i] = model.getValueAt(i, 4).toString();
                }
                catch(Exception e)
                {
                    firstNames[i] = "";
                    bulkEntryTable.changeSelection(i, 4, false, false);
                    JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "Please enter first name in row "+(i+1), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                //System.out.print(" "+firstNames[i]);
                try{
                    lastNames[i] = model.getValueAt(i, 5).toString();
                }
                catch(Exception e)
                {
                    lastNames[i] = "";
                    bulkEntryTable.changeSelection(i, 5, false, false);
                    JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "Please enter last name in row "+(i+1), "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                //System.out.println(" "+lastNames[i]);
                
            }
            
            
            
            
            {
                
                String rcptNumString = receiptNumberText.getText();
                
                //String subscriptionType = "By Post";
                String language = (String)(languageDropDown.getSelectedItem());
                String distributionType = "Distributor";
                String seriesString = (String)(seriesDropDown.getSelectedItem());
                subscriptionType = (String)(subscriptionDurationDropDown.getSelectedItem());
                /*  =================== */
                String paymentType = "Cash";
                String entryDate = dateText.getText();
                String entryMonth = monthText.getText();
                String entryYear = yearText.getText();
                String amountString = amountText.getText();
                String startMonthString = (String)(startingPeriodMonthDropDown.getSelectedItem());
                String startYearString = (String)(startingPeriodYearDropDown.getSelectedItem());
                history = (String)(counterDropDown.getSelectedItem());
                /*  =================== */
                String title = "";
                
                String returnBack = "0";
                
                
                
                int rcptNum = Integer.parseInt(rcptNumString);
                int seriesName = Integer.parseInt(seriesString);
                
                /*  =================== */
                int amount = Integer.parseInt(amountString);
                int startMonth = Integer.parseInt(startMonthString);
                int startYear = Integer.parseInt(startYearString);
                
                int flag = 0;
                
                
                try
                {
                    connect c2 = new connect();
                    Savepoint save1 = c2.con.setSavepoint();
                    c2.con.setAutoCommit(false);
                    
                    for(int i = 0 ; i < numItems; i++ )
                    {
                        
                        c2.a=c2.st.executeUpdate("insert into basic values("+asnNumbers[i]+",'BD',"+
                                subNumbers[i]+",'Active',"+rcptNum+",'"+
                                distributionType+"',"+distributionNumber+",'"+subscriptionType+"','"+language+"','"+
                                seriesName+"',0)");
                        
                        if(c2.a==1)
                            flag=flag+1;
                        //c2.closeAll();
                        
                        
                        //if(distributionNumber>0)
                        
                        //connect c10=new connect();
                        c2.a=c2.st.executeUpdate("insert into d"+distributionNumber+" values ("+asnNumbers[i]+")");
                        //c2.closeAll();
                    
                        
                        //database query for payment fragment
                        
                        //connect c3=new connect();
                        c2.a=c2.st.executeUpdate("insert into payment values("+asnNumbers[i]+",'"+
                                paymentType+"',0,"+entryDate+","+entryMonth+","+entryYear+","+amount+","+
                                startMonth+","+startYear+","+endingMonth+","+endingYear+" , '"+
                                SamsAddons.getCurrentSqlDate()+"', 'New')");
                        
                        if(c2.a==1)
                            flag=flag+1;
                        //c3.closeAll();
                        
                        //database query for subscription details fragment
                        
                        //connect c4=new connect();
                        c2.a=c2.st.executeUpdate("insert into subdetails values ("+asnNumbers[i]+",'"+
                                title+"','"+firstNames[i]+"','"+lastNames[i]+"','"+addressPart1+"','"+addressPart2+"','"+
                                addressPart3+"','"+district+"','"+state+"',"+pin+")");
                        if(c2.a==1)
                            flag=flag+1;
                        //c4.closeAll();
                        
                        
                        //database query for other details fragment
                        
                        //connect c5=new connect();
                        c2.a=c2.st.executeUpdate("insert into otherdet values("+asnNumbers[i]+",'"+
                                phone+"','"+history+"','"+email+"',"+returnBack+",'"+
                                remarks+"','','','')");
                        
                        if(c2.a==1)
                            flag=flag+1;
                        //c5.closeAll();
                    }
                    
                    if(flag == 4*numItems){
                        int z;
                        z = globalAsn + numItems;
                        connect c6=new connect();
                        c6.a=c6.st.executeUpdate("update asn set asn="+z+" where asn="+globalAsn);
                        c6.closeAll();
                        flag=0;
                        JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, numItems + " entries successfully added", "Done", JOptionPane.INFORMATION_MESSAGE);
                        saveButton.setEnabled(false);
                        c2.con.commit();
                    }
                    else
                        c2.con.rollback(save1);
                    
                    c2.con.setAutoCommit(true);
                    c2.closeAll();
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                    
                }
                //DefaultTableModel model = (DefaultTableModel) bulkEntryTable.getModel();
                for(int row = 0 ; row < numItems; row++ )
                {
                    for(int column = 0; column < col.length; column++)
                    {
                        model.setValueAt("", row, column);
                    }
                }
                cancelButton.setText("Back");
                
            }
            
        }
        
        if(ae.getSource() == cancelButton)
        {
            satSandeshBulkSubscriptionWindow.setVisible(false);
            new sams();
            satSandeshBulkSubscriptionWindow.dispose();
            
        }
        
    }
    
    
    @Override
    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource() == distributionNumberDropDown)
        {
            try
            {
                /*connect c14=new connect();
                c14.rs=c14.st.executeQuery("select district, state from despcode where dno="+Integer.parseInt((String)dt1.getSelectedItem()));
                c14.rs.next();
                dist21.setText(""+c14.rs.getString(1));
                dist21.setEnabled(false);
                dist21.setFont(f);
                statt1.setText(""+c14.rs.getString(2));
                statt1.setEnabled(false);
                statt1.setFont(f);
                c14.st.close();
                c14.con.close();*/
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        if(ie.getSource()==subscriptionDurationDropDown)
        {
            try
            {
                connect c7=new connect();
                c7.rs=c7.st.executeQuery("select amount from amountdet where language='"+languageDropDown.getSelectedItem()+"' and duration='"+subscriptionDurationDropDown.getSelectedItem()+"'");
                
                c7.rs.next();
                int amount =c7.rs.getInt(1);
                
                amountText.setText(""+amount);
                
                c7.st.close();
                c7.con.close();
                
                if(subscriptionDurationDropDown.getSelectedItem()=="Urdu"&&subscriptionDurationDropDown.getSelectedItem()=="Punjabi")
                {
                    amountText.setEnabled(true);
                    amountText.setText("");
                }
            }
            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ie.getSource()==startingPeriodMonthDropDown || ie.getSource()==startingPeriodYearDropDown || ie.getSource() == languageDropDown)
        {
            
            int startMonth = Integer.parseInt((String)startingPeriodMonthDropDown.getSelectedItem());
            int startYear = Integer.parseInt((String)startingPeriodYearDropDown.getSelectedItem());
            String language = (String)languageDropDown.getSelectedItem();
            int numberOfYears=0, numberOfMonths=0;
            try
            {
                
                connect c22 = new connect();
                c22.rs = c22.st.executeQuery("select year1, month1 from amountdet where duration='"+subscriptionDurationDropDown.getSelectedItem()+"' and language = '"+language+"'");
                if(c22.rs.next())
                {
                    numberOfYears = c22.rs.getInt(1);
                    numberOfMonths = c22.rs.getInt(2);
                    //System.out.println("year : "+numberOfYears+" month : "+numberOfMonths);
                }
                c22.closeAll();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            endingMonth=((startMonth-1)+numberOfMonths)%12;
            
            int tempFlag=0;
            
            if(endingMonth==0)
                endingMonth=12;
            
            if(endingMonth>0 && endingMonth <numberOfMonths)
                tempFlag++;
            if(endingMonth<12)
                endingYear=startYear+numberOfYears+tempFlag;
            else
                endingYear=startYear+numberOfYears-1+tempFlag;
            
            if(endingMonth==12 && numberOfMonths>0)
            {
                endingYear++;
            }
            
            endingPeriodText.setText(""+endingMonth+"/"+endingYear);
            
        }
    }
    
    
    String seriesNameText;
    
    @Override
    public void focusGained(FocusEvent fe) {
        if(fe.getSource() == receiptNumberText)
        {
            seriesNameText = (String)(seriesDropDown.getSelectedItem());
            //System.out.println("Gained " + seriesNameText);
        }
    }
    
    @Override
    public void focusLost(FocusEvent fe) {
        if(fe.getSource() == receiptNumberText)
        {
            //System.out.println("Lost " + seriesNameText);
            
            int rcptNum;
            String rcpt = receiptNumberText.getText();
            if(seriesNameText.isEmpty() && rcpt.isEmpty() == false)
            {
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow,"Please select series", "Please select series", JOptionPane.ERROR_MESSAGE);
                seriesDropDown.requestFocus();
                return;
            }
            if(seriesNameText.isEmpty() == false && rcpt.isEmpty())
            {
                JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow,"Please add reciept number", "Please add reciept number", JOptionPane.ERROR_MESSAGE);
                receiptNumberText.setText("");
                receiptNumberText.requestFocus();
                return;
            }
            
            if(seriesNameText.isEmpty() == false && rcpt.isEmpty() == false)
            {
                try{
                    rcptNum = (Integer.parseInt(rcpt));
                }
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                    receiptNumberText.setText("");
                    receiptNumberText.requestFocus();
                    return;
                }
                String countQuery = "select count(book_num) from receipt_book_inventory where end_rcpt_num > "+(rcptNum-1)+" and start_rcpt_num < "+(rcptNum+1)+" and series_name='"+seriesNameText+"'";
                String sqlQuery = "select issued_to,book_num, start_rcpt_num, end_rcpt_num from receipt_book_inventory where series_name = '"+seriesNameText+"' and end_rcpt_num > "+(rcptNum-1)+" and start_rcpt_num < "+(rcptNum+1);
                
                connect fillSeriesConnection = new connect();
                try
                {
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(countQuery);
                    fillSeriesConnection.rs.next();
                    
                    int ArrayCount = fillSeriesConnection.rs.getInt(1);
                    if(ArrayCount != 1)
                    {
                        JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        return;
                    }
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(sqlQuery);
                    fillSeriesConnection.rs.next();
                    
                    
                    String centre = fillSeriesConnection.rs.getString(1);
                    if(centre.isEmpty())
                    {
                        JOptionPane.showMessageDialog(satSandeshBulkSubscriptionWindow,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        return;
                    }
                    
                    String bookNum = fillSeriesConnection.rs.getString(2);
                    String subCounterQuery = "select issued_to from sub_issue_details where series_name='"+seriesNameText+"' and book_num="+bookNum+" and rcpt_num="+rcptNum;
                    //System.out.println(centre);
                    counterDropDown.setSelectedItem(centre);
                    subIssueCounterText.setText("");
                    //System.out.println(subCounterQuery);
                    String subCounter;
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(subCounterQuery);
                    
                    if(fillSeriesConnection.rs.next())
                        subCounter = fillSeriesConnection.rs.getString(1);
                    else
                        subCounter = "";
                    //System.out.println(subCounter);
                    subIssueCounterText.setText(subCounter);
                    fillSeriesConnection.closeAll();
                    
                } catch (Exception exc) {
                    exc.printStackTrace();
                    //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
                    fillSeriesConnection.closeAll();
                }
            }
            //System.out.println("Lost");
        }
    }
    
}