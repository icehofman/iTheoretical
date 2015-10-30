package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.Sale;

import java.util.Comparator;

public class SalesComparator implements Comparator<Sale> {

    private static SalesComparator salesComparator = new SalesComparator();

    private SalesComparator() {

    }

    public static SalesComparator getInstance() {
        return salesComparator;
    }

    public int compare(Sale o1, Sale o2) {
        return o1.getValue().min(o2.getValue()).intValue();
    }


}
