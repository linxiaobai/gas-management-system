package com.gms.swing;

import com.gms.util.PathUtil;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

/**
 * Created by Kevin on 2015/4/8.
 */
public class GmsStartWindow extends JWindow{
    ImageIcon image;
    JLabel pic;

    //构造函数
    public GmsStartWindow(String picName, final JFrame frame, int waitTime) {
        super(frame);
        String path = PathUtil.fetchPath(this.getClass(), picName);
        ImageIcon image = new ImageIcon(path);
        JLabel pic = new JLabel(image);

        this.getContentPane().add(pic, BorderLayout.CENTER);
        //调整图片大小到屏幕中央
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize  = pic.getPreferredSize();

        setLocation(screenSize.width/2 - (labelSize.width/2),
                screenSize.height/2 - (labelSize.height/2));

        final int PAUSE = waitTime;
        final Runnable closerRunner = new Runnable() {
            public void run() {
                setVisible(false);
                dispose();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                //System.exit(0);
            }
        };		//End closerRunner

        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(PAUSE);
                    SwingUtilities.invokeAndWait(closerRunner);
                }
                catch(Exception e) {
                    e.printStackTrace();
                    // 能够捕获InvocationTargetException
                    // 能够捕获InterruptedException
                }
            }
        };		//End waitRunner

        setVisible(true);
        Thread waitThread = new Thread(waitRunner, "SplashThread");
        waitThread.start();

    };
}
