package com.example.assignment2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etUserName);
        Button btnStartQuiz = findViewById(R.id.btnStartQuiz);

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        String userName = etUserName.getText().toString();
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
    }
}