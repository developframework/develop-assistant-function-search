package com.github.develop.function.window;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.BaseWindow;
import com.github.develop.function.search.BaiduSearchWay;
import com.github.develop.function.search.GoogleSearchWay;
import com.github.develop.function.search.MvnRepositorySearchWay;
import com.github.develop.function.search.SearchWay;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;

/**
 * 搜索窗口
 */
public class SearchWindow extends BaseWindow {

    public SearchWindow(Application application) {
        super(application, "Search", 30, 7, true);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/icon.png")));
        this.setResizable(false);

        JPanel searchPanel = new JPanel();
        BoxLayout searchBoxLayout = new BoxLayout(searchPanel, BoxLayout.X_AXIS);
        searchPanel.setLayout(searchBoxLayout);
        this.setContentPane(searchPanel);


        JComboBox<SearchWay> comboBox = new JComboBox<>(new SearchWay[]{new BaiduSearchWay(), new GoogleSearchWay(), new MvnRepositorySearchWay()});
        searchPanel.add(comboBox);
        HintTextField textField = new HintTextField();
        textField.setSearchWay((SearchWay) comboBox.getSelectedItem());
        searchPanel.add(textField);
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
                        SearchWindow.this.dispose();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    textField.getHintBox().requestFocus();
                }
            }
        });

        comboBox.addItemListener(e -> {
            System.out.println(e.getItem());
            textField.setSearchWay((SearchWay) e.getItem());
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                textField.requestFocus();
            }
        });
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

    public void toggle() {
        setVisible(!isVisible());
    }

}
