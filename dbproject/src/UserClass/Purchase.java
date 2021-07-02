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
import java.sql.ResultSet;
import java.sql.SQLException;

public class Purchase extends JFrame implements ActionListener {
    private JFrame frame;
    private JButton b1,b2;
    private JTable table;
    private JPanel panel,panel2;
    private JComboBox box1;
    DefaultTableModel tablemodle;

    ArrayList<Items> itemList = new ArrayList<>();

    public Purchase() {
        panel = new JPanel();
        frame = new JFrame("View Invntory");
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());
        
        table = new JTable(tablemodle);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);

        String[] catg = {"----","All","Electronics","Home Appliances","Clothing","Sports"};
        box1 = new JComboBox(catg);

        box1.addActionListener(this);
        
        b1 = new JButton("Add to Cart");
        b2 = new JButton("Back");
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(box1);
        panel2.add(b1);
        panel2.add(b2);
        
        frame.add(panel2,BorderLayout.SOUTH);
        b1.addActionListener(this);
        b2.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand()=="Add to Cart"){
            if(itemList.get(table.getSelectedRow()).getQuantity()!=0) {

                // ArrayList<Accounts> l = Accounts.ReadFile(".\\UserClass\\User Accounts");
                String op = JOptionPane.showInputDialog(null,"Enter the quantity");
                int quantity = Integer.parseInt(op);

                if(itemList.get(table.getSelectedRow()).getQuantity() - quantity >= 0){

                try{

                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
                    java.sql.PreparedStatement prep =  con.prepareStatement("Insert into cart values (?,?,?,?,?)");

                    java.sql.PreparedStatement ps = con.prepareStatement("select id from buyer where username = \'"+Login.getIndex()+"\'");
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){

                        prep.setInt(1,rs.getInt(1));    //buyer_id
                        prep.setString(2,table.getModel().getValueAt(table.getSelectedRow(),4).toString()); //inventory.category
                        prep.setString(3,table.getModel().getValueAt(table.getSelectedRow(),1).toString()); //name
                        prep.setInt(4,quantity); //quantity
                        prep.setInt(5,Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),3).toString())); //price
                        prep.executeQuery();
                    }
                    con.commit();
                    con.close();
                    }   catch(SQLException sq){sq.printStackTrace();}   
                    catch(Exception ex){ex.printStackTrace();}
                
                    JOptionPane.showMessageDialog(null,"Item Added Successfully");
                }   
                else{
                    JOptionPane.showMessageDialog(null,"Cannot order more than available Stock");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Out of Stock");
            }
        }

        if(e.getActionCommand()=="Back"){
            frame.dispose();
        }


        if(box1.getSelectedItem()=="All"){
            for (int i = itemList.size()-1; i >= 0; i--) {
                tablemodle.removeRow(i);
            }
            itemList.clear();
        
            try{

                String q = "select home_appliances.id,home_appliances.name,home_appliances.quantity,home_appliances.price,home_appliances.inventory_category as category "+
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
                "where inventory.category = sports.inventory_category ";

                showuser(q);

            }   catch(Exception ex){}
        }
        

        if((box1.getSelectedItem() == "Electronics")) {
            for (int i = itemList.size()-1; i >= 0; i--) {
                tablemodle.removeRow(i);
            }
            
            itemList.clear();
            try{
                String q = "SELECT electronics.id,electronics.name,electronics.quantity,electronics.price,electronics.inventory_category FROM inventory right JOIN electronics ON inventory.category= electronics.inventory_category ";
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

                String q = "SELECT home_appliances.id, home_appliances.name, home_appliances.quantity, home_appliances.price, home_appliances.inventory_category FROM inventory right JOIN home_appliances ON inventory.category= home_appliances.inventory_category";
                showuser(q);
                
            }   catch(Exception ex){}
        }
        if(box1.getSelectedItem()=="Clothing"){

            for (int i = itemList.size()-1; i >= 0; i--) {
                tablemodle.removeRow(i);
            }
            itemList.clear();
            try{

                String q = "SELECT clothing.id, clothing.name, clothing.quantity, clothing.price, clothing.inventory_category FROM inventory right JOIN clothing ON inventory.category= clothing.inventory_category";
                showuser(q);

            }   catch(Exception ex){}
        }
        if(box1.getSelectedItem()=="Sports"){
            for (int i = itemList.size()-1; i >= 0; i--) {
                tablemodle.removeRow(i);
            }
            itemList.clear();
            try{

                String q = "select sports.id, sports.name ,sports.quantity,sports.price,sports.inventory_category "+
                "from inventory "+
                "RIGHT JOIN sports ON inventory.category = sports.inventory_category ";
                showuser(q);

            }   catch(Exception ex){}
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
                itm = new Items(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getInt(4),rst.getString(5));
                itemList.add(itm);
            }

        } catch (Exception e) {e.printStackTrace();}

        return itemList;
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
}