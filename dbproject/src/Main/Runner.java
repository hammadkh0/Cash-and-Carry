package src.Main;

import src.UserClass.StartU;
import src.Adminclass.Start;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JFrame {
    
    private JLabel l1;
    private JButton button1,button2;
    private JFrame frame;

    public Runner(){

        frame = new JFrame();
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        l1 = new JLabel("BRUH ONLINE STORE");
        l1.setBounds(120,100,200,30);
        frame.getContentPane().add(l1);

        button1 = new JButton("ADMIN PORTAL");
        button1.setBounds(30, 200, 320, 70);
        button1.setFocusable(false);

        button2 = new JButton("BUYER PORTAL");
        button2.setBounds(30, 290, 320, 70);
        button2.setFocusable(false);
        
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);

        button1.addActionListener(new MyListener());
        button2.addActionListener(new MyListener());

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Runner run = new Runner();
    }

    public class MyListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand()=="ADMIN PORTAL"){
                frame.dispose();
                Start st = new Start();
            }
            if(e.getActionCommand()=="BUYER PORTAL"){
                frame.dispose();
                StartU gui = new StartU();
            }
        }
    }
}
