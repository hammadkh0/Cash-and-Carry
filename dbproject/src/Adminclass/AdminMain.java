package src.Adminclass;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminMain extends JFrame {
    
    protected AdminMain()
    {

        menubar = new JMenuBar();
        signout = new JMenu("Sign out");
        search = new JMenu("Search");


        acc = new JMenu(Ad_Login.getIndex());

        acc.setBorderPainted(false);

        info = new JMenuItem("Edit your Account");
        dispinfo = new JMenuItem("Display your Account");
        deleteinfo = new JMenuItem("Delete your Account");
        s_name = new JMenuItem("Search Accounts");
        s_inv = new JMenuItem("Search Inventory");

        acc.add(info);  acc.add(dispinfo);  acc.add(deleteinfo);
        search.add(s_name); search.add(s_inv);
        menubar.add(search);
        menubar.add(signout);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(acc);
        
        frame = new JFrame("Main Menu");
        frame.setJMenuBar(menubar);
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        b3= new JButton("Display all Admins");
        b3.setBounds(20, 70, 350, 30);
        b5= new JButton("Add another Admin");
        b5.setBounds(20, 110,350, 30);
        b7= new JButton("View your Inventory");
        b7.setBounds(20, 150,350, 30);
        b8= new JButton("Add Inventory");
        b8.setBounds(20, 190,350, 30);

        b3.setFocusable(false);
        b5.setFocusable(false);
        b7.setFocusable(false);
        b8.setFocusable(false);
        
        frame.getContentPane().add(b3);
        frame.getContentPane().add(b5);
        frame.getContentPane().add(b7);
        frame.getContentPane().add(b8);
        
        MyActionListener m = new MyActionListener();
        SampleMenuListener simp = new SampleMenuListener();
        
        b3.addActionListener(m);
        b5.addActionListener(m);
        b7.addActionListener(m);
        b8.addActionListener(m);
        info.addActionListener(m);
        dispinfo.addActionListener(m);
        deleteinfo.addActionListener(m);
        s_name.addActionListener(m);
        s_inv.addActionListener(m);

        signout.addMenuListener(simp);

        frame.setVisible(true);
        
    }
    public class MyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand() == "Display all Admins")
            {   
                Ad_DispAll dp = new Ad_DispAll();
            }
                
            if(e.getActionCommand()=="Add another Admin"){
                AddAdmin adadm = new AddAdmin();
            }
            if(e.getActionCommand()=="View your Inventory"){

                View_Invt view = new View_Invt();
            }
            if(e.getActionCommand()=="Add Inventory"){

                Ad_Inventory inv = new Ad_Inventory();
            }

            if(e.getSource()==info){
                frame.dispose();
                Ad_Record rec =  new Ad_Record();
            }
            if(e.getSource()==dispinfo){
                Ad_Display dp = new Ad_Display();
            }
            if(e.getSource()==deleteinfo){

                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int dialogeresult = JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogbutton);
                if(dialogeresult==JOptionPane.YES_OPTION){

                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
        
                    String str = "delete from admin where username = \'"+Ad_Login.getIndex()+"\'";
                    java.sql.PreparedStatement prep =  con.prepareStatement(str);
                    prep.executeQuery();

                    con.commit();
                    con.close();
                    frame.dispose();
                    Start s = new Start();
        
                }   catch (SQLException sq){sq.printStackTrace();}
                    catch (Exception exc) {exc.printStackTrace();}
                }
            }
        }
    }
    class SampleMenuListener implements MenuListener {
        
        @Override
        public void menuSelected(MenuEvent e) {
            if(e.getSource()==signout){
                frame.dispose();
                Start st = new Start();
            }
        }
    
        @Override
        public void menuDeselected(MenuEvent e) {
        }
    
        @Override
        public void menuCanceled(MenuEvent e) {
        }
    }

    //components
    private JFrame frame;
    private JButton b3,b5,b7,b8;
    private JMenuBar menubar;
    private JMenu acc,signout, search;
    private JMenuItem info, dispinfo, deleteinfo, s_inv, s_name;
}
