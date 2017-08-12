
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
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/**
 *
 * @author Sanjay
 */
public class IssueReceiptBookClass implements ActionListener, ItemListener {
    
    //Data Type declarations
    JFrame issueReceiptBookWindow;
    JLabel seriesLabel, bookNumLabel, fromLabel, toLabel, issuedToLabel, issueDateLabel, issuedByLabel;
    
    TextFieldWithLimit /*issueDateMonthtext, issueDateYearText, issueDatetext, */fromText, toText, issuedByText;
    JComboBox seriesDropDown, bookNumDropDown, stallDropDown;
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram"};
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    public IssueReceiptBookClass()
    {
        //setting environment for the Frame issueReceiptBookWindow
        issueReceiptBookWindow = new JFrame("Issue Receipt Book");
        issueReceiptBookWindow.setLayout(mLayout);
        issueReceiptBookWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        issueReceiptBookWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        issueReceiptBookWindow.setSize(500,350);
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = issueReceiptBookWindow.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        issueReceiptBookWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            issueReceiptBookWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
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
        issuedToLabel = new JLabel("<HTML>Issued to Stall</HTML>");
        issueDateLabel = new JLabel("<HTML>Issue Date</HTML>");
        issuedByLabel = new JLabel("<HTML>Issued By</HTML>");
        
        //DropDowns
        //fill the information from the database while initialization
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        
        
        bookNumDropDown = new JComboBox();
        stallDropDown = new JComboBox(stalls);
        
        seriesDropDown.addItemListener(this);
        bookNumDropDown.addItemListener(this);
        
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        
        //TextFields
        //issueDatetext = new TextFieldWithLimit( 2 , 2 );
        //issueDateMonthtext = new TextFieldWithLimit( 2 , 2 );
        //issueDateYearText = new TextFieldWithLimit( 4 , 4 );
        
        //issueDateMonthtext.setText(""+(SamsUtilities.getCurrentMonth()));
        //issueDateYearText.setText(""+(SamsUtilities.getCurrentYear()));
        
        fromText = new TextFieldWithLimit( 5 , 5 );
        fromText.setEnabled(false);
        
        toText  = new TextFieldWithLimit( 5 , 5 );
        toText.setEnabled(false);
        
        issuedByText = new TextFieldWithLimit(25, 25);
        issuedByText.setText(SamsUtilities.getUserName());
        
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        
        
        //adding the gui elements to frame and setting the layout simultaneously
        issueReceiptBookWindow.add(seriesLabel);
        issueReceiptBookWindow.add(seriesDropDown, "span 2, w 100!");
        issueReceiptBookWindow.add(bookNumLabel);
        issueReceiptBookWindow.add(bookNumDropDown,"span 3, w :90: , wrap 20px");
        issueReceiptBookWindow.add(fromLabel );
        issueReceiptBookWindow.add(fromText, "span 2, w 100!");
        issueReceiptBookWindow.add(toLabel);
        issueReceiptBookWindow.add(toText, "span 3, w :90:, wrap 20px");
        issueReceiptBookWindow.add(issuedToLabel);
        issueReceiptBookWindow.add(stallDropDown, "span 2, w 100!");
        issueReceiptBookWindow.add(issueDateLabel);
        issueReceiptBookWindow.add(datePicker,"w 150!, wrap 15px");
        //issueReceiptBookWindow.add(issueDatetext, "split 3, w 30!");
        //issueReceiptBookWindow.add(issueDateMonthtext, " w 30!");
        //issueReceiptBookWindow.add(issueDateYearText, "w 40! , wrap 20px");
        issueReceiptBookWindow.add(issuedByLabel);
        issueReceiptBookWindow.add(issuedByText,"wrap 20px");
        issueReceiptBookWindow.add(okButton, "gapleft 80, w :90:, span 2");
        issueReceiptBookWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        
        //populate information from datatbase
        //fillSeriesNameInformation();
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        issueReceiptBookWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            //gather the info from user input into the frame
            
            String seriesName = (String)seriesDropDown.getSelectedItem();
            String bookNumString = (String)(bookNumDropDown.getSelectedItem());
            //String fromNum = fromText.getText();
            //String toNum = toText.getText();
            String issuedTo = (String)stallDropDown.getSelectedItem();
            //String issueDate = issueDateYearText.getText()+"-"+issueDateMonthtext.getText()+"-"+issueDatetext.getText();
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int date= selectedDate.getDate();
            int month=selectedDate.getMonth()+1;
            int year=selectedDate.getYear()+1900;
            String issueDate = year+"-"+month+"-"+date;
            
            
            String issuedBy = issuedByText.getText();
            if(!seriesName.isEmpty()
                    && !bookNumString.isEmpty()
                    //&& !fromNum.isEmpty()
                    //&& !toNum.isEmpty()
                    && !issuedTo.isEmpty()
                    && !issueDate.isEmpty()
                    )
            {
                
                int bookNum = Integer.parseInt(bookNumString);
                //int from = Integer.parseInt(fromNum);
                //int to = Integer.parseInt(toNum);
                String sqlQuery = "update receipt_book_inventory set issued_to = '"+issuedTo+"' , issued_date = '"+issueDate+"', issued_by = '"+issuedBy+"' where series_name = '"+seriesName+"' and book_num = "+bookNum;
                int option = JOptionPane.showConfirmDialog(issueReceiptBookWindow, "Are you sure ?");
                //System.out.println("option :: "+option);
                if(option == 0)
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
                    
                }
                
                datePicker.getJFormattedTextField().setText("");
                //issueDateMonthtext.setText(""+(SamsUtilities.getCurrentMonth()));
                //issueDateYearText.setText(""+(SamsUtilities.getCurrentYear()));
                //issueDatetext.setText("");
                fromText.setText("");
                toText.setText("");
                issuedByText.setText("");
                
                seriesDropDown.setSelectedIndex(0);
                //bookNumDropDown.setSelectedIndex(1);
                stallDropDown.setSelectedIndex(0);
                
            }
            else
                JOptionPane.showMessageDialog(issueReceiptBookWindow, "Please fill all the fields");
            
            
            
        }
        else if(event.getSource() == cancelButton)
        {
            issueReceiptBookWindow.setVisible(false);
            new sams();
            issueReceiptBookWindow.dispose();
            
        }
        
    }
    
    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource() == seriesDropDown)
        {
            fillRecptNumInformation(bookNumDropDown, (String)seriesDropDown.getSelectedItem());
        }
        else if(ie.getSource() == bookNumDropDown)
        {
            String rcptNum =  (String)bookNumDropDown.getSelectedItem();
            String seriesName = (String)seriesDropDown.getSelectedItem();
            String sqlQuery = "select start_rcpt_num, end_rcpt_num from receipt_book_inventory where series_name = '"+seriesName+"' and book_num= "+rcptNum ;
            connect fillInfo = new connect();
            try
            {
                fillInfo.rs = fillInfo.st.executeQuery(sqlQuery);
                fillInfo.rs.next();
                int startNum = fillInfo.rs.getInt(1);
                fromText.setText(""+startNum);
                
                
                
                int endNum = fillInfo.rs.getInt(2);
                toText.setText(""+endNum);
            }
            catch(Exception e)
            {
                
            }
            fillInfo.closeAll();
        }
    }
    
    public static void main(String args[])
    {
        new IssueReceiptBookClass();
    }
    
    /*static public Object[] fillSeriesNameInformation()
    {
    connect fillSerieConnection = new connect();
    Object[] seriesNameArray = null;
    try
    {
    String query = "select distinct series_name from receipt_book_inventory";
    String countQuery = "select count(distinct series_name) from receipt_book_inventory";
    
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
    }*/
    
    public static void fillRecptNumInformation(JComboBox bookNumDropDown, String series_name)
    {
        connect fillRcptNumConnection = new connect();
        //Object[] bookNumArray = null;
        try
        {
            String query = "select book_num from receipt_book_inventory where series_name = '"+series_name+"'";
            String countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+series_name+"'";
            
            fillRcptNumConnection.rs = fillRcptNumConnection.st.executeQuery(countQuery);
            fillRcptNumConnection.rs.next();
            int ArrayCount = fillRcptNumConnection.rs.getInt(1);
            //System.out.println(query);
            //bookNumArray = new Object[ArrayCount + 1];
            //bookNumArray[0] = "";
            fillRcptNumConnection.rs = fillRcptNumConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            bookNumDropDown.removeAllItems();
            while (fillRcptNumConnection.rs.next()) {
                //bookNumArray[i] = fillRcptNumConnection.rs.getString(1);
                //System.out.println("Adding Item " + fillRcptNumConnection.rs.getString(1));
                bookNumDropDown.addItem(fillRcptNumConnection.rs.getString(1));
                //i++;
            }
            //System.out.println(bookNumDropDown.getSelectedObjects().length);
            
            fillRcptNumConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillRcptNumConnection.closeAll();
        }
        //return bookNumArray;
    }
}
