package PackageImperium;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Units.Unit;

import java.util.Comparator;

public class CustomComparator implements Comparator<Unit> {

    @Override
    public int compare(Unit unit1, Unit unit2){
        if (unit1.getCombatValue() > unit2.getCombatValue()) {
            return 1;
        } else if (unit1.getCombatValue() < unit2.getCombatValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
