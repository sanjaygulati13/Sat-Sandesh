
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sanjay
 */
public class SatSandeshSubscriptionReport extends JFrame implements ActionListener {

    JLabel monthLabel;//, l2;
    TextFieldWithLimit monthText, yearText;
    JTable subscriptionDetailsTable;
    JScrollPane detailsScrollPane;
    Container con;
    JButton showButton, closeButton, printButton;
    Object col[] = {"Month", "New", "Renew"};
    JComboBox subt;
    String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    
    JRadioButton dateRadioButton, yearRadioButton,financialYearRadioButton;
    ButtonGroup typeGroup;

    public static void main(String args[]) {
        new SatSandeshSubscriptionReport();
    }
    MigLayout mLayout= new MigLayout( "insets 30");


    SatSandeshSubscriptionReport() {
        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            System.out.println(cnf);
        }
        con = getContentPane();
        setSize(950, 600);
        setLocation(200, 80);
        //setVisible(true);
        con.setLayout(mLayout);
        setTitle("Subscription Report");
        //con.setBackground(Color.cyan);
        monthLabel = new JLabel("Enter Month");
        //monthLabel.setBounds(170, 40, 100, 20);
        

        monthText = new TextFieldWithLimit(2, 2);
        //t2.setBounds(300, 40, 30, 20);
        

        yearText = new TextFieldWithLimit(4, 4);
        //t3.setBounds(290, 40, 40, 20);
        

        showButton = new JButton("Show");
        //b1.setBounds(380, 40, 80, 25);
        showButton.setMnemonic('S');
        showButton.addActionListener(this);

        closeButton = new JButton("Close");
        closeButton.setMnemonic('C');
        //b3.setBounds(480, 530, 90, 25);
        closeButton.addActionListener(this);

        printButton = new JButton("Print");
        printButton.setMnemonic('P');
        //b4.setBounds(200, 530, 90, 25);
        printButton.addActionListener(this);
        
        dateRadioButton = new JRadioButton("Date"); 
        dateRadioButton.addActionListener(this);
        yearRadioButton = new JRadioButton("Year (Jan-Dec)");
        yearRadioButton.addActionListener(this);
        financialYearRadioButton = new JRadioButton("Financial Year (Apr-Mar)");
        financialYearRadioButton.addActionListener(this);
        
        typeGroup  = new ButtonGroup();
        typeGroup.add(dateRadioButton);
        typeGroup.add(yearRadioButton);
        typeGroup.add(financialYearRadioButton);
        
        //con.add(typeGroup);
        con.add(dateRadioButton);
        con.add(yearRadioButton);
        con.add(financialYearRadioButton,"gapright 100!");
        con.add(monthLabel);
        con.add(monthText);
        con.add(yearText);
        con.add(showButton);
        con.add(closeButton);
        con.add(printButton);
        printButton.setEnabled(false);
        
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    int dataSelectionType;

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == dateRadioButton){
            monthText.setEnabled(true);
            dataSelectionType = 1;
        }
        if(ae.getSource() == yearRadioButton){
            monthText.setText("");
            monthText.setEnabled(false);
            dataSelectionType = 2;
        }
        if(ae.getSource() == financialYearRadioButton){
            monthText.setText("");
            monthText.setEnabled(false);
            dataSelectionType = 3;
        }
        if (ae.getSource() == showButton) {
            showButton.setEnabled(false);
            int i = 0;
            String[] dates;
            try {

                connect c1 = new connect();
                //String sqlQuery, countQuery;
                String monthStr, yearStr;
                int month = 0, year = 0;
                monthStr = monthText.getText();
                yearStr = yearText.getText();
                if(monthStr.isEmpty() == false)month = Integer.parseInt(monthStr);
                if(yearStr.isEmpty() == false)year = Integer.parseInt(yearStr);
                if(dataSelectionType == 1)
                {
                    i = 1;
                    dates = new String[1];
                    dates[0] = months[month - 1] ;     
                }
                else if(dataSelectionType == 2)
                {
                    i = 12;
                    dates  = new String[12];
                    System.arraycopy(months, 0, dates, 0, 12);
                }
                else if(dataSelectionType == 3)
                {
                    i = 12;
                    dates  = new String[12];
                    for(int count = 0; count < 12; count++ )
                        dates[count] = months[(count+3)%12];
                }
                else
                    dates  = new String[0];
                    
                //sqlQuery = "select distinct datd from payment where datm=" + m1 + " and daty=" + y1 + " order by datd, datm, daty";
                //c1.rs = c1.st.executeQuery(countQuery);
                //c1.rs.next();
                //i = c1.rs.getInt(1);
                //c1.rs.close();

                //System.out.println(i);
                if (i == 0) {
                    JOptionPane.showMessageDialog(null, "No Records Found", "No Records", JOptionPane.ERROR_MESSAGE);
                } else {

                    Object data[][] = new Object[i + 1][4];

                    //j = 0;
                    int total = 0;
                    int total_new = 0;
                    int total_renew = 0;

                    connect c3 = new connect();
                    for (int z = 0; z < i; z++) {

                        int total_absolute = 0;
                        int total_temp = 0;
                        String newSqlQuery="";
                        String renewSqlQuery="";
                        if(dataSelectionType == 1)
                        {
                            newSqlQuery = "select count(asn) from payment where datm = "+month+" and daty = "+year+" and subscription_type = 'New'";
                            renewSqlQuery = "select count(asn) from payment where datm = "+month+" and daty = "+year+" and subscription_type = 'Renew'";
                        }
                        else if(dataSelectionType == 2)
                        {
                            newSqlQuery = "select count(asn) from payment where datm = "+(z+1)+" and daty = "+year+" and subscription_type = 'New'";
                            renewSqlQuery = "select count(asn) from payment where datm = "+(z+1)+" and daty = "+year+" and subscription_type = 'Renew'";
                        }
                        else if(dataSelectionType == 3)
                        {
                            int month_temp = ((z+3)%12)+1;
                            if(month_temp > 3)
                            {
                                newSqlQuery = "select count(asn) from payment where datm = "+month_temp+" and daty = "+year+" and subscription_type = 'New'";
                                renewSqlQuery = "select count(asn) from payment where datm = "+month_temp+" and daty = "+year+" and subscription_type = 'Renew'";
                            }
                            else
                            {
                                newSqlQuery = "select count(asn) from payment where datm = "+month_temp+" and daty = "+(year+1)+" and subscription_type = 'New'";
                                renewSqlQuery = "select count(asn) from payment where datm = "+month_temp+" and daty = "+(year+1)+" and subscription_type = 'Renew'";
                            }
                           
                        }
                        //System.out.println(newSqlQuery);
                        //System.out.println(renewSqlQuery);
                        //System.out.println("======================");
                        data[z][0] = dates[z];
                        c3.rs = c3.st.executeQuery(newSqlQuery);
                        while (c3.rs.next()) {
                            int newCount  = c3.rs.getInt(1);
                            data[z][1] = newCount;
                            total_new += newCount;
                        }
                        
                        //total_new += total_absolute;
                        //total_temp += total_absolute;
                        //total_absolute = 0;

                        c3.rs = c3.st.executeQuery(renewSqlQuery);
                        while (c3.rs.next()) {
                            int renewCount =  c3.rs.getInt(1);
                            data[z][2] = renewCount;
                            total_renew += renewCount;
                        }
                        
                        //total_renew += total_absolute;
                        //total_temp += total_absolute;
                        //total_absolute = 0;

                        data[z][3] = total_temp;

                        total += total_temp;
                    }
                    data[i][0] = "TOTAL";
                    data[i][1] = total_new;
                    data[i][2] = total_renew;
                    data[i][3] = total;

                    subscriptionDetailsTable = new JTable(data, col);
                    detailsScrollPane = new JScrollPane(subscriptionDetailsTable);
                    detailsScrollPane.setBounds(20, 100, 845, 410);
                    con.add(detailsScrollPane);
                    //con.add(closeButton);
                    //con.add(printButton);
                    printButton.setEnabled(true);
                }
            } catch (Exception e) {
                System.out.println("exception" + e);
                e.printStackTrace();
            }
        }
        /*if(ae.getSource()==b2)
         {
         new modify(Integer.parseInt(monthText.getText()));
         this.dispose();
         }*/
        if (ae.getSource() == closeButton) {
            this.setVisible(false);
            new sams();
            this.dispose();
        }

        if (ae.getSource() == printButton) {

            try {
                MessageFormat headerFormat = new MessageFormat("Sat Sandesh Subscription Report"); // \t (Page {0})");
                MessageFormat footerFormat = new MessageFormat("- Page {0} -");
                subscriptionDetailsTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
                System.err.println("Error printing: " + pe.getMessage());
            }
        }

    }
    
 
}
