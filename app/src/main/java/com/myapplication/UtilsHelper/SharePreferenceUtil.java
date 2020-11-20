package com.myapplication.UtilsHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SharePreferenceUtil {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_date";



    public static final SharedPreferences getSharePreference(Context context){
        /**
         * Context.MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，
         * 只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，
         * 如果想把新写入的内容追加到原文件中,可以使用Context.MODE_APPEND
         * Context.MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
         * Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件。
         * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
         * MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
         */

        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

        /*执行该方法，把文件保存在preference.xml中*/
    public static void updateInfo(String key,int value,Context context) {
        SharedPreferences sp = getSharePreference(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();

    }


    public static int GetInfo(String key,Context context){
        SharedPreferences sp = getSharePreference(context);
        return sp.getInt(key,0);
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param context 上下文对象
     * @param key key
     * @param object 需要存储的数据
     */
    public static void setParam(Context context , String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }

        editor.apply();
    }


}
