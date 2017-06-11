import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class memsum extends JFrame implements ActionListener
{
    
    public static void main(String args[])
    {
        new memsum();
    }
    
    JLabel l1,l2,l3, l4;
    JComboBox monthDropDown, yearDropDown;
    JButton b1, b2;
    JComboBox langt;
    
    protected void center() 
    {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setSize(screenSize.width-300,screenSize.height-300);
        Dimension frameSize = getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        
        setLocation(x, y);
    }
    
    public memsum()
    {
        try
        {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            System.out.println(cnf);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(300,250);
        setLocation(10,10);
        setLayout(null);
        setTitle("Member Status Summary");
        this.center();
        /*l1=new JLabel("Enter Month");
        add(l1);
        l1.setBounds(30,30,100,20);*/
        
        l2=new JLabel("Month");
        add(l2);
        l2.setBounds(30,30,100,20);
        
        monthDropDown = new JComboBox();
        yearDropDown = new JComboBox();
        
        for( int month =1; month <= 12 ; month++ )
            monthDropDown.addItem(""+month);
        
        int currYear = SamsUtilities.getCurrentYear();
        for( int year=(currYear) ; year>(currYear-10) ; year--)
            yearDropDown.addItem(""+year);
        
        
        //montht=new JTextField(20);
        monthDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
        //yeart=new JTextField(40);
        yearDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
        
        l3=new JLabel("Year");
        add(l3);
        l3.setBounds(30,60,100,20);
        
        l4=new JLabel("Language");
        add(l4);
        l4.setBounds(30,90,100,20);
        
        add(monthDropDown);
        add(yearDropDown);
        
        monthDropDown.setBounds(150,30,100,20);
        yearDropDown.setBounds(150,60,100,20);
        
        langt=new JComboBox();
        langt.addItem("Hindi");
        langt.addItem("English");
        langt.addItem("Punjabi");
        langt.addItem("Urdu");
        
        langt.setBounds(150,90,100,20);
        add(langt);
        
        b1=new JButton("Save/Ok");
        add(b1);
        b1.setBounds(40,150,80,25);
        b1.addActionListener(this);
        b1.setMnemonic('s');
        
        b2=new JButton("Back");
        add(b2);
        b2.setBounds(130,150,80,25);
        b2.addActionListener(this);
        b2.setMnemonic('b');
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==b1)
        {
            new memsummary((Integer.parseInt(monthDropDown.getSelectedItem().toString())),(Integer.parseInt(yearDropDown.getSelectedItem().toString())), (String)langt.getSelectedItem());
            this.dispose();
        }
        if(ae.getSource()==b2)
        {
            this.dispose();
            new sams();
        }
        
    }
    
}
