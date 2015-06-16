package com.gms.swing;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin on 2015/4/8.
 * 为了使得在tabbedPanel容器中能够移除某个JPanel，所以对其进行简单改写，增加name成员变量做为唯一标识，
 * 并且根据该变量去重写其equals以及hashCode方法
 */
public class GmsPanel extends JPanel{
    /**
     * 唯一标识
     */
    private String name;

    public GmsPanel(String name) {
        super();
        this.name = name;
    }

    public GmsPanel(String name, LayoutManager layoutManager) {
        super(layoutManager);
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof GmsPanel)) {
            return false;
        }
        if (StringUtils.isBlank(this.name)) {
            return false;
        }
        if (this.name.equals(((GmsPanel) obj).name)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        char[] chars = this.name.toCharArray();
        if (chars.length == 0) {
            return super.hashCode();
        } else {
            int hashCode = 17;
            for (int j = 0; j < chars.length; j++) {
                hashCode += (int)chars[j] * j;
            }
            return hashCode;
        }
    }
}
