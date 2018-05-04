package TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.Dreadnought;
import PackageImperium.Units.Unit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpaceSystemTest {
    private SpaceSystem testSystem = new SpaceSystem();
    private Dreadnought testShip = new Dreadnought();
    private ArrayList<Unit> temp;

    @Test
    void whenAddingShipsToSystem() {
        testSystem.addEnteredShip(testShip);
        assertTrue(testSystem.allShipsInSystem().contains(testShip));
    }

    @Test
    void whenRemovingShipsFromSystem() {
        testSystem.addEnteredShip(testShip);
        testSystem.removeLeavedShip(testShip);
        assertFalse(testSystem.allShipsInSystem().contains(testShip));
    }

    @Test
    void allShipsInSystem () {
        testSystem.addEnteredShip(testShip);
        temp = testSystem.allShipsInSystem();
        assertEquals(temp.get(0).getUnitType(),Unit.Type.DREADNOUGHT);
    }
}
