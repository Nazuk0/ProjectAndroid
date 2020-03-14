package com.example.projectandroid.MediumGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.projectandroid.R;


public class Game_medium extends AppCompatActivity implements Game_medium_frag.onMsgListennerMed, Game_medium_result_frag.returnScore {

    private Game_medium_frag gameFrag;
    private Game_medium_result_frag resultFrag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_medium);

        gameFrag = new Game_medium_frag();
        resultFrag = new Game_medium_result_frag();

        if(findViewById(R.id.frag_gameMed) != null){

            if(savedInstanceState!= null){

                return;
            }

            Game_medium_frag GEF= new Game_medium_frag();

            getSupportFragmentManager().beginTransaction().add(R.id.frag_gameMed, GEF, null).commit();
        }
    }

    //Method to send data between fragments
    @Override
    public void ResultMed(String btnItem1, String btnItem2, String btnItem3, String nbrItem1, String nbrItem2, String nbrItem3, String score) {

        Bundle bundle = new Bundle();
        bundle.putString("Item1", btnItem1);
        bundle.putString("Item2", btnItem2);
        bundle.putString("Item3", btnItem3);
        bundle.putString("Textbtn1", nbrItem1);
        bundle.putString("Textbtn2", nbrItem2);
        bundle.putString("Textbtn3", nbrItem3);
        bundle.putString("FinalScore", score);

        resultFrag.setArguments(bundle);

        FragmentTransaction fragTransac = getSupportFragmentManager().beginTransaction().replace(R.id.frag_gameMed,resultFrag, null);
        fragTransac.addToBackStack(null);
        fragTransac.commit();
    }

    //Method to get back score updated
    @Override
    public void ResultScore(String score) {

        Bundle bundleScore = new Bundle();
        bundleScore.putString("updateScore", score);

        gameFrag.setArguments(bundleScore);

        FragmentTransaction fragTransact = getSupportFragmentManager().beginTransaction().replace(R.id.frag_gameMed,gameFrag, null);
        fragTransact.addToBackStack(null);
        fragTransact.commit();



    }
}