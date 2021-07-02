package src.UserClass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Email extends JFrame {
    private JFrame frame;
    private JLabel l1,l2;
    private JTextField t1;
    private JButton b1,b2;

    protected Email(){
        frame = new JFrame("Edit Email");
        frame.setSize(400,300);
        frame.setLayout(null);

        l1 = new JLabel("Enter the new Email");
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
                if(!email(t1.getText())){
                    JOptionPane.showMessageDialog(null,"Invalid Email");
                    t1.setText("");
                }
                else{
                    try {
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                        PreparedStatement ps = con.prepareStatement("update buyer set email = \'"+t1.getText()+"\' where username = \'"+Login.getIndex()+"\'");
                        
                        ps.executeQuery();
                        
                        con.commit();
                        con.close();
    
                    } catch (SQLException sql) {
                        sql.printStackTrace();
                    } catch (Exception exc){
                        exc.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null,"Email Changed Successfully");
                    frame.dispose();
                }
            }
            if (e.getActionCommand() == "Back")
            {
                frame.dispose();
            }
        }
    }
    private boolean email(String s){          //method to validate email
        Pattern p = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher m = p.matcher(s);
        return(m.find()&& m.group().equals(s));
    }
}

