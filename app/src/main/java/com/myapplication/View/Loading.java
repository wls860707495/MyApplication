package com.myapplication.View;
/*
 *界面加载
 */
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Loading extends AppCompatActivity {
    private Handler handler = new Handler();
    private ImageView image2;
    String [] permissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    List<String> mPermissionList = new ArrayList<>();
    private static final int PERMISSION_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        setContentView(R.layout.activity_loading);
        image2 = (ImageView)findViewById(R.id.frontpage);
        int num=(int)(Math.random()*2);

        switch (num){
            case 1:
                image2.setBackground(getResources().getDrawable(R.drawable.one));//变形
                image2.setBackgroundResource(R.drawable.one);//变形
                image2.setBackgroundDrawable(getResources().getDrawable(R.drawable.one));
                break;
            default:
                image2.setBackground(getResources().getDrawable(R.drawable.three));//变形
                image2.setBackgroundResource(R.drawable.three);//变形
                image2.setBackgroundDrawable(getResources().getDrawable(R.drawable.three));
                break;

        }
        delayHandler(0);





    }
    private void delayHandler(int i){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Loading.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 200);//延时









        }

    }

