package src.UserClass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentInfo {

    private JFrame frame;
    private JLabel l1,l2,l3;
    private JButton b1,b2;
    private int buyer_id;

    protected PaymentInfo(){
        System.out.println("Hello");

        frame = new JFrame("Display");
        frame.setSize(400,300); 
        frame.setLayout(null);

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            String q = "select * from payment where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\')";
            java.sql.PreparedStatement prep =  con.prepareStatement(q);
            
            ResultSet rst = prep.executeQuery();
           
            if(rst.next()){
                buyer_id = rst.getInt(3);

                l1 = new JLabel("Balance:        "+rst.getInt(1));
                l1.setBounds(30,20,350,30);
                l2 = new JLabel("Account Number:       "+rst.getString(2));
                l2.setBounds(30,70,350,30);
                l3 = new JLabel("Buyer Id:        "+rst.getInt(3));
                l3.setBounds(30,120,350,30);
                // l4 = new JLabel("username       "+rst.getString(4));
                // l4.setBounds(30,170,350,30);
                // l5 = new JLabel("password       "+rst.getString(5));
                // l5.setBounds(30,220,350,30);
                // l6 = new JLabel("Email      "+rst.getString(6));
                // l6.setBounds(30,270,350,30);

                frame.getContentPane().add(l1);
                frame.getContentPane().add(l2);
                frame.getContentPane().add(l3);
                // frame.getContentPane().add(l4);
                // frame.getContentPane().add(l5);
                // frame.getContentPane().add(l6);

                b1= new JButton("Update Balance");
                b1.setBounds(100, 220, 125, 30);
                b2= new JButton("Back");
                b2.setBounds(240, 220, 90, 30);
                
                frame.getContentPane().add(b1);
                frame.getContentPane().add(b2);

                MyActionListener m = new MyActionListener();
                
                b1.addActionListener(m);
                b2.addActionListener(m);

                frame.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            if (e.getActionCommand() == "Update Balance")
            {
                String op = JOptionPane.showInputDialog(null,"Enter the new Balance Amount");
                int bal = Integer.parseInt(op);

                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                    PreparedStatement ps = con.prepareStatement("update payment set balance = "+bal+" where buyer_id = "+buyer_id);
                    ps.executeQuery();

                    l1.setText("Balance:        "+bal);
                    con.commit();
                    con.close();
                    
                } catch (SQLException sq) {sq.printStackTrace();}
                frame.dispose();
            }
            
            if (e.getActionCommand() == "Back")
            {
                frame.dispose();
            }
        }
    }
}
