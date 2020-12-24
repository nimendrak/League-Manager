package services;

import models.FootballClub;

import java.time.LocalDate;
import java.util.List;

public interface LeagueManager {
    void addClub(FootballClub footballClub);
    void addPlayedMatch(String teamOne, String teamTwo, int teamOneGoalsScored, int teamTwoGoalsScored, LocalDate date);
    void deleteClub(String clubName);
    FootballClub displaySingleClub(String clubName);
    List<FootballClub> displayLeagueTable();
    void saveData(String fileName);
    void loadData(String fileName);

}
