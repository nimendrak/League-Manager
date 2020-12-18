package models;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;

    public SportsClub(String clubName, String clubLocation) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    @Override
    public String toString() {
        return (clubName.substring(0,3).toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubName.toLowerCase(), that.clubName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName);
    }
}
