package com.myapplication.View;

/**
 * Created by www86 on 2020-07-21.
 */


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myapplication.R;


import java.util.List;

public class Fragment4 extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentPagerAdapter adapter;
    // 设置是否显示动画，为了防止在创建时就开启动画，用以下三个参数做了判断，只有当看到视图后才会显示动画
    public static int flag1 = 2;
    public static int flag2 = 1;
    public static int flag3 = 1;
    WebView echart_show;
    String TAG;
    private ProgressDialog dialog;

    private boolean isVisible;
    private boolean isCreated;






    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUserVisibleHint(true);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebSettings webSettings = echart_show.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        isCreated = true;


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isVisible = true;



        }
    }



    private void initListener(){
        echart_show.loadUrl("file:///android_asset/myechart.html");
        echart_show.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }


        });
    }

}
