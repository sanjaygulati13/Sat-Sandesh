
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.print.*;

public class petty_report extends JFrame implements ActionListener {

    JLabel l1, l2;
    TextFieldWithLimit t2, t3;
    JTable tb1;
    JScrollPane sp;
    Container con;
    JButton b1, b3, b4;
    Object col[] = {"DATE", "KIRPAL BAGH", "KIRPAL ASHRAM", "SAWAN ASHRAM", "TOTAL"};
    JComboBox subt;

    public static void main(String args[]) {
        new petty_report();
    }

    petty_report() {
        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            System.out.println(cnf);
        }
        con = getContentPane();
        setSize(950, 600);
        setLocation(200, 80);
        //setVisible(true);
        con.setLayout(null);
        setTitle("Petty Sales Details");
        //con.setBackground(Color.cyan);
        l1 = new JLabel("Enter Month");
        l1.setBounds(170, 40, 70, 20);
        con.add(l1);


        t2 = new TextFieldWithLimit(2, 2);
        t2.setBounds(250, 40, 30, 20);
        con.add(t2);

        t3 = new TextFieldWithLimit(4, 4);
        t3.setBounds(290, 40, 40, 20);
        con.add(t3);



        b1 = new JButton("Show");
        b1.setBounds(380, 40, 80, 25);
        b1.setMnemonic('S');

        con.add(b1);
        b1.addActionListener(this);

        b3 = new JButton("Close");
        b3.setMnemonic('C');
        b3.setBounds(480, 530, 90, 25);
        con.add(b3);
        b3.addActionListener(this);

        b4 = new JButton("Print");
        b4.setMnemonic('P');
        b4.setBounds(200, 530, 90, 25);
        con.add(b4);
        b4.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    int m1, y1;

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            b1.setEnabled(false);
            int i = 0;
            int[] dates;
            try {

                m1 = Integer.parseInt(t2.getText());
                y1 = Integer.parseInt(t3.getText());
                connect c1 = new connect();
                c1.rs = c1.st.executeQuery("select count(distinct datd) from petty where datm=" + m1 + " and daty=" + y1);
                c1.rs.next();
                i = c1.rs.getInt(1);
                c1.rs.close();

                //System.out.println(i);

                if (i == 0) {
                    JOptionPane.showMessageDialog(null, "No Records Found", "No Records", JOptionPane.ERROR_MESSAGE);
                } else {

                    int j = 0;
                    dates = new int[i];
                    connect c2 = new connect();

                    c2.rs = c2.st.executeQuery("select distinct datd from petty where datm=" + m1 + " and daty=" + y1);
                    while (c2.rs.next()) {
                        dates[j] = c2.rs.getInt(1);
                        j++;
                    }
                    c2.st.close();
                    c2.con.close();
                    c2.rs.close();

                    Object data[][] = new Object[i + 1][5];


                    j = 0;
                    int total = 0;
                    int total_kb=0;
                    int total_ka=0;
                    int total_sa=0;

                    connect c3=new connect();
                    for(int z=0;z<i;z++)
                    {

                        int amt=0;
                        int total_temp=0;
                        data[z][0] = dates[z]+"/"+m1+"/"+y1;
                        c3.rs=c3.st.executeQuery("select sum(amt) from petty where counter='Kirpal Bagh' and datd="+dates[z]+" and datm="+m1+" and daty="+y1);
                        while(c3.rs.next())
                        {
                            amt+=c3.rs.getInt(1);
                        }
                        data[z][1] = amt;
                        total_kb+=amt;
                        total_temp+=amt;
                        amt=0;

                        c3.rs=c3.st.executeQuery("select sum(amt) from petty where counter='Kirpal Ashram' and datd="+dates[z]+" and datm="+m1+" and daty="+y1);
                        while(c3.rs.next())
                        {
                            amt+=c3.rs.getInt(1);
                        }
                        data[z][2] = amt;
                        total_ka+=amt;
                        total_temp+=amt;
                        amt=0;


                        c3.rs=c3.st.executeQuery("select sum(amt) from petty where counter='Sawan Ashram' and datd="+dates[z]+" and datm="+m1+" and daty="+y1);
                        while(c3.rs.next())
                        {
                            amt+=c3.rs.getInt(1);
                        }
                        data[z][3] = amt;
                        total_sa+=amt;
                        total_temp+=amt;
                        amt=0;

                        data[z][4] = total_temp;

                        total+=total_temp;
                    }
                    data[i][0] = "TOTAL";
                    data[i][1] = total_kb;
                    data[i][2] = total_ka;
                    data[i][3] = total_sa;
                    data[i][4] = total;

                    tb1 = new JTable(data, col);
                    sp = new JScrollPane(tb1);
                    sp.setBounds(20, 100, 845, 410);
                    con.add(sp);
                }
            } catch (Exception e) {
                System.out.println("exception" + e);
                e.printStackTrace();
            }
        }
        /*if(ae.getSource()==b2)
        {
        new modify(Integer.parseInt(t2.getText()));
        this.dispose();
        }*/
        if (ae.getSource() == b3) {
            new sams();
            this.dispose();
        }


        if (ae.getSource() == b4) {

            try {
                MessageFormat headerFormat = new MessageFormat("Petty Sales Report for "+m1+"-"+y1+" "); // \t (Page {0})");
                MessageFormat footerFormat = new MessageFormat("- Page {0} -");
                tb1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
            } catch (PrinterException pe) {
                System.err.println("Error printing: " + pe.getMessage());
            }
        }

    }
}