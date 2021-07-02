package src.UserClass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Display extends JFrame {
    private JFrame frame;
    private JLabel l1,l2,l3,l4,l5,l6;
    private JButton b1,b2;

    protected Display(){
        frame = new JFrame("Display");
        frame.setSize(400,300); 
        frame.setLayout(null);

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            String q = "select * from buyer where username = \'"+Login.getIndex()+"\'";
            java.sql.PreparedStatement prep =  con.prepareStatement(q);
            
            ResultSet rst = prep.executeQuery();
           
            while(rst.next()){
                l1 = new JLabel("ID:        "+rst.getInt(1));
                l1.setBounds(30,20,350,30);
                l2 = new JLabel("Name       "+rst.getString(2));
                l2.setBounds(30,70,350,30);
                l3 = new JLabel("Contact        "+rst.getString(3));
                l3.setBounds(30,120,350,30);
                l4 = new JLabel("username       "+rst.getString(4));
                l4.setBounds(30,170,350,30);
                l5 = new JLabel("password       "+rst.getString(5));
                l5.setBounds(30,220,350,30);
                l6 = new JLabel("Email      "+rst.getString(6));
                l6.setBounds(30,270,350,30);

                frame.getContentPane().add(l1);
                frame.getContentPane().add(l2);
                frame.getContentPane().add(l3);
                frame.getContentPane().add(l4);
                frame.getContentPane().add(l5);
                frame.getContentPane().add(l6);

                b2= new JButton("Back");
                b2.setBounds(150, 320, 90, 30);
                
                frame.getContentPane().add(b2);

                MyActionListener m = new MyActionListener();
                
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
            if (e.getActionCommand() == "Back")
            {
                frame.dispose();
            }
        }
    }
}

