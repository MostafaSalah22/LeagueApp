package com.example.a3esoleague.matchesView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3esoleague.R;
import com.example.a3esoleague.dataBase.sLiteDB;

import java.util.ArrayList;

public class showMatches_RecyclerViewAdapter extends RecyclerView.Adapter<showMatches_RecyclerViewAdapter.showMatchesViewHolder> {

    onUserClicked onUserClicked;

    // 1. define the interface
    public interface onUserClicked{
        void onUserClicked(showMatches_object matches_object);
    }

    // 3.
    public void registerView(onUserClicked onUserClicked){
        this.onUserClicked = onUserClicked;
    }

    ArrayList<showMatches_object> showMatches_objects ;

    public showMatches_RecyclerViewAdapter(ArrayList<showMatches_object> showMatches_objects) {
        this.showMatches_objects = showMatches_objects;
    }

    @NonNull
    @Override
    public showMatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_show_custom_item,null,false);
        showMatches_RecyclerViewAdapter.showMatchesViewHolder viewHolder = new showMatchesViewHolder(v);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull showMatchesViewHolder holder, int position) {
        showMatches_object s =  showMatches_objects.get(position);
        holder.team_show1.setText(s.getTeam_show1());
        holder.team_show2.setText(s.getTeam_show2());
        holder.result_show1.setText(String.valueOf(s.getResult_show1()));
        holder.result_show2.setText(String.valueOf(s.getResult_show2()));
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClicked.onUserClicked(s);
            }
        });
       /* holder.parent_constrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClicked.onUserClicked(s);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return showMatches_objects.size();
    }


    class showMatchesViewHolder extends RecyclerView.ViewHolder{
        TextView team_show1 , team_show2 , result_show1 , result_show2 ;
        LinearLayout parent_constrant ;
        ImageView delete_icon;
        public showMatchesViewHolder(@NonNull View itemView) {
            super(itemView);
            delete_icon = itemView.findViewById(R.id.delete_icon);
            parent_constrant = itemView.findViewById(R.id.parent_constrant);
            team_show1 = itemView.findViewById(R.id.team_show1);
            team_show2 = itemView.findViewById(R.id.team_show2);
            result_show1 = itemView.findViewById(R.id.result_show_1);
            result_show2 = itemView.findViewById(R.id.result_show2);
        }
    }

    public void filter(String text, Context context) {
        sLiteDB mydb=new sLiteDB(context);
        showMatches_objects.clear();
        if(text.isEmpty()){
            showMatches_objects.addAll(mydb.getAllMatches());
        } else{
            text = text.toLowerCase();
            for(showMatches_object item: mydb.getAllMatches()){
                if(item.getTeam_show1().toLowerCase().contains(text) || item.getTeam_show2().toLowerCase().contains(text)){
                    showMatches_objects.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
