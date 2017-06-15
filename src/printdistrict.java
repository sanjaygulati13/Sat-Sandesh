import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;


public class printdistrict extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new printdistrict();
    }
    
    JLabel district, state, head;
    JButton ok, back, clr;
    TextFieldWithLimit districtt;
    JComboBox stateNameDropDown;
    
    
    public printdistrict()
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
        setTitle("District Details");
        setSize(300,300);
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        
        head=new JLabel("ENTER DETAILS");
        district=new JLabel("District");
        state=new JLabel("State");
        
        districtt=new TextFieldWithLimit(15,15);
        stateNameDropDown = new JComboBox(SamsUtilities.fillStateNameList());
        
        ok=new JButton("OK");
        clr=new JButton("Clear");
        back=new JButton("Back");
        
        
        head.setBounds(80,30,100,20);
        add(head);
        
        district.setBounds(30,70,50,20);
        add(district);
        
        state.setBounds(30,110,50,20);
        add(state);
        
        districtt.setBounds(110,70,150,20);
        add(districtt);
        
        stateNameDropDown.setBounds(110,110,150,20);
        add(stateNameDropDown);
        
        ok.setBounds(30,170,70,25);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        clr.setBounds(120,170,70,25);
        clr.addActionListener(this);
        clr.setMnemonic('C');
        add(clr);
        
        back.setBounds(210,170,70,25);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            String stateName = stateNameDropDown.getSelectedItem().toString();
            String stateCode = SamsUtilities.getStateCodeForStateName(stateName);
            new alldistrict(districtt.getText(),stateCode);
            this.dispose();            
        }
        
        if(ae.getSource()==clr)
        {
            districtt.setText("");
            stateNameDropDown.setSelectedItem("");
        }
        
        
        if(ae.getSource()==back)
        {
            this.dispose();
            new sams();
            
        }
        
    }
}

