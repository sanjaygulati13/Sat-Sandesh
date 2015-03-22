/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanjay
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class sams extends JFrame implements ActionListener {

    public static void main(String args[]) {
        sams sams = new sams();
    }
     protected final void center() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);
    }

    //ImageIcon img;
    JMenuBar mbar;
    JMenu subscription_menu, dis, mis, search, lab, month, rem, mem, inventory_management_menu;
    JMenuItem new1, renew, des, rembulk, remsub, cross, memd, mems, bulk, subno, all, dno, remstatus, dstatus, left;
    
    //miscellaneous
    JMenu recdel, oth, despatch, freeze, inactive, deactive, account_book_details_menu, petty_menu;
    JMenuItem rcptdet, accnt, accntsum,accnt_monthly, accnt_yearly, despdet, moddesp, retb,retl, freezed, freezes, deactived, deactives, inactived, inactives, state, district,subl, dnol, pet,petty_month, petty_year, dup;
    JMenuItem ind, retb1, SupplementaryIndex;
    //search
    JMenuItem searchsubno, searchrcptno, searchname, searchaddr;
    JButton newb, renewb, memstatusb, reminderb, labelsubb, labeldistb, indexb, backupb;
    Font f = new Font("ARIAL", Font.BOLD, 14);
    JLabel l1, l2;
    //inventory management
    JMenu receipt_book_menu, sat_sandesh_menu, receipt_issue_details_menu, receipt_book_reporting_menu;
    JMenuItem receipt_add_new_series_menu_item, issue_new_receipt_book_menu_item, issue_reissue_menu_item, issue_revert_menu_item;
    JMenuItem issue_receipt_book_status, issue_series_issue_details;
    
    JMenu add_inventory_menu, issue_inventory_menu, report_inventory_menu;
    JMenuItem new_inventory_menu_item, sub_issue_inventory_menu_item;
    JMenuItem distribute_inventory_menu_item, bind_inventory_menu_item;
    JMenuItem summary_inventory_report_menu_item, detailed_inventory_report_menu_item, distribution_inventory_report_menu_item, view_page_number_inventory_report ;
    
    
    public sams() {


        try {
            //For Mac
            //Application.getApplication().setDockIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            
            //For windows
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg")));
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); // Use the native L&F
        } catch (Exception cnf) {
            except except = new except(cnf, this.getClass().toString());
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 600);
        setLocation(10, 10);
        setLayout(null);
        setResizable(false);
        

        setTitle("Sat Sandesh");
        center();

        Container con = getContentPane();
        //con.setBackground(Color.cyan);

        mbar = new JMenuBar();
        this.setJMenuBar(mbar);
        subscription_menu = new JMenu("Subscription");								//subscription
        subscription_menu.setMnemonic('s');
        dis = new JMenu("Display/Print");								//display/print
        dis.setMnemonic('d');
        mis = new JMenu("Miscellaneous");								//miscellaneous
        mis.setMnemonic('m');
        
        inventory_management_menu = new JMenu("Inventory Management");
        inventory_management_menu.setMnemonic('i');
        
        search = new JMenu("Search");									//search
        search.setMnemonic('e');
        lab = new JMenu("Labels");									//labels--d/p
        lab.setMnemonic('l');
        new1 = new JMenuItem("New");									//new--subscription
        new1.setMnemonic('n');
        renew = new JMenuItem("Renew");								//renew--subscription
        renew.setMnemonic('r');
        ind = new JMenuItem("Index Register");						//index--d/p
        ind.setMnemonic('i');
        
        SupplementaryIndex = new JMenuItem("Suppl. index Register");
        SupplementaryIndex.setMnemonic('S');
        
        des = new JMenuItem("Despatch register");						//despatch--d/p
        des.setMnemonic('p');
        rem = new JMenu("Reminders");									//reminders--d/p
        rem.setMnemonic('r');
        cross = new JMenuItem("Cross Checking Reports");				//cross check reports--d/p
        cross.setMnemonic('c');
        mem = new JMenu("Member Status");							//member status--d/p
        mem.setMnemonic('m');

        memd = new JMenuItem("Detailed");
        memd.setMnemonic('D');
        mems = new JMenuItem("Summary");
        mems.setMnemonic('S');

        left = new JMenuItem("Leftouts");
        left.setMnemonic('L');
        remstatus = new JMenuItem("Reminder Status");							//member status--d/p
        remstatus.setMnemonic('r');

        dstatus = new JMenuItem("Distributor Status");							//member status--d/p
        dstatus.setMnemonic('d');


        month = new JMenu("Monthly Labels");							//monthly labels--d/p--labels
        month.setMnemonic('o');
        bulk = new JMenuItem("Bulk Address");							//bulk address--d/p--labels
        bulk.setMnemonic('b');
        all = new JMenuItem("Print all Distributor Details ");		//print all dno--d/p--labels
        all.setMnemonic('A');
        subno = new JMenuItem("Sub No.");								//by sub no--d/p--labels--monthly
        subno.setMnemonic('u');
        dno = new JMenuItem("Distributor");							//by distributor--d/p--labels--monthly
        dno.setMnemonic('t');
        rembulk = new JMenuItem("Bulk Reminders");					//by D#--d/p--reminders
        rembulk.setMnemonic('b');
        remsub = new JMenuItem("Sub No Reminders");					//by sub no--d/p--reminders
        remsub.setMnemonic('S');

        recdel = new JMenu("Record Deletion");						//record deletion--miscellaneous
        recdel.setMnemonic('e');

        oth = new JMenu("Others");									//other--miscellaneous
        oth.setMnemonic('O');

        despatch = new JMenu("Despatch");								//despatch--miscellaneous
        despatch.setMnemonic('D');

        state = new JMenuItem("State List");							//misclellaneous--others--state list
        state.setMnemonic('S');

        subl = new JMenuItem("Sub No List");							//misclellaneous--others--sub list
        subl.setMnemonic('u');

        dnol = new JMenuItem("D# List");							//misclellaneous--others--dno list
        dnol.setMnemonic('L');


        district = new JMenuItem("District list");					//misclellaneous--others--district list
        district.setMnemonic('D');


        rcptdet = new JMenuItem("Receipt Book details");				//Receipt Book details--misclellaneous
        rcptdet.setMnemonic('R');

        dup=new JMenuItem("Duplicate SUB No");
        dup.setMnemonic('D');
        dup.addActionListener(this);

        petty_menu=new JMenu("Petty Sales");
        petty_menu.setMnemonic('P');

        pet=new JMenuItem("Add Record");
        pet.setMnemonic('a');
        pet.addActionListener(this);

        petty_month=new JMenuItem("Monthly Report");
        petty_month.setMnemonic('M');
        petty_month.addActionListener(this);

        petty_year=new JMenuItem("Yearly Report");
        petty_year.setMnemonic('Y');
        petty_year.addActionListener(this);

        account_book_details_menu = new JMenu("Account Book");						//account book--misclellaneous
        account_book_details_menu.setMnemonic('A');

        accnt = new JMenuItem("Detailed");
        accnt.setMnemonic('D');

        accntsum = new JMenuItem("Summary");
        accntsum.setMnemonic('S');

        accnt_monthly = new JMenuItem("Monthly Report");
        accnt_monthly.setMnemonic('M');

        accnt_yearly = new JMenuItem("Yearly Report");
        accnt_yearly.setMnemonic('Y');


        despdet = new JMenuItem("Add D# Details");				//despatch code details--misclellaneous
        despdet.setMnemonic('D');

        moddesp = new JMenuItem("Modify D# Details");
        moddesp.setMnemonic('M');

        retb1=new JMenu("Return Back");
        retb1.setMnemonic('R');

        retb = new JMenuItem("Mark Return Back");						//mark return back--misclellaneous--record deletion
        retb.setMnemonic('M');

        retl=new JMenuItem("Return Back list");
        retl.setMnemonic('l');

        /*del=new JMenuItem("Delete");									//delete--misclellaneous--record deletion
        del.setMnemonic('e');
         */

        freeze = new JMenu("Freeze Records List");
        freeze.setMnemonic('f');

        deactive = new JMenu("Deactive Records List");
        deactive.setMnemonic('d');

        inactive = new JMenu("Inactive Records List");
        inactive.setMnemonic('i');


        freezed = new JMenuItem("Detailed");
        freezed.setMnemonic('D');

        freezes = new JMenuItem("Summary");
        freezes.setMnemonic('S');


        deactived = new JMenuItem("Detailed");
        deactived.setMnemonic('D');

        deactives = new JMenuItem("Summary");
        deactives.setMnemonic('S');


        inactived = new JMenuItem("Detailed");
        inactived.setMnemonic('D');

        inactives = new JMenuItem("Summary");
        inactives.setMnemonic('S');

        /* Receipt Book Menu under Inventory Management Menu*/
        receipt_book_menu = new JMenu("Receipt Book");
        receipt_book_menu.setMnemonic('r');
        
        receipt_add_new_series_menu_item = new JMenuItem("Add Series");
        receipt_add_new_series_menu_item.setMnemonic('a');
        receipt_add_new_series_menu_item.addActionListener(this);
        
        receipt_issue_details_menu = new JMenu("Issue Receipt Book");
        receipt_issue_details_menu.setMnemonic('i');
        
        receipt_book_reporting_menu = new JMenu("Report");
        receipt_book_reporting_menu.setMnemonic('r');
        
        issue_new_receipt_book_menu_item = new JMenuItem("New Receipt Book");
        issue_new_receipt_book_menu_item.setMnemonic('n');
        issue_new_receipt_book_menu_item.addActionListener(this);
        
        issue_receipt_book_status = new JMenuItem("Receipt Book Status");
        issue_receipt_book_status.setMnemonic('r');
        issue_receipt_book_status.addActionListener(this);
        
        issue_reissue_menu_item = new JMenuItem("Re-Issue");
        issue_reissue_menu_item.setMnemonic('r');
        issue_reissue_menu_item.addActionListener(this);
        
        issue_revert_menu_item = new JMenuItem("Revert Back");
        issue_revert_menu_item.setMnemonic('b');
        issue_revert_menu_item.addActionListener(this);
        
        
        
        issue_series_issue_details = new JMenuItem("Series Issue Details");
        issue_series_issue_details.setMnemonic('s');
        issue_series_issue_details.addActionListener(this);
        
        /*Sat Sandesh Menu under Inventory management Menu*/
        sat_sandesh_menu = new JMenu("Sat Sandesh");
        sat_sandesh_menu.setMnemonic('s');
        
        
        add_inventory_menu  = new JMenu("Add Inventory");
        add_inventory_menu.setMnemonic('a');
        
        issue_inventory_menu = new JMenu("Issue Inventory");
        issue_inventory_menu.setMnemonic('i');
        
        report_inventory_menu = new JMenu("Report");
        report_inventory_menu.setMnemonic('r');
        
        new_inventory_menu_item = new JMenuItem("New");
        new_inventory_menu_item.setMnemonic('S');
        new_inventory_menu_item.addActionListener(this);
        
        sub_issue_inventory_menu_item = new JMenuItem("Sub Issue");
        sub_issue_inventory_menu_item.setMnemonic('S');
        sub_issue_inventory_menu_item.addActionListener(this);
        
        distribute_inventory_menu_item = new JMenuItem("Distribution");
        distribute_inventory_menu_item.setMnemonic('S');
        distribute_inventory_menu_item.addActionListener(this);
        
        bind_inventory_menu_item = new JMenuItem("Binding");
        bind_inventory_menu_item.setMnemonic('S');
        bind_inventory_menu_item.addActionListener(this);
        
        summary_inventory_report_menu_item = new JMenuItem("Summary");
        summary_inventory_report_menu_item.setMnemonic('S');
        summary_inventory_report_menu_item.addActionListener(this);
        
        detailed_inventory_report_menu_item = new JMenuItem("Detailed");
        detailed_inventory_report_menu_item.setMnemonic('D');
        detailed_inventory_report_menu_item.addActionListener(this);
        
        distribution_inventory_report_menu_item = new JMenuItem("Distribution");
        distribution_inventory_report_menu_item.setMnemonic('r');
        distribution_inventory_report_menu_item.addActionListener(this);    
        
        
        view_page_number_inventory_report = new JMenuItem("View Page Details");
        view_page_number_inventory_report.setMnemonic('p');
        view_page_number_inventory_report.addActionListener(this);    
                
        

        searchsubno = new JMenuItem("Search By Sub No");				// search options
        searchsubno.setMnemonic('S');
        searchsubno.addActionListener(this);

        searchrcptno = new JMenuItem("Search By rcpt No");
        searchrcptno.setMnemonic('R');
        searchrcptno.addActionListener(this);

        searchname = new JMenuItem("Search By Name");
        searchname.setMnemonic('N');
        searchname.addActionListener(this);


        searchaddr = new JMenuItem("Search By Address");
        searchaddr.setMnemonic('A');
        searchaddr.addActionListener(this);



        newb = new JButton("New");								//new button
        newb.setFont(f);
        newb.setMnemonic('N');
        newb.setBounds(50, 50, 150, 50);
        add(newb);
        newb.addActionListener(this);

        renewb = new JButton("Renew");							//renew button
        renewb.setMnemonic('R');
        renewb.setBounds(300, 50, 150, 50);
        add(renewb);
        renewb.setFont(f);
        renewb.addActionListener(this);


        memstatusb = new JButton("Member Status");				//mem status button
        memstatusb.setMnemonic('T');
        memstatusb.setBounds(550, 50, 150, 50);
        add(memstatusb);
        memstatusb.setFont(f);
        memstatusb.addActionListener(this);


        reminderb = new JButton("Reminders");					//reminder button
        reminderb.setMnemonic('E');
        reminderb.setFont(f);
        reminderb.setBounds(800, 50, 150, 50);
        add(reminderb);
        reminderb.addActionListener(this);


        labelsubb = new JButton("Label Sub No");					//label sub no button
        labelsubb.setMnemonic('E');
        labelsubb.setFont(f);
        labelsubb.setBounds(50, 125, 150, 50);
        add(labelsubb);
        labelsubb.addActionListener(this);


        labeldistb = new JButton("Lable Dist Code");					//label distribuor code button
        labeldistb.setMnemonic('E');
        labeldistb.setBounds(300, 125, 150, 50);
        add(labeldistb);
        labeldistb.setFont(f);
        labeldistb.addActionListener(this);


        indexb = new JButton("Index Register");					//index register button
        indexb.setMnemonic('E');
        indexb.setFont(f);
        indexb.setBounds(550, 125, 150, 50);
        add(indexb);
        indexb.addActionListener(this);

        backupb = new JButton("Backup");							//backup button
        backupb.setMnemonic('B');
        backupb.setFont(f);
        backupb.setBounds(800, 125, 150, 50);
        add(backupb);
        backupb.addActionListener(this);




        dis.add(lab);												//d/p menu items
        dis.add(rem);
        dis.add(mem);
        dis.add(ind);
        dis.add(SupplementaryIndex);
        dis.add(des);
        dis.add(cross);
        dis.add(left);
        dis.add(remstatus);
        dis.add(dstatus);



        mem.add(memd);
        mem.add(mems);
        lab.add(month);
        lab.add(bulk);
        lab.add(all);
        month.add(subno);
        month.add(dno);

        rem.add(rembulk);
        rem.add(remsub);

        subscription_menu.add(new1);												//subscription menu items
        subscription_menu.add(renew);


        //JMenu recdel, oth;
        //JMenuItem

        mis.add(recdel);
        mis.add(oth);
        mis.add(despatch);
        //mis.add(account_book_details_menu);
        mis.add(petty_menu);
        mis.add(rcptdet);
        mis.add(dup);
        
        account_book_details_menu.add(accnt);
        account_book_details_menu.add(accntsum);
        account_book_details_menu.add(accnt_monthly);
        account_book_details_menu.add(accnt_yearly);
        despatch.add(despdet);
        despatch.add(moddesp);

        petty_menu.add(pet);
        petty_menu.add(petty_month);
        petty_menu.add(petty_year);

        recdel.add(retb1);
        recdel.add(freeze);

        retb1.add(retb);
        retb1.add(retl);

        freeze.add(freezed);
        freeze.add(freezes);

        recdel.add(deactive);

        deactive.add(deactived);
        deactive.add(deactives);

        recdel.add(inactive);

        inactive.add(inactived);
        inactive.add(inactives);
        //recdel.add(del);
        oth.add(state);
        oth.add(district);
        oth.add(subl);
        oth.add(dnol);

        //=============Inventory Management Menu----------------------//
        inventory_management_menu.add(receipt_book_menu);
        inventory_management_menu.add(sat_sandesh_menu);
        
        sat_sandesh_menu.add(add_inventory_menu);
        sat_sandesh_menu.add(issue_inventory_menu);
        sat_sandesh_menu.add(report_inventory_menu);
        
        add_inventory_menu.add(new_inventory_menu_item);
        add_inventory_menu.add(sub_issue_inventory_menu_item);
        
        issue_inventory_menu.add(distribute_inventory_menu_item);
        issue_inventory_menu.add(bind_inventory_menu_item);
        
        report_inventory_menu.add(summary_inventory_report_menu_item);
        report_inventory_menu.add(detailed_inventory_report_menu_item);
        report_inventory_menu.add(distribution_inventory_report_menu_item);
        report_inventory_menu.add(view_page_number_inventory_report);
        
        
        
        receipt_book_menu.add(receipt_add_new_series_menu_item);
        receipt_book_menu.add(receipt_issue_details_menu);
        receipt_book_menu.add(receipt_book_reporting_menu);
        receipt_book_menu.add(account_book_details_menu);
        
        receipt_issue_details_menu.add(issue_new_receipt_book_menu_item);
        receipt_issue_details_menu.add(issue_reissue_menu_item);
        receipt_issue_details_menu.add(issue_revert_menu_item);
        
        
        receipt_book_reporting_menu.add(issue_receipt_book_status);
        receipt_book_reporting_menu.add(issue_series_issue_details);

        //----------------search menu --------------------------------//
        search.add(searchsubno);
        search.add(searchrcptno);
        search.add(searchname);
        search.add(searchaddr);



        new1.addActionListener(this);
        renew.addActionListener(this);
        des.addActionListener(this);
        rem.addActionListener(this);
        cross.addActionListener(this);
        memd.addActionListener(this);
        left.addActionListener(this);
        mems.addActionListener(this);
        remstatus.addActionListener(this);
        dstatus.addActionListener(this);
        bulk.addActionListener(this);
        all.addActionListener(this);
        subno.addActionListener(this);
        dno.addActionListener(this);
        ind.addActionListener(this);
        SupplementaryIndex.addActionListener(this);
        remsub.addActionListener(this);
        rembulk.addActionListener(this);
        rcptdet.addActionListener(this);

        accnt.addActionListener(this);
        accntsum.addActionListener(this);
        accnt_monthly.addActionListener(this);
        accnt_yearly.addActionListener(this);

        despdet.addActionListener(this);
        retb.addActionListener(this);
        retl.addActionListener(this);
        freezed.addActionListener(this);
        freezes.addActionListener(this);
        inactived.addActionListener(this);
        inactives.addActionListener(this);
        deactived.addActionListener(this);
        deactives.addActionListener(this);
        state.addActionListener(this);
        district.addActionListener(this);
        subl.addActionListener(this);
        dnol.addActionListener(this);

        moddesp.addActionListener(this);


        l1 = new JLabel("SAT SANDESH");

        l1.setBounds(330, 350, 400, 100);
        l1.setFont(new Font("SERIF", Font.BOLD, 50));
        add(l1);

        try {
            l2 = new JLabel();
            l2.setBounds(450, 250, 110, 70);
            l2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg"))));
            add(l2);
        } catch (Exception e) {
            new except(e, this.getClass().toString());
        }
        mbar.add(subscription_menu);
        mbar.add(dis);
        mbar.add(mis);
        mbar.add(inventory_management_menu);
        mbar.add(search);


        setVisible(true);
        new refresh();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == newb) {
            new SatSandeshNewSubscription();
            this.dispose();

        }

        if (ae.getSource() == renewb) {
            new SatSandeshRenewSubNumSelection();
            this.dispose();

        }

        if (ae.getSource() == memstatusb) {
            new memstatus();
            this.dispose();
        }

        if (ae.getSource() == left) {
            new leftout();
            this.dispose();
        }


        if (ae.getSource() == remstatus) {
            remstatus1 remstatus1 = new remstatus1();
            this.dispose();
        }

        if (ae.getSource() == dstatus) {
            dstatus dstatus1 = new dstatus();
            this.dispose();
        }

        if (ae.getSource() == reminderb) {
            remindersub remindersub = new remindersub();
            this.dispose();
        }

        if (ae.getSource() == labelsubb) {
            labelsubno labelsubno = new labelsubno();
            this.dispose();
        }

        if (ae.getSource() == labeldistb) {
            labeldist labeldist = new labeldist();
            this.dispose();
        }

        if (ae.getSource() == indexb) {
            indexdate indexdate = new indexdate();
            this.dispose();
        }

        if (ae.getSource() == backupb) {
            TestBackup testBackup = new TestBackup();
            JOptionPane.showMessageDialog(null, "BACKUP SUCCESSFULL", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        }

        if (ae.getSource() == ind) {
            indexdate indexdate = new indexdate();
            this.dispose();
        }
        
        if(ae.getSource()==SupplementaryIndex)
        {
            new SupplementaryIndexDate();
            this.dispose();
        }


        if (ae.getSource() == new1) {
            SatSandeshNewSubscription entry = new SatSandeshNewSubscription();
            this.dispose();
        }

        if (ae.getSource() == renew) {
            SatSandeshRenewSubNumSelection renew1 = new SatSandeshRenewSubNumSelection();
            this.dispose();
        }

        if (ae.getSource() == subno) {
            labelsubno labelsubno = new labelsubno();
            this.dispose();
        }



        if (ae.getSource() == dno) {
            new labeldist();
            this.dispose();
        }

        if(ae.getSource()==dup)
        {
            new duplicate();
            this.dispose();
        }
        if(ae.getSource()==pet)
        {
            new petty();
            this.dispose();
        }

        if(ae.getSource()==petty_month)
        {
            new petty_report();
            this.dispose();
        }

        if(ae.getSource()==petty_year)
        {
            new petty_yearly();
            this.dispose();
        }

        if (ae.getSource() == bulk) {
            new bulkadd();
            this.dispose();
        }
        if (ae.getSource() == all) {
            new printalldno();
            this.dispose();
        }

        if (ae.getSource() == des) {
            new despreg();
            this.dispose();
        }

        if (ae.getSource() == cross) {
            new crosschk();
            this.dispose();
        }

        if (ae.getSource() == memd) {
            new memstatus();
            this.dispose();
        }

        if (ae.getSource() == mems) {
            new memsum();
            this.dispose();
        }


        //miscellaneous
        if (ae.getSource() == rcptdet) {
            new accdet();
            this.dispose();
        }


        if (ae.getSource() == despdet) {
            new desp();
            this.dispose();
        }

        if (ae.getSource() == accnt) {
            new accnt();
            this.dispose();
        }

        if (ae.getSource() == accntsum) {
            new accntsum();
            this.dispose();
        }

        if (ae.getSource() == accnt_monthly) {
            new accnt_monthly();
            this.dispose();
        }

        if (ae.getSource() == accnt_yearly) {
            new accnt_yearly();
            this.dispose();
        }

        if (ae.getSource() == retb) {
            new returnback();
            this.dispose();
        }

        if (ae.getSource() == retl) {
            new returnlist();
            this.dispose();
        }

        if (ae.getSource() == freezes) {
            new freeze();
            this.dispose();
        }


        if (ae.getSource() == deactives) {
            new deactive();
            this.dispose();
        }


        if (ae.getSource() == inactives) {
            new inactive();
            this.dispose();
        }

        if (ae.getSource() == freezed) {
            new freezed();
            this.dispose();
        }


        if (ae.getSource() == deactived) {
            new deactived();
            this.dispose();
        }


        if (ae.getSource() == inactived) {
            new inactived();
            this.dispose();
        }


        if (ae.getSource() == state) {
            new printstate();
            this.dispose();
        }

       
        if (ae.getSource() == district) {
            new printdistrict();
            this.dispose();
        }

        if (ae.getSource() == subl) {
            new printsubno();
            this.dispose();
        }
        if (ae.getSource() == dnol) {
            new printdno();
            this.dispose();
        }


        if (ae.getSource() == searchsubno) {
            new searchsub();
            this.dispose();
        }

        if (ae.getSource() == searchrcptno) {
            new searchrcpt();
            this.dispose();
        }

        if (ae.getSource() == searchname) {
            new searchcombo();
            this.dispose();
        }

        if (ae.getSource() == searchaddr) {
            new searchadd();
            this.dispose();
        }

        if (ae.getSource() == remsub) {
            new remindersub();
            this.dispose();
        }

        if (ae.getSource() == rembulk) {
            new reminderbulk();
            this.dispose();
        }

        if (ae.getSource() == moddesp) {
            new moddesp();
            this.dispose();
        }
        
        if(ae.getSource() == receipt_add_new_series_menu_item)
        {
            new ReceiptBookClass();
            this.dispose();
        }
        
        if(ae.getSource() == issue_new_receipt_book_menu_item)
        {
            new IssueReceiptBookClass();
            this.dispose();
        }
        
        if(ae.getSource() == issue_reissue_menu_item)
        {
            new InventorySubIssueDetailsClass();
            this.dispose();
        }
        
        if(ae.getSource() == issue_revert_menu_item)
        {
            new InventoryRevertIssueDetails();
            this.dispose();
        }
        
        if(ae.getSource() == new_inventory_menu_item)
        {
            this.setVisible(false);
            new SatSandeshInventory();
            this.dispose();
        }
        
        if(ae.getSource() == sub_issue_inventory_menu_item)
        {
            this.setVisible(false);
            new SatSandeshInventoryStoreIssue();
            this.dispose();
        }
        
        if(ae.getSource() == distribute_inventory_menu_item)
        {
            this.setVisible(false);
            new SatSandeshInventoryIssue();
            this.dispose();
        }
        
        if(ae.getSource() == bind_inventory_menu_item)
        {
            this.setVisible(false);
            new SatSandeshBindingEntry();
            this.dispose();
        }
        
        if(ae.getSource() == summary_inventory_report_menu_item)
        {
            this.setVisible(false);
            new SatSandeshInventorySummaryReport();
            this.dispose();
        }
        
        if(ae.getSource() == detailed_inventory_report_menu_item)
        {
            this.setVisible(false);
            new SatSandeshInventoryReport();
            this.dispose();
        }
        
        if(ae.getSource() == distribution_inventory_report_menu_item)
        {
            this.setVisible(false);
            new SatSandeshDistributionReport();
            this.dispose();
        }
        
        if(ae.getSource() == view_page_number_inventory_report)
        {
            this.setVisible(false);
            new SatSandeshInventoryView();
            this.dispose();
        }
                

    }
}


