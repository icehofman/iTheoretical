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

    /**
     * @return the salesmans
     */
    public Set<Salesman> getSalesmans() {
        return salesmans;
    }

    /**
     * @param salesmans the salesmans to set
     */
    public void setSalesmans(Set<Salesman> salesmans) {
        this.salesmans = salesmans;
    }

    /**
     * @return the processingDate
     */
    public Date getProcessingDate() {
        return processingDate;
    }

    /**
     * @param processingDate the processingDate to set
     */
    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    /**
     * @return the customers
     */
    public Set<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    /**
     * @return the customers amount inside this batch
     */
    public Integer getCustomersAmount() {
        return customers.size();
    }

    /**
     * @return the salesman amount inside this batch
     */
    public Integer getSalesmanAmount() {
        return salesmans.size();
    }

    /**
     * @return the most expensive sale
     */
    public Sale getMostExpensiveSale() {

        Set<Sale> allSales = new HashSet<Sale>();
        for (Salesman salesman : getSalesmans()) {
            allSales.addAll(salesman.getSales());
        }

        return Collections.max(allSales, SalesComparator.getInstance());
    }

    /**
     * @return the salesman with the worst sales values
     */
    public Salesman getWorstSalesman() {
        return Collections.min(salesmans, SalesmanComparator.getInstance());
    }

}
