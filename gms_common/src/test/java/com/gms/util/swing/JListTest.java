package com.gms.util.swing;

/**
 * Created by Kevin on 2015/4/19.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame=new ListFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class ListFrame extends JFrame{
    private JList words;
    private JPanel listPanel;
    private JLabel label;
    private JPanel buttonPanel;
    private ButtonGroup group;
    private String prefix="You have selected: ";
    private String suffix=".";
    public ListFrame()
    {
        this.setTitle("JListTest");
        //设置窗口大小
        this.setSize(400,300);
        //List中待显示的值列表
        String[] options=new String[]{"a","b","c","d","e","f","g","h"};
        //创建列表
        words=new JList();
        //设置选择模式为多选
        words.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //设置列表显示的数据
        words.setListData(options);
        //设置可见行数
        words.setVisibleRowCount(3);
        //添加滚动条支持
        JScrollPane pane=new JScrollPane(words);
        //创建一个JPanel
        listPanel=new JPanel();
        listPanel.add(pane);
        //添加事件监控
        words.addListSelectionListener(new ListSelectionListener(){

            public void valueChanged(ListSelectionEvent e) {
                Object[] values=words.getSelectedValues();
                StringBuilder text=new StringBuilder(prefix);
                for(int i=0;i<values.length;i++)
                {
                    String word=(String) values[i];
                    text.append(word);
                    text.append(" ");
                }
                text.append(suffix);
                label.setText(text.toString());
            }});
        buttonPanel =new JPanel();
        group=new ButtonGroup();
        //创建3个RadioButton
        this.makeButton("Vertical", JList.VERTICAL);
        this.makeButton("Vertical Wrap", JList.VERTICAL_WRAP);
        this.makeButton("Herizontal Wrap", JList.HORIZONTAL_WRAP);
        //用Border布局来放置控件到Frame
        this.add(listPanel,BorderLayout.NORTH);
        label=new JLabel(prefix+suffix);
        this.add(label,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
        //设置List第一条选中
        words.setSelectedIndex(0);
    }
    private void makeButton(String label,final int orientation)
    {
        JRadioButton button=new JRadioButton(label);
        buttonPanel.add(button);
        if(group.getButtonCount()==0)
        {
            button.setSelected(true);
        }
        group.add(button);
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                words.setLayoutOrientation(orientation);
                listPanel.revalidate();
            }});
    }
}