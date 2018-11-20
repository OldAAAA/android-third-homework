package com.magicwind.android.fitness_club.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.magicwind.android.fitness_club.R;

public class class_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
    }
}
