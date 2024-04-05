package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView tvScore, tvName;
    private Button btnTakeNewQuiz, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvScore);
        btnTakeNewQuiz = findViewById(R.id.btnTakeNewQuiz);
        btnFinish = findViewById(R.id.btnFinish);

        int score = getIntent().getIntExtra("SCORE", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        tvName.setText("Congratulation " + userName);
        tvScore.setText("Your Score: " + score);

        btnTakeNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Close the app
            }
        });
    }
}