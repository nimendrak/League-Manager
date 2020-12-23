package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import services.PremierLeagueManager;
import models.FootballClub;
import models.Match;

import utils.ApplicationUtil;
import java.util.List;

public class LeagueController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger("controller");

    public Result addRandomMatch() {
        Match match = PremierLeagueManager.getInstance().addRandomMatch();
        JsonNode jsonObject = Json.toJson(match);
        return created(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result showLeaderboard() {
        List<FootballClub> result = PremierLeagueManager.getInstance().displayLeagueTable();
        logger.debug("In LeagueController.displayLeagueTable(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result getSortedLeaderboardData(String sort, String order) {
        List<FootballClub> result = PremierLeagueManager.getInstance().getSortedTableData(sort, order);
        logger.debug("In LeagueController.displayLeagueTable(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    public Result showPlayedMatches() {
        List<Match> result = PremierLeagueManager.getInstance().getPlayedMatches();
        logger.debug("In LeagueController.displayLeagueTable(), result is: {}", result.toString());
        JsonNode jsonObject = Json.toJson(result);
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

}
