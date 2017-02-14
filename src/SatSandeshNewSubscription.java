
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;




public class SatSandeshNewSubscription extends JFrame implements ActionListener, ItemListener, FocusListener, Runnable
{
    public static void main(String args[])
    {
        SatSandeshNewSubscription e=new SatSandeshNewSubscription();
    }
    
    JLabel  subd1, asn1, sub1, status1, seriesLabel, rec1, dist1, d1, subt1, lang1;				//subscription details
    JLabel pay1, payt1, chdd1, dat1, am1, starm1,stary1, end1, newRenewLabel;						//payment details
    JLabel mem1, tit1, nam1, lnam1, add1, dis1, stat1, pin1;						//member details
    JLabel oth1, tel1, counterLabel, email1, ret1, rem1, subIssueCounterLabel; 									//other details
    int endm2, endy2;
    Thread t=null;
    
    Font f=new Font("ARIAL", Font.BOLD, 12);
    
    JPanel p1, p2, p3, p4,p5;
    
    
    JTextField asnt1, statust1 /*, distributionTypeDropDown, subscriptionDurationDropDown , langt1*/;						//subscription details
    JComboBox distributionTypeDropDown,  subscriptionDurationDropDown, langt1, distributionCodeDropDown, seriesDropDown;
    TextFieldWithLimit subNumberText, receiptNumberText;
    JComboBox subNumberCodeDropDown;
    
    int currMonth, currYear;
    
    JTextField /*paytt1,*/ chddt1;											//payment details
    TextFieldWithLimit /*dateText, monthText, yearText,*/ amt1, endt1, newRenewText;//starty1, startm1,
    JComboBox paytt1, starty1, startm1, stateCodeDropDown;
    
    
    TextFieldWithLimit titt1, namt1, lnamt1, pint1,addt11,addt21,addt31,dist21;	//member details
    JComboBox stateNameDropDown;
    
    JTextField  rett1;											//other details
    TextArea remt1;
    TextFieldWithLimit telt1,emailt1, subIssueCounterText;
    JComboBox counterDropDown;
    
    JButton sav, mod, clear, back;
    int x, amount;
    String str;
    int flag=0;
    int s2=0;
    Object[] items;
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    
    protected final void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        int xDim = (screenSize.width - frameSize.width) / 2;
        int yDim = (screenSize.height - frameSize.height) / 2;
        setLocation(xDim, yDim);
    }
    
    public SatSandeshNewSubscription()
    {
        
        
        try
        {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            JOptionPane.showMessageDialog(this, "ERROR : "+cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        flag=0;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("New Subscription");
        setSize(1050,690);
        //this.setSize(this.getToolkit().getScreenSize());
        //setResizable(false);
        setLocation(10,10);
        setLayout(new GridLayout(5,1));
        center();
        
        GregorianCalendar cal=new GregorianCalendar();
        
        currMonth = (cal.get(Calendar.MONTH)+1);
        currYear = cal.get(Calendar.YEAR);
        
        p1=new JPanel(null);
        //p1.setLayout(null);
        p2=new JPanel(null);
        //p2.setLayout(null);
        p3=new JPanel(null);
        //p3.setLayout(null);
        p4=new JPanel(null);
        //p4.setLayout(null);
        p5=new JPanel(null);
        //p5.setLayout(null);
        
        
        asn1=new JLabel("ASN");
        sub1=new JLabel("SUB No");
        status1=new JLabel("Status");
        seriesLabel = new JLabel("<html><b>-</b></html>");
        rec1=new JLabel("Reciept No");
        dist1=new JLabel("Distribution type");
        d1=new JLabel("D#");
        subt1=new JLabel("Subscription Type");
        lang1=new JLabel("Language");
        subd1=new JLabel("SUBSCRIPTION DETAILS");
        pay1=new JLabel("PAYMENT AND PERIOD DETAILS");
        payt1=new JLabel("Payment Type");
        chdd1=new JLabel("CH/ DD/ MO No");
        dat1=new JLabel("DATE");
        am1=new JLabel("Amount");
        starm1=new JLabel("Starting Period");
        stary1=new JLabel("/");
        end1=new JLabel("Ending Period");
        newRenewLabel = new JLabel("Type");
        mem1=new JLabel("SUBSCRIBER DETAILS");
        tit1=new JLabel("Title");
        nam1=new JLabel("Name");
        lnam1=new JLabel("LName");
        add1=new JLabel("Address");
        dis1=new JLabel("District");
        stat1=new JLabel("State");
        pin1=new JLabel("Pin Code");
        oth1=new JLabel("OTHER DETAILS");
        tel1=new JLabel("Telephone");
        counterLabel=new JLabel("Counter");
        email1=new JLabel("e-mail");
        ret1=new JLabel("Return Back");
        subIssueCounterLabel = new JLabel("<html>Sub Counter</html>");
        rem1=new JLabel("Remarks");
        
        asnt1=new JTextField(8);
        subNumberText=new TextFieldWithLimit(5,5);
        statust1=new JTextField(20);
        receiptNumberText=new TextFieldWithLimit(5,5);
        distributionTypeDropDown=new JComboBox();
        
        //Object[] items={"BH","BD","CM","DL","EN","HR","LF","LH","MH","MP","MS","PB","PJ","RJ","UK","UP","UR"};
        items = SamsUtilities.getSubCodes();
        subNumberCodeDropDown=new JComboBox(items);
        subNumberCodeDropDown.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        //subt22.setAutoscrolls(true);
        //subt22.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
        //subt22.setOpaque(true);
        //subt22.setDoubleBuffered(true);
        //subt22.addItemListener(this);
        //subt22.setPopupVisible(true);
        
        distributionCodeDropDown=new JComboBox();
        subscriptionDurationDropDown=new JComboBox();
        langt1=new JComboBox();
        paytt1=new JComboBox();
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        
        
        chddt1=new JTextField(9);
        //dateText=new TextFieldWithLimit(2,2);
        //monthText=new TextFieldWithLimit(2,2);
        //yearText=new TextFieldWithLimit(4,4);
        amt1=new TextFieldWithLimit(4,4);
        //startm1=new TextFieldWithLimit(2,2);
        //starty1=new TextFieldWithLimit("",4,4);
        endt1=new TextFieldWithLimit(10,10);
        newRenewText = new TextFieldWithLimit(6, 6);
        titt1=new TextFieldWithLimit(3,3);
        namt1=new TextFieldWithLimit(16,16);
        lnamt1=new TextFieldWithLimit(15,15);
        addt11=new TextFieldWithLimit(32,32);
        addt21=new TextFieldWithLimit(32,32);
        addt31=new TextFieldWithLimit(32,32);
        dist21=new TextFieldWithLimit(22,22);
        stateCodeDropDown = new JComboBox(SamsUtilities.fillStateCodeList());
        stateNameDropDown = new JComboBox(SamsUtilities.fillStateNameList());
        pint1=new TextFieldWithLimit(6,6);
        telt1=new TextFieldWithLimit(12,12);
        
        emailt1=new TextFieldWithLimit(100,100);
        subIssueCounterText = new TextFieldWithLimit(32,32);
        rett1=new JTextField(5);
        remt1=new TextArea(85,3);
        
        counterDropDown=new JComboBox();
        
        counterDropDown.addItem("Kirpal Bagh");
        counterDropDown.addItem("Kirpal Ashram");
        counterDropDown.addItem("Sawan Ashram");
        //counterDropDown.addItem("Darshan Dham");
        //counterDropDown.addItem("Tours/Function");
        
        
        sav=new JButton("Save");
        back=new JButton("Back");
        clear = new JButton("Clear");
        
        
        
        //-----------------------------------------modififcations------------------------------------------//
        
        try
        {
            distributionCodeDropDown.addItem("0");
            connect c13=new connect();
            c13.rs=c13.st.executeQuery("select dno from despcode order by dno");
            while(c13.rs.next())
            {
                distributionCodeDropDown.addItem(""+c13.rs.getInt(1));
            }
            
            c13.st.close();
            c13.con.close();
            
        }
        catch(Exception e)
        {
            
        }
        distributionCodeDropDown.addItemListener(this);
        
        startm1=new JComboBox();
        starty1=new JComboBox();
        
        for(int s=1;s<13;s++)
        {
            startm1.addItem(""+s);
        }
        
        
        s2=cal.get(Calendar.YEAR);
        
        for(int s1=(s2-2);s1<(s2+10);s1++)
        {
            starty1.addItem(""+s1);
        }
        
        starty1.setSelectedItem(""+s2);
        int s3=cal.get(Calendar.MONTH)+1;
        
        startm1.setSelectedItem(""+s3);
        startm1.addItemListener(this);
        starty1.addItemListener(this);
        
        //------------------------------------------------end------------------------------------------------//
        
        
        distributionTypeDropDown.addItem("By Hand");
        distributionTypeDropDown.addItem("By Post");
        distributionTypeDropDown.addItem("Distributor");
        
        
        
        subscriptionDurationDropDown.addItem("1 year");
        subscriptionDurationDropDown.addItem("2 year");
        subscriptionDurationDropDown.addItem("2 year plus");
        subscriptionDurationDropDown.addItem("3 year");
        subscriptionDurationDropDown.addItem("3 year plus");
        subscriptionDurationDropDown.addItem("5 year");
        subscriptionDurationDropDown.addItem("5 year plus");
        subscriptionDurationDropDown.addItem("Life");
        subscriptionDurationDropDown.addItem("Comp");
        
        
        langt1.addItem("Hindi");
        langt1.addItem("English");
        langt1.addItem("Urdu");
        langt1.addItem("Punjabi");
        
        paytt1.addItem("Cash");
        paytt1.addItem("CH/DD/MO");
        
        p1.add(subd1);
        subd1.setBounds(30,10,200,30);
        
        p1.add(asn1);
        asn1.setBounds(30,45,30,20);
        
        p1.add(asnt1);
        asnt1.setBounds(90,45,80,20);
        try
        {
            connect c1=new connect();
            c1.rs=c1.st.executeQuery("select * from asn");
            
            while(c1.rs.next())
            {
                x=c1.rs.getInt(1);
            }
            
            c1.st.close();
            c1.con.close();
        }
        catch(Exception e1)
        {
            
        }
        
        
        asnt1.setText("SN"+x);
        asnt1.setEnabled(false);
        asnt1.setFont(f);
        
        statust1.setText("Active");
        statust1.setEnabled(false);
        statust1.setFont(f);
        
        
        p1.add(sub1);
        sub1.setBounds(220,45,80,20);
        
        
        subNumberCodeDropDown.addItemListener(this);
        subNumberCodeDropDown.setBounds(300,45,50,20);
        p1.add(subNumberCodeDropDown);
        
        
        subNumberText.setBounds(360,45,60,20);
        p1.add(subNumberText);
        subNumberText.addFocusListener(this);
        
        p1.add(status1);
        status1.setBounds(430,45,40,20);
        
        p1.add(statust1);
        statust1.setBounds(500,45,80,20);
        
        p1.add(rec1);
        rec1.setBounds(650,45,100,20);
        
        p1.add(seriesDropDown);
        seriesDropDown.setBounds(730,45,80,20);
        
        p1.add(seriesLabel);
        seriesLabel.setBounds(820,45,20,20);
        
        p1.add(receiptNumberText);
        receiptNumberText.setBounds(840,45,40,20);
        receiptNumberText.addFocusListener(this);
        
        p1.add(dist1);
        dist1.setBounds(30,75,100,20);
        
        p1.add(distributionTypeDropDown);
        distributionTypeDropDown.addItemListener(this);
        distributionTypeDropDown.setBounds(140,75,100,20);
        
        p1.add(d1);
        d1.setBounds(280,75,40,20);
        
        p1.add(distributionCodeDropDown);
        distributionCodeDropDown.setEnabled(false);
        distributionCodeDropDown.setFont(f);
        distributionCodeDropDown.setSelectedItem("0");
        distributionCodeDropDown.setBounds(330,75,50,20);
        
        p1.add(lang1);
        lang1.setBounds(480,75,110,20);
        
        p1.add(langt1);
        langt1.setBounds(600,75,80,20);
        langt1.addItemListener(this);
        
        p1.add(subt1);
        subt1.setBounds(750,75,110,20);
        
        p1.add(subscriptionDurationDropDown);
        subscriptionDurationDropDown.setBounds(870,75,100,20);
        subscriptionDurationDropDown.addItemListener(this);
        
        p2.add(pay1);
        pay1.setBounds(30,0,200,30);
        
        p2.add(payt1);
        payt1.setBounds(30,45,130,20);
        
        
        p2.add(paytt1);
        paytt1.setBounds(130,45,100,20);
        paytt1.addItemListener(this);
        
        p2.add(chdd1);
        chdd1.setBounds(250,45,100,20);
        
        p2.add(chddt1);
        chddt1.setBounds(350,45,60,20);
        chddt1.setEnabled(false);
        chddt1.setFont(f);
        chddt1.setText("0");
        
        p2.add(dat1);
        dat1.setBounds(430,45, 50,20);
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());   
        }
        
        datePicker.setBounds(490,45,155,25);
        p2.add(datePicker);
        
        /*p2.add(dateText);
        dateText.setBounds(470,45,25,20);
        
        p2.add(monthText);
        monthText.setText(""+currMonth);
        monthText.setBounds(500,45,25,20);
        
        p2.add(yearText);
        yearText.setText(""+currYear);
        yearText.setBounds(530,45,40,20);*/
        
        
        p2.add(am1);
        am1.setBounds(660,45,50,20);
        
        p2.add(amt1);
        amt1.setBounds(720,45,40,20);
        amt1.setEnabled(false);
        
        amt1.setFont(f);
        amt1.setText("100");
        
        //------------------------------------------------start------------------------------------------------//
        
        p2.add(starm1);
        starm1.setBounds(30,75,100,20);
        
        p2.add(startm1);
        startm1.setBounds(140,75,65,20);
        
        p2.add(stary1);
        stary1.setBounds(210,75,10,20);
        
        p2.add(starty1);
        starty1.setBounds(225,75,90,20);
        
        p2.add(end1);
        end1.setBounds(330,75,90,20);
        
        p2.add(endt1);
        endt1.setBounds(430,75,70,20);
        endt1.setEnabled(false);
        endt1.setFont(f);
        
        p2.add(newRenewLabel);
        newRenewLabel.setBounds(510,75,80,20);
        
        p2.add(newRenewText);
        newRenewText.setBounds(600,75,45,20);
        newRenewText.setEnabled(false);
        newRenewText.setText("New");
        newRenewText.setFont(f);
        
        
        
        //------------------------------------------------end------------------------------------------------//
        
        p3.add(mem1);
        mem1.setBounds(30,0,200,30);
        
        p3.add(tit1);
        tit1.setBounds(30,45,50,20);
        
        p3.add(titt1);
        titt1.setBounds(80,45,30,20);
        
        p3.add(nam1);
        nam1.setBounds(120,45,40,20);
        
        p3.add(namt1);
        namt1.setBounds(160,45,145,20);
        
        p3.add(lnam1);
        lnam1.setBounds(310,45,40,20);
        
        p3.add(lnamt1);
        lnamt1.setBounds(350,45,145,20);
        
        p3.add(add1);
        add1.setBounds(520,45,60,20);
        
        p3.add(addt11);
        addt11.setBounds(600,35,240,20);
        p3.add(addt21);
        addt21.setBounds(600,60,240,20);
        p3.add(addt31);
        addt31.setBounds(600,85,240,20);
        
        p3.add(dis1);
        dis1.setBounds(30,110,60,20);
        
        p3.add(dist21);
        dist21.setBounds(90,110,240,20);
        
        p3.add(stat1);
        stat1.setBounds(340,110,40,20);
        
        p3.add(stateNameDropDown);
        stateNameDropDown.setBounds(380,110,200,20);
        stateNameDropDown.addItemListener(this);
        
        p3.add(stateCodeDropDown);
        stateCodeDropDown.setBounds(590,110,80,20);
        stateCodeDropDown.setEnabled(false);
        //stateCodeDropDown.addItemListener(this);
        
        p3.add(pin1);
        pin1.setBounds(680,110,90,20);
        
        p3.add(pint1);
        pint1.setText("0");
        pint1.setBounds(770,110,50,20);
        
        p4.add(oth1);
        oth1.setBounds(30,10,200,30);
        
        p4.add(tel1);
        tel1.setBounds(30,45,80,20);
        
        p4.add(telt1);
        telt1.setBounds(110,45,110,20);
        
        p4.add(counterLabel);
        counterLabel.setBounds(240,45,60,20);
        
        p4.add(counterDropDown);
        counterDropDown.setBounds(300,45,150,20);
        
        p4.add(email1);
        //emailt1.setEnabled(false);
        email1.setBounds(30,105,50,20);
        
        
        
        p4.add(emailt1);
        //emailt1.setEnabled(false);
        emailt1.setBounds(110,105,380,20);
        //setBounds(550,45,240,20);
        
        p4.add(ret1);
        ret1.setBounds(30,75,90,20);
        
        p4.add(rett1);
        //rett1.setEnabled(false);
        rett1.setText("0");
        rett1.setBounds(110,75,80,20);
        
        p4.add(subIssueCounterLabel);
        subIssueCounterLabel.setBounds(240,75,90,20);
        
        p4.add(subIssueCounterText);
        subIssueCounterText.setBounds(320,75,180,20);
        
        p4.add(rem1);
        rem1.setBounds(470,45,60,20);
        
        p4.add(remt1);
        
        remt1.setBounds(550,45,440,80);
        
        p5.add(sav);
        sav.setBounds(340,10,100,30);
        sav.setMnemonic('S');
        
        /*p5.add(mod);
        * mod.setMnemonic('M');
        * mod.setBounds(220,10,100,30);
        */
        
        p5.add(clear);
        clear.setMnemonic('C');
        clear.setBounds(580,10,100,30);
        
        p5.add(back);
        back.setMnemonic('B');
        back.setBounds(460,10,100,30);
        
        sav.addActionListener(this);
        //mod.addActionListener(this);
        clear.addActionListener(this);
        back.addActionListener(this);
        
        //p4.add(counterDropDown);
        
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        
        
        setVisible(true);
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        //database updation
        if(ae.getSource()==sav)
        {
            try
            {
                flag=0;
                int asn, rcpt,subno, dno;
                String subno1, dist, subt, lang,status;
                
                //basic info
                
                asn=x;
                
                String seriesName = (String)seriesDropDown.getSelectedItem();
                
                if(seriesName.isEmpty())
                {
                    JOptionPane.showMessageDialog(this,"Please select series", "Please select series", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                rcpt=Integer.parseInt(receiptNumberText.getText());
                dno=Integer.parseInt((String)distributionCodeDropDown.getSelectedItem());
                subno1=(String)subNumberCodeDropDown.getSelectedItem();
                subno=Integer.parseInt(subNumberText.getText());
                dist=(String)distributionTypeDropDown.getSelectedItem() ;
                subt=(String)subscriptionDurationDropDown.getSelectedItem();
                lang=(String)langt1.getSelectedItem();
                status=statust1.getText();
                
                
                //payment details
                
                int chno, amt, date1, dat2, dat3, startm, starty, endm, endy;
                String payt;
                
                chno=Integer.parseInt(chddt1.getText());
                amt=Integer.parseInt(amt1.getText());
                
                payt=(String)paytt1.getSelectedItem();
                
                Date selectedDate = (Date) datePicker.getModel().getValue();
                date1= selectedDate.getDate();
                dat2=selectedDate.getMonth()+1;
                dat3=selectedDate.getYear()+1900;
                        
                /*date1=Integer.parseInt(dateText.getText());
                dat2=Integer.parseInt(monthText.getText());
                dat3=Integer.parseInt(yearText.getText());*/
                
                
                startm=Integer.parseInt((String)startm1.getSelectedItem());
                starty=Integer.parseInt((String)starty1.getSelectedItem());
                
                /*
                * String x1=endt1.getText();
                * endm=Integer.parseInt(x1.substring(0,x1.lastIndexOf('/')-1));
                * endy=Integer.parseInt(x1.substring(x1.lastIndexOf('/'),x1.length()));
                *
                *
                * int period=0, period1=0;
                * try
                * {
                *
                * connect c9=new connect();
                * c9.rs=c9.st.executeQuery("select * from amountdet where duration='"+subscriptionDurationDropDown.getSelectedItem()+"'");
                * while(c9.rs.next())
                * {
                * period=c9.rs.getInt(4);
                * period1=c9.rs.getInt(5);
                * }
                *
                * c9.st.close();
                * c9.con.close();
                * }
                * catch(Exception e)
                * {
                *
                * }
                *
                * endm=((startm-1)+period1)%12;
                * if(endm==0)
                * endm=12;
                * if(endm<12)
                * endy=starty+period;
                * else
                * endy=starty+period-1;
                */
                
                endm=endm2;
                endy=endy2;
                //subscriber details
                
                String title, fname, lname, address1, add2, add3, dist2, state;
                int pin;
                
                title=titt1.getText();
                fname=namt1.getText();
                lname=lnamt1.getText();
                address1=addt11.getText();
                add2=addt21.getText();
                add3=addt31.getText();
                dist2=dist21.getText();
                state= (String)stateCodeDropDown.getSelectedItem();
                pin=Integer.parseInt(pint1.getText());
                
                //other details
                if(title.contains("'")
                        || fname.contains("'")
                        || lname.contains("'")
                        || address1.contains("'")
                        || add2.contains("'")
                        || add3.contains("'")
                        || dist2.contains("'")
                        || state.contains("'"))
                {
                    JOptionPane.showMessageDialog(this, "Please remove all the ' characters : ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int return1;
                String phone, history, email, remarks;
                
                
                phone=telt1.getText();
                history=(String)counterDropDown.getSelectedItem();
                email=emailt1.getText();
                return1=Integer.parseInt(rett1.getText());
                remarks=remt1.getText();
                
                int page_number = 0;
                String subscription_type = "New";
                //database query for basic fragment
                
                connect c2=new connect();
                String basicQuery = "insert into basic values("+asn+",'"+subno1+"',"+subno+",'"+status+"',"+rcpt+",'"+dist+"',"+dno+",'"+subt+"','"+lang+"','"+seriesName+"',"+page_number+")";
                System.out.println(basicQuery);
                c2.a=c2.st.executeUpdate(basicQuery);
                
                
                if(c2.a==1)
                    flag=flag+1;
                c2.closeAll();
                
                
                if(dno>0)
                {
                    connect c10=new connect();
                    c10.a=c10.st.executeUpdate("insert into d"+dno+" values ("+asn+")");
                    c10.closeAll();
                }
                
                //database query for payment fragment
                
                connect c3=new connect();
                String paymentQuery = "insert into payment values("+asn+",'"+payt+"',"+chno+","+date1+","+dat2+","+dat3+","+amt+","+startm+","+starty+","+endm+","+endy+" , '"+SamsUtilities.getCurrentSqlDate()+"', '"+subscription_type+"')";
                //System.out.println(paymentQuery);
                c3.a=c3.st.executeUpdate(paymentQuery);
                if(c3.a==1)
                    flag=flag+1;
                c3.closeAll();
                
                //database query for subscription details fragment
                
                connect c4=new connect();
                String subDetailsQuery = "insert into subdetails values ("+asn+",'"+title+"','"+fname+"','"+lname+"','"+address1+"','"+add2+"','"+add3+"','"+dist2+"','"+state+"',"+pin+")";
                //System.out.println(subDetailsQuery);
                c4.a=c4.st.executeUpdate(subDetailsQuery);
                if(c4.a==1)
                    flag=flag+1;
                c4.closeAll();
                
                
                //database query for other details fragment
                
                connect c5=new connect();
                String otherDetailsQuery = "insert into otherdet values("+asn+",'"+phone+"','"+history+"','"+email+"',"+return1+",'"+remarks+"','','','')";
                //System.out.println(otherDetailsQuery);
                c5.a=c5.st.executeUpdate(otherDetailsQuery);
                
                if(c5.a == 1)
                    flag++;
                
                
                String sqlQuery = "insert into receipt_book_details values ('"+seriesName+"',"+rcpt+","+asn+",'"+payt+"','"+dat3+"-"+dat2+"-"+date1+"',"+amt+",'"+history+"','0')";
                c5.a=c5.st.executeUpdate(sqlQuery);
                if(c5.a == 1)
                    flag++;
                
                String mainTableQuery = "insert into subscribers_primary_details values("+asn+",'"+subno1+"',"+subno+",'"+status+"',"+rcpt+",'"+dist+"',"+dno+",'"+subt+"','"+lang+"','"+seriesName+"','"+payt+"',"+chno+",'"+dat3+"-"+dat2+"-"+date1+"',"+amt+",'"+starty+"-"+startm+"-1','"+endy+"-"+endm+"-28','"+title+"','"+fname+"','"+lname+"','"+address1+"','"+add2+"','"+add3+"','"+dist2+"','"+state+"',"+pin+",'"+phone+"','"+history+"','"+email+"',"+page_number+",'"+subscription_type+"' , '"+SamsUtilities.getCurrentSqlDate()+"','"+remarks+"',"+return1+",'','','')";
                //System.out.println(mainTableQuery);
                c5.a=c5.st.executeUpdate(mainTableQuery);
                if(c5.a == 1) flag++;
                c5.closeAll();
                
                
                if(flag==6)
                {
                    JOptionPane.showMessageDialog(this, "RECORD ADDED SUCCESSFULLY", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    int z;
                    z = x+1;
                    connect c6=new connect();
                    c6.a=c6.st.executeUpdate("update asn set asn="+z+" where asn="+x);
                    c6.closeAll();
                    
                    asnt1.setText("SN"+z);
                    
                    flag=0;
                }
                
                
                setVisible(false);
                this.dispose();
                
                
                t=new Thread(this);
                //t.start();
                Thread.sleep(3000);
                new SatSandeshNewSubscription();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        //clear button
        if(ae.getSource()==clear)
        {
            subNumberText.setText("");
            statust1.setText("Active");
            receiptNumberText.setText("");
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionCodeDropDown.setEnabled(false);
            distributionCodeDropDown.setSelectedItem("0");
            subscriptionDurationDropDown.setSelectedItem("1 year");
            langt1.setSelectedItem("Hindi");
            paytt1.setSelectedItem("Cash");
            chddt1.setText("0");
            //dateText.setText("");
            //monthText.setText(""+currMonth);
            //yearText.setText(""+currYear);
            /*Date selectedDate = (Date) datePicker.getModel().getValue();
                int date1= selectedDate.getDate();
                int dat2=selectedDate.getMonth()+1;
                int dat3=selectedDate.getYear()+1900;
                System.out.println(date1 + " " + dat2 + " " + dat3);*/
            datePicker.getJFormattedTextField().setText("");
            amt1.setText("100");
            startm1.setSelectedItem("1");
            starty1.setSelectedItem("s2");
            endt1.setText("");
            titt1.setText("");
            namt1.setText("");
            lnamt1.setText("");
            dist21.setText("");
            stateCodeDropDown.setSelectedIndex(0);
            stateNameDropDown.setSelectedIndex(0);
            pint1.setText("0");
            telt1.setText("");
            counterDropDown.setSelectedIndex(1);
            emailt1.setText("");
            rett1.setText("0");
            rett1.setEnabled(false);
            remt1.setText("");
            addt11.setText("");
            addt21.setText("");
            addt31.setText("");
            
        }
        
        if(ae.getSource()==back)
        {
            //Date selectedDate = (Date) datePicker.getModel().getValue();
            //System.out.println(""+selectedDate.getDate()+ " " + (selectedDate.getMonth()+1) + " " + (selectedDate.getYear()+1900) );
            setVisible(false);
            new sams();
            this.dispose();
            
        }
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource() == stateNameDropDown)
        {
            stateCodeDropDown.setSelectedItem(SamsUtilities.getStateCodeForStateName((String)stateNameDropDown.getSelectedItem()));
        }
        if(ie.getSource()==distributionCodeDropDown)
        {
            try
            {
                connect c14=new connect();
                c14.rs=c14.st.executeQuery("select district, state from despcode where dno="+Integer.parseInt((String)distributionCodeDropDown.getSelectedItem()));
                c14.rs.next();
                dist21.setText(""+c14.rs.getString(1));
                dist21.setEnabled(false);
                dist21.setFont(f);
                stateCodeDropDown.setSelectedItem(""+c14.rs.getString(2));
                stateCodeDropDown.setEnabled(false);
                stateCodeDropDown.setFont(f);
                c14.st.close();
                c14.con.close();
            }
            catch(Exception e1)
            {
                JOptionPane.showMessageDialog(this, "ERROR : "+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        if(ie.getSource()==distributionTypeDropDown)
        {
            if(distributionTypeDropDown.getSelectedItem()=="Distributor")
            {
                distributionCodeDropDown.setEnabled(true);
                //dt1.select("1");
                //dt1.select(1);
            }
            
            if(distributionTypeDropDown.getSelectedItem()=="By Hand" || distributionTypeDropDown.getSelectedItem()=="By Post")
                //else
            {
                distributionCodeDropDown.setEnabled(false);
                distributionCodeDropDown.setSelectedItem("0");
            }
            
        }
        
        if(ie.getSource()==subscriptionDurationDropDown)
        {
            try
            {
                connect c7=new connect();
                c7.rs=c7.st.executeQuery("select * from amountdet where language='"+langt1.getSelectedItem()+"' and duration='"+subscriptionDurationDropDown.getSelectedItem()+"'");
                
                while(c7.rs.next())
                {
                    amount=c7.rs.getInt(3);
                }
                
                amt1.setText(""+amount);
                
                c7.st.close();
                c7.con.close();
                
                if(subscriptionDurationDropDown.getSelectedItem()=="Urdu"&&subscriptionDurationDropDown.getSelectedItem()=="Punjabi")
                {
                    amt1.setEnabled(true);
                    amt1.setText("");
                }
            }
            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        if(ie.getSource()==langt1)
        {
            try
            {
                connect c8=new connect();
                
                c8.rs=c8.st.executeQuery("select * from amountdet where language='"+langt1.getSelectedItem()+"'");
                
                subscriptionDurationDropDown.removeAll();
                while(c8.rs.next())
                {
                    subscriptionDurationDropDown.addItem(c8.rs.getString(2));
                }
                
                c8.st.close();
                c8.con.close();
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ie.getSource()==paytt1)
        {
            if(paytt1.getSelectedItem()=="Cash")
            {
                chddt1.setText("0");
                chddt1.setEnabled(false);
            }
            
            if(paytt1.getSelectedItem()=="CH/DD/MO")
            {
                chddt1.setText("");
                chddt1.setEnabled(true);
            }
        }
        
        if(ie.getSource()==startm1 || ie.getSource()==starty1)
        {
            int startm2=Integer.parseInt((String)startm1.getSelectedItem());
            int starty2=Integer.parseInt((String)starty1.getSelectedItem());
            
            int period2=0, period12=0;
            try
            {
                
                connect c22=new connect();
                c22.rs=c22.st.executeQuery("select * from amountdet where duration='"+subscriptionDurationDropDown.getSelectedItem()+"'");
                while(c22.rs.next())
                {
                    period2=c22.rs.getInt(4);
                    
                    period12=c22.rs.getInt(5);
                    
                    //System.out.println("year : "+period2+" month : "+period12);
                }
                
                c22.st.close();
                c22.con.close();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            endm2=((startm2-1)+period12)%12;
            //endy2;
            int tempFlag=0;
            if(endm2==0)
                endm2=12;
            if(endm2>0 && endm2 <period12)
                tempFlag++;
            if(endm2<12)
                endy2=starty2+period2+tempFlag;
            else
                endy2=starty2+period2-1+tempFlag;
            
            if(endm2==12 && period12>0)
            {
                endy2++;
            }
            
            
            endt1.setText(""+endm2+"/"+endy2);
            
        }
        
        if(ie.getSource()==subNumberCodeDropDown)
        {
            for(int stateCode = 0 ; stateCode != items.length ; ++stateCode)
            {
                if(subNumberCodeDropDown.getSelectedItem() == items[stateCode])
                {
                    String sub_code_str = (String)(items[stateCode]);
                    String state_code_text = SamsUtilities.getStateCodeForSubCode(sub_code_str);
                    String state_name_text = SamsUtilities.getStateNameForStateCode(state_code_text);
                    //System.out.println("'"+sub_code_str+ "' '" +state_name_text + "' '" + state_code_text+"'" );
                    
                    if(state_name_text.isEmpty() == false)
                    {
                        stateNameDropDown.setEnabled(false);
                        stateCodeDropDown.setEnabled(false);
                        stateCodeDropDown.setSelectedItem(state_code_text);
                        stateNameDropDown.setSelectedItem(state_name_text);
                        //stateNameDropDown.setSelectedItem(state_name_text);
                        distributionTypeDropDown.setSelectedItem("By Post");
                        distributionTypeDropDown.setEnabled(false);
                    }
                    else
                    {
                        stateNameDropDown.setSelectedIndex(0);
                        stateNameDropDown.setEnabled(true);
                        stateCodeDropDown.setSelectedIndex(0);
                        stateCodeDropDown.setEnabled(true);
                        distributionTypeDropDown.setSelectedItem("By Post");
                        distributionTypeDropDown.setEnabled(false);
                        
                    }
                    
                    if(sub_code_str.equals("BD"))
                    {
                        
                        stateCodeDropDown.setSelectedIndex(0);
                        stateCodeDropDown.setEnabled(true);
                        distributionTypeDropDown.setSelectedItem("Distributor");
                        distributionTypeDropDown.setEnabled(false);
                        
                    }
                    
                    String selectedItem = (String)subNumberCodeDropDown.getSelectedItem();
                    if(selectedItem.equals("BH") || selectedItem.equals("LH") || selectedItem.equals("EN") || selectedItem.equals("PJ") || selectedItem.equals("UR") )
                    {
                        stateCodeDropDown.setSelectedIndex(0);
                        stateCodeDropDown.setEnabled(true);
                        distributionTypeDropDown.setSelectedItem("By Hand");
                        if(selectedItem.equals("BH") || selectedItem.equals("LH"))
                            distributionTypeDropDown.setEnabled(false);
                        else
                            distributionTypeDropDown.setEnabled(true);
                        
                    }
                }
            }
            /*
            if(subNumberCodeDropDown.getSelectedItem()=="DL")
            {
            statt1.setText("DL");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            
            if(subNumberCodeDropDown.getSelectedItem()=="HR")
            {
            statt1.setText("HAR");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="MH")
            {
            statt1.setText("MAH");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="MP")
            {
            statt1.setText("MP");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            
            if(subNumberCodeDropDown.getSelectedItem()=="PB")
            {
            statt1.setText("PJB");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="RJ")
            {	statt1.setText("RAJ");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="UK")
            {
            statt1.setText("UK");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="UP")
            {
            statt1.setText("UP");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            }
            
            
            
            if(subNumberCodeDropDown.getSelectedItem()=="LF")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="BH")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="MS")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="LH")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="CM")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="EN")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            langt1.setSelectedItem("English");
            distributionTypeDropDown.setEnabled(true);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="UR")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(true);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="BD")
            {
            
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("Distributor");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subNumberCodeDropDown.getSelectedItem()=="PJ")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(true);
            
            }
            */
            
        }
        
    }
    
    @Override
    public void run()
    {
        try
        {
            //t.sleep(3000);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "ERROR : "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    String seriesNameText;
    
    @Override
    public void focusGained(FocusEvent fe) {
        if(fe.getSource() == receiptNumberText)
        {
            seriesNameText = (String)(seriesDropDown.getSelectedItem());
            //System.out.println("Gained " + seriesName);
        }
    }
    
    @Override
    public void focusLost(FocusEvent fe) {
        if(fe.getSource() == receiptNumberText)
        {
            int rcptNum;
            String rcpt = receiptNumberText.getText();
            if(seriesNameText.isEmpty() && rcpt.isEmpty() == false)
            {
                JOptionPane.showMessageDialog(this,"Please select series", "Please select series", JOptionPane.ERROR_MESSAGE);
                //receiptNumberText.setText("");
                seriesDropDown.requestFocus();
                return;
            }
            
            if(seriesNameText.isEmpty() == false && rcpt.isEmpty() == false)
            {
                rcptNum = (Integer.parseInt(rcpt));
                String countQuery = "select count(book_num) from receipt_book_inventory where end_rcpt_num > "+(rcptNum-1)+" and start_rcpt_num < "+(rcptNum+1)+" and series_name='"+seriesNameText+"'";
                String sqlQuery = "select issued_to,book_num, start_rcpt_num, end_rcpt_num from receipt_book_inventory where series_name = '"+seriesNameText+"' and end_rcpt_num > "+(rcptNum-1)+" and start_rcpt_num < "+(rcptNum+1);
                String alreadyIssuedRcptCheckQuery = "select count(asn) from basic where rcpt = "+rcptNum +" and series_name = '"+seriesNameText+"'";
                
                connect fillSeriesConnection = new connect();
                try
                {
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(countQuery);
                    fillSeriesConnection.rs.next();
                    
                    int ArrayCount = fillSeriesConnection.rs.getInt(1);
                    if(ArrayCount != 1)
                    {
                        JOptionPane.showMessageDialog(this,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        return;
                    }
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(sqlQuery);
                    fillSeriesConnection.rs.next();
                    
                    
                    String centre = fillSeriesConnection.rs.getString(1);
                    if(centre.isEmpty())
                    {
                        JOptionPane.showMessageDialog(this,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        return;
                    }
                    
                    
                    String bookNum = fillSeriesConnection.rs.getString(2);
                    //String subCounter = fillSeriesConnection.rs.getString(1);
                    
                    String subCounterQuery = "select issued_to from sub_issue_details where series_name='"+seriesNameText+"' and book_num="+bookNum+" and rcpt_num="+rcptNum;
                    //System.out.println(centre);
                    counterDropDown.setSelectedItem(centre);
                    subIssueCounterText.setText("");
                    //System.out.println(subCounterQuery);
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(subCounterQuery);
                    if(fillSeriesConnection.rs.next()){
                        String subCounter = fillSeriesConnection.rs.getString(1);
                        //System.out.println(subCounter);
                        subIssueCounterText.setText(subCounter);
                    }
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(alreadyIssuedRcptCheckQuery);
                    fillSeriesConnection.rs.next();
                    //System.out.println(alreadyIssuedRcptCheckQuery);
                    
                    int existingAsnCount = fillSeriesConnection.rs.getInt(1);
                    System.out.println(existingAsnCount);
                    if(existingAsnCount > 0 )
                    {
                        JOptionPane.showMessageDialog(this,"Already used receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        return;
                    }
                    
                    
                    fillSeriesConnection.closeAll();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
                    fillSeriesConnection.closeAll();
                }
            }
            //System.out.println("Lost");
        }
        
        if(fe.getSource() == subNumberText)
        {
            String subNumber = subNumberText.getText();
            int subNum;
            String subNumberCode = (String)subNumberCodeDropDown.getSelectedItem();
            if(subNumberCode.isEmpty() && subNumber.isEmpty() == false)
            {
                JOptionPane.showMessageDialog(this,"Please fill subscription number details", "Please fill subscription details", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(subNumberCode.isEmpty() == false && subNumber.isEmpty() == false)
            {
                subNum  = Integer.parseInt(subNumber);
                String countQuery = "select count(asn) from basic where subnos = '"+subNumberCode+"' and subno = "+subNum;
                //String sqlQuery = "select count(asn) from basic where subnos = '"+subNumberCode+"' and subno = "+subNum;
                //System.out.println(countQuery);
                connect fillSeriesConnection = new connect();
                try
                {
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(countQuery);
                    fillSeriesConnection.rs.next();
                    
                    int ArrayCount = fillSeriesConnection.rs.getInt(1);
                    if(ArrayCount > 0)
                    {
                        JOptionPane.showMessageDialog(this,"Subscription number "+subNumberCode+" "+subNumber+" already exists", "Subscription number already exists", JOptionPane.ERROR_MESSAGE);
                        subNumberText.setText("");
                        statust1.setText("Active");
                        receiptNumberText.setText("");
                        //distributionTypeDropDown.setSelectedItem("By Hand");
                        distributionCodeDropDown.setEnabled(false);
                        distributionCodeDropDown.setSelectedItem("0");
                        subscriptionDurationDropDown.setSelectedItem("1 year");
                        langt1.setSelectedItem("Hindi");
                        paytt1.setSelectedItem("Cash");
                        chddt1.setText("0");
                        datePicker.getJFormattedTextField().setText("");
                        /*dateText.setText("");
                        monthText.setText(""+currMonth);
                        yearText.setText(""+currYear);*/
                        amt1.setText("100");
                        startm1.setSelectedItem("1");
                        starty1.setSelectedItem("s2");
                        endt1.setText("");
                        titt1.setText("");
                        namt1.setText("");
                        lnamt1.setText("");
                        dist21.setText("");
                        stateCodeDropDown.setSelectedItem("");
                        stateNameDropDown.setSelectedIndex(0);
                        pint1.setText("0");
                        telt1.setText("");
                        counterDropDown.setSelectedIndex(1);
                        emailt1.setText("");
                        rett1.setText("0");
                        rett1.setEnabled(false);
                        remt1.setText("");
                        addt11.setText("");
                        addt21.setText("");
                        addt31.setText("");
                        return;
                    }
                    fillSeriesConnection.closeAll();
                } catch (Exception exc) {
                    //exc.printStackTrace();
                    //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
                    fillSeriesConnection.closeAll();
                }
            }
            //System.out.println("Lost");
        }
    }
}