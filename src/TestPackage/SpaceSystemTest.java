package TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.Carrier;
import PackageImperium.Units.Cruiser;
import PackageImperium.Units.Dreadnought;
import PackageImperium.Units.Unit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpaceSystemTest {
    private SpaceSystem testSystem = new SpaceSystem();
    private Dreadnought testShip = new Dreadnought();
    private Carrier testShip02 = new Carrier();
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
    void whenGettingAllShipsInSystem() {
        testSystem.addEnteredShip(testShip);
        testSystem.addEnteredShip(testShip02);
        temp = testSystem.allShipsInSystem();
        assertEquals(temp.get(0).getUnitType(),Unit.Type.DREADNOUGHT);
        assertEquals(temp.get(1).getUnitType(),Unit.Type.CARRIER);
    }

    @Test
    void whenCreatingASpaceBattleFindTheWinningPlayer01() {
        Player p1 = new Player("player1", "race1", "RED");
        Player p2 = new Player("player2", "race2", "BLUE");

        SpaceSystem testSystem01 = new SpaceSystem();

        Dreadnought d1 = new Dreadnought(p1);
        Cruiser c1 = new Cruiser(p1);

        Dreadnought d2 = new Dreadnought(p2);
        Cruiser c2 = new Cruiser(p2);

        testSystem01.addEnteredShip(d1);
        testSystem01.addEnteredShip(c1);

        testSystem01.addEnteredShip(d2);
        testSystem01.addEnteredShip(c2);

        ArrayList<Unit> test = testSystem01.allShipsOwnedByOnePlayerInSystem(p1);

        //When the value of detected hits is one it should therefore remove one unit
        testSystem01.removeUnitAfterHit(1, p1);
        //Each player has two units and when removing one, the size should be 1
        assertEquals(1, testSystem01.allShipsOwnedByOnePlayerInSystem(p1).size());
    }

    @Test
    void whenCreatingASpaceBattleFindTheWinningPlayer02() {
        Player p1 = new Player("player1", "race1", "RED");
        Player p2 = new Player("player2", "race2", "BLUE");

        SpaceSystem testSystem01 = new SpaceSystem();

        Dreadnought d1 = new Dreadnought(p1);
        Cruiser c1 = new Cruiser(p1);

        Dreadnought d2 = new Dreadnought(p2);
        Cruiser c2 = new Cruiser(p2);

        testSystem01.addEnteredShip(d1);
        testSystem01.addEnteredShip(c1);

        testSystem01.addEnteredShip(d2);
        testSystem01.addEnteredShip(c2);

        ArrayList<Unit> test = testSystem01.allShipsOwnedByOnePlayerInSystem(p1);

    }
}
