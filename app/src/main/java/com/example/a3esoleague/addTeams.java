package com.example.a3esoleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a3esoleague.dataBase.sLiteDB;
import com.example.a3esoleague.helpers.checkAddTeamsInputs;
import com.example.a3esoleague.tableView.leagueTable;

public class addTeams extends AppCompatActivity {

    EditText et_clubs;
    TextView tv_addedTeams;
    sLiteDB mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teams);

        et_clubs = findViewById(R.id.et_clubs);
        tv_addedTeams = findViewById(R.id.tv_addedTeams);

        mydb=new sLiteDB(this);

        if(mydb.enoughClubsInLeague())
        {
            Intent i=new Intent(addTeams.this, leagueTable.class);
            startActivity(i);
            finish();
        }


        findViewById(R.id.btn_addClub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkAddTeamsInputs.isEmpty(et_clubs) && !checkAddTeamsInputs.checkTeamInDataBase(et_clubs)){
                    mydb.insertData(et_clubs.getText().toString().trim());
                    visibleTeamsAdded();
                    et_clubs.setText("");
                }
            }
        });


        findViewById(R.id.btn_doneClubs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkAddTeamsInputs.enoughClubsInLeague(et_clubs)){
                    Intent i=new Intent(addTeams.this, leagueTable.class);
                    startActivity(i);
                }
            }
        });

    }

    public void visibleTeamsAdded(){
        findViewById(R.id.textView15).setVisibility(View.VISIBLE);
        tv_addedTeams.setVisibility(View.VISIBLE);
        tv_addedTeams.setText(tv_addedTeams.getText().toString().trim() + " - " + et_clubs.getText().toString().trim());

    }
}