package com.github.develop.function.window;

import com.github.develop.function.search.SearchWay;
import com.github.develop.function.utils.BrowseUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 浏览面板
 */
public class BrowsePanel extends JPanel{

    public BrowsePanel(JFrame frame) {
        BoxLayout searchBoxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(searchBoxLayout);
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"http://", "file://"});
        final JTextField textField = new JTextField();
        add(comboBox);
        add(textField);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String url = textField.getText().trim();
                    BrowseUtils.browse(url);
                    textField.setText("http://");
                    frame.dispose();
                }
            }
        });
    }
}
