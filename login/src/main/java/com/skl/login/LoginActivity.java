package com.skl.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.skl.annotations.BindPath;
import com.skl.arouter.ARouter;


@BindPath("login/login")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void jumpActivityLogin(View view) {
        ARouter.getInstance().jumpActivity("member/member", null);
    }

}
