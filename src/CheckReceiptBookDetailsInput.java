import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CheckReceiptBookDetailsInput extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new CheckReceiptBookDetailsInput();
    }
    
    JLabel from, to, rcpt, seriesNameLabel;
    JButton ok, back, clr;
    JTextField fromt, tot;
    JComboBox seriesNameDropDown;
    
    
    public CheckReceiptBookDetailsInput()
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
        setTitle("Receipt Book Details");
        setSize(300,300);
        setResizable(false);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize((screenSize.width*2)/3,(screenSize.height*7)/10);
        Dimension frameSize = this.getSize();
        int x = (screenSize.width - frameSize.width)/2;
        int y = (screenSize.height - frameSize.height)/2;
        this.setLocation(x, y);
        
        
        setLayout(null);
        
        rcpt=new JLabel("Receipt Book");
        from=new JLabel("From");
        to=new JLabel("To");
        seriesNameLabel = new JLabel("<html>Series Name</html>");
        
        fromt=new JTextField(20);
        tot=new JTextField(20);
        seriesNameDropDown = new JComboBox(fillSeriesNameInformation());
        
        ok=new JButton("OK");
        clr=new JButton("Clear");
        back=new JButton("Back");
        
        
        rcpt.setBounds(80,30,100,20);
        add(rcpt);
        
        from.setBounds(30,70,50,20);
        add(from);
        
        to.setBounds(30,110,50,20);
        add(to);
        
        fromt.setBounds(110,70,100,20);
        add(fromt);
        
        tot.setBounds(110,110,100,20);
        add(tot);
        
        seriesNameLabel.setBounds(30,150,50,35);
        seriesNameDropDown.setBounds(110,150,100,20);
        this.add(seriesNameLabel);
        this.add(seriesNameDropDown);
        
        ok.setBounds(30,210,70,25);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);
        
        clr.setBounds(120,210,70,25);
        clr.addActionListener(this);
        clr.setMnemonic('C');
        add(clr);
        
        back.setBounds(210,210,70,25);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            new CheckReceiptBookStatus(Integer.parseInt(fromt.getText()),Integer.parseInt(tot.getText()), (String)seriesNameDropDown.getSelectedItem());
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
    
    static public Object[] fillSeriesNameInformation()
    {
        connect fillSerieConnection = new connect();
        Object[] seriesNameArray = null;
        try
        {
            String query = "select distinct series_name from receipt_book_inventory";
            String countQuery = "select count(distinct series_name) from receipt_book_inventory";
            
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(countQuery);
            fillSerieConnection.rs.next();
            int ArrayCount = fillSerieConnection.rs.getInt(1);
            //System.out.println(ArrayCount+1);
            seriesNameArray = new Object[ArrayCount + 1];
            seriesNameArray[0] = "";
            fillSerieConnection.rs = fillSerieConnection.st.executeQuery(query);
            //CodeChooser.addItem("");
            int i = 1;
            while (fillSerieConnection.rs.next()) {
                seriesNameArray[i] = fillSerieConnection.rs.getString(1);
                i++;
            }
            
            fillSerieConnection.closeAll();
        } catch (Exception exc) {
            //exc.printStackTrace();
            //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
            fillSerieConnection.closeAll();
        }
        return seriesNameArray;
    }
}

