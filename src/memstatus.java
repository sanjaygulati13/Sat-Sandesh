import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class memstatus extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new memstatus();
    }
    
    JLabel month, year, lang;
    JButton ok, ok_new, back;
    JComboBox monthDropDown, yearDropDown;
    JComboBox langt;
    
    
    public memstatus()
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
        setTitle("Member Status");
        setSize(300,300);
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        
        month=new JLabel("Month");
        year=new JLabel("Year");
        lang=new JLabel("Language");
        
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
        
        langt=new JComboBox();
        
        ok=new JButton("OK");
        ok_new = new JButton("<html>OK New</html>");
        back=new JButton("Back");
        
        langt.addItem("Hindi");
        langt.addItem("English");
        langt.addItem("Punjabi");
        langt.addItem("Urdu");
        
        month.setBounds(30,30,50,20);
        add(month);
        
        year.setBounds(30,70,50,20);
        add(year);
        
        lang.setBounds(30,110,80,20);
        add(lang);
        
        monthDropDown.setBounds(110,30,100,20);
        add(monthDropDown);
        
        yearDropDown.setBounds(110,70,100,20);
        add(yearDropDown);
        
        langt.setBounds(110,110,100,20);
        add(langt);
        
        ok.setBounds(30,170,70,45);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        back.setBounds(120,170,70,45);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        
        ok_new.setBounds(210,170,70,45);
        ok_new.addActionListener(this);
        ok_new.setMnemonic('O');
        add(ok_new);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            int month1=Integer.parseInt(monthDropDown.getSelectedItem().toString());
            int year1=Integer.parseInt(yearDropDown.getSelectedItem().toString());
            String lang1=(String)langt.getSelectedItem();
            
            //System.out.println(month1+lang1+year1);
            
            //new join(month1, year1);
            new SatSandeshMemberStatusDetailed(month1,year1,lang1);
            this.dispose();
        }
        
        if(ae.getSource()==ok_new)
        {
            int month1=Integer.parseInt(monthDropDown.getSelectedItem().toString());
            int year1=Integer.parseInt(yearDropDown.getSelectedItem().toString());
            String lang1=(String)langt.getSelectedItem();
            
            //System.out.println(month1+lang1+year1);
            
            //new join(month1, year1);
            new SatSandeshMemberStatusDetailed_New(month1,year1,lang1);
            this.dispose();
        }
        
        
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
            
        }
        
    }
}

