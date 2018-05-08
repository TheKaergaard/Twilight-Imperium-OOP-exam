package PackageImperium.CustomComparators;
/*
 * OOP exam 2018
 * Simon Park Kærgaard
 * skarga17@student.aau.dk
 */

import PackageImperium.Units.Unit;

import java.util.Comparator;

public class CustomUnitResourceCostComparator implements Comparator<Unit> {

    @Override
    public int compare(Unit unit1, Unit unit2) {
        //Sorts from highest to lowest
        Integer rsc1 = unit1.getResourceCost();
        Integer rsc2 = unit2.getResourceCost();
        int cmp = rsc2.compareTo(rsc1);
        return cmp;
    }
}
