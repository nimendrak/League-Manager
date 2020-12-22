package models;

import java.time.LocalDate;

public class MatchModel {
    private String date;
    private String stats;
    private String clubName;
    private int goalsScored;
    private int goalsReceived;
    private int points;

    public MatchModel(String date, String clubName,String stats,  int goalsReceived,int goalsScored,
                      int points) {
        this.date = date;
        this.stats = stats;
        this.clubName = clubName;
        this.goalsReceived = goalsReceived;
        this.goalsScored = goalsScored;
        this.points = points;
    }
}
