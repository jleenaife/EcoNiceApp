package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CatchThatJunkResult extends AppCompatActivity {
    private Button nextLvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_that_junk_result);

        nextLvl = findViewById(R.id.nextLevelBtn);

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

        if (score >= 250) {
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
                Intent intent = new Intent(CatchThatJunkResult.this, CatchThatJunkLevel2.class);
                startActivity(intent);
            }
        });
    }

    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), CatchThatJunkLevel1.class));
    }

    @Override
    public void onBackPressed() {}
}