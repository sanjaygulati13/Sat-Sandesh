
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
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
 * @author sanjay gulati
 */
public class InventorySubIssueDetailsClass implements ActionListener, ItemListener
{
    
    public static void main(String args[])
    {
        InventorySubIssueDetailsClass abc = new InventorySubIssueDetailsClass();
    }
    
    
    /* Global variables */
    JFrame subIssueReceiptBookWindow;
    
    // Primary Details Part
    JLabel seriesLabel, bookNumLabel, fromLabel, toLabel, issuedToStallLabel;
    TextFieldWithLimit fromText, toText, issuedToStallText;
    JComboBox seriesDropDown, bookNumDropDown;
    
    JLabel issuedToLabel, issueDateLabel, descriptionLabel, subgroupLabel, receiptFromLabel, receiptToLabel;
    //JLabel revertBackFromLabel, revertBackToLabel;
    TextFieldWithLimit issuedToText/*, issueDateMonthtext, issueDateYearText, issueDatetext*/;
    TextFieldWithLimit receiptFromText, receiptToText , descriptionText, subgroupText;
    
    //JComboBox revertBackFromDropDown, revertBackToDropDown;
    
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    //Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram"};
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    InventorySubIssueDetailsClass()
    {
        //setting environment for the Frame issueReceiptBookWindow
        subIssueReceiptBookWindow = new JFrame("Re issue Receipt Book");
        subIssueReceiptBookWindow.setLayout(mLayout);
        subIssueReceiptBookWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        subIssueReceiptBookWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        subIssueReceiptBookWindow.setSize(600,400);
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = subIssueReceiptBookWindow.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        subIssueReceiptBookWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            //For Mac
            //Application.getApplication().setDockIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            
            subIssueReceiptBookWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
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
        seriesLabel = new JLabel("<HTML>Series</HTML>");
        bookNumLabel = new JLabel("<HTML>Receipt Book No</HTML>");
        fromLabel = new JLabel("<HTML>From</HTML>");
        toLabel = new JLabel("<HTML>To</HTML>");
        issuedToStallLabel = new JLabel("<HTML>Stall</HTML>");
        //issuedToLabel = new JLabel("<HTML>Stall</HTML>");
        issueDateLabel = new JLabel("<HTML>Issue Date</HTML>");
        
        
        //DropDowns
        //fill the information from the database while initialization
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        bookNumDropDown = new JComboBox();
        
        seriesDropDown.addItemListener(this);
        bookNumDropDown.addItemListener(this);
        
        fromText = new TextFieldWithLimit( 5 , 5 );
        fromText.setEnabled(false);
        
        toText  = new TextFieldWithLimit( 5 , 5 );
        toText.setEnabled(false);
        
        issuedToStallText = new TextFieldWithLimit(32, 32);
        issuedToStallText.setEnabled(false);
        
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        
        //issueDatetext = new TextFieldWithLimit( 2 , 2 );
        //issueDateMonthtext = new TextFieldWithLimit( 2 , 2 );
        //issueDateYearText = new TextFieldWithLimit( 4 , 4 );
        
        //issueDateMonthtext.setText(""+SamsUtilities.getCurrentMonth());
        //issueDateYearText.setText(""+SamsUtilities.getCurrentYear());
        
        descriptionLabel = new JLabel("<HTML>Description</HTML>");
        descriptionText = new TextFieldWithLimit( 32 , 32 );
        
        subgroupLabel = new JLabel("<HTML>Issued to </HTML>");
        subgroupText = new TextFieldWithLimit( 40 , 40 );
        
        //revertBackFromLabel = new JLabel("<HTML>Revert From</HTML>");
        //revertBackFromDropDown = new JComboBox();
        
        //revertBackToLabel = new JLabel("<HTML>Revert To</HTML>");
        //revertBackToDropDown = new JComboBox();
        
        
        receiptFromLabel= new JLabel("<HTML>Receipt From</HTML>");
        receiptFromText = new TextFieldWithLimit( 5 , 5 );
        
        receiptToLabel = new JLabel("<HTML>Receipt To</HTML>");
        receiptToText = new TextFieldWithLimit( 5 , 5 );
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        //adding the gui elements to frame and setting the layout simultaneously
        subIssueReceiptBookWindow.add(seriesLabel);
        subIssueReceiptBookWindow.add(seriesDropDown, "span 2, w 100!");
        subIssueReceiptBookWindow.add(bookNumLabel);
        subIssueReceiptBookWindow.add(bookNumDropDown,"span 3, w :90: , wrap 20px");
        subIssueReceiptBookWindow.add(fromLabel );
        subIssueReceiptBookWindow.add(fromText, "span 2, w 100!");
        subIssueReceiptBookWindow.add(toLabel);
        subIssueReceiptBookWindow.add(toText, "span 3, w :90:, wrap 20px");
        subIssueReceiptBookWindow.add(issuedToStallLabel);
        subIssueReceiptBookWindow.add(issuedToStallText, "span 2, w 120!");
        subIssueReceiptBookWindow.add(issueDateLabel);
        subIssueReceiptBookWindow.add(datePicker,"w 150!, wrap 15px");
        
        //subIssueReceiptBookWindow.add(issueDatetext, "split 3, w 30!");
        //subIssueReceiptBookWindow.add(issueDateMonthtext, " w 30!");
        //subIssueReceiptBookWindow.add(issueDateYearText, "w 50! , wrap 20px");
        
        subIssueReceiptBookWindow.add(new JSeparator(SwingConstants.HORIZONTAL),"span 5,w 400!, wrap 20px");
        subIssueReceiptBookWindow.add(descriptionLabel);
        subIssueReceiptBookWindow.add(descriptionText,"span 2, w 100!");
        subIssueReceiptBookWindow.add(subgroupLabel);
        subIssueReceiptBookWindow.add(subgroupText,"span 2, w 100!, wrap 20px");
        //subIssueReceiptBookWindow.add(revertBackFromLabel);
        //subIssueReceiptBookWindow.add(revertBackFromDropDown,"span 2 , w 100!");
        //subIssueReceiptBookWindow.add(revertBackToLabel);
        //subIssueReceiptBookWindow.add(revertBackToDropDown,"span 2 , w 100!, wrap 20px");
        
        subIssueReceiptBookWindow.add(receiptFromLabel);
        subIssueReceiptBookWindow.add(receiptFromText,"span 2, w 100!");
        subIssueReceiptBookWindow.add(receiptToLabel);
        subIssueReceiptBookWindow.add(receiptToText,"span 2 , w 100!, wrap 20px");
        subIssueReceiptBookWindow.add(okButton, "gapleft 80, w :90:, span 2");
        subIssueReceiptBookWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        
        subIssueReceiptBookWindow.setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            //gather the info from user input into the frame
            
            String seriesName = (String)seriesDropDown.getSelectedItem();
            String bookNumString = (String)(bookNumDropDown.getSelectedItem());
            String fromNum = receiptFromText.getText();
            String toNum = receiptToText.getText();
            String stall = issuedToStallText.getText();
            String issuingToGroup = subgroupText.getText();
            String description = descriptionText.getText();
            
            String origFrom = fromText.getText();
            String origTo = toText.getText();
            
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int date= selectedDate.getDate();
            int month=selectedDate.getMonth()+1;
            int year=selectedDate.getYear()+1900;
            String issueDate = year+"-"+month+"-"+date;
            
            //String issueDate = issueDateYearText.getText()+"-"+issueDateMonthtext.getText()+"-"+issueDatetext.getText();
            
            if(!seriesName.isEmpty()
                    && !bookNumString.isEmpty()
                    && !fromNum.isEmpty()
                    && !toNum.isEmpty()
                    && !stall.isEmpty()
                    && !issueDate.isEmpty()
                    && !issuingToGroup.isEmpty()
                    && !origFrom.isEmpty()
                    && !origTo.isEmpty()
                    )
            {
                
                int bookNum = Integer.parseInt(bookNumString);
                int from = Integer.parseInt(fromNum);
                int to = Integer.parseInt(toNum);
                
                int origFromNum = Integer.parseInt(origFrom);
                int origToNum = Integer.parseInt(origTo);
                
                if( to >= from && (from >= origFromNum && from <= origToNum) && (to <= origToNum))
                {
                    String currDate = SamsUtilities.getCurrentYear()+"-"+SamsUtilities.getCurrentMonth()+"-"+SamsUtilities.getCurrentDate();
                    int option = JOptionPane.showConfirmDialog(subIssueReceiptBookWindow, "Are you sure ?");
                    //System.out.println("option :: "+option);
                    if(option == 0)
                    {
                        connect updateconnection = new connect();
                        
                        //check if any entry exists in the table
                        int entryStart = 0;
                        for(int rcpt = from; rcpt <=to; rcpt++)
                        {
                            String sqlQuery = "select rcpt_num from  sub_issue_details where series_name = '"+seriesName+"' and book_num =  "+bookNum+" and rcpt_num = "+rcpt;
                            
                            try
                            {
                                //System.out.println(sqlQuery);
                                updateconnection.rs = updateconnection.st.executeQuery(sqlQuery);
                                while(updateconnection.rs.next())
                                {
                                    if(entryStart == 0)
                                    {
                                        entryStart = updateconnection.rs.getInt(1);
                                        break;
                                    }
                                }
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                        
                        if(entryStart == 0)
                        {
                            for(int rcpt = from; rcpt <=to; rcpt++)
                            {
                                String sqlQuery = "insert into sub_issue_details values ('"+seriesName+"', "+bookNum+", '"+issuingToGroup+"','"+issueDate+"','"+description+"','"+currDate+"',"+rcpt+",'"+stall+"' )";
                                
                                try
                                {
                                    System.out.println(sqlQuery);
                                    updateconnection.a = updateconnection.st.executeUpdate(sqlQuery);
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                                
                                
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(subIssueReceiptBookWindow, "Entry already exist for receipt from : "+entryStart);
                        
                        updateconnection.closeAll();
                        seriesDropDown.setSelectedItem(1);
                        bookNumDropDown.setSelectedItem(1);
                        receiptFromText.setText("");
                        receiptToText.setText("");
                        issuedToStallText.setText("");
                        subgroupText.setText("");
                        descriptionText.setText("");
                        
                        fromText.setText("");
                        toText.setText("");
                        
                        datePicker.getJFormattedTextField().setText("");
                        //issueDateYearText.setText(""+SamsUtilities.getCurrentYear());
                        //issueDateMonthtext.setText(""+SamsUtilities.getCurrentMonth());
                        //issueDatetext.setText("");
                    }
                    
                }
                else
                    JOptionPane.showMessageDialog(subIssueReceiptBookWindow, "Please check receipt number fields");
                
            }
            else
                JOptionPane.showMessageDialog(subIssueReceiptBookWindow, "Please fill all the fields");
        }
        else if(event.getSource() == cancelButton)
        {
            subIssueReceiptBookWindow.setVisible(false);
            new sams();
            subIssueReceiptBookWindow.dispose();
            
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource() == seriesDropDown)
        {
            IssueReceiptBookClass.fillRecptNumInformation(bookNumDropDown, (String)seriesDropDown.getSelectedItem());
        }
        if(ie.getSource() == bookNumDropDown)
        {
            String rcptNum =  (String)bookNumDropDown.getSelectedItem();
            String seriesName = (String)seriesDropDown.getSelectedItem();
            //if(!rcptNum.isEmpty())
            {
            //int rcptNumInt = Integer.parseInt(rcptNum);
            String sqlQuery = "select start_rcpt_num, end_rcpt_num, issued_to from receipt_book_inventory where series_name = '"+seriesName+"' and book_num= "+rcptNum ;
            //System.out.println(sqlQuery);
            connect fillInfo = new connect();
            try
            {
                fillInfo.rs = fillInfo.st.executeQuery(sqlQuery);
                fillInfo.rs.next();
                int startNum = fillInfo.rs.getInt(1);
                String issued_to_stall = fillInfo.rs.getString(3);
                int endNum = fillInfo.rs.getInt(2);
                
                fromText.setText(""+startNum);
                toText.setText(""+endNum);
                
                receiptFromText.setText(""+startNum);
                receiptToText.setText(""+endNum);
                
                issuedToStallText.setText(issued_to_stall);
            }
            catch(Exception e)
            {
                //TODO
                //I dont know the reason of this exception
                //but this exception is caught every time
                
                //System.out.println("Exception Caught");
                //e.printStackTrace();
                
            }
            fillInfo.closeAll();
        }
            
        }
        
    }
}
