package com.demo.weatherexercisenikhilbastikar.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.demo.weatherexercisenikhilbastikar.R;
import com.demo.weatherexercisenikhilbastikar.view.RootActivity;
import com.demo.weatherexercisenikhilbastikar.view.BaseActivity;

public class SplashActivity extends BaseActivity {

    /* Life-cycle methods */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            // This method will be executed once the timer is over
            Intent i = new Intent(SplashActivity.this, RootActivity.class);
            startActivity(i);
            finish();
        }, 5000);
    }
}