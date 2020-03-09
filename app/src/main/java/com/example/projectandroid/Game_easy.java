package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;


public class Game_easy extends AppCompatActivity {

    private static final String TAG = Game_easy.class.getName();
    private SQLiteDBHandler db;

    int score = 0;
    int numberItem1, numberItem2;

    Button item1, item2;

    TextView resultItem1, resultItem2, scoring;
    ImageView resultImg;

    List<Items> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy);

        db = new SQLiteDBHandler(this);
        items = db.allItems();

        for (Items i: items) {
            Log.i("Name", i.getNameItem());
            Log.i("NBR", i.getNumberTV());
            Log.i("IMG", i.getUrlIMG());
        }

        //All FindViewByID
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        resultItem1 = findViewById(R.id.resultTxt1);
        resultItem2 = findViewById(R.id.resultTxt2);
        scoring = findViewById(R.id.textView8);

        //ALL Setting
        item1.setText(getRandomString());
        Log.i("ITEM1", item1.getText().toString());
        item2.setText(getRandomString());
        Log.i("ITEM2", item2.getText().toString());
        while (item2.getText() == item1.getText()) {
             Log.i("ITEM22", item2.getText().toString());
             item2.setText(getRandomString());
        }
        resultItem1.setText(db.getItem(item1.getText().toString()).getNumberTV());
        resultItem2.setText(db.getItem(item2.getText().toString()).getNumberTV());

        //View result
        resultItem1.setVisibility(View.INVISIBLE);
        resultItem2.setVisibility(View.INVISIBLE);

        numberItem1 = Integer.parseInt(resultItem1.getText().toString());
        Log.i("IMGRES1", resultItem1.getText().toString());
        numberItem2 = Integer.parseInt(resultItem2.getText().toString());
        Log.i("IMGRES2", resultItem2.getText().toString());

        //Events
        item1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String finalScore;
                 //ResultEasy();
                 Game(numberItem1, numberItem2);
                 if (numberItem1 > numberItem2) {
                     score += 1;
                     finalScore = "Score : " + score;
                     scoring.setText(finalScore);
                     Log.i("SCOREING", scoring.getText().toString());
                 } else {
                     Intent over = new Intent(getApplicationContext(), GameOver.class);
                     over.putExtra("score", score);
                     startActivity(over);
                 }
             }
        });

        item2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String finalScore;
                 //ResultEasy();
                 Game(numberItem1, numberItem2);
                 if (numberItem1 < numberItem2) {
                     score += 1;
                     finalScore = "Score : " + score;
                     scoring.setText(finalScore);
                     Log.i("SCOREING2", scoring.getText().toString());
                 } else {
                     Intent over = new Intent(getApplicationContext(), GameOver.class);
                     over.putExtra("score", score);
                     startActivity(over);
                 }
             }
        });
    }

    public String getRandomString(){
        String[] array = getResources().getStringArray(R.array.words);
        String randomStr = array[new Random().nextInt(array.length)];

        return randomStr;
    }



    public void Game(int a, int b) {

        resultItem1.setVisibility(View.VISIBLE);
        resultItem2.setVisibility(View.VISIBLE);
        resultImg = findViewById(R.id.img);

        if(a < b) {
            item1.setBackgroundColor(Color.RED);
            item2.setBackgroundColor(Color.GREEN);
            Picasso.with(getApplicationContext()).load(db.getItem(item2.getText().toString()).getUrlIMG()).into(resultImg);

        }else{
            item2.setBackgroundColor(Color.RED);
            item1.setBackgroundColor(Color.GREEN);
            Picasso.with(getApplicationContext()).load(db.getItem(item1.getText().toString()).getUrlIMG()).into(resultImg);
        }
    }

    /*public void ResultEasy(){
        Intent ResultPage = new Intent(getApplicationContext(), Game_easy_result.class);
        ResultPage.putExtra("numberItem1", numberItem1);
        ResultPage.putExtra("numberItem2", numberItem2);
        ResultPage.putExtra("item1", item1.getText().toString());
        ResultPage.putExtra("item2", item2.getText().toString());
        startActivity(ResultPage);
    }*/
}