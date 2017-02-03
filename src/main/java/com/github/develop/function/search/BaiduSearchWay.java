package com.github.develop.function.search;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.develop.function.utils.HttpUtils;
import com.github.develop.function.window.SearchWindow;

import java.io.IOException;
import java.net.URLEncoder;

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

    @Override
    public void inputHint(SearchWindow.HintTextField hintTextField, String text) {
        try {
            String response = HttpUtils.get("https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su?json=1&wd=" + URLEncoder.encode(text, "UTF-8"));
            if (response != null) {
                String json = response.substring(response.indexOf("(") + 1, response.lastIndexOf(")"));
                String[] hints = parseJSON(json);
                hintTextField.setHints(hints);
                hintTextField.getHintBox().setPopupVisible(hints.length > 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] parseJSON(String json) {
        JSONObject root = JSONObject.parseObject(json);
        if (root.getIntValue("status") == 0) {
            JSONArray jsonArray = root.getJSONArray("g");
            if(jsonArray == null) {
                return new String[0];
            }
            int size = jsonArray.size();
            String[] hints = new String[size];
            for (int i = 0; i < size; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                hints[i] = jsonObject.getString("q");
            }
            return hints;
        }
        return new String[0];
    }
}
