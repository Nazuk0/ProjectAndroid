package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.Classes.Items;
import com.example.projectandroid.Databases.SQLiteDBHandler;
import com.example.projectandroid.EasyGame.Game_easy;
import com.example.projectandroid.HardGame.Game_hard;
import com.example.projectandroid.MediumGame.Game_medium;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private SQLiteDBHandler db;                                                      //Databases Items
    private RequestQueue mRequestQueue;
    private String keyAPI = "C69E953FB5D44CA080C74124F8B2C529&q";                    //Key of API
    private String api = "https://api.serpwow.com/live/search?api_key";              //url of API

    private Button easy;
    private Button medium;
    private Button hard;
    private Button leave;

    private List<Items> items;
    private String words[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new SQLiteDBHandler(this);
        items = db.allItems();
        words = getResources().getStringArray(R.array.words);

        for (String i: words) {
            String url = api + "=" + keyAPI + "=" + i + "\n";
            if(items.size() == 0) {
                try {
                    sendAndRequestResponse(url, i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //if you want to delete all the List Items
        /*for (Items i: items) {
            db.deleteOne(i);
            Log.i("item:",i.getNameItem());
        }*/

        //affect ID of components
        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        leave = findViewById(R.id.quit);

        //Events onClickButtons
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent easyPage = new Intent(getApplicationContext(), Game_easy.class);
                startActivity(easyPage);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mediumPage = new Intent(getApplicationContext(), Game_medium.class);
                startActivity(mediumPage);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hardPage = new Intent(getApplicationContext(), Game_hard.class);
                startActivity(hardPage);
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
    }


    //Request API
    private void sendAndRequestResponse(String url, final String item) {
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String objNumber  = response.getJSONObject("search_information").getString("total_results");
                            String objImg = response.getJSONObject("knowledge_graph").getJSONArray("images").get(0).toString();

                            Items newItem = new Items(item, objNumber, objImg);
                            db.addItem(newItem);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i(TAG,"Error :" + error.toString());
                    }
                });

        mRequestQueue.add(jsonObjectRequest);
    }
}
