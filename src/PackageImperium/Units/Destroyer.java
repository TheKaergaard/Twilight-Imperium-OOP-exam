package PackageImperium.Units;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Player;

public class Destroyer implements Unit {
    private Player owner;

    public Destroyer(Player owner) {
        this.owner = owner;
    }
    @Override
    public final Type getUnitType() {
        return Type.DESTROYER;
    }

    @Override
    public final int getResourceCost() {
        return 1;
    }

    @Override
    public final int getCombatValue() {
        return 9;
    }

    @Override
    public final int getMovementSpeed() {
        return 2;
    }

    @Override
    public final int getCapacity() {
        return 0;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
