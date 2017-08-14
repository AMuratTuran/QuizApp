package com.example.muratturan.quizappex;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends Activity {


    private Button loginButton;
    private Button createUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText userNameEdit;
    private ProgressDialog progressDialog;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<String> usernames;
    private ArrayList<String> highscores = new ArrayList<>();
    private ArrayList<String> names ;
    private ArrayList<String> lastnames ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        createUser = findViewById(R.id.createAccountButton);
        userNameEdit = findViewById(R.id.usernameEditText);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usernames = collectTitles((Map<String, Object>) dataSnapshot.getValue(),"username");
                names = collectTitles((Map<String, Object>) dataSnapshot.getValue(),"name");
                lastnames = collectTitles((Map<String, Object>) dataSnapshot.getValue(),"lastname");

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    System.out.println(ds.child("highScore"));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("-------", "onAuthStateChanged:signed_out");
            }
        };


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

            if (usernames.contains(username)) {
                progressDialog.hide();
                Intent mIntent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(mIntent);
            } else
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<String> collectTitles(Map<String, Object> posts,String tag) {

        ArrayList<String> info = new ArrayList<>();

        for (Map.Entry<String, Object> entry : posts.entrySet()) {

            Map singlePost = (Map) entry.getValue();
            info.add((String) singlePost.get(tag));
        }

        return info;
    }
}
