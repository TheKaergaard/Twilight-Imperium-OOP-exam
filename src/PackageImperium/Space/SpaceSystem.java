package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.CustomComparators.CustomUnitResourceCostComparator;
import PackageImperium.CustomExceptions.InvalidAmountOfPlayersException;
import PackageImperium.CustomExceptions.UnitDoesNotExistException;
import PackageImperium.Player;
import PackageImperium.Units.Unit;

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

    public ArrayList<Unit> getListOfShipsInSystem() {
        return listOfShipsInSystem;
    }

    //A players presence in a system is defined by the units in the system
    public ArrayList<Player> listOfPlayersInSystem() {
        ArrayList<Player> playersInSystem = new ArrayList<>();
        //Looks through all units in system and adding their owner to ArrayList if it's not already contained
        for (Unit unit : this.listOfShipsInSystem) {
            if (!playersInSystem.contains(unit.getOwner())) {
                playersInSystem.add(unit.getOwner());
            }
        }
        return playersInSystem;
    }

    public void addShipToSystem(Unit shipToAdd) {
        listOfShipsInSystem.add(shipToAdd);
    }

    public void removeShipFromSystem(Unit shipToRemove) {
        boolean check = listOfShipsInSystem.contains(shipToRemove);
        if (check) {
            listOfShipsInSystem.remove(shipToRemove);
        } else {
            try {
                throw new UnitDoesNotExistException(shipToRemove);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Unit> allShipsOwnedByOnePlayerInSystem(Player inputPlayer) {
        ArrayList<Unit> allShipsInSystem = getListOfShipsInSystem();
        ArrayList<Unit> shipsOwnedByPlayer = new ArrayList<>();
        //If a ships owner correspond to the looked at unit, it's added to ArrayList
        for (Unit ship : allShipsInSystem) {
            if (ship.getOwner().equals(inputPlayer)) {
                shipsOwnedByPlayer.add(ship);
            }
        }
        //Each players list of ships are sorted after resource cost (highest to lowest)
        shipsOwnedByPlayer.sort(new CustomUnitResourceCostComparator());
        return shipsOwnedByPlayer;
    }

    //Each players existence is defined by units in a system
    public Player playerWhoWonSpaceBattle() {
        int hitCountPlayer1;
        int hitCountPlayer2;
        Player winningPlayer;
        ArrayList<Player> playersInSystemInBattle = this.listOfPlayersInSystem();

        if (playersInSystemInBattle.size() != 2) {
            throw new InvalidAmountOfPlayersException("System does not only contain 2 players. Space battle can't happen.");
        } else {
            //Each player must have at least one unit in order to make a space battle
            while (!(allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(0)).isEmpty() && allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(1)).isEmpty())) {
                //Each player gets a hit-value from rolling a 10-sided dice
                hitCountPlayer1 = hitCount(allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(0)));
                hitCountPlayer2 = hitCount(allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(1)));

                System.out.println("System contains " + getListOfShipsInSystem().size() + " units");

                System.out.println("Player 1 has " + allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(0)).size() + " units");
                System.out.println("Player 2 has " + allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(1)).size() + " units");

                System.out.println("Player 1 hits: " + hitCountPlayer1);
                System.out.println("Player 2 hits: " + hitCountPlayer2);

                //Depending on the amount of rolled hits each player gets to remove a corresponding amount of units from the other player
                removeUnitAfterHit(hitCountPlayer1, playersInSystemInBattle.get(1));
                removeUnitAfterHit(hitCountPlayer2, playersInSystemInBattle.get(0));

                System.out.println("Player 1 now has " + allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(0)).size() + " units");
                System.out.println("Player 2 now has " + allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(1)).size() + " units");

                System.out.println("System contains now " + getListOfShipsInSystem().size() + " units");
                System.out.println("------------------------------");

                if ((allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(0)).isEmpty() && allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(1)).isEmpty())) {
                    //Draw
                    return new Player("Both players units got eliminated", "System is therefore neutral", " ");
                } else if (allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(1)).isEmpty()) {
                    //Player 1 is the winning player
                    winningPlayer = playersInSystemInBattle.get(0);
                    return winningPlayer;
                } else if (allShipsOwnedByOnePlayerInSystem(playersInSystemInBattle.get(0)).isEmpty()) {
                    //Player 2 is the winning player
                    winningPlayer = playersInSystemInBattle.get(1);
                    return winningPlayer;
                }
                //If the outcome of a space battle leaves no ships left in the system, the system will remain neutral
            }
        }
        return new Player("Failure", "Failure", "Failure");
    }

    //Decides if a player gets a hit when rolling 10-sided dice
    public int hitCount(ArrayList<Unit> unitsOwnedByPlayer) {
        Random rnd = new Random();
        int diceRoll = rnd.nextInt(9) + 1;
        int hit = 0;
        //The rolled number must be greater than the units combat value in order to create a hit
        for (Unit unit : unitsOwnedByPlayer) {
            if (diceRoll >= unit.getCombatValue()) {
                hit++;
            }
        }
        return hit;
    }

    public void removeUnitAfterHit(int detectedHits, Player inputPlayer) {
        if (!allShipsOwnedByOnePlayerInSystem(inputPlayer).isEmpty()) {
            if (allShipsOwnedByOnePlayerInSystem(inputPlayer).size() >= detectedHits) {
                while (detectedHits > 0) {
                    //removes an amount of ships from the input player corresponding to the value of detectedHits (from the other player)
                    getListOfShipsInSystem().remove(allShipsOwnedByOnePlayerInSystem(inputPlayer).get(allShipsOwnedByOnePlayerInSystem(inputPlayer).size() - 1));
                    detectedHits--;
                }
            }
            //If the value of detectedHits are greater than the amount of units of a player all units will be removed
            else {
                getListOfShipsInSystem().removeAll(allShipsOwnedByOnePlayerInSystem(inputPlayer));
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
