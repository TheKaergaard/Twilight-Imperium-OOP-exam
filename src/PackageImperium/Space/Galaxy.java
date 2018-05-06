package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

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

    public ArrayList<SpaceSystem> listOfSystemsInGalaxy() {
        ArrayList<SpaceSystem> allSystemsInGalaxy = new ArrayList<>();
        allSystemsInGalaxy.addAll(this.hexagonalGridOfSystems.values());
        return allSystemsInGalaxy;
    }

    public ArrayList<Unit> listOfShipsInGalaxy () {
        ArrayList<SpaceSystem> systems = listOfSystemsInGalaxy();
        ArrayList<Unit> units = new ArrayList<>();
        for(SpaceSystem temp : systems) {
            units.addAll(temp.allShipsInSystem());
        }
        return units;
    }

    public ArrayList<Planet> listOfPlanetsInGalaxy () {
        ArrayList<Planet> planets = new ArrayList<>();
        for(SpaceSystem temp : this.listOfSystemsInGalaxy()) {
            planets.addAll(temp.listOfPlanetsInSystem);
        }
        return planets;
    }

    /*
            public Galaxy(ArrayList<Planet> listOfPlanetsInGalaxy, ArrayList<SpaceSystem> listOfSystemsInGalaxy, ArrayList<Player> playersInGalaxy) {
                if (listOfSystemsInGalaxy.size() > 7) {
                    throw new IndexOutOfBoundsException();
                } else {
                    this.listOfPlanetsInGalaxy = listOfPlanetsInGalaxy;
                }
                this.listOfSystemsInGalaxy = listOfSystemsInGalaxy;
                this.playersInGalaxy = playersInGalaxy;
            }
            */
    public void createHexagonalGridOfSystems(HashMap<Point, SpaceSystem> hexagonalGridOfSystems) {
        Set<Point> keySet = this.hexagonalGridOfSystems.keySet();

        for (Point temp : keySet) {
            Point northCoordinates = new Point((int) temp.getX(), (int) temp.getY() + 1);
            Point northEastCoordinates = new Point((int) temp.getX() + 1, (int) temp.getY() + 1);
            Point southEastCoordinates = new Point((int) temp.getX() + 1, (int) temp.getY() - 1);
            Point southCoordinates = new Point((int) temp.getX(), (int) temp.getY() - 1);
            Point southWestCoordinates = new Point((int) temp.getX() - 1, (int) temp.getY() - 1);
            Point northWestCoordinates = new Point((int) temp.getX() - 1, (int) temp.getY() + 1);

            Boolean isNorth = hexagonalGridOfSystems.containsKey(northCoordinates);
            Boolean isNorthEast = hexagonalGridOfSystems.containsKey(northEastCoordinates);
            Boolean isSouthEast = hexagonalGridOfSystems.containsKey(southEastCoordinates);
            Boolean isSouth = hexagonalGridOfSystems.containsKey(southCoordinates);
            Boolean isSouthWest = hexagonalGridOfSystems.containsKey(southWestCoordinates);
            Boolean isNorthWest = hexagonalGridOfSystems.containsKey(northWestCoordinates);

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

    public Galaxy createGalaxyWithPlayers() {
        Player p1 = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player p2 = new Player("Pompey", "The Federation of Sol", "Red");

        Galaxy galaxy = new Galaxy();

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

        galaxy.setSystemsIntoGalaxy(new Point(0, 0), centerSystem);
        galaxy.setSystemsIntoGalaxy(new Point(0, 1), northSystem);
        galaxy.setSystemsIntoGalaxy(new Point(1, 1), northEastSystem);
        galaxy.setSystemsIntoGalaxy(new Point(1, -1), southEastSystem);
        galaxy.setSystemsIntoGalaxy(new Point(0, -1), southSystem);
        galaxy.setSystemsIntoGalaxy(new Point(-1, 1), southWestSystem);
        galaxy.setSystemsIntoGalaxy(new Point(-1, 1), northWestSystem);

        galaxy.createHexagonalGridOfSystems(galaxy.getHexagonalGridOfSystems());

        return galaxy;
    }

    public void propertiesForLegalGalaxy(Galaxy legalGalaxy) {
        HashMap<Point, SpaceSystem> legalHexagonalGrid = legalGalaxy.getHexagonalGridOfSystems();
        Set<Point> keyset = legalGalaxy.getHexagonalGridOfSystems().keySet();
        Set<SpaceSystem> values = new HashSet<>(legalHexagonalGrid.values());

        //Every system has at most three planets
        for (Point temp : keyset) {
            if (legalHexagonalGrid.get(temp).listOfPlanetsInSystem.size() > 3) {
                try {
                    throw new IndexOutOfBoundsException("Too many planets in system");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //TODO make sure only one planet in Center system
            //The center system must have exactly one planet named Mecatol Rex
            if (!temp.equals(new Point(0, 0)) && legalHexagonalGrid.get(temp).listOfPlanetsInSystem.get(0).planetName.equals("Mecatol Rex")) {
                try {
                    throw new Exception("Planet Metacol Rex does not exist in center system (0,0)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (Planet tempPlanet : legalHexagonalGrid.get(temp).listOfPlanetsInSystem) {
                    int frequency = Collections.frequency(values, tempPlanet);
                    if (frequency > 1) {
                        try {
                            throw new Exception("Planet" + tempPlanet + "occours in multiple Systems");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


            //for (int i = 0; i < legalGalaxy.getHexagonalGridOfSystems().get(temp).listOfPlanetsInSystem.size(); i++) {
                /*if (!legalGalaxy.getHexagonalGridOfSystems().get(temp).listOfPlanetsInSystem.get(lol).planetName.equals("Mecatol Rex")) {
                throw new IllegalStateException("Center system does not contain Mecatol Rex planet");
                */
        }
    }
}



