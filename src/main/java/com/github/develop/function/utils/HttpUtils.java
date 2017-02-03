package com.github.develop.function.utils;

import org.omg.CORBA.NameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * HTTP发送工具类
 */
public final class HttpUtils {

    public static String get(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setUseCaches(false);
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);
        connection.connect();
        if (connection.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            br.lines().forEach(line -> sb.append(line));
            br.close();
            connection.disconnect();
            return sb.toString();
        } else {
            connection.disconnect();
            return null;
        }
    }
}
