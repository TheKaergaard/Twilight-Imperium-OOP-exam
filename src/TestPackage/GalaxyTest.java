package TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;
import PackageImperium.Space.Galaxy;
import PackageImperium.Space.Planet;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GalaxyTest {
    Galaxy testGalaxy = new Galaxy();

    @Test
    void gettingAllShipsInGalaxy () {
        SpaceSystem testSystem = new SpaceSystem();
        Destroyer d1 = new Destroyer();
        Cruiser c1 = new Cruiser();
        Dreadnought d2 = new Dreadnought();
        Carrier c2 = new Carrier();
        testSystem.listOfShipsInSystem.add(d1);
        testSystem.listOfShipsInSystem.add(c1);
        testSystem.listOfShipsInSystem.add(c2);
        testSystem.listOfShipsInSystem.add(d2);

        testGalaxy.setSystemsIntoGalaxy(new Point(0,0), testSystem);

        ArrayList<Unit> ships = testGalaxy.listOfShipsInGalaxy();

        for (Unit unit : ships) {
            System.out.println(unit.getUnitType());
        }
    }

    @Test
    void gettingListOfSystemsInGalaxy () {
        SpaceSystem testSystem01 = new SpaceSystem();
        SpaceSystem testSystem02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0,0), testSystem01);
        testGalaxy.setSystemsIntoGalaxy(new Point(0,1), testSystem02);

        //testing for size and systems in the returned ArrayList
        assertEquals(2, testGalaxy.listOfSystemsInGalaxy().size());
        assertEquals(testSystem01,testGalaxy.listOfSystemsInGalaxy().get(0));
        assertEquals(testSystem02,testGalaxy.listOfSystemsInGalaxy().get(1));
    }

    @Test
    void positionOfSystemsNorthAndSouth() {
        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(0, -1), system02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());

        assertEquals(system01, system02.getHexagonalGrid().get(SpaceSystem.Position.NORTH));
        assertEquals(system02, system01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH));
    }

    @Test
    void positionOfSystemsNorthEastAndSouthWest() {
        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(-1, -1), system02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());

        assertEquals(system01, system02.getHexagonalGrid().get(SpaceSystem.Position.NORTH_EAST));
        assertEquals(system02, system01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH_WEST));
    }

    @Test
    void positionOfSystemsNorthWestAndSouthEast() {
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
        Galaxy testGalaxyWithPlayers = testGalaxy.creationOfGalaxyWithPredefinedConfiguration();
        HashMap<Point, SpaceSystem> systemsInTestGalaxy = testGalaxyWithPlayers.getHexagonalGridOfSystems();
        Set<Point> keyset = systemsInTestGalaxy.keySet();

        boolean centerSystemContainsOnlyMecatolRex = true;

        for(Point temp : keyset) {
            if(systemsInTestGalaxy.get(temp).hexagonalGrid.containsKey(SpaceSystem.Position.NORTH_EAST)) {
                System.out.println("hej");

            }
            if (!((systemsInTestGalaxy.get(temp).listOfPlanetsInSystem.size() == 1) && (systemsInTestGalaxy.get(temp).listOfPlanetsInSystem.get(0).getPlanetName().equals("Mecatol Rex")))) {
                centerSystemContainsOnlyMecatolRex = false;
            }
        }

        //assertTrue(centerSystemContainsOnlyMecatolRex);

        SpaceSystem lol = new SpaceSystem();
        if (keyset.contains(lol.getHexagonalGrid().get(SpaceSystem.Position.NORTH))) {

        }

        assertTrue(systemsInTestGalaxy.containsKey(new Point(0, 0)));

        System.out.println(keyset.size());
    }

    @Test
    void shipsOwnedByPlayers() {
        Galaxy testGalaxyWithPlayers = testGalaxy.creationOfGalaxyWithPredefinedConfiguration();
        HashMap<Point, SpaceSystem> systemsInTestGalaxy = testGalaxyWithPlayers.getHexagonalGridOfSystems();

        for (Point temp : systemsInTestGalaxy.keySet()) {
            String value = systemsInTestGalaxy.get(temp).toString();
            System.out.println(value);

            //System.out.println(testGalaxyWithPlayers.createGalaxyWithPlayers().getHexagonalGridOfSystems().size());
            //System.out.println(systemsInTestGalaxy.get(temp).allShipsInSystem().get(i).getUnitType());
        }
    }

    @Test
    void testingPropertiesForLegalGalaxy() {
        SpaceSystem centerSystem = new SpaceSystem();
        SpaceSystem testSystem02 = new SpaceSystem();
        SpaceSystem testSystem03 = new SpaceSystem();
        SpaceSystem testSystem04 = new SpaceSystem();
        SpaceSystem testSystem05 = new SpaceSystem();
        SpaceSystem testSystem06 = new SpaceSystem();
        SpaceSystem testSystem07 = new SpaceSystem();

        Planet mecatolRex = new Planet("Mecatol Rex");
        Planet planet01 = new Planet("planet01",1);
        Planet planet02 = new Planet("planet02",1);
        Planet planet03 = new Planet("planet03",1);
        Planet planet04 = new Planet("planet04",1);

        centerSystem.listOfPlanetsInSystem.add(mecatolRex);
        //centerSystem.listOfPlanetsInSystem.add(planet01);
        testSystem02.listOfPlanetsInSystem.add(planet02);
        testSystem02.listOfPlanetsInSystem.add(planet03);
        testSystem03.listOfPlanetsInSystem.add(planet04);
        //testSystem03.listOfPlanetsInSystem.add(planet04);

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), centerSystem);
        testGalaxy.setSystemsIntoGalaxy(new Point(0,1), testSystem02);
        testGalaxy.setSystemsIntoGalaxy(new Point(1,1), testSystem03);
        testGalaxy.setSystemsIntoGalaxy(new Point(1,0), testSystem04);
        testGalaxy.setSystemsIntoGalaxy(new Point(0,-1), testSystem05);
        testGalaxy.setSystemsIntoGalaxy(new Point(-1,-1), testSystem06);
        testGalaxy.setSystemsIntoGalaxy(new Point(-1,0), testSystem07);

        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());

        assertTrue(testGalaxy.checkForPropertiesOfLegalGalaxy());
    }

    @Test
    void testingIfRandomGeneratedGalaxyIsLegal() {
        Galaxy randomTestGalaxy = new Galaxy();
        SpaceSystem foo = new SpaceSystem();

        Player temp = randomTestGalaxy.generateRandomPlayer();
        randomTestGalaxy = randomTestGalaxy.constructRandomGalaxy();
        ArrayList<Unit> foo2 = randomTestGalaxy.generateRandomUnits();
        ArrayList<Planet> foo3 = randomTestGalaxy.generateRandomPlanets();

        assertEquals(7,randomTestGalaxy.getHexagonalGridOfSystems().size());
        assertTrue(!randomTestGalaxy.listOfSystemsInGalaxy().isEmpty());
        assertTrue(!randomTestGalaxy.listOfPlanetsInGalaxy().isEmpty());

        assertTrue(randomTestGalaxy.checkForPropertiesOfLegalGalaxy());
    }

    @Ignore
    void sizeOfCreatedGalaxyWithPlayers() {
        Galaxy testGalaxy02 = testGalaxy.creationOfGalaxyWithPredefinedConfiguration();

        assertEquals(7, testGalaxy02.getHexagonalGridOfSystems().size());
    }
}