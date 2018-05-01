package PackageImperium.Units;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;

public class Dreadnought implements Unit {
    private Player owner;

    public Dreadnought(Player owner) {
        this.owner = owner;
    }

    @Override
    public Type getUnitType() {
        return Type.DREADNOUGHT;
    }

    @Override
    public int getResourceCost() {
        return 5;
    }

    @Override
    public int getCombatValue() {
        return 5;
    }

    @Override
    public int getMovementSpeed() {
        return 1;
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
