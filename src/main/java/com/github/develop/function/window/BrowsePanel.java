package com.github.develop.function.window;

import com.github.develop.function.utils.BrowseUtils;
import com.github.develop.function.window.component.HintTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 浏览面板
 */
public class BrowsePanel extends JPanel {

    private JComboBox<HintTextField.HintTextEventListener> typeComboBox;
    private HintTextField hintTextField;

    private HintTextField.HintTextEventListener httpHintTextEventListener = new HintTextField.HintTextEventListener() {

        @Override
        public String toString() {
            return "http://";
        }

        @Override
        public void onTextFieldPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox) {
            String uri = typeComboBox.getSelectedItem() + textField.getText().trim();
            BrowseUtils.browse(uri);
            textField.setText("");
            frame.dispose();
        }

        @Override
        public void onTextFieldInput(JFrame frame, JTextField textField, JComboBox comboBox) {

        }

        @Override
        public void onHintBoxPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox) {

        }
    };

    private HintTextField.HintTextEventListener fileHintTextEventListener = new HintTextField.HintTextEventListener() {

        @Override
        public String toString() {
            return "file://";
        }

        @Override
        public void onTextFieldPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox) {
            if (comboBox.isPopupVisible()) {
                fillText(textField, comboBox);
                return;
            }
            if(checkValid(textField)) {
                String uri = typeComboBox.getSelectedItem() + textField.getText().trim().replaceAll("\\\\", "/");
                BrowseUtils.browse(uri);
                textField.setText("");
                frame.dispose();
            }
        }

        @Override
        public void onTextFieldInput(JFrame frame, JTextField textField, JComboBox comboBox) {
            String text = dealUriText(textField.getText());
            if (!text.isEmpty()) {
                textField.setForeground(checkValid(textField) ? Color.BLACK : Color.RED);
                checkValid(textField);
                String[] filenames = filenames(text);
                ((HintTextField) textField).setHints(filenames);
                comboBox.setPopupVisible(filenames.length > 0);
            }
        }

        @Override
        public void onHintBoxPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox) {
            fillText(textField, comboBox);
        }
    };

    public BrowsePanel(JFrame frame) {
        BoxLayout searchBoxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(searchBoxLayout);
        typeComboBox = new JComboBox<>(new HintTextField.HintTextEventListener[]{httpHintTextEventListener, fileHintTextEventListener});
        hintTextField = new HintTextField(frame);
        hintTextField.addHintTextEventListener((HintTextField.HintTextEventListener) typeComboBox.getSelectedItem());
        add(typeComboBox);
        add(hintTextField);

        typeComboBox.addItemListener(e -> {
            hintTextField.addHintTextEventListener((HintTextField.HintTextEventListener) typeComboBox.getSelectedItem());
            hintTextField.setText("");
            hintTextField.getHintBox().removeAllItems();
            hintTextField.getHintBox().hidePopup();
        });
    }

    private String dealUriText(String uri) {
        return uri.trim().replaceAll("[\\\\, \\/]", "\\" + File.separator);
    }

    private String[] filenames(String text) {
        int lastSlashIndex = text.lastIndexOf(File.separator);
        if (lastSlashIndex > 0) {
            String path = text.substring(0, lastSlashIndex + 1);
            String startStr = text.substring(lastSlashIndex + 1);
            return filenamesInDir(path, startStr);
        }
        return new String[0];
    }

    private String[] filenamesInDir(String path, final String startStr) {
        File file = new File(path);
        if (file.isDirectory()) {
            if (startStr == null || startStr.isEmpty()) {
                return file.list();
            }
            return file.list(((dir, name) -> name.startsWith(startStr)));
        } else if (file.isFile()) {
            return new String[]{file.getName()};
        }
        return new String[0];
    }

    private void fillText(JTextField textField, JComboBox comboBox) {
        String text = dealUriText(textField.getText());
        int lastSlashIndex = text.lastIndexOf(File.separator);
        text = text.substring(0, lastSlashIndex + 1);
        textField.setText(text + comboBox.getSelectedItem());
        comboBox.hidePopup();
        textField.requestFocus();
    }

    public boolean checkValid(JTextField textField) {
        return new File(textField.getText().trim()).exists();
    }
}
