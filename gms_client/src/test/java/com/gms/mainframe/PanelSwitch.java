package com.gms.mainframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelSwitch extends JFrame {
    JButton jbutton1 = new JButton("设置成绿色");
    JButton jbutton2 = new JButton("设置成蓝色");
    JPanel jpanel = new JPanel();
    JPanel jpane2 = new JPanel();
    JPanel contentPane = new JPanel();

    public PanelSwitch() {
        jbutton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(jpanel);
                contentPane.add(jpane2);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        jbutton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contentPane.remove(jpane2);
                contentPane.add(jpanel);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        jpanel.add(jbutton1);
        jpanel.setBackground(Color.BLUE);
        jpanel.setPreferredSize(new Dimension(600, 400));

        jpane2.add(jbutton2);
        jpane2.setBackground(Color.GREEN);
        jpane2.setPreferredSize(new Dimension(600, 400));

        setBounds(0, 0, 640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.add(jpanel);
        setContentPane(contentPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PanelSwitch();
    }
}