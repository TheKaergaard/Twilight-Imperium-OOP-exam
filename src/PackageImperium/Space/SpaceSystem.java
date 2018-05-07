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
import java.util.Objects;
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

    public SpaceSystem(Position tempPos, SpaceSystem tempSystem) {
        this.hexagonalGrid.put(tempPos, tempSystem);
    }

    public void setListOfPlanetsInSystem(ArrayList<Planet> listOfPlanetsInSystem) {
        if (listOfPlanetsInSystem.size() < 3) {
            throw new IndexOutOfBoundsException();
        } else {
            this.listOfPlanetsInSystem = listOfPlanetsInSystem;
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setHexagonalGrid(Position tempPos, SpaceSystem tempSystem) {
        this.hexagonalGrid.put(tempPos, tempSystem);
    }

    public Position getPosition() {
        return position;
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
        System.out.println(shipToAdd + "added to system");
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

    public Player createRandomPlayer() {
        Player randomPlayer = new Player();
        Random rnd = new Random();
        int amountOfPlayers = rnd.nextInt(4) + 2;
        //Creates a random player between 2-6
        for (int i = 0; i < amountOfPlayers; i++) {
            randomPlayer.setPlayerName("Player" + i);
            randomPlayer.setUniqueRace("Race" + i);
            randomPlayer.setUniqueColour("Colour" + i);
        }
        return randomPlayer;
    }

    public ArrayList<Unit> generateRandomUnits() {
        ArrayList<Unit> listOfRandomUnits = new ArrayList<>();
        Random rnd = new Random();
        //+1 secures at least one unit per player
        int amountOfUnits = rnd.nextInt(5)+1;

        for (int i = 0; i < amountOfUnits; i++) {
            int unitToCreate = rnd.nextInt(4);
            switch (unitToCreate) {
                case 0:
                    Destroyer destroyer = new Destroyer(createRandomPlayer());
                    listOfRandomUnits.add(destroyer);
                    break;
                case 1:
                    Cruiser cruiser = new Cruiser(createRandomPlayer());
                    listOfRandomUnits.add(cruiser);
                    break;
                case 2:
                    Carrier carrier = new Carrier(createRandomPlayer());
                    listOfRandomUnits.add(carrier);
                    break;
                case 3:
                    Dreadnought dreadnought = new Dreadnought(createRandomPlayer());
                    listOfRandomUnits.add(dreadnought);
                    break;
            }
        }
        return listOfRandomUnits;
    }

    public ArrayList<Planet> generateRandomPlanets() {
        ArrayList<Planet> listOfRandomPlanets = new ArrayList<>();
        Random rnd = new Random();
        int amountOfPlanets = rnd.nextInt(3)+1;


        for (int i = 0; i < amountOfPlanets; i++) {
            int resourceProduction = rnd.nextInt(6);
            Planet temp = new Planet("Planet" + i, resourceProduction);
            listOfRandomPlanets.add(temp);
        }
        return listOfRandomPlanets;
    }

    public SpaceSystem generateRandomSysten() {
        SpaceSystem randomSystem = new SpaceSystem();
        randomSystem.allShipsInSystem().addAll(generateRandomUnits());
        randomSystem.listOfPlanetsInSystem.addAll(generateRandomPlanets());
        return randomSystem;
    }

}
