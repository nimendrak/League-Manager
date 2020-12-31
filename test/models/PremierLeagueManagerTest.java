package models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {

//  initialize an instance of PremierLeagueManager to test its methods
    PremierLeagueManager premierLeagueManagerTest = PremierLeagueManager.getInstance();

    //  sample data for testing
    FootballClub fTest = new FootballClub("Real Madrid", "Spain");
    FootballClub fTest1 = new FootballClub("aaa", "aaa");
    FootballClub fTest2 = new FootballClub("bbb", "bbb");
    FootballClub fTest3 = new FootballClub("ccc", "ccc");

    // test list to hold the return values
    List<FootballClub> teamList = new ArrayList<>();


    @Test
    void addClubVariationOne() {
//      new club add to the league
        assertTrue(premierLeagueManagerTest.addClub(fTest));
    }

    @Test
    void addClubVariationTwo() {
        premierLeagueManagerTest.addClub(fTest);
//      check whether club is already added or not
        assertTrue(premierLeagueManagerTest.addClub(fTest));
    }

    @Test
    void addClubVariationThree() {
        premierLeagueManagerTest.addClub(fTest1);
        premierLeagueManagerTest.addClub(fTest2);
        premierLeagueManagerTest.addClub(fTest3);

//      if club count is exceeds limit of 3, no club will added to the league
        assertTrue(premierLeagueManagerTest.addClub(fTest));
    }

    @Test
    void addPlayedMatch() {
        java.time.LocalDate date = java.time.LocalDate.of(2020, 12, 31);
        String teamOneName = "aaa", teamTwoName = "bbb";
        int teamOneScore = 7, teamTwoScore = 8;

        assertTrue(premierLeagueManagerTest.addPlayedMatch(teamOneName, teamTwoName, teamOneScore, teamTwoScore, date));
    }

    @Test
    void deleteClub() {
        assertTrue(premierLeagueManagerTest.deleteClub("Real Madrid"));
    }

    @Test
    void displaySingleClub() {
        assertEquals(fTest, premierLeagueManagerTest.displaySingleClub("Real Madrid"));
    }

    @Test
    void displayLeagueTable() {
        teamList.add(fTest);

        assertEquals(teamList, premierLeagueManagerTest.displayLeagueTable());

        premierLeagueManagerTest.getTeamList().clear();
    }

    @Test
    void saveData() {
    }

    @Test
    void loadData() {
    }
}
