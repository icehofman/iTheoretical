package com.icehofman.itheoretical;

import org.junit.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BatchFileProcessorTest {

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
     * Test method for {@link BatchFileProcessor#process(java.lang.String, java.lang.String, char)}.
     *
     * @throws Exception
     */
    @Test
    public void testProcess() throws Exception {

        InputStream input = null;
        OutputStream output = null;
        String testPath = BatchFileProcessor.DEFAULT_INPUT_FOLDER_PATH +
                File.separator +
                "test.dat";

        try {
            input = getClass().getResourceAsStream("/test_input_file.dat");
            File testFolder = new File(BatchFileProcessor.DEFAULT_INPUT_FOLDER_PATH);
            if (!testFolder.exists()) {
                testFolder.mkdirs();
            }
            File testFile = new File(testPath);
            if (!testFile.exists()) {
                testFile.createNewFile();
            }
            output = new FileOutputStream(testFile);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }

        } finally {
            input.close();
            if (output != null) {
                output.close();
            }
        }
        File outputFileFolder = new File(System.getProperty("user.home") + File.separator + "data" + File.separator + "out");
        for (File file : outputFileFolder.listFiles()) {
            file.delete();
        }
        BatchFileProcessor.process();

        File outputFilePath = outputFileFolder.listFiles(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.endsWith(".done.dat");
            }
        })[0];
        byte[] encoded = Files.readAllBytes(Paths.get(outputFilePath.getAbsolutePath()));
        String content = new String(encoded, Charset.defaultCharset());
        Assert.assertNotNull(content);
    }

}