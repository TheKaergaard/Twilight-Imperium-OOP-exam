package TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.Galaxy;
import PackageImperium.Space.SpaceSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

class GalaxyTest {
    @Test
    void positionOfSystemsNorthSouth() {
        Galaxy testGalaxy = new Galaxy();

        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(0, -1), system02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());

        assertEquals(system01, system02.getHexagonalGrid().get(SpaceSystem.Position.NORTH));
        assertEquals(system02, system01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH));
    }

    @Test
    void positionOfSystemsNESW() {
        Galaxy testGalaxy = new Galaxy();

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
        Galaxy testGalaxy = new Galaxy();

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
        Galaxy testGalaxy = new Galaxy();
        HashMap<Point, SpaceSystem> systemsInGalaxy = testGalaxy.getHexagonalGridOfSystems();

        Set<Point> keyset = systemsInGalaxy.keySet();

        for (Point temp : keyset) {
            testGalaxy.getHexagonalGridOfSystems().
        }


    }
}