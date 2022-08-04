package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class AppSplashScreen extends AppCompatActivity {

        Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_splash_screen);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(AppSplashScreen.this, Nameinput_page.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
}
