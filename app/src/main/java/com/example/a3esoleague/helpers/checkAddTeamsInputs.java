package com.example.a3esoleague.helpers;

import android.widget.EditText;

import com.example.a3esoleague.dataBase.sLiteDB;

public class checkAddTeamsInputs {

    public static boolean isEmpty(EditText et_clubs){

        if(checkInput.isEmpty(et_clubs)) {
            et_clubs.setError("Enter the club name");
            et_clubs.requestFocus();
            return true;
        }

        else return false;

    }

    public static boolean checkTeamInDataBase(EditText et_clubs){

        if(checkInput.checkTeamInDataBase(et_clubs)) {
            et_clubs.setError("This club already in league");
            et_clubs.requestFocus();
            return true;
        }
        else return false;

    }

    public static boolean enoughClubsInLeague(EditText et_clubs){

        if(new sLiteDB(et_clubs.getContext()).getAllClubs().size() == 0 || new sLiteDB(et_clubs.getContext()).getAllClubs().size() == 1) {
            et_clubs.setError("Add the league clubs name");
            et_clubs.requestFocus();
            return false;
        }

        else return true;

    }

}
