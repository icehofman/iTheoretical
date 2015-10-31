package com.icehofman.itheoretical.model;

import com.icehofman.itheoretical.SalesComparator;
import com.icehofman.itheoretical.SalesmanComparator;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SalesBatch {

    private Set<Salesman> salesmans = new HashSet<Salesman>();

    private Date processingDate;

    private Set<Customer> customers = new HashSet<Customer>();

    public Set<Salesman> getSalesmans() {
        return salesmans;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Integer getCustomersAmount() {
        return customers.size();
    }

    public Integer getSalesmanAmount() {
        return salesmans.size();
    }

    public Sale getMostExpensiveSale() {

        Set<Sale> allSales = new HashSet<Sale>();
        for (Salesman salesman : getSalesmans()) {
            allSales.addAll(salesman.getSales());
        }

        return Collections.max(allSales, SalesComparator.getInstance());
    }

    public Salesman getWorstSalesman() {
        return Collections.min(salesmans, SalesmanComparator.getInstance());
    }
}
