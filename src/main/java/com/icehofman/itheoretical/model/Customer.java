package com.icehofman.itheoretical.model;

public class Customer {
    private String cnpj;

    private String name;

    private BusinessArea businessArea;

    public Customer(String cnpj, String name, BusinessArea businessArea) {
        super();
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    @Override
    public String toString() {
        return "Customer [cnpj=" + cnpj + ", name=" + name + ", businessArea=" + businessArea + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((businessArea == null) ? 0 : businessArea.hashCode());
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (businessArea == null) {
            if (other.businessArea != null)
                return false;
        } else if (!businessArea.equals(other.businessArea))
            return false;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
