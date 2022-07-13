package com.example.a3esoleague.matchesView;

public class showMatches_object {

    private String team_show1 , team_show2 ;
    private int result_show1 , result_show2 , id ;

    public showMatches_object(String team_show1, String team_show2, int result_show1, int result_show2, int id) {
        this.team_show1 = team_show1;
        this.team_show2 = team_show2;
        this.result_show1 = result_show1;
        this.result_show2 = result_show2;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_show1() {
        return team_show1;
    }

    public void setTeam_show1(String team_show1) {
        this.team_show1 = team_show1;
    }

    public String getTeam_show2() {
        return team_show2;
    }

    public void setTeam_show2(String team_show2) {
        this.team_show2 = team_show2;
    }

    public int getResult_show1() {
        return result_show1;
    }

    public void setResult_show1(int result_show1) {
        this.result_show1 = result_show1;
    }

    public int getResult_show2() {
        return result_show2;
    }

    public void setResult_show2(int result_show2) {
        this.result_show2 = result_show2;
    }
}
