package com.example.projectandroid.EndGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectandroid.Classes.Players;
import com.example.projectandroid.MainActivity;
import com.example.projectandroid.R;
import com.example.projectandroid.Databases.SQLitePlayer;

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

        //Connect DB
        dbPlayer = new SQLitePlayer(this);
        players = dbPlayer.allPlayers();
        Vibrator vib;

        //Find ID in View
        nickname = findViewById(R.id.username);
        menu = findViewById(R.id.menu);
        save = findViewById(R.id.save);

        //Get Final Score
        Bundle tabScore = getIntent().getExtras();
        score = tabScore.getString("score");

        //Sensor vibrate
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(1000);

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

                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                String name = nickname.getText().toString();

                player = new Players(name, score);
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
