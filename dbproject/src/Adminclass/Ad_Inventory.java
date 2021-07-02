package src.Adminclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Ad_Inventory extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel l1,l2,l3,l4;
    private JTextField t1, t2, t3;
    private JComboBox box;
    private JButton b1,b2;

    protected Ad_Inventory(){
        frame = new JFrame("Add Items");
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        l1 = new JLabel ("Product Category");
        l2 = new JLabel ("Product Name");
        l3 = new JLabel ("Enter Quantity");
        l4 = new JLabel ("Enter Price");

        l1.setBounds(38, 50, 120, 23);
        frame.getContentPane().add(l1);
        l2.setBounds(38, 90, 120, 23);
        frame.getContentPane().add(l2);
        l3.setBounds(38, 130, 120, 23);
        frame.getContentPane().add(l3);  
        l4.setBounds(38, 170, 120, 23);
        frame.getContentPane().add(l4);

        t1 = new JTextField(20);
        t1.setBounds(190, 90, 170, 23);
        frame.getContentPane().add(t1);
        t2 = new JTextField(20);
        t2.setBounds(190, 130, 170, 23);
        frame.getContentPane().add(t2);
        t3 = new JTextField(20);
        t3.setBounds(190, 170,170, 23);
        frame.getContentPane().add(t3);

        String[] catg = {"Electronics","Home_Appliances","Clothing","Sports"};
        box = new JComboBox(catg);
        box.addActionListener(this);
        box.setBounds(190, 50, 130, 30);
        frame.getContentPane().add(box);

        b1 = new JButton("SUBMIT");
        b1.setBounds(100, 350, 89, 30);
        frame.getContentPane().add(b1);
        b2 = new JButton("HOME");
        b2.setBounds(200, 350, 89, 30);
        frame.getContentPane().add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand()=="SUBMIT"){

            try {
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                PreparedStatement prep = con.prepareStatement("select count(*) from inventory");
                ResultSet queryResult = prep.executeQuery();
    
                int count = 0;
                while(queryResult.next()) {
                    count = queryResult.getInt(1);
                }

                PreparedStatement ps1 = con.prepareStatement("select id from admin where username = \'"+Ad_Login.getIndex()+"\'");
                ResultSet set = ps1.executeQuery();

                String q = "insert into inventory values(?,?,?)";
                PreparedStatement ps2 =  con.prepareStatement(q);
                if(set.next()){

                    ps2.setInt(1,++count);
                    ps2.setString(2,box.getSelectedItem().toString());
                    ps2.setInt(3,set.getInt(1));
                    ps2.executeQuery();
                }
                
                con.commit();
                con.close();
                JOptionPane.showMessageDialog(null,"Inventory added successfully");

            }   catch(java.sql.SQLException sql){
                    sql.printStackTrace();
            }   catch (Exception exc) {
                    exc.printStackTrace();
            }   
            finally{
                try{
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

                    PreparedStatement ps3 = con.prepareStatement("select count(*) from "+box.getSelectedItem().toString());
                    ResultSet rset = ps3.executeQuery();
        
                    int count1 = 0;
                    while(rset.next()) {
                        count1 = rset.getInt(1);
                    }

                    String str = "insert into "+box.getSelectedItem().toString()+ " values(?,?,?,?,?)";
                    PreparedStatement ps4 =  con.prepareStatement(str);

                    ps4.setInt(1,++count1);
                    ps4.setString(2,box.getSelectedItem().toString());
                    ps4.setString(3,t1.getText());
                    ps4.setString(4,t2.getText());
                    ps4.setString(5,t3.getText());
                    ps4.executeQuery();

                    con.commit();
                    con.close();

                }   catch(SQLException sql){sql.printStackTrace();}
                    catch(Exception ex){ex.printStackTrace();
            }

        }

        }
        if(e.getSource()==b2){
            frame.dispose();
        }
    }
}