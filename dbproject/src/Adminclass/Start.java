package src.Adminclass;
import src.Main.Runner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    private JLabel l1;
    private JButton button1,button2,button3;
    private JFrame frame;

    public Start(){
        frame = new JFrame();
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        l1 = new JLabel("ADMIN PORTAL");
        l1.setBounds(150,100,130,30);
        frame.getContentPane().add(l1);

        button1 = new JButton("SIGN IN");
        button1.setBounds(30, 200, 320, 40);
        button1.setFocusable(false);

        button2 = new JButton("LOG IN");
        button2.setBounds(30, 250, 320, 40);
        button2.setFocusable(false);

        button3 = new JButton("EXIT");
        button3.setBounds(120, 350, 150, 40);
        button3.setFocusable(false);
        
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(button3);

        button1.addActionListener(new MyListener());
        button2.addActionListener(new MyListener());
        button3.addActionListener(new MyListener());

        frame.setVisible(true);
    }

    public class MyListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand()=="SIGN IN"){
                frame.dispose();
                Ad_Signin sg = new Ad_Signin();
            }
            if(e.getActionCommand()=="LOG IN"){
                frame.dispose();
                Ad_Login lin = new Ad_Login();
            }
            if(e.getActionCommand()=="EXIT"){
                frame.dispose();
                Runner r = new Runner();
            }
        }
    }
}
