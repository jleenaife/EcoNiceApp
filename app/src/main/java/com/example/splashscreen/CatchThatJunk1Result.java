package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CatchThatJunk1Result extends AppCompatActivity {
    private Button nextLvl, tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_that_junk_result);

        nextLvl = findViewById(R.id.nextLevelBtn);
        tryAgain = findViewById(R.id.tryAgainBtn);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);
        TextView textCongrats = findViewById(R.id.textCongrats);

        int score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText("Score : " + score);

        // High Score
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE",0);

        if (score > highScore){
            // Update HighScore
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE",score);
            editor.apply();

            highScoreLabel.setText("High Score : " + score);


        } else {
            highScoreLabel.setText("High Score : " + highScore);
        }

        if (score >= 300) {
            nextLvl.setVisibility(View.VISIBLE);
            textCongrats.setVisibility(View.VISIBLE);
        }
        else {
            nextLvl.setVisibility(View.INVISIBLE);
            textCongrats.setVisibility(View.INVISIBLE);
        }

        nextLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatchThatJunk1Result.this, CatchThatJunk2Home.class);
                startActivity(intent);
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatchThatJunk1Result.this, CatchThatJunkLevel1.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {}
}