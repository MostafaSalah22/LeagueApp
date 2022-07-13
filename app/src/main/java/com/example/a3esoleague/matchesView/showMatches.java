package com.example.a3esoleague.matchesView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.a3esoleague.R;
import com.example.a3esoleague.dataBase.sLiteDB;
import com.example.a3esoleague.tableView.leagueTable;

public class showMatches extends AppCompatActivity implements showMatches_RecyclerViewAdapter.onUserClicked {
    RecyclerView rv;
    showMatches_RecyclerViewAdapter adapter;
    sLiteDB mydb;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_matches);
        mydb = new sLiteDB(this);
        rv = findViewById(R.id.rv_showMatches);
        searchView = findViewById(R.id.searchView);

        if(mydb.getAllMatches().size()>0) {
            findViewById(R.id.img_empty).setVisibility(View.GONE);
            findViewById(R.id.tv_empty).setVisibility(View.GONE);
            searchView.setVisibility(View.VISIBLE);
            searchView.setIconified(false);
            searchView.clearFocus();
        }
        else{
            findViewById(R.id.showMatches_parent).setBackgroundColor(Color.WHITE);
        }

        adapter = new showMatches_RecyclerViewAdapter(mydb.getAllMatches());

        adapter.registerView(this);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query.trim(),getApplicationContext());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText.trim(),getApplicationContext());
                return true;
            }
        });

    }


    @Override
    public void onUserClicked(showMatches_object matches_object) {

        AlertDialog deleteDialog = new AlertDialog.Builder(showMatches.this).create();
        deleteDialog.setTitle("3eso League");
        deleteDialog.setMessage("Are you sure to delete" +"\n"+"("+ matches_object.getTeam_show1() + "  "+ matches_object.getResult_show1() + " - " + matches_object.getResult_show2() + "  " + matches_object.getTeam_show2() + ")");

        deleteDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mydb.deleteMatch(matches_object.getId());
                mydb.updateAfterDeleteMatch(matches_object.getTeam_show1(),matches_object.getTeam_show2(),matches_object.getResult_show1(),matches_object.getResult_show2());

                Intent intent = new Intent(showMatches.this,showMatches.class);
                startActivity(intent);
                finish();
                deleteDialog.dismiss();
            }
        });

        deleteDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDialog.dismiss();
            }
        });

        deleteDialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(showMatches.this, leagueTable.class);
        startActivity(intent);
        finish();
    }
}