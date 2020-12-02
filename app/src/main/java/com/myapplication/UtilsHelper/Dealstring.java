package com.myapplication.UtilsHelper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Dealstring {
    public List<String> dealhtml(List<String> htmlstr){
        int ch = 0, start, end;
        List<String> ret = new ArrayList<>();
        while (ch < htmlstr.size()){
            start = htmlstr.get(ch).indexOf("raw:");
            String tmp = htmlstr.get(ch).substring(start+4, htmlstr.get(ch).length());
            Log.i("wangss", tmp);
            ch++;
            ret.add(tmp);
        }
        return ret;
    }
}
