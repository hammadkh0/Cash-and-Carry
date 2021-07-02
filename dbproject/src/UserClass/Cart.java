package src.UserClass;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Classes.Items;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cart extends JFrame implements ActionListener{
    private JFrame frame;
    private JButton b1,b2,b3;
    private JTable table;
    private JLabel payLabel,sumLabel;
    private JPanel panel,panel2;
    private JComboBox box1;
    private ArrayList<Items> itemList = new ArrayList<>();
    DefaultTableModel tablemodle;

    private int payment,totalSum;
    
    public Cart() {
        panel = new JPanel();
        frame = new JFrame("View Invntory");
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        table = new JTable(tablemodle);
        JScrollPane scrollPane = new JScrollPane(table);
        itemList.clear();

        String query = "select * from Cart where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\')";
        showuser(query);
        
        table.setAutoCreateRowSorter(true);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);

        payment = getPayment();

        payLabel = new JLabel("Balance: "+payment);
        sumLabel = new JLabel("Total price: "+total());
        b1 = new JButton("Purchase");
        b2 = new JButton("Back");
        b3 = new JButton("Remove from Cart");
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(payLabel);
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        panel2.add(sumLabel);
        
        frame.add(panel2,BorderLayout.SOUTH);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        

        if(e.getActionCommand()=="Purchase"){
            int dialogbutton = JOptionPane.YES_NO_OPTION;
            int dialogeresult = JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogbutton);

            if(dialogeresult==JOptionPane.YES_OPTION){
                totalSum = total();
                if(payment - totalSum >= 0){

                try {
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

                    PreparedStatement ps = con.prepareStatement("insert into purchase_history values((select name from buyer where username = \'"+Login.getIndex()+"\'),(select sum(quantity) as ITEMS from(select quantity from cart)),"+totalSum+",(select CURRENT_DATE from DUAL), (select id from buyer where username = \'"+Login.getIndex()+"\'))");

                    // ps.setInt(3, totalSum);

                    ps.executeQuery();
                    
                    for (int i = 0; !(itemList.isEmpty()); )  {
                        String category = itemList.get(i).getCategory();
                        System.out.println("Category: "+category);
                        String name = itemList.get(i).getName();
                        System.out.println("name: "+name);

                        String q = "update "+ category+" SET quantity = (select ( "+category+".quantity - cart.quantity ) as SUB1 from "+ category+",cart " +
                        "where "+ category+".name = \'"+name+"\' AND cart.name = \'"+name+"\' ) where "+category+".name  = \'"+name+"\'";

                        PreparedStatement ps1 = con.prepareStatement(q);
                        ps1.executeQuery();

                        java.sql.PreparedStatement prep =  con.prepareStatement("DELETE from Cart where name = \'"+ name+"\'");
                        prep.executeQuery();

                        itemList.remove(i);
                    }
                    
                    
                    con.commit();
                    con.close();
                    showuser("select * from cart where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\')");
                    
                    
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Items Purchased Successfully");
                payment = payment - totalSum;
                payLabel.setText("Balance: "+payment);
                sumLabel.setText("Total price: "+total());

            }
            else{
                JOptionPane.showMessageDialog(null,"Insufficient Funds Remove Item(s) or add balance");
            }
            }
        }
    
        if(e.getActionCommand()=="Remove from Cart"){
            try {
                
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

                String q = "delete from cart where cart.name = \'"+table.getModel().getValueAt(table.getSelectedRow(),1).toString()+"\'";

                PreparedStatement ps1 = con.prepareStatement(q);
                ps1.executeQuery();

                itemList.remove(table.getSelectedRow());
                con.commit();
                con.close();

                for (int i = itemList.size()-1; i >= 0; i--) {
                    tablemodle.removeRow(i);
                }
                itemList.clear();

                showuser("select * from cart where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\')");
                sumLabel.setText("Total price: "+total());

        }   catch(SQLException sq){sq.printStackTrace();}
            catch(Exception exc){exc.printStackTrace();}
    } 
        if(e.getActionCommand()=="Back"){
            frame.dispose();
        }
    }


    private void showuser(String str){
        
        itemList = itemList(str);
        createTable();
    }

    private ArrayList<Items> itemList(String str){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            java.sql.PreparedStatement prep =  con.prepareStatement(str);
            
            ResultSet rst = prep.executeQuery();
            Items itm;
            while(rst.next()){
                itm = new Items(rst.getInt(1),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getString(2));
                itemList.add(itm);
            }

        } catch (Exception e) {e.printStackTrace();}

        return itemList;
    } 

    private void createTable(){
        String[] columnNames = {"BUYER_ID","Name", "Quantity", "Price","Category"};
        Object data[][] = new Object[itemList.size()][5];

        for (int i = 0; i < itemList.size(); i++) {
            data[i][0] = itemList.get(i).getId();
            data[i][1] = itemList.get(i).getName();
            data[i][2] = itemList.get(i).getQuantity();
            data[i][3] = itemList.get(i).getPrice();
            data[i][4] = itemList.get(i).getCategory();
        }

        tablemodle = new DefaultTableModel(data,columnNames);
        table.setModel(tablemodle);
    }

    private int getPayment(){
        int balance = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

            java.sql.PreparedStatement prep =  con.prepareStatement("select balance from payment where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\')");
            
            ResultSet rst = prep.executeQuery();
            if(rst.next()){
                System.out.println(rst.getInt(1));
                balance =  rst.getInt(1);
            }
        }   catch (SQLException sql) {sql.printStackTrace();}
            catch (Exception exc){exc.printStackTrace();}
        return balance;
    }

    private int total(){
        int sum = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

            java.sql.PreparedStatement prep =  con.prepareStatement("select sum(RowSum) as TOTAL from( select cart.quantity * cart.price as RowSum from cart where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\'))");
            
            ResultSet rst = prep.executeQuery();
            if(rst.next()){
                sum = rst.getInt(1);
                System.out.println(rst.getInt(1));
            }

        }   catch (SQLException sql) {sql.printStackTrace();}
            catch (Exception exc){exc.printStackTrace();}

        return sum;
    }
}
