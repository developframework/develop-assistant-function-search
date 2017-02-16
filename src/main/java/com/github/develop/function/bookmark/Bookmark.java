package com.github.develop.function.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 书签
 */
@Getter
@AllArgsConstructor
public class Bookmark {

    private String description;

    private String url;

    @Override
    public String toString() {
        return String.format("%s      (%s)", description, url);
    }
}
