package com.demo.weatherexercisenikhilbastikar.view;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.demo.weatherexercisenikhilbastikar.R;

public class RootActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}