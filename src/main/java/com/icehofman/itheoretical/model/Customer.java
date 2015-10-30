package com.icehofman.itheoretical.model;

public class Customer {
    private String cnpj;

    private String name;

    private BusinessArea businessArea;

    public Customer() {
        super();
    }

    public Customer(String cnpj, String name, BusinessArea businessArea) {
        super();
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Customer [cnpj=" + cnpj + ", name=" + name + ", businessArea=" + businessArea + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((businessArea == null) ? 0 : businessArea.hashCode());
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
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
     * @return the businessArea
     */
    public BusinessArea getBusinessArea() {
        return businessArea;
    }

    /**
     * @param businessArea the businessArea to set
     */
    public void setBusinessArea(BusinessArea businessArea) {
        this.businessArea = businessArea;
    }


}
