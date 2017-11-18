
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author sanjay
 */
public class SatSandeshInventoryStoreIssue implements ActionListener, ItemListener, TableModelListener{
    
    JFrame addSatSandeshInventoryWindow;
    JLabel issueMonthLabel, issueYearLabel, issuePriceLabel, billNumberLabel, quantityLabel, entryDateLabel,/* nameLabel,*/ stallLabel, toStallLabel, languageLabel;
    JLabel pageNumber;
    
    
    TextFieldWithLimit issueMonthtext, issueYearText, issuePriceText, billNumberText, quantityText/*, entryDateText, entryDateMonthText,entryDateYearText, issuedByNameText*/;
    TextFieldWithLimit pageNumberText;
    JComboBox languageDropDown, stallDropDown, toStallDropDown;
    JButton okButton, cancelButton, addButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    Object [] stalls = {"Main Store", "Kirpal Bagh", "Kirpal Ashram","Other"};
    /*String language[] = {
    "",
    "Hindi",
    "English",
    "Urdu",
    "Punjabi"
    };*/
    
    JTable dataEntryTable;
    JScrollPane scrollPane;
    
    Object col[]={"S No.","Language","Issue Year","Issue Month", "Quantity","Price","Total", "Name"};
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    //Object [] stalls = {"Kirpl Bagh", "Kirpal Ashram"};
    public static void main (String[] args)
    {
        new SatSandeshInventoryStoreIssue();
    }
    
    
    public SatSandeshInventoryStoreIssue()
    {
        addSatSandeshInventoryWindow = new JFrame("Issue Sat Sandesh Inventory");
        addSatSandeshInventoryWindow.setLayout(mLayout);
        addSatSandeshInventoryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addSatSandeshInventoryWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = addSatSandeshInventoryWindow.getSize();
        int width = (screenSize.width*8)/10;
        int height = (screenSize.height*8)/10;
        addSatSandeshInventoryWindow.setSize(width,height);
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        //System.out.println(width + " " + height + " " + x + " " + y );
        addSatSandeshInventoryWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            addSatSandeshInventoryWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        issueMonthLabel = new JLabel("<HTML>Issue Month</HTML>");
        issueYearLabel = new JLabel("<HTML>Issue Year</HTML>");
        issuePriceLabel = new JLabel("<HTML>Issue Price</HTML>");
        quantityLabel = new JLabel("<HTML>Quantity</HTML>");
        entryDateLabel = new JLabel("<HTML>Date of Entry</HTML>");
        //nameLabel = new JLabel("<HTML>Your Name</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        stallLabel = new JLabel("<HTML>From Stall</HTML>");
        toStallLabel = new JLabel("<HTML>To Stall</HTML>");
        billNumberLabel = new JLabel("<HTML>Bill No.</HTML>");
        pageNumber =  new JLabel("<HTML>Page Number</HTML>");
        
        
        stallDropDown = new JComboBox(stalls);
        toStallDropDown = new JComboBox(stalls);
        toStallDropDown.setSelectedIndex(1);
        
        pageNumberText = new TextFieldWithLimit(4,4);
        issueMonthtext = new TextFieldWithLimit( 2 , 2 );
        issueYearText = new TextFieldWithLimit( 4 , 4 );
        issuePriceText = new TextFieldWithLimit( 4 , 4 );
        quantityText = new TextFieldWithLimit( 5 , 5 );
        billNumberText = new TextFieldWithLimit( 5 , 5 );
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        //entryDateText = new TextFieldWithLimit( 2 , 2 );
        //entryDateMonthText = new TextFieldWithLimit( 2 , 2 );
        //entryDateYearText = new TextFieldWithLimit( 4 , 4 );
        //issuedByNameText= new TextFieldWithLimit( 32 , 32 );
        //issuedByNameText.setText(SamsUtilities.getUserName());
        
        issueMonthtext.setText(""+SamsUtilities.getCurrentMonth());
        issueYearText.setText(""+SamsUtilities.getCurrentYear());
        
        //entryDateText.setText(""+SamsUtilities.getCurrentDate());
        //entryDateMonthText.setText(""+SamsUtilities.getCurrentMonth());
        //entryDateYearText.setText(""+SamsUtilities.getCurrentYear());
        
        
        addButton = new JButton("Add");
        addButton.setMnemonic('a');
        addButton.addActionListener(this);
        
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        //okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        addSatSandeshInventoryWindow.add(stallLabel);
        addSatSandeshInventoryWindow.add(stallDropDown);
        
        addSatSandeshInventoryWindow.add(toStallLabel);
        addSatSandeshInventoryWindow.add(toStallDropDown);
        
        addSatSandeshInventoryWindow.add(entryDateLabel);
        addSatSandeshInventoryWindow.add(datePicker,"w 150!");
        
        addSatSandeshInventoryWindow.add(pageNumber);
        addSatSandeshInventoryWindow.add(pageNumberText, "w 40!, wrap 20px");
        
        /*addSatSandeshInventoryWindow.add(issueMonthLabel);
        addSatSandeshInventoryWindow.add(issueMonthtext);
        
        addSatSandeshInventoryWindow.add(issueYearLabel);
        addSatSandeshInventoryWindow.add(issueYearText);
        
        addSatSandeshInventoryWindow.add(languageLabel);
        //addSatSandeshInventoryWindow.add(languageDropDown);
        addSatSandeshInventoryWindow.add(issuePriceLabel);
        addSatSandeshInventoryWindow.add(issuePriceText, "wrap 20px");
        
        addSatSandeshInventoryWindow.add(billNumberLabel);
        addSatSandeshInventoryWindow.add(billNumberText);
        
        addSatSandeshInventoryWindow.add(quantityLabel);
        addSatSandeshInventoryWindow.add(quantityText, "wrap 20px");*/
        
        
        //addSatSandeshInventoryWindow.add(entryDateText,"split 3");
        //addSatSandeshInventoryWindow.add(entryDateMonthText);
        //addSatSandeshInventoryWindow.add(entryDateYearText, "wrap 20px");
        
        //addSatSandeshInventoryWindow.add(nameLabel);
        //addSatSandeshInventoryWindow.add(issuedByNameText, "wrap 20px");
        //JComboBox seriesDropDown, bookNumDropDown, stallDropDown;
        addSatSandeshInventoryWindow.add(addButton, "gapleft 60, w :90: ,span 3");
        addSatSandeshInventoryWindow.add(okButton, "gapleft 80, w :90:, span 2");
        addSatSandeshInventoryWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        
        
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        addSatSandeshInventoryWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == addButton)
        {
            int numItems = 20;
            Object data[][]= new Object[numItems][col.length];
            dataEntryTable = new JTable(data,col){
                //@Override
                //public boolean isCellEditable(int row, int column) {
                //    return (column == 2 || column==3 || column==4) ? true : false;
                //}
            };
            
            Object[] issueMonth, issueYear, language;
            try
            {
                connect connectionObject = new connect();
                connectionObject.rs=connectionObject.st.executeQuery("select count(distinct issue_month) from sat_sandesh_inventory_entry where counter ='"+stallDropDown.getSelectedItem().toString()+"'");
                connectionObject.rs.next();
                int numMonths = connectionObject.rs.getInt(1);
                
                connectionObject.rs=connectionObject.st.executeQuery("select count(distinct issue_year) from sat_sandesh_inventory_entry where counter ='"+stallDropDown.getSelectedItem().toString()+"'");
                connectionObject.rs.next();
                int numYears = connectionObject.rs.getInt(1);
                
                connectionObject.rs=connectionObject.st.executeQuery("select count(distinct language) from sat_sandesh_inventory_entry where counter ='"+stallDropDown.getSelectedItem().toString()+"'");
                connectionObject.rs.next();
                int numLanguage = connectionObject.rs.getInt(1);
                
                issueMonth = new Object[numMonths];
                issueYear = new Object[numYears];
                language = new Object[numLanguage];
                
                
                int  i = 0;
                connectionObject.rs=connectionObject.st.executeQuery("select distinct issue_month from sat_sandesh_inventory_entry where counter ='"+stallDropDown.getSelectedItem().toString()+"' order by issue_month ASC");
                while (connectionObject.rs.next())
                {
                    issueMonth[i++] = connectionObject.rs.getInt(1);
                }
                
                i = 0;
                connectionObject.rs=connectionObject.st.executeQuery("select distinct issue_year from sat_sandesh_inventory_entry where counter ='"+stallDropDown.getSelectedItem().toString()+"' order by issue_year ASC");
                while (connectionObject.rs.next())
                {
                    issueYear[i++] = connectionObject.rs.getInt(1);
                }
                
                i = 0;
                connectionObject.rs=connectionObject.st.executeQuery("select distinct language from sat_sandesh_inventory_entry where counter ='"+stallDropDown.getSelectedItem().toString()+"' order by language ASC");
                while (connectionObject.rs.next())
                {
                    language[i++] = connectionObject.rs.getString(1);
                }
                
                connectionObject.closeAll();
                
                
                
                String userName = SamsUtilities.getUserName();
                for(i = 0; i < numItems; ++i){
                    data[i][0] = (i+1);
                    data[i][col.length-1] = userName;
                }
                
                dataEntryTable.setFont(new Font("Serif", Font.PLAIN, 15));
                
                //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                dataEntryTable.getColumnModel().getColumn(0).setMaxWidth(40);
                dataEntryTable.getColumnModel().getColumn(1).setMaxWidth(100);
                dataEntryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                dataEntryTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                dataEntryTable.getColumnModel().getColumn(4).setPreferredWidth(100);
                dataEntryTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                dataEntryTable.getColumnModel().getColumn(6).setPreferredWidth(100);
                
                
                TableColumn languageYearColumn = dataEntryTable.getColumnModel().getColumn(1);
                TableColumn issueYearColumn = dataEntryTable.getColumnModel().getColumn(2);
                TableColumn issueMonthColumn = dataEntryTable.getColumnModel().getColumn(3);
                
                
                JComboBox monthComboBox = new JComboBox(issueMonth);
                JComboBox yearComboBox = new JComboBox(issueYear);
                languageDropDown = new JComboBox(language);
                
                languageYearColumn.setCellEditor(new DefaultCellEditor(languageDropDown));
                issueYearColumn.setCellEditor(new DefaultCellEditor(yearComboBox));
                issueMonthColumn.setCellEditor(new DefaultCellEditor(monthComboBox));
                
                dataEntryTable.setRowHeight(28);
                dataEntryTable.setShowGrid(true);
                dataEntryTable.setGridColor(Color.GRAY);
                dataEntryTable.setShowHorizontalLines(true);
                dataEntryTable.setShowVerticalLines(true);
                dataEntryTable.setColumnSelectionAllowed(true);
                dataEntryTable.setRowSelectionAllowed(true);
                dataEntryTable.setFocusCycleRoot(true);
                dataEntryTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                
                dataEntryTable.getModel().addTableModelListener(this);
                scrollPane =new JScrollPane(dataEntryTable);
                
                Dimension frameSize = addSatSandeshInventoryWindow.getSize();
                scrollPane.setBounds(30,130,frameSize.width - 60, frameSize.height - 230);
                //scrollPane.setSize(frameSize.width - 60, frameSize.height - 480);
                //scrollPane.setLocation(30, 200);
                
                addSatSandeshInventoryWindow.add(scrollPane,"span 5, wrap 20px");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        if(event.getSource() == okButton)
        {
            
            //gather the info from user input into the frame
            String counter = (String)stallDropDown.getSelectedItem();
            String toCounter = (String)toStallDropDown.getSelectedItem();
            String issueMonth = issueMonthtext.getText();
            String issueYear = issueYearText.getText();
            String issuePriceString = issuePriceText.getText();
            String quantity = quantityText.getText();
            
            //String entryDate = entryDateYearText.getText()+"-"+entryDateMonthText.getText()+"-"+entryDateText.getText();
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int eDate= selectedDate.getDate();
            int eMonth=selectedDate.getMonth()+1;
            int eYear=selectedDate.getYear()+1900;
            String entryDate = eYear+"-"+eMonth+"-"+eDate;
            
            
            String issuedBy = SamsUtilities.getUserName();//issuedByNameText.getText();
            String language  = (String)languageDropDown.getSelectedItem();
            String billNumber = (String)billNumberText.getText();
            if(!issueMonth.isEmpty()
                    && !issueYear.isEmpty()
                    && !issuePriceString.isEmpty()
                    //&& !fromNum.isEmpty()
                    //&& !toNum.isEmpty()
                    && !quantity.isEmpty()
                    && !language.isEmpty()
                    && !counter.isEmpty()
                    && !toCounter.isEmpty()
                    && !billNumber.isEmpty()
                    )
            {
                int month = Integer.parseInt(issueMonth);
                int year = Integer.parseInt(issueYear);
                int qty = Integer.parseInt(quantity);
                int bill = Integer.parseInt(billNumber);
                
                if(month < 1 || month > 12 || qty == 0 || year < 1980 || counter.equals(toCounter) || bill < 1)
                {
                    JOptionPane.showMessageDialog(addSatSandeshInventoryWindow, "Please verify the details entered");
                }
                else
                {
                    String query = "insert into sat_sandesh_inventory_entry values ("+month+","+year+","+qty+",'"+entryDate+"','"+issuedBy+"','"+toCounter+"','"+language+"', "+issuePriceString+", "+bill+")";
                    String reduceQuery = "insert into sat_sandesh_inventory_entry values ("+month+","+year+",-"+qty+",'"+entryDate+"','"+issuedBy+"','"+counter+"','"+language+"', "+issuePriceString+", "+bill+")";
                    int option = JOptionPane.showConfirmDialog(addSatSandeshInventoryWindow, "Are you sure ?");
                    //System.out.println("option :: "+option);
                    if(option == 0)
                    {
                        connect updateconnection = new connect();
                        try
                        {
                            System.out.println(query);
                            updateconnection.a = updateconnection.st.executeUpdate(query);
                            
                            System.out.println(reduceQuery);
                            updateconnection.a = updateconnection.st.executeUpdate(reduceQuery);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        updateconnection.closeAll();
                        
                    }
                }
                
                issueMonthtext.setText(""+SamsUtilities.getCurrentMonth());
                issueYearText.setText(""+SamsUtilities.getCurrentYear());
                quantityText.setText("");
                datePicker.getJFormattedTextField().setText("");
                //entryDateYearText.setText("");
                //entryDateMonthText.setText("");
                //entryDateText.setText("");
                //issuedByNameText.setText("");
                languageDropDown.setSelectedItem("");
                stallDropDown.setSelectedIndex(0);
                issuePriceText.setText("");
                toStallDropDown.setSelectedIndex(1);
                
                
                
            }
            else
            {
                JOptionPane.showMessageDialog(addSatSandeshInventoryWindow, "Please fill all the fields");
            }
            
        }
        if(event.getSource() == cancelButton)
        {
            addSatSandeshInventoryWindow.setVisible(false);
            new sams();
            addSatSandeshInventoryWindow.dispose();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        if(column == 3)
        {
            
            {
                
                Object languageObj = (model.getValueAt(row, 1));
                Object monthObj = (model.getValueAt(row, 2));
                Object yearObj = (model.getValueAt(row, 3));
                int month = 0;
                int year = 0;
                String language = "";
                if(languageObj != null )language = (String)(languageObj);
                if(monthObj != null) month = (Integer)(monthObj);
                if(yearObj != null) year = (Integer)(yearObj);
                
                if(language.isEmpty()){
                    JOptionPane.showMessageDialog(addSatSandeshInventoryWindow, "Please check the data", "ERROR", JOptionPane.ERROR_MESSAGE);
                    dataEntryTable.changeSelection(row, 1, false, false);
                    model.setValueAt("", row, 1);
                    //model.setValueAt("", row, 2);
                    model.setValueAt("", row, 3);
                    return;
                }
                
                if(month == 0){
                    JOptionPane.showMessageDialog(addSatSandeshInventoryWindow, "Please check the data", "ERROR", JOptionPane.ERROR_MESSAGE);
                    dataEntryTable.changeSelection(row, 2, false, false);
                    model.setValueAt("", row, 2);
                    model.setValueAt("", row, 3);
                    return;
                }
                
                if(year == 0){
                    JOptionPane.showMessageDialog(addSatSandeshInventoryWindow, "Please check the data", "ERROR", JOptionPane.ERROR_MESSAGE);
                    dataEntryTable.changeSelection(row, 3, false, false);
                    model.setValueAt("", row, 3);
                    return;
                }
                
                connect fetchConnection = new connect();
                try
                {
                    String query = "select issue_price from sat_sandesh_inventory_entry where "
                            + "issue_month="+month+" and issue_year="+year+" "
                            + "and language='"+language+"' and counter='"+stallDropDown.getSelectedItem().toString()+"'";
                    System.out.println(query);
                    fetchConnection.rs = fetchConnection.st.executeQuery(query);
                    if(fetchConnection.rs.next())
                    {
                        int price = fetchConnection.rs.getInt(1);
                        model.setValueAt(price, row, 4);
                    }
                    
                    
                    
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                fetchConnection.closeAll();
                
            }
            
        }
        
    }
    
}
