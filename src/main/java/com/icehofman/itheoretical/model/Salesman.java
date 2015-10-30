package com.icehofman.itheoretical.model;

import ch.lambdaj.Lambda;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Salesman {
    private String name;

    private String cpf;

    private BigDecimal salary;

    private Set<Sale> sales = new HashSet<Sale>();

    /**
     * Default construct
     */
    public Salesman() {
        super();
    }

    /**
     * Parameter construct
     *
     * @param name
     * @param cpf
     * @param salary
     */
    public Salesman(String name, String cpf, BigDecimal salary) {
        super();
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Salesman [name=" + name + ", cpf=" + cpf + ", salary=" + salary + ", sales=" + sales + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        return result;
    }

    /**
     * Return the sum of all item's values
     *
     * @return
     */
    public BigDecimal getSalesValue() {
        return Lambda.sumFrom(sales).getValue();
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
        Salesman other = (Salesman) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * @return the sales
     */
    public Set<Sale> getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }


}
