package com.example.muratturan.quizappex;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CategoryActivity extends Activity implements OptionListFragment.OptionListListener {


    private ArrayList<String> questions = new ArrayList<>();
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_list);

    }

    @Override
    public void itemClicked(int position) {

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            PointFragment points = new PointFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, points);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        } else {

            if (position == 0) {

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Multiplication");
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        questions.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            questions.add(ds.getValue().toString());
                        }

                        useIntent("Multiplication");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            } else if (position == 1) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Sum");
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        questions.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            questions.add(ds.getValue().toString());
                        }
                        useIntent("Sum");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }


    private void useIntent(String tag) {

        Intent intent = new Intent(this, PointActivity.class);
        intent.putStringArrayListExtra("MultArrayList", questions);
        intent.putExtra("category",tag);
        startActivity(intent);


    }

    public static ArrayList<String> createArrayList(String tag) {

        final ArrayList<String> questionList = new ArrayList<>();

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(tag);
        try{

            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    questionList.add(ds.getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        }catch (Exception e){

        }

        return questionList;
    }


}
