package com.magicwind.android.fitness_club.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.magicwind.android.fitness_club.R;
import com.squareup.picasso.Picasso;

public class NewsFragment extends Fragment {
    private String weburl;
    private String channelName;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coursefragment, container, false);

        //Picasso.get().load("http://123.206.79.91:8000/static/image/1.jpeg").into(image);
        return view;
    }

    @Override
    public  void onStart() {

        super.onStart();
    }

    @Override
    public void setArguments(Bundle bundle) {//接收传入的数据
        weburl=bundle.getString("weburl");
        channelName=bundle.getString("name");
    }

}