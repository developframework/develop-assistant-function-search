package com.github.develop.function.search;

/**
 * 百度搜索
 */
public class BaiduSearchWay extends BrowseSearchWay {


    @Override
    public String toString() {
        return "Baidu";
    }

    @Override
    public String uriStr(String word) {
        return "http://www.baidu.com/s?wd=" + word;
    }
}
