import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class remindersub extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new remindersub();
    }
    
    JLabel monthf, yearf, montht, yeart, lang;
    JButton ok, back, clr, listFormatButton;
    JButton ok_old, listFormatButton_old;
    JComboBox monthFromDropDown, yearFromDropDown, monthToDropDown, yearToDropDown;
    JComboBox langt;
    
    
    public remindersub()
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
        setTitle("Reminder--Sub No");
        setSize(400,400);
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        
        monthf=new JLabel("<html>Month From</html>");
        
        montht=new JLabel("Month To");
        yeart=new JLabel("Year");
        
        lang=new JLabel("Language");
        
        
        monthFromDropDown=new JComboBox();
        monthToDropDown=new JComboBox();
        yearFromDropDown = new JComboBox();
        yearToDropDown=new JComboBox();
        langt=new JComboBox();
        
        ok=new JButton("<html>Label Format</html>");
        ok_old=new JButton("<html>Label Format (Old)</html>");
        clr=new JButton("Reset");
        back=new JButton("Back");
        listFormatButton = new JButton("<html>List Format</html>");
        listFormatButton_old = new JButton("<html>List Format (Old)</html>");
        
        langt.addItem("Hindi");
        langt.addItem("English");
        langt.addItem("Punjabi");
        langt.addItem("Urdu");
        
        monthf.setBounds(30,70,90,20);
        add(monthf);
        
        montht.setBounds(30,110,70,20);
        add(montht);
        
        yeart.setBounds(30,150,70,20);
        add(yeart);
        
        lang.setBounds(30,190,80,20);
        add(lang);
        
        {
            for( int month =1; month <= 12 ; month++ ){
                monthFromDropDown.addItem(""+month);
                monthToDropDown.addItem(""+month);
            }
            
            int currYear = SamsUtilities.getCurrentYear();
            for( int year=(currYear) ; year>(currYear-10) ; year--)
            {
                yearFromDropDown.addItem(""+year);
                yearToDropDown.addItem(""+year);
            }
            monthFromDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
            monthToDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
            yearFromDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
            yearToDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
        }
        
        monthFromDropDown.setBounds(130,70,100,20);
        add(monthFromDropDown);
        
        
        monthToDropDown.setBounds(130,110,100,20);
        add(monthToDropDown);
        
        yearToDropDown.setBounds(130,150,100,20);
        add(yearToDropDown);
        
        langt.setBounds(130,190,100,20);
        add(langt);
        
        ok.setBounds(30,230,90,45);
        add(ok);
        ok.addActionListener(this);
        ok.setMnemonic('l');
        
        clr.setBounds(120,230,70,45);
        add(clr);
        clr.addActionListener(this);
        clr.setMnemonic('r');
        
        back.setBounds(210,230,70,45);
        add(back);
        back.addActionListener(this);
        back.setMnemonic('b');
        
        listFormatButton.setBounds(300,230,90,45);
        add(listFormatButton);
        listFormatButton.addActionListener(this);
        listFormatButton.setMnemonic('i');
        
        ok_old.setBounds(30,280,90,60);
        //add(ok_old);
        ok_old.addActionListener(this);
        ok_old.setMnemonic('l');
        
        listFormatButton_old.setBounds(300,280,90,60);
        //add(listFormatButton_old);
        listFormatButton_old.addActionListener(this);
        listFormatButton_old.setMnemonic('i');
        
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==ok_old)
        {
            new SatSandeshDespatchRemindersLabel_Old(Integer.parseInt(monthFromDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()),Integer.parseInt(monthToDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()));
            this.dispose();
        }
        
        if(ae.getSource()==ok)
        {
            new SatSandeshDespatchRemindersLabel(Integer.parseInt(monthFromDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()),Integer.parseInt(monthToDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()));
            this.dispose();
        }
        
        if(ae.getSource()==listFormatButton_old)
        {
            new SatSandeshDespatchRemindersList_Old(Integer.parseInt(monthFromDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()),Integer.parseInt(monthToDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()));
            this.dispose();
        }
        
        if(ae.getSource()==listFormatButton)
        {
            new SatSandeshDespatchRemindersList(Integer.parseInt(monthFromDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()),Integer.parseInt(monthToDropDown.getSelectedItem().toString()),Integer.parseInt(yearToDropDown.getSelectedItem().toString()));
            this.dispose();
        }
        
        if(ae.getSource()==clr)
        {
            monthFromDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
            monthToDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
            yearFromDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
            yearToDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
        }
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
            
        }
        
    }
}

