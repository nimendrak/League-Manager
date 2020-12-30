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

    public Result addRandomMatch() {
        LeagueManagerServices.getInstance().loadData();
        Match result = LeagueManagerServices.getInstance().addRandomMatch();
        logger.debug("In LeagueController.addRandomMatch(), result is: {}", result.toString());

        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result showLeaderboard() {
        LeagueManagerServices.getInstance().loadData();
        List<FootballClub> result = LeagueManagerServices.getInstance().showLeaderboard();
        logger.debug("In LeagueController.showLeaderboard(), result is: {}", result.toString());

        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getSortedLeaderboardData(String sort, String order) {
        LeagueManagerServices.getInstance().loadData();
        List<FootballClub> result = LeagueManagerServices.getInstance().getSortedLeaderboardData(sort, order);
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

    public Result getSearchedMatch(String date) {
        LeagueManagerServices.getInstance().loadData();
        List<Match> result = LeagueManagerServices.getInstance().getSearchedMatch(date);
        logger.debug("In LeagueController.addRandomMatch(), result is: {}", result.toString());

        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getRandomMatch() {
        LeagueManagerServices.getInstance().loadData();
        Match result = LeagueManagerServices.getInstance().getRandomMatch();
        logger.debug("In LeagueController.addRandomMatch(), result is: {}", result.toString());

        JsonNode jsonObject = Json.toJson(result);
        LeagueManagerServices.getInstance().saveData();
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }
}
