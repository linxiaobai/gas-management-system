package com.gms.util.swing;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicListUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by Kevin on 2015/4/19.
 */
public class CkList extends JList {

    public CkList(){
        super();
        initCheckBox();
    }

    public CkList(final Vector<?> listData){
        super(listData);
        initCheckBox();
    }

    public CkList(final Object[] listData){
        super(listData);
        initCheckBox();
    }

    public CkList(ListModel dataModel){
        super(dataModel);
        initCheckBox();
    }

    private void initCheckBox() {
        this.setCellRenderer(new CheckBoxRenderer());
        this.setUI(new CheckBoxListUI());
    }

    class CheckBoxRenderer extends JCheckBox implements ListCellRenderer {
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            this.setSelected(isSelected);
            this.setText(value.toString());
            return this;
        }
    }

    class CheckBoxListUI extends BasicListUI implements MouseInputListener {
        protected MouseInputListener createMouseInputListener() {
            return this;
        }

        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            int row = CkList.this.locationToIndex(e.getPoint());

            boolean temp = CkList.this.getSelectionModel().isSelectedIndex(row);

            if (!temp) {
                CkList.this.addSelectionInterval(row, row);
            } else {
                CkList.this.removeSelectionInterval(row,row);
            }

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {

        }

        public void mouseMoved(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        JPanel jPanel = new JPanel();
        jPanel.add(new CkList());
        jFrame.setContentPane(jPanel);


    }

}
