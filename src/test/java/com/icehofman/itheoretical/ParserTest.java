package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.Business.BusinessArea;
import com.icehofman.itheoretical.model.Customer.Customers;
import com.icehofman.itheoretical.model.Sale.SaleItem;
import com.icehofman.itheoretical.model.Sale.Sales;
import com.icehofman.itheoretical.model.Sale.SalesBatch;
import com.icehofman.itheoretical.model.Sale.Salesman;
import com.icehofman.itheoretical.utils.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

public class ParserTest {
    @Test
    public void testProcessInputFile() throws Exception {
        SalesBatch salesBatch = Parser.getParser().processInputFile('ç', getClass().getResourceAsStream("/test_input_file.dat"));

        Assert.assertEquals(salesBatch.getCustomersAmount().intValue(), 2);
        Assert.assertEquals(salesBatch.getMostExpensiveSale().getValue(), new BigDecimal("1199.00"));
        Assert.assertEquals(salesBatch.getWorstSalesman().getName(), "Renato");

        Assert.assertNotNull(salesBatch);

        Set<Salesman> salesmans = salesBatch.getSalesmans();

        Assert.assertEquals(salesmans.size(), 2);
        Salesman salesman1 = new Salesman("Diego", "1234567891234", new BigDecimal("50000"));
        Salesman salesman2 = new Salesman("Renato", "3245678865434", new BigDecimal("40000.99"));
        Assert.assertTrue(salesmans.contains(salesman1));
        Assert.assertTrue(salesmans.contains(salesman2));

        Set<Customers> customers = salesBatch.getCustomers();
        Assert.assertEquals(salesBatch.getCustomers().size(), 2);

        Assert.assertTrue(customers.contains(new Customers("2345675434544345", "Jose da Silva", new BusinessArea("Rural"))));
        Assert.assertTrue(customers.contains(new Customers("2345675433444345", "Eduardo Pereira", new BusinessArea("Rural"))));

        for (Salesman salesman : salesmans) {
            Set<Sales> sales = salesman.getSales();
            Assert.assertEquals(sales.size(), 1);
            Sales sale = sales.iterator().next();
            Assert.assertEquals(sale.getSalesman(), salesman);
            Set<SaleItem> saleItens = sale.getSaleItems();
            if (salesman.equals(salesman1)) {
                Assert.assertEquals(sale.getId().intValue(), 10);
                Assert.assertTrue(saleItens.contains(new SaleItem(1, new BigDecimal("10"), new BigDecimal("100"), sale)));
                Assert.assertTrue(saleItens.contains(new SaleItem(2, new BigDecimal("30"), new BigDecimal("2.50"), sale)));
                Assert.assertTrue(saleItens.contains(new SaleItem(3, new BigDecimal("40"), new BigDecimal("3.10"), sale)));
            } else if (salesman.equals(salesman2)) {
                Assert.assertEquals(sale.getId().intValue(), 8);
                Assert.assertTrue(saleItens.contains(new SaleItem(1, new BigDecimal("34"), new BigDecimal("10"), sale)));
                Assert.assertTrue(saleItens.contains(new SaleItem(2, new BigDecimal("33"), new BigDecimal("1.50"), sale)));
                Assert.assertTrue(saleItens.contains(new SaleItem(3, new BigDecimal("40"), new BigDecimal("0.10"), sale)));
            }
        }

    }
}
