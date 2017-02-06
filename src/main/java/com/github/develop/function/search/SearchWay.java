package com.github.develop.function.search;

import com.github.develop.function.window.SearchPanel;

public interface SearchWay {

    void search(String word);

    void inputHint(SearchPanel.HintTextField hintTextField, String text);
}
