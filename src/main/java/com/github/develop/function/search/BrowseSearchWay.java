package com.github.develop.function.search;

import com.github.develop.function.utils.BrowseUtils;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * 默认浏览器搜索
 */
public abstract class BrowseSearchWay implements SearchWay{

    @Override
    public void search(String word) {
        try {
            BrowseUtils.browse(uriStr(URLEncoder.encode(word, "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public abstract String uriStr(String word);
}
