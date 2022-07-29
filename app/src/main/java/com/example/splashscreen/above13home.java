package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class above13home extends AppCompatActivity {
    private Button start;
    private MediaPlayer mediaPlayer;
    Button abv13Backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_above13);

        start = findViewById(R.id.b13startBtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(above13home.this, QuestionActivity2.class);
                startActivity(intent);
                above13home.this.finish();
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.main);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        abv13Backbtn = findViewById(R.id.abv13Backbtn);
        abv13Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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