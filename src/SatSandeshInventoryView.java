
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
import javax.swing.JTextField;
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
public class SatSandeshInventoryView implements ActionListener{
//Data Type declarations
    JFrame satSandeshInventoryViewWindow;
    JLabel pageNumberLabel;
    JTextField pageNumberText;
    
    JTable reportTable;
    JScrollPane scrollPane;
    
    JButton okButton, cancelButton, printButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    Object col[]={"S.No.","Customer","Month","Year", "Quantity","Language", "Issue Type","Amount", "D#","Counter", "Entry Date", "Issued By"};
    
    
    public static void main(String args[])
    {
        new SatSandeshInventoryView();
    }
    
    public SatSandeshInventoryView()
    {
        //setting environment for the Frame satSandeshInventoryReportWindow
        satSandeshInventoryViewWindow = new JFrame("Sat Sandesh Inventory Information");
        satSandeshInventoryViewWindow.setLayout(mLayout);
        satSandeshInventoryViewWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshInventoryViewWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
                );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshInventoryViewWindow.setSize((screenSize.width*4)/5,(screenSize.height*4)/5);
        Dimension frameSize = satSandeshInventoryViewWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 4;
        int y = (screenSize.height - frameSize.height) / 8;
        satSandeshInventoryViewWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshInventoryViewWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
            UIManager.put("Table.gridColor", new ColorUIResource(Color.GRAY));
        }
        catch (Exception cnf)
        {
            // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        pageNumberLabel = new JLabel("<HTML>Page Number</HTML>");
        pageNumberText = new JTextField();
        
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
        
        
        satSandeshInventoryViewWindow.add(pageNumberLabel," span 3, align right");
        satSandeshInventoryViewWindow.add(pageNumberText, "span 1, w 100!");        
        
        satSandeshInventoryViewWindow.add(okButton, "gapleft 80, w :90:, span 2");
        satSandeshInventoryViewWindow.add(printButton,"gapleft 80, w :90:, span 2");
        satSandeshInventoryViewWindow.add(cancelButton, "gapleft 60, w :90: ,span 3, wrap 20px");
        
        printButton.setEnabled(false);
        
        
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        satSandeshInventoryViewWindow.setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == okButton)
        {
            int idx =0;
            Object pageData[][];
            
            try
            {
                String pageString = pageNumberText.getText();
                
                if(pageString.isEmpty())
                    return;
                
                int pageNumber = Integer.parseInt(pageString);
                if(pageNumber > 0)
                {
                
                    {
                        connect c1=new connect();
                        String sqlQuery = "select * from sat_sandesh_inventory_issue where page_no="+pageNumber;
                        String countQuery = "select count(counter) from sat_sandesh_inventory_issue where issue_type not in ('Binding') and page_no="+pageNumber;
                        String bindingCountQuery = "select count(counter) from sat_sandesh_inventory_issue where issue_type='Binding' and issue_month=1 and page_no="+pageNumber;
                        //System.out.println(countQuery);
                        c1.rs=c1.st.executeQuery(countQuery);
                        int pageEntrySize = 0;
                        c1.rs.next();
                        pageEntrySize = c1.rs.getInt(1);
                        
                        c1.rs=c1.st.executeQuery(bindingCountQuery);
                        c1.rs.next();
                        pageEntrySize += c1.rs.getInt(1);
                        
                        System.out.println(pageEntrySize);
                        
                        int numEntries = 0;
                        pageData  = new Object[pageEntrySize][12];
                        c1.rs=c1.st.executeQuery(sqlQuery);
                        while(c1.rs.next())
                        //c1.rs.next();
                        {
                            if((c1.rs.getString(6)).equals("Binding") && c1.rs.getInt(2)>1)
                                continue;
                            pageData[numEntries][0] = (numEntries+1);
                            pageData[numEntries][1] = c1.rs.getString(1);
                            pageData[numEntries][2] = c1.rs.getInt(2);
                            pageData[numEntries][3] = c1.rs.getInt(3);
                            pageData[numEntries][4] = (-1)*(c1.rs.getInt(4));
                            pageData[numEntries][5] = c1.rs.getString(5);
                            pageData[numEntries][6] = c1.rs.getString(6);
                            pageData[numEntries][7] = c1.rs.getInt(7);
                            pageData[numEntries][8] = c1.rs.getInt(8);
                            pageData[numEntries][9] = c1.rs.getString(9);
                            pageData[numEntries][10] = c1.rs.getString(10);
                            pageData[numEntries][11] = c1.rs.getString(11);
                            
                            numEntries++;
                        }
                        c1.rs.close();
                        
                    }
                
                
                //System.out.println(i);                
                    /*
                    data[12][0] = "Total";
                    data[12][1] = total[0];
                    data[12][2] = total[1];
                    data[12][3] = total[2];
                    data[12][4] = total[3];
                    data[12][5] = total[0] + total[1] + total[2] + total[3];
                    
                    data[13][0] = "Binding";
                    int bindingTotalQty = 0;
                    for(int stall = 0; stall < 4 ; stall++)
                    {
                        connect c2=new connect();
                        String sqlQuery = "select quantity from sat_sandesh_inventory_issue where issue_month = 1 and issue_year="+year+" and issue_type = 'binding' and language = '"+languageString+"' and counter = '"+col[stall+1]+"' ";
                        //System.out.println(sqlQuery);
                        c2.rs=c2.st.executeQuery(sqlQuery);
                        int bindingQty = 0;
                        while(c2.rs.next())
                        {
                            bindingQty += c2.rs.getInt(1);
                        }
                        c2.rs.close();
                        data[13][stall + 1] = bindingQty;
                        bindingTotalQty += bindingQty;
                    }
                    data[13][5] = bindingTotalQty;
                */
                    reportTable = new JTable(pageData,col);
                    reportTable.setFont(new Font("Serif", Font.PLAIN, 15));
                    
                    //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    reportTable.getColumnModel().getColumn(0).setPreferredWidth(40);
                    reportTable.getColumnModel().getColumn(1).setPreferredWidth(160);
                    reportTable.getColumnModel().getColumn(2).setPreferredWidth(50);
                    reportTable.getColumnModel().getColumn(3).setPreferredWidth(40);
                    reportTable.getColumnModel().getColumn(4).setPreferredWidth(60);
                    reportTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                    reportTable.getColumnModel().getColumn(6).setPreferredWidth(140);
                    reportTable.getColumnModel().getColumn(7).setPreferredWidth(80);
                    reportTable.getColumnModel().getColumn(8).setPreferredWidth(60);
                    reportTable.getColumnModel().getColumn(9).setPreferredWidth(120);
                    reportTable.getColumnModel().getColumn(10).setPreferredWidth(120);
                    reportTable.getColumnModel().getColumn(11).setPreferredWidth(100);
                    
                    reportTable.setRowHeight(28);
                    reportTable.setShowGrid(true);
                    
                    scrollPane =new JScrollPane(reportTable);
                    
                    scrollPane.setBounds(40,100,satSandeshInventoryViewWindow.getSize().width-80,satSandeshInventoryViewWindow.getSize().height-150);
                    satSandeshInventoryViewWindow.add(scrollPane,"span 5, wrap 20px");
                    printButton.setEnabled(true);
                    satSandeshInventoryViewWindow.setTitle("Sat Sandesh Issue details on Page Number "+pageNumber);
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
            satSandeshInventoryViewWindow.setVisible(false);
            new sams();
            satSandeshInventoryViewWindow.dispose();
            
        }
        
        else if(event.getSource() == printButton)
        {
            try {
                
                MessageFormat header = new MessageFormat("Sat Sandesh Isue Details for Page : "+pageNumberText.getText());
                MessageFormat footer = new MessageFormat("Page - {0}");
                
                JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;
                                         
                boolean complete = reportTable.print(mode, header, footer, true, null, true, null);
                //boolean complete = reportTable.print();
                if (complete) {
                    /* show a success message  */
                    JOptionPane.showMessageDialog(satSandeshInventoryViewWindow,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    /*show a message indicating that printing was cancelled */
                    JOptionPane.showMessageDialog(satSandeshInventoryViewWindow,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
                    
                }
            } catch (PrinterException pe) {
                /* Printing failed, report to the user */
                JOptionPane.showMessageDialog(satSandeshInventoryViewWindow,
                                          "Printing Failed: " + pe.getMessage(),
                                          "Printing Result",
                                          JOptionPane.ERROR_MESSAGE);
                
            }
            
        }
    }
}
