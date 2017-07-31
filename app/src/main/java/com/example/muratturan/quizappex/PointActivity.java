package com.example.muratturan.quizappex;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PointActivity extends AppCompatActivity {

    private ArrayList<String> questionArrayList = new ArrayList<>();
    private int[] btnList = {
            R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5
            , R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10,
            R.id.button11, R.id.button12
    };

    private int index;
    private String buttonPoint;
    private String categoryTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        Intent mIntent = getIntent();
        questionArrayList = mIntent.getStringArrayListExtra("MultArrayList");
        categoryTag = mIntent.getStringExtra("category");

        if (questionArrayList == null) {
            System.out.println("girdi");
            categoryTag = mIntent.getStringExtra("categoryTag");
            System.out.println(categoryTag);
            questionArrayList= CategoryActivity.createArrayList("Multiplication");

        }


        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                buttonPoint = button1.getText().toString();
                useIntent();

            }
        });
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                buttonPoint = button2.getText().toString();
                useIntent();
            }
        });
        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                buttonPoint = button3.getText().toString();
                useIntent();
            }
        });
        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 3;
                buttonPoint = button4.getText().toString();
                useIntent();
            }
        });
        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 4;
                buttonPoint = button5.getText().toString();
                useIntent();
            }
        });
        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 5;
                buttonPoint = button6.getText().toString();
                useIntent();
            }
        });
        final Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 6;
                buttonPoint = button7.getText().toString();
                useIntent();
            }
        });
        final Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 7;
                buttonPoint = button8.getText().toString();
                useIntent();
            }
        });
        final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 8;
                buttonPoint = button9.getText().toString();
                useIntent();
            }
        });
        final Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 9;
                buttonPoint = button10.getText().toString();
                useIntent();
            }
        });
        final Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 10;
                buttonPoint = button11.getText().toString();
                useIntent();
            }
        });
        final Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 11;
                buttonPoint = button12.getText().toString();
                useIntent();
            }
        });

    }

    private void useIntent() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("questions", questionArrayList.get(index));
        intent.putExtra("mIndex", index);
        intent.putExtra("buttonPoint", buttonPoint);
        intent.putExtra("category", categoryTag);
        startActivity(intent);
    }
}
