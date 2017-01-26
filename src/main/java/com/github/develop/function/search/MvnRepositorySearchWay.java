package com.github.develop.function.search;

/**
 * Mvn Repository搜索
 */
public class MvnRepositorySearchWay extends BrowseSearchWay{

    @Override
    public String uriStr(String word) {
        return "http://mvnrepository.com/search?q=" + word;
    }

    @Override
    public String toString() {
        return "Mvn Repository";
    }
}
