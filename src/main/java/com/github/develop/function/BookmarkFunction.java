package com.github.develop.function;

import com.github.develop.assistant.*;
import com.github.develop.function.window.BookmarkWindow;
import com.melloware.jintellitype.JIntellitype;

import java.awt.*;

/**
 * 书签功能
 */
public class BookmarkFunction implements HotKeyFunction, ApplicationAware, MenuSupport {

    private BookmarkWindow bookmarkWindow;

    @Override
    public HotKey hotKey(int identifier) {
        return new HotKey(identifier, JIntellitype.MOD_ALT, 'B', "弹出书签");
    }

    @Override
    public void event() {
        bookmarkWindow.toggle();
    }

    @Override
    public MenuItem createMenuItem() {
        MenuItem bookmark = new MenuItem("书签");
        bookmark.addActionListener(event -> bookmarkWindow.toggle());
        return bookmark;
    }

    @Override
    public void setApplication(Application application) {
        bookmarkWindow = new BookmarkWindow(application);
    }
}
