package com.david.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.david.topquiz.R;
import com.david.topquiz.model.User;

public class MainActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private Button mPlayButton;
    private TextView mWelcomeTextView;
    private TextView mScoreInfo;
    private User mUser;
    public final int GAME_ACTIVITY_REQUEST_CODE = 25;
    private final String FILE_USER_INFO = "FILE_USER_INFO";
    private SharedPreferences mSharedPreferences;
    private final String USERNAME_INFO = "USERNAME_INFO";
    private final String USER_SCORE = "USER_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::onCreate");

        mNameEditText = findViewById(R.id.main_activity_name_text_edit);
        mPlayButton = findViewById(R.id.main_activity_play_button);
        mWelcomeTextView = findViewById(R.id.main_activity_welcome_text_view);
        mScoreInfo = findViewById(R.id.main_activity_score_info_text_view);
        mPlayButton.setEnabled(false);

        mSharedPreferences = getSharedPreferences(FILE_USER_INFO, MODE_PRIVATE);

        //On vérifier si une partie à déjas eu lieu
        String firstName = mSharedPreferences.getString(USERNAME_INFO, null);
        if (mSharedPreferences.getString(USERNAME_INFO, null) != null) {
            mWelcomeTextView.setText("Welcome " + firstName);
            mScoreInfo.setText("Your precedent score is " + mSharedPreferences.getInt(USER_SCORE, 0));
            mNameEditText.setText(firstName);
            mPlayButton.setEnabled(true);
        }

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() >= 3) mPlayButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                mUser = new User(mNameEditText.getText().toString(), 0);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(USERNAME_INFO, mUser.getName()).apply();
            editor.putInt(USER_SCORE, score).apply();
        }
    }

    @Override
    protected void onStart() {
        System.out.println("MainActivity::onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        System.out.println("MainActivity::onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("MainActivity::onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        System.out.println("MainActivity::onPause()");
        super.onPause();
    }

    @Override
    protected void onResume() {
        System.out.println("MainActivity::onResume()");
        super.onResume();
    }
}