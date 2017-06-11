import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class remstatus1 extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new remstatus1();
    }
    
    JLabel month, year, lang;
    JButton ok, back;
    JComboBox monthDropDown, yearDropDown;
    
    
    public remstatus1()
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
        //setVisible(true);
        setTitle("Reminder Status");
        setSize(300,200);
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        
        month=new JLabel("Month");
        year=new JLabel("Year");
        
        
        monthDropDown = new JComboBox();
        yearDropDown = new JComboBox();
        
        
        ok=new JButton("OK");
        back=new JButton("Back");
        
        month.setBounds(30,30,50,20);
        add(month);
        
        year.setBounds(30,70,50,20);
        add(year);
        
        monthDropDown = new JComboBox();
        yearDropDown = new JComboBox();
       
        
        for( int month =1; month <= 12 ; month++ )
            monthDropDown.addItem(""+month);
        
        int currYear = SamsUtilities.getCurrentYear();
        for( int year=(currYear) ; year>(currYear-10) ; year--)
        {
            yearDropDown.addItem(""+year);
        }
        
        //montht=new JTextField(20);
        monthDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
        //yeart=new JTextField(40);
        yearDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
        
        monthDropDown.setBounds(110,30,100,20);
        add(monthDropDown);
        
        yearDropDown.setBounds(110,70,100,20);
        add(yearDropDown);
        
        
        ok.setBounds(30,120,70,25);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        back.setBounds(120,120,70,25);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            int month1=Integer.parseInt(monthDropDown.getSelectedItem().toString());
            int year1=Integer.parseInt(yearDropDown.getSelectedItem().toString());
            
            new remstatus(month1,year1);
            this.dispose();
        }
        
        
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
            
        }
        
    }
}

