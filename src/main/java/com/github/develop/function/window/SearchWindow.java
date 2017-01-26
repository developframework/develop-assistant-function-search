package com.github.develop.function.window;

import com.github.develop.function.search.BaiduSearchWay;
import com.github.develop.function.search.GoogleSearchWay;
import com.github.develop.function.search.MvnRepositorySearchWay;
import com.github.develop.function.search.SearchWay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 搜索窗口
 */
public class SearchWindow extends BaseWindow {

    public SearchWindow() {
        super("Search", 450, 80, true);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/icon.png")));
        JPanel contentPanel = new JPanel(null);
        this.setContentPane(contentPanel);
        this.setResizable(false);

        contentPanel.setBounds(0, 0, 450, 80);
        contentPanel.setVisible(true);

        JComboBox<SearchWay> comboBox = new JComboBox<>(new SearchWay[]{new BaiduSearchWay(), new GoogleSearchWay(), new MvnRepositorySearchWay()});
        comboBox.setBounds(10, 13, 110, 25);
        contentPanel.add(comboBox);

        JTextField textField = new JTextField();
        textField.setBounds(130, 13, 300, 25);
        contentPanel.add(textField);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String word = textField.getText().trim();
                    if(!word.isEmpty()) {
                        SearchWay searchWay = (SearchWay) comboBox.getSelectedItem();
                        searchWay.search(word);
                        textField.setText("");
                        comboBox.setSelectedIndex(0);
                        SearchWindow.this.dispose();
                    }
                }
            }
        });
    }
}
