package com.example.projectandroid.HardGame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.Databases.SQLiteDBHandler;
import com.squareup.picasso.Picasso;

public class Game_hard_result_frag extends Fragment {
    // TODO: Rename and change types of parameters
    private SQLiteDBHandler db;

    private Button btnItem1;
    private Button btnItem2;
    private Button btnItem3;
    private Button btnItem4;
    private TextView nbrItem1;
    private TextView nbrItem2;
    private TextView nbrItem3;
    private TextView nbrItem4;
    private TextView scoring;
    private ImageView resultImg;
    private Button nextButton;
    private double numberItem1;
    private double numberItem2;
    private double numberItem3;
    private double numberItem4;
    private returnScore sendScore;

    public Game_hard_result_frag() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game_hard_result_frag, container, false);

        db = new SQLiteDBHandler(getActivity());

        //Get ID of components
        btnItem1 = view.findViewById(R.id.item1Hard);
        btnItem2 = view.findViewById(R.id.item2Hard);
        btnItem3 = view.findViewById(R.id.item3Hard);
        btnItem4 = view.findViewById(R.id.item4Hard);

        nbrItem1 = view.findViewById(R.id.resultTxt1Hard);
        nbrItem2 = view.findViewById(R.id.resultTxt2Hard);
        nbrItem3 = view.findViewById(R.id.resultTxt3Hard);
        nbrItem4 = view.findViewById(R.id.resultTxt4Hard);
        scoring = view.findViewById(R.id.score);
        resultImg = view.findViewById(R.id.imgResultHard);
        nextButton = view.findViewById(R.id.btnNext);

        //Get all info from game easy
        final Bundle bundle = getArguments();
        String button1 = bundle.getString("Item1");
        btnItem1.setText(button1);
        String button2 = bundle.getString("Item2");
        btnItem2.setText(button2);
        String button3 = bundle.getString("Item3");
        btnItem3.setText(button3);
        String button4 = bundle.getString("Item4");
        btnItem4.setText(button4);
        String textbtn1 = bundle.getString("Textbtn1");
        nbrItem1.setText(textbtn1);
        String textbtn2 = bundle.getString("Textbtn2");
        nbrItem2.setText(textbtn2);
        String textbtn3 = bundle.getString("Textbtn3");
        nbrItem3.setText(textbtn3);
        String textbtn4 = bundle.getString("Textbtn4");
        nbrItem4.setText(textbtn4);
        String finalScore = bundle.getString("FinalScore");
        scoring.setText(finalScore);

        //String to Double
        numberItem1 = Double.valueOf(nbrItem1.getText().toString());
        numberItem2 = Double.valueOf(nbrItem2.getText().toString());
        numberItem3 = Double.valueOf(nbrItem3.getText().toString());
        numberItem4 = Double.valueOf(nbrItem4.getText().toString());

        //Compare
        Game(numberItem1, numberItem2, numberItem3, numberItem4);

        //Event onClick
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendScore.ResultScore(scoring.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try{
            sendScore = (returnScore) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement onScoreSend...");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        sendScore = null;
    }

    public interface returnScore{

        void ResultScore(String score);
    }

    public void Game(double a, double b, double c, double d) {

        nbrItem1.setVisibility(View.VISIBLE);
        nbrItem2.setVisibility(View.VISIBLE);
        nbrItem3.setVisibility(View.VISIBLE);
        nbrItem4.setVisibility(View.VISIBLE);

        if((a > b) && (a > c) && (a > d)){
            btnItem1.setBackgroundColor(Color.GREEN);
            btnItem2.setBackgroundColor(Color.RED);
            btnItem3.setBackgroundColor(Color.RED);
            Log.i("String3",db.getItem(btnItem3.getText().toString()).getUrlIMG());
            Picasso.with(getActivity().getApplicationContext()).load(db.getItem(btnItem1.getText().toString()).getUrlIMG()).into(resultImg);
        }
        if((b > a) && (b > c) && (b > d)) {
            btnItem1.setBackgroundColor(Color.RED);
            btnItem2.setBackgroundColor(Color.GREEN);
            btnItem3.setBackgroundColor(Color.RED);
            Log.i("String2",db.getItem(btnItem3.getText().toString()).getUrlIMG());
            Picasso.with(getActivity().getApplicationContext()).load(db.getItem(btnItem2.getText().toString()).getUrlIMG()).into(resultImg);

        }
        if((c > a) && (c > b) && (c > d)){
            btnItem1.setBackgroundColor(Color.RED);
            btnItem2.setBackgroundColor(Color.RED);
            btnItem3.setBackgroundColor(Color.GREEN);
            Log.i("String",db.getItem(btnItem3.getText().toString()).getUrlIMG());
            Picasso.with(getActivity().getApplicationContext()).load(db.getItem(btnItem3.getText().toString()).getUrlIMG()).into(resultImg);
        }
        if((d > a) && (d > b) && (d > c)){
            btnItem1.setBackgroundColor(Color.RED);
            btnItem2.setBackgroundColor(Color.RED);
            btnItem3.setBackgroundColor(Color.RED);
            btnItem4.setBackgroundColor(Color.GREEN);
            Log.i("String4",db.getItem(btnItem4.getText().toString()).getUrlIMG());
            Picasso.with(getActivity().getApplicationContext()).load(db.getItem(btnItem4.getText().toString()).getUrlIMG()).into(resultImg);
        }
    }
}
