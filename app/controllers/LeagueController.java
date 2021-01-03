package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.FootballClub;
import models.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import services.LeagueManagerServices;
import utils.ApplicationUtil;

import java.util.*;

public class LeagueController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    /*
     * if any method that invokes teamList and matchList;
     * overwrite the dataSource and clear out the current array lists and
     * load new data again to the array lists
     */

    public Result showLeaderboard() {
        LeagueManagerServices.getInstance().loadData();
        List<FootballClub> result = LeagueManagerServices.getInstance().getLeaderboard();
        logger.debug("In LeagueController.showLeaderboard(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getSortedLeaderboardData(String sort, String order) {
        LeagueManagerServices.getInstance().loadData();
        List<FootballClub> result = LeagueManagerServices.getInstance().getSortedLeaderboard(sort, order);
        logger.debug("In LeagueController.getSortedLeaderboardData(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result showPlayedMatches() {
        LeagueManagerServices.getInstance().loadData();
        List<Match> result = LeagueManagerServices.getInstance().getPlayedMatches();
        logger.debug("In LeagueController.showPlayedMatches(), result is: {}", result.toString());

        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getSortedMatchTableData(String order) {
        LeagueManagerServices.getInstance().loadData();
        List<Match> result = LeagueManagerServices.getInstance().getSortedMatchTable(order);
        logger.debug("In LeagueController.getSortedMatchTable(), result is: {}", result.toString());

        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getSearchedMatch(String date) {
        LeagueManagerServices.getInstance().loadData();
        List<Match> result = LeagueManagerServices.getInstance().getSearchedMatch(date);
        logger.debug("In LeagueController.getSearchedMatch(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result addRandomMatch() {
        LeagueManagerServices.getInstance().loadData();
        Match result = LeagueManagerServices.getInstance().addRandomMatch();
        logger.debug("In LeagueController.addRandomMatch(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getRandomMatch() {
        LeagueManagerServices.getInstance().loadData();
        Match result = LeagueManagerServices.getInstance().getRandomMatch();
        logger.debug("In LeagueController.getRandomMatch(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }
}
