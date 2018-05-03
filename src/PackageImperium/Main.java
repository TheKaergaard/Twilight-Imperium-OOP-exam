package PackageImperium;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */


import PackageImperium.Space.Galaxy;
import PackageImperium.Space.Planet;
import PackageImperium.Space.SpaceSystem;
import PackageImperium.Units.Destroyer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Planet testPlanet01 = new Planet("Jupiter", 6 );
        Planet testPLanet02 = new Planet("Saturn",3);

        HashMap<SpaceSystem, Point> hashmap = new HashMap<>();

        ArrayList<Planet> listOfTestPlanets = new ArrayList<>();
        ArrayList<SpaceSystem> listOfTestSystems = new ArrayList<>();
        listOfTestPlanets.add(testPlanet01);
        listOfTestPlanets.add(testPlanet01);

        SpaceSystem testSystem = new SpaceSystem();


        Galaxy testgalaxy = new Galaxy();

        testgalaxy.setSystemsIntoGalaxy(new Point(1,2), testSystem);

    }
}
