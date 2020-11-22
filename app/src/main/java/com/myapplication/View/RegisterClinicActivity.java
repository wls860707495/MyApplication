package com.myapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.myapplication.Model.Usermessage;
import com.myapplication.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RegisterClinicActivity extends AppCompatActivity {
    private Usermessage user = new Usermessage();
    //用户名，密码，再次输入的密码的控件
    private EditText et_user_name,et_psw,et_psw_again,user_type,clinic_name,clinic_email,clinic_tel,clinic_type,clinic_head,head_name,head_tel,head_email,bank_name,regis_num,bank_branch,bank_accout,accout_name;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName,psw,pswAgain,user_typ,clinic_nam,clinic_emai,clinic_te,clinic_typ,clinic_hea,head_te,head_emai,bank_nam,regis_nu,bank_branc,bank_accou,accout_nam;
    private RadioGroup Sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {

        //从activity_register.xml 页面中获取对应的UI控件
        Button btn_register = (Button) findViewById(R.id.btn_register);
        et_user_name= (EditText) findViewById(R.id.et_user_name);
        et_psw= (EditText) findViewById(R.id.et_psw);
        et_psw_again= (EditText) findViewById(R.id.et_psw_again);
        user_type = (EditText) findViewById(R.id.user_type);
        clinic_name = (EditText) findViewById(R.id.clinic_name);
        clinic_email = (EditText) findViewById(R.id.clinic_email);
        clinic_tel = (EditText) findViewById(R.id.clinic_tel);
        clinic_type = (EditText) findViewById(R.id.clinic_type);
        clinic_head = (EditText) findViewById(R.id.clinic_head);
        head_tel = (EditText) findViewById(R.id.head_tel);
        head_email = (EditText) findViewById(R.id.head_email);
        bank_name = (EditText) findViewById(R.id.bank_name);
        regis_num = (EditText) findViewById(R.id.regis_num);
        bank_branch = (EditText) findViewById(R.id.bank_branch);
        bank_accout = (EditText) findViewById(R.id.bank_accout);
        accout_name = (EditText) findViewById(R.id.accout_name);


        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //获取输入在相应控件中的字符串
                getEditString();


                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterClinicActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterClinicActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(RegisterClinicActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterClinicActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();

                    /**
                     *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                     */
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterClinicActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(RegisterClinicActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存到sp里面
                    /**
                     * 保存账号和密码到SharedPreferences中
                     */
                    RegisterClinicActivity.MyCallable mc = new RegisterClinicActivity.MyCallable(userName, psw,user_typ,clinic_nam,clinic_emai,clinic_te,clinic_typ,clinic_hea,head_te,head_emai,bank_nam,regis_nu,bank_branc,bank_accou,accout_nam);
                    FutureTask<String> ft = new FutureTask<>(mc);
                    Thread thread = new Thread(ft);
                    thread.start();
                    try {
                        String bool = ft.get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    RegisterClinicActivity.this.finish();
                }
            }
        });
    }
    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();
        user_typ=user_type.getText().toString().trim();
        clinic_nam=clinic_name.getText().toString().trim();
        clinic_emai=clinic_email.getText().toString().trim();
        clinic_te=clinic_tel.getText().toString().trim();
        clinic_typ=clinic_type.getText().toString().trim();
        clinic_hea=clinic_head.getText().toString().trim();
        head_te=head_tel.getText().toString().trim();
        head_emai=head_email.getText().toString().trim();
        bank_nam=bank_name.getText().toString().trim();
        regis_nu=regis_num.getText().toString().trim();
        bank_branc=bank_branch.getText().toString().trim();
        bank_accou=bank_accout.getText().toString().trim();
        accout_nam=accout_name.getText().toString().trim();

    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    public class MyCallable implements Callable<String> {
        private String name,password,user_type,clinic_name,clinic_email,clinic_tel,clinic_type,clinic_head,head_tel,head_email,bank_name,regis_num,bank_branch,bank_accout,accout_name;

        public MyCallable(String name, String password,String user_type,String clinic_name,String clinic_email,String clinic_tel,String clinic_type,String clinic_head,String head_tel,String head_email,String bank_name,String regis_num,String bank_branch,String bank_accout,String accout_name ){
            this.name = name;
            this.password = password;
            this.user_type = user_type;
            this.clinic_name = clinic_name;
            this.clinic_email = clinic_email;
            this.clinic_tel = clinic_tel;
            this.clinic_type = clinic_type;
            this.clinic_head = clinic_head;
            this.head_tel = head_tel;
            this.head_email = head_email;
            this.bank_name = bank_name;
            this.regis_num = regis_num;
            this.bank_branch = bank_branch;
            this.bank_accout = bank_accout;
            this.accout_name = accout_name;

        }
        @Override
        public String call() {
            String bool = null;
            try {
                Log.i("wang", clinic_type);
                bool = user.insert(name,password,user_type,clinic_name,clinic_email,clinic_tel,clinic_type,clinic_head,head_tel,head_email,bank_name,regis_num,bank_branch,bank_accout,accout_name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bool;
        }
    }

}