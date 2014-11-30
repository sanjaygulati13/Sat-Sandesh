
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class renew extends JFrame implements ActionListener {

    public static void main(String args[]) {
        new renew();
    }
    JButton ok, back;
    JLabel subno;
    JComboBox subt1;
    TextFieldWithLimit subt2;

    protected void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);
    }

    public renew() {

        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            System.out.println(cnf);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        setLayout(null);
        setLocation(10, 10);
        setSize(350, 200);
        setTitle("Renew-SAMS");

        center();
        subno = new JLabel("Sub No.");
        add(subno);
        subno.setBounds(50, 30, 80, 25);

        subt1 = new JComboBox();
        subt1.setBounds(130, 30, 50, 25);
        add(subt1);

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

        subt2 = new TextFieldWithLimit(5, 5);
        //subt2.setSelectionStart(0);
        subt2.setBounds(190, 30, 60, 20);
        add(subt2);

        ok = new JButton("Renew");
        ok.addActionListener(this);
        ok.setBounds(50, 110, 100, 25);
        ok.setMnemonic('R');
        add(ok);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(180, 110, 100, 25);
        back.setMnemonic('B');
        add(back);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ok) {
            String stat = (String) subt1.getSelectedItem();
            int sub = Integer.parseInt(subt2.getText());
            //System.out.println(stat+sub);
            new renew1(stat, sub);
            this.dispose();
        }

        if (ae.getSource() == back) {
            new sams();
            this.dispose();

        }

    }
}
