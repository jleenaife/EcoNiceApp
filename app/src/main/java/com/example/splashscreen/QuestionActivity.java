package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question, qCount, timer;
    private Button option1, option2;
    private List<Question> questionList;
    private int quesNum;
    private CountDownTimer countDown;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        qCount = findViewById(R.id.quest_num);
        timer =findViewById(R.id.countdown);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);

        getQuestionList();

        score = 0;
    }
    private void getQuestionList()
    {
        questionList = new ArrayList<>();

        questionList.add(new Question("Question 1", "Biodegradable", "Non-Biodegradable", 1));
        questionList.add(new Question("Question 2", "Biodegradable", "Non-Biodegradable", 2));
        questionList.add(new Question("Question 3", "Biodegradable", "Non-Biodegradable", 1));
        questionList.add(new Question("Question 4", "Biodegradable", "Non-Biodegradable", 1));
        questionList.add(new Question("Question 5", "Biodegradable", "Non-Biodegradable", 2));
        questionList.add(new Question("Question 6", "Biodegradable", "Non-Biodegradable", 1));
        questionList.add(new Question("Question 7", "Biodegradable", "Non-Biodegradable", 2));
        questionList.add(new Question("Question 8", "Biodegradable", "Non-Biodegradable", 2));
        questionList.add(new Question("Question 9", "Biodegradable", "Non-Biodegradable", 1));
        questionList.add(new Question("Question 10", "Biodegradable", "Non-Biodegradable", 2));

        setQuestion();
    }

    public void setQuestion()
    {
        timer.setText(String.valueOf(10));

        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());

        qCount.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));

        startTimer();

        quesNum = 0;
    }

    private void startTimer()
    {
        countDown = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished <10)
                timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();
            }
        };

        countDown.start();
    }



    @Override
    public void onClick(View v) {

        int selectedOption = 0;

        switch (v.getId())
        {
            case R.id.option1:
                selectedOption = 1;
                break;
            case R.id.option2:
                selectedOption = 2;
                break;
            default:
        }
        countDown.cancel();
        checkAnswer(selectedOption, v);
    }

    private void checkAnswer(int selectedOption, View view)
    {

        if(selectedOption == questionList.get(quesNum).getCorrectAns())
        {
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }
        else
        {
            //Wrong Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionList.get(quesNum).getCorrectAns())
            {
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 2000);


    }

    private void changeQuestion()
    {
        if( quesNum < questionList.size() -1)
        {
            quesNum++;

            playAnim(question, 0, 0);
            playAnim(option1, 0, 1);
            playAnim(option2, 0, 2);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionList.size()));

            timer.setText((String.valueOf(10)));
            startTimer();
        }
        else
        {
            // Go to Score Activity
            Intent intent = new Intent(QuestionActivity.this,ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent);
            //QuestionActivity.this.finish();
        }
    }

    private void playAnim(View view, final int value, int viewNum)
    {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                            if (value == 0)
                            {
                                switch (viewNum)
                                {
                                    case 0:
                                        ((TextView)view).setText(questionList.get(quesNum).getQuestion());
                                        break;
                                    case 1:
                                        ((Button)view).setText(questionList.get(quesNum).getOptionA());
                                        break;
                                    case 2:
                                        ((Button)view).setText(questionList.get(quesNum).getOptionB());
                                        break;
                                }


                                if(viewNum != 0)
                                    ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFC54D")));

                                playAnim(view, 1,viewNum);
                            }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDown.cancel();
    }
}