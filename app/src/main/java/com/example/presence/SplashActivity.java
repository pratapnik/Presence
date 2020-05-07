package com.example.presence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.presence.ui.UserInterfaceHelper;
import com.example.presence.ui.UserInterfaceService;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1000;

    UserInterfaceService userInterfaceService = new UserInterfaceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        userInterfaceService.changeStatusBarColor(getResources().getColor(R.color.colorPrimary), getWindow());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
