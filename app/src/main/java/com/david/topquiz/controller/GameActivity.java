package com.david.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.david.topquiz.R;
import com.david.topquiz.model.Question;
import com.david.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;
    private TextView mQuestion;
    private final QuestionBank mQuestionBank = createQuestionBank();
    private Question mCurrentQuestion;
    private int mNumberOfQuestion;
    private int mScore;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private final String SCORE_INSTANCE_STATE = "SCORE_INSTANCE_STATE";
    private final String QUESTION_INSTANCE_STATE = "QUESTION_INSTANCE_STATE";
    private final String QUESTION_RESET_INSTANCE_STATE = "QUESTION_RESET_INSTANCE_STATE";
    private boolean mEnableTouch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mAnswer1 = findViewById(R.id.activity_game_answer1_button);
        mAnswer2 = findViewById(R.id.activity_game_answer2_button);
        mAnswer3 = findViewById(R.id.activity_game_answer3_button);
        mAnswer4 = findViewById(R.id.activity_game_answer4_button);
        mQuestion = findViewById(R.id.activity_game_question_textView);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(SCORE_INSTANCE_STATE);
            mCurrentQuestion = savedInstanceState.getParcelable(QUESTION_INSTANCE_STATE);
            mNumberOfQuestion = savedInstanceState.getInt(QUESTION_RESET_INSTANCE_STATE);
        } else {
            mNumberOfQuestion = 4;
            mScore = 0;
            initialiseQuestion(mQuestionBank.getNextQuestion());
        }

        mEnableTouch = true;

        mAnswer1.setOnClickListener(this);
        mAnswer2.setOnClickListener(this);
        mAnswer3.setOnClickListener(this);
        mAnswer4.setOnClickListener(this);
    }

    private QuestionBank createQuestionBank() {
        Question question1 = new Question("What's the first name of the developer of this game", Arrays.asList(
                "Jean",
                "Paul",
                "Christine",
                "David"
        ), 3),
        question2 = new Question("What's the capital of France", Arrays.asList(
                "Paris",
                "London",
                "Auth",
                "Marat"
        ), 0),
        question3 = new Question("What is the most important diagram UML", Arrays.asList(
                "Class diagram",
                "Object diagram",
                "Package diagram",
                "Object diagram"
        ), 0),
        question4 = new Question("What is a graph", Arrays.asList(
                "A set of dots and lines connecting some of these dots",
                "A diagram",
                "A soccer field",
                "A square"
        ), 0),
        question5 = new Question("cycle eulérien", Arrays.asList(
                "Une chaîne pour les chiens",
                "Une chaîne eulérienne dont les extémités sont confondus",
                "Une vache",
                "Rien du tout"
        ), 1);

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4));
    }

    @Override
    public void onClick(View view) {
        mEnableTouch = false;
        int index = (int) view.getTag();

        if (index == mCurrentQuestion.getCorrectAnswerIndex()) {
            Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect answer", Toast.LENGTH_SHORT).show();
        }

        if(mNumberOfQuestion > 0) {
            new Handler().postDelayed((Runnable) () -> {
                            mEnableTouch = true;
                    initialiseQuestion(mQuestionBank.getNextQuestion());
                    }, 2_000);

        } else {
            endGame();
        }

    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Well")
                .setMessage("Score: " + mScore)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void initialiseQuestion(Question currentQuestion) {
        mQuestion.setText(currentQuestion.getQuestion());
        mAnswer1.setText(currentQuestion.getAnswers().get(0));
        mAnswer2.setText(currentQuestion.getAnswers().get(1));
        mAnswer3.setText(currentQuestion.getAnswers().get(2));
        mAnswer4.setText(currentQuestion.getAnswers().get(3));
        mCurrentQuestion = currentQuestion;
        mNumberOfQuestion--;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouch && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_INSTANCE_STATE, mScore);
        outState.putParcelable(QUESTION_INSTANCE_STATE, mCurrentQuestion);
        outState.putInt(QUESTION_RESET_INSTANCE_STATE, mNumberOfQuestion);
    }


}