package com.magicwind.android.fitness_club.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.magicwind.android.fitness_club.Model.Responsebeen;
import com.magicwind.android.fitness_club.Network.RetrofitHelp;
import com.magicwind.android.fitness_club.R;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("瑜伽", "游泳", "拳击", "乒乓球", "电子竞技"));
        niceSpinner.attachDataSource(dataset);
    }

    public void homeAction(View view) {

        final Context main = this;
        String email = ((EditText)findViewById(R.id.editText3)).getText().toString();
        String password =  ((EditText)findViewById(R.id.editText4)).getText().toString();
        String username =  ((EditText)findViewById(R.id.editText2)).getText().toString();
        RetrofitHelp.server.register(email,password,username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Responsebeen>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Responsebeen userbeen) {
                        Toast.makeText(getApplicationContext(),userbeen.result,Toast.LENGTH_LONG).show();
                        if(userbeen.result == "注册成功"){
                            Intent intent = new Intent(main,IndexActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void returnAction(View view){
        Intent intent = new Intent(this, login.class);
        finish();
        startActivity(intent);
    }
}
