package src.Adminclass;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import src.Classes.Items;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_Invt extends JFrame {
    private JFrame frame;
    private JButton b1, b2,b3,b4;
    private JTable table;
    private JPanel panel,panel2;
    private JComboBox box1,box2;
    ArrayList<Items> list;
    DefaultTableModel tablemodle;
    ArrayList<Items> itemList = new ArrayList<>();

    public View_Invt() {
        panel = new JPanel();
        frame = new JFrame("View Invntory");
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // table = new JTable();
        table = new JTable(tablemodle);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);

        String[] catg = {"----","All","Electronics","Home Appliances","Clothing","Sports"};
        String[] filterCatg = {"----","0-2000","2000-10000","10000-50000","50000-300000"};
        box1 = new JComboBox(catg);
        box2 = new JComboBox(filterCatg);
        box1.addActionListener(new MyListener());
        box2.addActionListener(new MyListener());

        b1 = new JButton("Update Quantity");
        b3 = new JButton("Update Price");
        b4 = new JButton("Remove Item");
        b2 = new JButton("Back");
        b1.addActionListener(new ButtonListener());
        b3.addActionListener(new ButtonListener());
        b2.addActionListener(new ButtonListener());
        b4.addActionListener(new ButtonListener());

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(box1);
        panel2.add(box2);
        panel2.add(b1);
        panel2.add(b3);
        panel2.add(b2);
        panel2.add(b4);
        frame.add(panel2,BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    private class MyListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(box1.getSelectedItem()=="All"){
                for (int i = itemList.size()-1; i >= 0; i--) {
                    tablemodle.removeRow(i);
                }
                if(box2.getSelectedItem()=="----"){
                    itemList.clear();

                try{

                    String q =  "select id,name,quantity,price,category from ("+
                    "select home_appliances.id,home_appliances.name,home_appliances.quantity,home_appliances.price,home_appliances.inventory_category as category "+
                    "from Inventory,home_appliances "+
                    "where inventory.category = home_appliances.inventory_category " 
                    
                    +"UNION "+
                    
                    "select electronics.id, electronics.name ,electronics.quantity,electronics.price,electronics.inventory_category as category from inventory,electronics "+
                    "where inventory.category = electronics.inventory_category "+
                    
                    "UNION "+
                    
                    "select clothing.id, clothing.name ,clothing.quantity,clothing.price,clothing.inventory_category as category "+
                    "from inventory,clothing "+
                    "where inventory.category = clothing.inventory_category "+
                    
                    "UNION "+
                    "select sports.id, sports.name ,sports.quantity,sports.price,sports.inventory_category as category "+
                    "from inventory,sports "+
                    "where inventory.category = sports.inventory_category ) ";

                    showuser(q);

                }   catch(Exception ex){}
            }
                if(box2.getSelectedItem()=="0-2000"){
                    itemList.clear();

                try{

                    String q = "select id,name,quantity,price,category from ("+
                    
                    "select home_appliances.id,home_appliances.name,home_appliances.quantity,home_appliances.price,home_appliances.inventory_category as category "+
                    "from Inventory,home_appliances "+
                    "where inventory.category = home_appliances.inventory_category " 
                    
                    +"UNION "+
                    
                    "select electronics.id, electronics.name ,electronics.quantity,electronics.price,electronics.inventory_category as category from inventory,electronics "+
                    "where inventory.category = electronics.inventory_category "+
                    
                    "UNION "+
                    
                    "select clothing.id, clothing.name ,clothing.quantity,clothing.price,clothing.inventory_category as category "+
                    "from inventory,clothing "+
                    "where inventory.category = clothing.inventory_category "+
                    
                    "UNION "+
                    "select sports.id, sports.name ,sports.quantity,sports.price,sports.inventory_category as category "+
                    "from inventory,sports "+
                    "where inventory.category = sports.inventory_category ) "+

                    "where price > 0 AND price <2000";

                    showuser(q);

                }   catch(Exception ex){}
            }
                if(box2.getSelectedItem()=="2000-10000"){
                    itemList.clear();

                try{

                    String q = "select id,name,quantity,price,category from ("+
                    
                    "select home_appliances.id,home_appliances.name,home_appliances.quantity,home_appliances.price,home_appliances.inventory_category as category "+
                    "from Inventory,home_appliances "+
                    "where inventory.category = home_appliances.inventory_category " 
                    
                    +"UNION "+
                    
                    "select electronics.id, electronics.name ,electronics.quantity,electronics.price,electronics.inventory_category as category from inventory,electronics "+
                    "where inventory.category = electronics.inventory_category "+
                    
                    "UNION "+
                    
                    "select clothing.id, clothing.name ,clothing.quantity,clothing.price,clothing.inventory_category as category "+
                    "from inventory,clothing "+
                    "where inventory.category = clothing.inventory_category "+
                    
                    "UNION "+
                    "select sports.id, sports.name ,sports.quantity,sports.price,sports.inventory_category as category "+
                    "from inventory,sports "+
                    "where inventory.category = sports.inventory_category ) "+

                    "where price > 2000 AND price < 10000";

                    showuser(q);

                }   catch(Exception ex){}
            }
                if(box2.getSelectedItem()=="10000-50000"){
                    itemList.clear();

                try{

                    String q = "select id,name,quantity,price,category from ("+
                    
                    "select home_appliances.id,home_appliances.name,home_appliances.quantity,home_appliances.price,home_appliances.inventory_category as category "+
                    "from Inventory,home_appliances "+
                    "where inventory.category = home_appliances.inventory_category " 
                    
                    +"UNION "+
                    
                    "select electronics.id, electronics.name ,electronics.quantity,electronics.price,electronics.inventory_category as category from inventory,electronics "+
                    "where inventory.category = electronics.inventory_category "+
                    
                    "UNION "+
                    
                    "select clothing.id, clothing.name ,clothing.quantity,clothing.price,clothing.inventory_category as category "+
                    "from inventory,clothing "+
                    "where inventory.category = clothing.inventory_category "+
                    
                    "UNION "+
                    "select sports.id, sports.name ,sports.quantity,sports.price,sports.inventory_category as category "+
                    "from inventory,sports "+
                    "where inventory.category = sports.inventory_category ) "+

                    "where price > 10000 AND price < 50000";

                    showuser(q);

                }   catch(Exception ex){}
            }
                if(box2.getSelectedItem()=="50000-300000"){
                itemList.clear();

                try{

                    String q = "select id,name,quantity,price,category from ("+
                    
                    "select home_appliances.id,home_appliances.name,home_appliances.quantity,home_appliances.price,home_appliances.inventory_category as category "+
                    "from Inventory,home_appliances "+
                    "where inventory.category = home_appliances.inventory_category " 
                    
                    +"UNION "+
                    
                    "select electronics.id, electronics.name ,electronics.quantity,electronics.price,electronics.inventory_category as category from inventory,electronics "+
                    "where inventory.category = electronics.inventory_category "+
                    
                    "UNION "+
                    
                    "select clothing.id, clothing.name ,clothing.quantity,clothing.price,clothing.inventory_category as category "+
                    "from inventory,clothing "+
                    "where inventory.category = clothing.inventory_category "+
                    
                    "UNION "+
                    "select sports.id, sports.name ,sports.quantity,sports.price,sports.inventory_category as category "+
                    "from inventory,sports "+
                    "where inventory.category = sports.inventory_category ) "+

                    "where price > 50000 AND price < 300000";

                    showuser(q);

                }   catch(Exception ex){}
            }
            }

            if((box1.getSelectedItem() == "Electronics")) {
                for (int i = itemList.size()-1; i >= 0; i--) {
                    tablemodle.removeRow(i);
                }
                itemList.clear();
                
                try{

                    String q = "SELECT electronics.id,electronics.name,electronics.quantity,electronics.price, electronics.inventory_category FROM inventory right JOIN electronics ON inventory.category= electronics.inventory_category ";
                    showuser(q);
                }   catch(Exception ex){}

            }
            if(box1.getSelectedItem()=="Home Appliances"){
                itemList.clear();
                for (int i = itemList.size()-1; i >= 0; i--) {
                    tablemodle.removeRow(i);
                }
                itemList.clear();

                try{

                    String q = "SELECT home_appliances.id, home_appliances.name, home_appliances.quantity, home_appliances.price,  home_appliances.inventory_category FROM inventory right JOIN home_appliances ON inventory.category= home_appliances.inventory_category";
                    showuser(q);

                }   catch(Exception ex){}
            }
            if(box1.getSelectedItem()=="Clothing"){

                for (int i = itemList.size()-1; i >= 0; i--) {
                    tablemodle.removeRow(i);
                }
                itemList.clear();

                try{

                    String q = "SELECT clothing.id, clothing.name, clothing.quantity, clothing.price,clothing.inventory_category FROM inventory right JOIN clothing ON inventory.category= clothing.inventory_category ";
                    showuser(q);

                }   catch(Exception ex){}
            }
            if(box1.getSelectedItem()=="Sports"){
                for (int i = itemList.size()-1; i >= 0; i--) {
                    tablemodle.removeRow(i);
                }
                itemList.clear();

                try{

                    String q = "SELECT sports.id, sports.name, sports.quantity, sports.price, sports.inventory_category FROM inventory right JOIN sports ON inventory.category= sports.inventory_category";
                    showuser(q);

                }   catch(Exception ex){}
            }
        }
    }

    private ArrayList<Items> itemList(String str){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            java.sql.PreparedStatement prep =  con.prepareStatement(str);
            
            ResultSet rst = prep.executeQuery();
            Items itm;
            while(rst.next()){
                itm = new Items(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getInt(4),rst.getString(5));
                itemList.add(itm);
            }

        } catch (Exception e) {e.printStackTrace();}

        return itemList;
    } 

    private void showuser(String str){
        
        itemList = itemList(str);
        createTable();
        
    }

    private void createTable(){
        String[] columnNames = {"ID","Name", "Quantity", "Price","Category"};
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

    private void refresh(){
        for (int i = itemList.size()-1; i >= 0; i--) {
            tablemodle.removeRow(i);
        }
        createTable();
    }


    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand()=="Update Quantity"){

                try {
                    int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                    java.sql.PreparedStatement prep =  con.prepareStatement("select * from "+table.getModel().getValueAt(table.getSelectedRow(),4).toString()
                    +" WHERE id = "+ id);
                    
                    ResultSet rst = prep.executeQuery();
                    while(rst.next()){
                        
                        int quantity=0;
                        do{
                            String op = JOptionPane.showInputDialog(null,"Enter the quantity");
                            quantity = Integer.parseInt(op);
                        } while(quantity<0);

                        PreparedStatement ps = con.prepareStatement("UPDATE "+table.getModel().getValueAt(table.getSelectedRow(),4).toString()+
                        " SET quantity = "+quantity+" WHERE id = "+id);

                        ps.executeQuery();
                    }
                    con.commit();
                    con.close();
                    refresh();
        
                } catch (Exception ex) {ex.printStackTrace();}

            }

            if(e.getActionCommand()=="Update Price"){

                try {
                    int id = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
                    String tableName = table.getModel().getValueAt(table.getSelectedRow(),4).toString();

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                    java.sql.PreparedStatement prep =  con.prepareStatement("select * from "+tableName+" WHERE id = "+ id);
                    
                    ResultSet rst = prep.executeQuery();
                    while(rst.next()){
                        System.out.println(rst.getInt("ID")+" " +rst.getString(2)+" "+rst.getString(3)+" "+rst.getString(4)+" "+rst.getString(5));
                        

                        int price=0;
                        do{
                            String op = JOptionPane.showInputDialog(null,"Enter the new Price");
                            price = Integer.parseInt(op);
                        } while(price<0);

                        PreparedStatement ps = con.prepareStatement("UPDATE "+tableName+" SET price = "+price+" WHERE id = "+id);

                        ps.executeQuery();
                    }
                    con.commit();
                    con.close();
                    refresh();
        
                } catch (Exception ex) {ex.printStackTrace();}

            }

            if(e.getActionCommand() == "Remove Item"){
                try {
                    String category = table.getModel().getValueAt(table.getSelectedRow(),4).toString();
                    String name = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
    
                    System.out.println("Name: "+name);
                    System.out.println("category: "+category);

                    String q = "delete from "+ category +" where "+category+".name = \'"+name+"\'";
    
                    PreparedStatement ps1 = con.prepareStatement(q);
                    ps1.executeQuery();
    
                    // itemList.remove(table.getSelectedRow());
                    itemList.clear();
                    removeCart(name,category);
                    // tablemodle.removeRow(table.getSelectedRow());


                    con.commit();
                    con.close();
                    refresh();
    
            }   catch(SQLException sq){sq.printStackTrace();}
                catch(Exception exc){exc.printStackTrace();}
            }

            if(e.getActionCommand()=="Back"){
                frame.dispose();
            }
        }
    }

    private void removeCart(String name, String category){
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            PreparedStatement ps1 = con.prepareStatement("delete from cart where cart.name = \'"+name+"\' AND inventory_category = \'"+category+"\'");
            ps1.executeQuery();
            con.commit();
            con.close();

            System.out.println("cart item removed");

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
    }
}