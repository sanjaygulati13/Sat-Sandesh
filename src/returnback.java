
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
    String ret = "", posts, others, recs;
    JLabel l1, l2, post, rec_date, other;
    JComboBox subs;
    JTextField t1, t2, post_t, rec_date_t, other_t;
    JButton b1, b2, b3, b4, back;
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
        subs.setBounds(130, 30, 50, 20);
        add(subs);

        t1 = new JTextField();
        t1.setBounds(190, 30, 60, 20);
        add(t1);

        b1 = new JButton("Retrieve");
        b1.setBounds(270, 30, 80, 25);
        b1.setMnemonic('R');
        add(b1);
        b1.addActionListener(this);


        b2 = new JButton("Mark");
        b2.setBounds(40, 180, 80, 25);
        b2.setMnemonic('M');
        b2.addActionListener(this);


        b3 = new JButton("STOP");
        b3.setBounds(130, 180, 80, 25);
        b3.setMnemonic('S');
        b3.addActionListener(this);

        b4 = new JButton("Activate");
        b4.setBounds(220, 180, 80, 25);
        b4.setMnemonic('A');
        b4.addActionListener(this);

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
        if (ae.getSource() == b1) {

            b1.setEnabled(false);
            l2 = new JLabel("Month");
            l2.setBounds(30, 60, 60, 20);
            add(l2);

            t2 = new JTextField();
            t2.setBounds(130, 60, 180, 20);
            add(t2);

            post = new JLabel("PO Remarks");
            post.setBounds(30, 90, 80, 20);
            add(post);


            post_t = new JTextField();
            post_t.setBounds(130, 90, 180, 20);
            add(post_t);

            rec_date = new JLabel("Recieving Date");
            rec_date.setBounds(30, 120, 80, 20);
            add(rec_date);


            rec_date_t = new JTextField();
            rec_date_t.setBounds(130, 120, 180, 20);
            add(rec_date_t);

            other = new JLabel("Other Remarks");
            other.setBounds(30, 150, 90, 20);
            add(other);


            other_t = new JTextField();
            other_t.setBounds(130, 150, 180, 20);
            add(other_t);

            add(b2);
            add(b3);
            add(b4);

            try {
                connect c1 = new connect();

                c1.rs = c1.st.executeQuery("select o.asn , o.ret, o.post,o.rec_date, o.oth_remarks from basic b, otherdet o where o.asn=b.asn and o.asn in (select asn from basic b where subnos='" + subs.getSelectedItem().toString() + "' and subno=" + t1.getText() + ")");
                if (c1.rs == null) {
                    JOptionPane.showMessageDialog(null, "Record Not Found", "ERROR", JOptionPane.ERROR_MESSAGE);

                } else {
                    c1.rs.next();
                    asn = c1.rs.getInt(1);
                    ret = c1.rs.getString(2);
                    posts = c1.rs.getString(3);
                    recs = c1.rs.getString(4);
                    others = c1.rs.getString(5);

                    t2.setText(ret);
                    post_t.setText(posts);
                    rec_date_t.setText(recs);
                    other_t.setText(others);

                    c1.rs.close();
                    c1.st.close();
                    c1.con.close();
                    this.setSize(500, 300);
                }


            } catch (Exception e) {
                e.printStackTrace();
                new except(e, this.getClass().toString());
            }
        }

        if (ae.getSource() == b2) {

            try {
                if (!ret.equals(t2.getText().trim())) {
                    connect c2 = new connect();
                    c2.a = c2.st.executeUpdate("update otherdet set ret='" + t2.getText() + "' , post='" + post_t.getText() + "' , rec_date='" + rec_date_t.getText() + "' , oth_remarks='" + other_t.getText() + "'  where asn=" + asn);
                    if (c2.a > 0) {
                        JOptionPane.showMessageDialog(null, "Return Back Marked Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new sams();
                    }
                    c2.st.close();
                    c2.con.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter a new Month First", "ERROR", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    new sams();

                }

            } catch (Exception e) {
                new except(e, this.getClass().toString());
            }

        }

        if (ae.getSource() == b3) {

            try {
                String ret_status="";
                connect c3 = new connect();
                c3.rs=c3.st.executeQuery("select ret from otherdet where asn="+asn);
                while(c3.rs.next())
                {
                    ret_status=c3.rs.getString(1);
                }
                c3.a = c3.st.executeUpdate("update otherdet set ret='"+ret_status+" :STOPPED' , post='" + post_t.getText() + "' , rec_date='" + rec_date_t.getText() + "' , oth_remarks='" + other_t.getText() + "'  where asn=" + asn);
                c3.a += c3.st.executeUpdate("update basic set status='STOPPED' where asn=" + asn);
                if (c3.a > 1) {
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

        if (ae.getSource() == b4) {

            try {
                //System.out.println(asn);
                connect c4 = new connect();
                int a = c4.st.executeUpdate("update otherdet set ret='0',  post='' , rec_date='' , oth_remarks='' where asn=" + asn);
                System.out.println("a :" + a);

                c4.st.close();
                c4.con.close();

                connect c5 = new connect();
                int b = c5.st.executeUpdate("update basic b set b.status='Active' where b.asn=" + asn);
                System.out.println("b :" + b + asn);
                if ((a + b) ==2) {
                    JOptionPane.showMessageDialog(null, "Account Activation Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new sams();

                }

                c5.st.close();
                c5.con.close();


            } catch (Exception e) {
                new except(e, this.getClass().toString());
            }

        }



    }
}
