package services;

import models.FootballClub;
import models.Match;

import java.time.LocalDate;
import java.util.List;

public interface LeagueManager {
    void addClub(FootballClub footballClub);
    void addPlayedMatch(String teamOne, String teamTwo, int teamOneGoalsScored, int teamTwoGoalsScored, LocalDate date);
    void deleteClub(String clubName);
    void displayStatisticsForSpecificClub(String clubName);
    List displayLeagueTable();
    void saveData(String fileName);
    void loadData(String fileName);
    Match addRandomMatch();
}
