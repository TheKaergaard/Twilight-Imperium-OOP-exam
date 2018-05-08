package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;
import PackageImperium.Units.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SpaceSystem {
    private Position position;
    public ArrayList<Planet> listOfPlanetsInSystem = new ArrayList<>();
    public ArrayList<Unit> listOfShipsInSystem = new ArrayList<>();
    public HashMap<Position, SpaceSystem> hexagonalGrid = new HashMap<>();

    public enum Position {
        CENTER, NORTH, NORTH_EAST, NORTH_WEST, SOUTH, SOUTH_EAST, SOUTH_WEST
    }

    public SpaceSystem() {
    }

    public void setHexagonalGrid(Position tempPos, SpaceSystem tempSystem) {
        this.hexagonalGrid.put(tempPos, tempSystem);
    }

    public HashMap<Position, SpaceSystem> getHexagonalGrid() {
        return hexagonalGrid;
    }

    //Looks through all units in system and gets the individually players
    public ArrayList<Player> listOfPlayersInSystem() {
        ArrayList<Player> playersInSystem = new ArrayList<>();
        for (Unit unit : allShipsInSystem()) {
            if (!playersInSystem.contains(unit.getOwner())) {
                playersInSystem.add(unit.getOwner());
            }
        }
        return playersInSystem;
    }

    public void addEnteredShip(Unit shipToAdd) {
        listOfShipsInSystem.add(shipToAdd);
        System.out.println(shipToAdd.toString() + "added to system");
    }

    public void removeLeavedShip(Unit shipToRemove) {
        boolean check = listOfShipsInSystem.contains(shipToRemove);
        if (check) {
            listOfShipsInSystem.remove(shipToRemove);
        } else {
            System.out.println(shipToRemove + "does not exist in system");
        }
    }

    //Returning all ships in the system as ArrayList
    public ArrayList<Unit> allShipsInSystem() {
        ArrayList<Unit> allShipsInSystem = new ArrayList<>();
        allShipsInSystem.addAll(listOfShipsInSystem);

        return allShipsInSystem;
    }

    public ArrayList<Unit> allShipsOwnedByOnePlayer(Player inputPlayer) {
        ArrayList<Unit> allShipsInSystem = this.allShipsInSystem();
        ArrayList<Unit> shipsOwnedByPlayer = new ArrayList<>();

        for (Unit ship : allShipsInSystem) {
            if (ship.getOwner().equals(inputPlayer)) {
                shipsOwnedByPlayer.add(ship);
            }
        }
        //TODO Sort before output
        return shipsOwnedByPlayer;
    }

    public Player playerWhoWonSpaceBattle() {
        int hitPlayer1;
        int hitPlayer2;
        Player winningPlayer = new Player();
        ArrayList<Player> playersInSystemInBattle = this.listOfPlayersInSystem();

        if (playersInSystemInBattle.size() != 2) {
            System.out.println("Too many players in systems for space battle");
        } else {
            hitPlayer1 = hitCount(allShipsOwnedByOnePlayer(playersInSystemInBattle.get(0)));
            hitPlayer2 = hitCount(allShipsOwnedByOnePlayer(playersInSystemInBattle.get(1)));


        }


        return winningPlayer;
    }

    //Decides if a player gets a hit when rolling 10-sided dice. Looks at the given units combat value
    public int hitCount(ArrayList<Unit> unitsOwnedByPlayer) {
        Random rnd = new Random();
        int diceRoll = rnd.nextInt(9) + 1;
        int hit = 0;

        for (Unit unit : unitsOwnedByPlayer) {
            if (diceRoll >= unit.getCombatValue()) {
                hit++;
            }
        }
        return hit;
    }

    public void removeUnitAfterHit(int detectedHits, Player inputPlayer) {
        for (int i = 0; i < detectedHits; i++) {
            if (allShipsOwnedByOnePlayer(inputPlayer).size() >= detectedHits) {
                if (allShipsOwnedByOnePlayer(inputPlayer).isEmpty()) {
                    System.out.println(inputPlayer.toString() + " has no ships left to remove!");
                }
                //removes ship from the back in Array List for each hit
                allShipsInSystem().remove(allShipsOwnedByOnePlayer(inputPlayer).size() - 1);
                detectedHits--;
            } else {
                allShipsInSystem().removeAll();
            }
        }
    }

    @Override
    public String toString() {
        return "SpaceSystem{" +
                "position=" + position +
                ", listOfPlanetsInSystem=" + listOfPlanetsInSystem +
                ", listOfShipsInSystem=" + listOfShipsInSystem +
                ", hexagonalGrid=" + hexagonalGrid +
                '}';
    }
}
