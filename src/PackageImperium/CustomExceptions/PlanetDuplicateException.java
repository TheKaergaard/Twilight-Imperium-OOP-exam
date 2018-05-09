package PackageImperium.CustomExceptions;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

public class PlanetDuplicateException extends RuntimeException {
    public PlanetDuplicateException(String message) {
        super(message);
    }
}
