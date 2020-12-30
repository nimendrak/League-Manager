package services;

import models.FootballClub;
import models.Match;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSourceServices {

    List<FootballClub> clubsDataArray = new ArrayList<>();
    List<Match> matchDataArray = new ArrayList<>();

    private static DataSourceServices instance = null;

    private DataSourceServices() {
    }

    public static DataSourceServices getInstance() {
        if (instance == null) {
            synchronized (DataSourceServices.class) {
                if (instance == null) {
                    instance = new DataSourceServices();
                }
            }
        }
        return instance;
    }

    public void loadData(String clubSource, String matchSource) {
        File clubFile = new File(clubSource);
        File matchFile = new File(matchSource);

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
        System.out.println("loaded\nclubs count - " + clubsDataArray.size());
        System.out.println("matches count - " + matchDataArray.size());
        System.out.println();
    }

    public void saveData(String clubSource, String matchSource) {

        try {
            File clubFile = new File(clubSource);
            File matchFile = new File(matchSource);

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
        System.out.println("saved\nclubs count - " + clubsDataArray.size());
        System.out.println("matches count - " + matchDataArray.size());
        System.out.println();
    }

    public List<FootballClub> getClubsDataArray() {
        return clubsDataArray;
    }

    public List<Match> getMatchDataArray() {
        return matchDataArray;
    }
}
