package com.myapplication.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


import com.myapplication.UtilsHelper.Dealstring;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HtmlContent {
    int m=0;
    private Dealstring dealstring = new Dealstring();
    public String htmlcontent(String html, List<String> urls, String token) throws Exception {
        //注册诊所
         OkHttpClient client = new OkHttpClient();
        String url = "http://180.201.141.232:8081/Push/pushcontent";
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//mHashMap是图片path的集合
        int o = 0;
        List<String> urls2 = dealstring.dealhtml(urls);

        if(urls2 !=null){
            for (int i = 0; i < urls2.size(); i++) {
                File f = new File(urls2.get(i));
                if (f == null) break;
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(), options);
                    bm.compress(Bitmap.CompressFormat.JPEG, 90, new FileOutputStream(f));
//                    saveBitmap(bm);
                    bm.recycle();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Log.i("ss", f.getName());
                builder.addFormDataPart("img[]", f.getName(), RequestBody.create(MediaType.parse("image/jpeg"), f));
            }
        }
        builder.addFormDataPart("html",html);
        MultipartBody requesBody = builder.build();
        Request request = new Request.Builder()
                .addHeader("Authorization",token)
                .url(url)
                .post(requesBody)
                .build();
        Response response = client.newCall(request).execute();
        String back = response.body().toString();
        return back;

    }
    public String listpush(String very,String token) throws Exception {
        //获取发布内容条目
        Response response = null;
        String path = "http://180.201.141.232:8081/Push/listcontent";
        String jsonStr = "{\"very\":"+ very +"}";//json数据.
        response = HttpUtils.post_token(path, jsonStr,token);
        String back = response.body().string();
        Log.i("push", back);
        return back;
    }

    //test
    public void saveBitmap(Bitmap bitmap) throws IOException {
        Log.i("ss", "saveBitmap: ");
        m++;
        File file = new File(Environment.getExternalStorageDirectory() + "/image"+m+".jpg");
        Log.i("ss", file+"");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
