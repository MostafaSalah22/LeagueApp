package com.example.a3esoleague.tableView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3esoleague.R;

import java.util.ArrayList;

public class table_RecyclerViewAdapter extends RecyclerView.Adapter<table_RecyclerViewAdapter.tableViewHolder> {


    ArrayList<com.example.a3esoleague.tableView.clubs> clubs ;

    public table_RecyclerViewAdapter(ArrayList clubs) {
        this.clubs = clubs;
    }

    @NonNull
    @Override
    public tableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_custom_item,null,false);
        tableViewHolder viewHolder = new tableViewHolder(v);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull tableViewHolder holder, int position) {
        clubs c =  clubs.get(position);
        holder.team.setText(c.getClubName());
        holder.points.setText(String.valueOf(c.getPoints()));
        holder.wins.setText(String.valueOf(c.getWin()));
        holder.loses.setText(String.valueOf(c.getLose()));
        holder.draws.setText(String.valueOf(c.getDraw()));
        holder.gI.setText(String.valueOf(c.getgI()));
        holder.gO.setText(String.valueOf(c.getgO()));
        holder.gP.setText(String.valueOf(c.getgP()));

    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }







    class tableViewHolder extends RecyclerView.ViewHolder{
        TextView team , points , wins , loses , draws , gI , gO , gP;
        LinearLayout parent_linear ;
        public tableViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_linear = itemView.findViewById(R.id.parent_Linear);
            team = itemView.findViewById(R.id.team_recyclerMain);
            points = itemView.findViewById(R.id.points_recyclerMain);
            wins = itemView.findViewById(R.id.win_recyclerMain);
            loses = itemView.findViewById(R.id.lose_recyclerMain);
            draws = itemView.findViewById(R.id.draw_recyclerMain);
            gI = itemView.findViewById(R.id.gI_recyclerMain);
            gO = itemView.findViewById(R.id.gO_recyclerMain);
            gP = itemView.findViewById(R.id.gP_recyclerMain);

        }
    }
}
