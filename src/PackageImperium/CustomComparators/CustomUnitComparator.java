package PackageImperium.CustomComparators;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Units.Unit;

import java.util.Comparator;

public class CustomUnitComparator implements Comparator<Unit> {

    @Override
    public int compare(Unit unit1, Unit unit2) {

        Integer cbv1 = unit1.getCombatValue();
        Integer cbv2 = unit2.getCombatValue();
        int foo = cbv1.compareTo(cbv2);
        if (foo != 0) {
            return foo;
        }
        Integer rc1 = unit1.getResourceCost();
        Integer rc2 = unit2.getResourceCost();
        int bar = rc1.compareTo(rc2);
        if (bar != 0) {
            return bar;
        }
        return 0;
    }
}
