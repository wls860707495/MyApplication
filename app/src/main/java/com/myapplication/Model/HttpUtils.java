package com.myapplication.Model;
import android.util.Log;

import com.myapplication.UtilsHelper.SharePreferenceUtil;
import com.myapplication.View.LoginActivity;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import java.io.IOException;

import static com.myapplication.UtilsHelper.SharePreferenceUtil.Getcontent;

/**
 * Created by 01457141 on 2017/8/8.
 */
public class HttpUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    public static Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public static Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public static Response post_token(String url, String json, String token) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("token",token)
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public static Response get_token(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("token",token)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}