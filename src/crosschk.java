import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class crosschk extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new crosschk();
    }
    
    JLabel from, to, rcpt, seriesLabel;
    JButton ok, back, clr;
    JComboBox seriesDropDown;
    TextFieldWithLimit fromt, tot;
    
    
    public crosschk()
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
        setTitle("Cross Checking Report");
        setSize(300,300);
        setResizable(false);
        SamsUtilities.center(this);
        //setLocation(10,10);
        setLayout(null);
        
        
        rcpt=new JLabel("Receipt No");
        from=new JLabel("From");
        to=new JLabel("To");
        
        fromt=new TextFieldWithLimit(5,5);
        tot=new TextFieldWithLimit(5,5);
        
        ok=new JButton("OK");
        clr=new JButton("Clear");
        back=new JButton("Back");
        
        
        rcpt.setBounds(80,20,100,20);
        add(rcpt);
        
        
        seriesLabel = new JLabel("Series");
        seriesLabel.setBounds(30,70,80,20);
        this.add(seriesLabel);
        
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        seriesDropDown.setBounds(110,70,100,20);
        this.add(seriesDropDown);
        
        from.setBounds(30,110,50,20);
        add(from);
        
        to.setBounds(30,140,50,20);
        add(to);
        
        fromt.setBounds(110,110,100,20);
        add(fromt);
        
        tot.setBounds(110,140,100,20);
        add(tot);
        
        ok.setBounds(30,190,70,20);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        clr.setBounds(120,190,70,20);
        clr.addActionListener(this);
        clr.setMnemonic('C');
        add(clr);
        
        back.setBounds(210,190,70,20);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            new labelcross((String)seriesDropDown.getSelectedItem(),Integer.parseInt(fromt.getText()),Integer.parseInt(tot.getText()));
            this.dispose();
            
        }
        
        
        if(ae.getSource()==clr)
        {
            fromt.setText("");
            tot.setText("");
        }
        
        
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
        }
        
    }
}

