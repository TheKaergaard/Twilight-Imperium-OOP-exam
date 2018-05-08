package PackageImperium;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.Galaxy;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.Unit;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Player extends CustomComparator {
    private String playerName;
    private String uniqueRace;
    private String uniqueColour;

    public Player() {
    }

    public Player(String playerName, String uniqueRace, String uniqueColour) {
        this.playerName = playerName;
        this.uniqueRace = uniqueRace;
        this.uniqueColour = uniqueColour;
    }
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
        Boolean isOwned = true;

        //Goes through all systems in the input galaxy and then the units in each system.
        for (SpaceSystem temp : allSystemsInGalaxy) {
            Player onlyPlayerInSystem = null;
            for (Unit unit : temp.allShipsInSystem()) {
                //firstPlayer is only null once. Then it will be written over by the found player
                if (onlyPlayerInSystem == null) {
                    onlyPlayerInSystem = unit.getOwner();
                    isOwned = true;
                } else if (unit.getOwner().equals(onlyPlayerInSystem)) {
                    isOwned = true;
                } else {
                    isOwned = false;
                    break;
                }
            }
            if (isOwned) {
                systemsOwnedByPlayer.add(temp);
            }
        }
        return systemsOwnedByPlayer;
    }

    public void createTextFileOfPlayerOwnedPlanets(Galaxy inputGalaxy, String fileName) {
        Player tempPlayer = new Player();
        ArrayList<SpaceSystem> playerOwnedSystems = tempPlayer.listOfSystemsOwnedByOnePlayer(inputGalaxy);

        File textfile = new File("src/PackageImperium/"+fileName+".txt");
        //Try with resources closes the writer automatically
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/PackageImperium/"+fileName+".txt", true)))) {
            for (SpaceSystem temp : playerOwnedSystems) {
                out.write(temp.allShipsInSystem().get(0).getOwner().toString() + "\n");
                for (int i = 0; i < temp.listOfPlanetsInSystem.size(); i++) {
                    out.write("\t"+temp.listOfPlanetsInSystem.get(i).planetName + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Could not write to the given .txt file");
        }
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
