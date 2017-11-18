import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class SatSandeshMemberStatusDetailed_New extends JFrame implements ActionListener, Printable
{
    
    public static void main(String args[])
    {
        new SatSandeshMemberStatusDetailed_New(SamsUtilities.getCurrentMonth(),SamsUtilities.getCurrentYear(),"Hindi");
    }
    
    
    
    JLabel total, state, dist, status, period, hand, post, dno, total1, active, inactive, freeze, deactive, total2, hand1, post1, dno1, total11, life, comp;
    JLabel subscription_period, hand2, post2, dno2, total3, life1, cmp, norm, total4;
    JLabel head;
    
    Font f=new Font("ARIAL", Font.BOLD, 14);
    
    JLabel foot1, foot2, foot3, foot4, foot5, foot6, foot7, foot8;
    
    JFrame frameToPrint,j;
    
    JLabel statet[] =new JLabel[10];
    JLabel totalt;
    JLabel handt[] = new JLabel[10];
    JLabel postt[] = new JLabel[10];
    JLabel dnot[] = new JLabel[10];
    JLabel totalt1[] = new JLabel[10];
    JLabel activet[] = new JLabel[10];
    JLabel inactivet[] = new JLabel[10];
    JLabel freezet[] = new JLabel[10];
    JLabel deactivet[] = new JLabel[10];
    JLabel totalt2[] = new JLabel[10];
    //	JLabel handt1[] = new JLabel[10];
    JLabel handt1[] = new JLabel[10];
    //	JLabel handt1[] = new JLabel[10];
    JLabel postt1[] = new JLabel[10];
    JLabel dnot1[] = new JLabel[10];
    JLabel totalt11[] = new JLabel[10];
    JLabel lifet[] = new JLabel[10];
    JLabel compt[] = new JLabel[10];
    JLabel stopped_records;
    JButton back, print1;
    
    String state1[]=new String[10];
    
    int a1, a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13, a14;
    int l1, l2, l3, stopped_records_count;
    int t1, t2, t3, t4;
    
    public SatSandeshMemberStatusDetailed_New(int month, int year, String lang)
    {
        j=new JFrame();
        
        frameToPrint = j;
        try
        {
            j.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        }
        catch (Exception cnf)
        {
            System.out.println(cnf);
        }
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(false);
        j.setSize(1000,730);
        j.setFont(f);
        j.setLocation(10,10);
        j.setLayout(null);
        j.setTitle("Members Status for "+month+"-"+year);
        j.setBackground(Color.WHITE);
        Container con=j.getContentPane();
        con.setBackground(Color.white);
        
        
        state1[0]="DL";
        state1[1]="HAR";
        state1[2]="MAH";
        state1[3]="MP";
        state1[4]="PJB";
        state1[5]="RAJ";
        state1[6]="UK";
        state1[7]="UP";
        state1[8]="MS";
        state1[9]="Total";
        
        head=new JLabel("MEMBER STATUS FOR "+month+"-"+year);
        
        //total=new JLabel("Total Records");
        state=new JLabel("State");
        dist=new JLabel("DISTRIBUTION TYPE (Table 1)");
        status=new JLabel("STATUS WISE (Table 2)");
        period=new JLabel("PERIOD WISE (Table 3)");
        hand=new JLabel("By Hand");
        post=new JLabel("By Post");
        dno=new JLabel("Distributor");
        total1=new JLabel("Total");
        active=new JLabel("Active~");
        inactive=new JLabel("Inactive*");
        freeze=new JLabel("Freeze$");
        deactive=new JLabel("Deactive#");
        total2=new JLabel("Total");
        hand1=new JLabel("By Hand");
        post1=new JLabel("By Post");
        dno1=new JLabel("Distributor");
        total11=new JLabel("Total");
        
        j.add(head);
        head.setBounds(300,30,600,40);
        head.setFont(new Font("SERIF", Font.BOLD, 20));
        
        //add(total);
        //total.setBounds(400,30,300,20);
        
        j.add(state);
        state.setBounds(20,115,50,20);
        state.setFont(new Font("SERIF", Font.PLAIN, 20));
        
        j.add(dist);
        dist.setBounds(90,75,250,20);
        dist.setFont(new Font("SERIF", Font.BOLD, 14));
        
        j.add(status);
        status.setBounds(400,75,150,20);
        status.setFont(new Font("SERIF", Font.BOLD, 14));
        
        j.add(period);
        period.setBounds(680,75,200,20);
        period.setFont(new Font("SERIF", Font.BOLD, 14));
        
        j.add(hand);
        hand.setBounds(80,115,50,20);
        
        j.add(post);
        post.setBounds(140,115,50,20);
        
        j.add(dno);
        dno.setBounds(200,115,60,20);
        
        j.add(total1);
        total1.setBounds(270,115,50,20);
        
        j.add(active);
        active.setBounds(350,115,50,20);
        
        j.add(inactive);
        inactive.setBounds(410,115,50,20);
        
        j.add(deactive);
        deactive.setBounds(470,115,50,20);
        
        j.add(freeze);
        freeze.setBounds(530,115,50,20);
        
        
        j.add(total2);
        total2.setBounds(600,115,50,20);
        
        j.add(hand1);
        hand1.setBounds(680,115,40,20);
        
        j.add(post1);
        post1.setBounds(730,115,40,20);
        
        j.add(dno1);
        dno1.setBounds(780,115,60,20);
        
        j.add(total11);
        total11.setBounds(835,115,40,20);
        
        
        //------------------SUBSCRIPTION TYPE----------------------------//
        
        
        subscription_period =new JLabel("SUB TYPE");
        hand2 =new JLabel("By Hand");
        post2=new JLabel("By Post");
        dno2=new JLabel("Dist.");
        total3=new JLabel("Total");
        life1=new JLabel("Life");
        cmp=new JLabel("Comp");
        norm=new JLabel("Normal");
        total4=new JLabel("Total");
        
        j.add(subscription_period);
        subscription_period.setBounds(30,860,100,20);
        
        
        hand2.setBounds(140,860,60,20);
        j.add(hand2);
        
        post2.setBounds(210,860,60,20);
        j.add(post2);
        
        dno2.setBounds(280,860,60,20);
        j.add(dno2);
        
        total3.setBounds(350,860,60,20);
        j.add(total3);
        
        life1.setBounds(30,885,60,20);
        j.add(life1);
        
        cmp.setBounds(30,910,60,20);
        j.add(cmp);
        
        norm.setBounds(30,935,60,20);
        j.add(norm);
        
        total4.setBounds(30,960,60,20);
        j.add(total4);
        
        //-----------------------------------------------------------------------------------------------------
        
        String activeDate, inactiveStartDate, deactiveStartDate;
        activeDate = year+"-"+month+"-28";
        inactiveStartDate = activeDate;
        deactiveStartDate = activeDate;
        for(int i = 0 ; i < 10 ; i++)
        {
            
            statet[i] =new JLabel();
            handt[i] =new JLabel();
            postt[i] =new JLabel();
            dnot[i] =new JLabel();
            totalt1[i] =new JLabel();
            activet[i] =new JLabel();
            inactivet[i] =new JLabel();
            freezet[i] =new JLabel();
            deactivet[i] =new JLabel();
            totalt2[i] =new JLabel();
            handt1[i] =new JLabel();
            postt1[i] =new JLabel();
            dnot1[i] =new JLabel();
            totalt11[i] =new JLabel();
            
            j.add(statet[i]);
            statet[i].setBounds(20,155+(i*30),40,20);
            statet[i].setText(state1[i]);
            //statet[i].setEnabled(true);
            //statet[i].setFont(new Font("SERIF", Font.BOLD, 14));
            
            
            j.add(handt[i]);
            handt[i].setBounds(80,155+(i*30),40,20);
            handt[i].setEnabled(true);
            
            j.add(postt[i]);
            postt[i].setBounds(140,155+(i*30),40,20);
            postt[i].setEnabled(true);
            
            j.add(dnot[i]);
            dnot[i].setBounds(200,155+(i*30),50,20);
            dnot[i].setEnabled(true);
            
            
            j.add(totalt1[i]);
            totalt1[i].setBounds(270,155+(i*30),50,20);
            totalt1[i].setEnabled(true);
            
            j.add(activet[i]);
            activet[i].setBounds(350,155+(i*30),50,20);
            activet[i].setEnabled(true);
            
            j.add(inactivet[i]);
            inactivet[i].setBounds(410,155+(i*30),50,20);
            inactivet[i].setEnabled(true);
            
            j.add(deactivet[i]);
            deactivet[i].setBounds(470,155+(i*30),50,20);
            deactivet[i].setEnabled(true);
            
            j.add(freezet[i]);
            freezet[i].setBounds(530,155+(i*30),50,20);
            freezet[i].setEnabled(true);
            
            
            
            j.add(totalt2[i]);
            totalt2[i].setBounds(600,155+(i*30),50,20);
            totalt2[i].setEnabled(true);
            
            j.add(handt1[i]);
            handt1[i].setBounds(680,155+(i*30),40,20);
            handt1[i].setEnabled(true);
            
            j.add(postt1[i]);
            postt1[i].setBounds(730,155+(i*30),40,20);
            postt1[i].setEnabled(true);
            
            
            j.add(dnot1[i]);
            dnot1[i].setBounds(780,155+(i*30),40,20);
            dnot1[i].setEnabled(true);
            
            j.add(totalt11[i]);
            totalt11[i].setBounds(830,155+(i*30),40,20);
            totalt11[i].setEnabled(true);
            
            statet[i].setFont(f);
            handt[i].setFont(f);
            postt[i].setFont(f);
            dnot[i].setFont(f);
            totalt1[i].setFont(f);
            activet[i].setFont(f);
            inactivet[i].setFont(f);
            freezet[i].setFont(f);
            deactivet[i].setFont(f);
            totalt2[i].setFont(f);
            handt1[i].setFont(f);
            postt1[i].setFont(f);
            dnot1[i].setFont(f);
            totalt11[i].setFont(f);
            
            
            try
            {
                String type1, status1, duration1;
                a1=a2=a3=a4=a5=a6=a7=a8=a9=a10=a11=a12=a13=a14=0;
                connect c13=new connect();
                
                String query = "select count(asn) from subscribers_primary_details where "
                        + "language='"+lang+"' and state='"+state1[i]+"' and distribution_type='By Hand'";
                c13.rs=c13.st.executeQuery(query);
                if(c13.rs.next())
                {
                    a1 = c13.rs.getInt(1);
                    //System.out.println("a1 "+a1);
                }
                //System.out.println(query);
                
                query = "select count(asn) from subscribers_primary_details where "
                        + "language='"+lang+"' and state='"+state1[i]+"' and distribution_type='By Post'";
                c13.rs=c13.st.executeQuery(query);
                if(c13.rs.next())
                {
                    a2 = c13.rs.getInt(1);
                }
                //System.out.println(query);
                
                query = "select count(asn) from subscribers_primary_details where "
                        + "language='"+lang+"' and state='"+state1[i]+"' and distribution_type='Distributor'";
                c13.rs=c13.st.executeQuery(query);
                if(c13.rs.next())
                {
                    a3 = c13.rs.getInt(1);
                }
                //System.out.println(query);
                c13.st.close();
                c13.con.close();
                
                connect c1=new connect();
                l1=a1+a2+a3;
                t1+=t1;
                
                Date referenceDate = new Date((year-1900),month-1,28);
                
                
                
                {
                    Calendar c = Calendar.getInstance();
                    c.setTime(referenceDate);
                    c.add(Calendar.MONTH, -1);
                    //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                    activeDate = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
                    c.add(Calendar.MONTH, -6);
                    //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                    inactiveStartDate = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
                    c.add(Calendar.MONTH, -6);
                    //System.out.println(c.get(Calendar.DATE) +" - " + (c.get(Calendar.MONTH)+1) + " - " + c.get(Calendar.YEAR));
                    deactiveStartDate = c.get(Calendar.YEAR) +"-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
                }
                
                //System.out.println(deactiveStartDate +" <--> " + inactiveStartDate + " <--> " + activeDate );
                
                //query = "select count(asn) from subscribers_primary_details where language ='"+lang+"' and state='"+state1[i]+"' and membership_status='Active'";
                query = "select count(asn) from subscribers_primary_details where "
                        + "language ='"+lang+"' and state='"+state1[i]+"' and "
                        + "ending_period > '" + activeDate + "' and "
                        + "membership_status not in ('STOPPED')";
                //System.out.println(query);
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a4 = c1.rs.getInt(1);
                }
                
                //query = "select count(asn) from subscribers_primary_details where language ='"+lang+"' and state='"+state1[i]+"' and membership_status='Inactive' and membership_status not in ('STOPPED')";
                query = "select count(asn) from subscribers_primary_details where "
                        + "language ='"+lang+"' and state='"+state1[i]+"' and "
                        + "ending_period > '" + inactiveStartDate + "' and "
                        + "ending_period <= '" + activeDate + "'  and "
                        + "membership_status not in ('STOPPED')";
                //System.out.println(query);
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a5 = c1.rs.getInt(1);
                }
                
                //query = "select count(asn) from subscribers_primary_details where language ='"+lang+"' and state='"+state1[i]+"' and membership_status='Freeze' and membership_status not in ('STOPPED')";
                
                //System.out.println(query);
                query = "select count(asn) from subscribers_primary_details where "
                        + "language ='"+lang+"' and state='"+state1[i]+"' and "
                        + "ending_period <= '" + deactiveStartDate + "'  and "
                        + "membership_status not in ('STOPPED')";
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a6 = c1.rs.getInt(1);
                }
                
                //query = "select count(asn) from subscribers_primary_details where language ='"+lang+"' and state='"+state1[i]+"' and membership_status='Deactive' and membership_status not in ('STOPPED')";
                query = "select count(asn) from subscribers_primary_details where "
                        + "language ='"+lang+"' and state='"+state1[i]+"' and "
                        + "ending_period > '" + deactiveStartDate + "' and "
                        + "ending_period <= '" + inactiveStartDate + "' and "
                        + "membership_status not in ('STOPPED')";
                //System.out.println(query);
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a7 = c1.rs.getInt(1);
                }
                
                
                
                l2=a4+a5+a6+a7;
                
                query = "select count(asn) from subscribers_primary_details where language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED') and  state='"+state1[i]+"' and distribution_type='By Hand'";
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a8=c1.rs.getInt(1);
                }
                
                query = "select count(asn) from subscribers_primary_details where language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED') and  state='"+state1[i]+"' and distribution_type='By Post'";
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a9=c1.rs.getInt(1);
                }
                
                //c1.rs=c1.st.executeQuery("select count(asn) from subscribers_primary_details b, payment p where language='"+lang+"' and membership_status='Active' and state='"+state1[i]+"' and distribution_type='Distributor'");
                query = "select count(asn) from subscribers_primary_details where language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED') and  state='"+state1[i]+"' and distribution_type='Distributor'";
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a10=c1.rs.getInt(1);
                }
                
                query = "select count(asn) from subscribers_primary_details where language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED')";
                c1.rs=c1.st.executeQuery(query);
                if(c1.rs.next())
                {
                    a11=c1.rs.getInt(1);
                }
                
                c1.st.close();
                c1.con.close();
            }
            catch(Exception e)
            {
                
                System.out.println("error "+e);
                e.printStackTrace();
            }
            handt[i].setText(""+a1);
            postt[i].setText(""+a2);
            dnot[i].setText(""+a3);
            totalt1[i].setText(""+l1);
            activet[i].setText(""+a4);
            inactivet[i].setText(""+a5);
            freezet[i].setText(""+a6);
            deactivet[i].setText(""+a7);
            totalt2[i].setText(""+l2);
            handt1[i].setText(""+a8);
            postt1[i].setText(""+(a9));
            dnot1[i].setText(""+(a10));
            totalt11[i].setText(""+(a8+a9+a10));
        }
        
        a1=a2=a3=a4=a5=a6=a7=a8=a9=a10=0;
        l2=l3=0;
        
        
        
        
        try
        {
            
            
            connect c2=new connect();
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED')  and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and distribution_type='By Hand'");
            if(c2.rs.next())
            {
                a1=c2.rs.getInt(1);
            }
            
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED') and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and distribution_type='By Post'");
            if(c2.rs.next())
            {
                a2=c2.rs.getInt(1);
            }
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where language='"+lang+"' and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED') and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and distribution_type='Distributor'");
            if(c2.rs.next())
            {
                a3=c2.rs.getInt(1);
            }
            
            //----------------------------active inactive MS----------------------------------//
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and ending_period > '" + activeDate + "' and membership_status not in ('STOPPED')");
            if(c2.rs.next())
            {
                a4=c2.rs.getInt(1);
            }
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and ending_period > '" + inactiveStartDate + "' and ending_period <= '" + activeDate + "' and membership_status not in ('STOPPED') ");
            if(c2.rs.next())
            {
                a5=c2.rs.getInt(1);
            }
            
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and ending_period <= '" + deactiveStartDate + "' and membership_status not in ('STOPPED') ");
            if(c2.rs.next())
            {
                a6=c2.rs.getInt(1);
            }
            
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and ending_period > '" + deactiveStartDate + "' and ending_period <= '" + inactiveStartDate + "' and membership_status not in ('STOPPED') ");
            if(c2.rs.next())
            {
                a7=c2.rs.getInt(1);
            }
            
            
            
            l2=a4+a5+a6+a7;
            
            
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and (state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and distribution_type='By Hand')");
            if(c2.rs.next())
            {
                a8=c2.rs.getInt(1);
            }
            
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and (state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and distribution_type='By Post')");
            if(c2.rs.next())
            {
                a9=c2.rs.getInt(1);
            }
            
            c2.rs=c2.st.executeQuery("select count(asn) from subscribers_primary_details where  language='"+lang+"' and (state not in ('DL','HAR','MAH','MP','PJB','RAJ','UK','UP') and distribution_type='Distributor')");
            if(c2.rs.next())
            {
                a10=c2.rs.getInt(1);
            }
            l3=a8+a9+a10;
        }
        catch(Exception e1)
        {
            System.out.println("e1 :"+e1);
        }
        
        activet[8].setText(""+a4);
        inactivet[8].setText(""+a5);
        freezet[8].setText(""+a6);
        deactivet[8].setText(""+a7);
        totalt2[8].setText(""+l2);
        
        handt1[8].setText(""+a1);
        postt1[8].setText(""+a2);
        dnot1[8].setText(""+a3);
        l1=0;
        l1=a1+a2+a3;
        totalt11[8].setText(""+l1);
        
        
        handt[8].setText(""+a8);
        postt[8].setText(""+a9);
        dnot[8].setText(""+a10);
        totalt1[8].setText(""+l3);
        
        
        
        for(int s=0;s<9;s++)
        {
            t1+=Integer.parseInt(handt1[s].getText());
            t2+=Integer.parseInt(postt1[s].getText());
            t3+=Integer.parseInt(dnot1[s].getText());
            t4+=Integer.parseInt(totalt11[s].getText());
        }
        
        handt1[9].setText(""+t1);
        postt1[9].setText(""+t2);
        dnot1[9].setText(""+t3);
        
        totalt11[9].setText(""+t4);
        
        t1=t2=t3=t4=0;
        int t5=0;
        
        for(int s=0;s<9;s++)
        {
            t1+=Integer.parseInt(activet[s].getText());
            t2+=Integer.parseInt(inactivet[s].getText());
            t3+=Integer.parseInt(freezet[s].getText());
            t4+=Integer.parseInt(deactivet[s].getText());
            t5+=Integer.parseInt(totalt2[s].getText());
        }
        
        activet[9].setText(""+t1);
        inactivet[9].setText(""+t2);
        freezet[9].setText(""+t3);
        deactivet[9].setText(""+t4);
        totalt2[9].setText(""+t5);
        
        t1=t2=t3=t4=0;
        t5=0;
        
        for(int s=0;s<9;s++)
        {
            t1+=Integer.parseInt(handt[s].getText());
            t2+=Integer.parseInt(postt[s].getText());
            t3+=Integer.parseInt(dnot[s].getText());
            t4+=Integer.parseInt(totalt1[s].getText());
        }
        
        handt[9].setText(""+t1);
        postt[9].setText(""+t2);
        dnot[9].setText(""+t3);
        
        totalt1[9].setText(""+t4);
        
        
        //totalt.setBounds(500,30,200,20);
        //j.add(totalt);
        
        //----------------------SUB TYPE---------------------------------//
        
        int z1, z2, z3, z4, z5, z6, z7, z8, z9;
        z1=z2=z3=z4=z5=z6=z7=z8=z9=0;
        
        try
        {
            connect c4=new connect();
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period='Life' and language='"+lang+"' and distribution_type='By Hand'");
            if(c4.rs.next())
            {
                z1=c4.rs.getInt(1);
            }
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period='Life' and language ='"+lang+"' and distribution_type ='By Post'");
            if(c4.rs.next())
            {
                z2=c4.rs.getInt(1);
            }
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period='Life' and language ='"+lang+"' and distribution_type ='Distributor'");
            if(c4.rs.next())
            {
                z3=c4.rs.getInt(1);
            }
            
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period='Comp' and language ='"+lang+"' and distribution_type ='By Hand'");
            if(c4.rs.next())
            {
                z4=c4.rs.getInt(1);
            }
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period='Comp' and language ='"+lang+"' and distribution_type ='By Post'");
            if(c4.rs.next())
            {
                z5=c4.rs.getInt(1);
            }
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period='Comp' and language ='"+lang+"' and distribution_type ='Distributor'");
            if(c4.rs.next())
            {
                z6=c4.rs.getInt(1);
            }
            
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period not in ('Life','Comp') and language ='"+lang+"' and distribution_type ='By Hand'");
            if(c4.rs.next())
            {
                z7=c4.rs.getInt(1);
            }
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period not in ('Life','Comp') and language ='"+lang+"' and distribution_type ='By Post'");
            if(c4.rs.next())
            {
                z8=c4.rs.getInt(1);
            }
            
            c4.rs=c4.st.executeQuery("select count(asn) from subscribers_primary_details where subscription_period not in ('Life','Comp') and language ='"+lang+"' and distribution_type ='Distributor'");
            if(c4.rs.next())
            {
                z9=c4.rs.getInt(1);
                
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        
        
        
        try
        {
            connect stopped=new connect();
            stopped.rs=stopped.st.executeQuery("select count(asn) from subscribers_primary_details where membership_status='STOPPED' and language ='"+lang+"' ");
            stopped.rs.next();
            stopped_records_count=stopped.rs.getInt(1);
            stopped.rs.close();
            stopped.st.close();
            stopped.con.close();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        
        stopped_records=new JLabel("Number Of Stopped Records - "+stopped_records_count);
        stopped_records.setFont(new Font("SERIF", Font.BOLD, 18));
        j.add(stopped_records);
        stopped_records.setBounds(290, 470, 680, 25);
        
        
        
        //--------------------|||||||||||||------------------------------//
        
        
        
        
        foot1= new JLabel(">> Table 1 shows the total no. of members in each state & with mode of Receiving Sat Sandesh selected by them.");
        j.add(foot1);
        foot1.setBounds(40,500,680,15);
        
        foot2= new JLabel(">> Table 2 shows the total no. of members in each state according to their ending period i.e.");
        j.add(foot2);
        foot2.setBounds(40,520,680,15);
        
        foot3= new JLabel("     ~ Active column shows the no. of members having ending period of current calendar month or future months to come");
        j.add(foot3);
        foot3.setBounds(40,540,680,15);
        
        foot4= new JLabel("     * Inactive column shows no. of members whose period has expired during the last 6 months from the current calendar month");
        j.add(foot4);
        foot4.setBounds(40,560,680,15);
        
        foot5= new JLabel("     # De active column shows no. of records whose period has expired during the last 6 months from the last month of inactive");
        j.add(foot5);
        foot5.setBounds(40,580,680,15);
        
        foot6= new JLabel("     $ Freeze column shows the remaining no. of records in the database");
        j.add(foot6);
        foot6.setBounds(40,600,680,15);
        
        foot7= new JLabel(">> Table 3 shows the total no. of members in each state & with mode of Receiving Sat Sandesh selected by them having ending period of current calendar month or future months to come.");
        j.add(foot7);
        foot7.setBounds(40,620,920,15);
        
        back=new JButton("Back");
        back.setBounds(460, 660, 100,25);
        j.add(back);
        back.setMnemonic('B');
        
        back.addActionListener(this);
        
        
        
        print1=new JButton("Print");
        print1.setBounds(340, 660, 100,25);
        j.add(print1);
        print1.setMnemonic('P');
        print1.addActionListener(this);
        
        j.setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            new memstatus();
            j.dispose();
        }
        
        if(ae.getSource()==print1)
        {
            
            
            
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            
            
            int res = JOptionPane.showConfirmDialog(null,"You want to configure your print ","** PRINTING **", JOptionPane.YES_NO_OPTION);
            if( res == JOptionPane.YES_OPTION ) {
                //if (res == JOptionPane.YES_OPTION) (
                //	PageFormat format = job.pageDialog(job.defaultPage());
                PageFormat format = job.pageDialog (job.defaultPage ());
            } //)
            if( res == JOptionPane.NO_OPTION ) {
                PageFormat format =new PageFormat();
                format.setOrientation(PageFormat.LANDSCAPE);
            }
            
            
            boolean ok = job.printDialog();
            if (ok)
            {
                try
                {
                    job.print();
                }
                catch (PrinterException ex)
                {
                    ex.printStackTrace();
                    /* The job did not successfully complete */
                }
            }
            
        }
        
        
        
    }
    
    
    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {
        
        pf.setOrientation(PageFormat.LANDSCAPE);
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setOrientation(PageFormat.LANDSCAPE);
        
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        
        /* User (0,0) is typically outside the imageable area, so we must
        * translate by the X and Y values in the PageFormat to avoid clipping
        */
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        
        g2d.scale(pf.getImageableWidth()/frameToPrint.getWidth(), pf.getImageableHeight()/frameToPrint.getHeight());
        
        /* Now print the window and its visible contents */
        frameToPrint.printAll(g);
        
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
    
    
}