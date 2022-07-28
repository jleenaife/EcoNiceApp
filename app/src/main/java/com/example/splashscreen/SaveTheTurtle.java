package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SaveTheTurtle extends AppCompatActivity {
    public static TextView txt_score, txt_best_score, txt_score_over;
    public static RelativeLayout rl_game_over;
    public static RelativeLayout rl_complete;
    public static Button btn_start, startAgain, quitApp;
    private GameView gv;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_save_the_turtle);
        txt_score = findViewById(R.id.txt_score);
        txt_best_score = findViewById(R.id.txt_bestScore);
        txt_score_over = findViewById(R.id.txt_scoreOver);
        rl_game_over = findViewById(R.id.turtle_gameOver);
        rl_complete = findViewById(R.id.turtle_End);
        gv = findViewById(R.id.gv);
        btn_start = findViewById(R.id.btn_startTurtle);
        startAgain = findViewById(R.id.btn_startAgain);
        quitApp = findViewById(R.id.btn_QuitApp);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_score.setVisibility(view.VISIBLE);
                btn_start.setVisibility(View.INVISIBLE);
                gv.setStart(true);
            }
        });
        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveTheTurtle.this, Nameinput_page.class);
                startActivity(intent);
                SaveTheTurtle.this.finish();
            }
        });
        quitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitGameApp();
            }
        });
        rl_game_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(View.VISIBLE);
                rl_game_over.setVisibility(View.INVISIBLE);
                gv.setStart(false);
                gv.reset();
            }
        });
        rl_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_game_over.setVisibility(View.INVISIBLE);
                gv.setStart(false);
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.turtle);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    public void ExitGameApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
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