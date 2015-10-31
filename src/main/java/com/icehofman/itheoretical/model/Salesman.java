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

    public Salesman(String name, String cpf, BigDecimal salary) {
        super();
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Salesman [name=" + name + ", cpf=" + cpf + ", salary=" + salary + ", sales=" + sales + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        return result;
    }

    public BigDecimal getSalesValue() {
        return Lambda.sumFrom(sales).getValue();
    }

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

    public String getName() {
        return name;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
