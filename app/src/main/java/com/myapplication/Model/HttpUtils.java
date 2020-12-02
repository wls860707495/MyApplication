package com.myapplication.Model;
import android.util.Log;




import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                .addHeader("Authorization",token)
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public static Response get_token(String url, String token) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization",token)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}