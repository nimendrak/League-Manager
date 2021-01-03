import models.ProgressBar;
import models.FootballClub;
import models.PremierLeagueManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleApplication {
    static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    final static Scanner sc = new Scanner(System.in);

    final static String leagueMatches = "DataSource/PremierLeagueMatches.txt";
    final static String leagueClubs = "DataSource/PremierLeagueTeams.txt";

    public static void main(String[] args) {

        System.out.println("\n******************************************************");
        System.out.println("*********** " + "\033[1;93m" + "Football Premier League Manager " + "\033[0m" + "**********");
        System.out.println("******************************************************");

//      load data into the program once its initiated
        System.out.println("\nIndexing Premier League Data..");
        loadAllData(premierLeagueManager, leagueClubs, leagueMatches);

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
            System.out.println("Press 8 to Launch the GUI application");
            System.out.println("Press 9 to Quit");

            System.out.print("\nPrompt your Option : ");
            option = sc.next();

            // consuming the leftover new line
            // using the nextLine() method
            sc.nextLine();

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
                    displaySingleClub(premierLeagueManager);
                    break;
                case "6":
                    saveDate(premierLeagueManager);
                    break;
                case "7":
                    loadData(premierLeagueManager);
                    break;
                case "8":
                    invokePlayServer(true);
                    break;
                case "9":
                    System.out.println("\nApplication is now Existing...");
                    invokePlayServer(false);
                    break;
                default:
                    System.out.println("Invalid input");
                    System.out.println("\n------------------------------------------------------");
            }
        } while (!option.equals("9"));
    }

    /*
     * if any method that invokes teamList and matchList;
     * overwrite the dataSource and clear out the current array lists and
     * load new data again to the array lists
     * */

    private static void invokePlayServer(boolean isRun) {
        System.out.println("------------------------------------------------------");

        try {
            String currentRoot = new File("./").getCanonicalPath();

            ProcessBuilder builder = new ProcessBuilder();
            Process process = null;

            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            if (isWindows) {
                builder.command("cmd.exe", "/c", "sbt run").directory(new File(currentRoot));
            } else {
                if (isRun) {
                    builder.command("zsh", "-c", "sbt run").directory(new File(currentRoot));
                    process = builder.start();
                    System.out.println("\033[1;93m" + "\nGUI application is now Launching..\n" + "\033[0m");

//                  progress bar is a custom made class which initiate with the process builder
                    ProgressBar bar = new ProgressBar();
                    System.out.println("Process Starts Now!");

                    bar.update(0, 2000);
                    for (int i = 0; i < 2000; i++) {
                        for (int j = 0; j < 10000000; j++)
                            for (int p = 0; p < 10000000; p++) ;
                        // update the progress bar
                        bar.update(i, 2000);
                    }
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("Process Completed!\n");

                    System.out.println("------------------------------------------------------");
                } else {
                    builder.command("zsh", "-c", "kill -9 $(lsof -i:4200 -t) 2> /dev/null");
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadAllData(PremierLeagueManager leagueManager, String teamData, String matchData) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.print("Teams    - ");
            leagueManager.loadData(teamData);
            isSuccess("Successfully Loaded!", "load");

            TimeUnit.SECONDS.sleep(1);
            System.out.print("Matches  - ");
            leagueManager.loadData(matchData);
            isSuccess("Successfully Loaded!", "load");

            leagueManager.saveData(teamData);
            leagueManager.saveData(matchData);
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void loadData(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n**************************");
        System.out.println("\033[1;93m" + "Load League Data from a File" + "\033[0m");
        System.out.println("**************************\n");

        loadAllData(leagueManager, leagueClubs, leagueMatches);

        System.out.println("\n------------------------------------------------------");
    }

    private static void saveDate(PremierLeagueManager leagueManager) {
        System.out.println("------------------------------------------------------");

        System.out.println("\n**************************");
        System.out.println("\033[1;93m" + "Save League Data to a File" + "\033[0m");
        System.out.println("**************************\n");

        System.out.print("Teams    - ");
        leagueManager.saveData(leagueClubs);
        isSuccess("Successfully Saved to the File!", "save");

        System.out.print("Matches  - ");
        leagueManager.saveData(leagueMatches);
        isSuccess("Successfully Saved to the File!", "save");

        System.out.println("\n------------------------------------------------------");
    }

    private static void displaySingleClub(PremierLeagueManager leagueManager) {
        leagueManager.loadData(leagueClubs);
        System.out.println("------------------------------------------------------");

        System.out.println("\n*******************************");
        System.out.println("\033[1;93m" + "Display Stats for Specific Club" + "\033[0m");
        System.out.println("*******************************\n");

        while (true) {
            System.out.print("Name of the Club : ");
            String clubName = isContain("Name of the Club : ", sc.nextLine());

            System.out.println("\nClub\t\t\t\tMP  W   L   D   GS  GD  PTS");
            System.out.println("-----------------------------------------------");

            if (clubName.equals("null")) {
                System.out.println("\033[1;93m" + "\t\t  Prompted Team does not exist!" + "\033[0m");
                break;
            }

            if (!clubName.equalsIgnoreCase("null")) {
                System.out.println(leagueManager.displaySingleClub(clubName).toString());
                PremierLeagueManager.setSuccess(false);
            }

            System.out.println("\n*** Club Name is limited to 6 characters!!! ***\n\nW - Wins | MP - Matches Played\n" +
                    "L - Loss | GD - Goals Difference\nD - Draw | GS - Goals Scored");
            break;
        }
        System.out.println("\n------------------------------------------------------");
        leagueManager.saveData(leagueClubs);
    }

    private static void displayLeagueTable(PremierLeagueManager leagueManager) {
        leagueManager.loadData(leagueClubs);
        System.out.println("------------------------------------------------------");

        System.out.println("\n********************");
        System.out.println("\033[1;93m" + "Display League Table" + "\033[0m");
        System.out.println("********************\n");

        System.out.println("The table sorted out in descending order \naccording to " +
                "their points(PTS) gained\nthroughout the current League.\n");

        System.out.println("\033[1;93m" + "\t\t\t\tLeague Table" + "\033[0m");
        System.out.println("-----------------------------------------------");
        System.out.println("Club\t\t\t\tMP  W   L   D   GS  GD  PTS");
        System.out.println("-----------------------------------------------");

        if (!premierLeagueManager.getTeamList().isEmpty()) {
            for (FootballClub f : leagueManager.displayLeagueTable()) {
                System.out.println(f);
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
            }
            PremierLeagueManager.setSuccess(false);
        } else {
            System.out.println("\033[1;93m" + "\t     No Team has played a Match yet" + "\033[0m");
        }

        System.out.println("\n*** Club Name is limited to 6 characters!!! ***\n\nW - Wins | MP - Matches Played\n" +
                "L - Loss | GD - Goals Difference\nD - Draw | GS - Goals Scored");

        System.out.println("\n------------------------------------------------------");
        leagueManager.saveData(leagueClubs);
    }

    private static void addPlayedMatch(PremierLeagueManager leagueManager) {
        leagueManager.loadData(leagueClubs);
        leagueManager.loadData(leagueMatches);
        System.out.println("------------------------------------------------------");

        System.out.println("\n*************************");
        System.out.println("\033[1;93m" + "Add a Match to the League" + "\033[0m");
        System.out.println("*************************\n");

        labelLoop1:
        while (true) {
            if (premierLeagueManager.getTeamList().size() >= 2) {
                System.out.print("Club1 name ? : ");
                String clubOneName = isContain("Club1 name ? : ", sc.nextLine());
                if (clubOneName.equals("null")) {
                    break;
                }

                System.out.print("Club1 scored goals : ");
                int clubOneGoals = Integer.parseInt(isInt("Club1 scored goals : "));
                if (clubOneGoals == -1) {
                    break;
                }

                // consuming the leftover new line
                // using the nextLine() method
                sc.nextLine();

                System.out.print("\nClub2 name ? : ");
                String clubTwoName = isContain("Club2 name ? : ", sc.nextLine());
                if (clubTwoName.equals("null")) {
                    break;
                }

                System.out.print("Club2 scored goals : ");
                int clubTwoGoals = Integer.parseInt(isInt("Club2 scored goals : "));
                if (clubTwoGoals == -1) {
                    break;
                }

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
                            System.out.println("\n" + "\033[1;93m" + clubOneName.toUpperCase() + "\033[0m" + " vs " + "\033[1;93m" + clubTwoName.toUpperCase() + "\033[0m" + " match has been added!");
                            break labelLoop1;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid Date");
                    }
                }
            } else {
                System.out.println("Only one team registered on the league!");
                break;
            }
        }

        System.out.println("\n------------------------------------------------------");
        leagueManager.saveData(leagueMatches);
        leagueManager.saveData(leagueClubs);
    }

    private static void deleteClub(PremierLeagueManager leagueManager) {
        leagueManager.loadData(leagueClubs);
        System.out.println("------------------------------------------------------");

        System.out.println("\n***************************");
        System.out.println("\033[1;93m" + "Remove Team from the League" + "\033[0m");
        System.out.println("***************************\n");

        while (true) {
            System.out.print("Name of the Club : ");
            String clubName = isContain("Name of the Club : ", sc.nextLine());
            if (clubName.equals("null")) {
                break;
            }

            System.out.print("Do you want remove Team " + clubName.toUpperCase() + "? (Y/N) : ");
            String option = sc.next();

            if (option.equalsIgnoreCase("y")) {
                leagueManager.deleteClub(clubName);
                PremierLeagueManager.setSuccess(false);
            } else {
                System.out.println("\n" + "\033[1;93m" + clubName.toUpperCase() + "\033[0m" + " is still on the Play!");
            }
            break;
        }

        System.out.println("\n------------------------------------------------------");
        leagueManager.saveData(leagueClubs);
    }

    private static void addClub(PremierLeagueManager leagueManager) {
        leagueManager.loadData(leagueClubs);

        System.out.println("------------------------------------------------------");

        System.out.println("\n************************");
        System.out.println("\033[1;93m" + "Add a Team to the League" + "\033[0m");
        System.out.println("************************\n");

        System.out.print("01 Club name ? : ");
        String clubName = sc.nextLine();

        System.out.print("02 Club location ? : ");
        String clubLocation = sc.nextLine();

        FootballClub footballClub = new FootballClub(clubName, clubLocation);
        leagueManager.addClub(footballClub);
        PremierLeagueManager.setSuccess(false);

        leagueManager.saveData(leagueClubs);

        System.out.println("\n------------------------------------------------------");
    }

    //  validate function01 -> this method returns int values
    private static String isInt(String label) {
        while (!sc.hasNextInt()) {
            if (sc.next().equalsIgnoreCase("q")) {
                return "-1";
            }
            System.out.println("Prompt Integers to proceed !\n");
            System.out.print("Press \"Q\" to return, or enter " + label);
        }
        return sc.next();
    }

    //  validate function02 -> this method check whether prompted club registered on the league or not
    public static boolean isTeam(String clubName) {
        for (FootballClub f : premierLeagueManager.getTeamList()) {
            if (f.getClubName().equalsIgnoreCase(clubName)) {
                return false;
            }
        }
        return true;
    }

    //  validate function03 -> this method is a expansion of the validate function03
    private static String isContain(String label, String clubName) {
        while (isTeam(clubName)) {
            System.out.println("Prompted Club is not available!\n");
            System.out.print("Press \"Q\" to return, or enter " + label);
            clubName = sc.nextLine();
            if (clubName.equalsIgnoreCase("q")) {
                return "null";
            }
        }
        return clubName;
    }

    //  validate function04 -> this method returns specific methods of the PremierLeagueManager
    //  got executed successfully or not
    private static void isSuccess(String message, String type) {
        if (PremierLeagueManager.isSuccess()) {
            System.out.println(message);
        } else {
            if (type.equalsIgnoreCase("save")) {
                System.out.println("Error occurred while saving the data");
            } else {
                System.out.println("No Data Available!");
            }
        }
        PremierLeagueManager.setSuccess(false);
    }
}

