package models;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PremierLeagueManager implements LeagueManager {

    final static int MAX_TEAMS = 20;
    private final List<FootballClub> teamList = new ArrayList<>();
    private final List<Match> matchList = new ArrayList<>();
    private int availableSlots = MAX_TEAMS;

    // to check specific progress execution
    static boolean success = false;

    /*
     * using singleton design pattern
     * to use single premierLeague throughout the whole project
     * */
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
    public boolean addClub(FootballClub footballClub) {
        if (teamList.contains(footballClub)) {
            System.out.println("\nTeam has been already added to the League!");
        } else if (availableSlots == 0) {
            System.out.println("\nNo available slots in the League!");
        } else {
            teamList.add(footballClub);
            availableSlots -= 1;
            System.out.println("\nTeam " + footballClub.getClubName().toUpperCase() + " from " + footballClub.getClubLocation().toUpperCase() + " successfully added to the League!");
        }
        success = true;
        System.out.println("\nThere are " + "\033[1;93m" + availableSlots + " available slots " + "\033[0m" + "in the League");
        return success;
    }

    @Override
    public boolean addPlayedMatch(String teamOneName, String teamTwoName, int teamOneGoalsScored, int teamTwoGoalsScored, LocalDate date) {
        if (!teamList.isEmpty()) {
            Match match = new Match(date, teamOneName, teamTwoName, teamOneGoalsScored, teamTwoGoalsScored, "");
            match.updateStats("cliApp");
            matchList.add(match);
            success = true;
        }
        return success;
    }

    @Override
    public boolean deleteClub(String clubName) {
        if (!teamList.isEmpty()) {
            teamList.removeIf(f -> f.getClubName().equalsIgnoreCase(clubName));
            System.out.println("\nTeam " + "\033[1;93m" + clubName.toUpperCase() + "\033[0m" + " removed from the League!");
            availableSlots += 1;
            success = true;
        }
        return success;
    }

    @Override
    public FootballClub displaySingleClub(String clubName) {
        if (!teamList.isEmpty()) {
            for (FootballClub f : teamList)
                if (f.getClubName().equalsIgnoreCase(clubName)) {
                    success = true;
                    return f;
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
            success = true;
            return teamList;
        }
        return null;
    }

    @Override
    public boolean saveData(String fileName) {
        try {
            File file = new File(fileName);

//          overwriting with the existing data
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            if (fileName.contains("Teams.txt")) {
                for (FootballClub f : teamList) {
                    objectOutputStream.writeObject(f);
                    success = true;
                }
                teamList.clear();
            } else {
                for (Match m : matchList) {
                    objectOutputStream.writeObject(m);
                    success = true;
                }
                matchList.clear();
            }
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
//            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean loadData(String fileName) {
        File file = new File(fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            FootballClub f;
            while (true) {
                try {
                    if (fileName.contains("Teams.txt")) {
                        f = (FootballClub) objectInputStream.readObject();
                        teamList.add(f);
                        availableSlots = 20 - teamList.size();
                    } else {
                        Match m = (Match) objectInputStream.readObject();
                        matchList.add(m);
                    }
                    success = true;
                } catch (EOFException | ClassNotFoundException ex) {
//                        ex.printStackTrace();
                    break;
                }
            }

            fileInputStream.close();
            objectInputStream.close();

        } catch (IOException e) {
//                e.printStackTrace();
        }

        return success;
    }

//  these methods gives access to the private variables in this class

    public static boolean isSuccess() {
        return success;
    }

    public static void setSuccess(boolean success) {
        PremierLeagueManager.success = success;
    }

    public List<FootballClub> getTeamList() {
        return teamList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
