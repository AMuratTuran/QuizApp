package com.example.muratturan.quizappex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private Button loginButton;
    private Button createUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText userNameEdit;
    private ProgressDialog progressDialog;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<String> usernames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        createUser = (Button) findViewById(R.id.createAccountButton);
        userNameEdit = (EditText) findViewById(R.id.usernameEditText);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null && user.isEmailVerified()) {
                    startActivity(new Intent(LoginActivity.this, StartActivity.class));
                    Log.d("******", "onAuthStateChanged:signed_in:" + user.getUid());

                } else {

                    Log.d("-------", "onAuthStateChanged:signed_out");
                }
            }
        };

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usernames = collectTitles((Map<String,Object>) dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSignUpPage();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void loadSignUpPage() {

        Intent signUpIntent = new Intent(this, CreateAccountActivity.class);
        startActivity(signUpIntent);
    }

    private void login() {
        String username = userNameEdit.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "No username given", Toast.LENGTH_SHORT).show();
            userNameEdit.setError("Email cannot be empty");
        } else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging in...");
            progressDialog.show();


            if(usernames.contains(username)){
                progressDialog.hide();
                startActivity(new Intent(LoginActivity.this,StartActivity.class));
            }
            else
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();




        }
    }

    private ArrayList<String> collectTitles(Map<String, Object> posts) {

        ArrayList<String> titles = new ArrayList<>();
        //iterate through each user, ignoring their UID

        for (Map.Entry<String, Object> entry : posts.entrySet()){


            Map singlePost = (Map) entry.getValue();

            titles.add((String) singlePost.get("username"));
        }


        return titles;

    }


}
