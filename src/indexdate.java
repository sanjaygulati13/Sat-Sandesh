import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class indexdate extends JFrame implements ActionListener
{
    public static void main(String args[])
    {
        new indexdate();
    }
    
    JLabel date/*, d1, m1, y1*/;
    JButton ok, back, clr, oldOkButton;
    /*TextFieldWithLimit dt1, mt1, yt1;*/
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
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
        /*d1=new JLabel("DD");
        m1=new JLabel("MM");
        y1=new JLabel("YYYY");
        dt1=new TextFieldWithLimit(2,2);
        mt1=new TextFieldWithLimit(2,2);
        yt1=new TextFieldWithLimit(4,4);*/
        
        ok = new JButton("OK");
        oldOkButton = new JButton("OK old");
        clr = new JButton("Clear");
        back = new JButton("Back");
        
        
        date.setBounds(100,30,100,20);
        add(date);
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        
        datePicker.setBounds(30,55,240,45);
        add(datePicker);
        
        /*d1.setBounds(30,70,50,20);
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
        yt1.setText(""+SamsUtilities.getCurrentYear());*/
        
        
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
        
        oldOkButton.setBounds(120,230,70,25);
        oldOkButton.addActionListener(this);
        oldOkButton.setMnemonic('k');
        add(oldOkButton);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==ok)
        {
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int date= selectedDate.getDate();
            int month=selectedDate.getMonth()+1;
            int year=selectedDate.getYear()+1900;
            new SatSandeshIndexRegister(date,month, year, true);
            this.dispose();
            
        }
        if(ae.getSource() == oldOkButton)
        {
            Date selectedDate = (Date) datePicker.getModel().getValue();
            int date= selectedDate.getDate();
            int month=selectedDate.getMonth()+1;
            int year=selectedDate.getYear()+1900;
            new SatSandeshIndexRegister(date,month, year, false);
            this.dispose();
            
        }
        
        
        if(ae.getSource()==clr)
        {
            datePicker.getJFormattedTextField().setText("");
            /*dt1.setText("");
            mt1.setText("");
            yt1.setText("");*/
        }
        
        
        if(ae.getSource()==back)
        {
            new sams();
            this.dispose();
        }
        
    }
}

