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

public class Game_easy_result extends AppCompatActivity {

    private SQLiteDBHandler db;
    List<Items> items;

    TextView item1TV;
    TextView item2TV;

    Button item1;
    Button item2;

    int resultItem1;
    int resultItem2;

    String nameItem1;
    String nameItem2;

    ImageView resultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy_result);

        db = new SQLiteDBHandler(this);
        items = db.allItems();

        item1TV = findViewById(R.id.textItem1);
        item2TV = findViewById(R.id.textItem2);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        resultImage = findViewById(R.id.imgResult);

        Bundle extras = getIntent().getExtras();
        resultItem1 = extras.getInt("numberItem1");
        resultItem2 = extras.getInt("numberItem2");
        nameItem1 = extras.getString("item1");
        nameItem2 = extras.getString("item2");

        //ALL Setting
        item1.setText(nameItem1);
        //Log.i("ITEM1", item1.getText().toString());
        item2.setText(nameItem2);
        //Log.i("ITEM2", item2.getText().toString());
        item1TV.setText(String.valueOf(resultItem1));
        item2TV.setText(String.valueOf(resultItem2));

        //View result
        item1TV.setVisibility(View.INVISIBLE);
        item2TV.setVisibility(View.INVISIBLE);

        Game(resultItem1,resultItem2);

    }

    public void Game(int a, int b) {

        item1TV.setVisibility(View.VISIBLE);
        item2TV.setVisibility(View.VISIBLE);

        if(a < b) {
            item1.setBackgroundColor(Color.RED);
            item2.setBackgroundColor(Color.GREEN);
            Picasso.with(getApplicationContext()).load(db.getItem(item2.getText().toString()).getUrlIMG()).into(resultImage);

        }else{
            item2.setBackgroundColor(Color.RED);
            item1.setBackgroundColor(Color.GREEN);
            Picasso.with(getApplicationContext()).load(db.getItem(item1.getText().toString()).getUrlIMG()).into(resultImage);
        }
    }
}
