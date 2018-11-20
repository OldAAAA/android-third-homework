package com.magicwind.android.fitness_club.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.magicwind.android.fitness_club.R;


public class search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
    }

    public void searchResult(View view){
        Intent intent = new Intent(this,allCourse.class);
        startActivity(intent);
    }
}
