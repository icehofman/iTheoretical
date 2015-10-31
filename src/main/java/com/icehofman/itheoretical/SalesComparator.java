package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.Sale.Sales;
import java.util.Comparator;

public class SalesComparator implements Comparator<Sales> {

    private static SalesComparator salesComparator = new SalesComparator();

    private SalesComparator() {
    }

    public static SalesComparator getInstance() {
        return salesComparator;
    }

    public int compare(Sales o1, Sales o2) {
        return o1.getValue().min(o2.getValue()).intValue();
    }
}
