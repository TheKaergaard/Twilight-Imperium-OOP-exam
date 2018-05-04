package PackageImperium.Space;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Units.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SpaceSystem {
    private Position position;
    ArrayList<Planet> listOfPlanetsInSystem = new ArrayList<>();
    ArrayList<Unit> listOfShipsInSystem = new ArrayList<>();
    HashMap<Position, SpaceSystem> hexagonalGrid = new HashMap<>();

    enum Position {
        NORTH, NORTH_EAST, NORTH_WEST, SOUTH, SOUTH_EAST, SOUTH_WEST
    }

    public SpaceSystem() {
    }

    public SpaceSystem(Position tempPos, SpaceSystem tempSystem) {
        this.hexagonalGrid.put(tempPos, tempSystem);
    }

    public void setListOfPlanetsInSystem(ArrayList<Planet> listOfPlanetsInSystem) {
        this.listOfPlanetsInSystem = listOfPlanetsInSystem;
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

    public void setHexagonalGrid(Position tempPos, SpaceSystem tempSystem) {
        this.hexagonalGrid.put(tempPos, tempSystem);
    }

    public Position getPosition() {
        return position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceSystem that = (SpaceSystem) o;
        return Objects.equals(hexagonalGrid, that.hexagonalGrid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hexagonalGrid);
    }
}
