
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
public class SatSandeshConsolidateStockWindow implements ActionListener, TableModelListener, ItemListener{

    JFrame consolidateFrame;
    JLabel yearLabel, priceLabel, godownLabel, seriesLabel, oldSeriesLabel, transferLabel;
    JTextField yearText, priceText, oldSeriesStockText, transferText;
    JComboBox seriesDropDown, godownDropDown;
    JButton okButton, cancelButton, clearButton;
    
    
    MigLayout mLayout= new MigLayout( "insets 30");
    JTable bulkEntryTable;
    JScrollPane scrollPane;
    Object [] stalls = {"Main Store","Kirpal Bagh", "Kirpal Ashram","Other"};
    Object col[]={"Month", "Stock", "Transfer"};
    String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    Object[][] data;
    
    
    public static void main(String args[])
    {
        SatSandeshConsolidateStockWindow satSandeshConsolidateStockWindow = new SatSandeshConsolidateStockWindow();
    }
    
    public SatSandeshConsolidateStockWindow()
    {
        consolidateFrame = new JFrame();
        try
        {
            consolidateFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            except currentException = new except(cnf, this.getClass().toString());
            System.out.println(cnf);
        }
        consolidateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        consolidateFrame.setTitle("Consolidate Stock");
        consolidateFrame.setSize(1000,690);
        consolidateFrame.setResizable(false);
        consolidateFrame.setLocation(10,10);
        consolidateFrame.setLayout(mLayout);
        SamsUtilities.center(consolidateFrame);
        
        yearLabel = new JLabel("<HTML>Year</HTML>");
        priceLabel = new JLabel("<HTML>Price</HTML>");
        godownLabel = new JLabel("<HTML>Godown</HTML>");
        seriesLabel = new JLabel("<HTML>Series</HTML>");
        oldSeriesLabel = new JLabel("<HTML>Stock for old Series</HTML>");
        transferLabel = new JLabel("<HTML>Transfer</HTML>");
        
        yearText = new JTextField();
        priceText = new JTextField();
        godownDropDown = new JComboBox(stalls);
        oldSeriesStockText = new JTextField();
        transferText = new JTextField();
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        seriesDropDown.addItemListener(this);
        
        data = new Object[12][3];
        
        for(int _x = 0; _x < month.length; _x++)
            data[_x][0] = month[_x]; 
        bulkEntryTable = new JTable(data,col){
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column == 1 || column==2);
            }
        };
        
        Font tableFont = new Font("Serif", Font.PLAIN, 15);
        Font headerFont = new Font("Serif", Font.BOLD, 15);
        bulkEntryTable.getTableHeader().setFont(headerFont);
        bulkEntryTable.setFont(tableFont);
        /*bulkEntryTable.getColumnModel().getColumn(0).setMaxWidth(130);
        bulkEntryTable.getColumnModel().getColumn(1).setMaxWidth(100);
        bulkEntryTable.getColumnModel().getColumn(2).setPreferredWidth(100);*/
        
        bulkEntryTable.setRowHeight(28);
        bulkEntryTable.setShowGrid(true);
        bulkEntryTable.setGridColor(Color.DARK_GRAY);
        bulkEntryTable.setShowHorizontalLines(true);
        bulkEntryTable.setShowVerticalLines(true);
        bulkEntryTable.setColumnSelectionAllowed(true);
        bulkEntryTable.setRowSelectionAllowed(true);
        bulkEntryTable.setFocusCycleRoot(true);
        bulkEntryTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        bulkEntryTable.getModel().addTableModelListener(this);
        scrollPane =new JScrollPane(bulkEntryTable);
        
        okButton = new JButton(SamsUtilities.getOkButtonName()); 
        cancelButton = new JButton(SamsUtilities.getCancelButtonName()); 
        clearButton = new JButton(SamsUtilities.getClearButtonName());
        
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        consolidateFrame.add(yearLabel);
        consolidateFrame.add(yearText,"w 70");
        consolidateFrame.add(priceLabel);
        consolidateFrame.add(priceText," w 70");
        consolidateFrame.add(godownLabel);
        consolidateFrame.add(godownDropDown, "w 70");
        consolidateFrame.add(seriesLabel);
        consolidateFrame.add(seriesDropDown,"wrap, w 70 ");
        
        consolidateFrame.add(scrollPane, "wrap, span");
        
        consolidateFrame.add(oldSeriesLabel);
        consolidateFrame.add(oldSeriesStockText,"wrap, w 70");
        consolidateFrame.add(transferLabel);
        consolidateFrame.add(transferText,"wrap, w 70");
        
        consolidateFrame.add(okButton, "w 150, span 2");
        consolidateFrame.add(cancelButton,"w 150, span 2");
        consolidateFrame.add(clearButton,"w 150, span 2");
        
        
        
        consolidateFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == okButton)
        {
            String year = yearText.getText();
            String godown = (String)godownDropDown.getSelectedItem();
            String price = priceText.getText();
            String oldSeries = oldSeriesStockText.getText();
            String transfer = transferText.getText();
            System.out.println(year + " " + godown + " " + price + " " + oldSeries + " " + transfer );
            
        }
        else if(ae.getSource() == cancelButton)
        {
            sams sams = new sams();
            consolidateFrame.dispose();
        }
        else if(ae.getSource() == clearButton)
        {
            
        }
    }

    @Override
    public void tableChanged(TableModelEvent te) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if(ie.getSource() == seriesDropDown)
        {
            
            try
            {
                connect stockFetchConnection = new connect();
                String year = yearText.getText();
                String godown = (String)godownDropDown.getSelectedItem();
                String languageString = "Hindi";
                String sqlQuery = "select sum(quantity) from sat_sandesh_inventory_entry where issue_year="+year+" and issue_month ="+1+" and counter = '"+godown+"' and language ='"+languageString+"'";
                stockFetchConnection.rs=stockFetchConnection.st.executeQuery(sqlQuery);
                
                while(stockFetchConnection.rs.next())
                {
                    System.out.println(stockFetchConnection.rs.getInt(1));
                    //stallQuantity[idx -1][stall] += c2.rs.getInt(1);
                }
                        
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
    }
    
}
