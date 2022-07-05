package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question, qCount, timer;
    private Button option1, option2;
    private List<Question> questionList;
    int quesNum;

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
        CountDownTimer countDown = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

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

        checkAnswer(selectedOption);
    }

    private void checkAnswer(int selectedOption)
    {

        if(selectedOption == questionList.get(quesNum).getCorrectAns())
        {
            //Right Answer
        }
        else
        {
            //Wrong Answer
        }

        changeQuestion();
    }

    private void changeQuestion()
    {
        if( quesNum < questionList.size() -1)
        {

        }
        else
        {

        }
    }
}