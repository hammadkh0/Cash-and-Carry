package src.UserClass;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Signin extends JFrame {
    private JButton b1, b2;
    private JLabel l1,l2,l3,l6,l7;
    private JTextField t1,t2,t5,t6;
    private JPasswordField jpf;
    private JFrame frame;

    protected Signin(){
        user_signin();
    }
    private void user_signin(){

        frame = new JFrame("Signin");
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        l1 = new JLabel ("Enter Name");
        l2 = new JLabel ("Enter Username");
        l3 = new JLabel ("Enter Password");
        l6 = new JLabel ("Enter Email");
        l7 = new JLabel ("Enter Phone Number");

        l1.setBounds(38, 50, 120, 23);
        frame.getContentPane().add(l1);
        l2.setBounds(38, 90, 120, 23);
        frame.getContentPane().add(l2);
        l3.setBounds(38, 130, 120, 23);
        frame.getContentPane().add(l3);
       
        l6.setBounds(38, 170, 120, 23);
        frame.getContentPane().add(l6);
        l7.setBounds(38, 210, 120, 23);
        frame.getContentPane().add(l7);
              
        t1 = new JTextField(20);
        t1.setBounds(190, 50, 170, 23);
        frame.getContentPane().add(t1);
        t2 = new JTextField(20);
        t2.setBounds(190, 90, 170, 23);
        frame.getContentPane().add(t2);
        jpf = new JPasswordField(20);
        jpf.setBounds(190, 130,170, 23);
        frame.getContentPane().add(jpf);
        
        t5 = new JTextField(20);
        t5.setBounds(190, 170,170, 23);
        frame.getContentPane().add(t5);
        t6 = new JTextField(20);
        t6.setBounds(190, 210,170, 23);
        frame.getContentPane().add(t6);
        

        b1 = new JButton("Submit");
        b1.setBounds(100, 350, 89, 23);
        frame.getContentPane().add(b1);
        b2 = new JButton("Home");
        b2.setBounds(200, 350, 89, 23);
        frame.getContentPane().add(b2);
        
        b1.addActionListener(new MyListener());
        b2.addActionListener(new MyListener());
        frame.setVisible(true);
    }
    private class MyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand() == "Submit")
            {
                int check=0;
                if(!username(t2.getText())){
                    JOptionPane.showMessageDialog(null,"Username must have more than 5 characters");
                    t2.setText("");
                    check++;
                }

                if(!password(jpf.getText())){
                    JOptionPane.showMessageDialog(null,"Password must contain atleast 8 characters,An UpperCase, Lowercase and a Number");
                    jpf.setText("");
                    check++;
                }

                if(!email(t5.getText())){
                    JOptionPane.showMessageDialog(null,"Invalid Email");
                    t5.setText("");
                    check++;
                }

                if(!phone(t6.getText())){
                    JOptionPane.showMessageDialog(null,"Wrong phone number");
                    t6.setText("");
                    check++;
                }

                if(check==0){

                    try {
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                        PreparedStatement prep = con.prepareStatement("select count(*) from buyer");
                        ResultSet queryResult = prep.executeQuery();
    
                        int count = 0;
                        while(queryResult.next()) {
                            count = queryResult.getInt(1);
                        }
                        
                        
                        String str = "insert into Buyer(id,name,contact,username,password,email) values (?,?,?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(str);
                        ps.setInt(1, ++count);
                        ps.setString(2,t1.getText());
                        ps.setString(3,t6.getText());
                        ps.setString(4,t2.getText());
                        ps.setString(5,jpf.getText());
                        ps.setString(6,t5.getText());
                        ps.executeQuery();
                        con.commit();
                        con.close();

                        JOptionPane.showMessageDialog(null,"Added successfully");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                    frame.dispose();
                    StartU m = new StartU();
                }
            }
            if (e.getActionCommand() == "Home")
            {
                frame.dispose();
                StartU gf = new StartU();
            }
        }
    }
    private boolean username(String s){          //method to validate username
        Pattern p = Pattern.compile("^[A-Za-z0-9]\\w{5,11}$");        
        Matcher m = p.matcher(s);
        return(m.find()&& m.group().equals(s));
    }
    private boolean password(String s){          //method to validate password
        Pattern p = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
        Matcher m = p.matcher(s);
        return(m.find()&& m.group().equals(s));
    }
    private boolean email(String s){          //method to validate email
        Pattern p = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher m = p.matcher(s);
        return(m.find()&& m.group().equals(s));
    }
    private boolean phone(String s){          //method to validate contact number
        Pattern p = Pattern.compile("(0|92)?[3][0-4][0-9]{8}");
        Matcher m = p.matcher(s);
        return(m.find()&& m.group().equals(s));
    }

    private boolean validateJavaDate(String strDate){
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        if (strDate.trim().equals("")){
            return false;
        }
        else{
            java.util.Date javaDate = null;
            sdfrmt.setLenient(false);
            try
            {
                javaDate= sdfrmt.parse(strDate);
            }
            catch (ParseException e)
            {
                return false;
            }
            return true;
        }
    }
}
