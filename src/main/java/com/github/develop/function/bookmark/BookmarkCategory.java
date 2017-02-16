package com.github.develop.function.bookmark;

import lombok.Getter;
import lombok.Setter;

/**
 * 书签分类
 */
@Getter
@Setter
public class BookmarkCategory {

    private String name;

    private Bookmark[] bookmarks;

    @Override
    public String toString() {
        return name;
    }
}
