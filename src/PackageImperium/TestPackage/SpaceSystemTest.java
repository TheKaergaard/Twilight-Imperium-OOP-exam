package PackageImperium.TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.CustomComparators.CustomUnitResourceCostComparator;
import PackageImperium.Player;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpaceSystemTest {
    private SpaceSystem testSystem = new SpaceSystem();

    private Player p1 = new Player("player1", "race1", "RED");
    private Player p2 = new Player("player2", "race2", "BLUE");

    private Dreadnought d1 = new Dreadnought(p1);
    private Cruiser c1 = new Cruiser(p1);
    private Dreadnought d2 = new Dreadnought(p2);
    private Cruiser c2 = new Cruiser(p2);

    @Test
    void whenGettingListOfPlayersInSystem() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        assertEquals(2, testSystem.listOfPlayersInSystem().size());
        assertEquals(p1, testSystem.listOfPlayersInSystem().get(0));
        assertEquals(p2, testSystem.listOfPlayersInSystem().get(1));
    }

    @Test
    void whenAddingShipsToSystem() {
        testSystem.addShipToSystem(d1);
        assertTrue(testSystem.getListOfShipsInSystem().contains(d1));
        assertEquals(1, testSystem.listOfShipsInSystem.size());
    }

    @Test
    void whenRemovingShipsFromSystem() {
        testSystem.addShipToSystem(d1);
        testSystem.removeShipFromSystem(d1);
        assertTrue(!testSystem.getListOfShipsInSystem().contains(d1));
    }

    @Test
    void whenGettingAllShipsInSystem() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);

        assertEquals(Unit.Type.DREADNOUGHT, testSystem.getListOfShipsInSystem().get(0).getUnitType());
        assertEquals(Unit.Type.CRUISER, testSystem.getListOfShipsInSystem().get(1).getUnitType());
        assertEquals(2, testSystem.listOfShipsInSystem.size());
    }

    @Test
    void whenGettingAllShipsOwnedByOnePlayerInSystem() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        ArrayList<Unit> shipsOwnedByOnePlayer = testSystem.allShipsOwnedByOnePlayerInSystem(p1);

        assertEquals(2, shipsOwnedByOnePlayer.size());
        assertEquals(d1, shipsOwnedByOnePlayer.get(0));
        assertEquals(c1, shipsOwnedByOnePlayer.get(1));
    }

    @Test
    void whenSortingUnitsAfterResourceCost() {
        Destroyer des = new Destroyer();
        Cruiser cru = new Cruiser();
        Carrier car = new Carrier();
        Dreadnought dre = new Dreadnought();
        //Units added in reverse order
        testSystem.addShipToSystem(des);
        testSystem.addShipToSystem(cru);
        testSystem.addShipToSystem(car);
        testSystem.addShipToSystem(dre);

        ArrayList<Unit> listToBeSorted = testSystem.listOfShipsInSystem;
        listToBeSorted.sort(new CustomUnitResourceCostComparator());
        //Should be: dre, car, cru, des
        assertEquals(dre,listToBeSorted.get(0));
        assertEquals(car,listToBeSorted.get(1));
        assertEquals(cru,listToBeSorted.get(2));
        assertEquals(des,listToBeSorted.get(3));
    }

    @Test
    void whenGivingAHitValueItShouldRemoveThatManyUnits() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        //When the value of detected hits is one it should therefore remove one unit
        testSystem.removeUnitAfterHit(1, p1);
        testSystem.removeUnitAfterHit(2, p2);
        //Each player has two units and when removing one, the size should be 1
        assertEquals(1, testSystem.allShipsOwnedByOnePlayerInSystem(p1).size());
        assertEquals(0, testSystem.allShipsOwnedByOnePlayerInSystem(p2).size());
    }

    @Test
    void simulationOfSpaceBattleBetweenTwoPlayersWithTwoShipsEach() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        System.out.println("The winning player of the space battle is:\n\t"+testSystem.playerWhoWonSpaceBattle().toString());
    }
}
