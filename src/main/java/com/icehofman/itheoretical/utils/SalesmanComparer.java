package com.icehofman.itheoretical.utils;

import com.icehofman.itheoretical.models.sale.Salesman;

import java.util.Comparator;

public class SalesmanComparer implements Comparator<Salesman> {
    private static final SalesmanComparer SALESMAN_COMPARATOR = new SalesmanComparer();

    private SalesmanComparer() {
    }

    public static SalesmanComparer getInstance() {
        return SALESMAN_COMPARATOR;
    }

    public int compare(Salesman o1, Salesman o2) {
        return o1.getSalesValue().subtract(o2.getSalesValue()).intValue();
    }
}
