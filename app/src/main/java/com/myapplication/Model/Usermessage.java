package com.myapplication.Model;//package com.example.vinzee.andriodfragmentexample.Model;

/**
* Created by Yuki on 2019/6/29.
*/


import android.util.Log;

import com.squareup.okhttp.Call;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Usermessage {
    /**
     * 保存数据，传递参数给web服务器端
     * @param
     * @return
     */
    public String verify(String name,String password) throws Exception {
        //验证登陆
        Response response = null;
        String path = "http://180.201.141.232:8081/User/Signin";

        JSONObject jsonObjectTemp = new JSONObject();
        JSONArray jsonResultArrayTemp = null;
        String jsonStr = "{\"username\":"+name+",\"password\":"+password+",\"type\":"+password+"}";//json数据.
        JSONObject jsonObjectRequest = new JSONObject();
        Map<String,String> resultMap = new HashMap<>();
        response = HttpUtils.post(path, jsonStr);
        String back = response.body().string();
        return back;
    }
    public String insert(String name,String password,String type,String clinic_name,String clinic_email,String clinic_tel,String clinic_type,String clinic_head,String head_tel,String head_email,String bank_name,String regis_num,String bank_branch,String bank_accout,String accout_name) throws Exception {

        //注册诊所
        Response response = null;
        String path = "http://180.201.141.232:8081/Clinic/register";

        JSONObject jsonObjectTemp = new JSONObject();
        JSONArray jsonResultArrayTemp = null;
        String jsonStr = "{\"username\":\""+name+"\",\"password\":\""+password+"\",\"type\":\""+type+"\",\"clinic_name\":\""+clinic_name+"\",\"clinic_email\":\""+clinic_email+"\",\"clinic_tel\":\""+clinic_tel+"\",\"clinic_type\":\""+clinic_type+"\",\"clinic_head\":\""+clinic_head+"\",\"head_tel\":\""+head_tel+"\",\"head_email\":\""+head_email+"\",\"regis_num\":\""+regis_num+"\",\"bank_branch\":\""+bank_branch+"\",\"bank_accout\":\""+bank_accout+"\",\"accout_name\":\""+accout_name+"\",\"bank_name\":\""+bank_name+"\"}";//json数据.
        Log.i("wang", jsonStr);
        JSONObject jsonObjectRequest = new JSONObject();
        Map<String,String> resultMap = new HashMap<>();
        response = HttpUtils.post(path, jsonStr);
        String back = response.body().string();
        return back;
    }
}