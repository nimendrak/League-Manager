package services;

import models.FootballClub;
import models.Match;
import models.MatchModel;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PremierLeagueManager implements LeagueManager {

    final static int MAX_TEAMS = 20;
    private List<FootballClub> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    private int availableSlots = MAX_TEAMS;

    public List<FootballClub> getTeamList() {
        return teamList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    private static PremierLeagueManager instance = null;

    private PremierLeagueManager() {
    }

    public static PremierLeagueManager getInstance() {
        if (instance == null) {
            synchronized (PremierLeagueManager.class) {
                if (instance == null) {
                    instance = new PremierLeagueManager();
                }
            }
        }
        return instance;
    }

    //  delete
    FootballClub f1 = new FootballClub("ccc3", "Colombo", 0, 0, 0, 0, 0, 10, 0);
    FootballClub f2 = new FootballClub("aaa1", "Kelaniya", 0, 0, 0, 0, 0, 5, 0);
    FootballClub f3 = new FootballClub("bbb2", "Moratuwa", 0, 0, 0, 0, 0, 35, 0);

    Match m1 = new Match(f2, f1, 1, 1, LocalDate.of(2020, 12, 25));
    Match m2 = new Match(f1, f2, 2, 5, LocalDate.of(2020, 12, 24));
    Match m3 = new Match(f1, f3, 3, 0, LocalDate.of(2020, 12, 23));

    public void sampleData() {
        teamList.add(f1);
        teamList.add(f2);
        teamList.add(f3);

        m1.updateStats();
        m2.updateStats();
        m3.updateStats();

        matchList.add(m1);
        matchList.add(m2);
        matchList.add(m3);
    }

    @Override
    public void addClub(FootballClub footballClub) {
        if (teamList.contains(footballClub)) {
            System.out.println("\nTeam has been already added to the League!");
        } else if (availableSlots == 0) {
            System.out.println("\nNo available slots in the League!");
        } else {
            teamList.add(footballClub);
            availableSlots -= 1;
            System.out.println("\nTeam " + footballClub.getClubName() + " successfully added to the League!");
        }

        System.out.println("\nThere are " + "\033[1;93m" + availableSlots + " available slots " + "\033[0m" + "in the League");
    }

    @Override
    public void addPlayedMatch(String teamOneName, String teamTwoName, int teamOneGoalsScored, int teamTwoGoalsScored, LocalDate date) {
        FootballClub clubOne = null;
        FootballClub clubTwo = null;

        try {
            for (FootballClub f : teamList) {
                if (f.getClubName().equalsIgnoreCase(teamOneName)) {
                    clubOne = f;
                }
                if (f.getClubName().equalsIgnoreCase(teamTwoName)) {
                    clubTwo = f;
                }
            }
            Match match = new Match(clubOne, clubTwo, teamOneGoalsScored, teamTwoGoalsScored, date);
            match.updateStats();
            matchList.add(match);

        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Team not Found");
        }
    }

    @Override
    public void deleteClub(String clubName) {
        try {
            if (!teamList.isEmpty()) {
                for (FootballClub f : teamList) {
                    if (f.getClubName().equalsIgnoreCase(clubName)) {
                        teamList.remove(f);
                        System.out.println("\nTeam removed from the League!");
                    }
                }
            } else {
                System.out.println("No Team has added to the League yet");
            }
        } catch (ConcurrentModificationException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void displayStatisticsForSpecificClub(String clubName) {
        if (!teamList.isEmpty()) {
            for (FootballClub f : teamList) {
                if (f.getClubName().equalsIgnoreCase(clubName)) {
                    System.out.println(f.toString());
                }
            }
        } else {
            System.out.println("No Team has played a Match yet");
        }
    }

    @Override
    public List<FootballClub> displayLeagueTable() {
        // delete
        if (!oneTime) {
            sampleData();
            oneTime = true;
        }

//      Collections.sort(teamList, Collections.reverseOrder());
        return teamList;
    }

    @Override
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
                .comparing(FootballClub::getNumOfGoalsScored)
                .thenComparing(FootballClub::getNumOfGoalsScored);

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

    @Override
    public List<Match> getPlayedMatches() {
//        List<MatchModel> getPlayMatches = new ArrayList<>();
//
//        String teamOneStats, teamTwoStats;
//        FootballClub clubOne, clubTwo;
//        LocalDate date;
//
//        for (Match m: matchList) {
//            clubOne = m.getTeamOne();
//            clubTwo = m.getTeamTwo();
//            date = m.getDate();
//
//            if (m.getTeamOneScore() > m.getTeamTwoScore()) {
//                teamOneStats = "Win";
//                teamTwoStats = "Loss";
//            } else if (m.getTeamOneScore() < m.getTeamTwoScore()) {
//                teamOneStats = "Loss";
//                teamTwoStats = "Win";
//            } else {
//                teamOneStats = "Draw";
//                teamTwoStats = "Draw";
//            }
//            MatchModel modelOne = new MatchModel(String.valueOf(date), clubOne.getClubName(),teamOneStats,
//                    clubOne.getNumOfGoalsReceived(), clubOne.getNumOfGoalsScored(), clubOne.getNumOfPointsGained());
//            getPlayMatches.add(modelOne);
//
//            MatchModel modelTwo = new MatchModel("", clubTwo.getClubName(),teamTwoStats,
//                    clubTwo.getNumOfGoalsReceived(), clubTwo.getNumOfGoalsScored(), clubTwo.getNumOfPointsGained());
//            getPlayMatches.add(modelTwo);
//        }

        return matchList;
    }

    @Override
    public void saveData(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (!file.exists())
                file.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            if (fileName.contains("Teams.txt")) {
                for (FootballClub f : teamList) {
                    objectOutputStream.writeObject(f);
                }
            } else {
                for (Match m : matchList) {
                    objectOutputStream.writeObject(m);
                }
            }

            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Successfully Saved to the File!");

        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public void loadData(String fileName) {
        File file = new File(fileName);
        boolean empty = !file.exists() || file.length() == 0;

        if (!empty) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                FootballClub f;
                while (true) {
                    try {
                        if (fileName.contains("Teams.txt")) {
                            f = (FootballClub) objectInputStream.readObject();
                            teamList.add(f);
                        } else {
                            Match m = (Match) objectInputStream.readObject();
                            matchList.add(m);
                        }
                    } catch (EOFException | ClassNotFoundException ex) {
//                        ex.printStackTrace();
                        System.out.println("Successfully Loaded!");
                        break;
                    }
                }
                fileInputStream.close();
                objectInputStream.close();

            } catch (IOException e) {
//                e.printStackTrace();
            }
        } else {
            System.out.println("No Data Available!");
        }
    }

    //  delete
    static boolean oneTime = false;

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

            Match match = new Match(randomTeamOne, randomTeamTwo, randomScoreOne,
                    randomScoreTwo, randomLocalDate);
            matchList.add(match);

            return match;
        }
        return null;
    }

    public boolean isContain(String clubName) {
        for (FootballClub f : teamList) {
            if (f.getClubName().equalsIgnoreCase(clubName)) {
                return false;
            }
        }
        return true;
    }
}
