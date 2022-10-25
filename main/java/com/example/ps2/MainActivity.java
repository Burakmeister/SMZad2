package com.example.ps2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button butTrue;
    private Button butFalse;
    private Button butNext;
    private Button butAnswer;
    private TextView question;

    private Question[] questions ={
            new Question(R.string.q1,false),
            new Question(R.string.q2,false),
            new Question(R.string.q3,true),
            new Question(R.string.q4,false),
            new Question(R.string.q5,true)};
    private int curQuestion=0;
    private boolean answerWasShown = false;
    private static final String KEY_CURRENT_INDEX = "curQuestion";
    public static final String KEY_EXTRA_ANSWER = "com.example.ps2.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity", "Wywołana została metoda onSaveInstanceState()");
        outState.putInt(KEY_CURRENT_INDEX, curQuestion);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Metoda cyklu życia", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Metoda cyklu życia", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Metoda cyklu życia", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Metoda cyklu życia", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Metoda cyklu życia", "onResume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "Wywołana została metoda cyklu życia onCreate()");
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            curQuestion = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        this.butTrue = findViewById(R.id.trueButton);
        this.butFalse = findViewById(R.id.falseButton);
        this.butNext = findViewById(R.id.nextButton);
        this.question = findViewById(R.id.question);
        this.butAnswer = findViewById(R.id.answerBut);
        this.setQuestion();

        butTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        butFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curQuestion = (curQuestion+1)%questions.length;
                answerWasShown = false;
                setQuestion();
            }
        });
        butAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[curQuestion].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        });
    }

    private void checkAnswer(boolean userAnswer){
        boolean correctAnswer = questions[curQuestion].isTrueAnswer();
        int resultMessageId = 0;
        if(answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        }else{
            if(userAnswer == correctAnswer){
                resultMessageId = R.string.correct;
            }else{
                resultMessageId = R.string.incorrect;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

    private void setQuestion(){
        question.setText(questions[curQuestion].getQuestionId());
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){
            return;
        }if(requestCode == REQUEST_CODE_PROMPT){
            if(data == null){
                return;
            }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }
}