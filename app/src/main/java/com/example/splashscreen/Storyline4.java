package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Storyline4 extends AppCompatActivity {
    private Button st4, btnBack;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyline4);

        st4 = findViewById(R.id.st4_btn);
        st4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Storyline4.this, Storyline5.class);
                startActivity(intent);
                Storyline4.this.finish();
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Intent intent = new Intent(Storyline4.this, Storyline3.class);
                startActivity(intent);
                Storyline4.this.finish();
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.story2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }
}