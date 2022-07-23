package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity2 extends AppCompatActivity {

    private TextView score;
    private Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score2);

        score = findViewById(R.id.sa_score2);
        done = findViewById(R.id.sa_done2);

        String score_str = getIntent().getStringExtra("SCORE");
        score.setText(score_str);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondGame();
            }
        });
    }

    public void openSecondGame(){
        Intent intent = new Intent(this, CatchThatJunk1Home.class);
        startActivity(intent);
    }
}