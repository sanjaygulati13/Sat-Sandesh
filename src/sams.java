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

    
    JMenuBar mbar;
    JMenu subscription_menu, labels_top_menu, registers_menu, subscriber_report_menu, track_data_menu,   inventory_management_menu, inventory_report_menu;
    //JMenu search_menu;
    JMenuItem newSubscriptionMenuItem, renewSubscriptionMenuItem,bulknewEntry,bulkRenewEntry, despatchRegisterMenuItem, distributorRemindersMenuItem, despatchRemindersMenuItem, crossCheckingReportMenuItem, detailedMemberStatusMenuItem, summaryMemberStatusMenuItem, centerLabelsMenuItem, despatchLabelsMenuItem, centerListMenuItem, distributorLabelsMenuItem, reminderStatusMenuItem, distributorStatusMenuItem, leftoutsMenuItem;
    
    
    JMenu freeze, inactive, deactive, account_book_details_menu, petty_menu;
    JMenuItem receiptBookStatusMenuItem, accnt, accntsum,accnt_monthly, accnt_yearly, subscriptionReportMenuItem, addDistributorCodeMenuItem, alterDistributorCodeMenuItem, markReturnBackMenuItem,returnBackListMenuItem, /*freezed, freezes, deactived, deactives, inactived, inactives,*/ stateSubscriberReportMenuItem, districtSubscriberReportMenuItem,despatchCodeSubscriberReportMenuItem, distributorCodeSubscriberReportMenuItem, pet,petty_month, petty_year, dup;
    JMenuItem indexRegisterMenuItem, supplementaryIndexRegisterMenuItem;
    //JMenuItem recordDeletionMenu;
    
    
    JMenuItem searchsubno /*, searchrcptno, searchname, searchaddr*/;
    JButton newb, renewb, memstatusb, reminderb, labelsubb, labeldistb, indexb, backupButton;
    Font f = new Font("ARIAL", Font.BOLD, 14);
    JLabel titleLabel,userLabel, satSandeshIconLabel;
    
    
    JMenuItem inventory_management_sat_sandesh_menu_item, inventory_reports_sat_sandesh_menu_item;
    JMenuItem receipt_add_new_series_menu_item, issue_new_receipt_book_menu_item, issue_reissue_menu_item, issue_revert_menu_item;
    JMenuItem issue_series_issue_details;
    
    JMenuItem new_inventory_menu_item, sub_issue_inventory_menu_item;
    JMenuItem distribute_inventory_menu_item, bind_inventory_menu_item, account_book_posting_inventory_menu_item;
    JMenuItem summary_inventory_report_menu_item, detailed_inventory_report_menu_item, distribution_inventory_report_menu_item, view_page_number_inventory_report ;
    
    
    JMenuItem oldPeriodReportMenuItem, backupMenuItem, restoreBackupMenuItem,consolidate_stock_menu_item;
    JMenuItem inventory_management_receipt_book_menu_item, inventory_reports_receipt_book_menu_item;
    JMenuItem consolidated_reports_menu_item, counter_book_status_menu_item;
    
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
        
        labels_top_menu = new JMenu("Labels");								//display/print
        labels_top_menu.setMnemonic('l');
        
        registers_menu = new JMenu("Registers");		
        registers_menu.setMnemonic('r');
        
        subscriber_report_menu= new JMenu("Subscriber Reports");
        subscriber_report_menu.setMnemonic('u');
        
        track_data_menu = new JMenu("Track Data");	
        track_data_menu.setMnemonic('T');
        
        inventory_management_menu = new JMenu("Inventory Management");
        inventory_management_menu.setMnemonic('i');
        
        inventory_report_menu= new JMenu("Inventory Reports");	
        inventory_report_menu.setMnemonic('v');
        
        
        
        //search_menu = new JMenu("Search");									//search
        //search_menu.setMnemonic('e');
        //lab = new JMenu("Labels");									//labels--d/p
        //lab.setMnemonic('l');
        
        newSubscriptionMenuItem = new JMenuItem("New");									//new--subscription
        newSubscriptionMenuItem.setMnemonic('n');
        
        renewSubscriptionMenuItem = new JMenuItem("Renew");                                                                 //renew--subscription
        renewSubscriptionMenuItem.setMnemonic('r');
        
        bulknewEntry = new JMenuItem("Bulk New");							//bulk new--subscription
        bulknewEntry.setMnemonic('u');
         
        bulkRenewEntry = new JMenuItem("Bulk Renew");
        bulkRenewEntry.setMnemonic('l');
        
        
        despatchLabelsMenuItem = new JMenuItem("Despatch Labels");								//by sub no--d/p--labels--monthly
        despatchLabelsMenuItem.setMnemonic('l');
        
        despatchRemindersMenuItem = new JMenuItem("Despatch Reminders");					//by sub no--d/p--reminders
        despatchRemindersMenuItem.setMnemonic('r');
        
        distributorLabelsMenuItem = new JMenuItem("Distributor Labels");							//by distributor--d/p--labels--monthly
        distributorLabelsMenuItem.setMnemonic('d');
        
        distributorRemindersMenuItem = new JMenuItem("Distributor Reminders");					//by D#--d/p--reminders
        distributorRemindersMenuItem.setMnemonic('e');
        
        leftoutsMenuItem = new JMenuItem("Leftouts");
        leftoutsMenuItem.setMnemonic('o');
        
        //===============
        despatchRegisterMenuItem = new JMenuItem("Despatch register");						//despatch--d/p
        despatchRegisterMenuItem.setMnemonic('d');
        
        
        indexRegisterMenuItem = new JMenuItem("Index Register");						//index--d/p
        indexRegisterMenuItem.setMnemonic('i');
        
        supplementaryIndexRegisterMenuItem = new JMenuItem("Supplimentary index Register");
        supplementaryIndexRegisterMenuItem.setMnemonic('s');
        
        //===============
        centerLabelsMenuItem = new JMenuItem("Center Labels");							//bulk address--d/p--labels
        centerLabelsMenuItem.setMnemonic('c');
        
        centerListMenuItem = new JMenuItem("Center List");		//print centerListMenuItem distributorLabelsMenuItem--d/p--labels
        centerListMenuItem.setMnemonic('l');
        
        crossCheckingReportMenuItem = new JMenuItem("Cross Checking Report");				//cross check reports--d/p
        crossCheckingReportMenuItem.setMnemonic('r');
        
        despatchCodeSubscriberReportMenuItem = new JMenuItem("Despatch Code Wise");							//misclellaneous--others--sub list
        despatchCodeSubscriberReportMenuItem.setMnemonic('d');
        
        distributorCodeSubscriberReportMenuItem = new JMenuItem("Distributor Code Wise");							//misclellaneous--others--distributorLabelsMenuItem list
        distributorCodeSubscriberReportMenuItem.setMnemonic('i');
        
        distributorStatusMenuItem = new JMenuItem("Distributor Status");							//member status--d/p
        distributorStatusMenuItem.setMnemonic('s');
        
        districtSubscriberReportMenuItem = new JMenuItem("District Wise");					//misclellaneous--others--districtSubscriberReportMenuItem list
        districtSubscriberReportMenuItem.setMnemonic('t');
        
        detailedMemberStatusMenuItem = new JMenuItem("Member Status - Detailed");
        detailedMemberStatusMenuItem.setMnemonic('m');
        
        summaryMemberStatusMenuItem = new JMenuItem("Member Status - Summary");
        summaryMemberStatusMenuItem.setMnemonic('y');
        
        oldPeriodReportMenuItem = new JMenuItem("Old Period Report");
        oldPeriodReportMenuItem.setMnemonic('p');
        
        
        reminderStatusMenuItem = new JMenuItem("Reminder Status");							//member status--d/p
        reminderStatusMenuItem.setMnemonic('e');
        
        returnBackListMenuItem=new JMenuItem("Return Back List");
        returnBackListMenuItem.setMnemonic('b');
        
        stateSubscriberReportMenuItem = new JMenuItem("State Wise");							//misclellaneous--others--stateSubscriberReportMenuItem list
        stateSubscriberReportMenuItem.setMnemonic('w');
        
        subscriptionReportMenuItem = new JMenuItem("Subscription Report");
        subscriptionReportMenuItem.setMnemonic('u');
        
        //recordDeletionMenu = new JMenuItem("Record Deletion");						//record deletion--miscellaneous
        //recordDeletionMenu.addActionListener(this);
        //recordDeletionMenu.setMnemonic('e');
        
        //=================================
        
        addDistributorCodeMenuItem = new JMenuItem("Add Distributor Code");
        addDistributorCodeMenuItem.setMnemonic('a');

        alterDistributorCodeMenuItem = new JMenuItem("Alter Distributor Code");
        alterDistributorCodeMenuItem.setMnemonic('M');
        
        backupMenuItem = new JMenuItem("Backup");
        backupMenuItem.setMnemonic('b');
        
        restoreBackupMenuItem = new JMenuItem("Restore Backup");
        restoreBackupMenuItem.setMnemonic('r');
        
        markReturnBackMenuItem = new JMenuItem("Mark Return Back");						//mark return back--misclellaneous--record deletion
        markReturnBackMenuItem.setMnemonic('m');
        
        //=================================
        
        inventory_management_receipt_book_menu_item = new JMenuItem("Receipt Book");
        

        account_book_posting_inventory_menu_item = new JMenuItem("Account Book Posting");
        account_book_posting_inventory_menu_item.setMnemonic('p');
        account_book_posting_inventory_menu_item.addActionListener(this);
        
        receipt_add_new_series_menu_item = new JMenuItem("Add New Series");
        receipt_add_new_series_menu_item.setMnemonic('a');
        receipt_add_new_series_menu_item.addActionListener(this);
        
        issue_reissue_menu_item = new JMenuItem("Book Re-Issue");
        issue_reissue_menu_item.setMnemonic('r');
        issue_reissue_menu_item.addActionListener(this);
        
        issue_new_receipt_book_menu_item = new JMenuItem("Issue New Receipt Book");
        issue_new_receipt_book_menu_item.setMnemonic('i');
        issue_new_receipt_book_menu_item.addActionListener(this);
        
        issue_revert_menu_item = new JMenuItem("Book Revert Back");
        issue_revert_menu_item.setMnemonic('b');
        issue_revert_menu_item.addActionListener(this);
        
            //=================================
        inventory_management_sat_sandesh_menu_item = new JMenuItem("Sat Sandesh");
        
        new_inventory_menu_item = new JMenuItem("Add Inventory");
        new_inventory_menu_item.setMnemonic('v');
        new_inventory_menu_item.addActionListener(this);
        
        bind_inventory_menu_item = new JMenuItem("Binding Issue");
        bind_inventory_menu_item.setMnemonic('n');
        bind_inventory_menu_item.addActionListener(this);
        
        distribute_inventory_menu_item = new JMenuItem("Issue distribution");
        distribute_inventory_menu_item.setMnemonic('d');
        distribute_inventory_menu_item.addActionListener(this);
        
        consolidate_stock_menu_item = new JMenuItem("Consolidate Stock");
        consolidate_stock_menu_item.setMnemonic('c');
        
        sub_issue_inventory_menu_item = new JMenuItem("Issue Inventory");
        sub_issue_inventory_menu_item.setMnemonic('s');
        sub_issue_inventory_menu_item.addActionListener(this);
        
        //=================================
        
        inventory_reports_receipt_book_menu_item = new JMenuItem("Receipt Book");
        
        receiptBookStatusMenuItem = new JMenuItem("Receipt Book Status");				//Receipt Book details--misclellaneous
        receiptBookStatusMenuItem.setMnemonic('b');
        
        issue_series_issue_details = new JMenuItem("Series Issue Status");
        issue_series_issue_details.setMnemonic('i');
        issue_series_issue_details.addActionListener(this);
        
        account_book_details_menu = new JMenu("Account Book Status");						//account book--misclellaneous
        account_book_details_menu.setMnemonic('a');
        
        inventory_reports_sat_sandesh_menu_item = new JMenuItem("Sat Sandesh");
        
       
        consolidated_reports_menu_item = new JMenuItem("Consolidated Reports");
        consolidated_reports_menu_item.setMnemonic('o');
        consolidated_reports_menu_item.addActionListener(this);
        
        counter_book_status_menu_item = new JMenuItem("Counter Book Status");
        counter_book_status_menu_item.setMnemonic('c');
        counter_book_status_menu_item.addActionListener(this);
        
        
        summary_inventory_report_menu_item = new JMenuItem("Consolidated Stock Status");
        summary_inventory_report_menu_item.setMnemonic('n');
        summary_inventory_report_menu_item.addActionListener(this);
        
        detailed_inventory_report_menu_item = new JMenuItem("Stock Status");
        detailed_inventory_report_menu_item.setMnemonic('s');
        detailed_inventory_report_menu_item.addActionListener(this);
        
        distribution_inventory_report_menu_item = new JMenuItem("Distribution Report");
        distribution_inventory_report_menu_item.setMnemonic('d');
        distribution_inventory_report_menu_item.addActionListener(this);    
        
        
        view_page_number_inventory_report = new JMenuItem("View Page Details");
        view_page_number_inventory_report.setMnemonic('p');
        view_page_number_inventory_report.addActionListener(this);    
        

        //=================================
        
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
        
        accnt = new JMenuItem("Detailed");
        accnt.setMnemonic('D');

        accntsum = new JMenuItem("Summary");
        accntsum.setMnemonic('S');

        accnt_monthly = new JMenuItem("Monthly Report");
        accnt_monthly.setMnemonic('M');

        accnt_yearly = new JMenuItem("Yearly Report");
        accnt_yearly.setMnemonic('Y');
        
        /*del=new JMenuItem("Delete");									//delete--misclellaneous--record deletion
        del.setMnemonic('e');
         */

        /*freeze = new JMenu("Freeze Records List");
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
        inactives.setMnemonic('S');*/

        
        searchsubno = new JMenuItem("Search");				// search_menu options
        searchsubno.setMnemonic('S');
        searchsubno.addActionListener(this);

        /*searchrcptno = new JMenuItem("Search By rcpt No");
        searchrcptno.setMnemonic('R');
        searchrcptno.addActionListener(this);

        searchname = new JMenuItem("Search By Name");
        searchname.setMnemonic('N');
        searchname.addActionListener(this);


        searchaddr = new JMenuItem("Search By Address");
        searchaddr.setMnemonic('A');
        searchaddr.addActionListener(this);*/



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


        labeldistb = new JButton("Label Dist Code");					//label distribuor code button
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

        backupButton = new JButton("Backup");							//backup button
        backupButton.setMnemonic('B');
        backupButton.setFont(f);
        backupButton.setBounds(800, 125, 150, 50);
        add(backupButton);
        backupButton.addActionListener(this);

        
        //----------------search_menu menu --------------------------------//
        //search_menu.add(searchsubno);
        /*search_menu.add(searchrcptno);
        search_menu.add(searchname);
        search_menu.add(searchaddr);*/
        
        account_book_details_menu.add(accnt);
        account_book_details_menu.add(accntsum);
        account_book_details_menu.add(accnt_monthly);
        account_book_details_menu.add(accnt_yearly);

        subscription_menu.add(bulknewEntry);
        subscription_menu.add(bulkRenewEntry);
        subscription_menu.add(newSubscriptionMenuItem);												//subscription menu items
        subscription_menu.add(renewSubscriptionMenuItem);
 

        labels_top_menu.add(despatchLabelsMenuItem);
        labels_top_menu.add(despatchRemindersMenuItem);
        labels_top_menu.add(distributorLabelsMenuItem);
        labels_top_menu.add(distributorRemindersMenuItem);
        labels_top_menu.add(leftoutsMenuItem);
        
        
        registers_menu.add(despatchRegisterMenuItem);
        registers_menu.add(indexRegisterMenuItem);
        registers_menu.add(supplementaryIndexRegisterMenuItem);       
        
        registers_menu.add(petty_menu);
        //registers_menu.add(receiptBookStatusMenuItem);
        registers_menu.add(dup);

        
        subscriber_report_menu.add(centerLabelsMenuItem);
        subscriber_report_menu.add(centerListMenuItem);
        subscriber_report_menu.add(crossCheckingReportMenuItem);
        subscriber_report_menu.add(despatchCodeSubscriberReportMenuItem);
        subscriber_report_menu.add(distributorCodeSubscriberReportMenuItem);
        subscriber_report_menu.add(distributorStatusMenuItem);
        subscriber_report_menu.add(districtSubscriberReportMenuItem);
        subscriber_report_menu.add(detailedMemberStatusMenuItem);
        subscriber_report_menu.add(summaryMemberStatusMenuItem);
        subscriber_report_menu.add(oldPeriodReportMenuItem);
        subscriber_report_menu.add(reminderStatusMenuItem);
        subscriber_report_menu.add(returnBackListMenuItem);
        subscriber_report_menu.add(stateSubscriberReportMenuItem);
        subscriber_report_menu.add(subscriptionReportMenuItem);
        //subscriber_report_menu.add(recordDeletionMenu);
        
        //labels_top_menu.add(reminderStatusMenuItem);
        
        track_data_menu.add(addDistributorCodeMenuItem);
        track_data_menu.add(alterDistributorCodeMenuItem);
        track_data_menu.add(backupMenuItem);
        track_data_menu.add(markReturnBackMenuItem);
        track_data_menu.add(restoreBackupMenuItem);
        track_data_menu.add(searchsubno);
        
        inventory_management_menu.addSeparator();
        inventory_management_menu.add(inventory_management_receipt_book_menu_item);
        inventory_management_menu.addSeparator();
        inventory_management_menu.add(account_book_posting_inventory_menu_item);
        inventory_management_menu.add(receipt_add_new_series_menu_item);
        inventory_management_menu.add(issue_reissue_menu_item);
        inventory_management_menu.add(issue_new_receipt_book_menu_item);
        inventory_management_menu.add(issue_revert_menu_item);
        inventory_management_menu.addSeparator();
        inventory_management_menu.add(inventory_management_sat_sandesh_menu_item);
        inventory_management_menu.addSeparator();
        inventory_management_menu.add(new_inventory_menu_item);
        inventory_management_menu.add(bind_inventory_menu_item);
        inventory_management_menu.add(consolidate_stock_menu_item);
        inventory_management_menu.add(distribute_inventory_menu_item);
        inventory_management_menu.add(sub_issue_inventory_menu_item);
        
        
        
        inventory_report_menu.addSeparator();
        inventory_report_menu.add(inventory_reports_receipt_book_menu_item);
        inventory_report_menu.addSeparator();
        inventory_report_menu.add(account_book_details_menu);
        inventory_report_menu.add(consolidated_reports_menu_item);
        inventory_report_menu.add(counter_book_status_menu_item);
        inventory_report_menu.add(receiptBookStatusMenuItem);
        inventory_report_menu.add(issue_series_issue_details);
        inventory_report_menu.addSeparator();
        inventory_report_menu.add(inventory_reports_sat_sandesh_menu_item);
        inventory_report_menu.addSeparator();
        inventory_report_menu.add(summary_inventory_report_menu_item);
        inventory_report_menu.add(distribution_inventory_report_menu_item);
        inventory_report_menu.add(detailed_inventory_report_menu_item);
        inventory_report_menu.add(view_page_number_inventory_report);
        
        

        petty_menu.add(pet);
        petty_menu.add(petty_month);
        petty_menu.add(petty_year);

        
        /*recordDeletionMenu.add(freeze);

        freeze.add(freezed);
        freeze.add(freezes);

        recordDeletionMenu.add(deactive);

        deactive.add(deactived);
        deactive.add(deactives);

        recordDeletionMenu.add(inactive);

        inactive.add(inactived);
        inactive.add(inactives);*/
        
        //inventory_management_sat_sandesh_menu_item.add(report_inventory_menu);

        
        
        
        
        newSubscriptionMenuItem.addActionListener(this);
        renewSubscriptionMenuItem.addActionListener(this);
        bulknewEntry.addActionListener(this);
        despatchRegisterMenuItem.addActionListener(this);
        //rem.addActionListener(this);
        crossCheckingReportMenuItem.addActionListener(this);
        detailedMemberStatusMenuItem.addActionListener(this);
        leftoutsMenuItem.addActionListener(this);
        summaryMemberStatusMenuItem.addActionListener(this);
        oldPeriodReportMenuItem.addActionListener(this);
        reminderStatusMenuItem.addActionListener(this);
        distributorStatusMenuItem.addActionListener(this);
        centerLabelsMenuItem.addActionListener(this);
        centerListMenuItem.addActionListener(this);
        despatchLabelsMenuItem.addActionListener(this);
        distributorLabelsMenuItem.addActionListener(this);
        indexRegisterMenuItem.addActionListener(this);
        supplementaryIndexRegisterMenuItem.addActionListener(this);
        despatchRemindersMenuItem.addActionListener(this);
        distributorRemindersMenuItem.addActionListener(this);
        receiptBookStatusMenuItem.addActionListener(this);
        bulkRenewEntry.addActionListener(this);
        consolidate_stock_menu_item.addActionListener(this);

        accnt.addActionListener(this);
        accntsum.addActionListener(this);
        accnt_monthly.addActionListener(this);
        accnt_yearly.addActionListener(this);
        subscriptionReportMenuItem.addActionListener(this);

        addDistributorCodeMenuItem.addActionListener(this);
        backupMenuItem.addActionListener(this);
        restoreBackupMenuItem.addActionListener(this);
        markReturnBackMenuItem.addActionListener(this);
        returnBackListMenuItem.addActionListener(this);
        /*freezed.addActionListener(this);
        freezes.addActionListener(this);
        inactived.addActionListener(this);
        inactives.addActionListener(this);
        deactived.addActionListener(this);
        deactives.addActionListener(this);*/
        stateSubscriberReportMenuItem.addActionListener(this);
        districtSubscriberReportMenuItem.addActionListener(this);
        despatchCodeSubscriberReportMenuItem.addActionListener(this);
        distributorCodeSubscriberReportMenuItem.addActionListener(this);

        alterDistributorCodeMenuItem.addActionListener(this);


        titleLabel = new JLabel("SAT SANDESH");
        userLabel = new JLabel("User: "+SamsUtilities.getUserName());

        titleLabel.setBounds(330, 350, 400, 100);
        titleLabel.setFont(new Font("SERIF", Font.BOLD, 50));
        add(titleLabel);
        
        userLabel.setBounds(30, 450, 80, 40);
        userLabel.setFont(new Font("SERIF", Font.BOLD, 12));
        add(userLabel);

        try {
            satSandeshIconLabel = new JLabel();
            satSandeshIconLabel.setBounds(450, 250, 110, 70);
            satSandeshIconLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("skrm.jpg"))));
            add(satSandeshIconLabel);
        } catch (Exception e) {
            new except(e, this.getClass().toString());
        }
        
        mbar.add(subscription_menu);
        mbar.add(labels_top_menu);
        mbar.add(registers_menu);
        mbar.add(subscriber_report_menu);
        mbar.add(track_data_menu);
        mbar.add(inventory_management_menu);
        mbar.add(inventory_report_menu);
        


        setVisible(true);
        new refresh();
        
        
        while(SamsUtilities.getUserName().isEmpty())
        {
            String userName= JOptionPane.showInputDialog("Please enter your name ");
            if(userName.isEmpty()) userName = "Temp";
            SamsUtilities.setUserName(userName);
            userLabel.setText("User: "+SamsUtilities.getUserName());
            if(SamsUtilities.getUserName().isEmpty() == false)JOptionPane.showMessageDialog(this, "Welcome "+SamsUtilities.getUserName()+" !!");
        }
        

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

        if (ae.getSource() == leftoutsMenuItem) {
            new leftout();
            this.dispose();
        }


        if (ae.getSource() == reminderStatusMenuItem) {
            remstatus1 remstatus1 = new remstatus1();
            this.dispose();
        }

        if (ae.getSource() == distributorStatusMenuItem) {
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

        if (ae.getSource() == backupButton || ae.getSource() == backupMenuItem) {
            TestBackup testBackup = new TestBackup();
            JOptionPane.showMessageDialog(null, "BACKUP SUCCESSFULL", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        }

        if (ae.getSource() == indexRegisterMenuItem) {
            indexdate indexdate = new indexdate();
            this.dispose();
        }
        
        if(ae.getSource()==supplementaryIndexRegisterMenuItem)
        {
            new SupplementaryIndexDate();
            this.dispose();
        }


        if (ae.getSource() == newSubscriptionMenuItem) {
            SatSandeshNewSubscription entry = new SatSandeshNewSubscription();
            this.dispose();
        }

        if (ae.getSource() == renewSubscriptionMenuItem) {
            SatSandeshRenewSubNumSelection renew1 = new SatSandeshRenewSubNumSelection();
            this.dispose();
        }
        if (ae.getSource() == bulknewEntry) {
            SatSandeshBulkSubscription bulkEntry = new SatSandeshBulkSubscription();
            this.dispose();
        }
        if (ae.getSource() == bulkRenewEntry) {
            SatSandeshBulkRenewSubscription bulkRenew = new SatSandeshBulkRenewSubscription();
            this.dispose();
        }

        if (ae.getSource() == consolidated_reports_menu_item) {
        
            //this.dispose();
        }
        
        if (ae.getSource() == counter_book_status_menu_item) {
        
            //this.dispose();
        }
        
        if (ae.getSource() == consolidate_stock_menu_item) {
        
            //this.dispose();
        }
        if (ae.getSource() == restoreBackupMenuItem) {
        
            //this.dispose();
        }        
        
        if (ae.getSource() == despatchLabelsMenuItem) {
            labelsubno labelsubno = new labelsubno();
            this.dispose();
        }



        if (ae.getSource() == distributorLabelsMenuItem) {
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

        if (ae.getSource() == centerLabelsMenuItem) {
            new bulkadd();
            this.dispose();
        }
        if (ae.getSource() == centerListMenuItem) {
            new printalldno();
            this.dispose();
        }

        if (ae.getSource() == despatchRegisterMenuItem) {
            new despreg();
            this.dispose();
        }

        if (ae.getSource() == crossCheckingReportMenuItem) {
            new crosschk();
            this.dispose();
        }

        if (ae.getSource() == detailedMemberStatusMenuItem) {
            new memstatus();
            this.dispose();
        }

        if (ae.getSource() == summaryMemberStatusMenuItem) {
            new memsum();
            this.dispose();
        }


        //miscellaneous
        if (ae.getSource() == receiptBookStatusMenuItem) {
            this.setVisible(false);
            new CheckReceiptBookDetailsInput();
            this.dispose();
        }


        if (ae.getSource() == addDistributorCodeMenuItem) {
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
        
        if (ae.getSource() == subscriptionReportMenuItem) {
            this.setVisible(false);
            new SatSandeshSubscriptionReport();
            this.dispose();
        }
        

        if (ae.getSource() == markReturnBackMenuItem) {
            new returnback();
            this.dispose();
        }

        if (ae.getSource() == returnBackListMenuItem) {
            new returnlist();
            this.dispose();
        }

        /*
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
*/
        if(ae.getSource() == oldPeriodReportMenuItem)
        {
            new SatSandeshOldSubscriberStatusWindow();
            this.dispose();
        }

        if (ae.getSource() == stateSubscriberReportMenuItem) {
            new printstate();
            this.dispose();
        }

       
        if (ae.getSource() == districtSubscriberReportMenuItem) {
            new printdistrict();
            this.dispose();
        }

        if (ae.getSource() == despatchCodeSubscriberReportMenuItem) {
            new printsubno();
            this.dispose();
        }
        if (ae.getSource() == distributorCodeSubscriberReportMenuItem) {
            new printdno();
            this.dispose();
        }


        if (ae.getSource() == searchsubno) {
            new SatSandeshSearchBySubNumber();
            this.dispose();
        }

        /*if (ae.getSource() == searchrcptno) {
            new SatSandeshSubscriptionSearchByReceiptNumber();
            this.dispose();
        }

        if (ae.getSource() == searchname) {
            new searchcombo();
            this.dispose();
        }

        if (ae.getSource() == searchaddr) {
            new searchadd();
            this.dispose();
        }*/

        if (ae.getSource() == despatchRemindersMenuItem) {
            new remindersub();
            this.dispose();
        }

        if (ae.getSource() == distributorRemindersMenuItem) {
            new reminderbulk();
            this.dispose();
        }

        if (ae.getSource() == alterDistributorCodeMenuItem) {
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
        
        if(ae.getSource() == account_book_posting_inventory_menu_item)
        {
            this.setVisible(false);
            new SatSandeshAccountBookPosting();
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


