package src.UserClass;


import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Classes.History;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseHistory extends JFrame{
    private JFrame frame;
    private JButton b2;
    private JTable table;
    private JPanel panel,panel2;
    private JComboBox box1;
    ArrayList<History> history = new ArrayList<>();
    DefaultTableModel tablemodle;


    protected PurchaseHistory() {
        panel = new JPanel();
        frame = new JFrame("Purchae History");
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        table = new JTable(tablemodle);
        JScrollPane scrollPane = new JScrollPane(table);
        history.clear();

        showHistory();

        table.setAutoCreateRowSorter(true);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.CENTER);

        
        b2 = new JButton("Back");
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(b2);
        
        frame.add(panel2,BorderLayout.SOUTH);

        MyActionListener ms = new MyActionListener();

        b2.addActionListener(ms);
        frame.setVisible(true);
    }
    
    private class MyActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand()=="Back"){
            frame.dispose();
        }
    }
    }

    private void showHistory(){
        
        history = history();
        createTable();
    }

    private ArrayList<History> history(){

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");

            String str = "select * from purchase_history where buyer_id = (select id from buyer where username = \'"+Login.getIndex()+"\')";
            java.sql.PreparedStatement prep =  con.prepareStatement(str);
            ResultSet rst = prep.executeQuery();


            History his;
            while(rst.next()){
                his = new History(rst.getString(1),rst.getInt(2),rst.getInt(3),rst.getDate(4),rst.getInt(5));
                history.add(his);
            }

        }   catch (SQLException sq){sq.printStackTrace();}
            catch (Exception e) {e.printStackTrace();}

        return history;
    } 

    private void createTable(){
        String[] columnNames = {"Name","Total Items", "Total Price", "Date","BuyerId"};
        Object data[][] = new Object[history.size()][5];

        for (int i = 0; i < history.size(); i++) {
            data[i][0] = history.get(i).getName();
            data[i][1] = history.get(i).getTotalItems();
            data[i][2] = history.get(i).getTotalBill();
            data[i][3] = history.get(i).getDate();
            data[i][4] = history.get(i).getId();
        }

        tablemodle = new DefaultTableModel(data,columnNames);
        table.setModel(tablemodle);
    }
}