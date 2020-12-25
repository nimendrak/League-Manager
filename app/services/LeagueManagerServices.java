package services;

import models.FootballClub;
import models.Match;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LeagueManagerServices {
    PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    List<Match> matchList = premierLeagueManager.getMatchList();
    List<FootballClub> teamList = premierLeagueManager.getTeamList();

    final String leagueMatches = "app/utils/DataSource/PremierLeagueMatches.txt";
    final String leagueTeams = "app/utils/DataSource/PremierLeagueTeams.txt";

    private static LeagueManagerServices instance = null;

    private LeagueManagerServices() {
    }

    public static LeagueManagerServices getInstance() {
        if (instance == null) {
            synchronized (PremierLeagueManager.class) {
                if (instance == null) {
                    instance = new LeagueManagerServices();
                }
            }
        }
        return instance;
    }

    /*
    * ManagerServices has some additional services
    * which did not implemented in default
    * PremierLeagueManager class
    * which has been used for both Angular Client and CLI application
    * */

    public Match addRandomMatch() {

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

            premierLeagueManager.saveData(leagueMatches);

            return match;
        }
        return null;
    }

    public Match getRandomMatch() {
        return matchList.get(matchList.size() - 1);
    }

    public List<FootballClub> getSortedTableData(String type, String order) {
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

        if (type.equalsIgnoreCase("goals")) {
            if (order.equalsIgnoreCase("ascending")) {
                return sortedList.stream().sorted(compareByGoals).collect(Collectors.toList());
            } else {
                return sortedList.stream().sorted(compareByGoals.reversed()).collect(Collectors.toList());
            }
        } else if ((type.equalsIgnoreCase("wins"))) {
            if (order.equalsIgnoreCase("ascending")) {
                return sortedList.stream().sorted(compareByWins).collect(Collectors.toList());
            } else {
                return sortedList.stream().sorted(compareByWins.reversed()).collect(Collectors.toList());
            }
        } else {
            if (order.equalsIgnoreCase("ascending")) {
                return sortedList.stream().sorted(compareByPoints).collect(Collectors.toList());
            } else {
                return sortedList.stream().sorted(compareByPoints.reversed()).collect(Collectors.toList());
            }
        }
    }

    public List<Match> getSearchedMatch(String date) {
        List<Match> searchResults = new ArrayList<>();

        try {
            if (!matchList.isEmpty()) {
                for (Match m : matchList) {
                    if (m.getDate().equals(LocalDate.parse(date))) {
                        searchResults.add(m);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return searchResults;
    }

    public List<Match> getPlayedMatches() {
        Comparator<Match> compareByDate = Comparator
                .comparing(Match::getDate)
                .thenComparing(Match::getDate);

        return matchList.stream().sorted(compareByDate.reversed()).collect(Collectors.toList());
    }

    public List<FootballClub> saveTeamsData() {
        premierLeagueManager.saveData(leagueTeams);
        return teamList;
    }

    public List<Match> saveMatchesData() {
        premierLeagueManager.saveData(leagueMatches);
        return matchList;
    }

    public List<FootballClub> loadTeamsData() {
        premierLeagueManager.loadData(leagueTeams);
        return teamList;
    }

    public List<Match> loadMatchesData() {
        premierLeagueManager.loadData(leagueMatches);
        return matchList;
    }

}
