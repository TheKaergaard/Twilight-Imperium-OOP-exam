package TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.Galaxy;
import PackageImperium.Space.SpaceSystem;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GalaxyTest {
    Galaxy testGalaxy = new Galaxy();

    @Test
    void positionOfSystemsNorthSouth() {

        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(0, -1), system02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());
        //
        assertEquals(system01, system02.getHexagonalGrid().get(SpaceSystem.Position.NORTH));
        assertEquals(system02, system01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH));
    }

    @Test
    void positionOfSystemsNESW() {
        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(-1, -1), system02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());

        assertEquals(system01, system02.getHexagonalGrid().get(SpaceSystem.Position.NORTH_EAST));
        assertEquals(system02, system01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH_WEST));
    }

    @Test
    void positionOfSystemsNWSE() {
        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(1, -1), system02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());

        assertEquals(system01, system02.getHexagonalGrid().get(SpaceSystem.Position.NORTH_WEST));
        assertEquals(system02, system01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH_EAST));
    }

    @Test
    void creationOfGalaxyWithPlayers() {
        Galaxy testGalaxyWithPlayers = testGalaxy.createGalaxyWithPlayers();
        HashMap<Point, SpaceSystem> systemsInTestGalaxy = testGalaxyWithPlayers.getHexagonalGridOfSystems();

        Set<Point> keyset = systemsInTestGalaxy.keySet();

        SpaceSystem lol = new SpaceSystem();
        if (keyset.contains

                (lol.getHexagonalGrid().get(SpaceSystem.Position.NORTH))) {

        }

        assertTrue(systemsInTestGalaxy.containsKey(new Point(0, 0)));

        System.out.println(keyset.size());
    }

    @Test
    void shipsOwnedByPlayers() {
        Galaxy testGalaxyWithPlayers = testGalaxy.createGalaxyWithPlayers();

    }

    @Ignore
    void sizeOfCreatedGalaxyWithPlayers() {
        Galaxy testGalaxy02 = testGalaxy.createGalaxyWithPlayers();

        assertEquals(7, testGalaxy02.getHexagonalGridOfSystems().size());
    }
}