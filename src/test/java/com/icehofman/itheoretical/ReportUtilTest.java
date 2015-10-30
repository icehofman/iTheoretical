package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.SalesBatch;
import org.junit.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReportUtilTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link ReportUtil#writeToCSVFile(SalesBatch, java.lang.String, java.lang.String)}
     * .
     *
     * @throws Exception
     */
    @Test
    public void testWriteToCSVFile() throws Exception {


        SalesBatch salesBatch = Parser.getParser().processInputFile('�', getClass().getResourceAsStream("/test_input_file.dat"));

        String outputFilePath = System.getProperty("java.io.tmpdir") + "result.dat";
        ReportUtil.writeToCSVFile(salesBatch, outputFilePath, ";");
        byte[] encoded = Files.readAllBytes(Paths.get(outputFilePath));
        String content = new String(encoded, Charset.defaultCharset());
        Assert.assertEquals(content,
                "customers_amount;salesman_amount;most_expensive_sale;worst_salesman\r\n2;2;10;Renato");
    }

}
