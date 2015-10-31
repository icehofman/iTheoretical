package com.icehofman.itheoretical.model.Sale;

import com.icehofman.itheoretical.SalesComparator;
import com.icehofman.itheoretical.SalesmanComparator;
import com.icehofman.itheoretical.model.Customer.Customers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SalesBatch {

    private Set<Salesman> salesmans = new HashSet<Salesman>();
    private Set<Customers> customers = new HashSet<Customers>();

    public Set<Salesman> getSalesmans() {
        return salesmans;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public Integer getCustomersAmount() {
        return customers.size();
    }

    public Integer getSalesmanAmount() {
        return salesmans.size();
    }

    public Sales getMostExpensiveSale() {

        Set<Sales> allSales = new HashSet<Sales>();
        for (Salesman salesman : getSalesmans()) {
            allSales.addAll(salesman.getSales());
        }

        return Collections.max(allSales, SalesComparator.getInstance());
    }

    public Salesman getWorstSalesman() {
        return Collections.min(salesmans, SalesmanComparator.getInstance());
    }
}
