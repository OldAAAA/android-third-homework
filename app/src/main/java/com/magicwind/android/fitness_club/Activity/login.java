package com.magicwind.android.fitness_club.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.magicwind.android.fitness_club.Model.Responsebeen;
import com.magicwind.android.fitness_club.Network.RetrofitHelp;
import com.magicwind.android.fitness_club.R;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    public void loginAction(View view){
        EditText email = findViewById(R.id.editText);
        EditText password = findViewById(R.id.password);
        String emails = email.getText().toString();
        String passwords = password.getText().toString();


        final Context  main = this;
        RetrofitHelp.server.login(emails,passwords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Responsebeen>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Responsebeen userbeen) {
                        Toast.makeText(getApplicationContext(),userbeen.result,Toast.LENGTH_LONG).show();
                        if(userbeen.result.compareTo("登录成功") == 0){
                            Intent intent = new Intent(main,IndexActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void registerAction(View view){
        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }
}
