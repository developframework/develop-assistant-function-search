package com.github.develop.function.search;

import com.github.develop.function.window.component.HintTextField;

/**
 * Mvn Repository搜索
 */
public class MvnRepositorySearchWay extends BrowseSearchWay{

    @Override
    public String uriStr(String word) {
        return "http://mvnrepository.com/search?q=" + word;
    }

    @Override
    public String toString() {
        return "Mvn Repository";
    }

    @Override
    public void inputHint(HintTextField hintTextField, String text) {

    }
}
