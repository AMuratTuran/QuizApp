package com.example.muratturan.quizappex;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;


public class QuestionActivity extends Activity {

    private ArrayList<String> MultCorrect = new ArrayList<>();
    private ArrayList<String> Wronganswers = new ArrayList<>();
    private ArrayList<String> colorList = new ArrayList<>();
    private ArrayList<String> clickList = new ArrayList<>();
    private String question;
    private DatabaseReference AnswerDatabase;
    private DatabaseReference wrongAnsDatabase;
    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private Button answer4Button;
    private Button[] ButtonList;
    private ArrayList<String> temp;
    private ArrayList<String> mFalseAnswer = new ArrayList<>();
    private int buttonValue;
    private int index;
    private int totalPoints;
    private int earnedPoints;
    private int toastText = 0;
    private String buttonPointString;
    private boolean isTrue;
    private boolean isFalse;
    private boolean isOutOfTime;
    private int lostPoints = 50;
    private int remainingTime;
    private boolean running = true;
    private TextView scoreBoard;
    private String categoryTag;
    private int questionNumber;
    private int totalTime ;
    Handler handler = new Handler();
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final RelativeLayout questionLayout = findViewById(R.id.questionLayout);
        questionLayout.setVisibility(View.INVISIBLE);
        final RelativeLayout progressBarLayout = findViewById(R.id.progressBarLayout);
        progressBarLayout.setVisibility(View.VISIBLE);
        final ProgressBar progressBar = findViewById(R.id.progressBar4);
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }


        temp = new ArrayList<>();
        remainingTime = 15;

        Intent mIntent = getIntent();
        question = mIntent.getStringExtra("questions");
        index = mIntent.getIntExtra("mIndex", 0);
        categoryTag = mIntent.getStringExtra("category");
        buttonPointString = mIntent.getStringExtra("buttonPoint");
        totalPoints = mIntent.getIntExtra("currentScore", 0);
        earnedPoints = Integer.parseInt(buttonPointString);
        colorList = mIntent.getStringArrayListExtra("colorList");
        clickList = mIntent.getStringArrayListExtra("clickList");
        questionNumber = mIntent.getIntExtra("questionNumber", 0);
        totalTime = mIntent.getIntExtra("totalTime",0);


        TextView text = findViewById(R.id.question);
        text.setText(question);

        scoreBoard = findViewById(R.id.score);
        scoreBoard.setText("" + totalPoints);

        answer1Button = findViewById(R.id.answer1);
        answer2Button = findViewById(R.id.answer2);
        answer3Button = findViewById(R.id.answer3);
        answer4Button = findViewById(R.id.answer4);
        ButtonList = new Button[]{
                answer1Button, answer2Button, answer3Button, answer4Button
        };

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(answer1Button);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(answer2Button);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(answer3Button);
            }
        });
        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(answer4Button);
            }
        });


        if (categoryTag.equals("Multiplication"))
            AnswerDatabase = FirebaseDatabase.getInstance().getReference().child("MultCorrect");
        else AnswerDatabase = FirebaseDatabase.getInstance().getReference().child("SumCorrect");

        try {

            AnswerDatabase.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        MultCorrect.add(ds.getValue().toString());

                    }
                    wrongAnsDatabase = FirebaseDatabase.getInstance().getReference().child("WrongAnswers");
                    try {
                        wrongAnsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Wronganswers.add(ds.getValue().toString());
                                }
                                updateQuestion();
                                progressBarLayout.setVisibility(View.INVISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                                questionLayout.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        } catch (Exception e) {

        }


        runTimer();
        runTotalTimer();
    }


    private void updateQuestion() {

        for (int i = 0; i < 4; i++) {
            ButtonList[i].setClickable(true);
        }
        temp.clear();

        Random rand = new Random();

        buttonValue = rand.nextInt(4);

        int choiceValue = rand.nextInt(Wronganswers.size());
        ButtonList[buttonValue].setText(MultCorrect.get(index));

        mFalseAnswer.add(MultCorrect.get(index));
        temp.add(MultCorrect.get(index));

        for (int i = 0; i < 3; i++) {

            buttonValue = (buttonValue + 1) % 4;
            while (mFalseAnswer.contains(Wronganswers.get(choiceValue))) {
                choiceValue = rand.nextInt(Wronganswers.size());
            }

            ButtonList[buttonValue].setText(Wronganswers.get(choiceValue));
            mFalseAnswer.add(Wronganswers.get(choiceValue));
            temp.add(Wronganswers.get(choiceValue));
            choiceValue = rand.nextInt(Wronganswers.size());

        }
        mFalseAnswer.clear();
    }

    private void checkAnswer(Button choiceButton) {

        toastText = 0;

        if (choiceButton.getText().equals(MultCorrect.get(index))) {
            totalPoints += earnedPoints;
            for (int i = 0; i < 3; i++) {
                ButtonList[i].setClickable(false);
            }
            isTrue = true;
            toastText = R.string.correct_toast;

        } else {
            totalPoints -= lostPoints;
            for (int i = 0; i < 3; i++) {
                ButtonList[i].setClickable(false);
            }
            isFalse = true;
            toastText = R.string.incorrect_toast;

        }

        final Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
        toast.show();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
                updateScore();
                useIntent();
            }
        }, 1000);

    }


    private void runTimer() {

        final TextView timeView = findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String time = String.format("%d", remainingTime);
                timeView.setText(time);
                if (running) {
                    if (remainingTime != 0)
                        remainingTime--;

                    else {
                        totalPoints -= lostPoints;
                        isOutOfTime = true;
                        updateScore();
                        useIntent();
                    }
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runTotalTimer() {


        runnable = new Runnable() {
            @Override
            public void run() {
               totalTime += 1 ;
                System.out.println("++++++++++"+totalTime);
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);

    }

    private void updateScore() {
        String mScore = String.format("%d", totalPoints);
        scoreBoard.setText(mScore);
    }


    private void useIntent() {

        if (questionNumber == 12) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("finalTime", totalTime);
            intent.putExtra("finalPoints", totalPoints);
            handler.removeCallbacks(runnable);
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, PointActivity.class);
            intent.putExtra("isTrue", isTrue);
            intent.putExtra("isFalse", isFalse);
            intent.putExtra("isOutOfTime", isOutOfTime);
            intent.putExtra("categoryTag", categoryTag);
            intent.putExtra("prevTotalPoints", totalPoints);
            intent.putExtra("btnIndex", index);
            intent.putStringArrayListExtra("colorList", colorList);
            intent.putStringArrayListExtra("clickList", clickList);
            intent.putExtra("questionNumber", questionNumber);
            intent.putExtra("finalTime", totalTime);
            handler.removeCallbacks(runnable);
            startActivity(intent);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        running = false;

    }

    @Override
    protected void onStart() {
        super.onStart();
        running = true;
    }

}



