
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanjay
 */
public class petty extends JFrame implements ActionListener
{
    public static void main(String arg[])
    {
        new petty();
    }
    JLabel dat, count, amnt;
    JTextField datt1,datt2,datt3, amt;
    JComboBox countt;
    JButton back,ok;

    public petty()
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
        setTitle("Petty Sales");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setLocation(30,30);
        setSize(300,300);

        dat=new JLabel("Date");
        amnt=new JLabel("Amount");
        count=new JLabel("Counter");

        datt1=new JTextField(20);
        datt2=new JTextField(20);
        datt3=new JTextField(20);
        amt=new JTextField(20);

        countt=new JComboBox();
        countt.addItem("Kirpal Bagh");
		countt.addItem("Kirpal Ashram");
		countt.addItem("Sawan Ashram");
		countt.addItem("Dharshan Dham");
		countt.addItem("Tours/Function");

        ok=new JButton("OK");
        ok.setMnemonic('O');
        ok.addActionListener(this);

        back=new JButton("Back");
        back.setMnemonic('B');
        back.addActionListener(this);


        dat.setBounds(30,30,50,20);
        amnt.setBounds(30,60,50,20);
        count.setBounds(30,90,50,20);
        datt1.setBounds(100,30,30,20);
        datt2.setBounds(140,30,30,20);
        datt3.setBounds(180,30,30,20);
        amt.setBounds(100,60,50,20);
        countt.setBounds(100,90,100,20);
        ok.setBounds(50,120,70,25);
        back.setBounds(140,120,70,25);


        add(dat);
        add(amnt);
        add(count);
        add(datt1);
        add(datt2);
        add(datt3);
        add(amt);
        add(countt);
        add(back);
        add(ok);

        setVisible(true);


    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
        {
            try
            {
                connect c1=new connect();
                c1.a=c1.st.executeUpdate("insert into petty values( " + Integer.parseInt(datt1.getText()) + " , "+Integer.parseInt(datt2.getText())+","+Integer.parseInt(datt3.getText())+ " , "+Integer.parseInt(amt.getText())+",'"+(String)(countt.getSelectedItem())+"' )");
                if(c1.a==1)
                {
                    JOptionPane.showMessageDialog(null,"Added Succesfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    datt1.setText("");
                    datt2.setText("");
                    datt3.setText("");
                    amt.setText("");
                    countt.setSelectedIndex(1);

                }
            }
            catch(Exception ex)
            {
                new except(ex, this.getClass().toString());
            }
        }
        if(e.getSource()==back)
        {
            new sams();
            this.dispose();

        }
    }


}
