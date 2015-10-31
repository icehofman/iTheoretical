package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.Sale.Salesman;
import java.util.Comparator;

public class SalesmanComparator implements Comparator<Salesman> {
    private static final SalesmanComparator SALESMAN_COMPARATOR = new SalesmanComparator();

    private SalesmanComparator() {
    }

    public static SalesmanComparator getInstance() {
        return SALESMAN_COMPARATOR;
    }

    public int compare(Salesman o1, Salesman o2) {
        return o1.getSalesValue().subtract(o2.getSalesValue()).intValue();
    }
}
