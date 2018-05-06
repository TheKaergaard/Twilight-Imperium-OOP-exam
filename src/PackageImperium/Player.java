package PackageImperium;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.Galaxy;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.Unit;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Player extends CustomComparator {

    public Player(String playerName, String uniqueRace, String uniqueColour) {
        this.playerName = playerName;
        this.uniqueRace = uniqueRace;
        this.uniqueColour = uniqueColour;
    }

    public Player() {
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

    public ArrayList<Unit> shipsOwnedByPlayer(Player inputPlayer, Galaxy inputGalaxy) {
        ArrayList<Unit> listOfShips = inputGalaxy.listOfShipsInGalaxy();
        ArrayList<Unit> shipsOwnedByInputPlayer = new ArrayList<>();

        for (Unit ship : listOfShips) {
            if (ship.getOwner().equals(inputPlayer)) {
                shipsOwnedByInputPlayer.add(ship);
            }
        }
        Collections.sort(shipsOwnedByInputPlayer, new CustomComparator());

        return shipsOwnedByInputPlayer;
    }

    public ArrayList<SpaceSystem> listOfSystemsOwnedByOnePlayer(Galaxy inputGalaxy) {
        ArrayList<SpaceSystem> allSystemsInGalaxy = inputGalaxy.listOfSystemsInGalaxy();
        ArrayList<SpaceSystem> systemsOwnedByPlayer = new ArrayList<>();
        Player firstPlayer = null;
        Boolean isOwned = true;

        //Goes through all systems in the input galaxy and then the units in each system.
        for (SpaceSystem temp : allSystemsInGalaxy) {
            isOwned = true;
            for (Unit unit : temp.allShipsInSystem()) {
                //firstPlayer is only null once. Then it will be written over by the found player
                if (firstPlayer == null) {
                    firstPlayer = unit.getOwner();
                }
                if (!unit.getOwner().equals(firstPlayer)) {
                    isOwned = false;
                    break;
                }
                else {
                    isOwned = true;
                }
            }
            if (isOwned) {
                systemsOwnedByPlayer.add(temp);
            }
        }
        return systemsOwnedByPlayer;
    }


    public void createTextFileOfPlayerOwnedPlanets(Player inputPlayer) {


        /*
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/PackageImperium/planets_owned_by_player.txt", true)))) {
            out.print();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
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
