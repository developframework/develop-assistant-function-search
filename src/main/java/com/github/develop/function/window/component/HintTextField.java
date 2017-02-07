package com.github.develop.function.window.component;

import lombok.Getter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;

/**
 * 带智能提示的输入框
 */
public class HintTextField extends JTextField {

    @Getter
    private JComboBox<String> hintBox;

    private Optional<HintTextEventListener> hintTextEventListenerOptional = Optional.empty();

    public HintTextField(JFrame frame) {
        hintBox = new JComboBox<String>() {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        this.setLayout(new BorderLayout());
        this.add(hintBox, BorderLayout.SOUTH);

        this.setMargin(new Insets(0, 5, 0, 5));

        //文本框按键事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    hintTextEventListenerOptional.ifPresent(h -> h.onTextFieldPressKeyEnter(frame, HintTextField.this, hintBox));
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    hintBox.requestFocus();
                }
            }
        });

        //提示框事件
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
                    hintTextEventListenerOptional.ifPresent(h -> h.onHintBoxPressKeyEnter(frame, HintTextField.this, hintBox));
                }
            }
        });

        //文本框事件
        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hintTextEventListenerOptional.ifPresent(h -> h.onTextFieldInput(frame, HintTextField.this, hintBox));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hintTextEventListenerOptional.ifPresent(h -> h.onTextFieldInput(frame, HintTextField.this, hintBox));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // 无事件
            }
        });
    }

    public void setHints(String[] hints) {
        hintBox.removeAllItems();
        for (String hint : hints) {
            hintBox.addItem(hint);
        }
    }

    public void addHintTextEventListener(HintTextEventListener hintTextEventListener) {
        this.hintTextEventListenerOptional = Optional.of(hintTextEventListener);
    }
    public void removeHintTextEventListener() {
        this.hintTextEventListenerOptional = Optional.empty();
    }

    public interface HintTextEventListener {

        /**
         * 文本框按下回车事件
         * @param frame
         * @param textField
         * @param comboBox
         */
        void onTextFieldPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox);

        /**
         * 文本框输入事件
         * @param frame
         * @param textField
         * @param comboBox
         */
        void onTextFieldInput(JFrame frame, JTextField textField, JComboBox comboBox);

        /**
         * 提示框按回车事件
         * @param frame
         * @param textField
         * @param comboBox
         */
        void onHintBoxPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox);
    }
}
