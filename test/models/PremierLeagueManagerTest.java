package models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PremierLeagueManagerTest {

    //  initialize an instance of PremierLeagueManager to test its methods
    PremierLeagueManager premierLeagueManagerTest = PremierLeagueManager.getInstance();

    //  sample data for testing
    FootballClub fTest = new FootballClub("Real Madrid", "Spain");
    FootballClub fTest1 = new FootballClub("aaa", "aaa");
    FootballClub fTest2 = new FootballClub("bbb", "bbb");
    FootballClub fTest3 = new FootballClub("ccc", "ccc");

    //  test list to hold the compare return values
    List<FootballClub> teamList = new ArrayList<>();

    final String leagueMatches = "DataSource/test/TestDataMatches.txt";
    final String leagueClubs = "DataSource/test/TestDataTeams.txt";

    @Test
    void addClubVariationOne() {
//      new club add to the league
        assertTrue(premierLeagueManagerTest.addClub(fTest));
        resetData();
    }

    @Test
    void addClubVariationTwo() {
        premierLeagueManagerTest.addClub(fTest);
//      check whether club is already added or not
        assertTrue(premierLeagueManagerTest.addClub(fTest));
        resetData();
    }

    @Test
    void addClubVariationThree() {
        premierLeagueManagerTest.addClub(fTest1);
        premierLeagueManagerTest.addClub(fTest2);
        premierLeagueManagerTest.addClub(fTest3);
//      if club count is exceeds limit of 3, no club will added to the league
        assertTrue(premierLeagueManagerTest.addClub(fTest));
        resetData();
    }

    @Test
    void addPlayedMatch() {
        java.time.LocalDate date = java.time.LocalDate.of(2020, 12, 31);
        String teamOneName = "aaa", teamTwoName = "bbb";
        int teamOneScore = 0, teamTwoScore = 0;

        premierLeagueManagerTest.addClub(fTest1);
        premierLeagueManagerTest.addClub(fTest2);

        assertTrue(premierLeagueManagerTest.addPlayedMatch(teamOneName, teamTwoName, teamOneScore, teamTwoScore, date));
        resetData();
    }

    @Test
    void deleteClubVariationOne() {
        premierLeagueManagerTest.addClub(fTest);
        assertTrue(premierLeagueManagerTest.deleteClub("Real Madrid"));
        resetData();
    }

    @Test
    void deleteClubVariationTwo() {
//      if team list is empty deleteClub returns false
        assertFalse(premierLeagueManagerTest.deleteClub("Real Madrid"));
    }

    @Test
    void displaySingleClubVariationOne() {
        premierLeagueManagerTest.addClub(fTest);
        assertEquals(fTest, premierLeagueManagerTest.displaySingleClub("Real Madrid"));
        resetData();
    }

    @Test
    void displaySingleClubVariationTwo() {
        assertNull(premierLeagueManagerTest.displaySingleClub("Real Madrid"));
    }

    @Test
    void displayLeagueTableVariationOne() {
        teamList.add(fTest);
        premierLeagueManagerTest.addClub(fTest);

        assertEquals(teamList, premierLeagueManagerTest.displayLeagueTable());
        resetData();
    }

    @Test
    void displayLeagueTableVariationTwo() {
        assertNull(premierLeagueManagerTest.displayLeagueTable());
    }

    @Test
    void saveTeamsDataVariationOne() {
        premierLeagueManagerTest.addClub(fTest);

        assertFalse(premierLeagueManagerTest.saveData("/TestDataTeams.txt"));
        resetData();
    }

    @Test
    void saveTeamsDataVariationTwo() {
        premierLeagueManagerTest.addClub(fTest);

        assertTrue(premierLeagueManagerTest.saveData(leagueClubs));
        resetData();
    }

    @Test
    void saveMatchesData() {
        java.time.LocalDate date = java.time.LocalDate.of(2020, 12, 31);
        String teamOneName = "aaa", teamTwoName = "bbb";
        int teamOneScore = 0, teamTwoScore = 0;

        premierLeagueManagerTest.addClub(fTest1);
        premierLeagueManagerTest.addClub(fTest2);

        premierLeagueManagerTest.addPlayedMatch(teamOneName, teamTwoName, teamOneScore, teamTwoScore, date);

        assertTrue(premierLeagueManagerTest.saveData(leagueMatches));
        resetData();
    }

    @Test
    void loadTeamsDataVariationOne() {
        assertTrue(premierLeagueManagerTest.loadData(leagueClubs));
        System.out.println(PremierLeagueManager.isSuccess());
        resetData();
    }

    @Test
    void loadTeamDataVariationTwo() {
        assertFalse(premierLeagueManagerTest.loadData("/TestDataMatches.txt"));
        System.out.println(PremierLeagueManager.isSuccess());
        resetData();
    }

    @Test
    void loadMatchesData() {
        assertTrue(premierLeagueManagerTest.loadData(leagueMatches));
        resetData();
    }

    //  clear data of the lists to test next variation
    void resetData() {
        premierLeagueManagerTest.getTeamList().clear();
        premierLeagueManagerTest.getMatchList().clear();
        premierLeagueManagerTest.setAvailableSlots(3);

        PremierLeagueManager.setSuccess(false);
    }
}
