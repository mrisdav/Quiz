package com.david.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.david.topquiz.R;

public class MainActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.main_activity_name_text_edit);
        mPlayButton = findViewById(R.id.main_activity_play_button);

        mPlayButton.setEnabled(false);
    }
}