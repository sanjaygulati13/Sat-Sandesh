
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class SatSandeshInventoryReport implements ActionListener{
//Data Type declarations
    JFrame satSandeshInventoryReportWindow;
    JLabel languageLabel, issueYearLabel, stallLabel;
    
    JTable reportTable;
    JScrollPane scrollPane;
    
    JComboBox yearDropDown, stallDropDown, languageDropDown;
    JButton okButton, cancelButton, printButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram","Other"};
    
    Object col[]={"Month","Kirpal Bagh", "Kirpal Ashram","Other", "Total"};
    
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
        new SatSandeshInventoryReport();
    }
    
    public SatSandeshInventoryReport()
    {
        //setting environment for the Frame satSandeshInventoryReportWindow
        satSandeshInventoryReportWindow = new JFrame("Sat Sandesh Inventory Report");
        satSandeshInventoryReportWindow.setLayout(mLayout);
        satSandeshInventoryReportWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshInventoryReportWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
                );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshInventoryReportWindow.setSize((screenSize.width)/2,(screenSize.height*4)/5);
        Dimension frameSize = satSandeshInventoryReportWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 4;
        int y = (screenSize.height - frameSize.height) / 8;
        satSandeshInventoryReportWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshInventoryReportWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
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
        
        yearDropDown = new JComboBox(fillIssueYearInformation());        
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
        
        
        satSandeshInventoryReportWindow.add(issueYearLabel," span 1");
        satSandeshInventoryReportWindow.add(yearDropDown, "span 1, w 100!");        
        //satSandeshInventoryReportWindow.add(stallLabel);
        //satSandeshInventoryReportWindow.add(stallDropDown, "span 3, w 150!");
        satSandeshInventoryReportWindow.add(languageLabel," span 1");
        satSandeshInventoryReportWindow.add(languageDropDown,"span 1, w :90:, wrap 10px");
        
        satSandeshInventoryReportWindow.add(okButton, "gapleft 80, w :90:, span 2");
        satSandeshInventoryReportWindow.add(printButton,"gapleft 80, w :90:, span 2");
        satSandeshInventoryReportWindow.add(cancelButton, "gapleft 60, w :90: ,span 3, wrap 20px");
        
        printButton.setEnabled(false);
        
        
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        satSandeshInventoryReportWindow.setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == okButton)
        {
            int idx =0;
            int stallQuantity[][] = new int[12][3];
            int stallQuantityIssued[][] = new int[12][3];
            try
            {
                String yearString = (String)yearDropDown.getSelectedItem();
                //String stallString = (String)stallDropDown.getSelectedItem();
                String languageString = (String)languageDropDown.getSelectedItem();
                if(yearString.isEmpty() || languageString.isEmpty())
                    return;
                
                int year = Integer.parseInt(yearString);
                
                for(idx = 1; idx < 13; idx++)
                {
                    for(int stall = 0 ; stall < 3; stall++ )
                    {
                        connect c1=new connect();
                        String sqlQuery = "select quantity from sat_sandesh_inventory_issue where issue_year="+year+" and issue_month ="+idx+" and counter = '"+stalls[stall]+"' and language ='"+languageString+"'";
                        //System.out.println(sqlQuery);
                        c1.rs=c1.st.executeQuery(sqlQuery);
                        stallQuantityIssued[idx -1][stall] = 0;
                        while(c1.rs.next())
                        {
                            stallQuantityIssued[idx -1][stall] = c1.rs.getInt(1);
                        }
                        c1.rs.close();
                        
                        //============================================
                        
                        connect c2=new connect();
                        sqlQuery = "select quantity from sat_sandesh_inventory_entry where issue_year="+year+" and issue_month ="+idx+" and counter = '"+stalls[stall]+"' and language ='"+languageString+"'";
                        //System.out.println(sqlQuery);
                        c2.rs=c2.st.executeQuery(sqlQuery);
                        stallQuantity[idx -1][stall] = 0;
                        while(c2.rs.next())
                        {
                            stallQuantity[idx -1][stall] = c2.rs.getInt(1);
                        }
                        c2.rs.close();
                        //System.out.println((stallQuantity[idx -1][stall] - stallQuantityIssued[idx -1][stall]));
                    }
                }
                
                //System.out.println(i);                
                {
                    Object data[][]= new Object[13][5];
                    
                    int total[] = new int[3];
                    for(idx = 1; idx < 13; idx++)
                    {
                        data[idx-1][0] = month[idx-1];
                        int colTotal = 0;
                        for(int stall = 0 ; stall < 3; stall++ )
                        {
                            int qty = (stallQuantity[idx -1][stall] - stallQuantityIssued[idx -1][stall]);
                            data[idx -1][stall+1] = qty;
                            total[stall]+=qty;
                            colTotal+=qty;
                        }
                        data[idx-1][4] = colTotal;
                    }
                    data[12][0] = "Total";
                    data[12][1] = total[0];
                    data[12][2] = total[1];
                    data[12][3] = total[2];
                    data[12][4] = total[0] + total[1] + total[2];
                
                    reportTable = new JTable(data,col);
                    reportTable.setFont(new Font("Serif", Font.PLAIN, 15));
                    
                    //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    reportTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                    reportTable.getColumnModel().getColumn(1).setPreferredWidth(100);
                    reportTable.getColumnModel().getColumn(2).setPreferredWidth(100);
                    reportTable.getColumnModel().getColumn(3).setPreferredWidth(100);
                    reportTable.setRowHeight(28);
                    reportTable.setShowGrid(true);
                    
                    scrollPane =new JScrollPane(reportTable);
                    scrollPane.setBounds(40,100,500,400);
                    satSandeshInventoryReportWindow.add(scrollPane,"span 5, wrap 20px");
                    printButton.setEnabled(true);
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
            satSandeshInventoryReportWindow.setVisible(false);
            new sams();
            satSandeshInventoryReportWindow.dispose();
            
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
                    JOptionPane.showMessageDialog(satSandeshInventoryReportWindow,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    /*show a message indicating that printing was cancelled */
                    JOptionPane.showMessageDialog(satSandeshInventoryReportWindow,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
                    
                }
            } catch (PrinterException pe) {
                /* Printing failed, report to the user */
                JOptionPane.showMessageDialog(satSandeshInventoryReportWindow,
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
            String query = "select distinct issue_year from sat_sandesh_inventory_entry";
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
