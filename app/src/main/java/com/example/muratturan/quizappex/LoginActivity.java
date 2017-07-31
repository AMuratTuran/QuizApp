package com.example.muratturan.quizappex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    private Button submitButton;

    String usernameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageView = (EditText) findViewById(R.id.username);
                usernameText = messageView.getText().toString();
                useIntent();

            }
        });
    }


    private void useIntent() {
        Intent intent = new Intent(this, StartActivity.class);
        intent.putExtra("userNameData", usernameText);
        startActivity(intent);
    }
}
