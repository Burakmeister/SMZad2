package com.example.ps2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {
    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.ps2.answerShown";

    private boolean correctAnswer;

    private Button showAnswerBut;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);

        this.showAnswerBut = findViewById(R.id.showAnswerBut);
        this.answerTextView = findViewById(R.id.answer_text_view);

        showAnswerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = correctAnswer ? R.string.butTrue : R.string.butFalse;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean answerWasShawn){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShawn);
        setResult(RESULT_OK, resultIntent);
    }
}