package com.example.myapp.mydigipassdemo;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MainActivity extends LifecycleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}
