package com.baway.xianger;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 类的描述：
 * 时间：  2017/8/24.19:37
 * 姓名：chenlong
 */

public class HttpUrl {
        public static void sendOkHttpRequest(String address, Callback callback) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(address)
                        .build();
                client.newCall(request).enqueue(callback);
            }
}
