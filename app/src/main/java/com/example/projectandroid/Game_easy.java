package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class Game_easy extends AppCompatActivity {

    private static final String TAG = Game_easy.class.getName();

    int score = 0;
    int numberItem1 = 0;
    int numberItem2 = 0;

    Button item1;
    Button item2;

    private RequestQueue mRequestQueue;

    TextView resultItem1;
    TextView resultItem2;
    ImageView resultImg;

    boolean play = true;

    private String keyAPI = "C69E953FB5D44CA080C74124F8B2C529&q";
    private String api = "https://api.serpwow.com/live/search?api_key";
    String txtItem1;
    String txtItem2;
    String url1;
    String url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy);


        item1 = findViewById(R.id.item1);
        item1.setText(getRandomString());
        txtItem1 = item1.getText().toString();
        url1 = api + "=" + keyAPI + "=" + txtItem1 + "\n";
        Log.i("URL1 :",url1);

        item2 = findViewById(R.id.item2);
        item2.setText(getRandomString());
        txtItem2 = item2.getText().toString();
        url2 = api + "=" + keyAPI + "=" + txtItem2 + "\n";
        Log.i("URL2 :",url2);

        resultItem1 = findViewById(R.id.resultTxt1);
        resultItem2 = findViewById(R.id.resultTxt2);
        resultImg = findViewById(R.id.img);

        while(item2.getText() == item1.getText()){
            item2.setText(getRandomString());
        }

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game();
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public String getRandomString(){
        String[] array = getResources().getStringArray(R.array.words);
        String randomStr = array[new Random().nextInt(array.length)];

        return randomStr;
    }

    private void sendAndRequestResponse(String url, final TextView TV) {
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String objNumber  = response.getJSONObject("search_information").getString("total_results");
                            String objImg = response.getJSONObject("knowledge_graph").getJSONArray("images").get(0).toString();
                            TV.setText(objNumber);

                            //Picasso.with(getApplicationContext()).load(objImg).into(resultImg);

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

    public void Game() {

        sendAndRequestResponse(url1,resultItem1);
        sendAndRequestResponse(url2,resultItem2);
        Log.i("BUTTON1",resultItem1.getText().toString());
        Log.i("BUTTON2",resultItem2.getText().toString());

        //numberItem1 = Integer.parseInt(resultItem1.getText().toString());
        //numberItem2 = Integer.parseInt(resultItem2.getText().toString());

        /*if(numberItem1 < numberItem2) {
            item1.setBackgroundColor(Color.RED);
            item2.setBackgroundColor(Color.GREEN);
        }else{
            item2.setBackgroundColor(Color.RED);
            item1.setBackgroundColor(Color.GREEN);
        }*/
    }
}
