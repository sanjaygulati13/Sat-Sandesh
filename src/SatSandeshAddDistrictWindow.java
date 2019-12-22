
import java.awt.Dimension;
import java.awt.GridLayout;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjayg
 */
public class SatSandeshAddDistrictWindow implements ActionListener{
      
    Object[] numReceiptValues = { "","20", "25", "40", "50", "60" };
    JLabel districtLabel, stateLabel, zoneLabel;
    TextFieldWithLimit districtText, zoneText;
    JComboBox stateNameDropBox;
    JButton okButton, cancelButton;
    JFrame addDistrictWindow;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    
    public SatSandeshAddDistrictWindow()
    {
        //Initializing the frame and giving it characteristics
        addDistrictWindow = new JFrame("Add District");
        addDistrictWindow.setLayout(mLayout);
        addDistrictWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addDistrictWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        addDistrictWindow.setSize(500,300);
        //Getting the size of the screen, so that the window can 
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = addDistrictWindow.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        addDistrictWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame 
        try 
        {    
            addDistrictWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } 
        catch (Exception cnf) 
        {
           // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        //Populating the frame with information
        districtLabel = new JLabel("<HTML>District</HTML>");
        stateLabel = new JLabel("<HTML>State Name</HTML>");
        zoneLabel = new JLabel("<HTML>Zone Name</HTML>");
        
        
        districtText = new TextFieldWithLimit(32,32);
        stateNameDropBox = new JComboBox(SamsUtilities.fillStateNameList());
        zoneText = new TextFieldWithLimit(32,32);
        
        
        okButton = new JButton("OK");
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        
        
        //Laying out the GUI elements
        //seriesLabel.setBounds(30, 30, 50, 30);
        //seriesText.setBounds(100, 30, 180,30);
        
        //receiptNumLabel.setBounds(300 ,30, 90,30);
        //receiptNumComboBox.setBounds(400,30,80,25);
        
        //bookletNumLabel.setBounds(30, 80, 50,30);
        //bookletNumText.setBounds(100,80,80,30);
        
        //startingReceiptNumLabel.setBounds(300,80,90,30);
        //startingReceiptNumText.setBounds(400,80,50,30);
        
        //startingBookNumberLabel.setBounds(frameSize.width/4, 130, frameSize.width/4 - 10 , 30);
        //startingBookNumberText.setBounds(frameSize.width/2, 130, frameSize.width/4 -10 , 30);
        
        //okButton.setBounds(frameSize.width/4, 180, frameSize.width/4 - 10 , 30);
        okButton.setMnemonic('o');
        
        //cancelButton.setBounds(frameSize.width/2, 180, frameSize.width/4 -10 , 30);
        cancelButton.setMnemonic('c');
        
        //adding the GUI elements to frame
        addDistrictWindow.add(districtLabel, "w 130!");
        addDistrictWindow.add(districtText, "wrap 30px, w 250!");
        addDistrictWindow.add(stateLabel);
        addDistrictWindow.add(stateNameDropBox, "wrap 30px, w 250!");
        addDistrictWindow.add(zoneLabel);
        addDistrictWindow.add(zoneText, "wrap 30px, w 250!");
        addDistrictWindow.add(okButton, "gapleft 80, w :90:");
        addDistrictWindow.add(cancelButton, "gapleft 60, w :90:");
              
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        addDistrictWindow.setVisible(true);
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            //gather the info from user input into the frame
            String districtName = districtText.getText();
            String stateName = (String)(stateNameDropBox.getSelectedItem());
            String stateCode = SamsUtilities.getStateCodeForStateName(stateName);
            String zoneName = zoneText.getText();
            
            if(!districtName.isEmpty() && !stateName.isEmpty() && !zoneName.isEmpty())
            {
                JOptionPane.showConfirmDialog(addDistrictWindow, "Are you sure ?");
            }
            else
                JOptionPane.showMessageDialog(addDistrictWindow, "Please fill all the fields");
            //int startingRcptNum = startingNum;
            
            {
                //ending number for current receipt book
                connect seriesConnection = new connect();
                String fetchSubCodeQuery = "select sub_code from state_details_orig where state_code='"+stateCode+"'";
                try
                {
                    String subCode = "Manual";
                    //System.out.println(fetchSubCodeQuery);
                    seriesConnection.rs = seriesConnection.st.executeQuery(fetchSubCodeQuery);
                    while (seriesConnection.rs.next()) {
                        subCode = seriesConnection.rs.getString(1);
                        //System.out.println(subCode);
                    }
                    
                    String existingZoneQuery = "select count(district) from state_details where district = '"+districtName+"'";
                    //System.out.println(existingZoneQuery);
                    seriesConnection.rs = seriesConnection.st.executeQuery(existingZoneQuery);
                    int zoneCount  = 0;
                    while (seriesConnection.rs.next()) {
                        zoneCount = seriesConnection.rs.getInt(1);
                        //System.out.println(zoneCount);
                    }
                    if(zoneCount > 0)
                    {
                        JOptionPane.showMessageDialog(addDistrictWindow, "District already exists");
                        districtText.setText(""); 
                        zoneText.setText(""); 
                        stateNameDropBox.setSelectedIndex(0);
                        return;
                    }
                    
                    existingZoneQuery = "select count(zone) from state_details where zone = '"+zoneName+"'";
                    //System.out.println(existingZoneQuery);
                    seriesConnection.rs = seriesConnection.st.executeQuery(existingZoneQuery);
                    zoneCount  = 0;
                    while (seriesConnection.rs.next()) {
                        zoneCount = seriesConnection.rs.getInt(1);
                        //System.out.println(zoneCount);
                    }
                    
                    if(zoneCount == 0){
                        int confirm = JOptionPane.showConfirmDialog(addDistrictWindow, "Zone does not exist - create zone "+zoneName+" ?");
                        if(confirm == 0) zoneCount++;
                        //System.out.println(zoneCount);
                    }
                    
                    if(zoneCount > 0)
                    {
                        String sqlQuery = "insert into state_details values ( '"+subCode+"', '"+stateCode+"', '"+stateName+"' , '"+zoneName+"', '"+districtName+"')";
                        //System.out.println(sqlQuery);
                        seriesConnection.a = seriesConnection.st.executeUpdate(sqlQuery);
                    }
                    
                       
                    //System.out.println("status :: " + seriesConnection.a + " " +sqlQuery) ;
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Data Already Exists");
                    e.printStackTrace();
                    //System.exit(0);
                }
                seriesConnection.closeAll();
            }
            districtText.setText(""); 
            zoneText.setText(""); 
            stateNameDropBox.setSelectedIndex(0);
        }
        else if(event.getSource() == cancelButton)
        {
            addDistrictWindow.setVisible(false);
            new sams();
            addDistrictWindow.dispose();
            
        }
    }
    
    public static void main(String args[])
    {
        SatSandeshAddDistrictWindow demo = new SatSandeshAddDistrictWindow();        
    }
    
}
