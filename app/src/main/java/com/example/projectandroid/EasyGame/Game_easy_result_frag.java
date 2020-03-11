package com.example.projectandroid.EasyGame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.Databases.SQLiteDBHandler;
import com.squareup.picasso.Picasso;

public class Game_easy_result_frag extends Fragment {
    // TODO: Rename and change types of parameters
    private SQLiteDBHandler db;

    private Button btnItem1;
    private Button btnItem2;
    private TextView nbrItem1;
    private TextView nbrItem2;
    private TextView scoreString;
    private TextView scoring;
    private ImageView resultImg;
    private Button nextButton;
    private double numberItem1;
    private double numberItem2;
    private returnScore sendScore;

    public interface returnScore{

        void ResultScore(String score);
    }

    public Game_easy_result_frag() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game_easy_result_frag, container, false);

        db = new SQLiteDBHandler(getActivity());

        //Get ID of components
        btnItem1 = view.findViewById(R.id.item1);
        btnItem2 = view.findViewById(R.id.item2);
        nbrItem1 = view.findViewById(R.id.resultTxt1);
        nbrItem2 = view.findViewById(R.id.resultTxt2);
        scoring = view.findViewById(R.id.score);
        resultImg = view.findViewById(R.id.img);
        nextButton = view.findViewById(R.id.btnNext);

        //Get all info from game easy
        final Bundle bundle = getArguments();
        String button1 = bundle.getString("Item1");
        btnItem1.setText(button1);
        String button2 = bundle.getString("Item2");
        btnItem2.setText(button2);
        String textbtn1 = bundle.getString("Textbtn1");
        nbrItem1.setText(textbtn1);
        String textbtn2 = bundle.getString("Textbtn2");
        nbrItem2.setText(textbtn2);
        String finalScore = bundle.getString("FinalScore");
        scoring.setText(finalScore);

        //String to Double
        numberItem1 = Double.valueOf(nbrItem1.getText().toString());
        numberItem2 = Double.valueOf(nbrItem2.getText().toString());

        //Comparee
        Game(numberItem1, numberItem2);

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

    public void Game(double a, double b) {

        nbrItem1.setVisibility(View.VISIBLE);
        nbrItem2.setVisibility(View.VISIBLE);


        if(a < b) {
            btnItem1.setBackgroundColor(Color.RED);
            btnItem2.setBackgroundColor(Color.GREEN);
            Picasso.with(getActivity().getApplicationContext()).load(db.getItem(btnItem2.getText().toString()).getUrlIMG()).into(resultImg);

        }else{
            btnItem2.setBackgroundColor(Color.RED);
            btnItem1.setBackgroundColor(Color.GREEN);
            Picasso.with(getActivity().getApplicationContext()).load(db.getItem(btnItem1.getText().toString()).getUrlIMG()).into(resultImg);
        }
    }
}
