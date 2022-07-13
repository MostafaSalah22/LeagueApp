package com.example.a3esoleague.helpers;

import android.widget.EditText;
import android.widget.Toast;

import com.example.a3esoleague.helpers.checkInput;

public class checkSetMatchInputs {

    public static boolean isEmpty(EditText club1 , EditText club2 , EditText result1 , EditText result2){

        if(checkInput.isEmpty(club1) || checkInput.isEmpty(club2) ||
                checkInput.isEmpty(result1) || checkInput.isEmpty(result2))
        {
            Toast.makeText(club1.getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
            return true;
        }

        else return false;

    }

    public static boolean checkTeamInDataBase(EditText club1 , EditText club2){

        if(!checkInput.checkTeamInDataBase(club1) || !checkInput.checkTeamInDataBase(club2)){
            Toast.makeText(club1.getContext(), "There is a wrong team", Toast.LENGTH_LONG).show();
            return false;
        }

        else return true;

    }

    public static boolean isTheSameTeam(EditText club1 , EditText club2){

        if(club1.getText().toString().trim().equalsIgnoreCase(club2.getText().toString().trim())) {
            Toast.makeText(club1.getContext(), "It's the same team", Toast.LENGTH_SHORT).show();
            return true;
        }

        else return false;

    }
}
