
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
//SatSandeshAccountBookPosting

public class SatSandeshAccountBookPosting  implements ActionListener, Printable  {
    
    private static final int NUM_ROWS = 20;
    
    //Data Type declarations
    JFrame satSandeshAccountBookPostingWindow;
    JLabel pageNumberLabel, entryDateLabel, stallLabel;
    JLabel seriesFirstLabel, seriesSecondLabel,rcptFirstLabel, rcptSecondLabel;
    TextFieldWithLimit[] rcptText;
    JComboBox[] seriesDropDown;
    
    JPanel entryPanel;
    JScrollPane scrollPane;
    
    TextFieldWithLimit  entryDateText,entryMonthText,entryYearText, pageNumberText;
    //JComboBox yearDropDown[], monthDropDown[], stallDropDown, languageDropDown[], issueTypeDropDown[];
    //JComboBox customerNameDropDown[];
    JComboBox stallDropDown;
    JButton okButton, cancelButton, printButton;
    MigLayout mLayout= new MigLayout( "insets 30");
    MigLayout pLayout= new MigLayout( "insets 20");
    Object [] stalls = {"Kirpal Bagh", "Kirpal Ashram","Other"};
    
    
    public SatSandeshAccountBookPosting()
    {
        //setting environment for the Frame satSandeshAccountBookPostingWindow
        satSandeshAccountBookPostingWindow = new JFrame("Issue Sat Sandesh");
        satSandeshAccountBookPostingWindow.setLayout(mLayout);
        satSandeshAccountBookPostingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        satSandeshAccountBookPostingWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        
        //Getting the size of the screen, so that the window can
        // adjust itself at the center of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        satSandeshAccountBookPostingWindow.setSize((screenSize.width*2)/3,(screenSize.height*7)/10);
        Dimension frameSize = satSandeshAccountBookPostingWindow.getSize();
        int x = (screenSize.width - frameSize.width)  / 8;
        int y = (screenSize.height - frameSize.height) / 8;
        satSandeshAccountBookPostingWindow.setLocation(x, y);
        
        //adding the system look and feel to the frame
        try
        {
            satSandeshAccountBookPostingWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            // this class will create a log, helps in debugging
            except currentException = new except(cnf, this.getClass().toString());
        }
        
        
        //Data Type population Start
        
        entryPanel = new JPanel();
        entryPanel.setLayout(pLayout);
        
        //Labels
        stallLabel = new JLabel("<HTML>Stall</HTML>");
        pageNumberLabel = new JLabel("<HTML>Page No.</HTML>");
        entryDateLabel = new JLabel("<HTML>Issue date</HTML>");
        
        
        seriesFirstLabel = new JLabel("<HTML>Series</HTML>");
        seriesSecondLabel = new JLabel("<HTML>Receipt Number</HTML>");
        rcptFirstLabel = new JLabel("<HTML>Series</HTML>");
        rcptSecondLabel = new JLabel("<HTML>Receipt Number</HTML>");
        
        
        
        //issueTypeLabel = new JLabel[7];
        //quantityText  = new TextFieldWithLimit[7];
        //codeDropDown = new JComboBox[2];
        Object [] seriesNameArray = SamsUtilities.fillSeriesInformation();
        
        //customerNameText = new TextFieldWithLimit[NUM_ROWS];
        rcptText = new TextFieldWithLimit[NUM_ROWS];
        seriesDropDown = new JComboBox[NUM_ROWS];
        
        //DropDowns
        //fill the information from the database while initialization
        for(int i = 0; i < NUM_ROWS; i+=2)
        {
            
            rcptText[i] = new TextFieldWithLimit(5, 5);
            //rcptText[i].addActionListener(this);
            seriesDropDown[i] = new JComboBox(seriesNameArray);
            rcptText[i+1] = new TextFieldWithLimit(5, 5);
            seriesDropDown[i+1] = new JComboBox(seriesNameArray);
        }
        
        
        stallDropDown = new JComboBox(stalls);
        
        
        //TextFields
        entryDateText = new TextFieldWithLimit( 2 , 2 );
        entryMonthText = new TextFieldWithLimit( 2 , 2 );
        entryYearText = new TextFieldWithLimit( 4 , 4 );
        pageNumberText = new TextFieldWithLimit( 4 , 4 );
        
        entryMonthText.setText(""+SamsUtilities.getCurrentMonth());
        entryYearText.setText(""+SamsUtilities.getCurrentYear());
        
        //quantityText  = new TextFieldWithLimit( 5 , 5 );
        
        
        
        
        //Buttons
        okButton = new JButton("OK");
        okButton.setMnemonic('o');
        okButton.addActionListener(this);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('c');
        cancelButton.addActionListener(this);
        
        printButton = new JButton("Print");
        printButton.setMnemonic('p');
        printButton.addActionListener(this);
        
        //adding the gui elements to frame and setting the layout simultaneously
        
        satSandeshAccountBookPostingWindow.add(stallLabel,"span 1, w 100, align right");
        satSandeshAccountBookPostingWindow.add(stallDropDown, "span 3, w 150!");
        satSandeshAccountBookPostingWindow.add(pageNumberLabel,"span 2, align right");
        satSandeshAccountBookPostingWindow.add(pageNumberText,"span 1");
        satSandeshAccountBookPostingWindow.add(entryDateLabel,"span 2, align right");
        satSandeshAccountBookPostingWindow.add(entryDateText, "split 5, w 30!");
        satSandeshAccountBookPostingWindow.add(entryMonthText,"span 1");
        satSandeshAccountBookPostingWindow.add(entryYearText, "span 1, wrap 15px");
        
        
        entryPanel.add(seriesFirstLabel," span 1, w 100");
        entryPanel.add(seriesSecondLabel," span 1, gapleft 10");
        entryPanel.add(rcptFirstLabel," span 1");
        entryPanel.add(rcptSecondLabel," span 1, wrap 2px");
        
        for(int __x = 0; __x < NUM_ROWS; __x+=2)
        {
            
            entryPanel.add(seriesDropDown[__x],"span 1, w :130:");
            entryPanel.add(rcptText[__x],"span 1, w :130:, gapright 200");
            
            entryPanel.add(seriesDropDown[__x+1],"span 1, w :130:");
            entryPanel.add(rcptText[__x+1],"span 1, w :130:, wrap 2px");
            
            
        }
        
        scrollPane = new JScrollPane(entryPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        satSandeshAccountBookPostingWindow.add(scrollPane,"span, wrap");
        
        //satSandeshInventoryIssueWindow.add(quantityLabel );
        //satSandeshInventoryIssueWindow.add(rcptText, "span 2, w 100!");
        satSandeshAccountBookPostingWindow.add(okButton, "w :90:, span 5, align right");
        satSandeshAccountBookPostingWindow.add(cancelButton, " w :90: ,span 4, align center");
        
        //PRINT BUTTON HAS BEEN DISABLED as there is no need for it in this window
        //satSandeshAccountBookPostingWindow.add(printButton, "w :90: ,span 4, align left");
        
        //populate information from datatbase
        //fillSeriesNameInformation();
        
        //this is the last statement in the constructor, to make the frame visible
        // all the population of data structures has to be done before showing the frame
        satSandeshAccountBookPostingWindow.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        
        
        if(event.getSource() == okButton)
        {
            int status = 1;
            String counter = (String)stallDropDown.getSelectedItem();
            Vector<String> queryVec = new Vector<String>();
            String pageNumber = pageNumberText.getText();
            
            if(pageNumber.isEmpty())
            {
                JOptionPane.showMessageDialog(satSandeshAccountBookPostingWindow, "Please enter page number");
                return;
            }
            
            //Font font = new Font();
            
            //gather the info from user input into the frame
            String issueDate = entryYearText.getText()+"-"+entryMonthText.getText()+"-"+entryDateText.getText();
            for(int i = 0; i < NUM_ROWS; i++)
            {
                String seriesName = (String)seriesDropDown[i].getSelectedItem();
                //System.out.println(seriesName);
                if(!seriesName.isEmpty())
                {
                    String rcptNumStr = (String)rcptText[i].getText();
                    
                    if(
                            !counter.isEmpty()
                            && !issueDate.isEmpty()
                            && !pageNumber.isEmpty()
                            && !rcptNumStr.isEmpty()
                            )
                    {
                        
                        if(rcptNumStr.isEmpty())
                        {
                            status &= 0;
                            continue;
                        }
                        int rcptNum = Integer.parseInt(rcptNumStr);    
                        
                        if(rcptNum < 1)
                        {
                            JOptionPane.showMessageDialog(satSandeshAccountBookPostingWindow, "Please verify the details entered");
                            status &= 0;
                            continue;
                        }
                        
                        {
                            String sqlQuery = "update basic set page_number = "+pageNumber+" where rcpt = "+rcptNum+" and series_name = '"+seriesName+"'";
                            //System.out.println(sqlQuery);
                            queryVec.addElement(sqlQuery);
                        }
                    }
                    else
                    {
                        status &= 0;
                        JOptionPane.showMessageDialog(satSandeshAccountBookPostingWindow, "Please fill receipt number");
                    }
                }
            }
            if(status == 1)
            {
                int option = JOptionPane.showConfirmDialog(satSandeshAccountBookPostingWindow, "Are you sure ?");
                if(option == 0)
                {
                    for(int i = 0; i < queryVec.size(); i++)
                    {
                        //int option = JOptionPane.showConfirmDialog(satSandeshAccountBookPostingWindow, "Are you sure ?");
                        //System.out.println("option :: "+option);
                        //if(option == 0)
                        String sqlQuery = queryVec.elementAt(i);
                        {
                            connect updateconnection = new connect();
                            try
                            {
                                //System.out.println(sqlQuery);
                                updateconnection.a = updateconnection.st.executeUpdate(sqlQuery);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            updateconnection.closeAll();
                            
                            
                            rcptText[i].setText("");
                            seriesDropDown[i].setSelectedItem("");
                            pageNumberText.setText("");
                        }
                    }
                    
                }
                
                //else
                //    JOptionPane.showMessageDialog(satSandeshAccountBookPostingWindow, "Please fill all the fields");
                //}
                //}
                entryDateText.setText("");
                entryMonthText.setText(""+SamsUtilities.getCurrentMonth());
                entryYearText.setText(""+SamsUtilities.getCurrentYear());
            }
        }
        else if(event.getSource() == cancelButton)
        {
            satSandeshAccountBookPostingWindow.setVisible(false);
            new sams();
            satSandeshAccountBookPostingWindow.dispose();
            
        }
        
        else if(event.getSource() == printButton)
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            
            int res = JOptionPane.showConfirmDialog(null,"You want to configure your print ","** PRINTING **", JOptionPane.YES_NO_OPTION);
            if( res == JOptionPane.YES_OPTION ) {
                //if (res == JOptionPane.YES_OPTION) (
                //	PageFormat format = job.pageDialog(job.defaultPage());
                PageFormat format = job.pageDialog (job.defaultPage ());
            } //)
            if( res == JOptionPane.NO_OPTION ) {
                PageFormat format =new PageFormat();
                format.setOrientation(PageFormat.LANDSCAPE);
            }
            
            
            boolean ok = job.printDialog();
            if (ok)
            {
                try
                {
                    job.print();
                }
                catch (PrinterException ex)
                {
                    
                }
            }
            
        }
        
        
    }
    

    public static void main(String args[])
    {
        new SatSandeshAccountBookPosting();
    }
    
    
    
    
    /*static public Object[] fillSeriesNameInformation()
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
    }*/
    
    @Override
    public int print(Graphics graphics, PageFormat pf, int page) throws
            PrinterException {
        
        pf.setOrientation(PageFormat.LANDSCAPE);
        
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        
        /* User (0,0) is typically outside the imageable area, so we must
        * translate by the X and Y values in the PageFormat to avoid clipping
        */
        
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        //System.out.println(pf.getImageableWidth()/satSandeshInventoryIssueWindow.getWidth()+" "+pf.getImageableHeight()/satSandeshInventoryIssueWindow.getHeight());
        
        g2d.scale(pf.getImageableWidth()/satSandeshAccountBookPostingWindow.getWidth(), pf.getImageableHeight()/satSandeshAccountBookPostingWindow.getHeight());
        
        /* Now print the window and its visible contents */
        satSandeshAccountBookPostingWindow.printAll(g2d);
        
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
}
