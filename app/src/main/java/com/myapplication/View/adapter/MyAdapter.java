package com.myapplication.View.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapplication.R;
import com.myapplication.View.LoginActivity;
import com.myapplication.View.bean.ImageInfor;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<ImageInfor> list;

    public MyAdapter(List<ImageInfor> list) {
        this.list = list;
    }

    //新建点击事件接口
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 初始化布局视图
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 绑定视图组件数据
     */
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.iv_backgroud.setImageURL(list.get(position).getimageurl());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_time.setText(list.get(position).getPushtime());
        final int postions = position;
        if (mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getAdapterPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MyImageView iv_backgroud;
        public TextView tv_title;
        public TextView tv_name;
        public TextView tv_time;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_backgroud = (MyImageView) itemView.findViewById(R.id.picture);
            tv_title = (TextView) itemView.findViewById(R.id.title);
            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_time = (TextView) itemView.findViewById(R.id.pushtime);
        }
    }

}
