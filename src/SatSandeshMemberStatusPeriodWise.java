
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
public class SatSandeshMemberStatusPeriodWise implements ActionListener{
    
    public static void main(String args[])
    {
        new SatSandeshMemberStatusPeriodWise();
    }
    
    JFrame satSandeshPeriodWiseMemberStatusWindow;
    JLabel monthYearLabel, languageLabel;
    JButton fetchDataButton, clearButton, backButton, printButton;
    JComboBox monthDropDown, yearDropDown, languageDropDown;
    
    JTable dataTable;
    JScrollPane scrollPane;
    
    Object col[]={"","By Hand", "By Post", "Distributor","Total"};
    MigLayout mLayout= new MigLayout( "insets 30");
    
    public SatSandeshMemberStatusPeriodWise()
    {
        satSandeshPeriodWiseMemberStatusWindow = new JFrame();
        
        try
        {
            satSandeshPeriodWiseMemberStatusWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (ClassNotFoundException cnf)
        {
            JOptionPane.showMessageDialog(satSandeshPeriodWiseMemberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException cnf) {
            JOptionPane.showMessageDialog(satSandeshPeriodWiseMemberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException cnf) {
            JOptionPane.showMessageDialog(satSandeshPeriodWiseMemberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedLookAndFeelException cnf) {
            JOptionPane.showMessageDialog(satSandeshPeriodWiseMemberStatusWindow, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        satSandeshPeriodWiseMemberStatusWindow = new JFrame("Sat Sandesh Member Status - Period Wise");
        satSandeshPeriodWiseMemberStatusWindow.setLayout(mLayout);
        satSandeshPeriodWiseMemberStatusWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshPeriodWiseMemberStatusWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshPeriodWiseMemberStatusWindow.setSize((screenSize.width)*9/10,(screenSize.height*4)/5);
        Dimension frameSize = satSandeshPeriodWiseMemberStatusWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 2;
        int y = (screenSize.height - frameSize.height) / 3;
        satSandeshPeriodWiseMemberStatusWindow.setLocation(x, y);
        
        monthYearLabel = new JLabel("<html>Month/Year</html>");
        languageLabel = new JLabel("<html>Language</html>");
        
        monthDropDown = new JComboBox(SamsUtilities.getMonthNames());
        yearDropDown = new JComboBox();
        {
            int currentYear = SamsUtilities.getCurrentYear();
            for(int i = 0; i < 15 ; i++)
            {
                yearDropDown.addItem(""+currentYear);
                currentYear--;
            }
        }
        
        languageDropDown = new JComboBox(SamsUtilities.getLanguagesAvailable());
        
        fetchDataButton = new JButton(SamsUtilities.getOkButtonName());
        fetchDataButton.addActionListener(this);
        
        backButton = new JButton(SamsUtilities.getCancelButtonName());
        backButton.addActionListener(this);
        
        clearButton = new JButton(SamsUtilities.getClearButtonName());
        clearButton.addActionListener(this);
        
        printButton = new JButton(SamsUtilities.getPrintButtonName());
        printButton.addActionListener(this);
        printButton.setEnabled(false);
        
        satSandeshPeriodWiseMemberStatusWindow.add(monthYearLabel);
        satSandeshPeriodWiseMemberStatusWindow.add(monthDropDown);
        satSandeshPeriodWiseMemberStatusWindow.add(yearDropDown);
        satSandeshPeriodWiseMemberStatusWindow.add(languageLabel);
        satSandeshPeriodWiseMemberStatusWindow.add(languageDropDown,"wrap 20px");
        satSandeshPeriodWiseMemberStatusWindow.add(fetchDataButton);
        satSandeshPeriodWiseMemberStatusWindow.add(clearButton);
        satSandeshPeriodWiseMemberStatusWindow.add(backButton);
        satSandeshPeriodWiseMemberStatusWindow.add(printButton);
        
        
        
        satSandeshPeriodWiseMemberStatusWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fetchDataButton)
        {
            
            
            
            int numberOfDuration = 0;
            Vector<String> durationVector = new Vector<String>();
            
            try
            {
                durationVector.add("");
                connect c1=new connect();
                c1.rs=c1.st.executeQuery("select distinct(duration) from amountdet order by duration");
                while(c1.rs.next()){
                    durationVector.add(c1.rs.getString(1));
                    numberOfDuration++;
                }
                durationVector.add("Total");
                c1.closeAll();
                
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                return;
            }
            
            Object data[][]= new Object[numberOfDuration+1][col.length];
            String language = languageDropDown.getSelectedItem().toString();
            String monthText = monthDropDown.getSelectedItem().toString();
            String year = yearDropDown.getSelectedItem().toString();
            
            //System.out.println(language + " " + month + " " + year );
            
            int byHand = 0;
            int byPost = 0;
            int distributor = 0;
            int total = 0;
            int byHandTotal = 0;
            int byPostTotal = 0 ;
            int distributorTotal = 0;
            int overAllTotal = 0;
            
            connect c1 = new connect();
            for(int i = 0 ; i < numberOfDuration ; i++){
                data[i][0] = durationVector.get(i+1);
                
                try
                {
                    int month = (monthDropDown.getSelectedIndex())+1;
                    //String year = yearDropDown.getSelectedItem().toString();
                    String ending_period = year +"-"+month+"-1";
                    //System.out.println(ending_period);
                    String byHandQuery = "select count(asn) from subscribers_primary_details where  distribution_type='By Hand' and language='" + language + "' and membership_status='Active' and subscription_period = '"+durationVector.get(i+1)+"' and ending_period > '"+ ending_period+"'";
                    String byPostQuery = "select count(asn) from subscribers_primary_details where  distribution_type='By Post' and language='" + language + "' and membership_status='Active' and subscription_period = '"+durationVector.get(i+1)+"' and ending_period > '"+ ending_period+"'";
                    String distributorQuery = "select count(asn) from subscribers_primary_details where  distribution_type='Distributor' and language='" + language + "' and membership_status='Active' and subscription_period = '"+durationVector.get(i+1)+"' and ending_period > '"+ ending_period+"'";
                    
                    //System.out.println(byHandQuery);
                    
                    c1.rs = c1.st.executeQuery(byHandQuery);
                    if(c1.rs.next())
                         byHand = c1.rs.getInt(1);

                    data[i][1] = byHand;
                    
                    c1.rs=c1.st.executeQuery(byPostQuery);
                    if(c1.rs.next())
                        byPost = c1.rs.getInt(1);
                    data[i][2] = byPost;
                    
                    
                    c1.rs=c1.st.executeQuery(distributorQuery);
                    if(c1.rs.next())
                        distributor = c1.rs.getInt(1);
                    
                    data[i][3] = distributor;
                    
                    total = byHand+byPost+distributor;
                    byHandTotal += byHand;
                    byPostTotal += byPost;
                    distributorTotal += distributor;
                    overAllTotal += total;
                    
                    data[i][4] = total;
                    
                    byHand = byPost = distributor = total = 0;
                    
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
                
            }
            
            data[numberOfDuration][0] = "Total";
            data[numberOfDuration][1] = byHandTotal;
            data[numberOfDuration][2] = byPostTotal;
            data[numberOfDuration][3] = distributorTotal;
            data[numberOfDuration][4] = overAllTotal;
            c1.closeAll();
            
            dataTable = new JTable(data,col);
            dataTable.setFont(new Font("Serif", Font.PLAIN, 14));
            dataTable.setRowHeight(28);
            dataTable.setShowGrid(true);
            dataTable.setShowHorizontalLines(true);
            dataTable.setShowVerticalLines(true);
            dataTable.setColumnSelectionAllowed(true);
            dataTable.setRowSelectionAllowed(true);
            dataTable.setFocusCycleRoot(true);
            dataTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            dataTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
            
            
            scrollPane =new JScrollPane(dataTable);
            
            Dimension frameSize = satSandeshPeriodWiseMemberStatusWindow.getSize();
            scrollPane.setBounds(30,140,frameSize.width - 60, frameSize.height - 280);
            //scrollPane.setSize(frameSize.width - 60, frameSize.height - 480);
            //scrollPane.setLocation(30, 200);
            
            satSandeshPeriodWiseMemberStatusWindow.add(scrollPane,"span 5, wrap 20px");
            //saveButton.setBounds(frameSize.width/2-40, frameSize.height-40,80,30);
            printButton.setEnabled(true);
            
            //addDataButton.setEnabled(false);
        }
        if(e.getSource() == backButton)
        {
            satSandeshPeriodWiseMemberStatusWindow.dispose();
            new sams();
        }
        if(e.getSource() == clearButton)
        {
            languageDropDown.setSelectedIndex(0);
            monthDropDown.setSelectedIndex(0);
            yearDropDown.setSelectedIndex(0);
        }
        if(e.getSource()==printButton)
        {
            
            try {
                MessageFormat headerFormat = new MessageFormat("Period Wise Active Member Status"); // \t (Page {0})");
                MessageFormat footerFormat = new MessageFormat(""+monthDropDown.getSelectedItem().toString()+"-"+yearDropDown.getSelectedItem().toString()+" - Language: "+languageDropDown.getSelectedItem().toString()+"\t ( Page {0} )");
                dataTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
                System.err.println("Error printing: " + pe.getMessage());
            }
        }
    }
    
    
}
