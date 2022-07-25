package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CatchThatJunk2Result extends AppCompatActivity {
    private Button continueLvl, tryAgain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_that_junk2_result);

        continueLvl = findViewById(R.id.continueBtn);
        tryAgain2 = findViewById(R.id.tryAgainBtn2);

        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);
        TextView textCongrats = findViewById(R.id.textCongrats);

        int score2 = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText("Score : " + score2);

        // High Score
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore2 = sharedPreferences.getInt("HIGH_SCORE",0);

        if (score2 > highScore2){
            // Update HighScore
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE",score2);
            editor.apply();

            highScoreLabel.setText("High Score : " + score2);


        } else {
            highScoreLabel.setText("High Score : " + highScore2);
        }

        if (score2 >= 300) {
            continueLvl.setVisibility(View.VISIBLE);
            textCongrats.setVisibility(View.VISIBLE);
        }
        else {
            continueLvl.setVisibility(View.INVISIBLE);
            textCongrats.setVisibility(View.INVISIBLE);
        }

        continueLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatchThatJunk2Result.this, Storyline.class);
                startActivity(intent);
            }
        });

        tryAgain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatchThatJunk2Result.this, CatchThatJunkLevel2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You will lose your progress. Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}