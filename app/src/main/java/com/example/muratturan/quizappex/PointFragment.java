package com.example.muratturan.quizappex;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PointFragment extends Fragment {

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
    private boolean isLargeScreen = false ;
    private int questionNumber = 0;
    private int totalTime;
    Handler handler = new Handler();
    Runnable runnable;
    private Bundle extras = new Bundle();
    private View rootView;
    private Bundle incomingBundle = new Bundle();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_point, container, false);
        incomingBundle = getArguments();

        final Intent mIntent = getActivity().getIntent();


        if (savedInstanceState != null) {
            totalTime = savedInstanceState.getInt("totalTime");
        } else{
            if(incomingBundle != null)
                totalTime = incomingBundle.getInt("finalTime", 0);
            else totalTime = mIntent.getIntExtra("totalTime",0) ;

        }

        if(incomingBundle != null){

            isLargeScreen = incomingBundle.getBoolean("isLargeScreen");
            System.out.println(isLargeScreen);
            questionArrayList = incomingBundle.getStringArrayList("MultArrayList");
            categoryTag = incomingBundle.getString("category");
            totPoints = incomingBundle.getInt("prevTotalPoints", 0);
            index = incomingBundle.getInt("btnIndex", 0);
            isTrue = incomingBundle.getBoolean("isTrue", false);
            isFalse = incomingBundle.getBoolean("isFalse", false);
            isOutOfTime = incomingBundle.getBoolean("isOutOfTime", false);
            buttonColorList = incomingBundle.getStringArrayList("colorList");
            buttonClickableList = incomingBundle.getStringArrayList("clickList");
            questionNumber = incomingBundle.getInt("questionNumber", 0);

        }else {

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
        }





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
            if(incomingBundle != null)
            categoryTag = incomingBundle.getString("categoryTag");
            else categoryTag = mIntent.getStringExtra("categoryTag");
            questionArrayList = CategoryActivity.createArrayList(categoryTag);
        }

        TextView totScore = rootView.findViewById(R.id.totalScore);
        totScore.setText("" + totPoints);


        button1 = rootView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                buttonPoint = button1.getText().toString();
                questionNumber += 1;
                System.out.println(rootView.findViewById(R.id.fragment_container) );
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();

            }
        });
        button2 = rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                buttonPoint = button2.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();

            }
        });
        button3 = rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                buttonPoint = button3.getText().toString();
                questionNumber += 1;

                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button4 = rootView.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 3;
                buttonPoint = button4.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button5 = rootView.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 4;
                buttonPoint = button5.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button6 = rootView.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 5;
                buttonPoint = button6.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button7 = rootView.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 6;
                buttonPoint = button7.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button8 = rootView.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 7;
                buttonPoint = button8.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button9 = rootView.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 8;
                buttonPoint = button9.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button10 = rootView.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 9;
                buttonPoint = button10.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button11 = rootView.findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 10;
                buttonPoint = button11.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
            }
        });
        button12 = rootView.findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 11;
                buttonPoint = button12.getText().toString();
                questionNumber += 1;
                if (!isLargeScreen)
                    useIntent();
                else useFragmentTransaction();
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

        return rootView;
    }


    private void useFragmentTransaction() {
        System.out.println("********************");
        extras.putString("questions", questionArrayList.get(index));
        extras.putInt("mIndex", index);
        extras.putString("buttonPoint", buttonPoint);
        extras.putString("category", categoryTag);
        extras.putInt("currentScore", totPoints);
        extras.putStringArrayList("colorList", buttonColorList);
        extras.putStringArrayList("clickList", buttonClickableList);
        extras.putInt("finalPoints", totPoints);
        extras.putInt("questionNumber", questionNumber);
        extras.putInt("totalTime", totalTime);
        extras.putBoolean("isLargeScreen",true);
        handler.removeCallbacks(runnable);
        QuestionFragment frag = new QuestionFragment();
        frag.setArguments(extras);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, frag);
        ft.commit();
    }

    private void useIntent(){

        Intent intent = new Intent(getActivity(),QuestionActivity.class);
        intent.putExtra("questions", questionArrayList.get(index));
        intent.putExtra("mIndex", index);
        intent.putExtra("buttonPoint", buttonPoint);
        intent.putExtra("category", categoryTag);
        intent.putExtra("currentScore", totPoints);
        intent.putStringArrayListExtra("colorList", buttonColorList);
        intent.putStringArrayListExtra("clickList", buttonClickableList);
        intent.putExtra("finalPoints", totPoints);
        intent.putExtra("questionNumber", questionNumber);
        intent.putExtra("totalTime", totalTime);
        handler.removeCallbacks(runnable);
        startActivity(intent);
    }


    private void runTotalTimer() {

        runnable = new Runnable() {
            @Override
            public void run() {
                totalTime += 1;
                System.out.println("++++++++++" + totalTime);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("totalTime", totalTime);
        handler.removeCallbacks(runnable);

    }


}
