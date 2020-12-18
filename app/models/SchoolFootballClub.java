package models;

public class SchoolFootballClub extends FootballClub {
    private String schoolName;

    public SchoolFootballClub(String clubName, String clubLocation, int numOfMatchesPlayed, String schoolName, int seasonWins, int seasonDefeats,
                              int seasonDraws, int numOfGoalsReceived, int numOfGoalsScored, int numOfPointsGained) {

        super(clubName, clubLocation, seasonWins, seasonDefeats, seasonDraws, numOfGoalsReceived, numOfGoalsScored, numOfPointsGained, numOfMatchesPlayed);

        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


}
