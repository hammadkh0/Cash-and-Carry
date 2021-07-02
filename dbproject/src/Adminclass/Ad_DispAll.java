package src.Adminclass;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import src.Classes.Person;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Ad_DispAll extends JFrame{

    private JFrame frame;
    private JButton b1,b2,b3;
    private JTable table = new JTable();
    private JPanel panel1,panel2;
    DefaultTableModel tablemodle;
    JScrollPane scrollPane;
    
    protected Ad_DispAll(){
        init();
    }


    private ArrayList<Person> personList(){
        ArrayList<Person> personList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "cash", "cash");
            String q = "select * from admin";
            java.sql.PreparedStatement prep =  con.prepareStatement(q);
            
            ResultSet rst = prep.executeQuery();
            Person pers;
            while(rst.next()){
                pers = new Person(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
                personList.add(pers);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return personList;
    } 

    private class MyListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            if (e.getActionCommand() == "Search"){
                // String name = JOptionPane.showInputDialog(null, "Enter the Name");
                // int check = 0;
                // ArrayList<Accounts> acc = Accounts.ReadFile(".\\Adminclass\\Admin Accounts");
                // for (int i = 0; i < acc.size(); i++) {
                //     if(acc.get(i).getName().equals(name)){
                //         JOptionPane.showMessageDialog(null,"Account exists");
                //         check++;
                //     }
                // }
                // if(check==0){
                //     JOptionPane.showMessageDialog(null,"Account doesnot exist");
                // }
            }

            if (e.getActionCommand() == "Delete") 
            {
                
                // int dialogbutton = JOptionPane.YES_NO_OPTION;
                // int dialogeresult = JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogbutton);
                // if(dialogeresult==JOptionPane.YES_OPTION){
                //     System.out.println(table.getSelectedRow());
                //     System.out.println(Ad_Login.getIndex());
                //     if(table.getSelectedRow()!=Ad_Login.getIndex()){
                //         list.remove(list.get(table.getSelectedRow()));
                //         tablemodle.removeRow(table.getSelectedRow());
                //         try {
                //             ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(".\\Adminclass\\Admin Accounts"));
                //             output.writeObject(list);
                //             output.close();
                
                //         } catch (IOException ie) {
                //             ie.printStackTrace();
                //         }
                //     }
                //     else{
                //         JOptionPane.showMessageDialog(null, "Cannot delete your own account");
                //     }
                // }
                
            }
            if(e.getActionCommand()=="Back"){
                frame.dispose();
            }
        }
    }
    private void init(){
        frame = new JFrame("Display Admins");
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        showuser();
        table = new JTable(tablemodle);
        scrollPane = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);

        panel1 = new JPanel();      
        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane,BorderLayout.CENTER);
        frame.add(panel1,BorderLayout.CENTER);

        b1 = new JButton("Search");
        b2 = new JButton("Delete");
        b3 = new JButton("Back");
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.add(b1);
        panel2.add(b2);
        panel2.add(b3);
        
        frame.add(panel2,BorderLayout.SOUTH);
        b1.addActionListener(new MyListener());
        b2.addActionListener(new MyListener());
        b3.addActionListener(new MyListener());
        frame.setVisible(true);

    }

    private void showuser(){
        
        ArrayList<Person> list = personList();
        
        String[] columnNames = {"ID","Name", "Contact", "Email"};
        Object data[][] = new Object[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getId();
            data[i][1] = list.get(i).getName();
            data[i][2] = list.get(i).getContact();
            data[i][3] = list.get(i).getEmail();
        }

        tablemodle = new DefaultTableModel(data,columnNames);

    }
}

