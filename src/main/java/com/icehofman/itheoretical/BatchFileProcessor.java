package com.icehofman.itheoretical;

import com.icehofman.itheoretical.model.SalesBatch;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BatchFileProcessor {

    public static final String DEFAULT_INPUT_FOLDER_PATH = System.getProperty("user.home") +
            File.separator + "data" +
            File.separator + "in";

    public static final String DEFAULT_OUTPUT_FOLDER_PATH = System.getProperty("user.home") +
            File.separator + "data" +
            File.separator + "out";

    public static final String DEFAULT_PROCESSED_FOLDER_PATH = System.getProperty("user.home") +
            File.separator + "data" +
            File.separator + "processed";
    public static final Character DEFAULT_COLUMN_SEPARATOR = 'ç';
    private static final long DEFAULT_RUN_PERIOD = 5000;

    static {
        File processedFolder = new File(DEFAULT_PROCESSED_FOLDER_PATH);
        if (!processedFolder.exists()) {
            processedFolder.mkdirs();
        }
    }

    /**
     * Process the input folder
     *
     * @param inputFolderPath
     * @param outputFolderPath
     * @param columnSeparator  The column separator of the input and output files
     * @throws Exception
     */
    public static void process(String inputFolderPath, String outputFolderPath, char columnSeparator) throws Exception {

        File inputPath = new File(inputFolderPath);
        SalesBatch salesBatch = null;
        /*
         * Verify if inputPath is directory or file. If it is a directory, process all files inside it
		 * according to filter
		 */
        List<File> inputFiles = new ArrayList<File>();
        if (inputPath.isDirectory()) {
            if (!inputPath.exists()) {
                inputPath.mkdirs();
            }
            List<InputStream> inputStreams = new ArrayList<InputStream>();
            for (File inputFilePath : inputPath.listFiles(new DatFileNameFilter())) {
                inputStreams.add(new FileInputStream(inputFilePath));
                inputFiles.add(inputFilePath);
            }
            if (inputFiles.isEmpty()) {
                return;
            }
            salesBatch = Parser.getParser().processInputFile(columnSeparator,
                    inputStreams.toArray(new InputStream[inputStreams.size()]));

        } else {
			/*
			 * Process a single input file
			 */
            salesBatch = Parser.getParser().processInputFile(columnSeparator, new FileInputStream(inputPath));
            inputFiles.add(inputPath);
        }
        if (salesBatch == null) {
            return;
        }
		/*
		 * Writes the output file
		 */
        File outputFolder = new File(outputFolderPath);
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        String outputFileName = outputFolderPath + File.separator + "_" + new Date().getTime() + ".done.dat";
        ReportUtil.writeToCSVFile(salesBatch, outputFileName, String.valueOf(columnSeparator));
        moveProcessedFiles(inputFiles);
    }

    /**
     * Move processed files to a separate folder
     *
     * @param inputFiles
     * @throws IOException
     */
    private static void moveProcessedFiles(List<File> inputFiles) throws IOException {
        for (File file : inputFiles) {
            Path source = FileSystems.getDefault().getPath(file.getAbsolutePath());
            Path target = FileSystems.getDefault().getPath(DEFAULT_PROCESSED_FOLDER_PATH + File.separator + file.getName());
            CopyOption option = StandardCopyOption.REPLACE_EXISTING;
            Files.move(source, target, option);
        }


    }

    /**
     * Run the processor as a daemon
     */
    public static void run() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                try {
                    process(DEFAULT_INPUT_FOLDER_PATH, DEFAULT_OUTPUT_FOLDER_PATH, DEFAULT_COLUMN_SEPARATOR);
                    System.out.println("Processed: " + DEFAULT_INPUT_FOLDER_PATH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 0, DEFAULT_RUN_PERIOD);
        System.out.println("Running...");
    }

    /**
     * Main method to run through console
     *
     * @param args
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Default process method
     *
     * @throws Exception
     */
    public static void process() throws Exception {
        process(DEFAULT_INPUT_FOLDER_PATH, DEFAULT_OUTPUT_FOLDER_PATH, DEFAULT_COLUMN_SEPARATOR);

    }

    /**
     * Filter for the input files
     *
     * @author Ricardo
     */
    private static class DatFileNameFilter implements FilenameFilter {

        public boolean accept(File dir, String name) {
            return name.endsWith(".dat");
        }

    }

}
