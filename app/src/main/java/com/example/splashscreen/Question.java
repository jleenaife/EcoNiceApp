package com.example.splashscreen;

public class Question {

    String question;
    String optionA;
    String optionB;
    int correctAns;


    public Question(String question, String optionA, String optionB, int correctAns) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.correctAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }
}
