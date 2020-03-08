package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button easy;
    Button medium;
    Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easy = findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityEasy();
            }
        });

        medium = findViewById(R.id.medium);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMedium();
            }
        });

        hard = findViewById(R.id.hard);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityHard();
            }
        });
    }

    public void openActivityEasy(){
        Intent easyPage = new Intent(getApplicationContext(), Game_easy.class);
        startActivity(easyPage);
    }

    public void openActivityMedium(){
        Intent mediumPage = new Intent(getApplicationContext(), Game_medium.class);
        startActivity(mediumPage);
    }

    public void openActivityHard(){
        Intent hardPage = new Intent(getApplicationContext(), Game_hard.class);
        startActivity(hardPage);
    }
}
