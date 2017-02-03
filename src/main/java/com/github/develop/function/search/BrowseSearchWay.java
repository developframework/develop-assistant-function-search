package com.github.develop.function.search;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;

/**
 * 默认浏览器搜索
 */
public abstract class BrowseSearchWay implements SearchWay{

    @Override
    public void search(String word) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(URI.create(uriStr(URLEncoder.encode(word, "UTF-8"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract String uriStr(String word);
}
