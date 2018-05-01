package PackageImperium.Units;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;

public class Cruiser implements Unit {
    private Player owner;

    public Cruiser(Player owner) {
        this.owner = owner;
    }

    @Override
    public Type getUnitType() {
        return Type.CRUISER;
    }

    @Override
    public int getResourceCost() {
        return 2;
    }

    @Override
    public int getCombatValue() {
        return 7;
    }

    @Override
    public int getMovementSpeed() {
        return 2;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
