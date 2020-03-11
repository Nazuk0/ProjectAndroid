package com.example.projectandroid.HardGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.projectandroid.R;

import java.util.Random;

public class Game_hard extends AppCompatActivity {

    int score=0;
    Button item1;
    Button item2;
    Button item3;
    Button item4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hard);

        setTextBtn();

    }

    public String getRandomString(){
        String[] array = getResources().getStringArray(R.array.words);
        String randomStr = array[new Random().nextInt(array.length)];

        return randomStr;
    }

    public void setTextBtn(){

        item1 = findViewById(R.id.item1);
        item1.setText(getRandomString());

        item2 = findViewById(R.id.item2);
        item2.setText(getRandomString());
        while(item2.getText() == item1.getText()){
            item2.setText(getRandomString());
        }

        item3 = findViewById(R.id.item3);
        item3.setText(getRandomString());
        while(item3.getText() == item1.getText() && item3.getText() == item2.getText()){
            item2.setText(getRandomString());
        }

        item4 = findViewById(R.id.item4);
        item4.setText(getRandomString());
        while(item4.getText() == item1.getText() && item4.getText() == item2.getText() && item4.getText() == item3.getText()){
            item4.setText(getRandomString());
        }
    }
}
