package PackageImperium.CustomExceptions;/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

public class InvalidAmountOfPlayersException extends RuntimeException {

    public InvalidAmountOfPlayersException(String message){
        super(message);
    }
}
