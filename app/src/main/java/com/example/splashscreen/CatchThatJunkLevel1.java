package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
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

public class CatchThatJunkLevel1 extends AppCompatActivity {

    // Elements
    private TextView scoreLabel, startLabel;
    private ImageView trashbin, banana, battery, can, carrot, leaves1;

    // Size
    private int screenWidth;
    private int frameHeight;
    private int trashbinSize;

    // Position
    private  float trashbinY;
    private float bananaX, bananaY;
    private float batteryX, batteryY;
    private float canX, canY;
    private float carrotX, carrotY;
    private float leaves1X, leaves1Y;

    //Score
    private int score;

    //Timer
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    // Status
    private boolean action_flg = false;
    private boolean start_flg = false;

    // SoundPlayer
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_that_junk);

        soundPlayer = new SoundPlayer(this);

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        trashbin = findViewById(R.id.trashbin);
        banana = findViewById(R.id.banana);
        battery = findViewById(R.id.battery);
        can = findViewById(R.id.can);
        carrot = findViewById(R.id.carrot);
        leaves1 = findViewById(R.id.leaves1);

        // Screen Size
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        int screenHeight = size.y;

        // Initial Positions
        banana.setX(-80.0f);
        banana.setY(-80.0f);
        battery.setX(-80.0f);
        battery.setY(-80.0f);
        can.setX(-80.0f);
        can.setY(-80.0f);
        carrot.setX(-80.0f);
        carrot.setY(-80.0f);
        leaves1.setX(-80.0f);
        leaves1.setY(-80.0f);

        scoreLabel.setText("Score : " + score);
        //scoreLabel.setText(getString(R.string.score));
    }

    public void changePos() {

        hitCheck();

        // Banana
        bananaX -= 15;
        if (bananaX < 0){
            bananaX = screenWidth + 20;
            bananaY = (float)Math.floor(Math.random() * (frameHeight - banana.getHeight()));
        }
        banana.setX(bananaX);
        banana.setY(bananaY);

        // Battery
        batteryX -= 16;
        if (batteryX < 0){
            batteryX = screenWidth + 10;
            batteryY = (float)Math.floor(Math.random() * (frameHeight - battery.getHeight()));
        }
        battery.setX(batteryX);
        battery.setY(batteryY);

        // Can
        canX -= 16;
        if (canX < 0){
            canX = screenWidth + 1000;
            canY = (float)Math.floor(Math.random() * (frameHeight - can.getHeight()));
        }
        can.setX(canX);
        can.setY(canY);

        // Carrot
        carrotX -= 15;
        if (carrotX < 0){
            carrotX = screenWidth + 70;
            carrotY = (float)Math.floor(Math.random() * (frameHeight - carrot.getHeight()));
        }
        carrot.setX(carrotX);
        carrot.setY(carrotY);

        // leaves1
        leaves1X -= 20;
        if (leaves1X < 0){
            leaves1X = screenWidth + 200;
            leaves1Y = (float)Math.floor(Math.random() * (frameHeight - leaves1.getHeight()));
        }
        leaves1.setX(leaves1X);
        leaves1.setY(leaves1Y);


        if (action_flg){
            // Touching
            trashbinY -= 20;
        } else {
            // Releasing
            trashbinY += 20;
        }

        if (trashbinY < 0) trashbinY = 0;
        if (trashbinY > frameHeight - trashbinSize) trashbinY = frameHeight - trashbinSize;
        trashbin.setY(trashbinY);

        scoreLabel.setText("Score : " + score);
        //scoreLabel.setText(getString(R.string.score));
    }

    public void hitCheck() {

        // Banana
        float bananaCenterX = bananaX + banana.getWidth() / 2.0f;
        float bananaCenterY = bananaY + banana.getHeight() / 2.0f;

        if (0 <= bananaCenterX && bananaCenterX <= trashbinSize &&
                trashbinY <= bananaCenterY && bananaCenterY <= trashbinY + trashbinSize) {
            bananaX = -100.0f;
            score += 20;
            soundPlayer.playHitSound();
        }


        // Carrot
        float carrotCenterX = carrotX + carrot.getWidth() / 2.0f;
        float carrotCenterY = carrotY + carrot.getHeight() / 2.0f;

        if (0 <= carrotCenterX && carrotCenterX <= trashbinSize &&
                trashbinY <= carrotCenterY && carrotCenterY <= trashbinY + trashbinSize) {
            carrotX = -100.0f;
            score += 10;
            soundPlayer.playHitSound();
        }

        // Leaves1
        float leaves1CenterX = leaves1X + leaves1.getWidth() / 2.0f;
        float leaves1CenterY = leaves1Y + leaves1.getHeight() / 2.0f;

        if (0 <= leaves1CenterX && leaves1CenterX <= trashbinSize &&
                trashbinY <= leaves1CenterY && leaves1CenterY <= trashbinY + trashbinSize) {
            leaves1X = -100.0f;
            score += 30;
            soundPlayer.playHitSound();
        }

        // Battery
        float batteryCenterX = batteryX + battery.getWidth() / 2.0f;
        float batteryCenterY = batteryY + battery.getHeight() / 2.0f;

        if (0 <= batteryCenterX && batteryCenterX <= trashbinSize &&
                trashbinY <= batteryCenterY && batteryCenterY <= trashbinY + trashbinSize) {

            soundPlayer.playOverSound();

            // Game Over
            if (timer != null){
                timer.cancel();
                timer = null;
            }

            // Show Result Activity
            Intent intent = new Intent(getApplicationContext(), CatchThatJunkResult.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);
        }

        // Can
        float canCenterX = canX + can.getWidth() / 2.0f;
        float canCenterY = canY + can.getHeight() / 2.0f;

        if (0 <= canCenterX && canCenterX <= trashbinSize &&
                trashbinY <= canCenterY && canCenterY <= trashbinY + trashbinSize) {

            soundPlayer.playOverSound();

            // Game Over
            if (timer != null){
                timer.cancel();
                timer = null;
            }

            // Show Result Activity
            Intent intent = new Intent(getApplicationContext(), CatchThatJunkResult.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);
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
            trashbinY = trashbin.getY();
            trashbinSize = trashbin.getHeight();
            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 25);

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
    public void onBackPressed() {}
}





















