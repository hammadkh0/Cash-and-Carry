package src.Adminclass;

import javax.swing.*;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ad_Login extends  JFrame {
    private JFrame frame;
    private JLabel l1,l2;
    private JTextField t1;
    private JPasswordField jpf;
    private JButton b1,b2;
    
    private static String index;

    protected Ad_Login(){
        index="";
        Login();
    }
    protected static String getIndex(){
        return index;
    }

    private void Login(){
        
        frame = new JFrame("Login");
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        l1 = new JLabel ("Enter Username");
        l2 = new JLabel ("Enter Password");

        l1.setBounds(38, 97, 120, 23);
        frame.getContentPane().add(l1);
        l2.setBounds(38, 187, 120, 23);
        frame.getContentPane().add(l2);

        t1 = new JTextField(20);
        t1.setBounds(194, 98, 154, 23);
        frame.getContentPane().add(t1);

        jpf = new JPasswordField(20);
        jpf.setBounds(194, 188, 154, 23);
        frame.getContentPane().add(jpf);

        b1 = new JButton("LOGIN");
        b1.setBounds(100, 350, 89, 30);
        b2 = new JButton("HOME");
        b2.setBounds(200, 350, 89, 30);
        frame.getContentPane().add(b1);
        frame.getContentPane().add(b2);
        
        b1.addActionListener(new MyListener());
        b2.addActionListener(new MyListener());
        frame.setVisible(true);
    }
    private class MyListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand()=="LOGIN"){
                if(checkLogin()){

                    frame.dispose();
                    AdminMain ms = new AdminMain();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Username or Passowrd");
                   t1.setText("");
                   jpf.setText("");
                }
            }
            if(e.getActionCommand()=="HOME"){
                frame.dispose();
                Start st = new Start();
            }
        }
    }
    private boolean checkLogin(){
        
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            PreparedStatement ps = con.prepareStatement("select username, password from admin where username=? AND password=?");

            ps.setString(1, t1.getText());
            ps.setString(2, jpf.getText());

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("USERNAME: "+rs.getString(1));
                index = rs.getString(1);
                con.close();
                return true;
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception exc){
            exc.printStackTrace();
        }

        return false;
    }    
}
