package com.example.muratturan.quizappex;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PointActivity extends AppCompatActivity {

    private ArrayList<String> questionArrayList = new ArrayList<>();
    private int[] btnList = {
            R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5
            , R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10,
            R.id.button11, R.id.button12
    };
    private ArrayList<String> buttonColorList = new ArrayList<String>();
    private ArrayList<String> buttonClickableList = new ArrayList<>();

    private int index;
    private String buttonPoint;
    private String categoryTag;
    private int totPoints;
    private boolean isTrue;
    private boolean isFalse;
    private boolean isOutOfTime;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button[] ButtonList;
    private int questionNumber = 0;
    private int totalTime ;
    Handler handler = new Handler();
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        Intent mIntent = getIntent();
        questionArrayList = mIntent.getStringArrayListExtra("MultArrayList");
        categoryTag = mIntent.getStringExtra("category");
        totPoints = mIntent.getIntExtra("prevTotalPoints", 0);
        index = mIntent.getIntExtra("btnIndex", 0);
        isTrue = mIntent.getBooleanExtra("isTrue", false);
        isFalse = mIntent.getBooleanExtra("isFalse", false);
        isOutOfTime = mIntent.getBooleanExtra("isOutOfTime", false);
        buttonColorList = mIntent.getStringArrayListExtra("colorList");
        buttonClickableList = mIntent.getStringArrayListExtra("clickList");
        questionNumber = mIntent.getIntExtra("questionNumber", 0);
        totalTime = mIntent.getIntExtra("finalTime",0);

        runTotalTimer();



        if (buttonColorList == null) {
            buttonColorList = new ArrayList<>();
            for (int i = 0; i < btnList.length; i++) {
                buttonColorList.add(i, "#e4eaf0");
            }
        }
        if (buttonClickableList == null) {
            buttonClickableList = new ArrayList<>();
            for (int i = 0; i < btnList.length; i++) {
                buttonClickableList.add(i, "true");

            }
        }
        if (questionArrayList == null) {
            categoryTag = mIntent.getStringExtra("categoryTag");
            questionArrayList = CategoryActivity.createArrayList(categoryTag);
        }

        TextView totScore = (TextView) findViewById(R.id.totalScore);
        totScore.setText("" + totPoints);


        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                buttonPoint = button1.getText().toString();
                questionNumber += 1;
                useIntent();

            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                buttonPoint = button2.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                buttonPoint = button3.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 3;
                buttonPoint = button4.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 4;
                buttonPoint = button5.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 5;
                buttonPoint = button6.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 6;
                buttonPoint = button7.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 7;
                buttonPoint = button8.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 8;
                buttonPoint = button9.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 9;
                buttonPoint = button10.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 10;
                buttonPoint = button11.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });
        button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 11;
                buttonPoint = button12.getText().toString();
                questionNumber += 1;
                useIntent();
            }
        });

        ButtonList = new Button[]{
                button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12
        };

        for (int i = 0; i < ButtonList.length; i++) {
            ButtonList[i].setBackgroundColor(Color.parseColor(buttonColorList.get(i)));
        }
        for (int i = 0; i < ButtonList.length; i++) {
            ButtonList[i].setClickable(Boolean.parseBoolean(buttonClickableList.get(i)));
        }


        if (isTrue) {

            ButtonList[index].setBackgroundColor(Color.parseColor("#25FF00"));
            ButtonList[index].setClickable(false);
            buttonColorList.set(index, "#25FF00");
            buttonClickableList.set(index, "false");


        } else if (isFalse) {

            ButtonList[index].setBackgroundColor(Color.parseColor("#FF0000"));
            ButtonList[index].setClickable(false);
            buttonColorList.set(index, "#FF0000");
            buttonClickableList.set(index, "false");


        } else if (isOutOfTime) {

            ButtonList[index].setBackgroundColor(Color.parseColor("#003BFF"));
            ButtonList[index].setClickable(false);
            buttonColorList.set(index, "#003BFF");
            buttonClickableList.set(index, "false");

        }

    }

    private void useIntent() {


        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("questions", questionArrayList.get(index));
        intent.putExtra("mIndex", index);
        intent.putExtra("buttonPoint", buttonPoint);
        intent.putExtra("category", categoryTag);
        intent.putExtra("currentScore", totPoints);
        intent.putStringArrayListExtra("colorList", buttonColorList);
        intent.putStringArrayListExtra("clickList", buttonClickableList);
        intent.putExtra("finalPoints", totPoints);
        intent.putExtra("questionNumber", questionNumber);
        intent.putExtra("totalTime",totalTime);
        handler.removeCallbacks(runnable);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

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


}
