package com.github.develop.function.window;

import com.github.develop.function.search.BaiduSearchWay;
import com.github.develop.function.search.GoogleSearchWay;
import com.github.develop.function.search.MvnRepositorySearchWay;
import com.github.develop.function.search.SearchWay;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 搜索面板
 */
public class SearchPanel extends JPanel {

    public SearchPanel(JFrame frame) {
        BoxLayout searchBoxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(searchBoxLayout);

        JComboBox<SearchWay> comboBox = new JComboBox<>(new SearchWay[]{new BaiduSearchWay(), new GoogleSearchWay(), new MvnRepositorySearchWay()});
        this.add(comboBox);
        HintTextField textField = new HintTextField();
        textField.setSearchWay((SearchWay) comboBox.getSelectedItem());
        this.add(textField);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String word = textField.getText().trim();
                    if (!word.isEmpty()) {
                        SearchWay searchWay = (SearchWay) comboBox.getSelectedItem();
                        searchWay.search(word);
                        textField.setText("");
                        comboBox.setSelectedIndex(0);
                        frame.dispose();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    textField.getHintBox().requestFocus();
                }
            }
        });

        comboBox.addItemListener(e -> textField.setSearchWay((SearchWay) e.getItem()));
    }

    /**
     * 带智能提示的输入框
     */
    public class HintTextField extends JTextField {

        @Getter
        private JComboBox<String> hintBox;
        @Setter
        private SearchWay searchWay;

        public HintTextField() {
            this.setColumns(10);
            hintBox = new JComboBox<String>() {
                public Dimension getPreferredSize() {
                    return new Dimension(super.getPreferredSize().width, 0);
                }
            };
            hintBox.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (hintBox.getSelectedIndex() == 0) {
                            HintTextField.this.requestFocus();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        HintTextField.this.requestFocus();
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        HintTextField.this.setText((String) hintBox.getSelectedItem());
                        HintTextField.this.requestFocus();
                    }
                }
            });
            this.setLayout(new BorderLayout());
            this.add(hintBox, BorderLayout.SOUTH);
            this.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    searchWay.inputHint(HintTextField.this, HintTextField.this.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    searchWay.inputHint(HintTextField.this, HintTextField.this.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                }
            });
        }

        public void setHints(String[] hints) {
            hintBox.removeAllItems();
            for (String hint : hints) {
                hintBox.addItem(hint);
            }
        }
    }
}
