package com.example.a3esoleague.helpers;

import android.widget.EditText;

import com.example.a3esoleague.dataBase.sLiteDB;

public class checkInput {

    public static boolean isEmpty(EditText editText){

        if((editText.getText().toString().trim()).equalsIgnoreCase("")) return true;

        else return false;

    }

    public static boolean checkTeamInDataBase(EditText editText){

        if(new sLiteDB(editText.getContext()).checkTeam(editText.getText().toString().trim())) return true;

        else return false;

    }


}
