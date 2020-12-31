package models;

import java.time.LocalDate;
import java.util.List;

public interface LeagueManager {
    boolean addClub(FootballClub footballClub);
    boolean addPlayedMatch(String teamOne, String teamTwo, int teamOneGoalsScored, int teamTwoGoalsScored, LocalDate date);
    boolean deleteClub(String clubName);
    FootballClub displaySingleClub(String clubName);
    List<FootballClub> displayLeagueTable();
    boolean saveData(String fileName);
    boolean loadData(String fileName);
}
