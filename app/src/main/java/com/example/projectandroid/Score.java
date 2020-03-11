package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Score extends AppCompatActivity {

    private RecyclerView recycleView;
    private String nicknames[], scores[];
    private SQLitePlayer dbPlayer;
    private ArrayList<Players> players;
    private int[] images = {R.drawable.top1, R.drawable.top2, R.drawable.top3, R.drawable.top4, R.drawable.top5,
                            R.drawable.top6, R.drawable.top7, R.drawable.top8, R.drawable.top9, R.drawable.top10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        dbPlayer = new SQLitePlayer(this);
        players = dbPlayer.allPlayers();

        //if you want to delete all the List Players
        for (Players p: players) {
            dbPlayer.deleteOne(p);
        }


        recycleView = findViewById(R.id.listScore);

        nicknames = getResources().getStringArray(R.array.name);
        scores = getResources().getStringArray(R.array.score);

        MyAdapter myadapter = new MyAdapter(this, players, images);
        recycleView.setAdapter(myadapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

    }

}
