package com.skl.member;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.skl.annotations.BindPath;


@BindPath("member/member")
public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menber);
    }
}
