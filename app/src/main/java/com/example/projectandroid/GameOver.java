package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class GameOver extends AppCompatActivity {

    private SQLitePlayer dbPlayer;
    private List<Players> players;
    private Button save;
    private Button menu;
    private EditText nickname;
    private String score;
    private Players player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        dbPlayer = new SQLitePlayer(this);
        players = dbPlayer.allPlayers();

        nickname = findViewById(R.id.username);
        menu = findViewById(R.id.menu);
        save = findViewById(R.id.save);

        Bundle tabScore = getIntent().getExtras();
        score = tabScore.getString("score");

        for (Players p: players) {
            Log.i("Name", p.getMyName());
            Log.i("Score", p.getMyScore());
        }


        //Events
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMenu();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nickname.getText().toString();

                player = new Players();
                player.setMyName(name);
                player.setMyScore(score);
                dbPlayer.addPlayer(player);


                Intent scorePage = new Intent(getApplicationContext(), Score.class);
                scorePage.putExtra("saveUser", name);
                Log.i("user",name);
                startActivity(scorePage);
            }
        });
    }

    public void returnMenu(){
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }
}
