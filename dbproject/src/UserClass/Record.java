package src.UserClass;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Record extends JFrame{

    private JButton b1, b2, b3;
    private JLabel l;
    private JFrame frame;
    private JMenuBar menubar;
    private JMenu signout, back;

    protected Record()
    {
        frame = new JFrame("Actions");

        menubar = new JMenuBar();
        signout = new JMenu("Signout");
        back = new JMenu("Back");
        l = new JLabel(Login.getIndex()+"  ");

        menubar.add(back);
        menubar.add(signout);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(l);

        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setJMenuBar(menubar);

        b1= new JButton("Edit your Name");
        b2= new JButton("Edit your Password");
        b3= new JButton("Edit your Email");

        b1.setBounds(10, 90, 350, 30);
        b2.setBounds(10, 130, 350, 30);
        b3.setBounds(10, 170, 350, 30);

        b1.setFocusable(false);
        b2.setFocusable(false);
        b3.setFocusable(false);

        frame.getContentPane().add(b1);
        frame.getContentPane().add(b2);
        frame.getContentPane().add(b3);

        MyActionListener m = new MyActionListener();
        SampleMenuListener simp = new SampleMenuListener();

        b1.addActionListener(m);
        b2.addActionListener(m);
        b3.addActionListener(m);

        signout.addMenuListener(simp);
        back.addMenuListener(simp);

        frame.setVisible(true);
    }
    private class MyActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand() == "Edit your Name")
            {
                dispose();
                Name n = new Name();
            }
            if (e.getActionCommand() == "Edit your Password")
            {
                Password pass = new Password();
            }
                
            if (e.getActionCommand() == "Edit your Email")
            {
                Email em = new Email();
            }
           
        }
    }
    private class SampleMenuListener implements MenuListener {
        
        @Override
        public void menuSelected(MenuEvent e) {
            if(e.getSource()==signout){
                frame.dispose();
                StartU g = new StartU();
            }
            if(e.getSource()==back){
                frame.dispose();
                UserMain um = new UserMain();
            }
        }
    
        @Override
        public void menuDeselected(MenuEvent e) {
        }
    
        @Override
        public void menuCanceled(MenuEvent e) {
        }
    }
}
