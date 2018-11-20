package com.magicwind.android.fitness_club.APIServer;

import com.magicwind.android.fitness_club.Model.Responsebeen;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIServer {

@FormUrlEncoded
@POST("/login")
    Observable<Responsebeen> login(@Field("email") String name, @Field("password") String password);

@FormUrlEncoded
@POST("/register")
    Observable<Responsebeen> register(@Field("email") String name, @Field("password") String password, @Field("username") String username);
        }
