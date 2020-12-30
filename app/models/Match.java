package models;

import services.DataSourceServices;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Match implements Serializable {
    private LocalDate date;
    private String teamOneName;
    private int teamOneScore;
    private String teamTwoName;
    private int teamTwoScore;
    private String matchStats;

    public Match(LocalDate date, String teamOneName, String teamTwoName,
                 int teamOneScore, int teamTwoScore, String matchStats) {
        this.date = date;
        this.teamOneName = teamOneName;
        this.teamOneScore = teamOneScore;
        this.teamTwoName = teamTwoName;
        this.teamTwoScore = teamTwoScore;
        this.matchStats = matchStats;
    }

    public void updateStats(String typeOfApp) {
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
        DataSourceServices dataSourceServices = DataSourceServices.getInstance();
        FootballClub teamOne = null, teamTwo = null;

        if (typeOfApp.equalsIgnoreCase("cliApp")) {
            for (FootballClub f : premierLeagueManager.getTeamList()) {
                if (f.getClubName().equalsIgnoreCase(teamOneName)) {
                    teamOne = f;
                }
                if (f.getClubName().equalsIgnoreCase(teamTwoName)) {
                    teamTwo = f;
                }
            }
        } else {
            for (FootballClub f : dataSourceServices.getClubsDataArray()) {
                if (f.getClubName().equalsIgnoreCase(teamOneName)) {
                    teamOne = f;
                }
                if (f.getClubName().equalsIgnoreCase(teamTwoName)) {
                    teamTwo = f;
                }
            }
        }

        teamOne.setNumOfMatchesPlayed(teamOne.getNumOfMatchesPlayed() + 1);
        teamOne.setNumOfGoalsScored(teamOne.getNumOfGoalsScored() + this.teamOneScore);
        teamOne.setNumOfGoalsReceived(teamOne.getNumOfGoalsReceived() + this.teamTwoScore);

        teamTwo.setNumOfMatchesPlayed(teamTwo.getNumOfMatchesPlayed() + 1);
        teamTwo.setNumOfGoalsScored(teamTwo.getNumOfGoalsScored() + this.teamTwoScore);
        teamTwo.setNumOfGoalsReceived(teamTwo.getNumOfGoalsReceived() + this.teamOneScore);

        if (this.teamOneScore > this.teamTwoScore) {
            teamOne.setSeasonWins(teamOne.getSeasonWins() + 1);
            teamTwo.setSeasonDefeats(teamTwo.getSeasonDefeats() + 1);
            teamOne.setNumOfPointsGained(teamOne.getNumOfPointsGained() + 3);
            this.matchStats = teamOneName + " WON";

        } else if (this.teamOneScore < this.teamTwoScore) {
            teamTwo.setSeasonWins(teamTwo.getSeasonWins() + 1);
            teamOne.setSeasonDefeats(teamOne.getSeasonDefeats() + 1);
            teamTwo.setNumOfPointsGained(teamTwo.getNumOfPointsGained() + 3);
            this.matchStats = teamTwoName + " WON";

        } else {
            teamOne.setSeasonDraws(teamOne.getSeasonDraws() + 1);
            teamTwo.setSeasonDraws(teamTwo.getSeasonDraws() + 1);
            teamOne.setNumOfPointsGained(teamOne.getNumOfPointsGained() + 1);
            teamTwo.setNumOfPointsGained(teamTwo.getNumOfPointsGained() + 1);
            this.matchStats = "Match Drawn";
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTeamOneName() {
        return teamOneName;
    }

    public void setTeamOneName(String teamOneName) {
        this.teamOneName = teamOneName;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamTwoName(String teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public String getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(String matchStats) {
        this.matchStats = matchStats;
    }

    @Override
    public String toString() {
        return "Match {" +
                "date=" + date +
                ", teamOneName='" + teamOneName + '\'' +
                ", teamOneScore=" + teamOneScore +
                ", teamTwoName='" + teamTwoName + '\'' +
                ", teamTwoScore=" + teamTwoScore +
                ", matchStats='" + matchStats + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return teamOneScore == match.teamOneScore && teamTwoScore == match.teamTwoScore && Objects.equals(date, match.date) && Objects.equals(teamOneName, match.teamOneName) && Objects.equals(teamTwoName, match.teamTwoName) && Objects.equals(matchStats, match.matchStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, teamOneName, teamOneScore, teamTwoName, teamTwoScore, matchStats);
    }
}
