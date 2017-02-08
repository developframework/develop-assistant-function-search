package com.github.develop.function.window;

import com.github.develop.function.search.BaiduSearchWay;
import com.github.develop.function.search.GoogleSearchWay;
import com.github.develop.function.search.MvnRepositorySearchWay;
import com.github.develop.function.search.SearchWay;
import com.github.develop.function.window.component.HintTextField;

import javax.swing.*;

/**
 * 搜索面板
 */
public class SearchPanel extends JPanel {

    private JComboBox<SearchWay> typeComboBox;

    private HintTextField hintTextField;

    public SearchPanel(JFrame frame) {
        BoxLayout searchBoxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(searchBoxLayout);

        typeComboBox = new JComboBox<>(new SearchWay[]{new BaiduSearchWay(), new GoogleSearchWay(), new MvnRepositorySearchWay()});
        this.add(typeComboBox);
        hintTextField = new HintTextField(frame);
        this.add(hintTextField);

        hintTextField.addHintTextEventListener(new HintTextField.HintTextEventListener() {
            @Override
            public void onTextFieldPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox) {
                if (comboBox.isPopupVisible()) {
                    textField.setText(comboBox.getSelectedItem().toString());
                    comboBox.hidePopup();
                    textField.requestFocus();
                    return;
                }
                String word = textField.getText().trim();
                if (!word.isEmpty()) {
                    SearchWay searchWay = (SearchWay) typeComboBox.getSelectedItem();
                    searchWay.search(word);
                    textField.setText("");
                    frame.dispose();
                }
            }

            @Override
            public void onTextFieldInput(JFrame frame, JTextField textField, JComboBox comboBox) {
                ((SearchWay) typeComboBox.getSelectedItem()).inputHint((HintTextField) textField, textField.getText());
            }

            @Override
            public void onHintBoxPressKeyEnter(JFrame frame, JTextField textField, JComboBox comboBox) {
                textField.setText((String) comboBox.getSelectedItem());
                textField.requestFocus();
            }
        });
    }
}
