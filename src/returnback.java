
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Sanjay
 */
public class returnback extends JFrame implements ActionListener {
    
    public static void main(String arg[]) {
        new returnback();
    }
    String ret = "", posts, others, recs, firstName, lastName;
    JLabel l1, l2, post, rec_date, other, nameLabel;
    JComboBox subs;
    JTextField t1, t2, post_t, rec_date_t, other_t, firstNameText, lastNameText;
    JButton retrieveButton, markButton, stopButton, activateButton, back;
    int asn = 0;
    
    public returnback() {
        
        
        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            JOptionPane.showMessageDialog(this, "ERROR : " + cnf, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Return Back");
        setSize(500, 100);
        setLocation(10, 10);
        setLayout(null);
        SamsUtilities.center(this);
        subs = new JComboBox();
        
        l1 = new JLabel("Sub No");
        l1.setBounds(30, 30, 60, 20);
        add(l1);
        
        subs = new JComboBox(SamsUtilities.getSubCodes());
        /*
        //subs.addItem("BH");
        //subs.addItem("BD");
        subs.addItem("CM");
        subs.addItem("DL");
        subs.addItem("EN");
        subs.addItem("HR");
        subs.addItem("LF");
        subs.addItem("LH");
        subs.addItem("MH");
        subs.addItem("MP");
        subs.addItem("MS");
        subs.addItem("PB");
        subs.addItem("PJ");
        subs.addItem("RJ");
        subs.addItem("UK");
        subs.addItem("UP");
        //subs.addItem("UR");*/
        subs.setBounds(130, 30, 70, 20);
        add(subs);
        
        t1 = new JTextField();
        t1.setBounds(210, 30, 60, 20);
        add(t1);
        
        retrieveButton = new JButton("Retrieve");
        retrieveButton.setBounds(270, 30, 80, 25);
        retrieveButton.setMnemonic('R');
        add(retrieveButton);
        retrieveButton.addActionListener(this);
        
        
        markButton = new JButton("Mark");
        markButton.setBounds(40, 240, 80, 25);
        markButton.setMnemonic('M');
        markButton.addActionListener(this);
        
        
        stopButton = new JButton("STOP");
        stopButton.setBounds(130, 240, 80, 25);
        stopButton.setMnemonic('S');
        stopButton.addActionListener(this);
        
        activateButton = new JButton("Activate");
        activateButton.setBounds(220, 240, 80, 25);
        activateButton.setMnemonic('A');
        activateButton.addActionListener(this);
        
        back = new JButton("Back");
        back.setBounds(360, 30, 80, 25);
        back.setMnemonic('B');
        back.addActionListener(this);
        add(back);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            new sams();
            this.dispose();
        }
        if (ae.getSource() == retrieveButton) {
            
            nameLabel = new JLabel("Name");
            nameLabel.setBounds(30,60,60,20);
            add(nameLabel);
            
            firstNameText = new JTextField();
            firstNameText.setBounds(130,60,180,20);
            add(firstNameText);
            
            lastNameText = new JTextField();
            lastNameText.setBounds(130,90,180,20);
            add(lastNameText);
            
            //retrieveButton.setEnabled(false);
            l2 = new JLabel("Month");
            l2.setBounds(30, 120, 60, 20);
            add(l2);
            
            t2 = new JTextField();
            t2.setBounds(130, 120, 180, 20);
            add(t2);
            
            post = new JLabel("PO Remarks");
            post.setBounds(30, 150, 80, 20);
            add(post);
            
            
            post_t = new JTextField();
            post_t.setBounds(130, 150, 180, 20);
            add(post_t);
            
            rec_date = new JLabel("<html>Recieving Date</html>");
            rec_date.setBounds(30, 180, 80, 28);
            add(rec_date);
            
            
            rec_date_t = new JTextField();
            rec_date_t.setBounds(130, 180, 180, 20);
            add(rec_date_t);
            
            other = new JLabel("Other Remarks");
            other.setBounds(30, 210, 90, 20);
            add(other);
            
            
            other_t = new JTextField();
            other_t.setBounds(130, 210, 180, 20);
            add(other_t);
            
            add(markButton);
            add(stopButton);
            add(activateButton);
            
            try {
                connect c1 = new connect();
                //connect c2 = new connect();
                
                String sqlQuery = "select o.asn , o.ret, o.post,o.rec_date, o.oth_remarks from basic b, "
                        + "otherdet o where o.asn=b.asn and o.asn in (select asn from basic b where subnos='"
                        + subs.getSelectedItem().toString() + "' and subno=" + t1.getText() + ")";
                
                String mainTableQuery = "select asn ,return_issue_month, return_back_reason,return_back_stop_date, return_back_other_remarks, first_name, last_name from  "
                        + "subscribers_primary_details where subscription_code='"
                        + subs.getSelectedItem().toString() + "' and subscription_number=" + t1.getText();
                
                
                System.out.println(mainTableQuery);
                if(false)c1.rs = c1.st.executeQuery(sqlQuery);
                c1.rs = c1.st.executeQuery(mainTableQuery);
                
                
                if (c1.rs.next()) {
                    //c1.rs.next();
                    asn = c1.rs.getInt(1);
                    ret = c1.rs.getString(2);
                    posts = c1.rs.getString(3);
                    recs = c1.rs.getString(4);
                    others = c1.rs.getString(5);
                    firstName = c1.rs.getString(6);
                    lastName = c1.rs.getString(7);
                    
                    t2.setText(ret);
                    post_t.setText(posts);
                    rec_date_t.setText(recs);
                    other_t.setText(others);
                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    
                    c1.closeAll();
                    this.setSize(500, 350);
                    retrieveButton.setEnabled(false);
                }
                else
                    JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);
                
                
                
            } catch (Exception e) {
                e.printStackTrace();
                new except(e, this.getClass().toString());
            }
        }
        
        if (ae.getSource() == markButton) {
            
            try {
                if (!ret.equals(t2.getText().trim())) {
                    connect c2 = new connect();
                    String updateCommand = "update otherdet set ret='" + t2.getText()
                            + "' , post='" + post_t.getText() + "' , rec_date='"
                            + rec_date_t.getText() + "' , oth_remarks='"
                            + other_t.getText() + "'  where asn=" + asn;
                    
                    String mainTableUpdateCommand = "update subscribers_primary_details set return_issue_month = '" + t2.getText()
                            + "' , return_back_reason = '" + post_t.getText() + "' , return_back_stop_date='"
                            + rec_date_t.getText() + "' , return_back_other_remarks='"
                            + other_t.getText() + "'  where asn=" + asn;
                    
                    if(false)c2.a = c2.st.executeUpdate(updateCommand);
                    c2.a = c2.st.executeUpdate(mainTableUpdateCommand);
                    if (c2.a > 0) {
                        JOptionPane.showMessageDialog(null, "Return Back Marked Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new sams();
                        /*c2.a = c2.st.executeUpdate(mainTableUpdateCommand);
                        if (c2.a > 0) {
                        }*/
                    }
                    c2.closeAll();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter a new Month First", "ERROR", JOptionPane.ERROR_MESSAGE);
                    t2.requestFocus();
                    //this.dispose();
                    //new sams();
                    
                }
                
            } catch (Exception e) {
                new except(e, this.getClass().toString());
            }
            
        }
        
        if (ae.getSource() == stopButton) {
            
            try {
                String ret_status="";
                connect c3 = new connect();
                //c3.rs=c3.st.executeQuery("select ret from otherdet where asn="+asn);
                
                //main table query
                c3.rs=c3.st.executeQuery("select return_issue_month from subscribers_primary_details where asn="+asn);
                
                while(c3.rs.next())
                {
                    ret_status=c3.rs.getString(1);
                }
                
                String otherDetCommand = "update otherdet set ret='"+ret_status+" :STOPPED' , post='"
                        + post_t.getText() + "' , rec_date='" + rec_date_t.getText()
                        + "' , oth_remarks='" + other_t.getText() + "'  where asn=" + asn;
                
                String mainTableUpdateCommand = "update subscribers_primary_details set return_issue_month ='"+ret_status
                        +" :STOPPED' , return_back_reason = '"+ post_t.getText()
                        + "' , return_back_stop_date ='" + rec_date_t.getText()
                        + "' , return_back_other_remarks ='" + other_t.getText()
                        +"' where asn=" + asn;
                
                if(false)c3.a = c3.st.executeUpdate(otherDetCommand);
                c3.a += c3.st.executeUpdate(mainTableUpdateCommand);
                
                //String basicCommand = "update basic set status='STOPPED' where asn=" + asn;
                String mainTableCommand = "update subscribers_primary_details set membership_status = 'STOPPED' where asn = " +asn;
                //c3.a += c3.st.executeUpdate(basicCommand);
                c3.a += c3.st.executeUpdate(mainTableCommand);
                
                if (c3.a == 2 ) {
                    JOptionPane.showMessageDialog(null, "Account Stopped Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new sams();
                }
                
                c3.st.close();
                c3.con.close();
                
            } catch (Exception e) {
                new except(e, this.getClass().toString());
            }
            
        }
        
        if (ae.getSource() == activateButton) {
            
            try {
                //System.out.println(asn);
                connect c4 = new connect();
                //int a = c4.st.executeUpdate("update otherdet set ret='0',  post='' , rec_date='' , oth_remarks='' where asn=" + asn);
                int a = c4.st.executeUpdate("update subscribers_primary_details set return_issue_month='0',  return_back_reason='' , return_back_stop_date='' , return_back_other_remarks='' where asn=" + asn);
                //System.out.println("a :" + a);
                
                //int b = c4.st.executeUpdate("update basic b set b.status='Active' where b.asn=" + asn);
                int b = c4.st.executeUpdate("update subscribers_primary_details set membership_status='Active' where asn=" + asn);
                //System.out.println("b :" + b + asn);
                if ((a + b) == 2) {
                    JOptionPane.showMessageDialog(null, "Account Activation Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new sams();
                    
                }
                
                c4.closeAll();
                
            } catch (Exception e) {
                e.printStackTrace();
                //new except(e, this.getClass().toString());
            }
            
        }
        
        
        
    }
}
