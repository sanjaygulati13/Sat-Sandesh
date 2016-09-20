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
    JButton ok, back;
    JTextField montht, yeart;
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
        
        
        
        
        montht=new JTextField(20);
        montht.setText(""+SamsUtilities.getCurrentMonth());
        yeart=new JTextField(40);
        yeart.setText(""+SamsUtilities.getCurrentYear());
        langt=new JComboBox();
        
        ok=new JButton("OK");
        back=new JButton("Back");
        
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
        
        dnot.setBounds(110,30,80,20);
        add(dnot);
        
        
        montht.setBounds(110,70,80,20);
        add(montht);
        
        yeart.setBounds(110,110,80,20);
        add(yeart);
        
        langt.setBounds(110,150,120,20);
        add(langt);
        
        ok.setBounds(30,210,70,20);
        ok.setMnemonic('O');
        ok.addActionListener(this);
        add(ok);
        
        back.setBounds(120,210,70,20);
        back.setMnemonic('B');
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==ok)
        {
            new labeldno(Integer.parseInt((String)dnot.getSelectedItem()),Integer.parseInt(montht.getText()),Integer.parseInt(yeart.getText()));
            this.dispose();
        }
        
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
        }
        
    }
}


