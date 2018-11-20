package com.magicwind.android.fitness_club;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public MyListAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(mDatas.get(position))
                .placeholder(R.mipmap.ic_launcher)//这里放默认显示的图片
                .error(R.mipmap.ic_launcher)//这里放加载错误时显示的图片
                .into(holder.myListImage);
        holder.title.setText(Images.title[position]);
        holder.description.setText(Images.description[position]);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView myListImage;
        TextView title;
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            myListImage = itemView.findViewById(R.id.imageView3);
            title = itemView.findViewById(R.id.textView15);
            description = itemView.findViewById(R.id.textView14);
        }
    }

}
