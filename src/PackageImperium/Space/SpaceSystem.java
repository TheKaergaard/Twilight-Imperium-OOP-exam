package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Units.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SpaceSystem {
    Position position;
    ArrayList<Planet> listOfPlanetsInSystem;
    ArrayList<Unit> listOfShipsInSystem;
    HashMap<Position,SpaceSystem> hexagonGrid = new HashMap<>();

    enum Position {
        NORTH, NORTH_EAST, NORTH_WEST, SOUTH, SOUTH_EAST, SOUTH_WEST
    }

    public SpaceSystem() {
    }

    public void setListOfPlanetsInSystem(ArrayList<Planet> listOfPlanetsInSystem) {
        this.listOfPlanetsInSystem = listOfPlanetsInSystem;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    //Constructor for System. Max check for amount of planets
    public SpaceSystem(Position position, ArrayList<Planet> listOfPlanetsInSystem) {
        this.position = position;
        if (listOfPlanetsInSystem.size() < 3) {
            throw new IndexOutOfBoundsException();
        } else {
            this.listOfPlanetsInSystem = listOfPlanetsInSystem;
        }
    }
    //Returning all ships in the system as ArrayList
    public ArrayList<Unit> allShipsInSystem() {
        ArrayList<Unit> allShipsInSystem = new ArrayList<>();
        for(Unit temp : listOfShipsInSystem) {
            allShipsInSystem.add(temp);
        }
        return allShipsInSystem;
    }

    public void setHexagonGrid(Position tempPos, SpaceSystem tempSystem) {
        this.hexagonGrid.put(tempPos,tempSystem);
    }
}
