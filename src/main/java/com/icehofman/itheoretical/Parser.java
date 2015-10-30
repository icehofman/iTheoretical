package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.*;
import com.opencsv.CSVParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Parser {

    private Parser() {

    }

    /**
     * Return a new instance
     *
     * @return
     */
    public static Parser getParser() {
        return new Parser();
    }

    /**
     * Process the input files
     *
     * @param columnSeparator the column separator char of the input files
     * @param inputStreams    the input file's references
     * @return a salesBatch object with the files data
     * @throws Exception
     */
    public SalesBatch processInputFile(char columnSeparator, InputStream... inputStreams) throws Exception {

        CSVParser csv = new CSVParser(columnSeparator);
        SalesBatch salesBatch = null;
        Map<String, BusinessArea> businessAreaMap = new HashMap<String, BusinessArea>();
        Map<Integer, Sale> salesMap = new HashMap<Integer, Sale>();
        Map<String, Salesman> salesmanMap = new HashMap<String, Salesman>();

        for (InputStream inputStream : inputStreams) {
            LineNumberReader lineNumberReader = null;
            try {
                lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
                if (salesBatch == null) {
                    salesBatch = new SalesBatch();
                }
                while (lineNumberReader.ready()) {
                    String line = lineNumberReader.readLine();
                    String[] parsedLine = csv.parseLine(line);
                    int rowType = Integer.parseInt(parsedLine[0]);
                    switch (rowType) {
                        case 1: //Process lines with id 001: Salesman

                            Salesman salesman = new Salesman(parsedLine[2], parsedLine[1], new BigDecimal(parsedLine[3]));
                            salesBatch.getSalesmans().add(salesman);
                            salesmanMap.put(salesman.getName(), salesman);
                            break;

                        case 2://Process lines with 002: Customer

                            String businessAreaName = parsedLine[3];
                            BusinessArea businessArea = businessAreaMap.get(businessAreaName);
                            if (businessArea == null) {
                                businessArea = new BusinessArea(businessAreaName);
                                businessAreaMap.put(businessAreaName, businessArea);
                            }
                            Customer customer = new Customer(parsedLine[1], parsedLine[2], businessArea);
                            salesBatch.getCustomers().add(customer);
                            break;

                        case 3://Process lines with 003: Sale

                            Integer salesId = Integer.parseInt(parsedLine[1]);
                            Sale sale = salesMap.get(salesId);
                            if (sale == null) {
                                String salesmanName = parsedLine[3];
                                salesman = salesmanMap.get(salesmanName);

                                Set<SaleItem> saleItems = new HashSet<SaleItem>();
                                String[] rawItens = parsedLine[2].replaceFirst("\\[", "").replaceFirst("\\]", "").
                                        split("\\,");
                                for (String rawItem : rawItens) {
                                    String[] rawItemParts = rawItem.split("\\-");
                                    sale = new Sale(salesId, salesman, saleItems);
                                    SaleItem saleItem = new SaleItem(Integer.parseInt(rawItemParts[0]),
                                            new BigDecimal(rawItemParts[1]), new BigDecimal(rawItemParts[2]), sale);
                                    saleItems.add(saleItem);
                                }
                            }
                            break;

                        default:
                            break;
                    }
                }
            } finally {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            }
        }
        return salesBatch;
    }

}
