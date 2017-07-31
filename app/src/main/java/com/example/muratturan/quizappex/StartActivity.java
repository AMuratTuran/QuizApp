package com.example.muratturan.quizappex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button startButton;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Intent mIntent = getIntent();
        userName = mIntent.getStringExtra("userNameData");

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useIntent();
            }
        });
    }



    private void useIntent() {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("userNameData", userName);
        startActivity(intent);
    }
}
