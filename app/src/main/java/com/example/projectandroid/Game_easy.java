package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;


public class Game_easy extends AppCompatActivity {

    private static final String TAG = Game_easy.class.getName();
    private SQLiteDBHandler db;

    int score = 0;
    int numberItem1;
    int numberItem2;

    Button item1;
    Button item2;

    TextView resultItem1;
    TextView resultItem2;
    TextView scoring;
    ImageView resultImg;

    boolean play = true;

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

        do {
            item1 = findViewById(R.id.item1);
            item1.setText(getRandomString());
            Log.i("ITEM1", item1.getText().toString());

            item2 = findViewById(R.id.item2);
            item2.setText(getRandomString());
            Log.i("ITEM2",item2.getText().toString());
            while (item2.getText() == item1.getText()) {
                Log.i("ITEM22",item2.getText().toString());
                item2.setText(getRandomString());
            }

            resultItem1 = findViewById(R.id.resultTxt1);
            resultItem1.setText(db.getItem(item1.getText().toString()).getNumberTV());
            numberItem1 = Integer.parseInt(resultItem1.getText().toString());
            resultItem1.setVisibility(View.INVISIBLE);
            Log.i("IMGRES1",resultItem1.getText().toString());

            resultItem2 = findViewById(R.id.resultTxt2);
            resultItem2.setText(db.getItem(item2.getText().toString()).getNumberTV());
            numberItem2 = Integer.parseInt(resultItem2.getText().toString());
            resultItem2.setVisibility(View.INVISIBLE);
            Log.i("IMGRES2",resultItem2.getText().toString());

            scoring = findViewById(R.id.textView8);


            item1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game(numberItem1, numberItem2);
                    if (numberItem1 > numberItem2) {
                        score += 1;
                    } else {
                        play = false;
                    }
                }
            });

            item2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game(numberItem1, numberItem2);
                    if (numberItem1 < numberItem2) {
                        score += 1;
                    } else {
                        play = false;
                    }
                }
            });
        }while (play);
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
}