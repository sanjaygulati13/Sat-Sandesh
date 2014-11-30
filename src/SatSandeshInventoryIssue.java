
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjay
 */
public class SatSandeshInventoryIssue  implements ActionListener, ItemListener  {

    //Data Type declarations
    JFrame satSandeshInventoryIssueWindow;
    JLabel customerNameLabel, languageLabel, amountLabel,issueTypeLabel, issueYearLabel, issueMonthLabel, issuedByLabel, entryDateLabel, stallLabel, quantityLabel, codeLabel;
    TextFieldWithLimit[] quantityText;
    JComboBox[] codeDropDown;
    
    TextFieldWithLimit customerNameText[], entryDateText,entryMonthText,entryYearText, issuedByText[], amountText[];
    JComboBox yearDropDown[], monthDropDown[], stallDropDown, languageDropDown[], issueTypeDropDown[];
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram","Other"};
    String [] issueType = {
                            "",
                            "By Hand",
                            "By Hand (Bulk)",
                            "By Post",
                            "By Post (Bulk)",
                            "Cash",
                            "Grievances",
                            "Complimentary"
                            };
    
    String language[] = {
                        "",
                        "Hindi",
                        "English",
                        "Urdu",
                        "Punjabi"
    };
    
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
        satSandeshInventoryIssueWindow.setSize((screenSize.width*3)/4,(screenSize.height*6)/7);
        Dimension frameSize = satSandeshInventoryIssueWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 8;
        int y = (screenSize.height - frameSize.height) / 14;
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
        //Labels
        stallLabel = new JLabel("<HTML>Stall</HTML>");
        customerNameLabel = new JLabel("<HTML>Customer</HTML>");
        issueYearLabel = new JLabel("<HTML>Year</HTML>");
        issueMonthLabel = new JLabel("<HTML>Month</HTML>");
        quantityLabel = new JLabel("<HTML>Quantity</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        issueTypeLabel = new JLabel("<HTML>Issue Type</HTML>");
        amountLabel =  new JLabel("<HTML>Amount</HTML>");
        issuedByLabel = new JLabel("<HTML>Your Name</HTML>");
        entryDateLabel = new JLabel("<HTML>Issue date</HTML>");
        codeLabel = new JLabel("<HTML>D#</HTML>");
        
        //issueTypeLabel = new JLabel[7];
        //quantityText  = new TextFieldWithLimit[7];
        //codeDropDown = new JComboBox[2];
        Object [] codeArray = fillDespCodeInformation();
        
        customerNameText = new TextFieldWithLimit[15];
        yearDropDown = new JComboBox[15];
        monthDropDown = new JComboBox[15];
        quantityText = new TextFieldWithLimit[15];
        languageDropDown = new JComboBox[15];
        issueTypeDropDown =  new JComboBox[15];
        amountText = new TextFieldWithLimit[15];
        issuedByText = new TextFieldWithLimit[15];
        codeDropDown = new JComboBox[15];
        
        //DropDowns
        //fill the information from the database while initialization
        for(int i = 0; i < 15; ++i)
        {
            customerNameText[i] = new TextFieldWithLimit(32,32);
            yearDropDown[i] = new JComboBox(fillIssueYearInformation());
            monthDropDown[i] = new JComboBox();
            quantityText[i] = new TextFieldWithLimit(5, 5);
            languageDropDown[i] = new JComboBox(language);
            issueTypeDropDown[i] =  new JComboBox(issueType);
            amountText[i] = new TextFieldWithLimit(5, 5);
            issuedByText[i] = new TextFieldWithLimit(32, 32);
            codeDropDown[i] = new JComboBox(codeArray);
            
            yearDropDown[i].addItemListener(this);
        }
        
        
        stallDropDown = new JComboBox(stalls);
        
        
        //TextFields
        entryDateText = new TextFieldWithLimit( 2 , 2 );
        entryMonthText = new TextFieldWithLimit( 2 , 2 );
        entryYearText = new TextFieldWithLimit( 4 , 4 );
        
        //quantityText  = new TextFieldWithLimit( 5 , 5 );
        
        
        
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        
        
        //adding the gui elements to frame and setting the layout simultaneously
        
        satSandeshInventoryIssueWindow.add(stallLabel);
        satSandeshInventoryIssueWindow.add(stallDropDown, "span 3, w 150!");
        satSandeshInventoryIssueWindow.add(entryDateLabel);
        satSandeshInventoryIssueWindow.add(entryDateText, "split 5, w 30!");
        satSandeshInventoryIssueWindow.add(entryMonthText, " w 30!");
        satSandeshInventoryIssueWindow.add(entryYearText, "w 60! , wrap 10px");
        
        satSandeshInventoryIssueWindow.add(customerNameLabel," span 1");
        satSandeshInventoryIssueWindow.add(issueYearLabel," span 1");
        satSandeshInventoryIssueWindow.add(issueMonthLabel," span 1");
        satSandeshInventoryIssueWindow.add(quantityLabel," span 1");
        satSandeshInventoryIssueWindow.add(languageLabel," span 1");
        satSandeshInventoryIssueWindow.add(issueTypeLabel," span 1");
        satSandeshInventoryIssueWindow.add(amountLabel," span 1");
        satSandeshInventoryIssueWindow.add(issuedByLabel," span 1");
        satSandeshInventoryIssueWindow.add(codeLabel,"span 1, wrap 5px");
        
        
        for(int __x = 0; __x < 15; __x++)
        {
            satSandeshInventoryIssueWindow.add(customerNameText[__x], "span 1, w 100!");
            satSandeshInventoryIssueWindow.add(yearDropDown[__x], "span 1, w 100!");
            satSandeshInventoryIssueWindow.add(monthDropDown[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(quantityText[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(languageDropDown[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(issueTypeDropDown[__x],"span 1");
            satSandeshInventoryIssueWindow.add(amountText[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(issuedByText[__x],"span 1, w :90:");
            satSandeshInventoryIssueWindow.add(codeDropDown[__x],"span 1, w :90:, wrap 5px");
        }
        
        
        
        //satSandeshInventoryIssueWindow.add(quantityLabel );
        //satSandeshInventoryIssueWindow.add(quantityText, "span 2, w 100!");
        satSandeshInventoryIssueWindow.add(okButton, "gapleft 80, w :90:, span 2");
        satSandeshInventoryIssueWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        
        //populate information from datatbase
        //fillSeriesNameInformation();
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        satSandeshInventoryIssueWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            //gather the info from user input into the frame
            String issueDate = entryYearText.getText()+"-"+entryMonthText.getText()+"-"+entryDateText.getText();
            for(int i = 0; i < 15; i++)
            {
                String customerName = (String)customerNameText[i].getText();
                if(!customerName.isEmpty())
                {
                    String issueYear = (String)yearDropDown[i].getSelectedItem();
                    String issueMonth = (String)(monthDropDown[i].getSelectedItem());
                    //String fromNum = fromText.getText();
                    //String toNum = toText.getText();
                    String counter = (String)stallDropDown.getSelectedItem();
                    
                    String issuedBy = issuedByText[i].getText();
                    if(
                            !issueYear.isEmpty()
                            && !issueMonth.isEmpty()
                            && !counter.isEmpty()
                            && !issueDate.isEmpty()
                            && !issuedBy.isEmpty()
                            )
                    {
                        
                        int year = Integer.parseInt(issueYear);
                        int month = Integer.parseInt(issueMonth);
                        
                        String qty = quantityText[i].getText();
                        if(qty.isEmpty())
                            continue;
                        
                        String amt = amountText[i].getText();
                        if(amt.isEmpty())
                            continue;
                        
                        int amount = Integer.parseInt(amt);
                        
                        int quantity = Integer.parseInt(qty);
                        if(quantity < 1 || month < 1 || month > 12 || year < 1980 || amount < 0)
                        {
                            JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please verify the details entered");
                        }
                        
                        String despCode = "";
                        int dno = 0;
                        if(despCode.contains("Bulk"))
                        {
                            despCode = codeDropDown[i].getSelectedItem().toString();
                            
                            if(despCode.isEmpty())
                            {
                                JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please verify the D#");
                                continue;
                            }
                            
                            dno = Integer.parseInt(despCode);
                            if(dno < 1)
                            {
                                JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please verify the D#");
                                continue;
                            }
                        }
                        
                        
                        String language  = (String)languageDropDown[i].getSelectedItem();
                        String issue_type  = (String)issueTypeDropDown[i].getSelectedItem();
                        if(language.isEmpty() || issue_type.isEmpty())
                            continue;
                        
                        String sqlQuery = "insert into sat_sandesh_inventory_issue values ('"+ customerName +"',"+month+","+year+","+ quantity +",'"+language+"','"+issue_type+"',"+amount+","+dno+",'"+counter+"','"+issueDate+"','"+issuedBy+"')";
                        int option = JOptionPane.showConfirmDialog(satSandeshInventoryIssueWindow, "Are you sure ?");
                        System.out.println("option :: "+option);
                        if(option == 0)
                        {
                            connect updateconnection = new connect();
                            try
                            {
                                System.out.println(sqlQuery);
                                updateconnection.a = updateconnection.st.executeUpdate(sqlQuery);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            updateconnection.closeAll();
                            
                            customerNameText[i].setText("");
                            amountText[i].setText("");
                            languageDropDown[i].setSelectedItem("");
                            issueTypeDropDown[i].setSelectedItem("");
                            issuedByText[i].setText("");
                            quantityText[i].setText("");
                            yearDropDown[i].setSelectedItem("");
                            monthDropDown[i].setSelectedItem("");
                            codeDropDown[i].setSelectedItem("");
                            codeDropDown[i].setSelectedItem("");
                        }
                        
                        
                    }
                    
                    else
                        JOptionPane.showMessageDialog(satSandeshInventoryIssueWindow, "Please fill all the fields");
                }
            }
            entryDateText.setText("");
            entryMonthText.setText("");
            entryYearText.setText("");
        }
        else if(event.getSource() == cancelButton)
        {
            satSandeshInventoryIssueWindow.setVisible(false);
            new sams();
            satSandeshInventoryIssueWindow.dispose();
            
        }
        
    }
    
    public void itemStateChanged(ItemEvent ie)
    {
        for(int i = 0; i < 15; i++)
        {
            if(ie.getSource() == yearDropDown[i])
            {
                fillMonthInformation(monthDropDown[i], (String)yearDropDown[i].getSelectedItem());
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
            String query = "select distinct issue_year from sat_sandesh_inventory_entry";
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
    
    
    
    public static void fillMonthInformation(JComboBox monthDropDown, String issue_year)
    {
        connect fillRcptNumConnection = new connect();
        //Object[] bookNumArray = null;
        try
        {
            String query = "select distinct issue_month from sat_sandesh_inventory_entry where issue_year = '"+issue_year+"'";
            String countQuery = "select count(distinct issue_month) from sat_sandesh_inventory_entry where issue_year = '"+issue_year+"'";
            
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
    
}
