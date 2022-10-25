package com.example.ps2;

public class Question {
    private int questionId;
    private boolean correctAnswer;

    public Question(int questionId, boolean correctAnswer){
        this.correctAnswer = correctAnswer;
        this.questionId = questionId;
    }

    public boolean isTrueAnswer(){
        return this.correctAnswer;
    }

    public int getQuestionId(){
        return questionId;
    }
}
