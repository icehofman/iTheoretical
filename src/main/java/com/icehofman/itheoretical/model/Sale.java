package com.icehofman.itheoretical.model;

import ch.lambdaj.Lambda;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Sale {

    private Integer id;

    private Salesman salesman;
    private Set<SaleItem> saleItems = new HashSet<SaleItem>();


    /**
     * Empty constructor
     */
    public Sale() {
        super();
    }

    /**
     * Parameter constructor
     *
     * @param id
     * @param salesman
     * @param saleItems
     */
    public Sale(Integer id, Salesman salesman, Set<SaleItem> saleItems) {
        super();
        this.id = id;
        this.salesman = salesman;
        this.saleItems = saleItems;
        salesman.getSales().add(this);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((salesman == null) ? 0 : salesman.hashCode());
        return result;
    }

    /**
     * Return sale value, summing all itens values
     *
     * @return
     */
    public BigDecimal getValue() {
        return Lambda.sumFrom(getSaleItems()).getItemValue();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sale other = (Sale) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (salesman == null) {
            if (other.salesman != null)
                return false;
        } else if (!salesman.equals(other.salesman))
            return false;
        return true;
    }

    /**
     * @return the saleItems
     */
    public Set<SaleItem> getSaleItems() {
        return saleItems;
    }

    /**
     * @param saleItems the saleItems to set
     */
    public void setSaleItems(Set<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the salesman
     */
    public Salesman getSalesman() {
        return salesman;
    }

    /**
     * @param salesman the salesman to set
     */
    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }


}
