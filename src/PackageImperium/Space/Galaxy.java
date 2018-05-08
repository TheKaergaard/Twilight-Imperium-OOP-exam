package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.CustomException01;
import PackageImperium.Player;
import PackageImperium.Units.*;

import java.awt.*;
import java.util.*;

public class Galaxy {
    HashMap<Point, SpaceSystem> hexagonalGridOfSystems = new HashMap<>();

    public Galaxy() {
    }

    public void setSystemsIntoGalaxy(Point inputPos, SpaceSystem inputSystem) {
        this.hexagonalGridOfSystems.put(inputPos, inputSystem);
    }

    public HashMap<Point, SpaceSystem> getHexagonalGridOfSystems() {
        return hexagonalGridOfSystems;
    }

    //Returning an ArrayList of all units/ships in the given galaxy
    public ArrayList<Unit> listOfShipsInGalaxy() {
        ArrayList<SpaceSystem> systems = listOfSystemsInGalaxy();
        ArrayList<Unit> units = new ArrayList<>();
        for (SpaceSystem temp : systems) {
            units.addAll(temp.allShipsInSystem());
        }
        return units;
    }

    //Returning an ArrayList of all planets in the given galaxy
    public ArrayList<Planet> listOfPlanetsInGalaxy() {
        ArrayList<Planet> planets = new ArrayList<>();
        for (SpaceSystem temp : this.listOfSystemsInGalaxy()) {
            planets.addAll(temp.listOfPlanetsInSystem);
        }
        return planets;
    }

    //Returning an ArrayList of all systems in the given galaxy
    public ArrayList<SpaceSystem> listOfSystemsInGalaxy() {
        ArrayList<SpaceSystem> allSystemsInGalaxy = new ArrayList<>();
        allSystemsInGalaxy.addAll(this.hexagonalGridOfSystems.values());
        return allSystemsInGalaxy;
    }
    /*
     * This method takes in the HashMap of a created Galaxy and puts the contained systems into a hexagonal grid
     * with a center system surrounded by 6 other systems.
     * Each systems placement is defined by their correspondence to the center system with coordinates (0,0)
     */
    public void createHexagonalGridOfSystems(HashMap<Point, SpaceSystem> hexagonalGridOfSystems) {
        Set<Point> keySet = this.hexagonalGridOfSystems.keySet();

        //Goes through all the systems keyset Point(x,y)
        for (Point temp : keySet) {
            //Predefined coordinates (x,y) for system placement correspondence
            Point northCoordinates = new Point((int) temp.getX(), (int) temp.getY() + 1);
            Point northEastCoordinates = new Point((int) temp.getX() + 1, (int) temp.getY() + 1);
            Point southEastCoordinates = new Point((int) temp.getX() + 1, (int) temp.getY());
            Point southCoordinates = new Point((int) temp.getX(), (int) temp.getY() - 1);
            Point southWestCoordinates = new Point((int) temp.getX() - 1, (int) temp.getY() - 1);
            Point northWestCoordinates = new Point((int) temp.getX() - 1, (int) temp.getY());

            //Looks at the coordinates of the given system and defines its placement
            Boolean isNorth = hexagonalGridOfSystems.containsKey(northCoordinates);
            Boolean isNorthEast = hexagonalGridOfSystems.containsKey(northEastCoordinates);
            Boolean isSouthEast = hexagonalGridOfSystems.containsKey(southEastCoordinates);
            Boolean isSouth = hexagonalGridOfSystems.containsKey(southCoordinates);
            Boolean isSouthWest = hexagonalGridOfSystems.containsKey(southWestCoordinates);
            Boolean isNorthWest = hexagonalGridOfSystems.containsKey(northWestCoordinates);

            //The placement information are now put into each system.
            if (isNorth) {
                hexagonalGridOfSystems.get(temp).setHexagonalGrid(SpaceSystem.Position.NORTH, hexagonalGridOfSystems.get(northCoordinates));
            }
            if (isNorthEast) {
                hexagonalGridOfSystems.get(temp).setHexagonalGrid(SpaceSystem.Position.NORTH_EAST, hexagonalGridOfSystems.get(northEastCoordinates));
            }
            if (isSouthEast) {
                hexagonalGridOfSystems.get(temp).setHexagonalGrid(SpaceSystem.Position.SOUTH_EAST, hexagonalGridOfSystems.get(southEastCoordinates));
            }
            if (isSouth) {
                hexagonalGridOfSystems.get(temp).setHexagonalGrid(SpaceSystem.Position.SOUTH, hexagonalGridOfSystems.get(southCoordinates));
            }
            if (isSouthWest) {
                hexagonalGridOfSystems.get(temp).setHexagonalGrid(SpaceSystem.Position.SOUTH_WEST, hexagonalGridOfSystems.get(southWestCoordinates));
            }
            if (isNorthWest) {
                hexagonalGridOfSystems.get(temp).setHexagonalGrid(SpaceSystem.Position.NORTH_WEST, hexagonalGridOfSystems.get(northWestCoordinates));
            }
        }
    }

    public Galaxy creationOfGalaxyWithPredefinedConfiguration() {
        Player p1 = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player p2 = new Player("Pompey", "The Federation of Sol", "Red");

        Galaxy predefinedGalaxy = new Galaxy();

        SpaceSystem centerSystem = new SpaceSystem();
        SpaceSystem northSystem = new SpaceSystem();
        SpaceSystem northEastSystem = new SpaceSystem();
        SpaceSystem southEastSystem = new SpaceSystem();
        SpaceSystem southSystem = new SpaceSystem();
        SpaceSystem southWestSystem = new SpaceSystem();
        SpaceSystem northWestSystem = new SpaceSystem();

        Dreadnought dreadnought01 = new Dreadnought(p1);
        Dreadnought dreadnought02 = new Dreadnought(p1);
        Destroyer destroyer01 = new Destroyer(p1);
        Cruiser cruiser01 = new Cruiser(p2);
        Cruiser cruiser02 = new Cruiser(p2);
        Carrier carrier01 = new Carrier(p2);

        Planet mecatolRex = new Planet("Mecatol Rex", 6);
        Planet vegaMinor = new Planet("Vega Minor", 1);
        Planet vegaMajor = new Planet("Vega Major", 3);
        Planet industrex = new Planet("Industrex", 5);
        Planet rigelI = new Planet("Rigel I", 2);
        Planet rigelII = new Planet("Rigel II", 2);
        Planet mirage = new Planet("Mirage", 3);

        centerSystem.listOfPlanetsInSystem.add(mecatolRex);
        northSystem.listOfPlanetsInSystem.add(vegaMinor);
        northSystem.listOfPlanetsInSystem.add(vegaMajor);
        southEastSystem.listOfPlanetsInSystem.add(industrex);
        southSystem.listOfPlanetsInSystem.add(rigelI);
        southSystem.listOfPlanetsInSystem.add(rigelII);
        northWestSystem.listOfPlanetsInSystem.add(mirage);

        centerSystem.listOfShipsInSystem.add(dreadnought01);
        centerSystem.listOfShipsInSystem.add(dreadnought02);
        centerSystem.listOfShipsInSystem.add(destroyer01);
        northSystem.listOfShipsInSystem.add(cruiser01);
        northSystem.listOfShipsInSystem.add(cruiser02);
        northSystem.listOfShipsInSystem.add(carrier01);

        predefinedGalaxy.setSystemsIntoGalaxy(new Point(0, 0), centerSystem);
        predefinedGalaxy.setSystemsIntoGalaxy(new Point(0, 1), northSystem);
        predefinedGalaxy.setSystemsIntoGalaxy(new Point(1, 1), northEastSystem);
        predefinedGalaxy.setSystemsIntoGalaxy(new Point(1, 0), southEastSystem);
        predefinedGalaxy.setSystemsIntoGalaxy(new Point(0, -1), southSystem);
        predefinedGalaxy.setSystemsIntoGalaxy(new Point(-1, -1), southWestSystem);
        predefinedGalaxy.setSystemsIntoGalaxy(new Point(-1, 0), northWestSystem);

        //Putting the above created systems into hexagonal grid by coordinates
        predefinedGalaxy.createHexagonalGridOfSystems(predefinedGalaxy.getHexagonalGridOfSystems());

        return predefinedGalaxy;
    }

    public boolean checkForPropertiesOfLegalGalaxy() {
        HashMap<Point, SpaceSystem> legalHexagonalGrid = this.getHexagonalGridOfSystems();
        Set<Point> keyset = this.getHexagonalGridOfSystems().keySet();
        HashSet<Planet> noDuplicateSet = new HashSet<>();

        for (Point temp : keyset) {
            //Checks if every system has at most three planets
            if (legalHexagonalGrid.get(temp).listOfPlanetsInSystem.size() > 3) {
                try {
                    throw new IndexOutOfBoundsException("Too many planets in system");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //Checks if the center system has 6 neighbor systems and therefore is a galaxy
            if (legalHexagonalGrid.get(temp).getHexagonalGrid().size() == 6) {
            //Checks if the center system only contains exactly one planet named Mecatol Rex
                if (!((legalHexagonalGrid.get(temp).listOfPlanetsInSystem.size() == 1) && (legalHexagonalGrid.get(temp).listOfPlanetsInSystem.get(0).getPlanetName().equals("Mecatol Rex")))) {
                    try {
                        throw new CustomException01("Center system contains other planets than Mecatol Rex");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //Insures that no duplicate planets can occur as HashSet can't contain duplicates
        for(Planet temp : this.listOfPlanetsInGalaxy()) {
            if(!noDuplicateSet.add(temp)) {
                try {
                    throw new CustomException01("Planet " + temp.getPlanetName() + " occurs multiple times");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public Player generateRandomPlayer() {
        Player randomPlayer = new Player();
        Random rnd = new Random();
        int amountOfPlayers = rnd.nextInt(4) + 2;

        //Creates a random player with a number between 2-6
        for (int i = 0; i < amountOfPlayers; i++) {
            randomPlayer.setPlayerName("Player" + i);
            randomPlayer.setUniqueRace("Race" + i);
            randomPlayer.setUniqueColour("Colour" + i);
        }
        return randomPlayer;
    }

    public ArrayList<Planet> generateRandomPlanets() {
        ArrayList<Planet> listOfRandomPlanets = new ArrayList<>();
        Random rnd = new Random();
        int amountOfPlanets = rnd.nextInt(3) + 1;

        //Generates 1-3 random planets. Every planet has a random resource production between 0 and 6.
        for (int i = 0; i < amountOfPlanets; i++) {
            int resourceProduction = rnd.nextInt(6);
            Planet temp = new Planet("Planet" + i, resourceProduction);
            listOfRandomPlanets.add(temp);
        }
        return listOfRandomPlanets;
    }

    public SpaceSystem generateRandomSystem() {
        SpaceSystem randomSystem = new SpaceSystem();
        randomSystem.listOfPlanetsInSystem.addAll(generateRandomPlanets());
        return randomSystem;
    }


    public ArrayList<Unit> generateRandomUnits() {
        ArrayList<Unit> listOfRandomUnits = new ArrayList<>();
        Random rnd = new Random();
        //+1 secures at least one unit per player
        int amountOfUnits = rnd.nextInt(5) + 1;

        //Generates 0-6 random units each owned by a random generated player
        for (int i = 0; i < amountOfUnits; i++) {
            int unitToCreate = rnd.nextInt(4);
            switch (unitToCreate) {
                case 0:
                    Destroyer destroyer = new Destroyer(generateRandomPlayer());
                    listOfRandomUnits.add(destroyer);
                    break;
                case 1:
                    Cruiser cruiser = new Cruiser(generateRandomPlayer());
                    listOfRandomUnits.add(cruiser);
                    break;
                case 2:
                    Carrier carrier = new Carrier(generateRandomPlayer());
                    listOfRandomUnits.add(carrier);
                    break;
                case 3:
                    Dreadnought dreadnought = new Dreadnought(generateRandomPlayer());
                    listOfRandomUnits.add(dreadnought);
                    break;
            }
        }
        return listOfRandomUnits;
    }

    public Galaxy constructRandomGalaxy() {
        Galaxy randomGalaxy = new Galaxy();

        //Adding Mecatol Rex planet manually to center system (0,0)
        SpaceSystem centerSystem = new SpaceSystem();
        Planet mecatolRex = new Planet("Mecatol Rex", 6);
        centerSystem.listOfPlanetsInSystem.add(mecatolRex);

        /*
        * Generating the random systems for the galaxy.
        * Every system contains between 0-3 random generated planets.
        *
        */

        SpaceSystem randomSystem01 = generateRandomSystem();
        SpaceSystem randomSystem02 = generateRandomSystem();
        SpaceSystem randomSystem03 = generateRandomSystem();
        SpaceSystem randomSystem04 = generateRandomSystem();
        SpaceSystem randomSystem05 = generateRandomSystem();
        SpaceSystem randomSystem06 = generateRandomSystem();

        //Adding all random generated systems to Galaxy HashMap
        randomGalaxy.setSystemsIntoGalaxy(new Point(0, 0), centerSystem);
        randomGalaxy.setSystemsIntoGalaxy(new Point(0, 1), randomSystem01);
        randomGalaxy.setSystemsIntoGalaxy(new Point(1, 1), randomSystem02);
        randomGalaxy.setSystemsIntoGalaxy(new Point(1, -1), randomSystem03);
        randomGalaxy.setSystemsIntoGalaxy(new Point(0, -1), randomSystem04);
        randomGalaxy.setSystemsIntoGalaxy(new Point(-1, -1), randomSystem05);
        randomGalaxy.setSystemsIntoGalaxy(new Point(-1, 1), randomSystem06);

        randomGalaxy.createHexagonalGridOfSystems(randomGalaxy.getHexagonalGridOfSystems());

        for(SpaceSystem temp : randomGalaxy.listOfSystemsInGalaxy()) {
            temp.allShipsInSystem().addAll(generateRandomUnits());
        }

        return randomGalaxy;
    }
}



