package TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.Dreadnought;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpaceSystemTest {
    private SpaceSystem testSystem = new SpaceSystem();
    private Dreadnought testShip = new Dreadnought();

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
}
