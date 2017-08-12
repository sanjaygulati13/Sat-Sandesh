
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class InventoryRevertIssueDetails implements ActionListener, ItemListener
{
    
    public static void main(String args[])
    {
        InventoryRevertIssueDetails abc = new InventoryRevertIssueDetails();
    }
    
    
    /* Global variables */
    JFrame subIssueReceiptBookWindow;
    
    // Primary Details Part
    JLabel seriesLabel, bookNumLabel, fromLabel, toLabel, issuedToStallLabel;
    TextFieldWithLimit fromText, toText, issuedToStallText;
    JComboBox seriesDropDown, bookNumDropDown;
    
    JLabel issuedToLabel, issueDateLabel, descriptionLabel, receiptFromLabel, receiptToLabel;
    JLabel revertBackFromLabel, revertBackToLabel;
    TextFieldWithLimit issuedToText/*, issueDateMonthtext, issueDateYearText, issueDatetext*/;
    TextFieldWithLimit receiptFromText, receiptToText , descriptionText;
    
    JComboBox revertBackFromDropDown, revertBackToDropDown;
    
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram"};
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    InventoryRevertIssueDetails()
    {
        //setting environment for the Frame issueReceiptBookWindow
        subIssueReceiptBookWindow = new JFrame("Revert Receipt Book");
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
        issuedToStallLabel = new JLabel("<HTML>Issued To Stall</HTML>");
        issuedToLabel = new JLabel("<HTML>Issued To</HTML>");
        
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
        
        
        issuedToLabel = new JLabel("<HTML>Issued To</HTML>");
        issueDateLabel = new JLabel("<HTML>Issue Date</HTML>");
        
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
        
        revertBackFromLabel = new JLabel("<HTML>Revert From</HTML>");
        revertBackFromDropDown = new JComboBox();
        revertBackFromDropDown.addItemListener(this);
                
        revertBackToLabel = new JLabel("<HTML>Revert To</HTML>");
        revertBackToDropDown = new JComboBox(stalls);
        
                
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
        subIssueReceiptBookWindow.add(issuedToStallText, "span 2, w 130!");
        subIssueReceiptBookWindow.add(issueDateLabel);
        subIssueReceiptBookWindow.add(datePicker,"w 150!, wrap 15px");
        
        //subIssueReceiptBookWindow.add(issueDatetext, "split 3, w 30!");
        //subIssueReceiptBookWindow.add(issueDateMonthtext, " w 30!");
        //subIssueReceiptBookWindow.add(issueDateYearText, "w 50! , wrap 20px");
        
        subIssueReceiptBookWindow.add(new JSeparator(SwingConstants.HORIZONTAL),"span 5,w 400!, wrap 20px");
        //subIssueReceiptBookWindow.add(descriptionLabel);
        //subIssueReceiptBookWindow.add(descriptionText,"span 2, w 100!, wrap 20px");
        subIssueReceiptBookWindow.add(revertBackFromLabel);
        subIssueReceiptBookWindow.add(revertBackFromDropDown,"span 2 , w 130!");
        subIssueReceiptBookWindow.add(revertBackToLabel);
        subIssueReceiptBookWindow.add(revertBackToDropDown,"span 2 , w 140!, wrap 20px");
        
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
            String issuedToGroup = (String)revertBackFromDropDown.getSelectedItem();
            
            if(!seriesName.isEmpty() 
                    && !bookNumString.isEmpty() 
                    && !fromNum.isEmpty() 
                    && !toNum.isEmpty()
                    && !issuedToGroup.isEmpty()
                    )
            {
            
                int bookNum = Integer.parseInt(bookNumString);
                int from = Integer.parseInt(fromNum);
                int to = Integer.parseInt(toNum);
                
                if( to >= from)// && (from >= origFromNum && from <= origToNum) && (to <= origToNum))
                {
                    
                    int option = JOptionPane.showConfirmDialog(subIssueReceiptBookWindow, "Are you sure ?");
                    //System.out.println("option :: "+option);
                    if(option == 0)
                    {
                        connect updateconnection = new connect();
                        
                        
                        String sqlQuery = "delete from sub_issue_details  where series_name = '"+seriesName+"' and book_num = "+bookNum+" and issued_to =  '"+issuedToGroup+"' and rcpt_num >="+from+" and rcpt_num <="+to ;
                        
                        try
                        {
                            System.out.println(sqlQuery);
                            updateconnection.a = updateconnection.st.executeUpdate(sqlQuery);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        
                        
                        //else
                        //    JOptionPane.showMessageDialog(subIssueReceiptBookWindow, "Entry already exist for receipt from : "+entryStart);
                        
                        updateconnection.closeAll();
                        seriesDropDown.setSelectedItem(0);
                        bookNumDropDown.setSelectedItem(0);
                        receiptFromText.setText("");
                        receiptToText.setText("");
                        issuedToStallText.setText("");
                        descriptionText.setText("");
                        fromText.setText("");
                        toText.setText("");
                        revertBackFromDropDown.removeAllItems();
                        
                        
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
        else if(ie.getSource() == bookNumDropDown)
        {
            String rcptNum =  (String)bookNumDropDown.getSelectedItem();
            String seriesName = (String)seriesDropDown.getSelectedItem();
            //int rcptNumInt = Integer.parseInt(rcptNum);
            String sqlQuery = "select start_rcpt_num, end_rcpt_num, issued_to from receipt_book_inventory where series_name = '"+seriesName+"' and book_num= "+rcptNum ;
            //System.out.println(sqlQuery);
            connect fillInfo = new connect();
            try
            {
                fillInfo.rs = fillInfo.st.executeQuery(sqlQuery);
                fillInfo.rs.next();
                int startNum = fillInfo.rs.getInt(1);
                fromText.setText(""+startNum);
                int endNum = fillInfo.rs.getInt(2);
                toText.setText(""+endNum);
                String issued_to_stall = fillInfo.rs.getString(3);
                issuedToStallText.setText(issued_to_stall);
                revertBackToDropDown.setSelectedItem((Object)issued_to_stall);
                int rcpt = Integer.parseInt(rcptNum);
                fillSubGroupInformationForSeries(revertBackFromDropDown,seriesName,rcpt);
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
        else if(ie.getSource() == revertBackFromDropDown)
        {
            String rcptNum =  (String)bookNumDropDown.getSelectedItem();
            String seriesName = (String)seriesDropDown.getSelectedItem();
            String subgroupName = (String)revertBackFromDropDown.getSelectedItem();
            //int rcptNumInt = Integer.parseInt(rcptNum);
            //System.out.println(sqlQuery);
            connect fillInfo = new connect();
            try
            {
                String minSqlQuery = "select min(rcpt_num) from sub_issue_details where series_name='"+seriesName+"' and book_num="+rcptNum+" and issued_to='"+subgroupName+"'";
                fillInfo.rs = fillInfo.st.executeQuery(minSqlQuery);
                fillInfo.rs.next();
                int minNum = fillInfo.rs.getInt(1);
                receiptFromText.setText(""+minNum);
                
                String maxSqlQuery = "select max(rcpt_num) from sub_issue_details where series_name='"+seriesName+"' and book_num="+rcptNum+" and issued_to='"+subgroupName+"'";
                fillInfo.rs = fillInfo.st.executeQuery(maxSqlQuery);
                fillInfo.rs.next();
                int maxNum = fillInfo.rs.getInt(1);
                receiptToText.setText(""+maxNum);
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            fillInfo.closeAll();
            
        }
        
    }
    
    void fillSubGroupInformationForSeries(JComboBox revertBackFromDropDown,String series, int bookNum)
    {
        connect fillSerieConnection = new connect();
        Object[] seriesNameArray = null;
        if(series.isEmpty() || bookNum == 0) return;
        try
        {
            String query = "select distinct issued_to from sub_issue_details where series_name='"+series+"' and book_num="+bookNum;
            String countQuery = "select count(distinct issued_to) from sub_issue_details where series_name='"+series+"' and book_num="+bookNum;
            
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(countQuery);
            fillSerieConnection.rs.next();
            int ArrayCount = fillSerieConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            //seriesNameArray = new Object[ArrayCount + 1];
            //seriesNameArray[0] = "";
            revertBackFromDropDown.removeAllItems();
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillSerieConnection.rs.next()) {
                Object subgroup = fillSerieConnection.rs.getString(1);
                revertBackFromDropDown.addItem(subgroup);
                //seriesNameArray[i] = fillSerieConnection.rs.getString(1);
                //i++;
            }
            
            fillSerieConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillSerieConnection.closeAll();
        }
        //return seriesNameArray;
    }
}
