package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.FootballClub;
import models.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import services.DataSourceServices;
import utils.ApplicationUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LeagueController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");
    DataSourceServices data = DataSourceServices.getInstance();

    List<FootballClub> teamList = data.getClubsDataArray();
    List<Match> matchList = data.getMatchDataArray();

    final String leagueMatches = "DataSource/PremierLeagueMatches.txt";
    final String leagueClubs = "DataSource/PremierLeagueTeams.txt";

    public Result addRandomMatch() {
        data.loadData(leagueClubs, leagueMatches);

        FootballClub randomTeamOne, randomTeamTwo;
        int randomScoreOne, randomScoreTwo;
        LocalDate randomLocalDate;

//      generating a random match
        if (!teamList.isEmpty()) {
            Random rand = new Random();
            do {
                int indexOne = new Random().nextInt(teamList.size());
                int indexTwo = new Random().nextInt(teamList.size());

                randomTeamOne = (teamList.get(indexOne));
                randomTeamTwo = (teamList.get(indexTwo));

                randomScoreOne = rand.nextInt(20);
                randomScoreTwo = rand.nextInt(20);

                int minDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
                int maxDay = (int) LocalDate.of(2020, 12, 31).toEpochDay();
                long randomDay = minDay + rand.nextInt(maxDay - minDay);

                randomLocalDate = LocalDate.ofEpochDay(randomDay);

            } while (randomTeamOne.getClubName().equals(randomTeamTwo.getClubName()));

            Match match = new Match(randomLocalDate, randomTeamOne.getClubName(), randomTeamTwo.getClubName(),
                    randomScoreOne, randomScoreTwo, "");
            match.updateStats();
            matchList.add(match);

            JsonNode jsonObject = Json.toJson(match);

            data.saveData(leagueClubs, leagueMatches);

            return created(ApplicationUtil.createResponse(jsonObject, true));
        }
        return internalServerError();
    }

    public Result showLeaderboard() {
        data.loadData(leagueClubs, leagueMatches);
        System.out.println("\nafter load\nclubs count - " + teamList.size());
        System.out.println("matches count - " + matchList.size());

        if (!teamList.isEmpty()) {
            Collections.sort(teamList, Collections.reverseOrder());

            logger.debug("In LeagueController.showLeaderboard(), result is: {}", teamList.toString());
            JsonNode jsonObject = Json.toJson(teamList);

            data.saveData(leagueClubs, leagueMatches);

            System.out.println("\nsaved data\nclubs count - " + teamList.size());
            System.out.println("matches count - " + matchList.size());

            return ok(ApplicationUtil.createResponse(jsonObject, true));
        }
        return internalServerError();
    }

    public Result getSortedLeaderboardData(String sort, String order) {
        data.loadData(leagueClubs, leagueMatches);

        //      get a copy of current list and sorting the copy
        List<FootballClub> sortedList = teamList;

//      initialize three different comparators according to the sorting requirement
//      comparator for scored goals
        Comparator<FootballClub> compareByGoals = Comparator
                .comparing(FootballClub::getNumOfGoalsScored)
                .thenComparing(FootballClub::getNumOfGoalsScored);

//      comparator for seasonal wins
        Comparator<FootballClub> compareByWins = Comparator
                .comparing(FootballClub::getSeasonWins)
                .thenComparing(FootballClub::getSeasonWins);

//      comparator for gained points
        Comparator<FootballClub> compareByPoints = Comparator
                .comparing(FootballClub::getNumOfPointsGained)
                .thenComparing(FootballClub::getNumOfPointsGained);

        if (sort.equalsIgnoreCase("goals")) {
            if (order.equalsIgnoreCase("ascending")) {
                sortedList.stream().sorted(compareByGoals).collect(Collectors.toList());
            } else {
                sortedList.stream().sorted(compareByGoals.reversed()).collect(Collectors.toList());
            }
        } else if ((sort.equalsIgnoreCase("wins"))) {
            if (order.equalsIgnoreCase("ascending")) {
                sortedList.stream().sorted(compareByWins).collect(Collectors.toList());
            } else {
                sortedList.stream().sorted(compareByWins.reversed()).collect(Collectors.toList());
            }
        } else {
            if (order.equalsIgnoreCase("ascending")) {
                sortedList.stream().sorted(compareByPoints).collect(Collectors.toList());
            } else {
                sortedList.stream().sorted(compareByPoints.reversed()).collect(Collectors.toList());
            }
        }

        data.saveData(leagueClubs, leagueMatches);

        logger.debug("In LeagueController.getSortedLeaderboardData(), result is: {}", sortedList.toString());
        JsonNode jsonObject = Json.toJson(sortedList);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result showPlayedMatches() {
        data.loadData(leagueClubs, leagueMatches);

        Comparator<Match> compareByDate = Comparator
                .comparing(Match::getDate)
                .thenComparing(Match::getDate);

        matchList.stream().sorted(compareByDate.reversed()).collect(Collectors.toList());

        logger.debug("In LeagueController.showPlayedMatches(), result is: {}", matchList.toString());
        JsonNode jsonObject = Json.toJson(matchList);

        data.saveData(leagueClubs, leagueMatches);

        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getSearchedMatch(String date) {
        data.loadData(leagueClubs, leagueMatches);
        List<Match> searchResults = new ArrayList<>();

        try {
            if (!matchList.isEmpty()) {
                for (Match m : matchList) {
                    if (m.getDate().equals(LocalDate.parse(date))) {
                        searchResults.add(m);
                    }
                }
            }
        } catch (Exception ignored) {
        }

        logger.debug("In LeagueController.getSearchedMatch(), result is: {}", searchResults.toString());
        JsonNode jsonObject = Json.toJson(searchResults);

        data.saveData(leagueClubs, leagueMatches);

        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getRandomMatch() {
        data.loadData(leagueClubs, leagueMatches);

        Match result = matchList.get(matchList.size() - 1);
        logger.debug("In LeagueController.getSearchedMatch(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);

        data.saveData(leagueClubs, leagueMatches);

        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }
}
