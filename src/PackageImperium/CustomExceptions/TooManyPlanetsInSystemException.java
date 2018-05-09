package PackageImperium.CustomExceptions;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

public class TooManyPlanetsInSystemException extends RuntimeException {
    public TooManyPlanetsInSystemException(String message) {
        super(message);
    }
}
