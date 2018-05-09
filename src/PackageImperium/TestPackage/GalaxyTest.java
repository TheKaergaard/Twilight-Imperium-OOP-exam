package PackageImperium.TestPackage;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Space.Galaxy;
import PackageImperium.Space.Planet;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GalaxyTest {
    private Galaxy testGalaxy = new Galaxy();
    private SpaceSystem testSystem01 = new SpaceSystem();
    private SpaceSystem testSystem02 = new SpaceSystem();

    @Test
    void whenGettingListOfSystemsInGalaxy() {
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), testSystem01);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 1), testSystem02);

        assertTrue(!testGalaxy.listOfSystemsInGalaxy().isEmpty());
        assertEquals(2, testGalaxy.listOfSystemsInGalaxy().size());
        assertEquals(testSystem01, testGalaxy.listOfSystemsInGalaxy().get(0));
        assertEquals(testSystem02, testGalaxy.listOfSystemsInGalaxy().get(1));
    }

    @Test
    void whenGettingListOfPlanetsInGalaxy() {
        Planet testPlanet01 = new Planet("Test Planet 1");
        Planet testPlanet02 = new Planet("Test Planet 2");
        Planet testPlanet03 = new Planet("Test Planet 3");

        testSystem01.listOfPlanetsInSystem.add(testPlanet01);
        testSystem01.listOfPlanetsInSystem.add(testPlanet02);
        testSystem01.listOfPlanetsInSystem.add(testPlanet03);

        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), testSystem01);

        assertTrue(!testGalaxy.listOfPlanetsInGalaxy().isEmpty());
        assertEquals(testPlanet01, testGalaxy.listOfPlanetsInGalaxy().get(0));
        assertEquals(testPlanet02, testGalaxy.listOfPlanetsInGalaxy().get(1));
        assertEquals(testPlanet03, testGalaxy.listOfPlanetsInGalaxy().get(2));
    }

    @Test
    void whenGettingListOfAllShipsInGalaxy() {
        Destroyer d1 = new Destroyer();
        Cruiser c1 = new Cruiser();
        Dreadnought d2 = new Dreadnought();
        Carrier c2 = new Carrier();

        testSystem01.listOfShipsInSystem.add(d1);
        testSystem01.listOfShipsInSystem.add(c1);
        testSystem02.listOfShipsInSystem.add(d2);
        testSystem02.listOfShipsInSystem.add(c2);
        //The units are put into two different systems in the galaxy
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), testSystem01);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 1), testSystem02);

        assertTrue(!testGalaxy.listOfShipsInGalaxy().isEmpty());
        assertEquals(4, testGalaxy.listOfShipsInGalaxy().size());

        assertEquals(d1, testGalaxy.listOfShipsInGalaxy().get(0));
        assertEquals(c1, testGalaxy.listOfShipsInGalaxy().get(1));
        assertEquals(d2, testGalaxy.listOfShipsInGalaxy().get(2));
        assertEquals(c2, testGalaxy.listOfShipsInGalaxy().get(3));
    }

    @Test
    void positionOfSystemsNorthAndSouth() {
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), testSystem01);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, -1), testSystem02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());
        //Testing if (0, -1) compared to (0,0) is the northern system. If true the opposite must be south.
        assertEquals(testSystem01, testSystem02.getHexagonalGrid().get(SpaceSystem.Position.NORTH));
        assertEquals(testSystem02, testSystem01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH));
    }

    @Test
    void positionOfSystemsNorthEastAndSouthWest() {
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), testSystem01);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(-1, -1), testSystem02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());
        //Testing if (-1, -1) compared to (0,0) is north eastern system. If true the opposite must be south west.
        assertEquals(testSystem01, testSystem02.getHexagonalGrid().get(SpaceSystem.Position.NORTH_EAST));
        assertEquals(testSystem02, testSystem01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH_WEST));
    }

    @Test
    void positionOfSystemsNorthWestAndSouthEast() {
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), testSystem01);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(1, 0), testSystem02);
        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());
        //Testing if (1, 0) compared to (0,0) is north western system. If true the opposite must be south east.
        assertEquals(testSystem01, testSystem02.getHexagonalGrid().get(SpaceSystem.Position.NORTH_WEST));
        assertEquals(testSystem02, testSystem01.getHexagonalGrid().get(SpaceSystem.Position.SOUTH_EAST));
    }

    @Test
    void creationOfGalaxyWithPlayers() {
        Galaxy testGalaxyWithPlayers = testGalaxy.creationOfGalaxyWithPredefinedConfiguration();
        HashMap<Point, SpaceSystem> systemsInTestGalaxy = testGalaxyWithPlayers.getHexagonalGridOfSystems();

        assertTrue(systemsInTestGalaxy.containsKey(new Point(0, 0)));
        assertEquals(7, systemsInTestGalaxy.size());
        assertEquals(6, testGalaxyWithPlayers.listOfShipsInGalaxy().size());
        assertEquals(7, testGalaxyWithPlayers.listOfPlanetsInGalaxy().size());

        //assertEquals("Mecatol Rex", testGalaxy.getHexagonalGridOfSystems().get(new Point(0,0)).listOfPlanetsInSystem.get(0).planetName);
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
        Planet planet01 = new Planet("planet01", 1);
        Planet planet02 = new Planet("planet02", 1);
        Planet planet03 = new Planet("planet03", 1);
        Planet planet04 = new Planet("planet04", 1);

        centerSystem.listOfPlanetsInSystem.add(mecatolRex);
        //centerSystem.listOfPlanetsInSystem.add(planet01);
        testSystem02.listOfPlanetsInSystem.add(planet02);
        testSystem02.listOfPlanetsInSystem.add(planet03);
        testSystem03.listOfPlanetsInSystem.add(planet04);
        //testSystem03.listOfPlanetsInSystem.add(planet04);

        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 0), centerSystem);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, 1), testSystem02);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(1, 1), testSystem03);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(1, 0), testSystem04);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(0, -1), testSystem05);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(-1, -1), testSystem06);
        testGalaxy.setSystemsIntoHexagonalGridOfSystems(new Point(-1, 0), testSystem07);

        testGalaxy.createHexagonalGridOfSystems(testGalaxy.getHexagonalGridOfSystems());
        //Testing if the created test galaxy is legal
        assertTrue(testGalaxy.checkForPropertiesOfLegalGalaxy());
    }

    @Test
    void whenGeneratingRandomUnits() {
        ArrayList<Unit> listOfRandomUnits = testGalaxy.generateRandomUnits();
        //Testing if at least one unit is created when method is called
        assertTrue(listOfRandomUnits.size() > 0);
    }

    @Test
    void whenGeneratingRandomPlanets() {
        ArrayList<Planet> listOfRandomPlanets = testGalaxy.generateRandomPlanets();
        //Testing if the amount of random generated planets always is between 1-3
        assertTrue(listOfRandomPlanets.size() >= 1 && listOfRandomPlanets.size() <= 3);
    }

    @Test
    void whenGeneratingARandomSystem() {
        SpaceSystem randomGeneratedSystem = testGalaxy.generateRandomSystem();
        //Testing if a random generated system always has planets
        assertTrue(!randomGeneratedSystem.listOfPlanetsInSystem.isEmpty());
    }

    @Test
    void whenGeneratingARandomGalaxy() {
        Galaxy randomTestGalaxy = testGalaxy.constructRandomGalaxy();

        assertEquals(7, randomTestGalaxy.getHexagonalGridOfSystems().size());
        assertTrue(!randomTestGalaxy.listOfSystemsInGalaxy().isEmpty());
        assertTrue(!randomTestGalaxy.listOfPlanetsInGalaxy().isEmpty());
        assertTrue(!randomTestGalaxy.listOfShipsInGalaxy().isEmpty());

        assertTrue(randomTestGalaxy.checkForPropertiesOfLegalGalaxy());
    }
}