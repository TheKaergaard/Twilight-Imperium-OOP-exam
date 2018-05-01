
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import java.util.Objects;

public class Player {

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
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(player, player.playerName);
    }

    @Override
    public int hashCode() {
        return Object.hash(playerName);
    }




}
