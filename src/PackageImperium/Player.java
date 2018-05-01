package PackageImperium;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import java.util.Objects;

public class Player {

    public Player(String playerName, String uniqueRace, String uniqueColour) {
        this.playerName = playerName;
        this.uniqueRace = uniqueRace;
        this.uniqueColour = uniqueColour;
    }

    private String playerName;
    private String uniqueRace;
    private String uniqueColour;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setUniqueRace(String uniqueRace) {
        this.uniqueRace = uniqueRace;
    }

    public void setUniqueColour(String uniqueColour) {
        this.uniqueColour = uniqueColour;
    }

    public String getPlayerName() {

        return playerName;
    }

    public String getUniqueRace() {
        return uniqueRace;
    }

    public String getUniqueColour() {
        return uniqueColour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) &&
                Objects.equals(uniqueRace, player.uniqueRace) &&
                Objects.equals(uniqueColour, player.uniqueColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, uniqueRace, uniqueColour);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", uniqueRace='" + uniqueRace + '\'' +
                ", uniqueColour='" + uniqueColour + '\'' +
                '}';
    }

}
