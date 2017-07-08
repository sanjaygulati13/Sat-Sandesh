import java.awt.*;
import java.awt.event.*;
import java.sql.Savepoint;
import javax.swing.*;
import java.util.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
//done till sub no, nd status


public class SatSandeshRenewSubscription extends JFrame implements ActionListener, ItemListener, FocusListener
{
    public static void main(String args[])
    {
        new SatSandeshRenewSubscription("BH",131);
    }
    /* protected final void center() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    int x1 = (screenSize.width - frameSize.width) / 2;
    int y = (screenSize.height - frameSize.height) / 2;
    setLocation(x1, y);
    }*/
    
    JLabel  subd1, asn1, sub1, status1, seriesLabel, rec1, dist1, d1, subt1, lang1;				//subscription details
    JLabel pay1, payt1, chdd1, dat1, am1, starm1,stary1, end1, newRenewLabel;						//payment details
    JLabel mem1, tit1, nam1, lnam1, add1, dis1, stat1, pin1;						//member details
    JLabel oth1, tel1, hist1, email1, ret1, rem1, subIssueCounterLabel; 									//other details
    int s2;
    int endm2, endy2;
    JPanel p1, p2, p3, p4,p5;
    
    Font f=new Font("ARIAL", Font.BOLD,12);
    
    JTextField asnt1, statust1 /*, distributionTypeDropDown, subscriptionDurationDropDown , languageDropDown*/;						//subscription details
    JComboBox distributionCodeDropDown, distributionTypeDropDown, subNumberCodeDropDown, subscriptionDurationDropDown, languageDropDown, seriesDropDown;
    TextFieldWithLimit subt21, receiptNumberText;
    
    
    //JTextField /*paytt1,*/ ;											//payment details
    TextFieldWithLimit dateText, monthText, yearText, amt1, endingPeriodText, newRenewText, chddt1;
    JComboBox paytt1, startm1, starty1;
    
    
    TextFieldWithLimit titt1, namt1, lnamt1, pint1,addt11,addt21,addt31,dist21;	//member details
    JComboBox stateCodeDropDown, stateNameDropDown;
    
    JTextField  rett1, remt1;											//other details
    TextFieldWithLimit telt1,emailt1,subIssueCounterText;
    JComboBox counterDropDown;
    
    int currMonth, currYear;
    
    JButton renew, mod, clear, back;
    int x, amount;
    String str;
    int flag=0;
    Object[] items;
    
    UtilDateModel model = new UtilDateModel();
    Properties prop;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    
    
    public SatSandeshRenewSubscription(String stat,int num)
    {
        
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        setTitle("Renew Subscription");
        setSize(1000,690);
        //this.setSize(this.getToolkit().getScreenSize());
        setResizable(false);
        setLocation(10,10);
        setLayout(new GridLayout(5,1));
        SamsUtilities.center(this);
        
        GregorianCalendar cal = new GregorianCalendar();
        currMonth = (cal.get(Calendar.MONTH) +1);
        currYear = cal.get(Calendar.YEAR);
        
        p1=new JPanel();
        p1.setLayout(null);
        p2=new JPanel();
        p2.setLayout(null);
        p3=new JPanel();
        p3.setLayout(null);
        p4=new JPanel();
        p4.setLayout(null);
        p5=new JPanel();
        p5.setLayout(null);
        
        
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
        dat1=new JLabel("Date");
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
        hist1=new JLabel("Counter");
        email1=new JLabel("e-mail");
        ret1=new JLabel("Return Back");
        subIssueCounterLabel = new JLabel("<html>Sub Counter</html>");
        rem1=new JLabel("Remarks");
        
        
        asnt1=new JTextField(8);
        subt21=new TextFieldWithLimit(5,5);
        statust1=new JTextField(20);
        receiptNumberText=new TextFieldWithLimit(5,5);
        distributionTypeDropDown=new JComboBox();
        items = SamsUtilities.getSubCodes();
        subNumberCodeDropDown=new JComboBox(items);
        
        
        //dt1=new TextFieldWithLimit(2,2);
        subscriptionDurationDropDown=new JComboBox();
        languageDropDown=new JComboBox();
        
        
        paytt1=new JComboBox();
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        
        chddt1=new TextFieldWithLimit(10,10);
        //dateText=new TextFieldWithLimit(2,2);
        monthText=new TextFieldWithLimit(2,2);
        yearText=new TextFieldWithLimit(4,4);
        amt1=new TextFieldWithLimit(4,4);
        endingPeriodText=new TextFieldWithLimit(10,10);
        newRenewText = new TextFieldWithLimit(6, 6);
        titt1=new TextFieldWithLimit(3,3);
        namt1=new TextFieldWithLimit(16,16);
        lnamt1=new TextFieldWithLimit(15,15);
        addt11=new TextFieldWithLimit(32,32);
        addt21=new TextFieldWithLimit(32,32);
        addt31=new TextFieldWithLimit(32,32);
        dist21=new TextFieldWithLimit(22,22);
        stateCodeDropDown=new JComboBox(SamsUtilities.fillStateCodeList());
        stateNameDropDown = new JComboBox(SamsUtilities.fillStateNameList());
        pint1=new TextFieldWithLimit(6,6);
        telt1=new TextFieldWithLimit(12,12);
        counterDropDown=new JComboBox();
        emailt1=new TextFieldWithLimit(32,32);
        subIssueCounterText = new TextFieldWithLimit(32,32);
        rett1=new JTextField(5);
        remt1=new JTextField(50);
        
        
        counterDropDown.addItem("Kirpal Bagh");
        counterDropDown.addItem("Kirpal Ashram");
        counterDropDown.addItem("Sawan Ashram");
        counterDropDown.setEnabled(false);
        //		counterDropDown.addItem("Dharshan Dham");
        //		counterDropDown.addItem("Tours/Function");
        //
        
        
        renew=new JButton("Renew");
        //mod=new JButton("Modify");
        //clear=new JButton("Clear");
        back=new JButton("Back");
        
        distributionCodeDropDown=new JComboBox();
        distributionCodeDropDown.addItemListener(this);
        
        try
        {
            distributionCodeDropDown.addItem("0");
            connect c13=new connect();
            c13.rs=c13.st.executeQuery("select dno from despcode");
            while(c13.rs.next())
            {
                distributionCodeDropDown.addItem(""+c13.rs.getInt(1));
            }
            
            c13.st.close();
            c13.con.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
        
        startm1=new JComboBox();
        starty1=new JComboBox();
        
        for(int s=1;s<13;s++)
        {
            startm1.addItem(""+s);
        }
        
        
        s2=cal.get(Calendar.YEAR);
        
        for(int s1=(s2-2);s1<(s2+25);s1++)
        {
            starty1.addItem(""+s1);
        }
        
        //starty1.setSelectedItem(""+s2);
        int s3=cal.get(Calendar.MONTH)+1;
        
        //startm1.setSelectedItem(""+s3);
        startm1.addItemListener(this);
        starty1.addItemListener(this);
        
        
        /*
        subCodeDropdown.addItem("BH");
        subCodeDropdown.addItem("DL");
        subCodeDropdown.addItem("HR");
        subCodeDropdown.addItem("UP");
        subCodeDropdown.addItem("MP");
        subCodeDropdown.addItem("MH");
        subCodeDropdown.addItem("RJ");
        subCodeDropdown.addItem("PB");
        subCodeDropdown.addItem("LF");
        subCodeDropdown.addItem("LH");
        subCodeDropdown.addItem("UK");
        subCodeDropdown.addItem("MS");
        subCodeDropdown.addItem("EN");
        subCodeDropdown.addItem("UR");
        subCodeDropdown.addItem("PJ");
        subCodeDropdown.addItem("CM");
        subCodeDropdown.addItem("LH");
        subCodeDropdown.addItem("BD");
        */
        
        distributionTypeDropDown.addItem("By Hand");
        distributionTypeDropDown.addItem("By Post");
        distributionTypeDropDown.addItem("Distributor");
        distributionTypeDropDown.setEnabled(false);
        
        
        subscriptionDurationDropDown.addItem("1 year");
        subscriptionDurationDropDown.addItem("2 year");
        subscriptionDurationDropDown.addItem("2 year plus");
        subscriptionDurationDropDown.addItem("3 year");
        subscriptionDurationDropDown.addItem("3 year plus");
        subscriptionDurationDropDown.addItem("5 year");
        subscriptionDurationDropDown.addItem("5 year plus");
        subscriptionDurationDropDown.addItem("Life");
        subscriptionDurationDropDown.addItem("Comp");
        
        
        languageDropDown.addItem("Hindi");
        languageDropDown.addItem("English");
        languageDropDown.addItem("Urdu");
        languageDropDown.addItem("Punjabi");
        languageDropDown.setEnabled(false);
        
        paytt1.addItem("Cash");
        paytt1.addItem("CH/DD/MO");
        
        //_______________________________________________________________________________________
        
        p1.add(subd1);
        subd1.setBounds(30,10,200,30);
        
        p1.add(asn1);
        asn1.setBounds(30,45,30,20);
        
        p1.add(asnt1);
        asnt1.setBounds(90,45,80,20);
        
        int check=0;
        try
        {
            connect c1=new connect();
            c1.rs=c1.st.executeQuery("select * from basic where subnos='"+stat+"' and subno="+num);
            
            while(c1.rs.next())
            {
                check++;
                x=c1.rs.getInt(1);
                statust1.setText(c1.rs.getString(4));
                
                String s1=""+c1.rs.getString(6);
                if(s1.equals("BY POST") || s1.equals("by post"))
                    s1="By Post";
                if(s1.equals("BY HAND") || s1.equals("by hand"))
                    s1="By Hand";
                if(s1.equals("DISTRIBUTOR") || s1.equals("distributor"))
                    s1="Distributor";
                
                distributionTypeDropDown.setSelectedItem(s1);
                
                distributionCodeDropDown.setSelectedItem(""+c1.rs.getInt(7));
                //subscriptionDurationDropDown.setSelectedItem(c1.rs.getString(8));
                languageDropDown.setSelectedItem(c1.rs.getString(9));
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
        
        //statust1.setText("");
        //statust1.setEnabled(false);
        
        
        p1.add(sub1);
        sub1.setBounds(220,45,80,20);
        
        
        subNumberCodeDropDown.addItemListener(this);
        subNumberCodeDropDown.setBounds(270,45,80,20);
        subNumberCodeDropDown.setSelectedItem(stat);
        subNumberCodeDropDown.addItemListener(this);
        subNumberCodeDropDown.setEnabled(false);
        subNumberCodeDropDown.setFont(f);
        p1.add(subNumberCodeDropDown);
        
        
        subt21.setEnabled(false);
        subt21.setFont(f);
        //subt21.setBounds(380,45,60,20);
        subt21.setBounds(360,45,60,20);
        subt21.setText(""+num);
        p1.add(subt21);
        
        p1.add(status1);
        //status1.setBounds(480,45,40,20);
        status1.setBounds(430,45,40,20);
        
        p1.add(statust1);
        statust1.setEnabled(false);
        statust1.setFont(f);
        //statust1.setBounds(600,45,80,20);
        statust1.setBounds(500,45,80,20);
        
        p1.add(rec1);
        rec1.setBounds(750,45,100,20);
        rec1.setBounds(650,45,100,20);
        
        p1.add(seriesDropDown);
        seriesDropDown.setBounds(730,45,80,20);
        
        p1.add(seriesLabel);
        seriesLabel.setBounds(820,45,20,20);
        
        p1.add(receiptNumberText);
        //rect1.setBounds(860,45,40,20);
        receiptNumberText.setBounds(840,45,40,20);
        receiptNumberText.addFocusListener(this);
        
        p1.add(dist1);
        dist1.setBounds(30,75,100,20);
        
        p1.add(distributionTypeDropDown);
        distributionTypeDropDown.addItemListener(this);
        distributionTypeDropDown.setBounds(140,75,100,20);
        //distt1.setEnabled(false);
        
        p1.add(d1);
        d1.setBounds(280,75,40,20);
        
        p1.add(distributionCodeDropDown);
        distributionCodeDropDown.setEnabled(false);
        distributionCodeDropDown.setFont(f);
        //dt1.setText("00");
        distributionCodeDropDown.setBounds(330,75,50,20);
        
        p1.add(lang1);
        lang1.setBounds(480,75,110,20);
        
        p1.add(languageDropDown);
        languageDropDown.setBounds(600,75,80,20);
        languageDropDown.addItemListener(this);
        
        p1.add(subt1);
        subt1.setBounds(750,75,100,20);
        
        p1.add(subscriptionDurationDropDown);
        subscriptionDurationDropDown.setBounds(860,75,100,20);
        subscriptionDurationDropDown.addItemListener(this);
        
        //_______________________________________________________________________________________
        
        try
        {
            connect c10=new connect();
            c10.rs=c10.st.executeQuery("select * from payment where asn="+x);
            while(c10.rs.next())
            {
                //String d1, d2;
                //d1=""+(c10.rs.getInt(10)+1);
                //d2=""+c10.rs.getInt(11);
                //System.out.println(d1+"  "+d2);
                int	month=c10.rs.getInt(10)+1;					// modifying  may result in probs chk it
                startm1.setSelectedItem(""+month);
                int year=c10.rs.getInt(11);
                if(month==13)
                    year++;
                starty1.setSelectedItem(""+year);
                
            }
            
            c10.st.close();
            c10.con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
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
        dat1.setBounds(430,45, 40,20);
        
        {
            prop = new Properties();
            prop.put("text.today", "Today");
            prop.put("text.month", "Month");
            prop.put("text.year", "Year");
            datePanel = new JDatePanelImpl(model, prop);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }
        
        datePicker.setBounds(480,45,165,25);
        p2.add(datePicker);
        
        /*p2.add(monthText);
        monthText.setText(""+currMonth);
        monthText.setBounds(500,45,25,20);
        
        p2.add(yearText);
        yearText.setText(""+currYear);
        yearText.setBounds(530,45,40,20);*/
        
        
        p2.add(am1);
        am1.setBounds(650,45,50,20);
        
        p2.add(amt1);
        amt1.setBounds(740,45,60,20);
        amt1.setEnabled(false);
        amt1.setFont(f);
        amt1.setText("100");
        
        p2.add(starm1);
        starm1.setBounds(30,75,100,20);
        
        p2.add(startm1);
        startm1.setBounds(130,75,75,20);
        
        p2.add(stary1);
        stary1.setBounds(210,75,10,20);
        
        
        p2.add(starty1);
        starty1.setBounds(225,75,90,20);
        
        
        //p2.add(end1);
        //end1.setBounds(285,75,100,20);
        
        //p2.add(end1);
        end1.setBounds(400,75,100,20);
        
        //removing on request of Sumit
        //p2.add(endingPeriodText);
        endingPeriodText.setBounds(510,75,70,20);
        endingPeriodText.setEnabled(false);
        endingPeriodText.setFont(f);
        
        p2.add(newRenewLabel);
        newRenewLabel.setBounds(600,75,80,20);
        
        p2.add(newRenewText);
        newRenewText.setBounds(690,75,60,20);
        newRenewText.setEnabled(false);
        newRenewText.setText("Renew");
        newRenewText.setFont(f);
        
        //_______________________________________________________________________________________
        
        try
        {
            connect c2=new connect();
            c2.rs=c2.st.executeQuery("select * from subdetails where asn="+x);
            
            while(c2.rs.next())
            {
                titt1.setText(c2.rs.getString(2));
                namt1.setText(c2.rs.getString(3));
                lnamt1.setText(c2.rs.getString(4));
                addt11.setText(c2.rs.getString(5));
                addt21.setText(c2.rs.getString(6));
                addt31.setText(c2.rs.getString(7));
                dist21.setText(c2.rs.getString(8));
                String stateCode = c2.rs.getString(9);
                stateCodeDropDown.setSelectedItem(stateCode);
                //String subCode = (String)subNumberCodeDropDown.getSelectedItem();
                String stateName = SamsUtilities.getStateNameForStateCode(stateCode);
                stateNameDropDown.setSelectedItem(stateName);
                pint1.setText(""+c2.rs.getInt(10));
                
            }
            
            c2.st.close();
            c2.con.close();
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
            
        }
        
        
        p3.add(mem1);
        mem1.setBounds(30,0,200,30);
        
        p3.add(tit1);
        tit1.setBounds(30,45,50,20);
        
        p3.add(titt1);
        titt1.setEnabled(false);
        titt1.setFont(f);
        titt1.setBounds(80,45,30,20);
        
        p3.add(nam1);
        nam1.setBounds(120,45,40,20);
        
        p3.add(namt1);
        namt1.setEnabled(false);
        namt1.setFont(f);
        namt1.setBounds(160,45,145,20);
        
        p3.add(lnam1);
        lnam1.setBounds(310,45,50,20);
        
        p3.add(lnamt1);
        lnamt1.setEnabled(false);
        lnamt1.setFont(f);
        lnamt1.setBounds(360,45,145,20);
        
        p3.add(add1);
        add1.setBounds(520,45,60,20);
        
        p3.add(addt11);
        addt11.setEnabled(false);
        addt11.setFont(f);
        addt11.setBounds(600,45,340,20);
        
        p3.add(addt21);
        addt21.setEnabled(false);
        addt21.setFont(f);
        addt21.setBounds(600,65,340,20);
        
        p3.add(addt31);
        addt31.setEnabled(false);
        addt31.setFont(f);
        addt31.setBounds(600,85,340,20);
        
        p3.add(dis1);
        dis1.setBounds(30,115,60,20);
        
        p3.add(dist21);
        dist21.setEnabled(false);
        dist21.setFont(f);
        dist21.setBounds(90,115,240,20);
        
        p3.add(stat1);
        stat1.setBounds(340,115,40,20);
        
        p3.add(stateNameDropDown);
        stateNameDropDown.setEnabled(false);
        stateNameDropDown.setFont(f);
        stateNameDropDown.setBounds(375,115,200,20);
        stateNameDropDown.addItemListener(this);
        
        p3.add(stateCodeDropDown);
        stateCodeDropDown.setEnabled(false);
        stateCodeDropDown.setFont(f);
        stateCodeDropDown.setBounds(585,115,80,20);
        
        p3.add(pin1);
        pin1.setBounds(680,115,100,20);
        
        p3.add(pint1);
        pint1.setEnabled(false);
        pint1.setFont(f);
        pint1.setBounds(800,115,50,20);
        
        //_______________________________________________________________________________________
        
        
        try
        {
            
            connect c3=new connect();
            c3.rs=c3.st.executeQuery("select * from otherdet where asn="+x);
            
            while(c3.rs.next())
            {
                telt1.setText(c3.rs.getString(2));
                counterDropDown.setSelectedItem(c3.rs.getString(3));
                emailt1.setText(c3.rs.getString(4));
                rett1.setText(""+c3.rs.getString(5));
                remt1.setText(c3.rs.getString(6));
            }
            
            c3.st.close();
            c3.con.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        p4.add(oth1);
        oth1.setBounds(30,10,200,30);
        
        p4.add(tel1);
        tel1.setBounds(30,45,80,20);
        
        p4.add(telt1);
        telt1.setEnabled(false);
        telt1.setFont(f);
        telt1.setBounds(110,45,110,20);
        
        p4.add(hist1);
        hist1.setBounds(240,45,60,20);
        
        p4.add(counterDropDown);
        //histt1.setEnabled(false);
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
        subIssueCounterText.setEnabled(false);
        
        p4.add(rem1);
        rem1.setBounds(470,45,60,20);
        
        p4.add(remt1);
        
        remt1.setBounds(550,45,400,80);
        
        emailt1.setEnabled(false);
        emailt1.setFont(f);
        emailt1.setFont(f);
        
        rett1.setEnabled(false);
        rett1.setFont(f);
        remt1.setEnabled(false);
        remt1.setFont(f);
        
        
        
        //_______________________________________________________________________________________
        
        p5.add(renew);
        renew.setBounds(340,10,100,30);
        renew.setMnemonic('S');
        
        p5.add(back);
        back.setMnemonic('B');
        back.setBounds(460,10,100,30);
        
        renew.addActionListener(this);
        back.addActionListener(this);
        
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        
        if(check > 0)
        {
            setVisible(true);
        }
        else// if(check==0)
        {
            JOptionPane.showMessageDialog(null,"No Record exist for Sub No "+stat+"-"+num, "NO RECORDS", JOptionPane.ERROR_MESSAGE);
            SatSandeshRenewSubNumSelection satSandeshRenewSubNumSelection = new SatSandeshRenewSubNumSelection();
            this.dispose();
        }
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        
        
        //database updation
        if(ae.getSource()==renew)
        {
            try
            {
                
                int asn, rcpt,subno, dno;
                String sno1, subno1, dist, subt, lang,status;
                
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
                subno=Integer.parseInt(subt21.getText());
                dist=(String)distributionTypeDropDown.getSelectedItem() ;
                subt=(String)subscriptionDurationDropDown.getSelectedItem();
                lang=(String)languageDropDown.getSelectedItem();
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
                
                int period=0, period1=0, endm1=0, endy1=0;
                
                try
                {
                    
                    connect c9=new connect();
                    c9.rs=c9.st.executeQuery("select * from amountdet where duration='"+(String)subscriptionDurationDropDown.getSelectedItem()+"'");
                    while(c9.rs.next())
                    {
                        period=c9.rs.getInt(4);
                        period1=c9.rs.getInt(5);
                    }
                    
                    
                    
                    
                    c9.rs=c9.st.executeQuery("select * from payment where asn="+asn);
                    while(c9.rs.next())
                    {
                        endm1=c9.rs.getInt(10);
                        endy1=c9.rs.getInt(11);
                    }
                    
                    System.out.println("end "+endm1+endy1);
                    
                    c9.st.close();
                    c9.con.close();
                    
                }
                catch(Exception e)
                {
                    
                }
                
                //String str1, str2, str3;
                /*GregorianCalendar cal= new GregorianCalendar();
                * str1=String.valueOf(cal.get(Calendar.DATE));
                * str2=String.valueOf(cal.get(Calendar.MONTH)+1);
                * str3=String.valueOf(cal.get(Calendar.YEAR));
                */
                //System.out.println("Date "+str1+"/"+str2+str3);
                
                /*
                * int year=Integer.parseInt((String)starty1.getSelectedItem());
                * int month=Integer.parseInt((String)startm1.getSelectedItem());
                *
                * System.out.println(" current YM "+year+"-"+month);
                *
                * //System.out.println(endy1>=year);
                * //System.out.println(endm1>=month);
                *
                * if((endy1==year && endm1>month) || endy1>year)
                * {
                * startm=(endm1+1)%12;
                *
                * starty=endy1;
                * if(startm==1)
                * starty++;
                * //System.out.println("New starting period "+startm+"-"+starty);
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
                
                {
                    if(true)
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
                        String endingPeriod  = ""+endm2+"/"+endy2;
                        //endingPeriodText.setText(endingPeriod);
                        int res = JOptionPane.showConfirmDialog(this, "Updating with ending period: " + endingPeriod, "Confirm", JOptionPane.YES_NO_OPTION);
                        if( res == JOptionPane.NO_OPTION ) {
                            startm1.requestFocus();
                            return;
                        }
                        endm = endm2;
                        endy = endy2;
                    }
                }
                
                //subscriber details
                if(endm == 0 || endy == 0)
                {
                    JOptionPane.showMessageDialog(this, "Please enter ending period", "ERROR", JOptionPane.ERROR_MESSAGE);
                    startm1.requestFocus();
                    return;
                }
                
                String title, fname, lname, address1, add2, add3, dist2, state;
                int pin;
                
                title=titt1.getText();
                fname=namt1.getText();
                lname=lnamt1.getText();
                address1=addt11.getText();
                add2=addt21.getText();
                add3=addt31.getText();
                dist2=dist21.getText();
                state=(String)stateCodeDropDown.getSelectedItem();
                pin=Integer.parseInt(pint1.getText());
                
                //other details
                
                int return1;
                String phone, history, email, remarks;
                String endingPeriod = endy+"-"+endm+"-28";
                
                
                phone=telt1.getText();
                //System.out.println(phone);
                history=(String)counterDropDown.getSelectedItem();
                email=emailt1.getText();
                return1=Integer.parseInt(rett1.getText());
                remarks=remt1.getText();
                
                
                //database query for basic fragment
                
                connect c4=new connect();
                Savepoint save1;
                save1 = c4.con.setSavepoint();
                c4.con.setAutoCommit(false);
                
                c4.a=c4.st.executeUpdate("update basic set subnos='"+subno1+"', subno="+subno+" , status='Active', rcpt="+rcpt+", dist='"+dist+"', dno="+dno+", subt='"+subt+"', lang='"+lang+"', series_name = '"+seriesName+"', updated_by = '"+SamsUtilities.getUserName()+"' where asn="+asn);
                
                if(c4.a == 1) flag++;
                //database query for payment fragment
                
                
                c4.a=c4.st.executeUpdate("update payment set payt='"+payt+"', chno="+chno+", datd="+date1+", datm="+dat2+", daty="+dat3+", amt="+amt+", startm="+startm+", starty="+starty+", endm="+endm+", endy="+endy+", InsertionDate='"+SamsUtilities.getCurrentSqlDate()+"', subscription_type='Renew' where asn="+asn);
                if(c4.a == 1) flag++;
                //database query for other details fragment
                
                String sqlQuery = "insert into receipt_book_details values ('"+seriesName+"',"+rcpt+","+asn+",'"+payt+"','"+dat3+"-"+dat2+"-"+date1+"',"+amt+",'"+history+"','0','"+SamsUtilities.getUserName()+"','"+endingPeriod+"', "+dno+")";
                c4.a=c4.st.executeUpdate(sqlQuery);
                if(c4.a == 1) flag++;
                
                c4.a=c4.st.executeUpdate("update otherdet set history='"+history+"' where asn="+asn);
                
                if(c4.a == 1) flag++;
                
                String mainTableQuery = "update subscribers_primary_details set subscription_code='"
                        +subno1+"', subscription_number="+subno+" , membership_status='Active', receipt_number="
                        +rcpt+", distribution_type='"+dist+"', bulk_despatch_code="+dno+", subscription_period='"
                        +subt+"', language='"+lang+"', series_name = '"+seriesName+"', payment_type='"
                        +payt+"', instrument_number="+chno+", receipt_date = '"
                        +dat3+"-"+dat2+"-"+date1+"', amount="+amt+", starting_period='"
                        +starty+"-"+startm+"-1', ending_period='"+endingPeriod+"', entry_date='"
                        +SamsUtilities.getCurrentSqlDate()+"', subscription_type='Renew', counter_name = '"
                        +history+"' , updated_by = '"+SamsUtilities.getUserName()+"' where asn="+asn;
                //System.out.println(mainTableQuery);
                c4.a=c4.st.executeUpdate(mainTableQuery);
                if(c4.a == 1) flag++;
                
                if(flag == 5)
                {
                    JOptionPane.showMessageDialog(this, "RENEWAL DONE SUCCESSFULLY", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    flag=0;
                    c4.con.commit();
                }
                else
                    c4.con.rollback(save1);
                
                c4.con.setAutoCommit(true);
                c4.closeAll();
                
                new SatSandeshRenewSubNumSelection();
                this.dispose();
                
            }
            catch(Exception e)
            {
                System.out.println(e);
                
            }
        }
        
        if(ae.getSource()==back)
        {
            new SatSandeshRenewSubNumSelection();
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
                stateCodeDropDown.setSelectedItem(""+c14.rs.getString(2));
                stateCodeDropDown.setEnabled(false);
                c14.st.close();
                c14.con.close();
            }
            catch(Exception e1)
            {
                
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
            
            
            endingPeriodText.setText(""+endm2+"/"+endy2);
            
        }
        
        
        
        
        if(ie.getSource()==distributionTypeDropDown)
        {
            if(distributionTypeDropDown.getSelectedItem()=="Distributor")
            {
                distributionCodeDropDown.setEnabled(true);
                distributionCodeDropDown.setSelectedItem("0");
            }
            
            if(distributionTypeDropDown.getSelectedItem()=="By Hand"||distributionTypeDropDown.getSelectedItem()=="By Post")
                //else
            {
                distributionCodeDropDown.setEnabled(false);
                //dt1.setText("00");
            }
            
        }
        
        if(ie.getSource()==subscriptionDurationDropDown)
        {
            try
            {
                connect c7=new connect();
                c7.rs=c7.st.executeQuery("select * from amountdet where language='"+languageDropDown.getSelectedItem()+"' and duration='"+subscriptionDurationDropDown.getSelectedItem()+"'");
                
                while(c7.rs.next())
                {
                    amount=c7.rs.getInt(3);
                }
                
                amt1.setText(""+amount);
                
                c7.st.close();
                c7.con.close();
                
                if(subscriptionDurationDropDown.getSelectedItem()=="Urdu"||subscriptionDurationDropDown.getSelectedItem()=="Punjabi")
                {
                    amt1.setEnabled(true);
                    amt1.setText("");
                }
            }
            
            catch(Exception e)
            {
                
            }
            
        }
        
        if(ie.getSource()==languageDropDown)
        {
            try
            {
                connect c8=new connect();
                
                c8.rs=c8.st.executeQuery("select * from amountdet where language='"+languageDropDown.getSelectedItem()+"'");
                
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
            //System.out.println(seriesNameText + " " + rcpt);
            if(seriesNameText.isEmpty() && rcpt.isEmpty() == false)
            {
                JOptionPane.showMessageDialog(this,"Please select series", "Please select series", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(seriesNameText.isEmpty() == false && rcpt.isEmpty() == false)
            {
                rcptNum = (Integer.parseInt(rcpt));
                String countQuery = "select count(book_num) from receipt_book_inventory where series_name = '"+seriesNameText+"' and end_rcpt_num > "+(rcptNum-1)+" and start_rcpt_num < "+(rcptNum+1);
                String sqlQuery = "select issued_to,book_num, start_rcpt_num, end_rcpt_num from receipt_book_inventory where  series_name = '"+seriesNameText+"' and end_rcpt_num > "+(rcptNum-1)+" and start_rcpt_num < "+(rcptNum+1);
                //String alreadyIssuedRcptCheckQuery = "select count(asn) from basic where rcpt = "+rcptNum +" and series_name = '"+seriesNameText+"'";
                String alreadyIssuedRcptCheckQuery = "select count(asn) from receipt_book_details where receipt_number = "+rcptNum +" and series_name = '"+seriesNameText+"'";
                
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
                        return;
                    }
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(sqlQuery);
                    fillSeriesConnection.rs.next();
                    
                    
                    String centre = fillSeriesConnection.rs.getString(1);
                    if(centre.isEmpty())
                    {
                        JOptionPane.showMessageDialog(this,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
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
                    String despatchCodeStr = (String)distributionCodeDropDown.getSelectedItem();
                    //System.out.println(existingAsnCount + " " + despatchCodeStr);
                    int despatchCode = 0;
                    if(despatchCodeStr.isEmpty() == false) despatchCode = Integer.parseInt(despatchCodeStr);
                    
                    if(existingAsnCount > 0 && despatchCode == 0)
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
    }
    
    
}

