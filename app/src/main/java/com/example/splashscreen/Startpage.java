package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Startpage extends AppCompatActivity {
    public static final String NAME = "NAME";
    private TextView nameText;
    private Button button;
    private String name;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        nameText = findViewById(R.id.txtName);

        Intent i = getIntent();
        name = i.getStringExtra(NAME);

        nameText.setText("Before we start, "+name+"!");

        button = (Button) findViewById(R.id.b12_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open12and();
            };
        });

        button = (Button) findViewById(R.id.b13_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open13();
            };
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.sillychipsong);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void open12and() {
        Intent intent = new Intent(this, below12home.class);
        startActivity(intent);
    }

    public void open13() {
        Intent intent = new Intent(this, above13home.class);
        startActivity(intent);
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