package com.github.develop.function.window;

import javax.swing.*;
import java.awt.*;

/**
 * 基窗口
 */
public class BaseWindow extends JFrame{

    public BaseWindow(String title, int width, int height, boolean isAlignCenter) {
        this.setTitle(title);
        this.setSize(width, height);
        if (isAlignCenter) {
            this.setWindowAlignCenter();
        }
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    /**
     * 设置窗体居中
     */
    private void setWindowAlignCenter() {
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
