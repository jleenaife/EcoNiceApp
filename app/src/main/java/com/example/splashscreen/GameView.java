package com.example.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private Turtle turtle;
    private Handler handler;
    private Runnable r;
    private ArrayList<Pillar> arrPillars;
    private int sumpillar, distance;
    private int score, bestscore;
    private boolean start;
    private Context context;
    private int soundJump;
    private float volume;
    private boolean loadedsound;
    private SoundPool soundPool;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        SharedPreferences sp = context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
        if(sp!=null){
            bestscore = sp.getInt("bestscore", 0);
        }
        score = 0;
        bestscore = 0;
        start = false;
        handler = new Handler();
        initPillar();
        initTurtle();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        if(Build.VERSION.SDK_INT>=21){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttributes).setMaxStreams(5);
            this.soundPool = builder.build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                loadedsound = true;
            }
        });

        soundJump = this.soundPool.load(context, R.raw.jump_02, 1);
    }

    private void initPillar() {
        sumpillar = 6;
        distance = 700*Constants.SCREEN_HEIGHT/1920;
        arrPillars = new ArrayList<>();
        for (int i =0; i < sumpillar; i++){
            if(i<sumpillar/2){
                this.arrPillars.add(new Pillar (Constants.SCREEN_WIDTH+i*((Constants.SCREEN_WIDTH+200*Constants.SCREEN_WIDTH/1000)/(sumpillar/2)),
                        0, 300*Constants.SCREEN_WIDTH/1000, Constants.SCREEN_HEIGHT/2));
                this.arrPillars.get(this.arrPillars.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pillar1));
                this.arrPillars.get(this.arrPillars.size()-1).randomY();
            }else {
                this.arrPillars.add(new Pillar(this.arrPillars.get(i-sumpillar/2).getX(), this.arrPillars.get(i-sumpillar/2).getY()
                        +this.arrPillars.get(i-sumpillar/2).getHeight() + this.distance, 300*Constants.SCREEN_WIDTH/1000, Constants.SCREEN_HEIGHT/2));
                this.arrPillars.get(this.arrPillars.size()-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.pillar2));
            }
        }
    }

    private void initTurtle() {
        turtle = new Turtle();
        turtle.setWidth(200*Constants.SCREEN_WIDTH/1000);
        turtle.setHeight(200*Constants .SCREEN_HEIGHT/1920);
        turtle.setX(100*Constants.SCREEN_WIDTH/1000);
        turtle.setY(Constants.SCREEN_HEIGHT/2-turtle.getHeight()/2);
        ArrayList<Bitmap> arrBms = new ArrayList<>();
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.turtle1));
        arrBms.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.turtle2));
        turtle.setArrBms(arrBms);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        if(start){
            turtle.draw(canvas);
            for(int i = 0; i < sumpillar; i++) {
                if(turtle.getRect().intersect(arrPillars.get(i).getRect())||turtle.getY()-turtle.getHeight()<0||turtle.getY()>Constants.SCREEN_HEIGHT){
                    Pillar.speed = 0;
                    SaveTheTurtle.txt_score_over.setText(SaveTheTurtle.txt_score.getText());
                    SaveTheTurtle.txt_best_score.setText("best: " + bestscore);
                    SaveTheTurtle.txt_score.setVisibility(INVISIBLE);
                    SaveTheTurtle.rl_game_over.setVisibility(VISIBLE);
                }

                if (this.turtle.getX() + this.turtle.getWidth() > arrPillars.get(i).getX() + arrPillars.get(i).getWidth() / 2
                        && this.turtle.getX() + this.turtle.getWidth() <= arrPillars.get(i).getX() + arrPillars.get(i).getWidth() / 2 + Pillar.speed
                        && i < sumpillar / 2) {
                    score++;
                    if(score>bestscore){
                        bestscore = score;
                        SharedPreferences sp = context.getSharedPreferences("gamesetting", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("'bestScore", bestscore);
                        editor.apply();
                    }
                    SaveTheTurtle.txt_score.setText("" + score);

                }
                if (this.arrPillars.get(i).getX() < -arrPillars.get(i).getWidth()) {
                    this.arrPillars.get(i).setX(Constants.SCREEN_WIDTH);
                    if (i < sumpillar / 2) {
                        arrPillars.get(i).randomY();
                    } else {
                        arrPillars.get(i).setY(this.arrPillars.get(i - sumpillar / 2).getY()
                                + this.arrPillars.get(i - sumpillar / 2).getHeight() + this.distance);
                    }
                }
                this.arrPillars.get(i).draw(canvas);
            }
        } else {
            if(turtle.getY()>Constants.SCREEN_HEIGHT/2){
                turtle.setDrop(-15*Constants.SCREEN_HEIGHT/1920);
            }
            turtle.draw(canvas);
        }
        handler.postDelayed(r, 10);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            turtle.setDrop(-15);
            if(loadedsound){
                int streamId = this.soundPool.play(this.soundJump, (float)0.5, (float)0.5, 1, 0, 1f);
            }
        }
        return true;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void reset() {
        SaveTheTurtle.txt_score.setText("0");
        score=0;
        initPillar();
        initTurtle();
    }
}
