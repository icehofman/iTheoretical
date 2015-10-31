package com.icehofman.itheoretical.model.customer;

import com.icehofman.itheoretical.model.business.BusinessArea;

public class Customers {
    private String cnpj;

    private String name;

    private BusinessArea businessArea;

    public Customers(String cnpj, String name, BusinessArea businessArea) {
        super();
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    @Override
    public String toString() {
        return "Customers [cnpj=" + cnpj + ", name=" + name + ", businessArea=" + businessArea + "]";
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
        Customers other = (Customers) obj;
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
