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
    JTextField monthText,yearText;
    JButton b1, b2;
    JComboBox langt;
    
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
        l1=new JLabel("Enter Month");
        add(l1);
        l1.setBounds(100,30,100,20);
        l2=new JLabel("Month");
        add(l2);
        l2.setBounds(50,60,50,20);
        monthText=new JTextField(10);
        add(monthText);
        monthText.setBounds(110,60,50,20);
        monthText.setText(""+SamsUtilities.getCurrentMonth());
        l3=new JLabel("Year");
        add(l3);
        l3.setBounds(50,90,50,20);
        
        l4=new JLabel("Language");
        add(l4);
        l4.setBounds(50,120,50,20);
        
        yearText=new JTextField(10);
        add(yearText);
        yearText.setBounds(110,90,50,20);
        yearText.setText(""+SamsUtilities.getCurrentYear());
        
        langt=new JComboBox();
        langt.addItem("Hindi");
        langt.addItem("English");
        langt.addItem("Punjabi");
        langt.addItem("Urdu");
        
        langt.setBounds(110,120,100,20);
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
            new memsummary((Integer.parseInt(monthText.getText())),(Integer.parseInt(yearText.getText())), (String)langt.getSelectedItem());
            this.dispose();
        }
        if(ae.getSource()==b2)
        {
            this.dispose();
            new sams();
        }
        
    }
    
}
