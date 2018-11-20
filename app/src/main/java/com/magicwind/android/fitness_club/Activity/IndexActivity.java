package com.magicwind.android.fitness_club.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.magicwind.android.fitness_club.Model.Images;
import com.magicwind.android.fitness_club.Model.MyListAdapter;
import com.magicwind.android.fitness_club.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexActivity extends AppCompatActivity {
    Banner banner;
    @BindView(R.id.mycycle)
    RecyclerView myListRecyclerview;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        initView();
        initData();
        initRecyclerView();
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        mDatas.addAll(Arrays.asList(Images.imageThumbUrls));
    }

    private void initRecyclerView() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myListRecyclerview.setLayoutManager(linearLayoutManager);
        myListRecyclerview.setHasFixedSize(true);
        //设置adapter
        myListRecyclerview.setAdapter(new MyListAdapter(this,mDatas));
        myListRecyclerview.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL));
    }


    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.drawable.boxing);
        list.add(R.drawable.yoga);

        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    public void loginAction(View view){
        Intent intent = new Intent(this,search.class);
        startActivity(intent);
    }
}

