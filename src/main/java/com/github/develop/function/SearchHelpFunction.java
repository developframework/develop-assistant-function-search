package com.github.develop.function;

import com.github.develop.assistant.HotKey;
import com.github.develop.assistant.HotKeyFunction;
import com.github.develop.function.window.SearchWindow;
import com.melloware.jintellitype.JIntellitype;

/**
 * 搜索辅助
 */
public class SearchHelpFunction implements HotKeyFunction{


    private SearchWindow searchWindow = new SearchWindow();

    @Override
    public HotKey hotKey(int identifier) {
        return new HotKey(identifier, JIntellitype.MOD_ALT, 'Q', "弹出搜索框");
    }

    @Override
    public void event() {
        searchWindow.setVisible(!searchWindow.isVisible());
    }

    public static void main(String[] args) {
        SearchHelpFunction searchHelpFunction = new SearchHelpFunction();
        searchHelpFunction.event();
    }
}
