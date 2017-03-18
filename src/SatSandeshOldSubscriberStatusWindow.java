
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
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author sanjay
 */
public class SatSandeshOldSubscriberStatusWindow implements ActionListener{
    
    JComboBox statusTypeDropDown, detailsTypeDropdown;
    JLabel statusTypeLabel,detailsTypeLabel;
    JButton printButton,backButton;
    
    Object status[]={"Deactive","Freeze","Inactive"};
    Object details[]={"Detailed","Summary"};
    
    JFrame satSandeshSubscriberStatusWindow;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    public static void main(String args[])
    {
        new SatSandeshOldSubscriberStatusWindow();
    }
    
    SatSandeshOldSubscriberStatusWindow()
    {
        satSandeshSubscriberStatusWindow = new JFrame();
        
        try
        {
            satSandeshSubscriberStatusWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (ClassNotFoundException cnf)
        {
            JOptionPane.showMessageDialog(satSandeshSubscriberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException cnf) {
            JOptionPane.showMessageDialog(satSandeshSubscriberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException cnf) {
            JOptionPane.showMessageDialog(satSandeshSubscriberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedLookAndFeelException cnf) {
            JOptionPane.showMessageDialog(satSandeshSubscriberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        satSandeshSubscriberStatusWindow = new JFrame("Sat Sandesh Old Subscriber Record");
        satSandeshSubscriberStatusWindow.setLayout(mLayout);
        satSandeshSubscriberStatusWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshSubscriberStatusWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshSubscriberStatusWindow.setSize((screenSize.width)*4/10,(screenSize.height*2)/5);
        Dimension frameSize = satSandeshSubscriberStatusWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 3;
        satSandeshSubscriberStatusWindow.setLocation(x, y);
        
        
        statusTypeLabel = new JLabel("Status");
        satSandeshSubscriberStatusWindow.add(statusTypeLabel);
        
        statusTypeDropDown = new JComboBox(status);
        satSandeshSubscriberStatusWindow.add(statusTypeDropDown);
        
        detailsTypeLabel = new JLabel("Type");
        satSandeshSubscriberStatusWindow.add(detailsTypeLabel);
        
        detailsTypeDropdown = new JComboBox(details);
        satSandeshSubscriberStatusWindow.add(detailsTypeDropdown,"wrap 30");
        
        printButton = new JButton("Get List");
        printButton.addActionListener(this);
        printButton.setMnemonic('p');
        satSandeshSubscriberStatusWindow.add(printButton,"span 2");
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setMnemonic('b');
        satSandeshSubscriberStatusWindow.add(backButton,"span 2");
        
        satSandeshSubscriberStatusWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshSubscriberStatusWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == printButton){
            String statusType = (String)statusTypeDropDown.getSelectedItem();
            String detailsType = (String)detailsTypeDropdown.getSelectedItem();
            //""};
            if(statusType.equals("Freeze")){
                
                if(detailsType.equals("Detailed"))
                    new freezed();
                else
                    new freeze();
            }
            else if(statusType.equals("Deactive"))
            {
                if(detailsType.equals("Detailed"))
                    new deactived();
                else
                    new deactive();
                
            }
            else{ //Inactive
                if(detailsType.equals("Detailed"))
                    new inactived();
                else
                    new inactive();
            }
            satSandeshSubscriberStatusWindow.dispose();
        }
        if(e.getSource() == backButton){
            new sams();
            satSandeshSubscriberStatusWindow.dispose();
        }
    }
    
}
