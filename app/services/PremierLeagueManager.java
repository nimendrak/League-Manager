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
    static boolean success = false;

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
            System.out.println("\nTeam " + "\033[1;93m" + clubName + "\033[0m" + " removed from the League!");
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
        /*
         * compareTo method use as the default sorting
         * if search function triggered, program will use custom comparators accordingly
         * */
        if (!teamList.isEmpty()) {
            Collections.sort(teamList, Collections.reverseOrder());
            return teamList;
        }
        return null;
    }

    @Override
    public void saveData(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();

//          clear all the data by deleting the existing file and recreating it
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            boolean fileCreated = file.createNewFile();

            if (fileCreated) {
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
                success = true;
//                System.out.println("Successfully Saved to the File!");
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
                            if (!teamList.contains(f)) {
                                teamList.add(f);
                            }
                        } else {
                            Match m = (Match) objectInputStream.readObject();
                            if (!matchList.contains(m)) {
                                matchList.add(m);
                            }
                        }
                    } catch (EOFException | ClassNotFoundException ex) {
//                        ex.printStackTrace();
                        success = true;
                        break;
                    }
                }
                fileInputStream.close();
                objectInputStream.close();

            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

    public List<FootballClub> getTeamList() {
        return teamList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public static boolean isSuccess() {
        return success;
    }

    public static void setSuccess(boolean success) {
        PremierLeagueManager.success = success;
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
