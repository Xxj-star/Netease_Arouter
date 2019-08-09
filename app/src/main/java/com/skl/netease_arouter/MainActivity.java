package com.skl.netease_arouter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.skl.annotations.BindPath;
import com.skl.arouter.ARouter;
import com.skl.arouter.IRouter;

//自定义注解路径
@BindPath("main/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //跳转代码
    public void jumpActivity(View view) {
        ARouter.getInstance().jumpActivity("login/login", null);
    }
}
