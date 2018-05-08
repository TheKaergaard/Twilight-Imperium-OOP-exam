package PackageImperium.TestPackage;
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

    private Player p1 = new Player("player1", "race1", "RED");
    private Player p2 = new Player("player2", "race2", "BLUE");

    private Dreadnought d1 = new Dreadnought(p1);
    private Cruiser c1 = new Cruiser(p1);
    private Dreadnought d2 = new Dreadnought(p2);
    private Cruiser c2 = new Cruiser(p2);

    @Test
    void whenAddingShipsToSystem() {
        testSystem.addShipToSystem(d1);
        assertTrue(testSystem.getListOfShipsInSystem().contains(d1));
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
        ArrayList<Unit> temp = testSystem.getListOfShipsInSystem();
        assertEquals(Unit.Type.DREADNOUGHT, temp.get(0).getUnitType());
        assertEquals(Unit.Type.CRUISER, temp.get(1).getUnitType());
    }

    @Test
    void whenGettingAllPlayersInSystem() {
        SpaceSystem testSystem = new SpaceSystem();

        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        ArrayList<Player> listOfPlayersInSystem = testSystem.listOfPlayersInSystem();

        assertEquals(2, listOfPlayersInSystem.size());
        assertEquals(p1, listOfPlayersInSystem.get(0));
        assertEquals(p2, listOfPlayersInSystem.get(1));
    }

    @Test
    void whenGettingAllShipsOwnedByOnePlayer() {
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
    void whenGivingAHitValueItShouldRemoveThatManyUnits() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        ArrayList<Unit> test = testSystem.allShipsOwnedByOnePlayerInSystem(p1);

        //When the value of detected hits is one it should therefore remove one unit
        testSystem.removeUnitAfterHit(1, p1);
        //Each player has two units and when removing one, the size should be 1
        assertEquals(1, testSystem.allShipsOwnedByOnePlayerInSystem(p1).size());
    }

    @Test
    void whenCreatingASpaceBattleFindTheWinningPlayer02() {
        testSystem.addShipToSystem(d1);
        testSystem.addShipToSystem(c1);
        testSystem.addShipToSystem(d2);
        testSystem.addShipToSystem(c2);

        System.out.println(testSystem.playerWhoWonSpaceBattle().toString());

        System.out.println(testSystem.allShipsOwnedByOnePlayerInSystem(p1).size());
        System.out.println(testSystem.allShipsOwnedByOnePlayerInSystem(p2).size());
    }
}