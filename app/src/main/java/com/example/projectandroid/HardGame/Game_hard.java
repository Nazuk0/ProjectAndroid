package com.example.projectandroid.HardGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.projectandroid.R;


public class Game_hard extends AppCompatActivity implements Game_hard_frag.onMsgListennerHard, Game_hard_result_frag.returnScore {

    private Game_hard_frag gameFrag;
    private Game_hard_result_frag resultFrag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_hard);

        gameFrag = new Game_hard_frag();
        resultFrag = new Game_hard_result_frag();

        if(findViewById(R.id.frag_gameHard) != null){

            if(savedInstanceState!= null){

                return;
            }

            Game_hard_frag GEF= new Game_hard_frag();

            getSupportFragmentManager().beginTransaction().add(R.id.frag_gameHard, GEF, null).commit();
        }
    }

    @Override
    public void ResultHard(String btnItem1, String btnItem2, String btnItem3, String btnItem4, String nbrItem1, String nbrItem2, String nbrItem3, String nbrItem4, String score) {

        Bundle bundle = new Bundle();
        bundle.putString("Item1", btnItem1);
        bundle.putString("Item2", btnItem2);
        bundle.putString("Item3", btnItem3);
        bundle.putString("Item4", btnItem4);
        bundle.putString("Textbtn1", nbrItem1);
        bundle.putString("Textbtn2", nbrItem2);
        bundle.putString("Textbtn3", nbrItem3);
        bundle.putString("Textbtn4", nbrItem4);
        bundle.putString("FinalScore", score);

        resultFrag.setArguments(bundle);

        FragmentTransaction fragTransac = getSupportFragmentManager().beginTransaction().replace(R.id.frag_gameHard,resultFrag, null);
        fragTransac.addToBackStack(null);
        fragTransac.commit();
    }

    @Override
    public void ResultScore(String score) {

        Bundle bundleScore = new Bundle();
        bundleScore.putString("updateScore", score);

        gameFrag.setArguments(bundleScore);

        FragmentTransaction fragTransact = getSupportFragmentManager().beginTransaction().replace(R.id.frag_gameHard,gameFrag, null);
        fragTransact.addToBackStack(null);
        fragTransact.commit();



    }
}