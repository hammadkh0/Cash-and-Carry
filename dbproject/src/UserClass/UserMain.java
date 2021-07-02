package src.UserClass;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserMain extends JFrame {
    private JFrame frame;
    private JButton b1, b2, b3,b4,b5,b6;
    private JMenuBar menubar;
    private JMenu signout, viewcart,user;
    private JLabel l;
    private JMenuItem payment,paymentInfo;

    protected UserMain()
    {
        menubar = new JMenuBar();
        signout = new JMenu("Signout");
        viewcart = new JMenu("View Cart");
        user = new JMenu(Login.getIndex());
        l = new JLabel(" | ");

        user.setBorderPainted(false);
        payment = new JMenuItem("Add Payment Option");
        paymentInfo = new JMenuItem("View Payment Information");
        user.add(payment);
        user.add(paymentInfo);

        menubar.add(signout);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(user);
        menubar.add(l);
        menubar.add(viewcart);

        frame = new JFrame("Main Menu");
        frame.setJMenuBar(menubar);
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        b1= new JButton("Edit your Record");
        b1.setBounds(10, 50, 350, 30);
        b2= new JButton("Display your Record");
        b2.setBounds(10, 90, 350, 30);
        b3= new JButton("Delete your Record");
        b3.setBounds(10, 130,350, 30);
        b4= new JButton("Add another account");
        b4.setBounds(10, 170,350, 30);
        b5= new JButton("Purchase an Item");
        b5.setBounds(10, 210,350, 30);
        b6= new JButton("View Purchase History");
        b6.setBounds(10, 250,350, 30);

        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);
        b4.setFocusable(false);
        b5.setFocusable(false);
        b6.setFocusable(false);
        
        frame.getContentPane().add(b1);
        frame.getContentPane().add(b2);
        frame.getContentPane().add(b3);
        frame.getContentPane().add(b4);
        frame.getContentPane().add(b5);
        frame.getContentPane().add(b6);
        
        MyActionListener m = new MyActionListener();
        SampleMenuListener simp = new SampleMenuListener();
        
        b1.addActionListener(m);
        b2.addActionListener(m);
        b3.addActionListener(m);
        b4.addActionListener(m);
        b5.addActionListener(m);
        b6.addActionListener(m);
        payment.addActionListener(m);
        paymentInfo.addActionListener(m);

        signout.addMenuListener(simp);
        viewcart.addMenuListener(simp);

        frame.setVisible(true);
        
    }
    private class MyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand() == "Edit your Record")
            {
                frame.dispose();
                Record rec =  new Record();
            }
            if (e.getActionCommand() == "Display your Record")
            {   
                Display dp = new Display();
            }
                
            if (e.getActionCommand() == "Delete your Record")
            {
                int dialogbutton = JOptionPane.YES_NO_OPTION;
                int dialogeresult = JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogbutton);
                if(dialogeresult==JOptionPane.YES_OPTION){

                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
        
                    String str = "delete from buyer where username = \'"+Login.getIndex()+"\'";
                    java.sql.PreparedStatement prep =  con.prepareStatement(str);
                    prep.executeQuery();

                    con.commit();
                    con.close();
                    frame.dispose();
                    StartU su = new StartU();
        
                }   catch (SQLException sq){sq.printStackTrace();}
                    catch (Exception exc) {exc.printStackTrace();}
                }
            }
            if(e.getActionCommand()=="Add another account"){
                AddAccount adacc = new AddAccount();
            }
            if(e.getActionCommand()=="Purchase an Item"){
                Purchase pur = new Purchase();
            }

            if(e.getSource()==payment){
                Payment pay = new Payment();
            }
            if(e.getSource()==paymentInfo){
                PaymentInfo pay = new PaymentInfo();
            }
            if(e.getActionCommand() == "View Purchase History"){
                PurchaseHistory phs = new PurchaseHistory();
            }
        }
    }
    private class SampleMenuListener implements MenuListener {
        
        @Override
        public void menuSelected(MenuEvent e) {
            if(e.getSource()==signout){
                frame.dispose();
                StartU g = new StartU();
            }
            if(e.getSource()==viewcart){
                Cart crt = new Cart();
            }
        }
    
        @Override
        public void menuDeselected(MenuEvent e) {
        }
    
        @Override
        public void menuCanceled(MenuEvent e) {
        }
    }
}
