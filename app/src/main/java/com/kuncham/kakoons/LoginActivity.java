package com.kuncham.kakoons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent it=new Intent(this,HomePage.class);
        startActivity(it);
    }

    public void lostPassword(View view) {
        Intent it=new Intent(this,ForgetPassword.class);
        startActivity(it);
    }

    public void createAccount(View view) {
        Intent it=new Intent(this,RegistrationActivity.class);
        startActivity(it);
    }
}
