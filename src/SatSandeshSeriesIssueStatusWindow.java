
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
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
public class SatSandeshSeriesIssueStatusWindow implements ActionListener, ItemListener {
    
    public static void main(String args[])
    {
        new SatSandeshSeriesIssueStatusWindow();
    }
    
    
    JComboBox seriesDropDown, bookNumberDropDown;
    JLabel seriesLabel, rcptFromNumberLabel, rcptToNumberLabel;
    JTextField rcptFromText, rcptToText;
    JButton okButton, refreshButton, backButton, printButton;
    Object col[]={"Receipt Book #","FROM","TO","ISSUED TO"};
    JTable searchResultsTable;
    JScrollPane sp;
    
    JRadioButton allRadioButton, issuedRadioButton, unissuedRadioButton,bookNumberRadioButton, rcptNumberRadioButton;
    ButtonGroup typeGroup;
    int mode;
    
    JFrame satSandeshSeriesIssueStatusWindowFrame;
    MigLayout mLayout= new MigLayout( "insets 30");
    
    protected void center()
    {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshSeriesIssueStatusWindowFrame.setSize(screenSize.width-300,screenSize.height-300);
        Dimension frameSize = satSandeshSeriesIssueStatusWindowFrame.getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        
        satSandeshSeriesIssueStatusWindowFrame.setLocation(x, y);
    }
    
    public SatSandeshSeriesIssueStatusWindow()
    {
        mode = 0;
        satSandeshSeriesIssueStatusWindowFrame = new JFrame("Series Issue Details");
        satSandeshSeriesIssueStatusWindowFrame.setLayout(mLayout);
        satSandeshSeriesIssueStatusWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshSeriesIssueStatusWindowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshSeriesIssueStatusWindowFrame.setSize((screenSize.width*3)/6,(screenSize.height*8)/10);
        Dimension frameSize = satSandeshSeriesIssueStatusWindowFrame.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        satSandeshSeriesIssueStatusWindowFrame.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshSeriesIssueStatusWindowFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            cnf.printStackTrace();
            // this class will create a log, helps in debugging
            //except currentException = new except(cnf, this.getClass().toString());
        }
        
        seriesLabel     = new JLabel("Series");
        rcptFromNumberLabel = new JLabel("From");
        rcptToNumberLabel = new JLabel("To");
        
        
        seriesDropDown      = new JComboBox(SamsUtilities.fillSeriesInformation());
        bookNumberDropDown  = new JComboBox();
        rcptFromText        = new JTextField();
        rcptToText          = new JTextField();
        
        
        okButton        = new JButton(SamsUtilities.getOkButtonName());
        refreshButton   = new JButton(SamsUtilities.getClearButtonName());
        backButton      = new JButton(SamsUtilities.getCancelButtonName());
        printButton     = new JButton(SamsUtilities.getPrintButtonName());
        
        
        allRadioButton = new JRadioButton("All");
        issuedRadioButton = new JRadioButton("Issued");
        unissuedRadioButton = new JRadioButton("Not issued");
        bookNumberRadioButton = new JRadioButton("Book Number");
        rcptNumberRadioButton = new JRadioButton("Receipt");
        
        allRadioButton.addActionListener(this);
        issuedRadioButton.addActionListener(this);
        unissuedRadioButton.addActionListener(this);
        bookNumberRadioButton.addActionListener(this);
        rcptNumberRadioButton.addActionListener(this);
        
        typeGroup  = new ButtonGroup();
        typeGroup.add(allRadioButton);
        typeGroup.add(issuedRadioButton);
        typeGroup.add(unissuedRadioButton);
        typeGroup.add(bookNumberRadioButton);
        typeGroup.add(rcptNumberRadioButton);
        
        
        satSandeshSeriesIssueStatusWindowFrame.add(seriesLabel);
        satSandeshSeriesIssueStatusWindowFrame.add(seriesDropDown, "w 150!, wrap, span 2");
        
        satSandeshSeriesIssueStatusWindowFrame.add(allRadioButton, "w 150!,wrap, span 2");
        satSandeshSeriesIssueStatusWindowFrame.add(issuedRadioButton, "w 150!,wrap, span 2");
        satSandeshSeriesIssueStatusWindowFrame.add(unissuedRadioButton, "w 150!,wrap, span 2");
        satSandeshSeriesIssueStatusWindowFrame.add(bookNumberRadioButton);
        satSandeshSeriesIssueStatusWindowFrame.add(bookNumberDropDown, "w 150!,wrap, span 2");
        satSandeshSeriesIssueStatusWindowFrame.add(rcptNumberRadioButton);
        satSandeshSeriesIssueStatusWindowFrame.add(rcptFromNumberLabel);
        satSandeshSeriesIssueStatusWindowFrame.add(rcptFromText,"w 70!");
        satSandeshSeriesIssueStatusWindowFrame.add(rcptToNumberLabel);
        satSandeshSeriesIssueStatusWindowFrame.add(rcptToText, "w 70!,wrap");
        
        
        satSandeshSeriesIssueStatusWindowFrame.add(okButton);
        satSandeshSeriesIssueStatusWindowFrame.add(refreshButton);
        satSandeshSeriesIssueStatusWindowFrame.add(backButton);
        satSandeshSeriesIssueStatusWindowFrame.add(printButton, "wrap");
        
        
        seriesDropDown.addItemListener(this);
        
        okButton.addActionListener(this);
        refreshButton.addActionListener(this);
        backButton.addActionListener(this);
        printButton.addActionListener(this);
        printButton.setEnabled(false);
        
        
        satSandeshSeriesIssueStatusWindowFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == okButton)
        {
            if(searchResultsTable != null){
                //DefaultTableModel model = (DefaultTableModel) searchResultsTable.getModel();
                //model.setRowCount(0);
            }
            try{
                
                connect con = new connect();
                
                String query="", countQuery="";
                String series_name = seriesDropDown.getSelectedItem().toString();
                
                if(mode == 1) //All books
                {
                    query = "select book_num,start_rcpt_num,end_rcpt_num,issued_to from receipt_book_inventory where series_name = '"+series_name+"'";
                    countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+series_name+"'";
                }
                else if (mode == 2) //issued
                {
                    query = "select book_num,start_rcpt_num,end_rcpt_num,issued_to from receipt_book_inventory where series_name = '"+series_name+"'  and issued_to not in ('')";
                    countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+series_name+"'  and issued_to not in ('')";
                }
                else if (mode == 3) //non issued
                {
                    query = "select book_num,start_rcpt_num,end_rcpt_num,issued_to from receipt_book_inventory where series_name = '"+series_name+"' and issued_to = ''";
                    countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+series_name+"' and issued_to = ''";
                }
                else if (mode == 4) //book number
                {
                    String book_number = bookNumberDropDown.getSelectedItem().toString();
                    int bookNum = Integer.parseInt(book_number);
                    if(bookNum == 0)
                    {
                        JOptionPane.showMessageDialog(null,"Please enter book number","Incorrect book number",JOptionPane.ERROR_MESSAGE);
                        bookNumberDropDown.requestFocus();
                        return;
                    }
                    
                    query = "select book_num,start_rcpt_num,end_rcpt_num,issued_to from receipt_book_inventory where series_name = '"+series_name+"' and book_num = "+bookNum;
                    countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+series_name+"' and book_num = "+bookNum;
                }
                else if (mode == 5) //rcpt number
                {
                    String start_rcpt_num = rcptFromText.getText();
                    String end_rcpt_num = rcptToText.getText();
                    int startNum = Integer.parseInt(start_rcpt_num);
                    int endNum = Integer.parseInt(end_rcpt_num);
                    
                    if(startNum == 0 && !start_rcpt_num.equals("0"))
                    {
                        JOptionPane.showMessageDialog(null,"Please enter From receipt number","Incorrect receipt number",JOptionPane.ERROR_MESSAGE);
                        rcptFromText.requestFocus();
                        return;
                    }
                    
                    if(endNum == 0)
                    {
                        JOptionPane.showMessageDialog(null,"Please enter To receipt number","Incorrect receipt number",JOptionPane.ERROR_MESSAGE);
                        rcptToText.requestFocus();
                        return;
                    }
                    
                    if(endNum <= startNum)
                    {
                        JOptionPane.showMessageDialog(null,"Please check receipt number range","Incorrect receipt number",JOptionPane.ERROR_MESSAGE);
                        rcptFromText.requestFocus();
                        return;
                    }
                    
                    query = "select book_num,start_rcpt_num,end_rcpt_num,issued_to from receipt_book_inventory where series_name = '"+series_name+"'  and end_rcpt_num >= "+startNum+" and start_rcpt_num <= "+endNum;
                    countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+series_name+"'  and end_rcpt_num >= "+startNum+" and start_rcpt_num <= "+endNum;
                }
                
                
                int totalEntries = 0;
                con.rs=con.st.executeQuery(countQuery);
                if(con.rs.next())
                    totalEntries = con.rs.getInt(1);
                
                //System.out.println(totalEntries);
                if(totalEntries==0)
                    JOptionPane.showMessageDialog(null,"No Records Found","No Records",JOptionPane.ERROR_MESSAGE);
                
                else
                {
                    
                    
                    con.rs=con.st.executeQuery(query);
                    Object data[][]= new Object[totalEntries][4];
                    int j=0;
                    while(con.rs.next())
                    {
                        data[j][0]=con.rs.getString(1);
                        data[j][1]=con.rs.getString(2);
                        data[j][2]=con.rs.getString(3);
                        data[j][3]=con.rs.getString(4);
                        j++;
                    }
                    
                    searchResultsTable = new JTable(data,col);
                    //searchResultsTable.setModel(new DefaultTableModel());
                    sp = new JScrollPane(searchResultsTable);
                    sp.setBounds(20,250,545,300);
                    satSandeshSeriesIssueStatusWindowFrame.add(sp,"span, wrap");
                    //satSandeshSeriesIssueStatusWindowFrame.repaint();
                    //System.out.println("Added table");
                    
                    for (int x=0; x < col.length; x++)
                    {
                        TableColumn column = searchResultsTable.getColumnModel().getColumn(x);
                        //System.out.println(searchResultsTable.getColumnModel().getColumn(totalEntries));
                        
                        if (x==0) column.setPreferredWidth(5);
                        if (x==1) column.setPreferredWidth(5);
                        if (x==2) column.setPreferredWidth(5);
                        if (x==3) column.setPreferredWidth(5);
                        
                    }
                    //satSandeshSeriesIssueStatusWindowFrame.add(sp,"span, wrap");
                }
                printButton.setEnabled(true);
                okButton.setEnabled(false);
                refreshButton.setEnabled(false);
                con.closeAll();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
        }
        else if(ae.getSource() == refreshButton)
        {
            seriesDropDown.setSelectedIndex(0);
            bookNumberDropDown.removeAllItems();
            rcptFromText.setText("");
            rcptToText.setText("");
            
        }
        else if(ae.getSource() == backButton)
        {
            new sams();
            satSandeshSeriesIssueStatusWindowFrame.dispose();
            
        }
        else if(ae.getSource() == printButton)
        {
            try {
                MessageFormat headerFormat = new MessageFormat("Series issue status"); // \t (Page {0})");
                MessageFormat footerFormat = new MessageFormat("\t ( Page {0} )");
                searchResultsTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
                System.err.println("Error printing: " + pe.getMessage());
            }
        }
        else if(ae.getSource() == allRadioButton)
        {
            mode = 1;
        }
        else if(ae.getSource() == issuedRadioButton)
        {
            mode = 2;
        }
        else if(ae.getSource() == unissuedRadioButton)
        {
            mode = 3;
        }
        else if(ae.getSource() == bookNumberRadioButton)
        {
            mode = 4;
        }
        else if(ae.getSource() == rcptNumberRadioButton)
        {
            mode = 5;
        }
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == seriesDropDown)
        {
            try{
                bookNumberDropDown.removeAllItems();
                connect con = new connect();
                
                String query = "select book_num from receipt_book_inventory where series_name = '"+seriesDropDown.getSelectedItem().toString()+"'";
                con.rs = con.st.executeQuery(query);
                while(con.rs.next()){
                    bookNumberDropDown.addItem(con.rs.getInt(1));
                }
                
                con.closeAll();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
        }
    }
    
}
