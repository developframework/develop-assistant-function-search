package com.github.develop.function;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.HotKey;
import com.github.develop.assistant.HotKeyFunction;
import com.github.develop.function.window.SearchWindow;
import com.melloware.jintellitype.JIntellitype;

import java.awt.*;

/**
 * 搜索辅助热键
 */
public class SearchHelpFunction implements HotKeyFunction{

    private SearchWindow searchWindow;

    @Override
    public HotKey hotKey(int identifier) {
        return new HotKey(identifier, JIntellitype.MOD_ALT, 'Q', "弹出搜索框");
    }

    @Override
    public void event(Application application) {
        if(searchWindow == null) {
            searchWindow = new SearchWindow(application);
        }
        searchWindow.toggle();
    }

    @Override
    public MenuItem createMenuItem() {
        MenuItem search = new MenuItem("搜索条");
        search.addActionListener(event -> searchWindow.toggle());
        return search;
    }

    public static void main(String[] args) {
        new SearchHelpFunction().event(null);
    }

}
