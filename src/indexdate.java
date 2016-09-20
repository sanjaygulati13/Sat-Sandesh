import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class indexdate extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new indexdate();
    }
    
    JLabel date, d1, m1, y1;
    JButton ok, back, clr;
    TextFieldWithLimit dt1, mt1, yt1;
    
    
    public indexdate()
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
        setTitle("INDEX REGISTER");
        setSize(300,300);
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        
        date=new JLabel("Enter Date");
        d1=new JLabel("DD");
        m1=new JLabel("MM");
        y1=new JLabel("YYYY");
        dt1=new TextFieldWithLimit(2,2);
        mt1=new TextFieldWithLimit(2,2);
        yt1=new TextFieldWithLimit(4,4);
        
        ok=new JButton("OK");
        clr=new JButton("Clear");
        back=new JButton("Back");
        
        
        date.setBounds(80,30,100,20);
        add(date);
        
        d1.setBounds(30,70,50,20);
        add(d1);
        
        m1.setBounds(30,110,50,20);
        add(m1);
        
        y1.setBounds(30,150,50,20);
        add(y1);
        
        dt1.setBounds(110,70,100,20);
        add(dt1);
        dt1.setText(""+SamsUtilities.getCurrentDate());
        
        mt1.setBounds(110,110,100,20);
        add(mt1);
        mt1.setText(""+SamsUtilities.getCurrentMonth());
        
        yt1.setBounds(110,150,100,20);
        add(yt1);
        yt1.setText(""+SamsUtilities.getCurrentYear());
        
        
        ok.setBounds(30,190,70,25);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        clr.setBounds(120,190,70,25);
        clr.addActionListener(this);
        clr.setMnemonic('C');
        add(clr);
        
        back.setBounds(210,190,70,25);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            new SatSandeshIndexRegister(Integer.parseInt(dt1.getText()),Integer.parseInt(mt1.getText()),Integer.parseInt(yt1.getText()));
            this.dispose();
            
        }
        
        
        if(ae.getSource()==clr)
        {
            dt1.setText("");
            mt1.setText("");
            yt1.setText("");
        }
        
        
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
        }
        
    }
}

