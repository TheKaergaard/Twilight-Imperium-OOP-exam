package PackageImperium.CustomExceptions;/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

public class UnitDoesNotExcistException extends RuntimeException {

    public UnitDoesNotExcistException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
