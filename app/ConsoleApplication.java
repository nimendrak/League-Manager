import models.FootballClub;
import services.PremierLeagueManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleApplication {
    static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    final static Scanner sc = new Scanner(System.in);

    // delete
    static boolean oneTime = false;

    public static void main(String[] args) {

        System.out.println("\n******************************************************");
        System.out.println("*********** " + "\033[1;93m" + "Football Premier League Manager " + "\033[0m" + "**********");
        System.out.println("******************************************************");

        // delete
        if (!oneTime) {
            premierLeagueManager.sampleData();
            oneTime = true;
        }

        final String leagueMatches = "app/utils/DataSource/PremierLeagueMatches.txt";
        final String leagueTeams = "app/utils/DataSource/PremierLeagueTeams.txt";

        System.out.println("\nIndexing Premier League Data..");
        loadAllData(premierLeagueManager, leagueTeams, leagueMatches);

        System.out.println("\n------------------------------------------------------");

        String option;
        do {
            System.out.println("Choose a option, which mentioned below;\n");

            System.out.println("Press 1 to Add club");
            System.out.println("Press 2 to Delete club");
            System.out.println("Press 3 to Add Played Match");
            System.out.println("Press 4 to Display League Table");
            System.out.println("Press 5 to Display Stats for specific Club");
            System.out.println("Press 6 to Save data into a file");
            System.out.println("Press 7 to Load data from the file");
            System.out.println("Press 8 to Quit");

            System.out.print("\nPrompt your Option : ");
            option = sc.next();

            switch (option) {
                case "1":
                    addClub(premierLeagueManager);
                    break;
                case "2":
                    deleteClub(premierLeagueManager);
                    break;
                case "3":
                    addPlayedMatch(premierLeagueManager);
                    break;
                case "4":
                    displayLeagueTable(premierLeagueManager);
                    break;
                case "5":
                    displayStatisticsForSpecificClub(premierLeagueManager);
                    break;
                case "6":
                    saveDate(premierLeagueManager, leagueTeams, leagueMatches);
                    break;
                case "7":
                    loadData(premierLeagueManager, leagueTeams, leagueMatches);
                    break;
                case "8":
                    System.out.println("\nApplication is now Existing...\n");
//                    saveDate(premierLeagueManager, leagueTeams, leagueMatches);
                    break;
                default:
                    System.out.println("Invalid input");
                    System.out.println("\n------------------------------------------------------");
            }
        } while (!option.equals("8"));
    }


    private static void loadAllData(PremierLeagueManager leagueManager, String teamData, String matchData) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.print("Teams    - ");
            leagueManager.loadData(teamData);
            TimeUnit.SECONDS.sleep(1);
            System.out.print("Matches  - ");
            leagueManager.loadData(matchData);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void loadData(PremierLeagueManager leagueManager, String teamData, String matchData) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n**************************");
        System.out.println("\033[1;93m" + "Load League Data from a File" + "\033[0m");
        System.out.println("**************************\n");

        loadAllData(leagueManager, teamData, matchData);

        System.out.println("\n------------------------------------------------------");
    }

    private static void saveDate(PremierLeagueManager leagueManager, String teamData, String matchData) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n**************************");
        System.out.println("\033[1;93m" + "Save League Data to a File" + "\033[0m");
        System.out.println("**************************\n");

        System.out.print("Teams    - ");
        leagueManager.saveData(teamData);
        System.out.print("Matches  - ");
        leagueManager.saveData(matchData);

        System.out.println("\n------------------------------------------------------");
    }

    private static void displayStatisticsForSpecificClub(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n*******************************");
        System.out.println("\033[1;93m" + "Display Stats for Specific Club" + "\033[0m");
        System.out.println("*******************************\n");

        System.out.print("Name of the Club : ");
        String clubName = isContain(leagueManager, "Name of the Club : ", sc.next());

        System.out.println("\nClub\t\t MP\t  W\t  L\t  D\t  GS  GR  PTS");
        System.out.println("-----------------------------------------");

        System.out.println(leagueManager.displaySingleClub(clubName).toString());

        System.out.println("\n* Club Name is limited to 3 characters! *\n\nW - Wins | MP - Matches Played\n" +
                "L - Loss | GR - Goals Received\nD - Draw | GS - Goals Scored");

        System.out.println("\n------------------------------------------------------");
    }

    private static void displayLeagueTable(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n********************");
        System.out.println("\033[1;93m" + "Display League Table" + "\033[0m");
        System.out.println("********************\n");

        System.out.println("The table sorted out in descending order \naccording to " +
                "their points(PTS) gained\nthroughout the current League.\n");

        System.out.println("\033[1;93m" + "\t\t\t  League Table" + "\033[0m");
        System.out.println("-----------------------------------------");
        System.out.println("Club\t\t MP\t  W\t  L\t  D\t  GS  GR  PTS");
        System.out.println("-----------------------------------------");

        if (!premierLeagueManager.getTeamList().isEmpty()) {
            for (FootballClub f : leagueManager.displayLeagueTable()) {
                System.out.println(f);
                System.out.println("- - - - - - - - - - - - - - - - - - - - -");
            }
        } else {
            System.out.println("No Team has played a Match yet");
        }

        System.out.println("\n* Club Name is limited to 3 characters! *\n\nW - Wins | MP - Matches Played\n" +
                "L - Loss | GR - Goals Received\nD - Draw | GS - Goals Scored");

        System.out.println("\n------------------------------------------------------");
    }

    private static void addPlayedMatch(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n*************************");
        System.out.println("\033[1;93m" + "Add a Match to the League" + "\033[0m");
        System.out.println("*************************\n");

        System.out.print("Club1 name ? : ");
        String clubOneName = isContain(leagueManager, "Club1 name ? : ", sc.next());

        System.out.print("Club1 scored goals : ");
        int clubOneGoals = Integer.parseInt(validateInt("Club1 scored goals : "));

        System.out.print("\nClub2 name ? : ");
        String clubTwoName = isContain(leagueManager, "Club2 name ? : ", sc.next());

        System.out.print("Club2 scored goals : ");
        int clubTwoGoals = Integer.parseInt(validateInt("Club2 scored goals : "));

        String dateString;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            try {
                System.out.print("\nPlayed Date (yyyy-MM-dd) : ");
                dateString = sc.next();
                dateFormat.parse(dateString);

                LocalDate today = LocalDate.now();

                if (today.isAfter(LocalDate.parse(dateString)) | today.equals(LocalDate.parse(dateString))) {
                    leagueManager.addPlayedMatch(clubOneName, clubTwoName, clubOneGoals, clubTwoGoals, LocalDate.parse(dateString));

                    System.out.println("\n" + "\033[1;93m" + clubOneName + "\033[0m" + " vs " + "\033[1;93m" + clubTwoName + "\033[0m" + " match has been added!");
                }

                break;
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("Invalid Date");
            }
        }
        System.out.println("\n------------------------------------------------------");
    }

    private static void deleteClub(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n***************************");
        System.out.println("\033[1;93m" + "Remove Team from the League" + "\033[0m");
        System.out.println("***************************\n");

        System.out.print("Name of the Club : ");
        String clubName = isContain(leagueManager, "Name of the Club : ", sc.next());

        leagueManager.deleteClub(clubName);

        System.out.println("\n------------------------------------------------------");
    }

    private static void addClub(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n************************");
        System.out.println("\033[1;93m" + "Add a Team to the League" + "\033[0m");
        System.out.println("************************\n");

        System.out.print("01 Club name ? : ");
        String clubName = sc.next();

        System.out.print("02 Club location ? : ");
        String clubLocation = sc.next();

        int seasonWins = 0, seasonDefeats = 0, seasonDraws = 0, goalsReceived = 0, goalsScored = 0, pointsGained = 0, matchesPlayed = 0;

        FootballClub footballClub = new FootballClub(clubName, clubLocation, matchesPlayed,
                seasonWins, seasonDefeats, seasonDraws, goalsReceived, goalsScored, pointsGained);

        leagueManager.addClub(footballClub);

        System.out.println("\n------------------------------------------------------");
    }

    private static String validateInt(String label) {
        while (!sc.hasNextInt()) {
            System.out.println("Prompt Integers to proceed !\n");
            System.out.print(label);
            sc.next();
        }
        return sc.next();
    }

    private static String isContain(PremierLeagueManager leagueManager, String label, String clubName) {
        while (leagueManager.isContain(clubName)) {
            System.out.println("Prompted Club is not available!\n");
            System.out.print(label);
            clubName = sc.next();
        }
        return clubName;
    }
}
