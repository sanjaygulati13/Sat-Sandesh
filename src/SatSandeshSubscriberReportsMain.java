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
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;


public class SatSandeshSubscriberReportsMain extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new SatSandeshSubscriberReportsMain();
    }
    
    MigLayout mLayout= new MigLayout( "insets 30");
    JButton okButton, backButton;
    JComboBox subNumberDropDown;
    
    JRadioButton subCodeRadioButton, districtRadioButton,distributorRadioButton, stateRadioButton;
    ButtonGroup typeGroup;
    
    JComboBox stateNameDropDown, distributorCodeDropDown, stateNameDropDown_district;
    TextFieldWithLimit districtNameTextField;
    
    int x,y;
    int mode = 0;
    
    public SatSandeshSubscriberReportsMain()
    {
        
        try
        {
            
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
        }
        catch (Exception cnf)
        {
            System.out.println(cnf);
        }
        
        this.setLayout(mLayout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        setTitle("Subscriber Reports");
        //setSize(210,200);
        //setResizable(false);
        //setLocation(10,10);
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((screenSize.width)*6/11,(screenSize.height*1)/3);
        Dimension frameSize = this.getSize();
        //x = (screenSize.width - frameSize.width)  / 2;
        //y = (screenSize.height - frameSize.height) / 3;
        //this.setLocation(x, y);
        SamsUtilities.center(this);
        
        subCodeRadioButton = new JRadioButton("Sub Code");
        subCodeRadioButton.addActionListener(this);
        
        districtRadioButton = new JRadioButton("District");
        districtRadioButton.addActionListener(this);
        
        distributorRadioButton = new JRadioButton("Distributor #");
        distributorRadioButton.addActionListener(this);
        
        stateRadioButton = new JRadioButton("State");
        stateRadioButton.addActionListener(this);
        
        typeGroup  = new ButtonGroup();
        typeGroup.add(subCodeRadioButton);
        typeGroup.add(districtRadioButton);
        typeGroup.add(distributorRadioButton);
        typeGroup.add(stateRadioButton);
        
        
        stateNameDropDown = new JComboBox(SamsUtilities.fillStateNameList());
        
        districtNameTextField=new TextFieldWithLimit(20,20);
        stateNameDropDown_district = new JComboBox(SamsUtilities.fillStateNameList());
        
        subNumberDropDown=new JComboBox(SamsUtilities.getSubCodes());
        
        distributorCodeDropDown=new JComboBox(SamsUtilities.getDistributorCodes());
        
        okButton=new JButton(SamsUtilities.getOkButtonName());
        backButton=new JButton(SamsUtilities.getCancelButtonName());
        
        subNumberDropDown.setBounds(90,60,100,20);
        
        
        this.add(subCodeRadioButton);
        this.add(subNumberDropDown,"wrap");
        
        this.add(distributorRadioButton);
        this.add(distributorCodeDropDown,"wrap");
        
        this.add(districtRadioButton);
        this.add(districtNameTextField);
        this.add(stateNameDropDown_district,"wrap");
        
        this.add(stateRadioButton);
        this.add(stateNameDropDown,"wrap");
        
        //okButton.setBounds(30,90,70,25);
        okButton.addActionListener(this);
        okButton.setMnemonic('O');
        add(okButton);
        
        
        //backButton.setBounds(110,90,70,25);
        backButton.addActionListener(this);
        backButton.setMnemonic('B');
        add(backButton);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == subCodeRadioButton)
        {
            mode = 1;
        }
        else if(ae.getSource() == distributorRadioButton)
        {
            mode = 2;
        }
        else if(ae.getSource() == districtRadioButton)
        {
            mode = 3;
        }
        else if(ae.getSource() == stateRadioButton)
        {
            mode = 4;
        }
        
        if(ae.getSource()==okButton)
        {
            if(mode == 1){
                String subNumber = subNumberDropDown.getSelectedItem().toString();
                if(subNumber.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"Please select sub code", "Please select sub code", JOptionPane.ERROR_MESSAGE);
                    subNumberDropDown.requestFocus();
                    return;
                }
                new allsubno(subNumber);
                this.dispose();
            }
            else if(mode == 2){
                
                int dNumber = Integer.parseInt(distributorCodeDropDown.getSelectedItem().toString());
                if(dNumber == 0){
                    JOptionPane.showMessageDialog(this,"Please select D#", "Please select D#", JOptionPane.ERROR_MESSAGE);
                    distributorCodeDropDown.requestFocus();
                    return;
                }
                
                new alldno(dNumber);
                this.dispose();
            }
            else if(mode == 3){
                String districtName = districtNameTextField.getText();
                if(districtName.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"Please enter district", "Please enter district", JOptionPane.ERROR_MESSAGE);
                    districtNameTextField.requestFocus();
                    return;
                }
                String stateName = stateNameDropDown_district.getSelectedItem().toString();
                if(stateName.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"Please select state", "Please select state", JOptionPane.ERROR_MESSAGE);
                    stateNameDropDown_district.requestFocus();
                    return;
                }
                String stateCode = SamsUtilities.getStateCodeForStateName(stateName);
                new alldistrict(districtName,stateCode);
                this.dispose();
                
            }
            else if(mode == 4){
                String stateName = stateNameDropDown.getSelectedItem().toString();
                if(stateName.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"Please select state", "Please select state", JOptionPane.ERROR_MESSAGE);
                    stateNameDropDown.requestFocus();
                    return;
                }
                String stateCode = SamsUtilities.getStateCodeForStateName(stateName);
                //System.out.println(stateName + " " + stateCode);
                new allstate(stateCode);
                this.dispose();
                
            }
            else{
                JOptionPane.showMessageDialog(this,"Please select report type", "Please select report type", JOptionPane.ERROR_MESSAGE);
                subCodeRadioButton.requestFocus();
                return;
            }   
        }
        
        if(ae.getSource()==backButton)
        {
            this.dispose();
            new sams();
            
        }
    }
}

