package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Storyline1 extends AppCompatActivity {
    private Button st1;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storyline1);

        st1 = findViewById(R.id.st1_btn);
        st1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Storyline1.this,Storyline2.class);
                startActivity(intent);
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