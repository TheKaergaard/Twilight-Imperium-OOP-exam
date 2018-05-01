package PackageImperium.Units;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;

public class Carrier implements Unit {
    private Player owner;

    public Carrier(Player owner) {
        this.owner = owner;
    }

    @Override
    public Type getUnitType() {
        return Type.CARRIER;
    }

    @Override
    public int getResourceCost() {
        return 3;
    }

    @Override
    public int getCombatValue() {
        return 9;
    }

    @Override
    public int getMovementSpeed() {
        return 1;
    }

    @Override
    public int getCapacity() {
        return 6;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

}
