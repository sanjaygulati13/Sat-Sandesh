
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
import javax.swing.UIManager;


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
    
    Object [] stalls = {"Kirpl Bagh", "Kirpal Ashram"};
    
    public IssueReceiptBookClass()
    {
        //setting environment for the Frame issueReceiptBookWindow
        issueReceiptBookWindow = new JFrame("Issue a Receipt Book");
        issueReceiptBookWindow.setLayout(null);
        issueReceiptBookWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        issueReceiptBookWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        issueReceiptBookWindow.setSize(500,200);
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
        issueDateMonthtext = new TextFieldWithLimit( 2 , 2 );
        issueDateYearText = new TextFieldWithLimit( 4 , 4 );
        issueDatetext = new TextFieldWithLimit( 2 , 2 );
        fromText = new TextFieldWithLimit( 5 , 5 );
        toText  = new TextFieldWithLimit( 5 , 5 );
        issuedByText = new TextFieldWithLimit(32, 32);
        
        
        //Buttons
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        
        
        
        //adding the gui elements to frame
        issueReceiptBookWindow.add(seriesLabel);
        issueReceiptBookWindow.add(seriesDropDown);
        issueReceiptBookWindow.add(bookNumLabel);
        issueReceiptBookWindow.add(bookNumDropDown);
        issueReceiptBookWindow.add(fromLabel);
        issueReceiptBookWindow.add(fromText);
        issueReceiptBookWindow.add(toLabel);
        issueReceiptBookWindow.add(toText);
        issueReceiptBookWindow.add(issuedToLabel);
        issueReceiptBookWindow.add(stallDropDown);
        issueReceiptBookWindow.add(issueDateLabel);
        issueReceiptBookWindow.add(issueDatetext);
        issueReceiptBookWindow.add(issueDateMonthtext);
        issueReceiptBookWindow.add(issueDateYearText);
        issueReceiptBookWindow.add(issuedByLabel);
        issueReceiptBookWindow.add(issuedByText);
        issueReceiptBookWindow.add(okButton);
        issueReceiptBookWindow.add(cancelButton);
        
    
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        issueReceiptBookWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        
    }
    
}
