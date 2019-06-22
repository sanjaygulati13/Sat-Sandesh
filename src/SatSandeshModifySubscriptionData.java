

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
import java.sql.Savepoint;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;





public class SatSandeshModifySubscriptionData extends JFrame implements ActionListener, ItemListener,FocusListener
{
    public static void main(String args[])
    {
        //new SatSandeshModifySubscriptionData(27447);
        new SatSandeshModifySubscriptionData(30673);
    }
    
    JLabel  subd1, asn1, sub1, status1, rec1, dist1, despatchNumberLabel, subt1, lang1;				//subscription details
    JLabel pay1, payt1, chdd1, dat1, am1, starm1,stary1, endingPeriodLabel;						//payment details
    JLabel mem1, tit1, nam1, lnam1, add1, dis1, stat1, pin1;						//member details
    JLabel oth1, tel1, counterLabel, email1, ret1, rem1; 									//other details
    
    Font f=new Font("ARIAL", Font.BOLD, 12);
    
    JPanel p1, p2, p3, p4,p5;
    
    
    JTextField asnt1, statust1 /*, distributionTypeDropDown, subscriptionDurationDropDown , languageDropDown*/;						//subscription details
    JComboBox distributionCodeDropDown, distributionTypeDropDown, subNumberCodeDropDown, subscriptionDurationDropDown, languageDropDown;
    TextFieldWithLimit subNumberText, receiptNumberText ;
    
    
    //JTextField /*paymentTypeDropDown,*/ ;											//payment details
    TextFieldWithLimit datt1, datt2, datt3, amt1,starty1, startm1, endingPeriodText, chequeInstrumentNumberText;
    JComboBox paymentTypeDropDown;
    
    
    TextFieldWithLimit titt1, namt1, lnamt1, pint1,addt11,addt21,addt31,districtText;	//member details
    JComboBox stateCodeDropDown, stateNameDropDown, seriesDropDown;
    
    JTextField   rett1;											//other details
    TextArea remt1;
    TextFieldWithLimit telt1,emailt1;
    JComboBox counterDropDown;
    
    JButton renew, modifyButton, clear, back;
    int x, amount;
    String str;
    int flag=0;
    
    Object[] items;
    
    String originalRcptNumber, originalSeriesName, originalSubNumberCode, originalSubNumber;
    
    
    public SatSandeshModifySubscriptionData(int num)
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
            System.out.println(cnf);
            cnf.printStackTrace();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Modify Record");
        setSize(1050,690);
        //this.setSize(this.getToolkit().getScreenSize());
        //setResizable(false);
        setLocation(10,10);
        setLayout(new GridLayout(5,1));
        SamsUtilities.center(this);
        
        items = SamsUtilities.getSubCodes();
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
        rec1=new JLabel("Reciept No");
        dist1=new JLabel("<html>Distribution type</html>");
        despatchNumberLabel = new JLabel("D#");
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
        endingPeriodLabel = new JLabel("Ending Period");
        mem1=new JLabel("SUBSCRIBER DETAILS");
        tit1=new JLabel("Title");
        nam1=new JLabel("Name");
        lnam1=new JLabel("Last Name");
        add1=new JLabel("Address");
        dis1=new JLabel("District");
        stat1=new JLabel("State");
        pin1=new JLabel("Pin Code");
        oth1=new JLabel("OTHER DETAILS");
        tel1=new JLabel("Telephone");
        counterLabel=new JLabel("Counter");
        email1=new JLabel("e-mail");
        ret1=new JLabel("Return Back");
        rem1=new JLabel("Remarks");
        
        asnt1=new JTextField(8);
        subNumberText=new TextFieldWithLimit(5,5);
        statust1=new JTextField(20);
        receiptNumberText=new TextFieldWithLimit(5,5);
        distributionTypeDropDown=new JComboBox();
        subNumberCodeDropDown=new JComboBox(items);
        subNumberCodeDropDown.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        
        distributionCodeDropDown=new JComboBox();
        subscriptionDurationDropDown=new JComboBox();
        languageDropDown=new JComboBox();
        paymentTypeDropDown=new JComboBox();
        seriesDropDown = new JComboBox(SamsUtilities.fillSeriesInformation());
        seriesDropDown.addItem("PRERNA");
        
        chequeInstrumentNumberText = new TextFieldWithLimit(10,10);
        datt1=new TextFieldWithLimit(2,2);
        datt2=new TextFieldWithLimit(2,2);
        datt3=new TextFieldWithLimit(4,4);
        amt1=new TextFieldWithLimit(4,4);
        startm1=new TextFieldWithLimit(2,2);
        starty1=new TextFieldWithLimit("",4,4);
        endingPeriodText=new TextFieldWithLimit(10,10);
        
        titt1=new TextFieldWithLimit(3,3);
        namt1=new TextFieldWithLimit(16,16);
        lnamt1=new TextFieldWithLimit(15,15);
        addt11=new TextFieldWithLimit(32,32);
        addt21=new TextFieldWithLimit(32,32);
        addt31=new TextFieldWithLimit(32,32);
        districtText=new TextFieldWithLimit(22,22);
        stateCodeDropDown=new JComboBox(SamsUtilities.fillStateCodeList());
        stateNameDropDown = new JComboBox(SamsUtilities.fillStateNameList());
        pint1=new TextFieldWithLimit(6,6);
        telt1=new TextFieldWithLimit(12,12);
        counterDropDown=new JComboBox();
        emailt1=new TextFieldWithLimit(100,100);
        
        rett1=new JTextField(5);
        remt1=new TextArea(85,3);
        
        
        counterDropDown.addItem("Kirpal Bagh");
        counterDropDown.addItem("Kirpal Ashram");
        counterDropDown.addItem("Sawan Ashram");
        
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
            new except(e, this.getClass().toString());
            e.printStackTrace();
        }
        
        
        
        
        //renew=new JButton("Modify");
        modifyButton=new JButton("Modify");
        //clear=new JButton("Clear");
        back=new JButton("Back");
        
        
        subNumberCodeDropDown.setDoubleBuffered(true);
        subNumberCodeDropDown.setOpaque(true);
        subNumberCodeDropDown.addItemListener(this);
        stateCodeDropDown.addItemListener(this);
        stateNameDropDown.addItemListener(this);
        seriesDropDown.addItemListener(this);
        
        /*
        subt22.addItem("BH");
        subt22.addItem("BD");
        subt22.addItem("CM");
        subt22.addItem("DL");
        subt22.addItem("EN");
        subt22.addItem("HR");
        subt22.addItem("LF");
        subt22.addItem("LH");
        subt22.addItem("MH");
        subt22.addItem("MP");
        subt22.addItem("MS");
        subt22.addItem("PB");
        subt22.addItem("PJ");
        subt22.addItem("RJ");
        subt22.addItem("UK");
        subt22.addItem("UP");
        subt22.addItem("UR");
        */
        
        
        
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
        
        
        languageDropDown.addItem("Hindi");
        languageDropDown.addItem("English");
        languageDropDown.addItem("Urdu");
        languageDropDown.addItem("Punjabi");
        
        paymentTypeDropDown.addItem("Cash");
        paymentTypeDropDown.addItem("CH/DD/MO");
        
        p1.add(subd1);
        subd1.setBounds(30,10,200,30);
        
        p1.add(asn1);
        asn1.setBounds(30,45,30,20);
        
        p1.add(asnt1);
        asnt1.setBounds(90,45,80,20);
        int d=0;
        try
        {
            connect c1=new connect();
            c1.rs=c1.st.executeQuery("select subscription_code,subscription_number,membership_status,"
                    + "receipt_number,distribution_type,bulk_despatch_code,subscription_period,"
                    + "language,series_name from subscribers_primary_details where asn="+num);
            if(c1.rs.next()){
                originalSubNumberCode = c1.rs.getString(1);
                originalSubNumber = c1.rs.getString(2);
                
                subNumberCodeDropDown.setSelectedItem(originalSubNumberCode);
                subNumberText.setText(originalSubNumber);
                
                statust1.setText(c1.rs.getString(3));
                statust1.setEnabled(false);
                originalRcptNumber = ""+c1.rs.getInt(4);
                receiptNumberText.setText(originalRcptNumber);
                
                String s1=""+c1.rs.getString(5);
                if(s1.equals("BY POST") || s1.equals("by post"))
                    s1="By Post";
                if(s1.equals("BY HAND") || s1.equals("by hand"))
                    s1="By Hand";
                if(s1.equals("DISTRIBUTOR") || s1.equals("distributor"))
                    s1="Distributor";
                
                distributionTypeDropDown.setSelectedItem(s1);
                if(s1.equals("Distributor"))
                {
                    //JOptionPane.showMessageDialog(this, "", "Info", , );
                    districtText.setEnabled(false);
                    stateCodeDropDown.setEnabled(false);
                    stateNameDropDown.setEnabled(false);
                }
                
                d=c1.rs.getInt(6);
                distributionCodeDropDown.setSelectedItem(""+d);
                subscriptionDurationDropDown.setSelectedItem(c1.rs.getString(7));
                languageDropDown.setSelectedItem(c1.rs.getString(8));
                originalSeriesName = c1.rs.getString(9);
                seriesDropDown.setSelectedItem(originalSeriesName);
                //Prerna series is only for migration of prerna members
                // data modification for series and rcpt num is not allowed
               
                if(originalSeriesName.equalsIgnoreCase("Prerna")) {
                    seriesDropDown.setEnabled(false);
                    receiptNumberText.setEnabled(false);
                }
                
            }
            else{
                JOptionPane.showMessageDialog(this, "ERROR: Invalid ASN", "ERROR", JOptionPane.ERROR_MESSAGE);
                this.dispose();
                new SatSandeshSearchBySubNumber();
            }
            
            c1.closeAll();
            
        }
        catch(Exception e1)
        {
            JOptionPane.showMessageDialog(this, "ERROR"+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        
        x=num;
        
        asnt1.setText("SN"+x);
        asnt1.setEnabled(false);
        asnt1.setFont(f);
        
        p1.add(sub1);
        sub1.setBounds(220,45,80,20);
        
        
        subNumberCodeDropDown.addItemListener(this);
        subNumberCodeDropDown.setBounds(300,45,60,20);
        p1.add(subNumberCodeDropDown);
        subNumberCodeDropDown.addItemListener(this);
        
        
        subNumberText.setBounds(360,45,60,20);
        p1.add(subNumberText);
        subNumberText.addFocusListener(this);
        
        p1.add(status1);
        status1.setBounds(430,45,40,20);
        
        p1.add(statust1);
        statust1.setBounds(500,45,80,20);
        
        p1.add(dist1);
        dist1.setBounds(600,45,140,20);
        
        p1.add(distributionTypeDropDown);
        distributionTypeDropDown.addItemListener(this);
        distributionTypeDropDown.setBounds(730,45,140,20);
        
        p1.add(despatchNumberLabel);
        despatchNumberLabel.setBounds(890,45,40,20);
        
        p1.add(distributionCodeDropDown);
        if(d==0) distributionCodeDropDown.setEnabled(false);
        distributionCodeDropDown.setFont(f);
        distributionCodeDropDown.addItemListener(this);
        distributionCodeDropDown.setBounds(930,45,90,20);
        
        p1.add(rec1);
        rec1.setBounds(30,75,100,20);
        
        p1.add(seriesDropDown);
        seriesDropDown.setBounds(130,75,100,20);
        
        p1.add(receiptNumberText);
        receiptNumberText.setBounds(260,75,60,20);
        receiptNumberText.setFont(f);
        receiptNumberText.addFocusListener(this);
        
        p1.add(lang1);
        lang1.setBounds(350,75,110,20);
        
        p1.add(languageDropDown);
        languageDropDown.setBounds(480,75,120,20);
        languageDropDown.addItemListener(this);
        languageDropDown.setEnabled(false);
        languageDropDown.setFont(f);
        
        p1.add(subt1);
        subt1.setBounds(650,75,150,20);
        
        p1.add(subscriptionDurationDropDown);
        subscriptionDurationDropDown.setBounds(800,75,100,20);
        subscriptionDurationDropDown.addItemListener(this);
        subscriptionDurationDropDown.setEnabled(false);
        subscriptionDurationDropDown.setFont(f);
        
        
        try
        {
            connect c10=new connect();
            //c10.rs=c10.st.executeQuery("select payt,chno,datd,datm,daty,amt,startm,starty,endm,endy from payment where asn="+x);
            c10.rs=c10.st.executeQuery("select payment_type,instrument_number,"
                    + "receipt_date,amount,starting_period,ending_period "
                    + "from subscribers_primary_details where asn="+x);
            c10.rs.next();
            
            paymentTypeDropDown.setSelectedItem(c10.rs.getString(1));
            chequeInstrumentNumberText.setText(""+c10.rs.getInt(2));
            
            java.util.Date subscriptionDate = c10.rs.getDate(3);
            
            datt1.setText(""+subscriptionDate.getDate());
            datt2.setText(""+(subscriptionDate.getMonth()+1));
            datt3.setText(""+(subscriptionDate.getYear()+1900));
            amt1.setText(""+c10.rs.getInt(4));
            
            java.util.Date startDate = c10.rs.getDate(5);
            startm1.setText(""+(startDate.getMonth()+1));
            starty1.setText(""+(startDate.getYear()+1900));
            java.util.Date endDate = c10.rs.getDate(6);
            endingPeriodText.setText(""+(endDate.getMonth()+1)+"/"+(endDate.getYear()+1900));
            //startm1.setText(""+(c10.rs.getInt(10)+1));
            //starty1.setText(""+c10.rs.getInt(11));
            
            
            c10.st.close();
            c10.con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "ERROR"+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        p2.add(pay1);
        pay1.setBounds(30,0,200,30);
        
        p2.add(payt1);
        payt1.setBounds(30,45,130,20);
        
        
        p2.add(paymentTypeDropDown);
        paymentTypeDropDown.setBounds(130,45,100,20);
        paymentTypeDropDown.addItemListener(this);
        paymentTypeDropDown.setFont(f);
        
        p2.add(chdd1);
        chdd1.setBounds(250,45,150,20);
        
        p2.add(chequeInstrumentNumberText);
        chequeInstrumentNumberText.setBounds(380,45,60,20);
        //chequeInstrumentNumberText.setEnabled(true);
        chequeInstrumentNumberText.setFont(f);
        chequeInstrumentNumberText.setText("0");
        
        p2.add(dat1);
        dat1.setBounds(460,45, 50,20);
        
        p2.add(datt1);
        datt1.setBounds(520,45,25,20);
        datt1.setEnabled(false);
        datt1.setFont(f);
        
        p2.add(datt2);
        datt2.setBounds(550,45,25,20);
        datt2.setEnabled(false);
        datt2.setFont(f);
        
        p2.add(datt3);
        datt3.setBounds(580,45,40,20);
        datt3.setEnabled(false);
        datt3.setFont(f);
        
        p2.add(am1);
        am1.setBounds(690,45,50,20);
        
        p2.add(amt1);
        amt1.setBounds(750,45,40,20);
        amt1.setEnabled(false);
        amt1.setFont(f);
        
        p2.add(starm1);
        starm1.setBounds(30,75,100,20);
        
        p2.add(startm1);
        startm1.setBounds(140,75,75,20);
        startm1.setEnabled(false);
        startm1.setFont(f);
        
        p2.add(stary1);
        stary1.setBounds(215,75,10,20);
        
        p2.add(starty1);
        starty1.setBounds(225,75,90,20);
        starty1.setEnabled(false);
        starty1.setFont(f);
        
        
        p2.add(endingPeriodLabel);
        endingPeriodLabel.setBounds(330,75,100,20);
        
        p2.add(endingPeriodText);
        endingPeriodText.setBounds(440,75,80,20);
        endingPeriodText.setEnabled(false);
        endingPeriodText.setFont(f);
        
        
        
        try
        {
            connect c2=new connect();
            //c2.rs=c2.st.executeQuery("select title,fname,lname,add1,add2,add3,dist,state,pin from subdetails where asn="+x);
            c2.rs=c2.st.executeQuery("select title,first_name,last_name,"
                    + "address_line1,address_line2,address_line3,district,state,pin_code "
                    + "from subscribers_primary_details where asn="+x);
            
            while(c2.rs.next())
            {
                titt1.setText(c2.rs.getString(1));
                namt1.setText(c2.rs.getString(2));
                lnamt1.setText(c2.rs.getString(3));
                addt11.setText(c2.rs.getString(4));
                addt21.setText(c2.rs.getString(5));
                addt31.setText(c2.rs.getString(6));
                districtText.setText(c2.rs.getString(7));
                String stateCode = c2.rs.getString(8);
                stateCodeDropDown.setSelectedItem(stateCode);
                stateNameDropDown.setSelectedItem(SamsUtilities.getStateNameForStateCode(stateCode));
                pint1.setText(""+c2.rs.getInt(9));
            }
            
            c2.st.close();
            c2.con.close();
        }
        catch(Exception e1)
        {
            JOptionPane.showMessageDialog(this, "ERROR"+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
        
        
        p3.add(mem1);
        mem1.setBounds(30,0,200,30);
        
        p3.add(tit1);
        tit1.setBounds(30,30,50,20);
        
        p3.add(titt1);
        titt1.setBounds(80,30,30,20);
        
        p3.add(nam1);
        nam1.setBounds(120,30,40,20);
        
        p3.add(namt1);
        namt1.setBounds(160,30,145,20);
        
        p3.add(lnam1);
        lnam1.setBounds(310,30,80,20);
        
        p3.add(lnamt1);
        lnamt1.setBounds(400,30,145,20);
        
        
        add1.setBounds(560,30,60,20);
        p3.add(add1);
        
        
        addt11.setBounds(640,30,240,20);
        p3.add(addt11);
        
        p3.add(addt21);
        addt21.setBounds(640,55,240,20);
        
        p3.add(addt31);
        addt31.setBounds(640,80,240,20);
        
        
        
        p3.add(dis1);
        dis1.setBounds(30,115,60,20);
        
        p3.add(districtText);
        districtText.setBounds(90,115,240,20);
        
        p3.add(stat1);
        
        stat1.setBounds(340,115,40,20);
        
        p3.add(stateNameDropDown);
        stateNameDropDown.setFont(f);
        stateNameDropDown.setBounds(375,115,200,20);
        
        p3.add(stateCodeDropDown);
        
        stateCodeDropDown.setFont(f);
        stateCodeDropDown.setBounds(580,115,80,20);
        
        p3.add(pin1);
        pin1.setBounds(700,115,80,20);
        
        p3.add(pint1);
        
        pint1.setBounds(800,115,80,20);
        
        
        try
        {
            
            connect c3=new connect();
            //c3.rs=c3.st.executeQuery("select phone,history,email,ret,remarks from otherdet where asn="+x);
            c3.rs=c3.st.executeQuery("select phone_number, counter_name, email, "
                    + "return_issue_month, remarks from "
                    + "subscribers_primary_details where asn="+x);
            
            while(c3.rs.next())
            {
                telt1.setText(c3.rs.getString(1));
                counterDropDown.setSelectedItem(c3.rs.getString(2));
                emailt1.setText(c3.rs.getString(3));
                rett1.setText(""+c3.rs.getString(4));
                remt1.setText(c3.rs.getString(5));
            }
            
            c3.st.close();
            c3.con.close();
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "ERROR"+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        
        p4.add(oth1);
        oth1.setBounds(30,10,200,30);
        
        p4.add(tel1);
        tel1.setBounds(30,45,80,20);
        
        p4.add(telt1);
        
        telt1.setBounds(110,45,110,20);
        
        p4.add(counterLabel);
        counterLabel.setBounds(240,45,60,20);
        
        p4.add(counterDropDown);
        counterDropDown.setEnabled(false);
        counterDropDown.setFont(f);
        counterDropDown.setBounds(300,45,150,20);
        
        p4.add(email1);
        email1.setBounds(30,105,60,20);
        
        
        p4.add(emailt1);
        
        emailt1.setBounds(110,105,380,20);
//setBounds(550,45,240,20);

p4.add(ret1);
ret1.setBounds(30,75,90,20);

p4.add(rett1);
//rett1.setEnabled(false);
rett1.setText("0");
rett1.setBounds(110,75,80,20);

p4.add(rem1);
rem1.setBounds(470,45,60,20);

p4.add(remt1);

remt1.setBounds(550,45,420,80);

//_______________________________________________________________________________________


p5.add(modifyButton);
modifyButton.setMnemonic('M');
modifyButton.setBounds(220,10,100,30);


p5.add(back);
back.setMnemonic('B');
back.setBounds(460,10,100,30);

modifyButton.addActionListener(this);
back.addActionListener(this);

add(p1);
add(p2);
add(p3);
add(p4);
add(p5);

setVisible(true);

    }
    
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
        
        //database updation
        if(ae.getSource()==modifyButton)
        {
            try
            {
                
                int asn, rcpt,subno, dno;
                String sno1, subno1, dist, subt, lang,status;
                
                //basic info
                
                asn=x;
                //rcpt=Integer.parseInt(receiptNumberText.getText());
                subno1=(String)subNumberCodeDropDown.getSelectedItem();
                subno=Integer.parseInt(subNumberText.getText());
                dist=(String)distributionTypeDropDown.getSelectedItem();
                dno=Integer.parseInt((String)distributionCodeDropDown.getSelectedItem());
                
                String rcptText = receiptNumberText.getText();
                rcpt=Integer.parseInt(rcptText);
                
                /*
                
                subt=subscriptionDurationDropDown.getSelectedItem();
                lang=languageDropDown.getSelectedItem();
                status=statust1.getText();
                
                
                //payment details
                
                int chno, amt, dat1, dat2, dat3, startm, starty, endm, endy;
                String payt;
                
                chno=Integer.parseInt(chequeInstrumentNumberText.getText());
                amt=Integer.parseInt(amt1.getText());
                
                payt=paymentTypeDropDown.getSelectedItem();
                dat1=Integer.parseInt(datt1.getText());
                dat2=Integer.parseInt(datt2.getText());
                dat3=Integer.parseInt(datt3.getText());
                
                startm=Integer.parseInt(startm1.getText());
                starty=Integer.parseInt(starty1.getText());
                
                int period=0, period1=0, endm1=0, endy1=0;
                
                try
                {
                
                connect c9=new connect();
                c9.rs=c9.st.executeQuery("select * from amountdet where duration='"+subscriptionDurationDropDown.getSelectedItem()+"'");
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
                
                String str1, str2, str3;
                GregorianCalendar g= new GregorianCalendar();
                str1=String.valueOf(g.get(Calendar.DATE));
                str2=String.valueOf(g.get(Calendar.MONTH)+1);
                str3=String.valueOf(g.get(Calendar.YEAR));
                
                System.out.println("Date "+str1+"/"+str2+str3);
                
                int year=Integer.parseInt(str3);
                int month=Integer.parseInt(str2);
                
                System.out.println(" current YM "+year+"-"+month);
                
                System.out.println(endy1>=year);
                System.out.println(endm1>=month);
                
                if((endy1==year && endm1>month) || endy1>year)
                {
                startm=endm1;
                starty=endy1;
                System.out.println("New starting period "+startm+"-"+starty);
                
                }
                
                endm=((startm-1)+period1)%12;
                if(endm==0)
                endm=12;
                if(endm<12)
                endy=starty+period;
                else
                endy=starty+period-1;
                */
                //subscriber details
                
                String title, fname, lname, add1, add2, add3, dist2, state;
                int pin;
                
                title=titt1.getText();
                fname=namt1.getText();
                lname=lnamt1.getText();
                add1=addt11.getText();
                add2=addt21.getText();
                add3=addt31.getText();
                dist2=districtText.getText();
                state = (String)stateCodeDropDown.getSelectedItem();
                pin=Integer.parseInt(pint1.getText());
                
                
                if(state.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Please select state", "ERROR", JOptionPane.ERROR_MESSAGE);
                    stateNameDropDown.requestFocus();
                    return;
                }
                
                if(subno1.equals("BD") && dno == 0){
                    JOptionPane.showMessageDialog(this, "Please select D#", "ERROR", JOptionPane.ERROR_MESSAGE);
                    distributionCodeDropDown.requestFocus();
                    return;
                }
                //other details
                
                int return1;
                String phone, history, email, remarks;
                
                
                phone=telt1.getText();
                //System.out.println(phone);
                history=(String)counterDropDown.getSelectedItem();
                email=emailt1.getText();
                return1=Integer.parseInt(rett1.getText());
                remarks=remt1.getText();
                //System.out.println(remarks);
                String newline=System.getProperty("line.seperator");
                
                remarks=remarks.replaceAll("'", ",");
                //System.out.println(remarks);
                
                
                //database query for basic fragment
                String seriesName = (String)seriesDropDown.getSelectedItem();
                //System.out.println(seriesName);
                
                
                connect c14=new connect();
                
                Savepoint save1;
                save1 = c14.con.setSavepoint();
                c14.con.setAutoCommit(false);
                //System.out.println(basicQuery);
                /*String basicQuery = "update basic set subnos='"+subno1+"', subno="+subno+" ,rcpt="+rcpt+", dist='"+dist+"', dno="+dno+", series_name='"+seriesName+"', updated_by = '"+SamsUtilities.getUserName()+"'  where asn="+asn;
                c14.a=c14.st.executeUpdate(basicQuery);
                
                if(c14.a == 1) flag++;
                
                //database query for subscription details fragment
                String subDetailsQuery = "update subdetails set title='"+title+"', fname='"+fname+"', lname='"+lname+"', add1='"+add1+"', add2='"+add2+"',add3='"+add3+"',dist='"+dist2+"', state='"+state+"',pin="+pin+" where asn="+asn;
                c14.a=c14.st.executeUpdate(subDetailsQuery);
                
                if(c14.a == 1) flag++;
                
                //database query for other details fragment
                
                //System.out.println("update otherdet set phone='"+phone+"', history='"+history+"', email='"+email+"', remarks='"+remarks+"' where asn="+asn);
                String otherDetQuery = "update otherdet set phone='"+phone+"', history='"+history+"', email='"+email+"', remarks='"+remarks+"' where asn="+asn;
                c14.a=c14.st.executeUpdate(otherDetQuery);
                //System.out.println(c5.a);
                
                if(c14.a == 1) flag++;
                
                */
                
                if( seriesName.equals(originalSeriesName) == false || rcptText.equals(originalRcptNumber) == false ){
                    String sqlQuery = "update receipt_book_details set series_name = '"+seriesName+"', receipt_number = "+rcpt+", updated_by = '"+SamsUtilities.getUserName()+"' where asn ="+asn+" and series_name = '"+originalSeriesName+"' and receipt_number = "+originalRcptNumber+"";
                    //System.out.println(sqlQuery);
                    c14.a=c14.st.executeUpdate(sqlQuery);
                    if(c14.a >= 1) flag++;
                }
                else
                    flag++;
                
                
                String mainTableQuery = "update subscribers_primary_details set subscription_code='"
                        +subno1+"', subscription_number="+subno+" ,receipt_number="
                        +rcpt+", distribution_type='"+dist+"', bulk_despatch_code="
                        +dno+", title='"+title+"', first_name='"+fname+"', last_name='"
                        +lname+"', address_line1='"+add1+"', address_line2='"
                        +add2+"',address_line3='"+add3+"',district='"+dist2+"', state='"
                        +state+"',pin_code="+pin+", phone_number='"+phone+"', counter_name='"
                        +history+"', email='"+email+"',series_name='"+seriesName+"', remarks='"
                        +remarks+"', updated_by = '"+SamsUtilities.getUserName()+"' where asn="+asn;
                
                //System.out.println(mainTableQuery);
                c14.a=c14.st.executeUpdate(mainTableQuery);
                //System.out.println(c14.a);
                
                if(c14.a == 1) flag=flag+1;
                
                if(flag==(5-3))
                {
                    JOptionPane.showMessageDialog(this, "RECORD MODIFIED SUCCESSFULLY", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    flag=0;
                    c14.con.commit();
                    c14.con.setAutoCommit(true);
                }
                else{
                    //System.out.println("flag: "+ flag);
                    c14.con.rollback(save1);
                    c14.con.setAutoCommit(true);
                }
                
                
                c14.closeAll();
                
                this.dispose();
                new sams();
            }
            catch(Exception e)
            {
                //System.out.println(e);
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR"+e, "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        
        if(ae.getSource()==back)
        {
            this.dispose();
            new sams();
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
                        //System.out.println(countQuery);
                        JOptionPane.showMessageDialog(this,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        fillSeriesConnection.closeAll();
                        return;
                    }
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(sqlQuery);
                    fillSeriesConnection.rs.next();
                    
                    
                    String centre = fillSeriesConnection.rs.getString(1);
                    if(centre.isEmpty())
                    {
                        //System.out.println(sqlQuery);
                        JOptionPane.showMessageDialog(this,"Invalid receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                        receiptNumberText.setText("");
                        receiptNumberText.requestFocus();
                        fillSeriesConnection.closeAll();
                        return;
                    }
                    
                    
                    counterDropDown.setSelectedItem(centre);
                    String bookNum = fillSeriesConnection.rs.getString(2);
                    //String subCounter = fillSeriesConnection.rs.getString(1);
                    
                    //String subCounterQuery = "select issued_to from sub_issue_details where series_name='"+seriesNameText+"' and book_num="+bookNum+" and rcpt_num="+rcptNum;
                    //System.out.println(centre);
                    
                    /*//subIssueCounterText.setText("");
                    //System.out.println(subCounterQuery);
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(subCounterQuery);
                    if(fillSeriesConnection.rs.next()){
                    String subCounter = fillSeriesConnection.rs.getString(1);
                    //System.out.println(subCounter);
                    //subIssueCounterText.setText(subCounter);
                    }*/
                    
                    fillSeriesConnection.rs = fillSeriesConnection.st.executeQuery(alreadyIssuedRcptCheckQuery);
                    fillSeriesConnection.rs.next();
                    //System.out.println(alreadyIssuedRcptCheckQuery);
                    
                    int existingAsnCount = fillSeriesConnection.rs.getInt(1);
                    //System.out.println(existingAsnCount);
                    int despatchCode=Integer.parseInt((String)distributionCodeDropDown.getSelectedItem());
                    if(existingAsnCount > 0 && despatchCode == 0)
                    {
                        //System.out.println(originalSeriesName + " " + seriesNameText + " " + originalRcptNumber + " " + rcpt);
                        if( seriesNameText.equals(originalSeriesName) == false || rcpt.equals(originalRcptNumber) == false ){
                            String distributionType = (String)distributionTypeDropDown.getSelectedItem();
                            //if( distributionType.equals("Distributor"))
                            {
                            JOptionPane.showMessageDialog(this,"Already used receipt number", "Invalid receipt number", JOptionPane.ERROR_MESSAGE);
                            receiptNumberText.setText("");
                            receiptNumberText.requestFocus();
                            fillSeriesConnection.closeAll();
                            return;
                        }
                        }
                    }
                    
                    
                    fillSeriesConnection.closeAll();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    //Except.except(exc, "ADD JOB CARD--Raw Material Thread Error");
                    fillSeriesConnection.closeAll();
                }
            }
        }
        
        if(fe.getSource() == subNumberText)
        {
            String subNumber = subNumberText.getText();
            int subNum;
            String subNumberCode = (String)subNumberCodeDropDown.getSelectedItem();
            if(subNumberCode.isEmpty() && subNumber.isEmpty() == false)
            {
                JOptionPane.showMessageDialog(this,"Please fill subscription number details", "Please fill subscription details", JOptionPane.ERROR_MESSAGE);
                subNumberText.requestFocus();
                return;
            }
            if(subNumberCode.isEmpty() == false && subNumber.isEmpty() == false)
            {
                subNum  = Integer.parseInt(subNumber);
                if(subNumber.equals(originalSubNumber) == false || subNumberCode.equals(originalSubNumberCode) == false)
                {
                    //String countQuery = "select count(asn) from basic where subnos = '"+subNumberCode+"' and subno = "+subNum;
                    String countQuery = "select count(asn) from "
                            + "subscribers_primary_details where "
                            + "subscription_code = '"+subNumberCode+"' "
                            + "and subscription_number = "+subNum;
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
                            subNumberText.requestFocus();
                        }
                        fillSeriesConnection.closeAll();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        fillSeriesConnection.closeAll();
                    }
                }
            }
        }
        //System.out.println("Lost");
        
    }
    
    public void itemStateChanged(ItemEvent ie)
    {
        
        if(ie.getSource() == stateNameDropDown)
        {
            stateCodeDropDown.setSelectedItem(SamsUtilities.getStateCodeForStateName((String)stateNameDropDown.getSelectedItem()));
        }
        
        
        if(ie.getSource()==subNumberCodeDropDown)
        {
            //districtText.setText("");
            boolean flag = false;
            for(int stateCode = 0 ; stateCode != items.length ; ++stateCode)
            {
                if(subNumberCodeDropDown.getSelectedItem() == items[stateCode])
                {
                    String sub_code_str = (String)(items[stateCode]);
                    String state_code_text = SamsUtilities.getStateCodeForSubCode(sub_code_str);
                    String state_name_text = SamsUtilities.getStateNameForStateCode(state_code_text);
                    //System.out.println("'"+sub_code_str+ "' '" +state_name_text + "' '" + state_code_text+"'" );
                    languageDropDown.setSelectedItem("Hindi");
                    languageDropDown.setEnabled(false);
                    flag = true;
                    
                    if(state_name_text.isEmpty() == false)
                    {
                        stateNameDropDown.setEnabled(false);
                        stateCodeDropDown.setEnabled(false);
                        stateCodeDropDown.setSelectedItem(state_code_text);
                        stateNameDropDown.setSelectedItem(state_name_text);
                        //stateNameDropDown.setSelectedItem(state_name_text);
                        distributionTypeDropDown.setSelectedItem("By Post");
                        distributionTypeDropDown.setEnabled(false);
                        distributionCodeDropDown.setEnabled(false);
                    }
                    else
                    {
                        stateNameDropDown.setSelectedIndex(0);
                        stateNameDropDown.setEnabled(true);
                        stateCodeDropDown.setSelectedIndex(0);
                        stateCodeDropDown.setEnabled(true);
                        distributionTypeDropDown.setSelectedItem("By Post");
                        distributionTypeDropDown.setEnabled(false);
                        distributionCodeDropDown.setEnabled(false);
                        
                    }
                    
                    if(sub_code_str.equals("BD"))
                    {
                        
                        stateCodeDropDown.setSelectedIndex(0);
                        stateCodeDropDown.setEnabled(true);
                        distributionTypeDropDown.setSelectedItem("Distributor");
                        distributionTypeDropDown.setEnabled(false);
                        distributionCodeDropDown.setEnabled(true);
                        
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
                        {
                            distributionTypeDropDown.setEnabled(true);
                            flag = false;
                        }
                        
                        distributionCodeDropDown.setEnabled(false);
                        
                    }
                    
                }
                if(!flag)
                    languageDropDown.setEnabled(true);
            }
        }
        
        
        if(ie.getSource()==distributionCodeDropDown)
        {
            try
            {
                connect c14=new connect();
                String sqlQuery = "select district, state from despcode where dno="+Integer.parseInt((String)distributionCodeDropDown.getSelectedItem());
                c14.rs=c14.st.executeQuery(sqlQuery);
                //System.out.println(sqlQuery);
                if(c14.rs.next()){
                    //System.out.println(c14.rs.getString(1));
                    districtText.setText(""+c14.rs.getString(1));
                    districtText.setEnabled(false);
                    stateCodeDropDown.setSelectedItem(c14.rs.getString(2));
                    stateCodeDropDown.setEnabled(false);
                    stateNameDropDown.setSelectedItem(SamsUtilities.getStateNameForStateCode((String)stateCodeDropDown.getSelectedItem()));
                    stateNameDropDown.setEnabled(false);
                }
                c14.closeAll();
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                
            }
            
        }
        if(ie.getSource()==distributionTypeDropDown)
        {
            if(distributionTypeDropDown.getSelectedItem()=="Distributor")
            {
                distributionCodeDropDown.setEnabled(true);
                distributionCodeDropDown.setSelectedIndex(0);
            }
            
            if(distributionTypeDropDown.getSelectedItem()=="By Hand"||distributionTypeDropDown.getSelectedItem()=="By Post")
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
                e.printStackTrace();
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
                e.printStackTrace();
                
            }
        }
        
        if(ie.getSource()==paymentTypeDropDown)
        {
            if(paymentTypeDropDown.getSelectedItem()=="Cash")
            {
                chequeInstrumentNumberText.setText("0");
                chequeInstrumentNumberText.setEnabled(false);
            }
            
            if(paymentTypeDropDown.getSelectedItem()=="CH/DD/MO")
            {
                chequeInstrumentNumberText.setText("");
                chequeInstrumentNumberText.setEnabled(true);
            }
        }
        
        if(ie.getSource()==subNumberCodeDropDown)
        {
            districtText.setEnabled(true);
            
            for(int stateCode = 0 ; stateCode != items.length ; ++stateCode)
            {
                if(subNumberCodeDropDown.getSelectedItem() == items[stateCode])
                {
                    String sub_code_str = (String)(items[stateCode]);
                    String state_code_text = SamsUtilities.getStateCodeForSubCode(sub_code_str);
                    String state_name_text = SamsUtilities.getStateNameForSubCode(sub_code_str);
                    //System.out.println("'"+sub_code_str+ "' '" +state_name_text + "' '" + state_code_text+"'" );
                    
                    
                    if(state_name_text.isEmpty() == false)
                    {
                        stateNameDropDown.setEnabled(false);
                        stateCodeDropDown.setEnabled(false);
                        //stateCodeDropDown.setSelectedItem(state_code_text);
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
            if(subt22.getSelectedItem()=="DL")
            {
            statt1.setText("DL");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            
            }
            
            
            if(subt22.getSelectedItem()=="HR")
            {
            statt1.setText("HAR");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            if(subt22.getSelectedItem()=="MH")
            {
            statt1.setText("MAH");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            if(subt22.getSelectedItem()=="MP")
            {
            statt1.setText("MP");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            
            if(subt22.getSelectedItem()=="PB")
            {
            statt1.setText("PJB");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            if(subt22.getSelectedItem()=="RJ")
            {	statt1.setText("RAJ");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            if(subt22.getSelectedItem()=="UK")
            {
            statt1.setText("UK");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            if(subt22.getSelectedItem()=="UP")
            {
            statt1.setText("UP");
            statt1.setEnabled(false);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            }
            
            
            
            if(subt22.getSelectedItem()=="LF")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            
            }
            
            if(subt22.getSelectedItem()=="BH")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            
            }
            
            if(subt22.getSelectedItem()=="MS")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            
            }
            
            if(subt22.getSelectedItem()=="LH")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            
            }
            
            if(subt22.getSelectedItem()=="CM")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Post");
            distributionTypeDropDown.setEnabled(false);
            districtText.setText("");
            
            }
            
            if(subt22.getSelectedItem()=="EN")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(true);
            districtText.setText("");
            
            }
            
            if(subt22.getSelectedItem()=="UR")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(true);
            
            }
            
            if(subt22.getSelectedItem()=="BD")
            {
            
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("Distributor");
            distributionTypeDropDown.setEnabled(false);
            
            }
            
            if(subt22.getSelectedItem()=="PJ")
            {
            statt1.setText("");
            statt1.setEnabled(true);
            distributionTypeDropDown.setSelectedItem("By Hand");
            distributionTypeDropDown.setEnabled(true);
            districtText.setText("");
            
            }
            */
        }
        
    }
}

