package com.example.a3esoleague.tableView;

public class clubs {
    private String clubName;
    private int points,win,lose,draw,gI,gO,gP;

    public clubs(String clubName, int points, int win, int lose, int draw, int gI, int gO, int gP) {
        this.clubName = clubName;
        this.points = points;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.gI = gI;
        this.gO = gO;
        this.gP = gP;
    }

    public int getgP() {
        return gP;
    }

    public void setgP(int gP) {
        this.gP = gP;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getgI() {
        return gI;
    }

    public void setgI(int gI) {
        this.gI = gI;
    }

    public int getgO() {
        return gO;
    }

    public void setgO(int gO) {
        this.gO = gO;
    }

    /*@Override
    public int compareTo(clubs o) {

            if (points == o.points) {
                    if ((gI - gO) == (o.gI - o.gO)) {
                        if (gI == o.gI)
                            return 0;
                        else if (gI > o.gI)
                            return 1;
                        else
                            return -1;
                    }
                    else if ((gI - gO) > (o.gI - o.gO))
                        return 1;
                    else
                        return -1;
            }
            else if (points > o.points)
                return 1;
            else
                return -1;
    }*/
}
