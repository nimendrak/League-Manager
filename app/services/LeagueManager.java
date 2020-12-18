package services;

import models.FootballClub;

import java.time.LocalDate;

public interface LeagueManager {
    void addClub(FootballClub footballClub);
    void addPlayedMatch(String teamOne, String teamTwo, int teamOneGoalsScored, int teamTwoGoalsScored, LocalDate date);
    void deleteClub(String clubName);
    void displayStatisticsForSpecificClub(String clubName);
    void displayLeagueTable();
    void saveData(String fileName);
    void loadData(String fileName);
}
