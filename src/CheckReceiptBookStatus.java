import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import net.miginfocom.swing.MigLayout;

public class CheckReceiptBookStatus extends JFrame implements ActionListener
{
    
    public static void main(String args[])
    {
        new CheckReceiptBookStatus(2601,2650,"2015");
    }
    
    int startNum,endNum;
    int k=0;
    int count=0;
    int unusedReceipts[];
    int j=0;
    //JLabel[] unusedRcptLabel, unaccountedReceipts;
    //JLabel unusedRcptLabel;
    JButton backButton;
    MigLayout mLayout= new MigLayout( "insets 20");
    //MigLayout pLayout= new MigLayout( "insets 20");
    
    JScrollPane unusedScrollPane, unaccountedScrollPane;
    JTable unusedTable, unaccountedTable;
    
    public CheckReceiptBookStatus(int fromRcptNumber, int toRcptNumber, String series)
    {
        //System.out.println(fromRcptNumber + " :: " + toRcptNumber + " :: " + series);
        
        
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
        
        
        //setLocation(10,10);
        this.setTitle("Receipt Book Status");
        this.setLayout(mLayout);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        startNum=fromRcptNumber;
        endNum=toRcptNumber;
        JLabel l1=new JLabel("Unused Receipt Numbers");
        //l1.setBounds(290,50,3000,30);
        
        JLabel unaccountedRcptLabel=new JLabel("Unaccounted Receipt Numbers");
        
        
        //int flag=0;
        try
        {
            
            connect c1=new connect();
            
            //String sqlQuery = "select count(distinct(rcpt)) from basic where ( rcpt < "+(endNum+1) +" and rcpt > "+(startNum-1)+" ) and series_name = '"+series+"' ";
            String sqlQuery = "select count(distinct(receipt_number)) from receipt_book_details where ( receipt_number < "+(endNum+1) +" and receipt_number > "+(startNum-1)+" ) and series_name = '"+series+"' ";
            //System.out.println(sqlQuery);
            c1.rs=c1.st.executeQuery(sqlQuery);
            if(c1.rs.next())
                k = c1.rs.getInt(1);
            //System.out.println(k);
              
            c1.closeAll();
            //System.out.println("k : "+k);
            int free = (endNum - startNum - k + 1);
            if(free < 0)
            {
                JOptionPane.showMessageDialog(this, "Please check from and to numbers", "ERROR", JOptionPane.ERROR_MESSAGE);;
                return;
            }
            //System.out.println("free : "+free);
            unusedReceipts = new int[free+1];
            //unusedReceipts = new int[endNum - startNum];
            SortedSet set = new TreeSet();
            j=0;
            connect c2=new connect();
            for(count = startNum; count <= endNum; count++)
            {
                k=0;
                //String searchQuery = "select count(asn) from receipt_book_details where series_name = '"+series+"' and rcpt = "+count;
                String searchQuery = "select count(asn) from receipt_book_details where series_name = '"+series+"' and receipt_number = "+count;
                //System.out.println(searchQuery);
                c2.rs=c2.st.executeQuery(searchQuery);
                //flag=0;
                if(c2.rs.next())
                    k = c2.rs.getInt(1);
                //System.out.println(k);
                
                if(k == 0)
                {
                    unusedReceipts[j] = count;
                    set.add(count);
                    j++;
                }
            }
            
            c2.closeAll();
            
            this.add(l1,"span,  wrap , align center");
            
            Object col[] = {"","","","","","","","","","","","","","",""}; 
            Object[][] unusedData= new Object[(free/15)+1][15];
            
            for(int _count = 0 ; _count < unusedReceipts.length - 1 ; _count++)
            {
                int _row = _count/15;
                int _column = _count%15;
                unusedData[_row][_column] = unusedReceipts[_count];
                //System.out.println(unusedReceipts[_count] + " " + _row + " " + _column + " " + unusedData[_row][_column]);
            }
            
            
            
            unusedTable = new JTable(unusedData,col);
            unusedTable.setFont(new Font("Serif", Font.PLAIN, 15));
            unusedTable.setShowGrid(true);
            //reportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            //reportTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            //for(int i = 0; i < col.length ; i++ )
            //    unusedTable.getColumnModel().getColumn(i).setPreferredWidth(100);
            //reportTable.setRowHeight(28);
            
            
            unusedScrollPane =new JScrollPane(unusedTable);
            unusedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            unusedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.add(unusedScrollPane,"span, wrap, width 700!, height 150!");
            
            
            //=============== Section 2==============================//
            this.add(unaccountedRcptLabel,"span, wrap, align center");
            
            //select rcpt from basic where rcpt > 2999 and rcpt < 3051 and series_name = '2015' and page_number = 0;
            
            Object[][] unaccountedData = fillUnaccountedReceiptDetails(startNum, endNum, series, set);
            unaccountedTable = new JTable(unaccountedData,col);
            unaccountedTable.setFont(new Font("Serif", Font.PLAIN, 15));
            unaccountedTable.setShowGrid(true);
            
            unaccountedScrollPane =new JScrollPane(unaccountedTable);
            unaccountedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            unaccountedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.add(unaccountedScrollPane,"span, wrap, width 700, height 150!");
            
            backButton=new JButton("Back");
            this.add(backButton, "align center");
            backButton.addActionListener(this);
            backButton.setMnemonic('B');

            this.setSize(780,500);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //this.setSize(780,(screenSize.height*7)/10);
            Dimension frameSize = this.getSize();
            int x = (screenSize.width - frameSize.width)/2;
            int y = (screenSize.height - frameSize.height)/2;
            this.setLocation(x, y);
            setVisible(true);
        }
        catch(Exception e)
        {
            System.out.println(""+e);
            e.printStackTrace();
            new except(e, this.getClass().toString());
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == backButton)
        {
            this.setVisible(false);
            new CheckReceiptBookDetailsInput();
            this.dispose();
        }
        
    }
    
    final Object[][] fillUnaccountedReceiptDetails(int startNum, int endNum, String series, SortedSet set)
    {
        Object data[][] = new Object[1][15];
        
        try
        {
            //int unaccountedReceipts[];
            connect c1=new connect();
            int k = 0;
            int unaccountedReceiptCount = 0;
            //select rcpt from basic where rcpt > 2999 and rcpt < 3051 and series_name = '2015' and page_number = 0;
            String sqlQuery = "select count(distinct(receipt_number)) from receipt_book_details where ( receipt_number < "+(endNum+1) +" and receipt_number > "+(startNum-1)+" ) and series_name = '"+series+"' and page_number = 0";
            //System.out.println(sqlQuery);
            c1.rs=c1.st.executeQuery(sqlQuery);
            if(c1.rs.next())
                k = c1.rs.getInt(1);
            unaccountedReceiptCount = k;
            
            //System.out.println(k);
              
            c1.closeAll();
            //System.out.println("k : "+k);
            //int free = (endNum - startNum - k + 1);
            //System.out.println("free : "+free);
            //unaccountedReceipts = new int[k+1];
            //unusedReceipts = new int[endNum - startNum];
            
            j=0;
            connect c2=new connect();
            for(count = startNum; count <= endNum; count++)
            {
                k=0;
                //String searchQuery = "select count(asn) from basic where page_number = 0 and series_name = '"+series+"' and rcpt = "+count;
                String searchQuery = "select count(asn) from receipt_book_details where page_number = 0 and series_name = '"+series+"' and receipt_number = "+count;
                //System.out.println(searchQuery);
                c2.rs=c2.st.executeQuery(searchQuery);
                //flag=0;
                if(c2.rs.next())
                    k = c2.rs.getInt(1);
                //System.out.println(k);
                
                if(k != 0)
                {
                    //unaccountedReceipts[j] = count;
                    set.add(count);
                    j++;
                }
            }
            
            c2.closeAll();
            
            
            data = new Object[(set.size()/15)+1][15];
            int _count = 0;
            //set.toArray(unaccountedReceipts);
            //for(int _count = 0 ; _count < unaccountedReceipts.length - 1 ; _count++)
            //for(int _count = 0 ; _count < set.leng - 1 ; _count++)
            for(final Iterator it = set.iterator(); it.hasNext(); )
            {
                
                Object elem = it.next();
                int _row = _count/15;
                int _column = _count%15;
                data[_row][_column] = elem;
                _count++;
                //System.out.println(unaccountedReceipts[_count] + " " + _row + " " + _column + " " + data[_row][_column]);
            }
            
            return data;
            
        }
        catch(Exception e)
        {
            System.out.println(""+e);
            e.printStackTrace();
            new except(e, this.getClass().toString());
        }
        
        return data;
    }
    
}