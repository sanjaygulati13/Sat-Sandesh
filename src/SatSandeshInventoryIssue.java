
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
public class SatSandeshInventoryIssue  implements ActionListener, ItemListener, Printable  {

    private static final int NUM_ROWS = 20;
    
    //Data Type declarations
    JFrame satSandeshInventoryIssueWindow;
    JLabel customerNameLabel, languageLabel, pageNumberLabel, amountLabel,issueTypeLabel, issueYearLabel, issueMonthLabel, issuedByLabel, entryDateLabel, stallLabel, quantityLabel, codeLabel;
    TextFieldWithLimit[] quantityText;
    JComboBox[] codeDropDown;
    
    JLabel totalQuantityLabel, totalAmountLabel;
    JTextField totalQuantityText, totalAmountText;
    
    JPanel entryPanel;
    JScrollPane scrollPane;
    
    TextFieldWithLimit /*customerNameText[],*/ /*entryDateText,entryMonthText,entryYearText,*/ pageNumberText, issuedByText[], amountText[];
    JComboBox yearDropDown[], monthDropDown[], stallDropDown, languageDropDown[], issueTypeDropDown[];
    JComboBox customerNameDropDown[];
    JButton okButton, cancelButton, printButton, clearButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    MigLayout pLayout= new MigLayout( "insets 20");    
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram","Other"};
    String [] issueType = {
                            "",
                            "By Hand",
                            "By Hand (Bulk)",
                            "By Post",
                            "By Post (Bulk)",
                            "Cash",
                            "Grievances",
                            "Complimentary",
                            "Binding"
                            };
    
    String [] customerName = {
      "",
      "Member",
      "Non Member"
    };
    
    String language[] = {
                        "",
                        "Hindi",
                        "English",
                        "Urdu",
                        "Punjabi"
    };
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    public SatSandeshInventoryIssue()
    {
        //setting environment for the Frame satSandeshInventoryIssueWindow
        satSandeshInventoryIssueWindow = new JFrame("Issue Sat Sandesh");
        satSandeshInventoryIssueWindow.setLayout(mLayout);
        satSandeshInventoryIssueWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshInventoryIssueWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
                );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshInventoryIssueWindow.setSize((screenSize.width*5)/6,(screenSize.height*9)/10);
        Dimension frameSize = satSandeshInventoryIssueWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 8;
        int y = (screenSize.height - frameSize.height) / 8;
        satSandeshInventoryIssueWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshInventoryIssueWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        
        //Data Type population Start
        
        entryPanel = new JPanel();
        entryPanel.setLayout(pLayout);
        
        //Labels
        stallLabel = new JLabel("<HTML>Stall</HTML>");
        customerNameLabel = new JLabel("<HTML>Customer</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        issueYearLabel = new JLabel("<HTML>Year</HTML>");
        issueMonthLabel = new JLabel("<HTML>Month</HTML>");
        pageNumberLabel = new JLabel("<HTML>Page No.</HTML>");
        quantityLabel = new JLabel("<HTML>Quantity</HTML>");
        issueTypeLabel = new JLabel("<HTML>Issue Type</HTML>");
        amountLabel =  new JLabel("<HTML>Amount</HTML>");
        issuedByLabel = new JLabel("<HTML>Your Name</HTML>");
        entryDateLabel = new JLabel("<HTML>Issue date</HTML>");
        codeLabel = new JLabel("<HTML>D#</HTML>");
        
        //issueTypeLabel = new JLabel[7];
        //quantityText  = new TextFieldWithLimit[7];
        //codeDropDown = new JComboBox[2];
        Object [] codeArray = fillDespCodeInformation();
        
        //customerNameText = new TextFieldWithLimit[NUM_ROWS];
        customerNameDropDown = new JComboBox[NUM_ROWS];
        yearDropDown = new JComboBox[NUM_ROWS];
        monthDropDown = new JComboBox[NUM_ROWS];
        quantityText = new TextFieldWithLimit[NUM_ROWS];
        languageDropDown = new JComboBox[NUM_ROWS];
        issueTypeDropDown =  new JComboBox[NUM_ROWS];
        amountText = new TextFieldWithLimit[NUM_ROWS];
        issuedByText = new TextFieldWithLimit[NUM_ROWS];
        codeDropDown = new JComboBox[NUM_ROWS];
        
        totalQuantityLabel = new JLabel("<HTML>Total Quantity</HTML>");
        totalQuantityText = new JTextField();
        totalQuantityText.setEditable(false);
        
        totalAmountLabel = new JLabel("<HTML>Total Amount</HTML>");
        totalAmountText = new JTextField();        
        totalAmountText.setEditable(false);
        
        //DropDowns
        //fill the information from the database while initialization
        for(int i = 0; i < NUM_ROWS; ++i)
        {
            customerNameDropDown[i] = new JComboBox(customerName);
            languageDropDown[i] = new JComboBox(language);
            yearDropDown[i] = new JComboBox(fillIssueYearInformation());
            monthDropDown[i] = new JComboBox();
            
            quantityText[i] = new TextFieldWithLimit(5, 5);
            quantityText[i].addActionListener(this);
            
            issueTypeDropDown[i] =  new JComboBox(issueType);
            amountText[i] = new TextFieldWithLimit(5, 5);
            amountText[i].setText("0");          
            amountText[i].addActionListener(this);
            
            issuedByText[i] = new TextFieldWithLimit(32, 32);
            codeDropDown[i] = new JComboBox(codeArray);
            
            yearDropDown[i].addItemListener(this);
            issueTypeDropDown[i].addItemListener(this);
        }
        
        
        stallDropDown = new JComboBox(stalls);
        
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        //TextFields
        //entryDateText = new TextFieldWithLimit( 2 , 2 );
        //entryMonthText = new TextFieldWithLimit( 2 , 2 );
        //entryYearText = new TextFieldWithLimit( 4 , 4 );
        pageNumberText = new TextFieldWithLimit( 4 , 4 );
        
        //entryMonthText.setText(""+SamsUtilities.getCurrentMonth());
        //entryYearText.setText(""+SamsUtilities.getCurrentYear());
        
        //quantityText  = new TextFieldWithLimit( 5 , 5 );
        
        
        
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Back");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        
        clearButton = new JButton("Reset");
        clearButton.setMnemonic('r');
        clearButton.addActionListener(this);
        
        
        printButton = new JButton("Print");
        printButton.setMnemonic('p');
        printButton.addActionListener(this);
        
        //adding the gui elements to frame and setting the layout simultaneously
        
        satSandeshInventoryIssueWindow.add(stallLabel,"span 1, w 100, align right");
        satSandeshInventoryIssueWindow.add(stallDropDown, "span 3, w 150!");
        satSandeshInventoryIssueWindow.add(pageNumberLabel,"span 2, align right");
        satSandeshInventoryIssueWindow.add(pageNumberText,"span 1");
        satSandeshInventoryIssueWindow.add(entryDateLabel,"span 2, align right");
        satSandeshInventoryIssueWindow.add(datePicker,"w 150!, wrap 10px");
        //satSandeshInventoryIssueWindow.add(entryDateText, "split 5, w 30!");
        //satSandeshInventoryIssueWindow.add(entryMonthText,"span 1");
        //satSandeshInventoryIssueWindow.add(entryYearText, "span 1, wrap 4px");
        
        /*
        satSandeshInventoryIssueWindow.add(customerNameLabel," span 1, w 100");
        satSandeshInventoryIssueWindow.add(languageLabel," span 1, gapleft 10");
        satSandeshInventoryIssueWindow.add(issueYearLabel," span 1");
        satSandeshInventoryIssueWindow.add(issueMonthLabel," span 1");
        satSandeshInventoryIssueWindow.add(quantityLabel," span 1");
        satSandeshInventoryIssueWindow.add(issueTypeLabel," span 1");
        satSandeshInventoryIssueWindow.add(amountLabel," span 1");
        satSandeshInventoryIssueWindow.add(issuedByLabel," span 1");
        satSandeshInventoryIssueWindow.add(codeLabel,"span 1, wrap 2px");
        */
        entryPanel.add(customerNameLabel," span 1, w 100");
        entryPanel.add(languageLabel," span 1, gapleft 10");
        entryPanel.add(issueYearLabel," span 1");
        entryPanel.add(issueMonthLabel," span 1");
        entryPanel.add(quantityLabel," span 1");
        entryPanel.add(issueTypeLabel," span 1");
        entryPanel.add(amountLabel," span 1");
        entryPanel.add(issuedByLabel," span 1");
        entryPanel.add(codeLabel,"span 1, wrap 2px");
        
        for(int __x = 0; __x < NUM_ROWS; __x++)
        {
            /*
            satSandeshInventoryIssueWindow.add(customerNameText[__x], "span 1, w 100!");
            satSandeshInventoryIssueWindow.add(languageDropDown[__x],"span 1, w :100:");
            satSandeshInventoryIssueWindow.add(yearDropDown[__x], "span 1, w 100!");
            satSandeshInventoryIssueWindow.add(monthDropDown[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(quantityText[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(issueTypeDropDown[__x],"span 1");
            satSandeshInventoryIssueWindow.add(amountText[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(issuedByText[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(codeDropDown[__x],"span 1, w :90:, wrap 2px");
            */
            entryPanel.add(customerNameDropDown[__x], "span 1, w 130!");
            entryPanel.add(languageDropDown[__x],"span 1, w :100:");
            entryPanel.add(yearDropDown[__x], "span 1, w 100!");
            entryPanel.add(monthDropDown[__x],"span 1, w :90:");
            entryPanel.add(quantityText[__x],"span 1, w :90:");
            entryPanel.add(issueTypeDropDown[__x],"span 1");
            entryPanel.add(amountText[__x],"span 1, w :90:");
            entryPanel.add(issuedByText[__x],"span 1, w :90:");
            entryPanel.add(codeDropDown[__x],"span 1, w :90:, wrap 2px");
            
        }
        
        scrollPane = new JScrollPane(entryPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        satSandeshInventoryIssueWindow.add(scrollPane,"span, wrap");
        
        satSandeshInventoryIssueWindow.add(totalQuantityLabel,"span 7, w :90:, align right");
        satSandeshInventoryIssueWindow.add(totalQuantityText,"span 1, w :90:");
        satSandeshInventoryIssueWindow.add(totalAmountLabel,"span 3, align right");
        satSandeshInventoryIssueWindow.add(totalAmountText,"span 1, w :90:, wrap 2px");
        
        //satSandeshInventoryIssueWindow.add(quantityLabel );
        //satSandeshInventoryIssueWindow.add(quantityText, "span 2, w 100!");
        satSandeshInventoryIssueWindow.add(okButton, "w :90:, span 5, align right");
        satSandeshInventoryIssueWindow.add(cancelButton, " w :90: ,span 3, align center");
        satSandeshInventoryIssueWindow.add(clearButton, " w :90: ,span 3, align center");
        satSandeshInventoryIssueWindow.add(printButton, "w :90: ,span 3, align left");
        
        //populate information from datatbase
        //fillSeriesNameInformation();
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        satSandeshInventoryIssueWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        
        for(int i = 0; i < NUM_ROWS; i++)
        {
            if(event.getSource() == amountText[i])
            {
                float total = 0.0f;
                for(int j = 0; j < NUM_ROWS; j++)
                {
                    String amtString = amountText[j].getText(); 
                    float amount = 0.0f;
                    if(amtString.isEmpty() == false)
                        amount = Float.parseFloat(amtString);
                    total += amount;
                    
                }
                totalAmountText.setText(""+total);
            }
            if(event.getSource() == quantityText[i])
            {
                int total = 0;
                for(int j = 0; j < NUM_ROWS; j++)
                {
                    String qtyString = quantityText[j].getText(); 
                    int quantity = 0;
                    if(qtyString.isEmpty() == false)
                        quantity = Integer.parseInt(qtyString);
                    total += quantity;
                    
                }
                totalQuantityText.setText(""+total);
            }
        }
        if(event.getSource() == okButton)
        {
            int status = 1;
            Vector<String> queryVec = new Vector<String>();
            //Font font = new Font();
            
            //gather the info from user input into the frame
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int eDate= selectedDate.getDate();
            int eMonth=selectedDate.getMonth()+1;
            int eYear=selectedDate.getYear()+1900;
            String issueDate = eYear+"-"+eMonth+"-"+eDate;
            //String issueDate = entryYearText.getText()+"-"+entryMonthText.getText()+"-"+entryDateText.getText();
            for(int i = 0; i < NUM_ROWS; i++)
            {
                String customerName = (String)customerNameDropDown[i].getSelectedItem();
                if(!customerName.isEmpty())
                {
                    String issueYear = (String)yearDropDown[i].getSelectedItem();
                    String issueMonth = (String)(monthDropDown[i].getSelectedItem());
                    //String fromNum = fromText.getText();
                    //String toNum = toText.getText();
                    String counter = (String)stallDropDown.getSelectedItem();
                    String pageNumber = pageNumberText.getText();
                    
                    String issuedBy = issuedByText[i].getText();
                    if(
                            !issueYear.isEmpty()
                            && !issueMonth.isEmpty()
                            && !counter.isEmpty()
                            && !issueDate.isEmpty()
                            && !issuedBy.isEmpty()
                            && !pageNumber.isEmpty()
                            )
                    {
                        
                        int year = Integer.parseInt(issueYear);
                        int month = Integer.parseInt(issueMonth);
                        
                        String qty = quantityText[i].getText();
                        if(qty.isEmpty())
                        {
                            status &= 0;
                            continue;
                        }
                        
                        String amt = amountText[i].getText();
                        if(amt.isEmpty())
                        {
                            status &= 0;
                            continue;
                        }
                        
                        int amount = Integer.parseInt(amt);
                        
                        int quantity = Integer.parseInt(qty);
                        if(quantity < 1 || month < 1 || month > 12 || year < 1980 || amount < 0)
                        {
                            JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please verify the details entered");
                            status &= 0;
                            continue;
                        }
                        
                        String despCode = "";
                        int dno = 0;
                        if(despCode.contains("Bulk"))
                        {
                            despCode = codeDropDown[i].getSelectedItem().toString();
                            
                            if(despCode.isEmpty())
                            {
                                JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please verify the D#");
                                status &= 0;
                                continue;
                            }
                            
                            dno = Integer.parseInt(despCode);
                            if(dno < 1)
                            {
                                JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please verify the D#");
                                status &= 0;
                                continue;
                            }
                        }
                        
                        
                        String language  = (String)languageDropDown[i].getSelectedItem();
                        String issue_type  = (String)issueTypeDropDown[i].getSelectedItem();
                        if(language.isEmpty() || issue_type.isEmpty())
                        {
                            status &= 0;
                            continue;
                        }
                        
                        if(issue_type.equals("Binding"))
                        {
                            for(int mnth_num = 1 ; mnth_num < 13; mnth_num++)
                            {
                                if(mnth_num == 2 || mnth_num == 8) continue;
                                String sqlQuery = "insert into sat_sandesh_inventory_issue values ('"+ customerName +"',"+mnth_num+","+year+",-"+ quantity +",'"+language+"','"+issue_type+"',"+amount+","+dno+",'"+counter+"','"+issueDate+"','"+issuedBy+"',"+pageNumber+")";
                                queryVec.addElement(sqlQuery);
                            }
                        }
                        else
                        {
                            String sqlQuery = "insert into sat_sandesh_inventory_issue values ('"+ customerName +"',"+month+","+year+","+ quantity +",'"+language+"','"+issue_type+"',"+amount+","+dno+",'"+counter+"','"+issueDate+"','"+issuedBy+"',"+pageNumber+")";
                            queryVec.addElement(sqlQuery);
                        }
                    }
                    else
                    {
                        status &= 0;
                        JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please fill all the fields");
                    }
                }
            }
            System.out.println("Status : "+status);
            if(status == 1)
            {
                int option = JOptionPane.showConfirmDialog(satSandeshInventoryIssueWindow, "Are you sure ?");
                if(option == 0)
                {
                    for(int i = 0; i < queryVec.size(); i++)
                    {
                        //int option = JOptionPane.showConfirmDialog(satSandeshInventoryIssueWindow, "Are you sure ?");
                        //System.out.println("option :: "+option);
                        //if(option == 0)
                        String sqlQuery = queryVec.elementAt(i);
                        {
                            connect updateconnection = new connect();
                            try
                            {
                                //System.out.println(sqlQuery);
                                updateconnection.a = updateconnection.st.executeUpdate(sqlQuery);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            updateconnection.closeAll();
                            
                            customerNameDropDown[i].setSelectedIndex(0);
                            amountText[i].setText("");
                            languageDropDown[i].setSelectedItem("");
                            issueTypeDropDown[i].setSelectedItem("");
                            quantityText[i].setText("");
                            yearDropDown[i].setSelectedItem("");
                            issuedByText[i].setText("");
                            monthDropDown[i].setSelectedItem("");
                            codeDropDown[i].setSelectedItem("");
                            codeDropDown[i].setSelectedItem("");
                            pageNumberText.setText("");
                        }
                    }
                    
                }
                
                //else
                //    JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please fill all the fields");
                //}
                //}
                datePicker.getJFormattedTextField().setText("");
                //entryDateText.setText("");
                //entryMonthText.setText(""+SamsUtilities.getCurrentMonth());
                //entryYearText.setText(""+SamsUtilities.getCurrentYear());
                totalQuantityText.setText("");
                totalAmountText.setText("");
            }
        }
        else if(event.getSource() == clearButton)
        {
            for(int i = 0; i < NUM_ROWS; i++)
            {
                customerNameDropDown[i].setSelectedIndex(0);
                amountText[i].setText("");
                languageDropDown[i].setSelectedItem("");
                issueTypeDropDown[i].setSelectedItem("");
                quantityText[i].setText("");
                yearDropDown[i].setSelectedItem("");
                issuedByText[i].setText("");
                monthDropDown[i].setSelectedItem("");
                codeDropDown[i].setSelectedItem("");
                codeDropDown[i].setSelectedItem("");
                pageNumberText.setText("");
            }
            datePicker.getJFormattedTextField().setText("");
            //entryDateText.setText("");
            //entryMonthText.setText(""+SamsUtilities.getCurrentMonth());
            //entryYearText.setText(""+SamsUtilities.getCurrentYear());
            totalQuantityText.setText("");
            totalAmountText.setText("");
        }
        else if(event.getSource() == cancelButton)
        {
            satSandeshInventoryIssueWindow.setVisible(false);
            new sams();
            satSandeshInventoryIssueWindow.dispose();
            
        }
        
        else if(event.getSource() == printButton)
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            
            int res = JOptionPane.showConfirmDialog(null,"You want to configure your print ","** PRINTING **", JOptionPane.YES_NO_OPTION);
            if( res == JOptionPane.YES_OPTION ) {
                //if (res == JOptionPane.YES_OPTION) (
                //	PageFormat format = job.pageDialog(job.defaultPage());
                PageFormat format = job.pageDialog (job.defaultPage ());
            } //)
            if( res == JOptionPane.NO_OPTION ) {
                PageFormat format =new PageFormat();
                format.setOrientation(PageFormat.LANDSCAPE);
            }
            
            
            boolean ok = job.printDialog();
            if (ok)
            {
                try
                {
                    job.print();
                }
                catch (PrinterException ex)
                {
                    /* The job did not successfully complete */
                }
            }
            
        }
        
        
    }
    
    public void itemStateChanged(ItemEvent ie)
    {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            if(ie.getSource() == yearDropDown[i])
            {
                //System.out.println("Evenbt enabled");
                fillMonthInformation(monthDropDown[i], (String)yearDropDown[i].getSelectedItem(),(String)languageDropDown[i].getSelectedItem());
                issuedByText[i].setText(SamsUtilities.getUserName());
            }
            //System.out.println(ie.getItem());
            if(ie.getSource() == issueTypeDropDown[i])
            {
                String selectedObject = (String)issueTypeDropDown[i].getSelectedItem();
                //System.out.println(selectedObject);
                if( selectedObject.equals("By Post")
                        || selectedObject.equals("By Post(Bulk)") 
                        || selectedObject.equals("Grievances") 
                        || selectedObject.equals("Complimentary"))
                {
                    amountText[i].setText("0");
                    amountText[i].setEditable(false);
                }
                else if (selectedObject.equals("Cash") )
                {
                    connect getPriceConnection = new connect();
                    try
                    {
                        String year  = (String)yearDropDown[i].getSelectedItem();
                        String month  = (String)monthDropDown[i].getSelectedItem();
                        String language = (String)languageDropDown[i].getSelectedItem();
                        if(!year.isEmpty() && !month.isEmpty() && !language.isEmpty())
                        {
                            String query = "select issue_price from sat_sandesh_inventory_entry where issue_year = "+year+" and issue_month = "+month+" and language = '"+language+"'";
                            getPriceConnection.rs = getPriceConnection.st.executeQuery(query);
                            getPriceConnection.rs.next();
                            int issuePriceValue = getPriceConnection.rs.getInt(1);
                            int qty = Integer.parseInt((String)quantityText[i].getText());
                            int totalAmount = issuePriceValue*qty;
                            amountText[i].setText(""+totalAmount);
                            //System.out.println(ArrayCount+1);
                        }
                        getPriceConnection.closeAll();
                        
                        amountText[i].setEditable(true);
                    }
                    catch(Exception e)
                    {
                                
                    }
                }
                else if (selectedObject.equals("Binding") )
                {
                    connect getPriceConnection = new connect();
                    try
                    {
                        String year  = (String)yearDropDown[i].getSelectedItem();
                        String month  = (String)monthDropDown[i].getSelectedItem();
                        String language = (String)languageDropDown[i].getSelectedItem();
                        if(!year.isEmpty() && !month.isEmpty() && !language.isEmpty())
                        {
                            //String query = "select amount from sat_sandesh_inventory_entry where issue_year = "+year+" and issue_type = 'binding' and language = '"+language+"'";
                            String query = "select distinct (amount) from sat_sandesh_inventory_issue where issue_year = "+year+" and issue_type = 'binding' and language = '"+language+"' and issue_month = "+ month;
                            getPriceConnection.rs = getPriceConnection.st.executeQuery(query);
                            getPriceConnection.rs.next();
                            int issuePriceValue = getPriceConnection.rs.getInt(1);
                            int qty = Integer.parseInt((String)quantityText[i].getText());
                            int totalAmount = issuePriceValue*qty;
                            amountText[i].setText(""+totalAmount);
                            //System.out.println(ArrayCount+1);
                        }
                        getPriceConnection.closeAll();
                        
                        amountText[i].setEditable(true);
                    }
                    catch(Exception e)
                    {
                                
                    }
                }
                else
                {
                    amountText[i].setText("0");
                    amountText[i].setEditable(true);
                }
            }
            
                
        }
    }
    
    public static void main(String args[])
    {
        new SatSandeshInventoryIssue();
    }
    
    static public Object[] fillIssueYearInformation()
    {
        connect fillSerieConnection = new connect();
        Object[] seriesNameArray = null;
        try
        {
            String query = "select distinct issue_year from sat_sandesh_inventory_entry order by issue_year DESC";
            String countQuery = "select count(distinct issue_year) from sat_sandesh_inventory_entry";
            
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(countQuery);
            fillSerieConnection.rs.next();
            int ArrayCount = fillSerieConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            seriesNameArray = new Object[ArrayCount + 1];
            seriesNameArray[0] = "";
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillSerieConnection.rs.next()) {
                seriesNameArray[i] = fillSerieConnection.rs.getString(1);
                i++;
            }
            
            fillSerieConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillSerieConnection.closeAll();
        }
        return seriesNameArray;
    }
    
    
    static public Object[] fillDespCodeInformation()
    {
        connect fillSerieConnection = new connect();
        Object[] despCodeArray = null;
        try
        {
            String query = "select dno from despcode;";
            String countQuery = "select count(dno) from despcode";
            
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(countQuery);
            fillSerieConnection.rs.next();
            int ArrayCount = fillSerieConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            despCodeArray = new Object[ArrayCount + 1];
            despCodeArray[0] = "";
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillSerieConnection.rs.next()) {
                despCodeArray[i] = fillSerieConnection.rs.getString(1);
                i++;
            }
            
            fillSerieConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillSerieConnection.closeAll();
        }
        return despCodeArray;
    }
    
    
    
    public static void fillMonthInformation(JComboBox monthDropDown, String issue_year, String language)
    {
        connect fillRcptNumConnection = new connect();
        //Object[] bookNumArray = null;
        try
        {
            String query = "select distinct issue_month from sat_sandesh_inventory_entry where issue_year = '"+issue_year+"' and language = '"+language+"' order by issue_month";
            String countQuery = "select count(distinct issue_month) from sat_sandesh_inventory_entry where issue_year = '"+issue_year+"' and language = '"+language+"'";
            
            fillRcptNumConnection.rs = fillRcptNumConnection.st.executeQuery(countQuery);
            fillRcptNumConnection.rs.next();
            int ArrayCount = fillRcptNumConnection.rs.getInt(1);
            //System.out.println(query);
            //bookNumArray = new Object[ArrayCount + 1];
            //bookNumArray[0] = "";
            fillRcptNumConnection.rs = fillRcptNumConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            monthDropDown.removeAllItems();
            while (fillRcptNumConnection.rs.next()) {
                //bookNumArray[i] = fillRcptNumConnection.rs.getString(1);
                //System.out.println("Adding Item " + fillRcptNumConnection.rs.getString(1));
                monthDropDown.addItem(fillRcptNumConnection.rs.getString(1));
                //i++;
            }
            //System.out.println(monthDropDown.getSelectedObjects().length);
            
            fillRcptNumConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillRcptNumConnection.closeAll();
        }
        //return bookNumArray;
    }

public int print(Graphics graphics, PageFormat pf, int page) throws
                                                        PrinterException {

	pf.setOrientation(PageFormat.LANDSCAPE);

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
         
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
		
        //System.out.println(pf.getImageableWidth()/satSandeshInventoryIssueWindow.getWidth()+" "+pf.getImageableHeight()/satSandeshInventoryIssueWindow.getHeight());
		
        g2d.scale(pf.getImageableWidth()/satSandeshInventoryIssueWindow.getWidth(), pf.getImageableHeight()/satSandeshInventoryIssueWindow.getHeight());
		
        /* Now print the window and its visible contents */
        satSandeshInventoryIssueWindow.printAll(g2d);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
}
