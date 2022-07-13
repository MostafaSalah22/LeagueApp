package com.example.a3esoleague.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a3esoleague.matchesView.showMatches_object;
import com.example.a3esoleague.tableView.clubs;

import java.util.ArrayList;

public class sLiteDB extends SQLiteOpenHelper {
    public static final String DataBaseName = "league";
    public sLiteDB(Context con) {
        super(con,DataBaseName,null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table league(club TEXT , points INTEGER , win INTEGER , lose INTEGER , draw INTEGER , gI INTEGER , gO INTEGER , gP INTEGER) ");

        db.execSQL("create table show_matches(team_show1 TEXT , team_show2 TEXT , result_show1 INTEGER , result_show2 INTEGER , id INTEGER PRIMARY KEY AUTOINCREMENT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS league");
        db.execSQL("DROP TABLE IF EXISTS show_matches");
        onCreate(db);
    }

    public  boolean enoughClubsInLeague()
    {
        SQLiteDatabase s = this.getWritableDatabase();
        Cursor c = s.rawQuery("select * from league",null);

        if(c.getCount() > 1) {return true;}
        else {return false;}
    }
    public boolean checkTeam(String team)
    {
        SQLiteDatabase s = this.getWritableDatabase();
        Cursor cursor = s.rawQuery("select * from league where club = ?", new String[]{team});
        if (cursor.getCount()>0)
        {return true;}
        else
        {return false;}
    }
    public Boolean insertData(String c)
    {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("club",c);
        values.put("points","0");
        values.put("win","0");
        values.put("draw","0");
        values.put("lose","0");
        values.put("gI","0");
        values.put("gO","0");
        values.put("gP","0");
        long re = s.insert("league", null,values);
        if (re == -1)return false;
        else return true;

    }

    public Boolean insertData(String team_show1 , String team_show2 , int result_show1 , int result_show2){
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("team_show1",team_show1);
        values.put("team_show2",team_show2);
        values.put("result_show1",result_show1);
        values.put("result_show2",result_show2);
        long re = s.insert("show_matches", null,values);
        if (re == -1)return false;
        else return true;

    }

    public void delete() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("league", null, null);

    }

    public void deleteAllMatches(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("show_matches" , null , null);
    }


    public void deleteMatch(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("show_matches",  "id=?"  , new String[]{String.valueOf(id)});
    }

    public void updateData(String c , int p , int w, int d, int l, int gI , int gO , int gP)
    {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("points",p);
        values.put("win",w);
        values.put("draw",d);
        values.put("lose",l);
        values.put("gI",gI);
        values.put("gO",gO);
        values.put("gP",gP);
        s.update("league",values,"club=?",new String[]{c});
    }

    public void repeatLeague()
    {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int i=0 ; i<getAllClubs().size() ; i++) {
            String c = getAllClubs().get(i).getClubName();
            values.put("points", "0");
            values.put("win", "0");
            values.put("draw", "0");
            values.put("lose", "0");
            values.put("gI", "0");
            values.put("gO", "0");
            values.put("gP", "0");
            s.update("league", values, "club=?", new String[]{c});
        }
        deleteAllMatches();
    }

    public void updateAfterDeleteMatch(String team_show1 , String team_show2 , int result_show1 , int result_show2){
        SQLiteDatabase s = this.getWritableDatabase();
        int  gINew = 0 , gONew = 0 , points = 0 , gPNew = 0 ;
        if(result_show1 == result_show2){
            int draw = 0 ;

            for(int i = 0 ; i<getAllClubs().size() ; i++) {
                if ((getAllClubs().get(i).getClubName()).equalsIgnoreCase(team_show1)) {
                    draw = getAllClubs().get(i).getDraw() - 1 ;
                    gINew = getAllClubs().get(i).getgI() - result_show1;
                    gONew = getAllClubs().get(i).getgO() - result_show2;
                    points = getAllClubs().get(i).getPoints() - 1;
                    gPNew = getAllClubs().get(i).getgP() - 1;

                    ContentValues values = new ContentValues();
                    values.put("draw",draw);
                    values.put("gI",gINew);
                    values.put("gO",gONew);
                    values.put("points",points);
                    values.put("gP",gPNew);
                    s.update("league",values,"club=?",new String[]{team_show1});
                }
                if((getAllClubs().get(i).getClubName()).equalsIgnoreCase(team_show2)){
                    draw = getAllClubs().get(i).getDraw() - 1 ;
                    gINew = getAllClubs().get(i).getgI() - result_show1;
                    gONew = getAllClubs().get(i).getgO() - result_show2;
                    points = getAllClubs().get(i).getPoints() - 1;
                    gPNew = getAllClubs().get(i).getgP() - 1;

                    ContentValues values = new ContentValues();
                    values.put("draw",draw);
                    values.put("gI",gINew);
                    values.put("gO",gONew);
                    values.put("points",points);
                    values.put("gP",gPNew);
                    s.update("league",values,"club=?",new String[]{team_show2});

                }

            }

        }
         else{

            int win = 0 ;
            for(int i = 0 ; i<getAllClubs().size() ; i++) {
                if ((getAllClubs().get(i).getClubName()).equalsIgnoreCase(team_show1)) {
                    win = getAllClubs().get(i).getWin() - 1 ;
                    gINew = getAllClubs().get(i).getgI() - result_show1 ;
                    gONew = getAllClubs().get(i).getgO() - result_show2 ;
                    points = getAllClubs().get(i).getPoints() - 3;
                    gPNew = getAllClubs().get(i).getgP() - 1;

                    ContentValues values = new ContentValues();
                    values.put("win",win);
                    values.put("gI",gINew);
                    values.put("gO",gONew);
                    values.put("points",points);
                    values.put("gP",gPNew);
                    s.update("league",values,"club=?",new String[]{team_show1});

                }

                int lose = 0  ;
                if ((getAllClubs().get(i).getClubName()).equalsIgnoreCase(team_show2)) {
                    lose = getAllClubs().get(i).getLose() - 1;
                    gINew = getAllClubs().get(i).getgI() - result_show2;
                    gONew = getAllClubs().get(i).getgO() - result_show1;
                    gPNew = getAllClubs().get(i).getgP() - 1;
                    
                    ContentValues values = new ContentValues();
                    values.put("lose",lose);
                    values.put("gI",gINew);
                    values.put("gO",gONew);
                    values.put("gP",gPNew);
                    s.update("league",values,"club=?",new String[]{team_show2});

                }
            }

        }

    }

    public void updateDraw(String c , int gI , int gO)
    {

        int draw = 0 , gINew = 0 , gONew = 0 , points = 0 , gPNew = 0 ;
        for(int i = 0 ; i<getAllClubs().size() ; i++) {
            if ((getAllClubs().get(i).getClubName()).equalsIgnoreCase(c)) {
                draw = getAllClubs().get(i).getDraw() + 1 ;
                gINew = getAllClubs().get(i).getgI() + gI ;
                gONew = getAllClubs().get(i).getgO() + gO ;
                points = getAllClubs().get(i).getPoints() + 1;
                gPNew = getAllClubs().get(i).getgP() + 1;
                break;

            }
        }


        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("draw",draw);
        values.put("gI",gINew);
        values.put("gO",gONew);
        values.put("points",points);
        values.put("gP",gPNew);
        s.update("league",values,"club=?",new String[]{c});

    }

    public void updateWin(String c ,  int gI , int gO)
    {
        int win = 0 , gINew = 0 , gONew = 0 , points = 0 , gPNew = 0 ;
        for(int i = 0 ; i<getAllClubs().size() ; i++) {
            if ((getAllClubs().get(i).getClubName()).equalsIgnoreCase(c)) {
                win = getAllClubs().get(i).getWin() + 1 ;
                gINew = getAllClubs().get(i).getgI() + gI ;
                gONew = getAllClubs().get(i).getgO() + gO ;
                points = getAllClubs().get(i).getPoints() + 3;
                gPNew = getAllClubs().get(i).getgP() + 1;
                break;
            }
        }


        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("win",win);
        values.put("gI",gINew);
        values.put("gO",gONew);
        values.put("points",points);
        values.put("gP",gPNew);
        s.update("league",values,"club=?",new String[]{c});
    }

        public void updateLose(String c , int gI , int gO)
        {

            int lose = 0 , gINew = 0 , gONew = 0 , gPNew = 0 ;
            for(int i = 0 ; i<getAllClubs().size() ; i++) {
                if ((getAllClubs().get(i).getClubName()).equalsIgnoreCase(c)) {
                    lose = getAllClubs().get(i).getLose() + 1;
                    gINew = getAllClubs().get(i).getgI() + gI;
                    gONew = getAllClubs().get(i).getgO() + gO;
                    gPNew = getAllClubs().get(i).getgP() + 1;
                    break;
                }
            }


            SQLiteDatabase s = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("lose",lose);
            values.put("gI",gINew);
            values.put("gO",gONew);
            values.put("gP",gPNew);
            s.update("league",values,"club=?",new String[]{c});
        }



    public ArrayList<clubs> getAllClubs(){
        ArrayList<clubs> clubs = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM league",null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()==false)
        {
            String club = cursor.getString(cursor.getColumnIndex("club"));
            int points = cursor.getInt(cursor.getColumnIndex("points"));
            int win = cursor.getInt(cursor.getColumnIndex("win"));
            int lose = cursor.getInt(cursor.getColumnIndex("lose"));
            int draw = cursor.getInt(cursor.getColumnIndex("draw"));
            int gI = cursor.getInt(cursor.getColumnIndex("gI"));
            int gO = cursor.getInt(cursor.getColumnIndex("gO"));
            int gP = cursor.getInt(cursor.getColumnIndex("gP"));
            clubs Clubs = new clubs(club,points,win,lose,draw,gI,gO,gP);
            clubs.add(Clubs);
            cursor.moveToNext();
        }



        return clubs;
    }

    public ArrayList<clubs> sortTable()
    {
        ArrayList<clubs> clubs = new ArrayList<>();
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cursor=sq.rawQuery("SELECT * FROM league ORDER BY points DESC ,gI-gO DESC,gI DESC",null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String club = cursor.getString(cursor.getColumnIndex("club"));
            int gP = cursor.getInt(cursor.getColumnIndex("gP"));
            int points = cursor.getInt(cursor.getColumnIndex("points"));
            int win = cursor.getInt(cursor.getColumnIndex("win"));
            int draw = cursor.getInt(cursor.getColumnIndex("draw"));
            int lose = cursor.getInt(cursor.getColumnIndex("lose"));
            int gI = cursor.getInt(cursor.getColumnIndex("gI"));
            int gO = cursor.getInt(cursor.getColumnIndex("gO"));

            clubs.add(new clubs(club,points,win,lose,draw,gI,gO,gP));
            cursor.moveToNext();
        }
        return clubs;
    }


    public ArrayList<showMatches_object> getAllMatches(){
        ArrayList<showMatches_object> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM show_matches",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            String team_show1 = cursor.getString(cursor.getColumnIndex("team_show1"));
            String team_show2 = cursor.getString(cursor.getColumnIndex("team_show2"));
            int result_show1 = cursor.getInt(cursor.getColumnIndex("result_show1"));
            int result_show2 = cursor.getInt(cursor.getColumnIndex("result_show2"));
            int id = cursor.getInt(4);
            showMatches_object matches_object = new showMatches_object(team_show1,team_show2,result_show1,result_show2,id);
            arrayList.add(matches_object);
            cursor.moveToNext();
        }

        return arrayList;
    }


}
