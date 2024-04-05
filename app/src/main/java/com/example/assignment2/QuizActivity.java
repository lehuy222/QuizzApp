package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private Question[] questions = new Question[]{
            new Question("How many hand are there on human body", new String[]{"1", "2", "3", "4"}, 1),
            new Question("Best country in the World?", new String[]{"America", "Thailand", "Australia", "Egypt"}, 2),
            new Question("1 Kilometer equal to?", new String[]{"1000 meters", "10000 meters", "100 meters", "10 meters"}, 0),
            new Question("Biggest animal in the world?", new String[]{"Elephant", "Whale", "Polar Bear", "Human"}, 1),
            new Question("An British celebrity?", new String[]{"Will Smith", "Halle Berry", "Emma Watson", "Donald Trump"}, 2),
    };
    private int currentQuestionIndex = 0;
    private int score = 0;

    private ProgressBar progressBar;
    private TextView tvQuestion;
    private RadioGroup radioGroupOptions;
    private RadioButton[] radioButtons = new RadioButton[4];
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressBar = findViewById(R.id.progressBar);
        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Initialize radioButtons
        radioButtons[0] = findViewById(R.id.radioButton1);
        radioButtons[1] = findViewById(R.id.radioButton2);
        radioButtons[2] = findViewById(R.id.radioButton3);
        radioButtons[3] = findViewById(R.id.radioButton4);

        progressBar.setMax(questions.length);
        updateQuestion();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupOptions.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                int selectedIndex = radioGroupOptions.indexOfChild(selectedRadioButton);

                if (selectedIndex == questions[currentQuestionIndex].getCorrectAnswer()) {
                    score++;
                    selectedRadioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                } else {
                    radioButtons[questions[currentQuestionIndex].getCorrectAnswer()]
                            .setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    selectedRadioButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }

                // Disable options to prevent changing the answer
                for (RadioButton rb : radioButtons) {
                    rb.setEnabled(false);
                }

                // Wait for 2 seconds before moving to the next question
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                if (currentQuestionIndex < questions.length - 1) {
                                    currentQuestionIndex++;
                                    updateQuestion();
                                } else {
                                    // Quiz is finished
                                    Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                                    String userName = getIntent().getStringExtra("USER_NAME");
                                    intent.putExtra("SCORE", score);
                                    intent.putExtra("USER_NAME", userName);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        },
                        2000);
            }
        });
    }

    private void updateQuestion() {
        Question currentQuestion = questions[currentQuestionIndex];
        tvQuestion.setText(currentQuestion.getQuestion());
        progressBar.setProgress(currentQuestionIndex + 1);

        // Reset options and background colors
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i].setText(currentQuestion.getOptions()[i]);
            radioButtons[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            radioButtons[i].setEnabled(true);
        }
        radioGroupOptions.clearCheck();
    }
}