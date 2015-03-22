
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
public class SatSandeshInventoryStoreIssue implements ActionListener, ItemListener{

    JFrame addSatSandeshInventoryWindow;
    JLabel issueMonthLabel, issueYearLabel, issuePriceLabel, billNumberLabel, quantityLabel, entryDateLabel, nameLabel, stallLabel, toStallLabel, languageLabel;
    
    TextFieldWithLimit issueMonthtext, issueYearText, issuePriceText, billNumberText, quantityText, entryDateText, entryDateMonthText,entryDateYearText, issuedByNameText;
    JComboBox languageDropDown, stallDropDown, toStallDropDown;
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    Object [] stalls = {"Main Store", "Kirpal Bagh", "Kirpal Ashram","Other"};
    String language[] = {
                        "",
                        "Hindi",
                        "English",
                        "Urdu",
                        "Punjabi"
    };
    
    //Object [] stalls = {"Kirpl Bagh", "Kirpal Ashram"};
    public static void main (String[] args)
    {
        new SatSandeshInventoryStoreIssue();
        
    }
    
    public SatSandeshInventoryStoreIssue()
    {
        addSatSandeshInventoryWindow = new JFrame("Add Sat Sandesh Inventory");
        addSatSandeshInventoryWindow.setLayout(mLayout);
        addSatSandeshInventoryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addSatSandeshInventoryWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        addSatSandeshInventoryWindow.setSize(550,440);
        //Getting the size of the screen, so that the window can 
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = addSatSandeshInventoryWindow.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
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
        nameLabel = new JLabel("<HTML>Your Name</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        stallLabel = new JLabel("<HTML>From Stall</HTML>"); 
        toStallLabel = new JLabel("<HTML>To Stall</HTML>"); 
        billNumberLabel = new JLabel("<HTML>Bill No.</HTML>");
        
        languageDropDown = new JComboBox(language);
        stallDropDown = new JComboBox(stalls);
        toStallDropDown = new JComboBox(stalls);
        toStallDropDown.setSelectedIndex(1);
        
        issueMonthtext = new TextFieldWithLimit( 2 , 2 );
        issueYearText = new TextFieldWithLimit( 4 , 4 );
        issuePriceText = new TextFieldWithLimit( 4 , 4 );
        quantityText = new TextFieldWithLimit( 5 , 5 );
        billNumberText = new TextFieldWithLimit( 5 , 5 );
        entryDateText = new TextFieldWithLimit( 2 , 2 );
        entryDateMonthText = new TextFieldWithLimit( 2 , 2 );
        entryDateYearText = new TextFieldWithLimit( 4 , 4 );
        issuedByNameText= new TextFieldWithLimit( 32 , 32 );
        
        issueMonthtext.setText(""+SamsUtilities.getCurrentMonth());
        issueYearText.setText(""+SamsUtilities.getCurrentYear());
        
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        addSatSandeshInventoryWindow.add(stallLabel);
        addSatSandeshInventoryWindow.add(stallDropDown);
        
        addSatSandeshInventoryWindow.add(toStallLabel);
        addSatSandeshInventoryWindow.add(toStallDropDown,"wrap 20px");
        
        addSatSandeshInventoryWindow.add(issueMonthLabel);
        addSatSandeshInventoryWindow.add(issueMonthtext); 
        
        addSatSandeshInventoryWindow.add(issueYearLabel); 
        addSatSandeshInventoryWindow.add(issueYearText, "wrap 20px"); 
        
        addSatSandeshInventoryWindow.add(languageLabel);
        addSatSandeshInventoryWindow.add(languageDropDown);
        addSatSandeshInventoryWindow.add(issuePriceLabel);
        addSatSandeshInventoryWindow.add(issuePriceText, "wrap 20px");
        
        addSatSandeshInventoryWindow.add(billNumberLabel);
        addSatSandeshInventoryWindow.add(billNumberText);
        
        addSatSandeshInventoryWindow.add(quantityLabel);
        addSatSandeshInventoryWindow.add(quantityText, "wrap 20px");
        
        addSatSandeshInventoryWindow.add(entryDateLabel);
        addSatSandeshInventoryWindow.add(entryDateText,"split 3");
        addSatSandeshInventoryWindow.add(entryDateMonthText);
        addSatSandeshInventoryWindow.add(entryDateYearText, "wrap 20px"); 
        
        addSatSandeshInventoryWindow.add(nameLabel);
        addSatSandeshInventoryWindow.add(issuedByNameText, "wrap 20px");
        //JComboBox seriesDropDown, bookNumDropDown, stallDropDown;
        addSatSandeshInventoryWindow.add(okButton, "gapleft 80, w :90:, span 2");
        addSatSandeshInventoryWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        addSatSandeshInventoryWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == okButton)
        {
            //gather the info from user input into the frame
            String counter = (String)stallDropDown.getSelectedItem();
            String toCounter = (String)toStallDropDown.getSelectedItem();
            String issueMonth = issueMonthtext.getText();
            String issueYear = issueYearText.getText();
            String issuePriceString = issuePriceText.getText();
            String quantity = quantityText.getText();
            String entryDate = entryDateYearText.getText()+"-"+entryDateMonthText.getText()+"-"+entryDateText.getText();;
            String issuedBy = issuedByNameText.getText();
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
                entryDateYearText.setText("");
                entryDateMonthText.setText("");
                entryDateText.setText("");
                issuedByNameText.setText("");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
