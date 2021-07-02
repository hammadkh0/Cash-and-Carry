package src.Adminclass;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ad_Record extends JFrame{

    private JButton b1, b2, b3,b4;
    private JLabel l;
    private JFrame frame;
    private JMenuBar menubar;
    private JMenu signout, back;

    protected Ad_Record()
    {
        frame = new JFrame("Actions");

        menubar = new JMenuBar();
        signout = new JMenu("Sign out");
        back = new JMenu("Back");
        l = new JLabel(Ad_Login.getIndex());

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
                Ad_Name n = new Ad_Name();
            }
            if (e.getActionCommand() == "Edit your Password")
            {
                Ad_Password pass = new Ad_Password();
            }
                
            if (e.getActionCommand() == "Edit your Email")
            {
                Ad_Email em = new Ad_Email();
            }
        }
    }
    private class SampleMenuListener implements MenuListener {
        
        @Override
        public void menuSelected(MenuEvent e) {
            if(e.getSource()==signout){
                frame.dispose();
                Start st = new Start();
            }
            if(e.getSource()==back){
                frame.dispose();
                AdminMain um = new AdminMain();
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
