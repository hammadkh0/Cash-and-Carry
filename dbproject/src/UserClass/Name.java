package src.UserClass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Name extends JFrame {
    private JFrame frame;
    private JLabel l1;
    private JTextField t1;
    private JButton b1,b2;

    protected Name(){
        frame = new JFrame("Edit Name");
        frame.setSize(400,300);
        frame.setLayout(null);

        l1 = new JLabel("Enter the new Name");
        l1.setBounds(10,50,150,30);
        t1 = new JTextField(20);
        t1.setBounds(200,50,150,20);
        frame.getContentPane().add(l1);
        frame.getContentPane().add(t1);

        b1= new JButton("Submit");
        b1.setBounds(110, 150, 90, 30);
        b2= new JButton("Back");
        b2.setBounds(230, 150, 90, 30);
        
        b1.setFocusable(false);
        b2.setFocusable(false);

        frame.getContentPane().add(b1);
        frame.getContentPane().add(b2);

        MyActionListener m = new MyActionListener();
        
        b1.addActionListener(m);
        b2.addActionListener(m);

        frame.setVisible(true);
    }

    private class MyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand() == "Submit")
            {
                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                    PreparedStatement ps = con.prepareStatement("update buyer set name = \'"+t1.getText()+"\' where username = \'"+Login.getIndex()+"\'");
                    
                    ps.executeQuery();
                    
                    con.commit();
                    con.close();

                } catch (SQLException sql) {
                    sql.printStackTrace();
                } catch (Exception exc){
                    exc.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Name Changed Successfully");
                frame.dispose();
            }
            if (e.getActionCommand() == "Back")
            {
                frame.dispose();
            }
        }
    }
}
