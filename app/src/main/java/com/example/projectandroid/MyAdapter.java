package com.example.projectandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Players> listPlayersScore;
    Context context;
    int images[];

    public MyAdapter(Context ct, ArrayList<Players> player, int img[]){
        context = ct;
        listPlayersScore = player;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Players player = listPlayersScore.get(position);

        holder.myName.setText(player.getMyName());
        holder.myScore.setText(player.getMyScore());
        holder.myMedal.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return listPlayersScore.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myName, myScore;
        ImageView myMedal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myName = itemView.findViewById(R.id.pseudo);
            myScore = itemView.findViewById(R.id.scoring);
            myMedal = itemView.findViewById(R.id.medal);

        }
    }
}
