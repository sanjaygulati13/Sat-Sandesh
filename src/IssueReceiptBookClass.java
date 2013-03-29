
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;


/**
 *
 * @author Sanjay
 */
public class IssueReceiptBookClass implements ActionListener {
    
    //Data Type declarations
    JFrame issueReceiptBookWindow;
    JLabel seriesLabel, bookNumLabel, fromLabel, toLabel, issuedToLabel, issueDateLabel, issuedByLabel;
    
    TextFieldWithLimit issueDateMonthtext, issueDateYearText, issueDatetext, fromText, toText, issuedByText;
    JComboBox seriesDropDown, bookNumDropDown, stallDropDown;
    JButton okButton, cancelButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object [] stalls = {"Kirpl Bagh", "Kirpal Ashram"};
    
    public IssueReceiptBookClass()
    {
        //setting environment for the Frame issueReceiptBookWindow
        issueReceiptBookWindow = new JFrame("Issue a Receipt Book");
        issueReceiptBookWindow.setLayout(mLayout);
        issueReceiptBookWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        issueReceiptBookWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        issueReceiptBookWindow.setSize(500,300);
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
        seriesDropDown = new JComboBox();
        bookNumDropDown = new JComboBox();
        stallDropDown = new JComboBox(stalls);
        
        //TextFields
        issueDatetext = new TextFieldWithLimit( 2 , 2 );
        issueDateMonthtext = new TextFieldWithLimit( 2 , 2 );
        issueDateYearText = new TextFieldWithLimit( 4 , 4 );

        fromText = new TextFieldWithLimit( 5 , 5 );
        fromText.setEnabled(false);
        
        toText  = new TextFieldWithLimit( 5 , 5 );
        toText.setEnabled(false);
        
        issuedByText = new TextFieldWithLimit(25, 25);
        
        
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
        issueReceiptBookWindow.add(issueDatetext, "split 3, w 20!");
        issueReceiptBookWindow.add(issueDateMonthtext, " w 20!");
        issueReceiptBookWindow.add(issueDateYearText, "w 40! , wrap 20px");
        issueReceiptBookWindow.add(issuedByLabel);
        issueReceiptBookWindow.add(issuedByText,"wrap 20px");
        issueReceiptBookWindow.add(okButton, "gapleft 80, w :90:, span 2");
        issueReceiptBookWindow.add(cancelButton, "gapleft 60, w :90: ,span 3");
        
    
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
            /*
            String seriesName = seriesText.getText();
            String receiptString = (String)(receiptNumComboBox.getSelectedItem());
            String bookletString = bookletNumText.getText();
            String startingString = startingReceiptNumText.getText();
            if(!seriesName.isEmpty() && !receiptString.isEmpty() && !bookletString.isEmpty() && !startingString.isEmpty())
            {
                
                int numReceipts = Integer.parseInt(receiptString);
                int numBooklets = Integer.parseInt(bookletString);
                int startingNum = Integer.parseInt(startingString);*/
                JOptionPane.showConfirmDialog(issueReceiptBookWindow, "Are you sure ?");
                /*
            }
             
             
              else 
                JOptionPane.showMessageDialog(issueReceiptBookWindow, "Please fill all the fields");
             */
            
            
        }
        else if(event.getSource() == cancelButton)
        {
            issueReceiptBookWindow.setVisible(false);
            new sams();
            issueReceiptBookWindow.dispose();
            
        }
        
    }
    
    public static void main(String args[])
    {
        new IssueReceiptBookClass();
    }
    
}
