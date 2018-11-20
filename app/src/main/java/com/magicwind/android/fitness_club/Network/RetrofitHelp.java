package com.magicwind.android.fitness_club.Network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.magicwind.android.fitness_club.APIServer.APIServer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelp {
    //使用retrofit 连接服务器
    private OkHttpClient client;
    public static APIServer server;

    public static RetrofitHelp mRetrofitHip = new RetrofitHelp();

    private RetrofitHelp(){
        //这个是一个okhttp3的客户端
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://123.206.79.91:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        server = retrofit.create(APIServer.class);
    }

}
