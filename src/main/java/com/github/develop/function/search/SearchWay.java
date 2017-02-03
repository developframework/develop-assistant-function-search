package com.github.develop.function.search;

import com.github.develop.function.window.SearchWindow;

public interface SearchWay {

    void search(String word);

    void inputHint(SearchWindow.HintTextField hintTextField, String text);
}
