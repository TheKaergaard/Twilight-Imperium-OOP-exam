package PackageImperium.TestPackage;
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
import org.junit.jupiter.api.*;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Galaxy testGalaxy = new Galaxy();

    @Test
    void sortedListOfShipsFromPlayer() {
        Player p1 = new Player("p1", "race1", "colour");
        Galaxy testGalaxy = new Galaxy();
        SpaceSystem testSystem = new SpaceSystem();
        Destroyer d1 = new Destroyer(p1);
        Cruiser c1 = new Cruiser(p1);
        Dreadnought d2 = new Dreadnought(p1);
        Carrier c2 = new Carrier(p1);
        testSystem.listOfShipsInSystem.add(d1);
        testSystem.listOfShipsInSystem.add(c1);
        testSystem.listOfShipsInSystem.add(c2);
        testSystem.listOfShipsInSystem.add(d2);

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 0), testSystem);

        ArrayList<Unit> sortedListOfPlayerOwnedShips = p1.shipsOwnedByPlayer(p1, testGalaxy);

        assertEquals(Unit.Type.DREADNOUGHT, sortedListOfPlayerOwnedShips.get(0).getUnitType());
        assertEquals(Unit.Type.CRUISER, sortedListOfPlayerOwnedShips.get(1).getUnitType());
        assertEquals(Unit.Type.DESTROYER, sortedListOfPlayerOwnedShips.get(2).getUnitType());
        assertEquals(Unit.Type.CARRIER, sortedListOfPlayerOwnedShips.get(3).getUnitType());
    }

    @Test
    void systemsOwnedByPlayer() {
        //Works for first entered player
        Player p1 = new Player("test person1", "race1", "blue");
        Player p2 = new Player("test person2", "race2", "red");

        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();
        SpaceSystem system03 = new SpaceSystem();
        SpaceSystem system04 = new SpaceSystem();
        SpaceSystem system05 = new SpaceSystem();

        Dreadnought d1 = new Dreadnought(p1);
        Dreadnought d2 = new Dreadnought(p2);

        system01.addShipToSystem(d1);

        system02.addShipToSystem(d1);
        system02.addShipToSystem(d2);

        system03.addShipToSystem(d1);
        system03.addShipToSystem(d2);
        system03.addShipToSystem(d1);

        system04.addShipToSystem(d1);
        system04.addShipToSystem(d1);

        system05.addShipToSystem(d2);

        testGalaxy.setSystemsIntoGalaxy(new Point(0, 1), system01);
        testGalaxy.setSystemsIntoGalaxy(new Point(1, 0), system02);
        testGalaxy.setSystemsIntoGalaxy(new Point(-1, 0), system03);
        testGalaxy.setSystemsIntoGalaxy(new Point(0, -1), system04);
        testGalaxy.setSystemsIntoGalaxy(new Point(1, 1), system05);

        ArrayList<SpaceSystem> systemsOwnedByFirstPlayer = p2.listOfSystemsOwnedByOnePlayer(testGalaxy);

        assertEquals(systemsOwnedByFirstPlayer.get(0), system01);
        assertEquals(systemsOwnedByFirstPlayer.get(1), system04);
        assertEquals(systemsOwnedByFirstPlayer.get(2), system05);
    }

    @Test
    void creationOfTextFileOfPlayersAndThePlanetsTheyControl() {
        Player testPlayer01 = new Player("test1", "test1", "test1");
        Player testPlayer02 = new Player("test2", "test2", "test2");
        Galaxy temp = new Galaxy();

        SpaceSystem system01 = new SpaceSystem();
        SpaceSystem system02 = new SpaceSystem();
        SpaceSystem system03 = new SpaceSystem();
        SpaceSystem system04 = new SpaceSystem();

        Planet testPlanet01 = new Planet("Planet1");
        Planet testPlanet02 = new Planet("Planet2");
        Planet testPlanet03 = new Planet("Planet3");
        Planet testPlanet04 = new Planet("Planet4");
        Planet testPlanet05 = new Planet("Planet5");
        Planet testPlanet06 = new Planet("Planet6");
        Planet testPlanet07 = new Planet("Planet7");

        system01.listOfPlanetsInSystem.add(testPlanet01);
        system01.listOfPlanetsInSystem.add(testPlanet02);
        system02.listOfPlanetsInSystem.add(testPlanet03);
        system02.listOfPlanetsInSystem.add(testPlanet04);
        system03.listOfPlanetsInSystem.add(testPlanet05);
        system03.listOfPlanetsInSystem.add(testPlanet06);
        system04.listOfPlanetsInSystem.add(testPlanet07);

        Dreadnought d1 = new Dreadnought(testPlayer01);
        Dreadnought d2 = new Dreadnought(testPlayer02);

        system01.addShipToSystem(d1);

        system02.addShipToSystem(d1);
        system02.addShipToSystem(d2);

        system03.addShipToSystem(d1);
        system03.addShipToSystem(d2);
        system03.addShipToSystem(d1);

        system04.addShipToSystem(d2);

        temp.setSystemsIntoGalaxy(new Point(0, 1), system01);
        temp.setSystemsIntoGalaxy(new Point(1, 0), system02);
        temp.setSystemsIntoGalaxy(new Point(-1, 0), system03);
        temp.setSystemsIntoGalaxy(new Point(-1, 1), system04);

        //Creates ArrayList of systems owned by only one player
        ArrayList<SpaceSystem> playerOwnedSystems = testPlayer01.listOfSystemsOwnedByOnePlayer(temp);

        //Prints out "playerOwnedSystems" in a text file with each systems corresponding owner
        testPlayer01.createTextFileOfPlayerOwnedPlanets(temp, "test");

        //Tests if the correct data is written in the text file
        try (FileReader fr = new FileReader("src/PackageImperium/test.txt")) {
            BufferedReader bfr = new BufferedReader(fr);
            //testPlayer01 should own testPlanet01 and 02
            assertEquals(bfr.readLine(), testPlayer01.toString());
            assertEquals(bfr.readLine(), "\t" + testPlanet01.getPlanetName());
            assertEquals(bfr.readLine(), "\t" + testPlanet02.getPlanetName());
            //testPlayer02 should own testPlanet07
            assertEquals(bfr.readLine(), testPlayer02.toString());
            assertEquals(bfr.readLine(), "\t" + testPlanet07.getPlanetName());

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Deletes the test text file after use
        try {
            if (Files.deleteIfExists(Paths.get("src/PackageImperium/test.txt"))) {
                System.out.println("Text file is successfully deleted!");
            } else {
                System.out.println("Failed to delete text file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    void sortedListOfShipsFromPlayer02() {
        Player p1 = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Galaxy testGalaxy = new Galaxy();
        testGalaxy = testGalaxy.creationOfGalaxyWithPredefinedConfiguration();

        ArrayList<Unit> ships = p1.shipsOwnedByPlayer(p1, testGalaxy);

        for (Unit unit : ships) {
            System.out.println(unit.getUnitType());
        }

    }
}
