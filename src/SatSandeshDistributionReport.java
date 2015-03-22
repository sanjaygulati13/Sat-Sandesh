
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import net.miginfocom.swing.MigLayout;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjay
 */
public class SatSandeshDistributionReport implements ActionListener{
//Data Type declarations
    JFrame satSandeshDistributionReportWindow;
    JLabel languageLabel, issueYearLabel, stallLabel, reportYearLabel, monthLabel;
    
    JTable reportTable;
    JScrollPane scrollPane;
    
    TextFieldWithLimit monthText;
    
    JComboBox yearDropDown, stallDropDown, languageDropDown, reportYearDropDown;
    JButton okButton, cancelButton, printButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object [] stalls = {"Main Store","Kirpal Bagh", "Kirpal Ashram","Other"};
    
    Object col[]={"Month","By Hand",
                            "By Hand (Bulk)",
                            "By Post",
                            "By Post (Bulk)",
                            "Cash",
                            "Grievances",
                            "Complimentary","Binding", "Total"};
    
    String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    
    String language[] = {
                        "",
                        "Hindi",
                        "English",
                        "Urdu",
                        "Punjabi"
    };
    
    public static void main(String args[])
    {
        new SatSandeshDistributionReport();
    }
    
    public SatSandeshDistributionReport()
    {
        //setting environment for the Frame satSandeshDistributionReportWindow
        satSandeshDistributionReportWindow = new JFrame("Sat Sandesh Distrbiution Report");
        satSandeshDistributionReportWindow.setLayout(mLayout);
        satSandeshDistributionReportWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshDistributionReportWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
                );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshDistributionReportWindow.setSize(900,(screenSize.height*4)/5);
        Dimension frameSize = satSandeshDistributionReportWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        satSandeshDistributionReportWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshDistributionReportWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
            UIManager.put("Table.gridColor", new ColorUIResource(Color.GRAY));
        }
        catch (Exception cnf)
        {
            // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        //stallLabel = new JLabel("<HTML>Stall</HTML>");
        issueYearLabel = new JLabel("<HTML>Year</HTML>");
        languageLabel = new JLabel("<HTML>Language</HTML>");
        //stallLabel = new JLabel("<HTML>Stall</HTML>");
        reportYearLabel = new JLabel("<HTML>Report year</HTML>");
        monthLabel  = new JLabel("<HTML>Month</HTML>");
        
        monthText = new TextFieldWithLimit(2, 2);
        
        
        
        yearDropDown = new JComboBox(fillIssueYearInformation());        
        reportYearDropDown = new JComboBox(fillIssueYearInformation()); 
        //stallDropDown = new JComboBox(stalls);
        languageDropDown = new JComboBox(language);
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Back");
        cancelButton.setMnemonic('b');
        cancelButton.addActionListener(this);
        
        printButton = new JButton("Print");
        printButton.setMnemonic('p');
        printButton.addActionListener(this);
        
        
        satSandeshDistributionReportWindow.add(issueYearLabel," span 1");
        satSandeshDistributionReportWindow.add(yearDropDown, "span 1, w 100!");        
        satSandeshDistributionReportWindow.add(monthLabel, "gapleft 50");
        satSandeshDistributionReportWindow.add(monthText, "span 1, w :90");
        //satSandeshDistributionReportWindow.add(stallLabel);
        //satSandeshDistributionReportWindow.add(stallDropDown, "span 3, w 150!");
        satSandeshDistributionReportWindow.add(languageLabel," gapleft 50, span 1");
        satSandeshDistributionReportWindow.add(languageDropDown,"span 1, w :90:");
        satSandeshDistributionReportWindow.add(reportYearLabel,"gapleft 50");
        satSandeshDistributionReportWindow.add(reportYearDropDown, "span 1, w :90:, wrap 10px");
        
        satSandeshDistributionReportWindow.add(okButton, " w :90:, span 2");
        satSandeshDistributionReportWindow.add(printButton,"gapleft 80, w :90:, span 2");
        satSandeshDistributionReportWindow.add(cancelButton, "gapleft 60, w :90: ,span 3, wrap 20px");
        
        printButton.setEnabled(false);
        
        
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        satSandeshDistributionReportWindow.setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == okButton)
        {
            int idx =0;
            //int typeQuantity[][] = new int[12][4];
            int typeQuantityIssued[][] = new int[12][col.length - 1 ];
            try
            {
                String yearString = (String)yearDropDown.getSelectedItem();
                String reportString = (String)reportYearDropDown.getSelectedItem();
                String monthString = monthText.getText();
                //String stallString = (String)stallDropDown.getSelectedItem();
                String languageString = (String)languageDropDown.getSelectedItem();
                if(yearString.isEmpty() || languageString.isEmpty() || reportString.isEmpty() )
                    return;
                
                int year = Integer.parseInt(yearString);
                int reportYear = Integer.parseInt(reportString);
                int monthNum = 0; 
                if(monthString.isEmpty() == false)monthNum= Integer.parseInt(monthString);
                
                connect c1=new connect();
                for(idx = 1; idx < 13; idx++)
                {
                    for(int type = 0 ; type < col.length -2 ; type++ )
                    {
                        
                        String sqlQuery;
                        if(monthNum == 0 )
                            sqlQuery = "select quantity from sat_sandesh_inventory_issue where issue_year="+reportYear+" and issue_month ="+idx+" and issue_type = '"+col[type+1]+"' and language ='"+languageString+"' and entry_date between '"+year+"-4-1' and '"+(year+1)+"-3-31'";
                        else
                            sqlQuery = "select quantity from sat_sandesh_inventory_issue where issue_year="+reportYear+" and issue_month ="+idx+" and issue_type = '"+col[type+1]+"' and language ='"+languageString+"' and entry_date between '"+year+"-"+monthNum+"-1' and '"+year+"-"+monthNum+"-31'";
                        
                        if(col[type+1].equals("Binding") == true)
                            sqlQuery += " and customer_name not in ('Binding')";
                                    
                        
                        //System.out.println(sqlQuery);
                        c1.rs=c1.st.executeQuery(sqlQuery);
                        typeQuantityIssued[idx -1][type] = 0;
                        while(c1.rs.next())
                        {
                            int qty = c1.rs.getInt(1);
                            if(col[type+1].equals("Binding") == true)
                            {
                                if(idx > 1)
                                    continue;
                                qty = -1*qty;
                            }
                            typeQuantityIssued[idx -1][type] += qty;
                        }
                        
                        
                        //============================================
                        
                        //connect c2=new connect();
                        //sqlQuery = "select quantity from sat_sandesh_inventory_entry where issue_year="+year+" and issue_month ="+idx+" and counter = '"+stalls[stall]+"' and language ='"+languageString+"'";
                        //System.out.println(sqlQuery);
                        //c2.rs=c2.st.executeQuery(sqlQuery);
                        //typeQuantity[idx -1][type] = 0;
                        //while(c2.rs.next())
                        //{
                        //    typeQuantity[idx -1][type] += c2.rs.getInt(1);
                        //}
                        //c2.rs.close();
                        //System.out.println((stallQuantity[idx -1][stall] - stallQuantityIssued[idx -1][stall]));
                    }
                }
                c1.rs.close();
                //System.out.println(i);                
                {
                    Object data[][]= new Object[14][col.length];
                    
                    int total[] = new int[col.length - 1];
                    for(idx = 1; idx < 13; idx++)
                    {
                        data[idx-1][0] = month[idx-1];
                        int colTotal = 0;
                        for(int type = 0 ; type < col.length - 2; type++ )
                        {
                            //int qty = (typeQuantity[idx -1][type] - typeQuantityIssued[idx -1][type]);
                            int qty = typeQuantityIssued[idx -1][type];
                            data[idx -1][type+1] = qty;
                            total[type]+=qty;
                            colTotal+=qty;
                        }
                        data[idx-1][col.length -1] = colTotal;
                    }
                    data[12][0] = "Total";
                    int totalQty = 0;
                    for(idx  = 1; idx < col.length ; idx++){
                        data[12][idx] = total[idx - 1];
                        totalQty += total[idx-1];
                    }
                    data[12][col.length - 1] = totalQty;
                    
                    reportTable = new JTable(data,col);
                    reportTable.setFont(new Font("Serif", Font.PLAIN, 15));
                    
                    //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    reportTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                    for(int i = 1; i < col.length ; i++ )
                        reportTable.getColumnModel().getColumn(i).setPreferredWidth(100);
                    
                    reportTable.setRowHeight(28);
                    reportTable.setShowGrid(true);
                    
                    scrollPane =new JScrollPane(reportTable);
                    scrollPane.setBounds(40,150,760,400);
                    satSandeshDistributionReportWindow.add(scrollPane,"span 5, wrap 20px");
                    printButton.setEnabled(true);
                    if(monthNum == 0 )
                        satSandeshDistributionReportWindow.setTitle("Sat Sandesh Distribution Report for the period Jan-Dec'"+reportString+" issued in the financial year "+yearString);
                    else
                        satSandeshDistributionReportWindow.setTitle("Sat Sandesh Distribution Report for the period Jan-Dec'"+reportString+" issued in "+month[monthNum-1]+"-"+yearString);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("exception"+e);
            }
            
        }
        else if(event.getSource() == cancelButton)
        {
            satSandeshDistributionReportWindow.setVisible(false);
            new sams();
            satSandeshDistributionReportWindow.dispose();
            
        }
        
        else if(event.getSource() == printButton)
        {
            try {
                
                MessageFormat header = new MessageFormat("Sat Sandesh Inventory for year"+(String)yearDropDown.getSelectedItem());
                MessageFormat footer = new MessageFormat("Page - {0}");
                
                JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;
                                         
                boolean complete = reportTable.print(mode, header, footer, true, null, true, null);
                //boolean complete = reportTable.print();
                if (complete) {
                    /* show a success message  */
                    JOptionPane.showMessageDialog(satSandeshDistributionReportWindow,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    /*show a message indicating that printing was cancelled */
                    JOptionPane.showMessageDialog(satSandeshDistributionReportWindow,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
                    
                }
            } catch (PrinterException pe) {
                /* Printing failed, report to the user */
                JOptionPane.showMessageDialog(satSandeshDistributionReportWindow,
                                          "Printing Failed: " + pe.getMessage(),
                                          "Printing Result",
                                          JOptionPane.ERROR_MESSAGE);
                
            }
            
        }
    }

    
    
    static public Object[] fillIssueYearInformation()
    {
        connect fillSerieConnection = new connect();
        Object[] seriesNameArray = null;
        try
        {
            String query = "select distinct issue_year from sat_sandesh_inventory_entry order by issue_year DESC";
            String countQuery = "select count(distinct issue_year) from sat_sandesh_inventory_entry";
            
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
