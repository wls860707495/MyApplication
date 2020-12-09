package com.myapplication.View;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.Model.HtmlContent;
import com.myapplication.R;
import com.myapplication.UtilsHelper.Dealstring;
import com.myapplication.View.adapter.MyAdapter;
import com.myapplication.View.bean.ImageInfor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    public HtmlContent htmlContent = new HtmlContent();
    private RecyclerView main_rv;
    public Dealstring dealstring = new Dealstring();
    public String very = "imhere";
    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_fragment3);
        initView();
        initDatas();
        
    }
    private void initView() {
        main_rv = (RecyclerView) getActivity().findViewById(R.id.main_rv);
    }

    private void initDatas() {
        final List<ImageInfor> list = new ArrayList<>();
       SharedPreferences sp = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
       String to = sp.getString("token",null);
        int j=0;
        try {
       MyCallable callable = new MyCallable(very,to);
       FutureTask<String> ft = new FutureTask<>(callable);
       Thread thread = new Thread(ft);
       thread.start();
       String jsonArray = ft.get();
       final JSONArray result = new JSONArray(jsonArray);
       for (int i=0;i<result.length();i++) {
           list.add(new ImageInfor( dealstring.html(result.getJSONObject(j).getString("content")), result.getJSONObject(j).getString("title"), result.getJSONObject(j).getString("user_name"),  result.getJSONObject(j).getString("pushtime")));
           Log.i("wang",  dealstring.html(result.getJSONObject(j).getString("content")));
           j++;
       }
        //添加数据



        //设置列表显示方式
        main_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置列表默认动画效果
        main_rv.setItemAnimator(new DefaultItemAnimator());
        //绑定适配器
        MyAdapter myAdapter = new MyAdapter(list);
        main_rv.setAdapter(myAdapter);
        //列表点击事件
        myAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), ContentActivity.class);
                try {
                    intent.putExtra("html",result.getJSONObject(position).getString("content"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                Log.i("position=", position + "");
            }
        });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public class MyCallable implements Callable<String> {
        private String very;
        private String to;
        public MyCallable(String very,String to){
            this.very = very;
            this.to = to;
        }
        @Override
        public String call() {
            String json = null;
            try {
                json = htmlContent.listpush(very,to);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }
    }

    }
