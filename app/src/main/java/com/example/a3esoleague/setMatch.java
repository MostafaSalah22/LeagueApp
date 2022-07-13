package com.example.a3esoleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a3esoleague.dataBase.sLiteDB;
import com.example.a3esoleague.helpers.checkSetMatchInputs;
import com.example.a3esoleague.tableView.leagueTable;

public class setMatch extends AppCompatActivity {
EditText club1,club2,result1,result2;
String theWinner,theLoser;
int theWinnerR,theLoserR;
    sLiteDB mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_match);
        mydb=new sLiteDB(this);
        findViewById(R.id.Result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                club1 = (EditText) findViewById(R.id.club1);
                club2 = (EditText) findViewById(R.id.club2);
                result1 = (EditText) findViewById(R.id.re1);
                result2 = (EditText) findViewById(R.id.re2);

                    if(!checkSetMatchInputs.isEmpty(club1,club2,result1,result2) && !checkSetMatchInputs.isTheSameTeam(club1,club2) && checkSetMatchInputs.checkTeamInDataBase(club1,club2) )
                    {
                        matchResult(club1,club2,result1,result2);

                        mydb.insertData(theWinner,theLoser,theWinnerR,theLoserR);

                        if (theWinnerR == theLoserR) {

                            mydb.updateDraw(theWinner, theWinnerR, theLoserR);
                            mydb.updateDraw(theLoser, theWinnerR, theLoserR);


                        } else {
                            mydb.updateWin(theWinner, theWinnerR, theLoserR);
                            mydb.updateLose(theLoser, theLoserR, theWinnerR);

                        }
                        Intent intent = new Intent(setMatch.this, leagueTable.class);
                        startActivity(intent);
                        finish();
                    }
                }
        });
    }

    public void matchResult(EditText club1 , EditText club2 , EditText result1 , EditText result2 ){

        if ((Integer.parseInt(result1.getText().toString().trim())) > (Integer.parseInt(result2.getText().toString().trim()))) {
            theWinner = club1.getText().toString().trim();
            theLoser = club2.getText().toString().trim();
            theWinnerR = Integer.parseInt(result1.getText().toString().trim());
            theLoserR = Integer.parseInt(result2.getText().toString().trim());
        } else if ((Integer.parseInt(result1.getText().toString().trim())) < (Integer.parseInt(result2.getText().toString().trim()))) {
            theWinner = club2.getText().toString().trim();
            theLoser = club1.getText().toString().trim();
            theWinnerR = Integer.parseInt(result2.getText().toString().trim());
            theLoserR = Integer.parseInt(result1.getText().toString().trim());
        } else {
            theWinner = club1.getText().toString().trim();
            theLoser = club2.getText().toString().trim();
            theWinnerR = theLoserR = Integer.parseInt(result1.getText().toString().trim());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(setMatch.this,leagueTable.class);
        startActivity(intent);
    }
}