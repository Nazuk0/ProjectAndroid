package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameOver extends AppCompatActivity {

    Button save;
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMenu();
            }
        });

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText usernameText = findViewById(R.id.username);
                String name = usernameText.getText().toString();

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
