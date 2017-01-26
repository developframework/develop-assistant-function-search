package com.github.develop.function;

import com.github.develop.assistant.Application;
import com.github.develop.assistant.HotKey;
import com.github.develop.assistant.HotKeyFunction;
import com.github.develop.function.window.SearchWindow;
import com.melloware.jintellitype.JIntellitype;

/**
 * 搜索辅助
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
        searchWindow.setVisible(!searchWindow.isVisible());
    }
}
