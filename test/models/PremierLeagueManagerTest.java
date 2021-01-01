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

    //  test dataSources
    final String leagueMatches = "DataSource/test/TestDataMatches.txt";
    final String leagueClubs = "DataSource/test/TestDataTeams.txt";

    //  new club add to the league
    @Test
    void addClubVariationOne() {
        assertTrue(premierLeagueManagerTest.addClub(fTest));
        resetData();
    }

    //  check whether club is already added or not
    @Test
    void addClubVariationTwo() {
        premierLeagueManagerTest.addClub(fTest);
        assertTrue(premierLeagueManagerTest.addClub(fTest));
        resetData();
    }

    //  if club count is exceeds limit of 3, no club will added to the league
    @Test
    void addClubVariationThree() {
        premierLeagueManagerTest.addClub(fTest1);
        premierLeagueManagerTest.addClub(fTest2);
        premierLeagueManagerTest.addClub(fTest3);
        assertTrue(premierLeagueManagerTest.addClub(fTest));
        resetData();
    }

    //  in order to test the add match method, already added two clubs will be used
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

    //  test already added club got deleted or not
    @Test
    void deleteClubVariationOne() {
        premierLeagueManagerTest.addClub(fTest);
        assertTrue(premierLeagueManagerTest.deleteClub("Real Madrid"));
        resetData();
    }

    //  if team list is empty deleteClub returns false
    @Test
    void deleteClubVariationTwo() {
        assertFalse(premierLeagueManagerTest.deleteClub("Real Madrid"));
    }

    //  if the passed clubName is already in the league, test case will return its toString()
    @Test
    void displaySingleClubVariationOne() {
        premierLeagueManagerTest.addClub(fTest);
        assertEquals(fTest, premierLeagueManagerTest.displaySingleClub("Real Madrid"));
        resetData();
    }

    //  if the passed clubName is not in the league, null value will return
    @Test
    void displaySingleClubVariationTwo() {
        assertNull(premierLeagueManagerTest.displaySingleClub("Real Madrid"));
    }

    //  if teamList is not empty, club array will return
    @Test
    void displayLeagueTableVariationOne() {
        teamList.add(fTest);
        premierLeagueManagerTest.addClub(fTest);
        assertEquals(teamList, premierLeagueManagerTest.displayLeagueTable());
        resetData();
    }

    //  if teamList is empty, null value will return
    @Test
    void displayLeagueTableVariationTwo() {
        assertNull(premierLeagueManagerTest.displayLeagueTable());
    }

    //  if passed fileName doesn't found, false will return
    @Test
    void saveTeamsDataVariationOne() {
        premierLeagueManagerTest.addClub(fTest);

        assertFalse(premierLeagueManagerTest.saveData("/TestDataTeams.txt"));
        resetData();
    }

    //  if saved method successfully executed, true will return
    @Test
    void saveTeamsDataVariationTwo() {
        premierLeagueManagerTest.addClub(fTest);

        assertTrue(premierLeagueManagerTest.saveData(leagueClubs));
        resetData();
    }

    //  if saved method successfully executed, true will return
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

    //  if load method successfully executed, true will return
    @Test
    void loadTeamsDataVariationOne() {
        assertTrue(premierLeagueManagerTest.loadData(leagueClubs));
        System.out.println(PremierLeagueManager.isSuccess());
        resetData();
    }

    //  if passed fileName doesn't found, false will return
    @Test
    void loadTeamDataVariationTwo() {
        assertFalse(premierLeagueManagerTest.loadData("/TestDataMatches.txt"));
        System.out.println(PremierLeagueManager.isSuccess());
        resetData();
    }

    //  if load method successfully executed, true will return
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
