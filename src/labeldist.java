import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class labeldist extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new labeldist();
    }
    
    JLabel month, year, lang, dno;
    JButton ok, back, oldOkButton;
    JComboBox monthDropDown, yearDropDown;
    JComboBox langt, dnot;
    
    
    public labeldist()
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
        setTitle("Monthly Labels--Bulk");
        setSize(300,300);
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        SamsUtilities.center(this);
        
        dno=new JLabel("D#");
        month=new JLabel("Month");
        year=new JLabel("Year");
        lang=new JLabel("Language");
        
        dnot=new JComboBox();
        
        try
        {
            connect c13=new connect();
            c13.rs=c13.st.executeQuery("select dno from despcode");
            while(c13.rs.next())
            {
                dnot.addItem(""+c13.rs.getInt(1));
            }
            
            c13.st.close();
            c13.con.close();
            
        }
        catch(Exception e)
        {
            
        }
        
        
        
        monthDropDown = new JComboBox();
        yearDropDown = new JComboBox();
        
        langt=new JComboBox();
        
        monthDropDown = new JComboBox();
        yearDropDown = new JComboBox();
       
        
        for( int month =1; month <= 12 ; month++ )
            monthDropDown.addItem(""+month);
        
        int currYear = SamsUtilities.getCurrentYear()+1;
        for( int year=(currYear) ; year>(currYear-10) ; year--)
        {
            yearDropDown.addItem(""+year);
        }
        
        monthDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
        yearDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
        
        
        ok=new JButton("OK");
        back=new JButton("Back");
        oldOkButton = new JButton("Ok old");
        
        langt.addItem("Hindi");
        langt.addItem("English");
        langt.addItem("Punjabi");
        langt.addItem("Urdu");
        
        dno.setBounds(30,30,40,20);
        add(dno);
        
        month.setBounds(30,70,50,20);
        add(month);
        
        year.setBounds(30,110,50,20);
        add(year);
        
        lang.setBounds(30,150,80,20);
        add(lang);
        
        dnot.setBounds(110,30,100,20);
        add(dnot);
        
        
        monthDropDown.setBounds(110,70,100,20);
        add(monthDropDown);
        
        yearDropDown.setBounds(110,110,100,20);
        add(yearDropDown);
        
        langt.setBounds(110,150,120,20);
        add(langt);
        
        ok.setBounds(30,210,70,20);
        ok.setMnemonic('O');
        ok.addActionListener(this);
        add(ok);
        
        oldOkButton.setBounds(120,210,70,20);
        oldOkButton.setMnemonic('k');
        oldOkButton.addActionListener(this);
        //add(oldOkButton);
        
        back.setBounds(210,210,70,20);
        back.setMnemonic('B');
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==ok)
        {
            new SatSandeshDistributorLabelsList(Integer.parseInt((String)dnot.getSelectedItem()),Integer.parseInt(monthDropDown.getSelectedItem().toString()),Integer.parseInt(yearDropDown.getSelectedItem().toString()), true);
            this.dispose();
        }
        else if(ae.getSource()==oldOkButton)
        {
            new SatSandeshDistributorLabelsList(Integer.parseInt((String)dnot.getSelectedItem()),Integer.parseInt(monthDropDown.getSelectedItem().toString()),Integer.parseInt(yearDropDown.getSelectedItem().toString()), false);
            this.dispose();
        }
        else if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
        }
        
    }
}


