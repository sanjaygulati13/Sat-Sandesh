import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class abc implements Printable, ActionListener {

    public static void main(String args[])
    {
        new abc("DL", 10001, 10900, "2010", "DELHI/NEW DELHI");
    }

    int[] pageBreak;
    int numLines = 0;
    String[][] textLines;
    int[] asn;
    int x = 0;
    int x1 = 0;
    int i = 0;
    int chk = 0;
    int m1, y1;
    JButton b, back;
    JFrame f;
    int mark;
    String subnos;
    int start;
    int end;
    int linesPerPage;
    String footer;
    String header = "SAT SANDESH : abc REGISTER FOR YEAR - ";

    public abc(String subno, int start1, int end1, String header1, String footer1) {
        subnos = subno;
        end = end1;
        start = start1;
        header += header1;
        footer = footer1;

        try {
            f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            new except(cnf, this.getClass().toString());
        }


        f = new JFrame("print register");
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(300, 100);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        b = new JButton("Print register");
        b.addActionListener(this);
        back = new JButton("Back");
        back.addActionListener(this);
        f.add(b);
        b.setBounds(10, 10, 100, 25);
        b.setMnemonic('P');
        f.add(back);
        back.setMnemonic('B');
        back.setBounds(150, 10, 100, 25);
    //f.pack();




    }

    public void initLines() {
        try {

            x = (end - start) + 1;
            //System.out.println("x : " + x);
            numLines = 2 * x;

            //========================================================================================

            int y = (numLines % linesPerPage);
            int page = (numLines / linesPerPage);


            if (numLines % linesPerPage > 0) {
                page++;
            }



            int head = page * 3;

            numLines = (x + head) * 2;
            y = numLines % linesPerPage;
            if (y < 4) {
                head += 3;
            }

            x += head;
            numLines = x * 2;
            page = (numLines / linesPerPage);

            //======================================================================================================


            int half2 = (page) / 2;
            int half = (page) % 2;
            if (half > 0) {
                half2++;
            }
            x += half2;

            if ((numLines % 55) > 53) {
                x++;
            }

            //====================================================================================================


            //System.out.println("x : " + x);


            numLines = x * 2;

            //====================================================================================



            textLines = new String[numLines][19];
            asn = new int[x];
            int s = 0;

            //System.out.println("hello " + numLines);

            connect comp = new connect();

            for (int j = start; j < end + 1; j++) {


                if (s % (linesPerPage / 2) == 0 && j<end) {
                    asn[s] = 0;
                    //System.out.println("asn[" + s + "]=" + asn[s]);
                    s++;
                }
                if (s % (linesPerPage / 2) == 1 && j<end) {
                    asn[s] = 0;
                    //System.out.println("asn[" + s + "]=" + asn[s]);
                    s++;
                }
                if (s % (linesPerPage / 2) == 2 && j<end) {
                    asn[s] = 0;
                    //System.out.println("asn[" + s + "]=" + asn[s]);
                    s++;
                }

                //System.out.println("j : "+j);
                if (s % (linesPerPage / 2) > 2)
                {
                    int k = 0;
                    comp.rs = comp.st.executeQuery("select count(asn) from basic where subnos='" + subnos + "' and subno=" + j);
                    comp.rs.next();
                    k = comp.rs.getInt(1);
                    if (k > 0)
                    {
                        comp.rs = comp.st.executeQuery("select asn from basic where subnos='" + subnos + "' and subno=" + j);
                        comp.rs.next();
                        asn[s] = comp.rs.getInt(1);
                    }
                    else
                    {
                        asn[s] = 0;
                    }
                    //System.out.println("asn[" + s + "]=" + asn[s]);
                    mark=s;
                    s++;

                }

            }
            comp.st.close();

            i = 0;
            int l = start;
            connect c3 = new connect();
            for (i = 0; i < x; i++) {

                if ( i > mark )
                {
                    textLines[i][0] = "";
                    textLines[i][1] = "";
                    textLines[i][2] = "";
                    textLines[i][3] = "";
                    textLines[i][4] = "";
                    textLines[i][5] = "";
                    textLines[i][6] = "";
                    textLines[i][7] = "";
                    textLines[i][8] = "";
                    textLines[i][9] = "";
                    textLines[i][10] = "";
                    textLines[i][11] = "";
                    textLines[i][12] = "";
                    textLines[i][13] = "";
                    textLines[i][14] = "";
                    textLines[i][15] = "";
                    textLines[i][16] = "";
                    textLines[i][17] = "";
                    textLines[i][18] = "";

                }
                else if (asn[i] == 0 && i % (linesPerPage / 2) == 0) {
                    textLines[i][0] = "";
                    textLines[i][1] = "";
                    textLines[i][2] = "";
                    textLines[i][3] = "";
                    textLines[i][4] = "";
                    textLines[i][5] = "";
                    textLines[i][6] = "";
                    textLines[i][7] = "";
                    textLines[i][8] = "";
                    textLines[i][9] = "";
                    textLines[i][10] = "";
                    textLines[i][11] = "";
                    textLines[i][12] = "";
                    textLines[i][13] = "";
                    textLines[i][14] = "";
                    textLines[i][15] = "";
                    textLines[i][16] = "";
                    textLines[i][17] = "";
                    textLines[i][18] = "";

                } else if (asn[i] == 0 && i % (linesPerPage / 2) == 1) {
                    textLines[i][0] = "";
                    textLines[i][1] = "";
                    textLines[i][2] = "" + header;
                    textLines[i][3] = "";
                    textLines[i][4] = "";
                    textLines[i][5] = "";
                    textLines[i][6] = "";
                    textLines[i][7] = "";
                    textLines[i][8] = "";
                    textLines[i][9] = "";
                    textLines[i][10] = "";
                    textLines[i][11] = "";
                    textLines[i][12] = "";
                    textLines[i][13] = "";
                    textLines[i][14] = "";
                    textLines[i][15] = "";
                    textLines[i][16] = "";
                    textLines[i][17] = "";
                    textLines[i][18] = "";

                } else if (asn[i] == 0 && i % (linesPerPage / 2) == 2) {
                    textLines[i][0] = "SUB NO";
                    textLines[i][1] = "NAME";
                    textLines[i][2] = "RCPT";
                    textLines[i][3] = "END Prd";
                    textLines[i][4] = "AMT";
                    textLines[i][5] = "RCPT";
                    textLines[i][6] = "New Prd";
                    textLines[i][7] = "01";
                    textLines[i][8] = "02";
                    textLines[i][9] = "03";
                    textLines[i][10] = "04";
                    textLines[i][11] = "05";
                    textLines[i][12] = "06";
                    textLines[i][13] = "07";
                    textLines[i][14] = "08";
                    textLines[i][15] = "09";
                    textLines[i][16] = "10";
                    textLines[i][17] = "11";
                    textLines[i][18] = "12";


                } else if (asn[i] == 0 && i % (linesPerPage / 2) > 2) {
                    textLines[i][0] = "" + subnos + l;
                    textLines[i][1] = "";
                    textLines[i][2] = "";
                    textLines[i][3] = "";
                    textLines[i][4] = "";
                    textLines[i][5] = "";
                    textLines[i][6] = "";
                    textLines[i][7] = "";
                    textLines[i][8] = "";
                    textLines[i][9] = "";
                    textLines[i][10] = "";
                    textLines[i][11] = "";
                    textLines[i][12] = "";
                    textLines[i][13] = "";
                    textLines[i][14] = "";
                    textLines[i][15] = "";
                    textLines[i][16] = "";
                    textLines[i][17] = "";
                    textLines[i][18] = "";
                    l++;

                } else if (asn[i] > 0) {
                    //System.out.println("hello2");
                    c3.rs = c3.st.executeQuery("select b.subnos, b.subno, b.rcpt, o.ret, b.dno from basic b, otherdet o where b.asn=" + asn[i] + " and o.asn=" + asn[i]);
                    //System.out.println("hello3");
                    c3.rs.next();
                    String ret = c3.rs.getString(4);
                    if (ret.equals("STOPPED")) {
                        ret = "STOPPED ON REQUEST/ RETURN BACK";
                    } else {
                        ret = "";
                    }

                    int dno = c3.rs.getInt(5);
                    String dnum = "";
                    if (dno != 0) {
                        connect c4 = new connect();
                        c4.rs = c4.st.executeQuery("select district, state from despcode where dno=" + dno);
                        c4.rs.next();
                        dnum = " D# - " + dno + " ," + c4.rs.getString(1) + " , " + c4.rs.getString(2);
                    } else {
                        dnum = "";
                    }

                    if (i % (linesPerPage / 2) > 2) {

                        textLines[i][0] = c3.rs.getString(1) + " " + c3.rs.getString(2);
                        textLines[i][1] = "";
                        textLines[i][2] = c3.rs.getString(3);
                        textLines[i][3] = "";
                        textLines[i][4] = "";
                        textLines[i][5] = "";
                        textLines[i][6] = "";
                        textLines[i][7] = "" + ret + dnum;
                        textLines[i][8] = "";
                        textLines[i][9] = "";
                        textLines[i][10] = "";
                        textLines[i][11] = "";
                        textLines[i][12] = "";
                        textLines[i][13] = "";
                        textLines[i][14] = "";
                        textLines[i][15] = "";
                        textLines[i][16] = "";
                        textLines[i][17] = "";
                        textLines[i][18] = "";
                        l++;
                    }
                }
            }

            c3.st.close();

            c3.con.close();
            i = 0;
            connect c2 = new connect();
            for (i = 0; i < x; i++) {
                if (asn[i] > 0) {
                    if (i % (linesPerPage / 2) > 2) {
                        c2.rs = c2.st.executeQuery("select endm, endy from payment where asn=" + asn[i]);
                        c2.rs.next();
                        textLines[i][3] += c2.rs.getInt(1) + "/" + c2.rs.getInt(2);
                    }
                }
            }

            c2.st.close();

            c2.con.close();
            i = 0;
            connect c4 = new connect();
            for (i = 0; i < x; i++) {
                if (asn[i] > 0) {
                    if (i % (linesPerPage / 2) > 2) {
                        c4.rs = c4.st.executeQuery("select fname, lname from subdetails where asn=" + asn[i]);
                        c4.rs.next();
                        String fname = c4.rs.getString(1);
                        String lname = c4.rs.getString(2);
                        if (fname == null) {
                            fname = "";
                        }
                        if (lname == null) {
                            lname = "";
                        }
                        textLines[i][1] += fname + " " + lname;
                    }
                }
            }

            c4.st.close();

            c4.con.close();
            i = 0;
        } catch (Exception e) {
            new except(e, this.getClass().toString());

        }

    }

    public int print(Graphics g, PageFormat pf, int pageIndex) {
        Font f1 = new Font("SERIF", Font.PLAIN, 8);
        FontMetrics metric = g.getFontMetrics(f1);
        int lineHeight = metric.getHeight();


        int a = metric.stringWidth(footer);


        if (pageBreak == null) {

            linesPerPage = (int) (pf.getImageableHeight() / lineHeight);
            initLines();

            int numBreaks = (numLines / linesPerPage);
            pageBreak =
                    new int[numBreaks];
            for (int b = 0; b <
                    numBreaks; b++) {
                pageBreak[b] = (b + 1) * linesPerPage;
            }

        }

        if (pageIndex > pageBreak.length) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        int y = lineHeight;

        g.setFont(new Font("SERIF", Font.PLAIN, 9));


        int start = 0;
        if (pageIndex == 0) {
            start = 0;
        } else {
            start = pageBreak[pageIndex - 1];
        }

        int end = 0;

        if (pageIndex == pageBreak.length) {
            end = numLines;
        } else {
            end = pageBreak[pageIndex];
        }
        if (chk % 2 == 1 && (end - start) != lineHeight) {

            for (int line = start; line < end && i < x; line += 2) {
                y += 10;
                if (line > start + 2) {
                    g.drawLine(4, y - lineHeight, 4, y + lineHeight);
                    g.drawLine(210, y - lineHeight, 210, y + lineHeight);				//vertical line after name field
                    g.drawLine(50, y - lineHeight, 50, y + lineHeight);				//vertical line after sub no
                    g.drawLine(237, y - lineHeight, 237, y + lineHeight);				//vertical line after rcpt no
                    g.drawLine(280, y - lineHeight, 280, y + lineHeight);				//vertical line after ending period


                    g.drawLine(310, y - lineHeight, 310, y + lineHeight);				//vertical line after amt
                    g.drawLine(340, y - lineHeight, 340, y + lineHeight);				//vertical line after new rcpt no
                    g.drawLine(385, y - lineHeight, 385, y + lineHeight);				//vertical line after new period


                    g.drawLine(403, y - lineHeight, 403, y + lineHeight);				//vertical line after 01
                    g.drawLine(421, y - lineHeight, 421, y + lineHeight);				//vertical line after 02
                    g.drawLine(439, y - lineHeight, 439, y + lineHeight);				//vertical line after 03
                    g.drawLine(457, y - lineHeight, 457, y + lineHeight);				//vertical line after 04
                    g.drawLine(475, y - lineHeight, 475, y + lineHeight);				//vertical line after 05
                    g.drawLine(493, y - lineHeight, 493, y + lineHeight);				//vertical line after 06
                    g.drawLine(511, y - lineHeight, 511, y + lineHeight);				//vertical line after 07
                    g.drawLine(529, y - lineHeight, 529, y + lineHeight);				//vertical line after 08
                    g.drawLine(547, y - lineHeight, 547, y + lineHeight);				//vertical line after 09
                    g.drawLine(565, y - lineHeight, 565, y + lineHeight);				//vertical line after 10
                    g.drawLine(582, y - lineHeight, 582, y + lineHeight);				//vertical line after 11
                    g.drawLine(600, y - lineHeight, 600, y + lineHeight);				//vertical line after 12
                //g.drawLine(618,y-lineHeight,618,y+lineHeight);			//vertical line after
                }

                //System.out.println(i);
                g.drawString("" + textLines[i][0], 5, y);
                g.drawString("" + textLines[i][1], 52, y);
                g.drawString("" + textLines[i][2], 212, y);
                g.drawString("" + textLines[i][3], 239, y);
                g.drawString("" + textLines[i][4], 282, y);
                g.drawString("" + textLines[i][5], 312, y);
                g.drawString("" + textLines[i][6], 342, y);
                g.drawString("" + textLines[i][7], 387, y);
                g.drawString("" + textLines[i][8], 405, y);
                g.drawString("" + textLines[i][9], 423, y);
                g.drawString("" + textLines[i][10], 441, y);
                g.drawString("" + textLines[i][11], 459, y);
                //50,210,237,280,310,340,385,403,421,439,457,475,493,511,529,547,565,582,600
                g.drawString("" + textLines[i][12], 477, y);
                g.drawString("" + textLines[i][13], 495, y);
                g.drawString("" + textLines[i][14], 513, y);
                g.drawString("" + textLines[i][15], 531, y);
                g.drawString("" + textLines[i][16], 549, y);
                g.drawString("" + textLines[i][17], 567, y);
                g.drawString("" + textLines[i][18], 584, y);
                if (line > start) {
                    g.drawLine(4, y + 8, 600, y + 8);									//horizontal line after every field
                }
                //g.drawString(textLines[i][1], 215, y);
                y += lineHeight;
                i++;

                if (line == end - 2) {
                    g.drawString("" + footer, (int) (pf.getWidth()) / 2 - (a / 2), y + 10);
                }

            }
        } else {

            for (int line = start; line < end && i < x; line += 2) {
            }

        }
        chk++;

        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean ok = job.printDialog();

            if (ok) {
                try {
                    job.print();
                } catch (PrinterException pe) {
                    System.out.println(pe);
                    new except(pe, this.getClass().toString());

                }

            }
        }
        if (ae.getSource() == back) {
            f.dispose();
            new despreg();
        }
    }
}