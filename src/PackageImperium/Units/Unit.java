package PackageImperium.Units;

import PackageImperium.Player;

public interface Unit {
    Type getUnitType();
    int getResourceCost();
    int getCombatValue();
    int getMovementSpeed();
    int getCapacity();
    Player getOwner();

    enum Type{
        DESTROYER, CRUISER, CARRIER, DREADNOUGHT
    }


}
