
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
import javax.swing.JTextField;
import javax.swing.UIManager;
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
public class SatSandeshCancelReceiptWindow implements ActionListener, ItemListener {
    
    public static void main(String args[])
    {
        new SatSandeshCancelReceiptWindow();
    }
    
    
    JComboBox seriesDropDown, bookNumberDropDown, rcptNumberDropDown;
    JLabel seriesLabel, bookNumberLabel, counterLabel, rcptNumberLabel;
    JTextField counterText;
    JButton okButton, refreshButton, backButton;
    
    JFrame satSandeshCancelReceiptWindowFrame;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    protected void center()
    {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshCancelReceiptWindowFrame.setSize(screenSize.width-300,screenSize.height-300);
        Dimension frameSize = satSandeshCancelReceiptWindowFrame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        
        satSandeshCancelReceiptWindowFrame.setLocation(x, y);
    }
    
    public SatSandeshCancelReceiptWindow()
    {
        satSandeshCancelReceiptWindowFrame = new JFrame("Cancel Receipt");
        satSandeshCancelReceiptWindowFrame.setLayout(mLayout);
        satSandeshCancelReceiptWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshCancelReceiptWindowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshCancelReceiptWindowFrame.setSize((screenSize.width*1)/4,(screenSize.height*3)/10);
        Dimension frameSize = satSandeshCancelReceiptWindowFrame.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        satSandeshCancelReceiptWindowFrame.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshCancelReceiptWindowFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            // this class will create a log, helps in debugging
            cnf.printStackTrace();
        }
        
        seriesLabel     = new JLabel("Series");
        bookNumberLabel = new JLabel("Book Number");
        counterLabel    = new JLabel("Counter");
        rcptNumberLabel = new JLabel("Receipt Number");
        
        seriesDropDown      = new JComboBox(SamsUtilities.fillSeriesInformation());
        bookNumberDropDown  = new JComboBox();
        counterText         = new JTextField();
        rcptNumberDropDown  = new JComboBox();
        
        
        okButton        = new JButton(SamsUtilities.getOkButtonName());
        refreshButton   = new JButton(SamsUtilities.getClearButtonName());
        backButton      = new JButton(SamsUtilities.getCancelButtonName());
        
        counterText.setEnabled(false);
        
        satSandeshCancelReceiptWindowFrame.add(seriesLabel);
        satSandeshCancelReceiptWindowFrame.add(seriesDropDown, "w 150!,wrap, span 2");
        satSandeshCancelReceiptWindowFrame.add(bookNumberLabel);
        satSandeshCancelReceiptWindowFrame.add(bookNumberDropDown, "w 150!,wrap, span 2");
        satSandeshCancelReceiptWindowFrame.add(counterLabel);
        satSandeshCancelReceiptWindowFrame.add(counterText, "w 150!, wrap, span 2");
        satSandeshCancelReceiptWindowFrame.add(rcptNumberLabel);
        satSandeshCancelReceiptWindowFrame.add(rcptNumberDropDown, "w 150!,wrap, span 2");
        
        satSandeshCancelReceiptWindowFrame.add(okButton);
        satSandeshCancelReceiptWindowFrame.add(refreshButton);
        satSandeshCancelReceiptWindowFrame.add(backButton, "wrap");
        
        
        seriesDropDown.addItemListener(this);
        bookNumberDropDown.addItemListener(this);
        //rcptNumberDropDown.addItemListener(this);
        okButton.addActionListener(this);
        refreshButton.addActionListener(this);
        backButton.addActionListener(this);
        
        
        satSandeshCancelReceiptWindowFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton)
        {
            try{
                
                connect con = new connect();
                
                //String query = "insert into receipt_book_details values ( 1000, amount = 0, counter = '"+counterText.getText()+"', updated_by = '"+SamsUtilities.getUserName()+"', ending_period = '2000-1-28', bulk_despatch_code = 0 where series_name = '"+seriesDropDown.getSelectedItem().toString()+"' and receipt_number = "+rcptNumberDropDown.getSelectedItem().toString();
                String seriesName = seriesDropDown.getSelectedItem().toString();
                String rcptNum = rcptNumberDropDown.getSelectedItem().toString();
                String amount = "0";
                String counter = counterText.getText();
                
                String query = "insert into receipt_book_details values ('"
                                +seriesName+"',"
                                +rcptNum+",1000,'Cash','"+SamsUtilities.getCurrentYear()+"-"
                                +SamsUtilities.getCurrentMonth()+"-"+SamsUtilities.getCurrentDate()+"',"
                                +amount+",'"+counter+"','0','"+SamsUtilities.getUserName()+"','2000-1-28',0)";
                //System.out.println(query);
                con.a = con.st.executeUpdate(query);
                if(con.a == 0)
                {
                    JOptionPane.showMessageDialog(satSandeshCancelReceiptWindowFrame, "ERROR : No record updated", "ERROR: No record updated", JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(satSandeshCancelReceiptWindowFrame, "Update successfull", "Update successfull", JOptionPane.INFORMATION_MESSAGE);
                    
                //System.out.println(con.a);
                seriesDropDown.setSelectedIndex(0);
                bookNumberDropDown.removeAllItems();
                rcptNumberDropDown.removeAllItems();
                counterText.setText("");
                con.closeAll();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
        }
        else if(e.getSource() == refreshButton)
        {
            seriesDropDown.setSelectedIndex(0);
            bookNumberDropDown.removeAllItems();
            rcptNumberDropDown.removeAllItems();
            counterText.setText("");
        }
        else if(e.getSource() == backButton)
        {
            new sams();
            satSandeshCancelReceiptWindowFrame.dispose();
            
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == seriesDropDown)
        {
            try{
                bookNumberDropDown.removeAllItems();
                connect con = new connect();
                
                String query = "select book_num from receipt_book_inventory where series_name = '"+seriesDropDown.getSelectedItem().toString()+"'";
                con.rs = con.st.executeQuery(query);
                while(con.rs.next()){
                    bookNumberDropDown.addItem(con.rs.getInt(1));
                }
                
                con.closeAll();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
        }
        else if(e.getSource() == bookNumberDropDown)
        {
            if(seriesDropDown.getSelectedItem().toString().isEmpty() == false){
                try{
                    counterText.setText("");
                    connect con = new connect();
                    
                    {
                        String bookNum = bookNumberDropDown.getSelectedItem().toString();
                        
                        String query = "select issued_to from receipt_book_inventory where series_name = '"+seriesDropDown.getSelectedItem().toString()+"' and book_num = "+bookNum +"and issued_to not in ('')";
                        con.rs = con.st.executeQuery(query);
                        if(con.rs.next()){
                            counterText.setText(con.rs.getString(1));
                        }
                        
                        query = "select start_rcpt_num, end_rcpt_num from receipt_book_inventory where  series_name = '"+seriesDropDown.getSelectedItem().toString()+"' and book_num = "+bookNum +"and issued_to not in ('')"; 
                        
                        int start = 0;
                        int end = 0;
                        con.rs = con.st.executeQuery(query);
                        if(con.rs.next()){
                            start = con.rs.getInt(1);
                            end = con.rs.getInt(2);
                        }
                        
                        
                        rcptNumberDropDown.removeAllItems();
                        for ( int x = start; x <= end; x++){
                            rcptNumberDropDown.addItem(""+x);
                        }
                        
                        query = "select distinct(receipt_number) from receipt_book_details where series_name = '"+seriesDropDown.getSelectedItem().toString()+"' and receipt_number >= "+start+" and receipt_number <= "+ end + " order by receipt_number ASC";
                        con.rs = con.st.executeQuery(query);
                        while(con.rs.next()){
                            rcptNumberDropDown.removeItem(""+con.rs.getInt(1));
                        }
                    }
                    con.closeAll();
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                    //exc.printStackTrace();
                }
            }
            
        }
    }
    
}
