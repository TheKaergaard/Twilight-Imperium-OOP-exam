package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;
import PackageImperium.Units.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Galaxy {
    HashMap<Point, SpaceSystem> hexagonalGridOfSystems; //TODO Bytte om på key og value, da keys ikke kan have duplikater
    ArrayList<SpaceSystem> listOfSystemsInGalaxy;
    ArrayList<Planet> listOfPlanetsInGalaxy;
    ArrayList<Unit> listOfUnitsInGalaxa;

    public Galaxy() {
    }

    public void setSystemsIntoGalaxy(Point inputPos, SpaceSystem inputSystem) {
        this.hexagonalGridOfSystems.put(inputPos, inputSystem);
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
            Point northPoint = new Point((int) temp.getX(), (int) temp.getY() + 1);
            Point northEastPoint = new Point((int) temp.getX() + 1, (int) temp.getY() + 1);
            Point southEastPoint = new Point((int) temp.getX() + 1, (int) temp.getY() - 1);
            Point southPoint = new Point((int) temp.getX(), (int) temp.getY() - 1);
            Point southWestPoint = new Point((int) temp.getX() - 1, (int) temp.getY() - 1);
            Point northWestPoint = new Point((int) temp.getX() - 1, (int) temp.getY() + 1);

            Boolean isNorth = hexagonalGridOfSystems.containsKey(northPoint);
            Boolean isNorthEast = hexagonalGridOfSystems.containsKey(northEastPoint);
            Boolean isSouthEast = hexagonalGridOfSystems.containsKey(southEastPoint);
            Boolean isSouth = hexagonalGridOfSystems.containsKey(southPoint);
            Boolean isSouthWest = hexagonalGridOfSystems.containsKey(southWestPoint);
            Boolean isNorthWest = hexagonalGridOfSystems.containsKey(northWestPoint);

            if (isNorth) {
                hexagonalGridOfSystems.get(temp).setHexagonGrid(SpaceSystem.Position.NORTH, hexagonalGridOfSystems.get(northPoint));
            } else if (isNorthEast) {
                hexagonalGridOfSystems.get(temp).setHexagonGrid(SpaceSystem.Position.NORTH_EAST, hexagonalGridOfSystems.get(northEastPoint));
            } else if (isSouthEast) {
                hexagonalGridOfSystems.get(temp).setHexagonGrid(SpaceSystem.Position.SOUTH_EAST, hexagonalGridOfSystems.get(southEastPoint));
            } else if (isSouth) {
                hexagonalGridOfSystems.get(temp).setHexagonGrid(SpaceSystem.Position.SOUTH, hexagonalGridOfSystems.get(southPoint));
            } else if (isSouthWest) {
                hexagonalGridOfSystems.get(temp).setHexagonGrid(SpaceSystem.Position.SOUTH_WEST, hexagonalGridOfSystems.get(southWestPoint));
            } else if (isNorthWest) {
                hexagonalGridOfSystems.get(temp).setHexagonGrid(SpaceSystem.Position.NORTH_WEST, hexagonalGridOfSystems.get(northWestPoint));
            }
        }
    }

    public Galaxy createGalaxyWithPlayers(ArrayList<Planet> planets, ArrayList<SpaceSystem> systems, ArrayList<Player> players) {
        ArrayList<Planet> planetsInGalaxy = planets;
        ArrayList<SpaceSystem> systemsInGalaxy = systems;

        Player p1 = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player p2 = new Player("Pompey", "The Federation of Sol", "Red");

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

        Planet metacolRex = new Planet("Metacol Rex", 6);
        Planet vegaMinor = new Planet("Vega Minor", 1);
        Planet vegaMajor = new Planet("Vega Major", 3);
        Planet industrex = new Planet("Industrex", 5);
        Planet rigelI = new Planet("Rigel I", 2);
        Planet rigelII = new Planet("Rigel II", 2);
        Planet mirage = new Planet("Mirage", 3);

        northSystem.listOfPlanetsInSystem.add(vegaMinor);
        northSystem.listOfPlanetsInSystem.add(vegaMajor);
        southEastSystem.listOfPlanetsInSystem.add(industrex);
        southSystem.listOfPlanetsInSystem.add(rigelI);
        southSystem.listOfPlanetsInSystem.add(rigelII);
        northWestSystem.listOfPlanetsInSystem.add(mirage);

        northSystem.listOfShipsInSystem.add(dreadnought01);
        northSystem.listOfShipsInSystem.add(dreadnought02);
        northSystem.listOfShipsInSystem.add(destroyer01);
        northSystem.listOfShipsInSystem.add(cruiser01);
        northSystem.listOfShipsInSystem.add(cruiser02);
        northSystem.listOfShipsInSystem.add(carrier01);

        Galaxy galaxy = new Galaxy();

        galaxy.setSystemsIntoGalaxy(new Point(0, 0), centerSystem);
        galaxy.setSystemsIntoGalaxy(new Point(0, 1), northSystem);
        galaxy.setSystemsIntoGalaxy(new Point(1, 1), northEastSystem);
        galaxy.setSystemsIntoGalaxy(new Point(1, -1), southEastSystem);
        galaxy.setSystemsIntoGalaxy(new Point(0, -1), southSystem);
        galaxy.setSystemsIntoGalaxy(new Point(-1, 1), southWestSystem);
        galaxy.setSystemsIntoGalaxy(new Point(-1, 1), northWestSystem);

        createHexagonalGridOfSystems(hexagonalGridOfSystems);

        return galaxy;
    }
}
