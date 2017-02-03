package com.github.develop.function.search;

import com.github.develop.function.window.SearchWindow;

/**
 * Google搜索
 */
public class GoogleSearchWay extends BrowseSearchWay{

    @Override
    public String uriStr(String word) {
        return "http://www.google.com/#q=" + word;
    }

    @Override
    public String toString() {
        return "Google";
    }

    @Override
    public void inputHint(SearchWindow.HintTextField hintTextField, String text) {

    }
}
