package PackageImperium;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */


import PackageImperium.Space.Planet;
import PackageImperium.Units.Destroyer;

public class Main {

    public static void main(String[] args) {
        Player p1 = new Player("Hej", "Orc", "Blue");
        Destroyer d1 = new Destroyer(p1);
        Planet planetTest = new Planet("Jupiter", 6 );
    }
}
