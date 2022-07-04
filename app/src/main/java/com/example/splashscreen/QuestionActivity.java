package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView question, qCount, timer;
    private Button option1, option2;
    private List<Question> questionList;

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

    @Override
    public void onClick(View view) {

    }
}