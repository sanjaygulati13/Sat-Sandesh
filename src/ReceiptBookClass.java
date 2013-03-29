/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanjay
 */

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
        
public class ReceiptBookClass implements ActionListener {
    
    Object[] numReceiptValues = { "20", "25", "40", "50", "60" };
    JLabel seriesLabel, receiptNumLabel, bookletNumLabel, startingReceiptNumLabel;
    TextFieldWithLimit seriesText, bookletNumText, startingReceiptNumText;
    JComboBox receiptNumComboBox;
    JButton okButton, cancelButton;
    JFrame receiptWindow;
    
    
    public ReceiptBookClass()
    {
        //Initializing the frame and giving it characteristics
        receiptWindow = new JFrame("Add New Series");
        receiptWindow.setLayout(null);
        receiptWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        receiptWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        receiptWindow.setSize(500,250);
        //Getting the size of the screen, so that the window can 
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = receiptWindow.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        receiptWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame 
        try 
        {    
            receiptWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } 
        catch (Exception cnf) 
        {
           // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        //Populating the frame with information
        seriesLabel = new JLabel("Series");
        receiptNumLabel = new JLabel("<HTML>No of Receipts per Booklet</HTML>");
        bookletNumLabel = new JLabel("<HTML>No of Booklets</HTML>");
        startingReceiptNumLabel = new JLabel();
        startingReceiptNumLabel.setText("<HTML>Starting Receipt No.</HTML>");
        
        seriesText = new TextFieldWithLimit(25, 25);
        receiptNumComboBox = new JComboBox(numReceiptValues);
        bookletNumText = new TextFieldWithLimit(4, 4);
        startingReceiptNumText = new TextFieldWithLimit(5, 5);
        
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        
        
        
        

        //Laying out the GUI elements
        seriesLabel.setBounds(30, 30, 50, 30);
        seriesText.setBounds(100, 30, 180,30);
        
        receiptNumLabel.setBounds(300 ,30, 90,30);
        receiptNumComboBox.setBounds(400,30,50,25);
        
        bookletNumLabel.setBounds(30, 80, 50,30);
        bookletNumText.setBounds(100,80,80,30);
        
        startingReceiptNumLabel.setBounds(300,80,90,30);
        startingReceiptNumText.setBounds(400,80,50,30);
        
        okButton.setBounds(frameSize.width/4, 130, frameSize.width/4 - 10 , 30);
        okButton.setMnemonic('o');
        
        cancelButton.setBounds(frameSize.width/2, 130, frameSize.width/4 -10 , 30);
        cancelButton.setMnemonic('c');
        
        //adding the GUI elements to frame
        receiptWindow.add(seriesLabel);
        receiptWindow.add(seriesText);
        receiptWindow.add(receiptNumLabel);
        receiptWindow.add(receiptNumComboBox);
        receiptWindow.add(bookletNumLabel);
        receiptWindow.add(bookletNumText);
        receiptWindow.add(startingReceiptNumLabel);
        receiptWindow.add(startingReceiptNumText);
        receiptWindow.add(okButton);
        receiptWindow.add(cancelButton);
              
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        receiptWindow.setVisible(true);
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            //gather the info from user input into the frame
            String seriesName = seriesText.getText();
            String receiptString = (String)(receiptNumComboBox.getSelectedItem());
            String bookletString = bookletNumText.getText();
            String startingString = startingReceiptNumText.getText();
            if(!seriesName.isEmpty() && !receiptString.isEmpty() && !bookletString.isEmpty() && !startingString.isEmpty())
            {
                
                int numReceipts = Integer.parseInt(receiptString);
                int numBooklets = Integer.parseInt(bookletString);
                int startingNum = Integer.parseInt(startingString);
                JOptionPane.showConfirmDialog(receiptWindow, "S::"+seriesName+" N::"+numReceipts+" B::"+numBooklets+" s::"+startingNum);
            }
            else
                JOptionPane.showMessageDialog(receiptWindow, "Please fill all the fields");
            
            
        }
        else if(event.getSource() == cancelButton)
        {
            receiptWindow.setVisible(false);
            new sams();
            receiptWindow.dispose();
            
        }
        
    }
    
    
    public static void main(String args[])
    {
        ReceiptBookClass demo = new ReceiptBookClass();        
    }
}


