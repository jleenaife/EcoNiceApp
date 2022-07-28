package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CatchThatJunkLevel2 extends AppCompatActivity {

    // Elements
    private TextView scoreLabel2, startLabel3, startLabel4;
    private ImageView trashbin2, poop, bottle, bag, paper, paint;

    // Size
    private int screenWidth;
    private int frameHeight;
    private int trashbinSize;

    // Position
    private  float trashbin2Y;
    private float poopX, poopY;
    private float bottleX, bottleY;
    private float bagX, bagY;
    private float paperX, paperY;
    private float paintX, paintY;

    //Score
    private int score2;

    //Timer
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    // Status
    private boolean action_flg = false;
    private boolean start_flg = false;

    // SoundPlayer
    private SoundPlayer soundPlayer;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_that_junk_level2);

        soundPlayer = new SoundPlayer(this);

        scoreLabel2 = findViewById(R.id.scoreLabel2);
        startLabel3 = findViewById(R.id.startLabel3);
        startLabel4 = findViewById(R.id.startLabel4);
        trashbin2 = findViewById(R.id.trashbin2);
        poop = findViewById(R.id.poop);
        bottle = findViewById(R.id.bottle);
        bag = findViewById(R.id.bag);
        paper = findViewById(R.id.paper);
        paint = findViewById(R.id.paint);

        // Screen Size
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        int screenHeight = size.y;

        // Initial Positions
        poop.setX(-80.0f);
        poop.setY(-80.0f);
        bottle.setX(-80.0f);
        bottle.setY(-80.0f);
        bag.setX(-80.0f);
        bag.setY(-80.0f);
        paper.setX(-80.0f);
        paper.setY(-80.0f);
        paint.setX(-80.0f);
        paint.setY(-80.0f);

        scoreLabel2.setText("Score : " + score2);
        //scoreLabel.setText(getString(R.string.score));

        mediaPlayer = MediaPlayer.create(this, R.raw.junk);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void changePos() {

        hitCheck();

        // Banana
        poopX -= 15;
        if (poopX < 0){
            poopX = screenWidth + 20;
            poopY = (float)Math.floor(Math.random() * (frameHeight - poop.getHeight()));
        }
        poop.setX(poopX);
        poop.setY(poopY);

        // Battery
        bottleX -= 16;
        if (bottleX < 0){
            bottleX = screenWidth + 10;
            bottleY = (float)Math.floor(Math.random() * (frameHeight - bottle.getHeight()));
        }
        bottle.setX(bottleX);
        bottle.setY(bottleY);

        // Can
        bagX -= 16;
        if (bagX < 0){
            bagX = screenWidth + 1000;
            bagY = (float)Math.floor(Math.random() * (frameHeight - bag.getHeight()));
        }
        bag.setX(bagX);
        bag.setY(bagY);

        // Carrot
        paperX -= 15;
        if (paperX < 0){
            paperX = screenWidth + 70;
            paperY = (float)Math.floor(Math.random() * (frameHeight - paper.getHeight()));
        }
        paper.setX(paperX);
        paper.setY(paperY);

        paintX -= 20;
        if (paintX < 0){
            paintX = screenWidth + 200;
            paintY = (float)Math.floor(Math.random() * (frameHeight - paint.getHeight()));
        }
        paint.setX(paintX);
        paint.setY(paintY);


        if (action_flg){
            // Touching
            trashbin2Y -= 20;
        } else {
            // Releasing
            trashbin2Y += 20;
        }

        if (trashbin2Y < 0) trashbin2Y = 0;
        if (trashbin2Y > frameHeight - trashbinSize) trashbin2Y = frameHeight - trashbinSize;
        trashbin2.setY(trashbin2Y);

        scoreLabel2.setText("Score : " + score2);
        //scoreLabel.setText(getString(R.string.score));
    }

    public void hitCheck() {

        float poopCenterX = poopX + poop.getWidth() / 2.0f;
        float poopCenterY = poopY + poop.getHeight() / 2.0f;

        if (0 <= poopCenterX && poopCenterX <= trashbinSize &&
                trashbin2Y <= poopCenterY && poopCenterY <= trashbin2Y + trashbinSize) {

            soundPlayer.playOverSound();

            // Game Over
            if (timer != null) {
                timer.cancel();
                timer = null;

                // Show Result Activity
                Intent intent = new Intent(getApplicationContext(), CatchThatJunk2Result.class);
                intent.putExtra("SCORE", score2);
                startActivity(intent);
            }
        }


        float paperCenterX = paperX + paper.getWidth() / 2.0f;
        float paperCenterY = paperY + paper.getHeight() / 2.0f;

        if (0 <= paperCenterX && paperCenterX <= trashbinSize &&
                trashbin2Y <= paperCenterY && paperCenterY <= trashbin2Y + trashbinSize) {

            soundPlayer.playOverSound();

            // Game Over
            if (timer != null) {
                timer.cancel();
                timer = null;

                // Show Result Activity
                Intent intent = new Intent(getApplicationContext(), CatchThatJunk2Result.class);
                intent.putExtra("SCORE", score2);
                startActivity(intent);
            }
        }

        float bottleCenterX = bottleX + bottle.getWidth() / 2.0f;
        float bottleCenterY = bottleY + bottle.getHeight() / 2.0f;

        if (0 <= bottleCenterX && bottleCenterX <= trashbinSize &&
                trashbin2Y <= bottleCenterY && bottleCenterY <= trashbin2Y + trashbinSize) {

            bottleX = -100.0f;
            score2 += 10;
            soundPlayer.playHitSound();

        }

        float bagCenterX = bagX + bag.getWidth() / 2.0f;
        float bagCenterY = bagY + bag.getHeight() / 2.0f;

        if (0 <= bagCenterX && bagCenterX <= trashbinSize &&
                trashbin2Y <= bagCenterY && bagCenterY <= trashbin2Y + trashbinSize) {
            bagX = -100.0f;
            score2 += 20;
            soundPlayer.playHitSound();
        }

        float paintCenterX = paintX + paint.getWidth() / 2.0f;
        float paintCenterY = paintY + paint.getHeight() / 2.0f;

        if (0 <= paintCenterX && paintCenterX <= trashbinSize &&
                trashbin2Y <= paintCenterY && paintCenterY <= trashbin2Y + trashbinSize) {

            paintX = -100.0f;
            score2 += 30;
            soundPlayer.playHitSound();

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!start_flg) {
            start_flg = true;

            // FrameHeight
            FrameLayout frameLayout = findViewById(R.id.frame);
            frameHeight = frameLayout.getHeight();

            // Trashbin
            trashbin2Y = trashbin2.getY();
            trashbinSize = trashbin2.getHeight();
            startLabel3.setVisibility(View.GONE);
            startLabel4.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(
                            new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 30);

        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(CatchThatJunkLevel2.this, CatchThatJunk2Home.class);
                        startActivity(intent);
                        CatchThatJunkLevel2.this.finish();
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

