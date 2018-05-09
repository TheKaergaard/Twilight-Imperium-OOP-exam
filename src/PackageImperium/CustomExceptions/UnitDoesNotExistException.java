package PackageImperium.CustomExceptions;/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Units.Unit;

public class UnitDoesNotExistException extends RuntimeException {

    public UnitDoesNotExistException(Unit unit) {
        System.out.println(unit + " does not exist in system!");
    }
}
