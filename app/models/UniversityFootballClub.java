package models;

public class UniversityFootballClub extends FootballClub {
    private String universityName;

    public UniversityFootballClub(String clubName, String clubLocation, String universityName, int numOfMatchesPlayed, int seasonWins, int seasonDefeats,
                                  int seasonDraws, int numOfGoalsReceived, int numOfGoalsScored, int numOfPointsGained) {
        super(clubName, clubLocation, seasonWins, seasonDefeats, seasonDraws, numOfGoalsReceived, numOfGoalsScored, numOfPointsGained, numOfMatchesPlayed);

        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
