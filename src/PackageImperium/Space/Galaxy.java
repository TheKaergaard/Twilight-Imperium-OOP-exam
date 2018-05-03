package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;
import PackageImperium.Units.Dreadnought;
import PackageImperium.Units.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Galaxy {
    HashMap<SpaceSystem, Point> hexagonalGridOfSystems; //TODO Bytte om på key og value, da keys ikke kan have duplikater
    ArrayList<SpaceSystem> listOfSystemsInGalaxy;
    ArrayList<Planet> listOfPlanetsInGalaxy;
    ArrayList<Unit> listOfUnitsInGalaxa;
    ArrayList<Player> playersInGalaxy;

    public Galaxy() {
    }

    public void setSystemsIntoGalaxy (SpaceSystem inputSystem, Point inputPos) {
        this.hexagonalGridOfSystems.put(inputSystem, inputPos);
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
    public void createHexagonalGridOfSystems(HashMap<SpaceSystem, Point> hexagonalGridOfSystems){
        Set<SpaceSystem> keySet = this.hexagonalGridOfSystems.keySet();

        for(SpaceSystem temp : keySet){
            Point northPoint = new Point((int)hexagonalGridOfSystems.get(temp).getX(),(int)hexagonalGridOfSystems.get(temp).getY() + 1);
            Point northEastPoint = new Point((int)hexagonalGridOfSystems.get(temp).getX()+1,(int)hexagonalGridOfSystems.get(temp).getY()+1);
            Point southEastPoint = new Point((int)hexagonalGridOfSystems.get(temp).getX()+1,(int)hexagonalGridOfSystems.get(temp).getY()-1);
            Point southPoint = new Point((int)hexagonalGridOfSystems.get(temp).getX(),(int)hexagonalGridOfSystems.get(temp).getY() -1);
            Point southWestPoint = new Point((int)hexagonalGridOfSystems.get(temp).getX()-1,(int)hexagonalGridOfSystems.get(temp).getY()-1);
            Point northWestPoint = new Point((int)hexagonalGridOfSystems.get(temp).getX()-1,(int)hexagonalGridOfSystems.get(temp).getY() + 1);

            Boolean isNorth = hexagonalGridOfSystems.containsValue(northPoint);
            Boolean isNorthEast = hexagonalGridOfSystems.containsValue(northEastPoint);
            Boolean isSouthEast = hexagonalGridOfSystems.containsValue(southEastPoint);
            Boolean isSouth = hexagonalGridOfSystems.containsValue(southPoint);
            Boolean isSouthWest = hexagonalGridOfSystems.containsValue(southWestPoint);
            Boolean isNorthWest = hexagonalGridOfSystems.containsValue(northWestPoint);

            SpaceSystem northernSystem = new SpaceSystem();
            SpaceSystem northEastSystem = new SpaceSystem();
            SpaceSystem southEastSystem = new SpaceSystem();
            SpaceSystem southSystem = new SpaceSystem();
            SpaceSystem southWestSystem = new SpaceSystem();
            SpaceSystem northWestSystem = new SpaceSystem();

            for(SpaceSystem tempSystem : keySet){
                if(hexagonalGridOfSystems.get(tempSystem).equals(northPoint)){
                    northernSystem = tempSystem;
                }else if(hexagonalGridOfSystems.get(tempSystem).equals(northEastPoint)){
                    northEastSystem = tempSystem;
                }else if(hexagonalGridOfSystems.get(tempSystem).equals(southEastPoint)){
                    southEastSystem = tempSystem;
                }else if(hexagonalGridOfSystems.get(tempSystem).equals(southPoint)){
                    southSystem = tempSystem;
                } else if(hexagonalGridOfSystems.get(tempSystem).equals(southWestPoint)){
                    southWestSystem = tempSystem;
                }else if(hexagonalGridOfSystems.get(tempSystem).equals(northWestPoint)){
                    northWestSystem = tempSystem;
                }
            }
            if(isNorth){
                temp.setHexagonGrid(SpaceSystem.Position.NORTH, northernSystem);
            }else if(isNorthEast){
                temp.setHexagonGrid(SpaceSystem.Position.NORTH_EAST, northEastSystem);
            }else if(isSouthEast){
                temp.setHexagonGrid(SpaceSystem.Position.SOUTH_EAST, southEastSystem);
            }else if(isSouth){
                temp.setHexagonGrid(SpaceSystem.Position.SOUTH, southSystem);
            }else if(isSouthWest){
                temp.setHexagonGrid(SpaceSystem.Position.SOUTH_WEST, southWestSystem);
            }else if(isNorthWest){
                temp.setHexagonGrid(SpaceSystem.Position.NORTH_WEST, northWestSystem);
            }
        }
    }

    public Galaxy createGalaxyWithPlayers (ArrayList<Planet> planets, ArrayList<SpaceSystem> systems, ArrayList<Player> players) {
        ArrayList<Planet> planetsInGalaxy = planets;
        ArrayList<SpaceSystem> systemsInGalaxy = systems;
        ArrayList<Player> playersInGalaxy = players;

        Planet metacolRex = new Planet("Metacol Rex", 6);
        Planet vegaMinor = new Planet("Vega Minor", 1);
        Planet vegaMajor = new Planet("Vega Major", 3);
        Planet industrex = new Planet("Industrex", 5);
        Planet rigelI = new Planet("Rigel I", 2);
        Planet rigelII = new Planet("Rigel II", 2);
        Planet mirage = new Planet("Mirage", 3);

        SpaceSystem centerSystem = new SpaceSystem();
        SpaceSystem northSystem = new SpaceSystem();
        SpaceSystem northEastSystem = new SpaceSystem();
        SpaceSystem southEastSystem = new SpaceSystem();
        SpaceSystem southSystem = new SpaceSystem();
        SpaceSystem southWestSystem = new SpaceSystem();
        SpaceSystem northWestSystem = new SpaceSystem();

        northSystem.listOfPlanetsInSystem.add(vegaMinor);
        northSystem.listOfPlanetsInSystem.add(vegaMajor);
        southEastSystem.listOfPlanetsInSystem.add(industrex);
        southSystem.listOfPlanetsInSystem.add(rigelI);
        southSystem.listOfPlanetsInSystem.add(rigelII);
        northWestSystem.listOfPlanetsInSystem.add(mirage);

        Galaxy galaxy = new Galaxy();

        galaxy.setSystemsIntoGalaxy(centerSystem, new Point(0,0));
        galaxy.setSystemsIntoGalaxy(northSystem, new Point(0,1));
        galaxy.setSystemsIntoGalaxy(northEastSystem, new Point(1,1));
        galaxy.setSystemsIntoGalaxy(southEastSystem, new Point(1,-1));
        galaxy.setSystemsIntoGalaxy(southSystem, new Point(0,-1));
        galaxy.setSystemsIntoGalaxy(southWestSystem, new Point(-1,1));
        galaxy.setSystemsIntoGalaxy(northWestSystem, new Point(-1,1));

        createHexagonalGridOfSystems(hexagonalGridOfSystems);

        return galaxy;
    }
}
