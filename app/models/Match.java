package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Match implements Serializable {
    private FootballClub teamOne;
    private FootballClub teamTwo;
    private int teamOneScore;
    private int teamTwoScore;
    private LocalDate date;

    public Match(FootballClub teamOne, FootballClub teamTwo, int teamOneScore, int teamTwoScore, LocalDate date) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
        this.date = date;
    }

    public void updateStats() {
        this.teamOne.setNumOfMatchesPlayed(this.teamOne.getNumOfMatchesPlayed() + 1);
        this.teamOne.setNumOfGoalsScored(this.teamOne.getNumOfGoalsScored() + this.teamOneScore);
        this.teamOne.setNumOfGoalsReceived(this.teamOne.getNumOfGoalsReceived() + this.teamTwoScore);

        this.teamTwo.setNumOfMatchesPlayed(this.teamTwo.getNumOfMatchesPlayed() + 1);
        this.teamTwo.setNumOfGoalsScored(this.teamTwo.getNumOfGoalsScored() + this.teamTwoScore);
        this.teamTwo.setNumOfGoalsReceived(this.teamTwo.getNumOfGoalsReceived() + this.teamOneScore);

        if (this.teamOneScore > this.teamTwoScore) {
            this.teamOne.setSeasonWins(this.teamOne.getSeasonWins() + 1);
            this.teamTwo.setSeasonDefeats(this.teamTwo.getSeasonDefeats() + 1);
            this.teamOne.setNumOfPointsGained(this.teamOne.getNumOfPointsGained() + 3);

        } else if (this.teamOneScore < this.teamTwoScore) {
            this.teamTwo.setSeasonWins(this.teamTwo.getSeasonWins() + 1);
            this.teamOne.setSeasonDefeats(this.teamOne.getSeasonDefeats() + 1);
            this.teamTwo.setNumOfPointsGained(this.teamTwo.getNumOfPointsGained() + 3);

        } else {
            this.teamOne.setSeasonDraws(this.teamOne.getSeasonDraws() + 1);
            this.teamTwo.setSeasonDraws(this.teamTwo.getSeasonDraws() + 1);
            this.teamOne.setNumOfPointsGained(this.teamOne.getNumOfPointsGained() + 1);
            this.teamTwo.setNumOfPointsGained(this.teamTwo.getNumOfPointsGained() + 1);
        }
    }

    public FootballClub getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(FootballClub teamOne) {
        this.teamOne = teamOne;
    }

    public FootballClub getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(FootballClub teamTwo) {
        this.teamTwo = teamTwo;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PlayedMatch{" + "teamOne=" + teamOne.getClubName() + ", teamTwo=" + teamTwo.getClubName() +
                ", teamOneScore=" + teamOneScore + ", teamTwoScore=" + teamTwoScore + ", date=" + date;
    }
}
