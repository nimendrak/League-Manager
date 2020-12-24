package services;

import models.FootballClub;
import models.Match;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    final static int MAX_TEAMS = 20;
    private List<FootballClub> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    private int availableSlots = MAX_TEAMS;

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
    static boolean oneTime = false;

    //  delete
    FootballClub f1 = new FootballClub("ccc3", "Colombo", 0, 0, 0, 0, 0, 10, 0);
    FootballClub f2 = new FootballClub("aaa1", "Kelaniya", 0, 0, 0, 0, 0, 5, 0);
    FootballClub f3 = new FootballClub("bbb2", "Moratuwa", 0, 0, 0, 0, 0, 35, 0);

    //  delete
    Match m1 = new Match(LocalDate.of(2020, 12, 25), "aaa1", "bbb2", 1, 2, "");

    //  delete
    public void sampleData() {
//        teamList.add(f1);
//        teamList.add(f2);
//        teamList.add(f3);

//        m1.updateStats();
//        matchList.add(m1);
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
        try {
            Match match = new Match(date, teamOneName, teamTwoName, teamOneGoalsScored, teamTwoGoalsScored, "");
            match.updateStats();
            matchList.add(match);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClub(String clubName) {
        if (!teamList.isEmpty()) {
            teamList.removeIf(f -> f.getClubName().equalsIgnoreCase(clubName));
        }
    }

    @Override
    public FootballClub displaySingleClub(String clubName) {
        if (!teamList.isEmpty()) {
            for (FootballClub f : teamList) {
                if (f.getClubName().equalsIgnoreCase(clubName)) {
                    return f;
                }
            }
        }
        return null;
    }

    @Override
    public List<FootballClub> displayLeagueTable() {
        // delete
        if (!oneTime) {
            sampleData();
            System.out.println("Sample Data");
            oneTime = true;
        }

        /*
         * compareTo method use as the default sorting
         * if search function triggered, program will use custom comparators accordingly
         * */
        Collections.sort(teamList, Collections.reverseOrder());
        return teamList;
    }

    @Override
    public void saveData(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();

            boolean fileDeleted = false;
//          clear all the data by deleting the existing file and recreating it
            if (file.exists() && file.isFile()) {
                fileDeleted = file.delete();
            }
            boolean fileCreated = file.createNewFile();

            if (fileCreated & fileDeleted) {
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
            }

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

    public boolean isContain(String clubName) {
        for (FootballClub f : teamList) {
            if (f.getClubName().equalsIgnoreCase(clubName)) {
                return false;
            }
        }
        return true;
    }

    public List<FootballClub> getTeamList() {
        return teamList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }
}
