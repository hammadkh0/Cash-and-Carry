package src.UserClass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Payment extends JFrame{
    private JFrame frame;
    private JLabel l1,l2;
    private JTextField t1,t2;
    private JButton b1,b2;

    protected Payment(){
        frame = new JFrame("Payment");
        frame.setSize(400,300); 
        frame.setLayout(null);

        l1 = new JLabel ("Enter Balance");
        l2 = new JLabel ("Enter Account Number");

        l1.setBounds(38, 50, 120, 23);
        frame.getContentPane().add(l1);
        l2.setBounds(38, 90, 120, 23);
        frame.getContentPane().add(l2);

        t1 = new JTextField(20);
        t1.setBounds(190, 50, 170, 23);
        frame.getContentPane().add(t1);
        t2 = new JTextField(20);
        t2.setBounds(190, 90, 170, 23);
        frame.getContentPane().add(t2);

        b1= new JButton("Add");
        b1.setBounds(100, 170, 90, 30);
        b2= new JButton("Back");
        b2.setBounds(200, 170, 90, 30);
        
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
            
            if (e.getActionCommand() == "Add"){
                    
                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

                    PreparedStatement ps1 = con.prepareStatement("select id from buyer where username = \'"+Login.getIndex()+"\'");
                    ResultSet rs = ps1.executeQuery();

                    String q = "insert into Payment(balance,bank_account,buyer_id) values(?,?,?)";
                    java.sql.PreparedStatement prep =  con.prepareStatement(q);
                    
                    if(rs.next()){
                        
                        System.out.println(rs.getInt(1));

                        prep.setInt(1, Integer.parseInt(t1.getText()));
                        prep.setString(2, t2.getText());
                        prep.setInt(3, rs.getInt(1));

                        prep.executeQuery();
                    }

                    JOptionPane.showMessageDialog(null,"Payment added successfully");
                    con.commit();
                    con.close();
                    frame.dispose();

                } catch (Exception ex) {ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Infromation already added");}
            }

            if (e.getActionCommand() == "Back")
            {
                frame.dispose();
            }
        }
    }
}


