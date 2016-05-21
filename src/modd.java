import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class modd extends JFrame implements ActionListener
{
    
    public static void main(String args[])
    {
        new modd(2);
    }
    
    JLabel nam, phno, moddcd, email, add1, rem, hist, dist, stat, pin, distributionTypeLabel;
    JTextField  remt, histt, distt;
    TextFieldWithLimit namt, lnamt, phnot, moddcdt, addt11, addt12, addt13, statt, pint, emailt;
    JComboBox distributionTypeDropDown;
    JButton mod, back;
    int dno;
    public modd(int d)
    {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        dno=d;
        try
        {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            new except(cnf, this.getClass().toString());
            System.out.println(cnf);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        setTitle("Modify Despatch Code Details");
        setSize(800,500);
        //this.pack();
        setResizable(false);
        setLocation(10,10);
        setLayout(null);
        //setUndecorated(true);
        
        nam=new JLabel("Name");
        phno=new JLabel("Phone No");
        moddcd=new JLabel("Desp Code");
        email=new JLabel("email id");
        add1=new JLabel("Address");
        rem=new JLabel("Remarks");
        hist=new JLabel("History");
        dist=new JLabel("District");
        stat=new JLabel("State");
        pin=new JLabel("Pin Code");
        distributionTypeLabel = new JLabel("<html>Dist Type</html>");
        
        
        
        namt=new TextFieldWithLimit(16,16);
        lnamt=new TextFieldWithLimit(15,15);
        phnot=new TextFieldWithLimit(13,12);
        moddcdt=new TextFieldWithLimit(3,2);
        addt11=new TextFieldWithLimit(33,32);
        addt12=new TextFieldWithLimit(33,32);
        addt13=new TextFieldWithLimit(33,32);
        statt=new TextFieldWithLimit(4,3);
        pint=new TextFieldWithLimit(7,6);
        
        histt=new JTextField(20);
        emailt=new TextFieldWithLimit(32,32);
        distt=new JTextField(20);
        remt=new JTextField(20);
        
        mod=new JButton("Modify");
        //clr=new JButton("Clear");
        back=new JButton("Back");
        
        distributionTypeDropDown = new JComboBox();
        distributionTypeDropDown.addItem("By Post");
        distributionTypeDropDown.addItem("By Hand");
        
        nam.setBounds(20,30,50,20);
        add(nam);
        
        phno.setBounds(400,30,80,20);
        add(phno);
        
        moddcd.setBounds(20,70,130,20);
        add(moddcd);
        
        email.setBounds(400,70,50,20);
        add(email);
        
        add1.setBounds(20,110,80,20);
        add(add1);
        
        rem.setBounds(20,230,80,20);
        add(rem);
        
        hist.setBounds(400,230,80,20);
        add(hist);
        
        dist.setBounds(20,270,90,20);
        add(dist);
        
        stat.setBounds(400,270,50,20);
        add(stat);
        
        pin.setBounds(20,310,90,20);
        add(pin);
        
        distributionTypeLabel.setBounds(180,70,80,20);
        add(distributionTypeLabel);
        
        distributionTypeDropDown.setBounds(260,70,120,20);
        add(distributionTypeDropDown);
        
        //textfields
        //-------------------------------------------------data input------------------------------------------//
        
        try
        {
            connect c2=new connect();
            c2.rs=c2.st.executeQuery("select * from despcode where dno="+dno);
            c2.rs.next();
            namt.setText(c2.rs.getString(2));
            lnamt.setText(c2.rs.getString(3));
            phnot.setText(c2.rs.getString(4));
            emailt.setText(c2.rs.getString(5));
            addt11.setText(c2.rs.getString(6));
            addt12.setText(c2.rs.getString(7));
            addt13.setText(c2.rs.getString(8));
            remt.setText(c2.rs.getString(9));
            histt.setText(c2.rs.getString(10));
            distt.setText(c2.rs.getString(11));
            statt.setText(c2.rs.getString(12));
            pint.setText(c2.rs.getString(13));
        }
        catch(Exception e)
        {
            new except(e, this.getClass().toString());
            
        }
        
        namt.setBounds(100,30,130,20);
        add(namt);
        
        lnamt.setBounds(250,30,130,20);
        add(lnamt);
        
        phnot.setBounds(480,30,120,20);
        add(phnot);
        
        moddcdt.setBounds(100,70,30,20);
        add(moddcdt);
        moddcdt.setText(""+dno);
        
        emailt.setBounds(480,70,240,20);
        add(emailt);
        
        addt11.setBounds(100,110,240,20);
        add(addt11);
        
        addt12.setBounds(100,140,240,20);
        add(addt12);
        
        addt13.setBounds(100,170,240,20);
        add(addt13);
        
        remt.setBounds(100,230,240,20);
        add(remt);
        
        histt.setBounds(480,230,240,20);
        add(histt);
        
        distt.setBounds(100,270,200,20);
        add(distt);
        
        statt.setBounds(480,270,40,20);
        add(statt);
        
        pint.setBounds(100,310,60,20);
        pint.setText("0");
        add(pint);
        
        mod.setBounds(170,360,100,25);
        mod.addActionListener(this);
        mod.setMnemonic('M');
        add(mod);
        
        back.setBounds(430,360,100,25);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==mod)
        {
            
            String name1,lname1, phone, email, add1, add2, add3, remarks, history, district, state;
            String distributionTypeText;
            int pinno;
            //dno=Integer.parseInt(moddcdt.getText());
            name1=namt.getText();
            lname1=lnamt.getText();
            phone=phnot.getText();
            email=emailt.getText();
            add1=addt11.getText();
            add2=addt12.getText();
            add3=addt13.getText();
            remarks=remt.getText();
            history=histt.getText();
            district=distt.getText();
            state=statt.getText();
            pinno=Integer.parseInt(pint.getText());
            distributionTypeText = (String)(distributionTypeDropDown.getSelectedItem());
            //System.out.println(""+dno+name1+phone+email+add1+add2+add3+remarks+history+district+state+pinno);
            
            try
            {
                
                connect c1=new connect();
                c1.a=c1.st.executeUpdate("update despcode set fname='"+name1+"', lname='"+lname1+"',phone='"+phone+"',email='"+email+"',add1='"+add1+"',add2='"+add2+"',add3='"+add3+"',remarks='"+remarks+"',history='"+history+"',district='"+district+"',state='"+state+"',pinno="+pinno+", distributionType = '"+distributionTypeText+"' where dno="+dno);
                if(c1.a==1)
                {
                    JOptionPane.showMessageDialog(this, "Modification Done SuccessFully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    new moddesp();
                    this.dispose();
                }
                c1.st.close();
                c1.con.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            
            
        }
        
        if(ae.getSource()==back)
        {
            new moddesp();
            this.dispose();
            
        }
    }
}
