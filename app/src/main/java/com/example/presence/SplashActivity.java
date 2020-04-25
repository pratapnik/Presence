package com.example.presence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.presence.ui.UserInterfaceHelper;
import com.example.presence.ui.UserInterfaceService;

public class SplashActivity extends AppCompatActivity {

    UserInterfaceService userInterfaceService = new UserInterfaceHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userInterfaceService.changeStatusBarColor(getResources().getColor(R.color.colorPrimary), getWindow());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, UserDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
