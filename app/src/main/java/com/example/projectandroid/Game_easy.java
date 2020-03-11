package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class Game_easy extends AppCompatActivity implements Game_easy_frag.onMsgListenner, Game_easy_result_frag.returnScore{

    private Game_easy_frag gameFrag;
    private Game_easy_result_frag resultFrag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy);

        gameFrag = new Game_easy_frag();
        resultFrag = new Game_easy_result_frag();

        if(findViewById(R.id.frag_game) != null){

            if(savedInstanceState!= null){

                return;
            }

            Game_easy_frag GEF= new Game_easy_frag();

            getSupportFragmentManager().beginTransaction().add(R.id.frag_game, GEF, null).commit();
        }
    }

    @Override
    public void ResultEasy(String btnItem1, String btnItem2, String nbrItem1, String nbrItem2, String score) {

        Bundle bundle = new Bundle();
        bundle.putString("Item1", btnItem1);
        bundle.putString("Item2", btnItem2);
        bundle.putString("Textbtn1", nbrItem1);
        bundle.putString("Textbtn2", nbrItem2);
        bundle.putString("FinalScore", score);

        resultFrag.setArguments(bundle);

        FragmentTransaction fragTransac = getSupportFragmentManager().beginTransaction().replace(R.id.frag_game,resultFrag, null);
        fragTransac.addToBackStack(null);
        fragTransac.commit();
    }

    @Override
    public void ResultScore(String score) {

        Bundle bundleScore = new Bundle();
        bundleScore.putString("updateScore", score);

        gameFrag.setArguments(bundleScore);

        FragmentTransaction fragTransact = getSupportFragmentManager().beginTransaction().replace(R.id.frag_game,gameFrag, null);
        fragTransact.addToBackStack(null);
        fragTransact.commit();



    }
}