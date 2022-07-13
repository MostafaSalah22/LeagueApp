package com.example.a3esoleague.tableView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a3esoleague.R;
import com.example.a3esoleague.addTeams;
import com.example.a3esoleague.dataBase.sLiteDB;
import com.example.a3esoleague.matchesView.showMatches;
import com.example.a3esoleague.setMatch;

public class leagueTable extends AppCompatActivity {
    RecyclerView rv;
    table_RecyclerViewAdapter adapter;
    sLiteDB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        mydb = new sLiteDB(this);
        rv = findViewById(R.id.rv_table);


        adapter = new table_RecyclerViewAdapter(mydb.sortTable());


        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);


        findViewById(R.id.delete_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog deleteDialog = new AlertDialog.Builder(leagueTable.this).create();
                deleteDialog.setTitle("3eso League");
                deleteDialog.setMessage("Do you want to make a new league with new teams?");

                deleteDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.delete();
                        mydb.deleteAllMatches();

                        Intent i = new Intent(leagueTable.this, addTeams.class);
                        startActivity(i);
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
        });


        findViewById(R.id.set_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(leagueTable.this, setMatch.class);
                startActivity(i);
                finish();
            }
        });


        findViewById(R.id.repeatLeague_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog deleteDialog = new AlertDialog.Builder(leagueTable.this).create();
                deleteDialog.setTitle("3eso League");
                deleteDialog.setMessage("Do you want to repeat league with the same teams?");

                deleteDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.repeatLeague();

                        Intent i = new Intent(leagueTable.this, leagueTable.class);
                        startActivity(i);
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
        });


        findViewById(R.id.btn_toMatches).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(leagueTable.this, showMatches.class);
                startActivity(intent);
                finish();
            }
        });

    }

}