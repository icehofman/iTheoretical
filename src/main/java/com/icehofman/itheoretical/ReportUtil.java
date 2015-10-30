package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.SalesBatch;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ReportUtil {

    private ReportUtil() {

    }

    /**
     * Write a result report csv file
     *
     * @param salesBatch      the report data
     * @param outputFilePath
     * @param columnSeparator the column separator to be used on the report csv file
     * @throws Exception
     */
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
