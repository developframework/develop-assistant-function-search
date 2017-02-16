package com.github.develop.function.window;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.develop.assistant.Application;
import com.github.develop.assistant.BaseWindow;
import com.github.develop.assistant.resource.Resource;
import com.github.develop.function.bookmark.Bookmark;
import com.github.develop.function.bookmark.BookmarkCategory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 书签窗口
 */
public class BookmarkWindow extends BaseWindow {

    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    public BookmarkWindow(Application application) {
        super(application, "书签", 40, 30, true);
        this.setResizable(false);
        this.setLayout(new GridLayout(1, 1));
        BookmarkCategory[] bookmarkCategories = readBookmarkCategory(application);
        this.add(tabbedPane);
        for(BookmarkCategory bookmarkCategory : bookmarkCategories) {
            tabbedPane.addTab(bookmarkCategory.getName(), new SinpleBookmarkPanel(this, bookmarkCategory.getBookmarks()));
        }
    }

    private BookmarkCategory[] readBookmarkCategory(Application application) {
        Resource resource = application.getResource("bookmark.json");
        if(resource != null) {
            try {
                BufferedReader br =  new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
                StringBuilder sb = new StringBuilder();
                br.lines().forEach(sb::append);
                br.close();
                String json = sb.toString();
                JSONObject root = JSONObject.parseObject(json);
                JSONArray bookmarksCategoriesJSONArray = root.getJSONArray("bookmark_categories");
                BookmarkCategory[] bookmarkCategories = new BookmarkCategory[bookmarksCategoriesJSONArray.size()];
                for (int i = 0; i < bookmarksCategoriesJSONArray.size(); i++) {
                    JSONObject categoryJSONObject = bookmarksCategoriesJSONArray.getJSONObject(i);
                    String name = categoryJSONObject.getString("name");
                    JSONArray bookmarksJSONArray = categoryJSONObject.getJSONArray("bookmarks");
                    Bookmark[] bookmarks = new Bookmark[bookmarksJSONArray.size()];
                    bookmarkCategories[i] = new BookmarkCategory();
                    bookmarkCategories[i].setName(name);
                    bookmarkCategories[i].setBookmarks(bookmarks);
                    for (int j = 0; j < bookmarksJSONArray.size(); j++) {
                        JSONObject bookmarkJSONObject = bookmarksJSONArray.getJSONObject(j);
                        String description = bookmarkJSONObject.getString("description");
                        String url = bookmarkJSONObject.getString("url");
                        bookmarks[j] = new Bookmark(description, url);
                    }
                }
                return bookmarkCategories;
            } catch (IOException e) {
                return new BookmarkCategory[0];
            }
        } else {
            return new BookmarkCategory[0];
        }
    }
}
