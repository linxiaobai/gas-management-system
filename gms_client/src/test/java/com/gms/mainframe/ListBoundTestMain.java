package com.gms.mainframe;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
/**
 * Created by Kevin on 2015/5/1.
 */



public class ListBoundTestMain extends JFrame {
    JList m_list = null;
    DefaultListModel m_model = null;

    public static void main(String[] args) {
        new ListBoundTestMain().initView();
    }

    public void initView() {
        m_model = new DefaultListModel();
        m_list = new JList(m_model);
        getContentPane().add(m_list);
        m_model.insertElementAt("Test1", 0);
        m_model.insertElementAt("Test2", 0);
        m_model.insertElementAt("Test3", 0);
        m_model.insertElementAt("Test4", 0);
        m_model.insertElementAt("Test5", 0);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);

        new Thread(){
            private int i = 0;
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(500);
                    }catch (Exception e) {
                        // TODO: handle exception
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            i++;
                            m_model.insertElementAt("Loop"+i, 0);
                            System.out.println("Loop"+i);
                            System.out.println("Before size="+m_model.getSize());
                            m_model.remove(m_model.getSize()-1);

                            System.out.println("After size="+m_model.getSize());
                        }
                    });

                }
            }
        }.start();

        setVisible(true);
    }
}