package com.example.muratturan.quizappex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    private int finalPoints;
    private int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();


        totalTime = intent.getIntExtra("finalTime", 0);
        finalPoints = intent.getIntExtra("finalPoints", 0);


        TextView scoreBoard = (TextView) findViewById(R.id.scorePoints);
        String editedPoints = String.format("%d", finalPoints);
        scoreBoard.setText(editedPoints);


        TextView timeBoard = (TextView) findViewById(R.id.totalTime);
        int minutes = (totalTime % 3600) / 60;
        int secs = totalTime % 60;
        String editedTime = String.format("%02d:%02d", minutes, secs);
        timeBoard.setText(editedTime);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,CategoryActivity.class);
        startActivity(intent);
    }
}
