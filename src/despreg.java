import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class despreg extends JFrame implements ActionListener, ItemListener {

    public static void main(String args[]) {
        new despreg();
    }
    JLabel lang, hdr, subnof, subnot, ftr, start, end, blank;
    JTextField hdrt, subnoft, subnott, ftrt, startt, endt, blankt;
    JButton ok, clr, back;
    JComboBox subt1, subt2;

    public despreg() {

        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            System.out.println(cnf);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        setTitle("Despatch Register");
        setSize(450, 480);
        setResizable(false);
        setLocation(10, 10);
        setLayout(null);


        hdr = new JLabel("Header Line (Year)");
        subnof = new JLabel("Sub No From");
        subnot = new JLabel("Sub No To");
        ftr = new JLabel("Footer Line");
        start = new JLabel("Starting No");
        end = new JLabel("Ending no");
        blank = new JLabel("Blank Entries");


        hdrt = new JTextField(20);
        subnoft = new JTextField(20);
        subnott = new JTextField(20);
        ftrt = new JTextField(20);
        startt = new JTextField(20);
        endt = new JTextField(20);
        blankt = new JTextField(20);

        ok = new JButton("OK");
        clr = new JButton("Clear");
        back = new JButton("Back");

        subt1 = new JComboBox();

        subt1.addItem("BH");
        subt1.addItem("BD");
        subt1.addItem("CM");
        subt1.addItem("DL");
        subt1.addItem("EN");
        subt1.addItem("HR");
        subt1.addItem("LF");
        subt1.addItem("LH");
        subt1.addItem("MH");
        subt1.addItem("MP");
        subt1.addItem("MS");
        subt1.addItem("PB");
        subt1.addItem("PJ");
        subt1.addItem("RJ");
        subt1.addItem("UA");
        subt1.addItem("UP");
        subt1.addItem("UR");
        subt1.addItemListener(this);


        subt2 = new JComboBox();

        subt2.addItem("BH");
        subt2.addItem("BD");
        subt2.addItem("CM");
        subt2.addItem("DL");
        subt2.addItem("EN");
        subt2.addItem("HR");
        subt2.addItem("LF");
        subt2.addItem("LH");
        subt2.addItem("MH");
        subt2.addItem("MP");
        subt2.addItem("MS");
        subt2.addItem("PB");
        subt2.addItem("PJ");
        subt2.addItem("RJ");
        subt2.addItem("UA");
        subt2.addItem("UP");
        subt2.addItem("UR");

        subt2.setEnabled(false);


        hdr.setBounds(30, 30, 90, 20);
        add(hdr);

        subnof.setBounds(30, 70, 120, 20);
        add(subnof);

        subnot.setBounds(30, 110, 120, 20);
        add(subnot);

        ftr.setBounds(30, 150, 120, 20);
        add(ftr);

        start.setBounds(30, 190, 120, 20);
        add(start);

        end.setBounds(300, 190, 60, 20);
        add(end);

        blank.setBounds(30, 230, 120, 20);
        add(blank);


        hdrt.setBounds(160, 30, 250, 20);
        add(hdrt);

        subt1.setBounds(160, 70, 50, 20);
        add(subt1);

        subnoft.setBounds(230, 70, 70, 20);
        add(subnoft);

        subt2.setBounds(160, 110, 50, 20);
        add(subt2);

        subnott.setBounds(230, 110, 70, 20);
        add(subnott);

        ftrt.setBounds(160, 150, 250, 20);
        add(ftrt);

        startt.setBounds(160, 190, 60, 20);
        add(startt);
        startt.setEnabled(false);

        endt.setBounds(360, 190, 60, 20);
        add(endt);
        endt.setEnabled(false);

        blankt.setBounds(160, 230, 60, 20);
        add(blankt);
        blankt.setEnabled(false);



        ok.setBounds(80, 270, 70, 30);
        ok.addActionListener(this);
        ok.setMnemonic('O');
        add(ok);

        clr.setBounds(180, 270, 70, 30);
        clr.addActionListener(this);
        clr.setMnemonic('C');
        add(clr);

        back.setBounds(280, 270, 70, 30);
        back.addActionListener(this);
        back.setMnemonic('B');
        add(back);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ok) {
            new despatch((String) subt1.getSelectedItem(), Integer.parseInt(subnoft.getText()), Integer.parseInt(subnott.getText()), hdrt.getText(), ftrt.getText());
            this.dispose();
        }

        if (ae.getSource() == clr) {
        }

        if (ae.getSource() == back) {
            new sams();
            this.dispose();
        }
    }

    int st, en, bl, sum;
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == subt1) {
            
            subt2.setSelectedItem((String) subt1.getSelectedItem());
            try {
                connect c1 = new connect();
                /*c1.rs = c1.st.executeQuery("select min(subno) from basic where subnos='" + (String) subt1.getSelectedItem() + "'");
                c1.rs.next();
                st = c1.rs.getInt(1);
                startt.setText("" + st);
                //subnoft.setText("" + st);
                 * */
                //st=Integer.parseInt(startt.getText());

                c1.rs = c1.st.executeQuery("select max(subno) from basic where subnos='" + (String) subt1.getSelectedItem() + "'");
                c1.rs.next();
                en = c1.rs.getInt(1);
                endt.setText("" + en);
                //subnott.setText(""+(en+100));

                c1.rs = c1.st.executeQuery("select count(subno) from basic where subnos='" + (String) subt1.getSelectedItem() + "'");
                c1.rs.next();
                bl = c1.rs.getInt(1);


                if (en != st) {
                    sum = en - st + 1;
                } else {
                    sum = en - st;
                }
                sum = sum - bl;
                blankt.setText("" + sum);

                String s = (String) subt1.getSelectedItem();

                if (s.equals("BH")) {
                    ftrt.setText("BY HAND");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("BD")) {
                    ftrt.setText("BULK DISTRIBUTORS");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("CM")) {
                    ftrt.setText("COMPLIMENTARY");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("DL")) {
                    ftrt.setText("DELHI / NEW DELHI");
                    subnoft.setText("10001");
                    startt.setText("10001");
                    st=10001;
                }

                if (s.equals("EN")) {
                    ftrt.setText("ENGLISH MEMBERS");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("HR")) {
                    ftrt.setText("HARAYANA");
                    subnoft.setText("20001");
                    startt.setText("20001");
                    st=20001;
                }

                if (s.equals("LF")) {
                    ftrt.setText("LIFE - BY POST");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("LH")) {
                    ftrt.setText("LIFE - BY HAND");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("MH")) {
                    ftrt.setText("MAHARASHTRA");
                    subnoft.setText("30001");
                    startt.setText("30001");
                    st=30001;
                }

                if (s.equals("MP")) {
                    ftrt.setText("MADHYA PRADESH");
                    subnoft.setText("40001");
                    startt.setText("40001");
                    st=40001;
                }

                if (s.equals("MS")) {
                    ftrt.setText("MISCELLANEOUS STATES");
                    subnoft.setText("90001");
                    startt.setText("90001");
                    st=90001;
                }

                if (s.equals("PB")) {
                    ftrt.setText("PUNJAB");
                    subnoft.setText("50001");
                    startt.setText("50001");
                    st=50001;
                }

                if (s.equals("RJ")) {
                    ftrt.setText("RAJASTHAN");
                    subnoft.setText("60001");
                    startt.setText("60001");
                    st=60001;
                }

                if (s.equals("UA")) {
                    ftrt.setText("UTTARANCHAL");
                    subnoft.setText("70001");
                    startt.setText("70001");
                    st=70001;
                }

                if (s.equals("UP")) {
                    ftrt.setText("UTTAR PRADESH");
                    subnoft.setText("80001");
                    startt.setText("80001");
                    st=80001;
                }

                if (s.equals("UR")) {
                    ftrt.setText("URDU MEMBERS");
                    subnoft.setText("1");
                    startt.setText("1");
                    st=1;
                }

                if (s.equals("PJ")) {
                    ftrt.setText("PUNJABI MEMBERS");
                    subnoft.setText("0");
                    startt.setText("0");
                    st=0;
                }


            /*
            BH	BY HAND
            BD	BULK DISTRIBUTORS
            CM	COMPLIMENTORY
            DL	DELHI / NEW DELHI
            EN 	ENGLISH MEMBERS
            HR	HARYANA
            LF	LIFE – BY POST
            LH	LIFE – BY HAND
            MH	MAHARASHTARA
            MP	MADHYA PRADESH
            MS	MISCELLENEOUS STATES
            PB 	PUNJAB
            RJ	RAJASTHAN
            UA	UTTARANCHAL
            UP	UTTAR PRADESH
            UR	URDU MEMBERS
            PJ	PUNJABI MEMBERS
             */


            } catch (Exception ex) {
                System.out.println(ex);
                new except(ex, this.getClass().toString());
            }


        }
    }
}