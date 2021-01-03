package services;

import models.FootballClub;
import models.Match;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LeagueManagerServices {

    List<FootballClub> clubsDataArray = new ArrayList<>();
    List<Match> matchDataArray = new ArrayList<>();

    /*
     * since both CLI application and GUI application
     * use the same dataSource and, it's the common component
     * for the both applications.
     * everytime CLI application get updated, all the modified data will
     * write to the text files and GUI application does the same thing.
     * */

    final static String leagueMatches = "DataSource/PremierLeagueMatches.txt";
    final static String leagueClubs = "DataSource/PremierLeagueTeams.txt";

    private static LeagueManagerServices instance = null;

    private LeagueManagerServices() {
    }

    public static LeagueManagerServices getInstance() {
        if (instance == null) {
            synchronized (LeagueManagerServices.class) {
                if (instance == null) {
                    instance = new LeagueManagerServices();
                }
            }
        }
        return instance;
    }

    public List<FootballClub> getLeaderboard() {
        if (!clubsDataArray.isEmpty()) {
            Collections.sort(clubsDataArray, Collections.reverseOrder());
        }
        return clubsDataArray;
    }

    public List<FootballClub> getSortedLeaderboard(String type, String order) {
//      get a copy of current list and sorting the copy
        List<FootballClub> sortedList = clubsDataArray;

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

    public Match addRandomMatch() {
        FootballClub randomTeamOne, randomTeamTwo;
        int randomScoreOne, randomScoreTwo;
        LocalDate randomLocalDate;

//      generating a random match
        if (clubsDataArray.size() >= 2) {
            Random rand = new Random();
            do {
                int indexOne = new Random().nextInt(clubsDataArray.size());
                int indexTwo = new Random().nextInt(clubsDataArray.size());

                randomTeamOne = (clubsDataArray.get(indexOne));
                randomTeamTwo = (clubsDataArray.get(indexTwo));

                randomScoreOne = rand.nextInt(11);
                randomScoreTwo = rand.nextInt(11);

                int minDay = (int) LocalDate.of(2020, 6, 1).toEpochDay();
                int maxDay = (int) LocalDate.of(2020, 7, 31).toEpochDay();
                long randomDay = minDay + rand.nextInt(maxDay - minDay);

                randomLocalDate = LocalDate.ofEpochDay(randomDay);

            } while (randomTeamOne.getClubName().equals(randomTeamTwo.getClubName()));

            Match match = new Match(randomLocalDate, randomTeamOne.getClubName(), randomTeamTwo.getClubName(),
                    randomScoreOne, randomScoreTwo, "");

            match.updateStats("guiApp");
            matchDataArray.add(match);
            return match;
        }
        return null;
    }

    public Match getRandomMatch() {
        Match match = null;
        if (!clubsDataArray.isEmpty()) {
            match = matchDataArray.get(matchDataArray.size() - 1);

        }
        return match;
    }

    public List<Match> getSearchedMatch(String date) {
        List<Match> searchResults = new ArrayList<>();

        try {
            if (!matchDataArray.isEmpty()) {
                for (Match m : matchDataArray) {
                    if (m.getDate().equals(LocalDate.parse(date))) {
                        searchResults.add(m);
                    }
                }
            }
        } catch (Exception e) {
            return searchResults;
        }
        return searchResults;
    }

    public List<Match> getPlayedMatches() {
        return matchDataArray;
    }

    public List<Match> getSortedMatchTable(String order) {
//      get a copy of current list and sorting the copy
        List<Match> sortedList = matchDataArray;

//      initialize three different comparators according to the sorting requirement
//      comparator for dates
        Comparator<Match> compareByDate = Comparator
                .comparing(Match::getDate)
                .thenComparing(Match::getDate);

        if (order.equalsIgnoreCase("ascending")) {
            return sortedList.stream().sorted(compareByDate).collect(Collectors.toList());
        } else {
            return sortedList.stream().sorted(compareByDate.reversed()).collect(Collectors.toList());
        }
    }

    public void loadData() {
        File clubFile = new File(leagueClubs);
        File matchFile = new File(leagueMatches);

        boolean isClubFileEmpty = !clubFile.exists() || clubFile.length() == 0;
        boolean isMatchFileEmpty = !clubFile.exists() || clubFile.length() == 0;

        if (!isClubFileEmpty || !isMatchFileEmpty) {
            try {
                FileInputStream clubsInputStream = new FileInputStream(clubFile);
                ObjectInputStream clubsObjectInputStream = new ObjectInputStream(clubsInputStream);

                FileInputStream matchInputStream = new FileInputStream(matchFile);
                ObjectInputStream matchObjectInputStream = new ObjectInputStream(matchInputStream);

                while (true) {
                    try {
                        FootballClub f = (FootballClub) clubsObjectInputStream.readObject();
                        clubsDataArray.add(f);
                    } catch (EOFException | ClassNotFoundException ex) {
                        break;
                    }
                }
                clubsInputStream.close();
                clubsObjectInputStream.close();

                while (true) {
                    try {
                        Match m = (Match) matchObjectInputStream.readObject();
                        matchDataArray.add(m);
                    } catch (EOFException | ClassNotFoundException ex) {
                        break;
                    }
                }
                matchInputStream.close();
                matchObjectInputStream.close();

            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    public void saveData() {
        try {
            File clubFile = new File(leagueClubs);
            File matchFile = new File(leagueMatches);

//          overwriting with the existing data
            FileOutputStream clubOutputStream = new FileOutputStream(clubFile, false);
            ObjectOutputStream clubObjectOutputStream = new ObjectOutputStream(clubOutputStream);

//          overwriting with the existing data
            FileOutputStream matchOutputStream = new FileOutputStream(matchFile, false);
            ObjectOutputStream matchObjectOutputStream = new ObjectOutputStream(matchOutputStream);

            for (FootballClub f : clubsDataArray) {
                clubObjectOutputStream.writeObject(f);
            }

            clubObjectOutputStream.flush();
            clubObjectOutputStream.close();
            clubOutputStream.close();
            clubsDataArray.clear();

            for (Match m : matchDataArray) {
                matchObjectOutputStream.writeObject(m);
            }

            matchObjectOutputStream.flush();
            matchObjectOutputStream.close();
            matchOutputStream.close();
            matchDataArray.clear();

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public List<FootballClub> getClubsDataArray() {
        return clubsDataArray;
    }

}
