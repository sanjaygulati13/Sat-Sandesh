import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanjay
 */
public class Preview extends JFrame implements ActionListener
{
    //Graphics graph;
    public static void main(String args[])
    {
        
        //graph.drawLine(5, 10, 100, 150);
      //  new Preview(graph);
      //  new Preview();
    }
    Graphics2D g1;

    protected void center() 
    {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width-300,screenSize.height-300);
        Dimension frameSize = getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        
        setLocation(x, y);
    }

    public Preview(Graphics g)
    //public Preview()
    {

        //g1=(Component)g;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.center();
        Container con=this.getContentPane(); // inherit main frame
        con.setBackground(Color.white);        // paint background
        JPanel pane=new JPanel();     // create drawing canvas
        con.add(pane);

        Rectangle w1 = g.getClipBounds();
        int w= (int)w1.getWidth();
        int h = (int)w1.getHeight();
        int type = BufferedImage.TYPE_INT_RGB;  // see api for options
        BufferedImage bi = new BufferedImage(w, h, type);
        //Graphics2D g2 = bi.createGraphics();
        Graphics2D g2=(Graphics2D)g;
        paint(g2);
        g2.dispose();
        String ext = "jpg";
        File file = new File("c:/pseudo." + ext);
        try
        {
            ImageIO.write(bi, ext, file);
        }
        catch(IOException ioe) { 
            System.out.println(ioe);
            ioe.printStackTrace();
        }

        //Image page = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_ARGB);
        //Graphics g1 = page.getGraphics();

        setVisible(true);
        //pane.add(g1);





    }
    public void initPane()
    {

    }

    public void actionPerformed(ActionEvent ae)
    {

    }
}

