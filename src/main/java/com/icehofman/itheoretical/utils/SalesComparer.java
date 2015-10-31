package com.icehofman.itheoretical.utils;

import com.icehofman.itheoretical.models.sale.Sales;

import java.util.Comparator;

public class SalesComparer implements Comparator<Sales> {

    private static SalesComparer salesComparator = new SalesComparer();

    private SalesComparer() {
    }

    public static SalesComparer getInstance() {
        return salesComparator;
    }

    public int compare(Sales o1, Sales o2) {
        return o1.getValue().min(o2.getValue()).intValue();
    }
}
