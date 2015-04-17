package com.gms.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin on 2015/4/7.
 * JFrame 关于本系统的封装
 * 采用builder构建器模式，便于server和client前台的重用
 */
public class GmsFrame extends JFrame{

    private String title; //标题

    private boolean isVisible; //是否可见

    private boolean isResizable; //是否可以变动大小

    private LayoutManager layout; //布局选择（默认flowLayout）

    private int defaultCloseOperation; //退出方式

    private int width;

    private int height;

    private Component locRelativeComponent;  //为null可以使窗口居中

    private JMenuBar jMenuBar; //菜单栏 (组件需要另外添加)

    public static class Builder {
        //Required parameters
        private final int width;
        private final int height;
        private final String title;

        //Optional parameters
        private boolean isVisible = true;    //默认为可见的
        private boolean isResizable = false; //默认为不可调节大小的
        private LayoutManager layout = null;  //默认为flowLayout
        private int defaultCloseOperation = JFrame.EXIT_ON_CLOSE; //默认点右上方叉就关闭
        private Component locRelativeComponent = null; //默认居中
        private JMenuBar jMenuBar;

        public Builder(int width, int height, String title) {
            this.width = width;
            this.height = height;
            this.title = title;
        }
        public Builder defaultCloseOperation(int defaultCloseOperation) {
            this.defaultCloseOperation = defaultCloseOperation;
            return this;
        }
        public Builder isVisible(boolean isVisible) {
            this.isVisible = isVisible;
            return this;
        }
        public Builder isResizable(boolean isResizable) {
            this.isResizable = isResizable;
            return this;
        }
        public Builder layout(LayoutManager layout) {
            this.layout = layout;
            return this;
        }
        public Builder locRelativeComponent(Component component) {
            this.locRelativeComponent = component;
            return this;
        }
        public Builder jMenuBar(JMenuBar jMenuBar) {
            this.jMenuBar = jMenuBar;
            return this;
        }

        public GmsFrame build() {
            return new GmsFrame(this);
        }
    }

    private GmsFrame(Builder builder) {
        isVisible = builder.isVisible;
        isResizable = builder.isResizable;
        layout = builder.layout;
        defaultCloseOperation = builder.defaultCloseOperation;
        width = builder.width;
        height = builder.height;
        locRelativeComponent = builder.locRelativeComponent;
        title = builder.title;
        jMenuBar = builder.jMenuBar;
        setVisible(isVisible);
        setResizable(isResizable);
        setLayout(layout);
        setDefaultCloseOperation(defaultCloseOperation);
        setSize(width, height);
        setLocationRelativeTo(locRelativeComponent);
        setTitle(title);
        this.setJMenuBar(jMenuBar);
    }
}
