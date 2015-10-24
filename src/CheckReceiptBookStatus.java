import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

public class CheckReceiptBookStatus extends JFrame implements ActionListener
{
    
    public static void main(String args[])
    {
        new CheckReceiptBookStatus(1001,3050,"2015");
    }
    
    int startNum,endNum;
    int k=0;
    int count=0;
    int[] unusedReceipts;
    int j=0;
    JLabel[] unusedRcptLabel;
    //JLabel unusedRcptLabel;
    JButton backButton;
    
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
        setTitle("Receipt Book Status");
        setLayout(null);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        startNum=fromRcptNumber;
        endNum=toRcptNumber;
        JLabel l1=new JLabel("Unused Receipt Numbers");
        add(l1);
        l1.setBounds(290,50,3000,30);
        //int flag=0;
        try
        {
            connect c1=new connect();
            //for(count = startNum; count <= endNum ; count++)
            //{
            String sqlQuery = "select count(distinct(rcpt)) from basic where ( rcpt < "+(endNum+1) +" and rcpt > "+(startNum-1)+" ) and series_name = '"+series+"' ";
            //System.out.println(sqlQuery);
            c1.rs=c1.st.executeQuery(sqlQuery);
            if(c1.rs.next())
                k = c1.rs.getInt(1);
            //System.out.println(k);
              
            c1.closeAll();
            //System.out.println("k : "+k);
            int free = (endNum - startNum - k + 1);
            //System.out.println("free : "+free);
            unusedReceipts = new int[free+1];
            //unusedReceipts = new int[endNum - startNum];
            
            j=0;
            //int foundCount = 0;
            String unusedRcptString = "<html><p>";
            connect c2=new connect();
            for(count = startNum; count <= endNum; count++)
            {
                k=0;
                String searchQuery = "select count(asn) from basic where series_name = '"+series+"' and rcpt = "+count;
                //System.out.println(searchQuery);
                c2.rs=c2.st.executeQuery(searchQuery);
                //flag=0;
                if(c2.rs.next())
                    k = c2.rs.getInt(1);
                //System.out.println(k);
                
                if(k == 0)
                {
                    unusedReceipts[j] = count;
                    unusedRcptString += " " + count;
                    //System.out.println(j);
                    //System.out.println(unusedReceipts[j]);
                    j++;
                }
               
                
            }
            //System.out.println(foundCount);
            c2.closeAll();
            unusedRcptString += "</p></html>";
            System.out.println(unusedRcptString);
            unusedRcptLabel = new JLabel[free];
            //unusedRcptLabel = new JLabel();
            
            /*int i1=free/15;
            if(free%15>0)
                i1++;*/
            
            
            for(count = 0; count < free; count++ )
            {
                unusedRcptLabel[count]=new JLabel(""+unusedReceipts[count]);
                unusedRcptLabel[count].setBounds(30+(count%15)*50,100+(count/15)*30,40,20);
                add(unusedRcptLabel[count]);
            }
            /*unusedRcptLabel.setBounds(30,80,40+((j%15)*50),(j%15)*30);
            unusedRcptLabel.setText(unusedRcptString);
            this.add(unusedRcptLabel);*/
            
            
            backButton=new JButton("Back");
            backButton.setBounds(330,150+(j/15)*30,80,20);
            add(backButton);
            backButton.addActionListener(this);
            backButton.setMnemonic('B');

            setSize(780,230+(j/15)*30);
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
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == backButton)
        {
            this.setVisible(false);
            new CheckReceiptBookDetailsInput();
            this.dispose();
        }
        
    }
    
}