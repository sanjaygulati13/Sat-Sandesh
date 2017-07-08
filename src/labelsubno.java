import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class labelsubno extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new labelsubno();
    }
    
    JLabel month, year, lang;
    JButton ok, back, stickerLabelButton;
    JButton ok_old, stickerLabelButton_old;
    JComboBox monthDropDown, yearDropDown;
    JComboBox langt;
    
    
    public labelsubno()
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
        setTitle("Monthly Labels--Sub No");
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
        {
            yearDropDown.addItem(""+year);
        }
        
        //montht=new JTextField(20);
        monthDropDown.setSelectedItem(""+SamsUtilities.getCurrentMonth());
        //yeart=new JTextField(40);
        yearDropDown.setSelectedItem(""+SamsUtilities.getCurrentYear());
        langt=new JComboBox();
        
        
        ok=new JButton("Labels");
        stickerLabelButton = new JButton("Sticker");
        
        ok_old =new JButton("<html>Labels old</html>");
        stickerLabelButton_old  = new JButton("<html>Sticker old</html>");
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
        
        ok.setBounds(30,170,70,40);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        stickerLabelButton.setBounds(110,170,70,40);
        stickerLabelButton.addActionListener(this);
        stickerLabelButton.setMnemonic('O');
        add(stickerLabelButton);
        
        back.setBounds(190,170,70,40);
        back.setMnemonic('B');
        back.addActionListener(this);
        add(back);
        
        ok_old.setBounds(30,220,70,40);
        ok_old.addActionListener(this);
        ok_old.setMnemonic('O');
        add(ok_old);
        
        stickerLabelButton_old.setBounds(110,220,70,40);
        stickerLabelButton_old.addActionListener(this);
        stickerLabelButton_old.setMnemonic('O');
        add(stickerLabelButton_old);
        
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            //                    System.out.println(""+Integer.parseInt(montht.getText()));
            //                    System.out.println(""+Integer.parseInt(yeart.getText()));
            //                    System.out.println(""+(String)langt.getSelectedItem());
            new SatSandeshLabelPrint(Integer.parseInt(monthDropDown.getSelectedItem().toString()),Integer.parseInt(yearDropDown.getSelectedItem().toString()),(String)langt.getSelectedItem());
            this.dispose();
        }
        
        if(ae.getSource()==ok_old)
        {
            //                    System.out.println(""+Integer.parseInt(montht.getText()));
            //                    System.out.println(""+Integer.parseInt(yeart.getText()));
            //                    System.out.println(""+(String)langt.getSelectedItem());
            new label(Integer.parseInt(monthDropDown.getSelectedItem().toString()),Integer.parseInt(yearDropDown.getSelectedItem().toString()),(String)langt.getSelectedItem());
            this.dispose();
        }
        
        if(ae.getSource()== stickerLabelButton)
        {
            //                    System.out.println(""+Integer.parseInt(montht.getText()));
            //                    System.out.println(""+Integer.parseInt(yeart.getText()));
            //                    System.out.println(""+(String)langt.getSelectedItem());
            new SatSandeshStickerLabelsPrint(Integer.parseInt(monthDropDown.getSelectedItem().toString()),Integer.parseInt(yearDropDown.getSelectedItem().toString()),(String)langt.getSelectedItem());
            this.dispose();
        }
        
        if(ae.getSource()== stickerLabelButton_old)
        {
            //                    System.out.println(""+Integer.parseInt(montht.getText()));
            //                    System.out.println(""+Integer.parseInt(yeart.getText()));
            //                    System.out.println(""+(String)langt.getSelectedItem());
            new stickerLabels(Integer.parseInt(monthDropDown.getSelectedItem().toString()),Integer.parseInt(yearDropDown.getSelectedItem().toString()),(String)langt.getSelectedItem());
            this.dispose();
        }
        
        if(ae.getSource()==back)
        {
            this.dispose();
            new sams();
        }
    }
}

