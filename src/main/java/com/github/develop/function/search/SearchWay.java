package com.github.develop.function.search;

import com.github.develop.function.window.component.HintTextField;

public interface SearchWay {

    void search(String word);

    void inputHint(HintTextField hintTextField, String text);
}
