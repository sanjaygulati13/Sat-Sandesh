
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
public class SatSandeshInventory implements ActionListener, ItemListener{

    JFrame addSatSandeshInventoryWindow;
    JLabel issueMonthLabel, issueYearLabel, quantityLabel, entryDateLabel, nameLabel, stallLabel, languageLabel;
    
    TextFieldWithLimit issueMonthtext, issueYearText, quantityText, entryDateText, entryDateMonthText,entryDateYearText, issuedByNameText;
    JComboBox languageDropDown, stallDropDown;
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram","Other"};
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
        new SatSandeshInventory();
        
    }
    
    public SatSandeshInventory()
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
        addSatSandeshInventoryWindow.setSize(500,300);
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
        quantityLabel = new JLabel("<HTML>Quantity</HTML>");
        entryDateLabel = new JLabel("<HTML>Date of Entry</HTML>");
        nameLabel = new JLabel("<HTML>Your Name</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        stallLabel = new JLabel("<HTML>Stall</HTML>");
        
        languageDropDown = new JComboBox(language);
        stallDropDown = new JComboBox(stalls);
        
        issueMonthtext = new TextFieldWithLimit( 2 , 2 );
        issueYearText = new TextFieldWithLimit( 4 , 4 );
        quantityText = new TextFieldWithLimit( 5 , 5 );
        entryDateText = new TextFieldWithLimit( 2 , 2 );
        entryDateMonthText = new TextFieldWithLimit( 2 , 2 );
        entryDateYearText = new TextFieldWithLimit( 4 , 4 );
        issuedByNameText= new TextFieldWithLimit( 32 , 32 );
        
        
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        addSatSandeshInventoryWindow.add(stallLabel);
        addSatSandeshInventoryWindow.add(stallDropDown,"split 2");
        
        addSatSandeshInventoryWindow.add(issueMonthLabel);
        addSatSandeshInventoryWindow.add(issueMonthtext); 
        
        addSatSandeshInventoryWindow.add(issueYearLabel); 
        addSatSandeshInventoryWindow.add(issueYearText, "wrap 20px"); 
        
        addSatSandeshInventoryWindow.add(languageLabel);
        addSatSandeshInventoryWindow.add(languageDropDown);
        addSatSandeshInventoryWindow.add(quantityLabel);
        addSatSandeshInventoryWindow.add(quantityText, "span 3, wrap 20px");
        
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
            String issueMonth = issueMonthtext.getText();
            String issueYear = issueYearText.getText();
            String quantity = quantityText.getText();
            String entryDate = entryDateYearText.getText()+"-"+entryDateMonthText.getText()+"-"+entryDateText.getText();;
            String issuedBy = issuedByNameText.getText();
            String language  = (String)languageDropDown.getSelectedItem();
            if(!issueMonth.isEmpty() 
                    && !issueYear.isEmpty() 
                    //&& !fromNum.isEmpty() 
                    //&& !toNum.isEmpty()
                    && !issuedBy.isEmpty()
                    && !quantity.isEmpty()
                    && !language.isEmpty()
                    && !counter.isEmpty()
                    )
            {
                int month = Integer.parseInt(issueMonth);
                int year = Integer.parseInt(issueYear);          
                int qty = Integer.parseInt(quantity);
                
                if(month < 1 || month > 12 || qty == 0 || year < 1980 )
                {
                    JOptionPane.showMessageDialog(addSatSandeshInventoryWindow, "Please verify the details entered");
                }
                else
                {
                    String query = "insert into sat_sandesh_inventory_entry values ("+month+","+year+","+qty+",'"+entryDate+"','"+issuedBy+"','"+counter+"','"+language+"')";
                    int option = JOptionPane.showConfirmDialog(addSatSandeshInventoryWindow, "Are you sure ?");
                    //System.out.println("option :: "+option);
                    if(option == 0)
                    {
                        connect updateconnection = new connect();
                        try
                        {
                            System.out.println(query);
                            updateconnection.a = updateconnection.st.executeUpdate(query);
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        updateconnection.closeAll();
                        
                    }
                }
                
                issueMonthtext.setText("");
                issueYearText.setText("");
                quantityText.setText("");
                entryDateYearText.setText("");
                entryDateMonthText.setText("");
                entryDateText.setText("");
                issuedByNameText.setText("");
                languageDropDown.setSelectedItem("");
                stallDropDown.setSelectedItem("");
                
                
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
