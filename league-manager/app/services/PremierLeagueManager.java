package services;

import models.FootballClub;
import models.Match;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

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
    FootballClub f1 = new FootballClub("ccc3", "Colombo", 0, 10, 0, 0, 15, 0, 0);
    FootballClub f2 = new FootballClub("aaa1", "Kelaniya", 0, 12, 0, 0, 13, 0, 0);
    FootballClub f3 = new FootballClub("bbb2", "Moratuwa", 0, 8, 0, 0, 17, 0, 0);

    Match m1 = new Match(f2, f1, 1, 1, LocalDate.of(2020, 12, 25));
    Match m2 = new Match(f1, f2, 2, 5, LocalDate.of(2020, 12, 24));
    Match m3 = new Match(f1, f3, 3, 0, LocalDate.of(2020, 12, 23));

    public void sampleData() {
        teamList.add(f3);
        teamList.add(f1);
        teamList.add(f2);

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
    public void displayLeagueTable() {
        Collections.sort(teamList, Collections.reverseOrder());
        if (!teamList.isEmpty()) {
            for (FootballClub f : teamList) {
                System.out.println(f);
                System.out.println("- - - - - - - - - - - - - - - - - - - - -");
            }
        } else {
            System.out.println("No Team has played a Match yet");
        }
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

    public boolean isContain(String clubName) {
        for (FootballClub f : teamList) {
            if (f.getClubName().equalsIgnoreCase(clubName)) {
                return false;
            }
        }
        return true;
    }
}
