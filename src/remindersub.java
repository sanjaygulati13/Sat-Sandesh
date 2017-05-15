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
    JTextField monthft, yearft, monthtt, yeartt;
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
        
        monthf=new JLabel("Month From");
        
        montht=new JLabel("Month To");
        yeart=new JLabel("Year");
        
        lang=new JLabel("Language");
        
        
        monthft=new JTextField(20);
        monthtt=new JTextField(20);
        yeartt=new JTextField(40);
        langt=new JComboBox();
        
        ok=new JButton("Label Format");
        clr=new JButton("Reset");
        back=new JButton("Back");
        listFormatButton = new JButton("List Format");
        
        langt.addItem("Hindi");
        langt.addItem("English");
        langt.addItem("Punjabi");
        langt.addItem("Urdu");
        
        monthf.setBounds(30,70,70,20);
        add(monthf);
        
        montht.setBounds(30,110,70,20);
        add(montht);
        
        yeart.setBounds(30,150,70,20);
        add(yeart);
        
        lang.setBounds(30,190,80,20);
        add(lang);
        
        monthft.setBounds(130,70,50,20);
        add(monthft);
        
        
        monthtt.setBounds(130,110,50,20);
        add(monthtt);
        
        yeartt.setBounds(130,150,50,20);
        add(yeartt);
        
        langt.setBounds(130,190,100,20);
        add(langt);
        
        ok.setBounds(30,230,80,25);
        add(ok);
        ok.addActionListener(this);
        ok.setMnemonic('l');
        
        clr.setBounds(120,230,70,25);
        add(clr);
        clr.addActionListener(this);
        clr.setMnemonic('r');
        
        back.setBounds(210,230,70,25);
        add(back);
        back.addActionListener(this);
        back.setMnemonic('b');
        
        listFormatButton.setBounds(300,230,70,25);
        add(listFormatButton);
        listFormatButton.addActionListener(this);
        listFormatButton.setMnemonic('i');
        
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==ok)
        {
            new reminder(Integer.parseInt(monthft.getText()),Integer.parseInt(yeartt.getText()),Integer.parseInt(monthtt.getText()),Integer.parseInt(yeartt.getText()));
            this.dispose();
        }
        
        if(ae.getSource()==listFormatButton)
        {
            new SatSandeshDespatchRemindersList(Integer.parseInt(monthft.getText()),Integer.parseInt(yeartt.getText()),Integer.parseInt(monthtt.getText()),Integer.parseInt(yeartt.getText()));
            this.dispose();
        }
        
        if(ae.getSource()==clr)
        {
            monthft.setText("");
            monthtt.setText("");
            yearft.setText("");
            yeartt.setText("");
        }
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
            
        }
        
    }
}

