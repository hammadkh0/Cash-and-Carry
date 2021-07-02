package src.Adminclass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ad_Password extends JFrame {
    private JFrame frame;
    private JLabel l1,l2;
    private JPasswordField t1,t2;
    private JButton b1,b2;

    protected Ad_Password(){
        frame = new JFrame("Edit Password");
        frame.setSize(400,300);
        frame.setLayout(null);

        l1 = new JLabel("Enter the current Password");
        l1.setBounds(30,50,160,30);
        l2 = new JLabel("Enter the new Password");
        l2.setBounds(30,100,150,30);

        t1 = new JPasswordField(20);
        t1.setBounds(200,50,150,30);
        t2 = new JPasswordField(20);
        t2.setBounds(200,100,150,30);
        
        frame.getContentPane().add(l1);
        frame.getContentPane().add(t1);
        frame.getContentPane().add(l2);
        frame.getContentPane().add(t2);

        b1= new JButton("Submit");
        b1.setBounds(110, 200, 90, 30);
        b2= new JButton("Back");
        b2.setBounds(230, 200, 90, 30);
        
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
                int check=0;
                try {
                    
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                    PreparedStatement ps = con.prepareStatement("select password from admin where username = \'"+Ad_Login.getIndex()+"\' AND password =  \'"+t1.getText()+"\'");
                    
                    ps.executeQuery();
                    ResultSet rs = ps.executeQuery();

                    if(rs.next()){
                        System.out.println("Password: "+rs.getString(1));
                    //     con.close();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Incorrect Current Password");
                        t1.setText("");
                        t2.setText("");
                        check++;
                    }

                    if(!password(t2.getText())){
                        JOptionPane.showMessageDialog(null,"Password must contain atleast 8 characters,An UpperCase, Lowercase and a Number");
                        t2.setText("");
                        check++;
                    }
    
                    if(check==0){
                        // Updates.editPassword(t2.getText(),Login.getIndex());
                        PreparedStatement ps1 = con.prepareStatement("update admin set password =  \'"+t2.getText()+"\' where username = \'"+Ad_Login.getIndex()+"\'");
                        ps1.executeQuery();

                        JOptionPane.showMessageDialog(null,"Passowrd Changed Successfully");
                        con.commit();
                        con.close();
                        frame.dispose();
                    }

                } catch (SQLException sql) {sql.printStackTrace();}

            }
            if (e.getActionCommand() == "Back")
            {
                frame.dispose();
            }
        }
    }
    private boolean password(String s){          //method to validate password
        Pattern p = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
        Matcher m = p.matcher(s);
        return(m.find()&& m.group().equals(s));
    }
}
