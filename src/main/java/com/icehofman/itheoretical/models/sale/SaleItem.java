package com.icehofman.itheoretical.models.sale;

import java.math.BigDecimal;

public class SaleItem {
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Sales sale;

    public SaleItem(Integer id, BigDecimal quantity, BigDecimal price, Sales sale) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "SaleItem [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((sale == null) ? 0 : sale.hashCode());
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
        SaleItem other = (SaleItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (sale == null) {
            if (other.sale != null)
                return false;
        } else if (!sale.equals(other.sale))
            return false;
        return true;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getItemValue() {
        return getPrice().multiply(getQuantity());
    }
}
