package models;

public class FootballClub extends SportsClub implements Comparable<FootballClub> {
    private int numOfMatchesPlayed;
    private int seasonWins;
    private int seasonDefeats;
    private int seasonDraws;
    private int numOfGoalsReceived;
    private int numOfGoalsScored;
    private int numOfPointsGained;
    private int goalsDifference;

    public FootballClub(String clubName, String clubLocation) {
        super(clubName, clubLocation);
    }

    public int getSeasonWins() {
        return seasonWins;
    }

    public void setSeasonWins(int seasonWins) {
        this.seasonWins = seasonWins;
    }

    public int getSeasonDefeats() {
        return seasonDefeats;
    }

    public void setSeasonDefeats(int seasonDefeats) {
        this.seasonDefeats = seasonDefeats;
    }

    public int getSeasonDraws() {
        return seasonDraws;
    }

    public void setSeasonDraws(int seasonDraws) {
        this.seasonDraws = seasonDraws;
    }

    public int getNumOfGoalsReceived() {
        return numOfGoalsReceived;
    }

    public void setNumOfGoalsReceived(int numOfGoalsReceived) {
        this.numOfGoalsReceived = numOfGoalsReceived;
    }

    public int getNumOfGoalsScored() {
        return numOfGoalsScored;
    }

    public void setNumOfGoalsScored(int numOfGoalsScored) {
        this.numOfGoalsScored = numOfGoalsScored;
    }

    public int getNumOfPointsGained() {
        return numOfPointsGained;
    }

    public void setNumOfPointsGained(int numOfPointsGained) {
        this.numOfPointsGained = numOfPointsGained;
    }

    public int getNumOfMatchesPlayed() {
        return numOfMatchesPlayed;
    }

    public void setNumOfMatchesPlayed(int numOfMatchesPlayed) {
        this.numOfMatchesPlayed = numOfMatchesPlayed;
    }

    public int getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(int goalsDifference) {
        this.goalsDifference = goalsDifference;
    }

    @Override
    public String toString() {
        return super.toString() + "\t\t\t\t" + String.format("%02d", numOfMatchesPlayed) + "  " + String.format("%02d", seasonWins) + "  " + String.format("%02d", seasonDefeats) + "  " +
                String.format("%02d", seasonDraws) + "  " + String.format("%02d", numOfGoalsScored) + "  " + String.format("%02d", goalsDifference) + "  " + String.format("%02d", numOfPointsGained);
    }

    @Override
    public int compareTo(FootballClub o) {
        if (this.numOfMatchesPlayed != 0 && o.numOfMatchesPlayed != 0) {
            if (this.numOfPointsGained == o.numOfPointsGained) {
//          if goalsScored same, checking goal difference here
                if ((this.goalsDifference) > ((o.goalsDifference))) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (this.numOfPointsGained > o.numOfPointsGained) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }

}

