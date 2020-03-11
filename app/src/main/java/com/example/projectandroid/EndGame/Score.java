package com.example.projectandroid.EndGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectandroid.Classes.Players;
import com.example.projectandroid.MainActivity;
import com.example.projectandroid.MyAdapter;
import com.example.projectandroid.R;
import com.example.projectandroid.Databases.SQLitePlayer;

import java.util.ArrayList;

public class Score extends AppCompatActivity {

    private RecyclerView recycleView;
    private SQLitePlayer dbPlayer;                  //DB Player
    private ArrayList<Players> players;             //List player
    private Button returnMenu;
    private int[] images = {R.drawable.top1, R.drawable.top2, R.drawable.top3, R.drawable.top4, R.drawable.top5,
                            R.drawable.top6, R.drawable.top7, R.drawable.top8, R.drawable.top9, R.drawable.top10,
                            R.drawable.blanc,R.drawable.blanc,R.drawable.blanc,R.drawable.blanc,R.drawable.blanc,
                            R.drawable.blanc,R.drawable.blanc,R.drawable.blanc,R.drawable.blanc,R.drawable.blanc,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        dbPlayer = new SQLitePlayer(this);
        players = dbPlayer.allPlayers();

        //if you want to delete all the List Players
        /*for (Players p: players) {
            dbPlayer.deleteOne(p);
        }*/

        //Search ID for components
        recycleView = findViewById(R.id.listScore);
        returnMenu = findViewById(R.id.btnMenu);

        MyAdapter myadapter = new MyAdapter(this, players, images);
        recycleView.setAdapter(myadapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        //Events onClickButton
        returnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backMenu);
            }
        });

    }

}
