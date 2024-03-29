package com.icehofman.itheoretical.utils;

import com.icehofman.itheoretical.models.sale.SalesBatch;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Report {

    private Report() {
    }

    public static void writeToCSVFile(SalesBatch salesBatch, String outputFilePath, String columnSeparator) throws Exception {
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath));

            bufferedWriter.append("customers_amount")
                    .append(columnSeparator)
                    .append("salesman_amount")
                    .append(columnSeparator)
                    .append("most_expensive_sale")
                    .append(columnSeparator)
                    .append("worst_salesman");

            bufferedWriter.newLine();

            bufferedWriter.append(String.valueOf(salesBatch.getCustomersAmount()))
                    .append(columnSeparator)
                    .append(String.valueOf(salesBatch.getSalesmanAmount()))
                    .append(columnSeparator)
                    .append(String.valueOf(salesBatch.getMostExpensiveSale().getId()))
                    .append(columnSeparator)
                    .append(salesBatch.getWorstSalesman().getName());

        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
