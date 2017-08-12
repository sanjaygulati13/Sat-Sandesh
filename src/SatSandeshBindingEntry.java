
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjay
 */
public class SatSandeshBindingEntry implements ActionListener{
    
    JFrame addSatSandeshBindingWindow;
    JLabel yearLabel, stallLabel, dateLabel, nameLabel, languageLabel, countLabel, amountLabel;
    TextFieldWithLimit /*dateText, monthText, yearText,*/ nameText, countText, amountText;
    JComboBox yearDropDown, languageDropDown, stallDropDown;
    JButton okButton, cancelButton;
    
    MigLayout mLayout= new MigLayout( "insets 30");
    Object [] stalls = {"Main Store","Kirpal Bagh", "Kirpal Ashram","Other"};
    //Object [] stalls = {"Main Store"};
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
    
    public static void main(String args[])
    {
        new SatSandeshBindingEntry();
    }
    
    public SatSandeshBindingEntry()
    {
        
        addSatSandeshBindingWindow = new JFrame("Add Sat Sandesh Binding");
        addSatSandeshBindingWindow.setLayout(mLayout);
        addSatSandeshBindingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addSatSandeshBindingWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        addSatSandeshBindingWindow.setSize(500,350);
        //Getting the size of the screen, so that the window can 
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = addSatSandeshBindingWindow.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        addSatSandeshBindingWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame 
        try 
        {    
            addSatSandeshBindingWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } 
        catch (Exception cnf) 
        {
           // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        
        yearLabel = new JLabel("<HTML>Year</HTML>");
        stallLabel = new JLabel("<HTML>Stall</HTML>");
        dateLabel = new JLabel("<HTML>Date</HTML>");
        nameLabel = new JLabel("<HTML>Name</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        countLabel = new JLabel("<HTML>Bindings</HTML>");
        amountLabel = new JLabel("<HTML>Amount</HTML>");
        
        yearDropDown = new JComboBox(fillIssueYearInformation());
        languageDropDown = new JComboBox(language);
        stallDropDown = new JComboBox(stalls);
        
        
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        
        //dateText = new TextFieldWithLimit( 2 , 2 );
        //monthText = new TextFieldWithLimit( 2 , 2 );
        //yearText = new TextFieldWithLimit( 4 , 4 );
        nameText = new TextFieldWithLimit( 32 , 32 );
        nameText.setText(SamsUtilities.getUserName());
        countText = new TextFieldWithLimit( 4 , 4 );
        amountText = new TextFieldWithLimit( 5 , 5 );
        
        //monthText.setText(""+SamsUtilities.getCurrentMonth());
        //yearText.setText(""+SamsUtilities.getCurrentYear());
        
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        addSatSandeshBindingWindow.add(yearLabel);
        addSatSandeshBindingWindow.add(yearDropDown);
        
        addSatSandeshBindingWindow.add(languageLabel);
        addSatSandeshBindingWindow.add(languageDropDown, "wrap 20px");
        
        addSatSandeshBindingWindow.add(stallLabel);
        addSatSandeshBindingWindow.add(stallDropDown);
        addSatSandeshBindingWindow.add(countLabel);
        addSatSandeshBindingWindow.add(countText, "wrap 20px");
        
        
        addSatSandeshBindingWindow.add(dateLabel);
        addSatSandeshBindingWindow.add(datePicker,"w 150!");
        //addSatSandeshBindingWindow.add(dateText, "split 3");
        //addSatSandeshBindingWindow.add(monthText);
        //addSatSandeshBindingWindow.add(yearText);
        addSatSandeshBindingWindow.add(amountLabel);
        addSatSandeshBindingWindow.add(amountText, "wrap 20px");
        
        
        addSatSandeshBindingWindow.add(nameLabel);
        addSatSandeshBindingWindow.add(nameText, "wrap 20px");
        
        
        addSatSandeshBindingWindow.add(okButton, "gapleft 80, w :90:, span 2");
        addSatSandeshBindingWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        addSatSandeshBindingWindow.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent event) {
         
        if(event.getSource() == okButton)
        {
            String yearString = (String)yearDropDown.getSelectedItem();
            String language = (String)languageDropDown.getSelectedItem();
            String stall = (String)stallDropDown.getSelectedItem();
            String bindingsString = countText.getText();
            //String entryDate = yearText.getText()+"-"+monthText.getText()+"-"+dateText.getText();
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int eDate= selectedDate.getDate();
            int eMonth=selectedDate.getMonth()+1;
            int eYear=selectedDate.getYear()+1900;
            String entryDate = eYear+"-"+eMonth+"-"+eDate;
            
            String issuedBy = nameText.getText();
            String issuePriceString = amountText.getText();
            
            if(
                    !yearString.isEmpty()
                    && !language.isEmpty()
                    && !stall.isEmpty()
                    && !bindingsString.isEmpty()
                    && !entryDate.isEmpty()
                    && !issuedBy.isEmpty()
                    && !issuePriceString.isEmpty()                    
                    )
            {
                int price = Integer.parseInt(issuePriceString);
                int year = Integer.parseInt(yearString);          
                int qty = Integer.parseInt(bindingsString);
                
                if(price < 0 || year < 1980 || qty < 1)
                {
                    JOptionPane.showMessageDialog(addSatSandeshBindingWindow, "Please verify the details entered");
                    
                }
                else
                {
                    int option = JOptionPane.showConfirmDialog(addSatSandeshBindingWindow, "Are you sure ?");
                    //System.out.println("option :: "+);
                    if(option == 0)                    
                    {
                        for(int monthNum = 1; monthNum < 13; monthNum++)
                        {
                            if(monthNum == 8 || monthNum == 2)
                                continue;
                            String query = "insert into sat_sandesh_inventory_issue values ( 'Binding', "+monthNum+","+year+","+qty+", '"+language+"','binding', "+price+",0, '"+stall+"', '"+entryDate+"', '"+issuedBy+"', 99999 )";
                            
                            {
                                connect updateconnection = new connect();
                                try
                                {
                                    //System.out.println(query);
                                    updateconnection.a = updateconnection.st.executeUpdate(query);
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                                updateconnection.closeAll();
                                
                            }
                        }
                    }
                    
                    yearDropDown.setSelectedIndex(0);
                    languageDropDown.setSelectedIndex(0);
                    stallDropDown.setSelectedIndex(0);
                    datePicker.getJFormattedTextField().setText("");
                    //dateText.setText("");
                    //monthText.setText(""+SamsUtilities.getCurrentMonth());
                    //yearText.setText(""+SamsUtilities.getCurrentYear());
                    nameText.setText("");
                    countText.setText("");
                    amountText.setText("");
                    
                }
                
            }
        
        }
        
        if(event.getSource() == cancelButton)
        {
            addSatSandeshBindingWindow.setVisible(false);
            new sams();
            addSatSandeshBindingWindow.dispose();
        }
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
    
}
