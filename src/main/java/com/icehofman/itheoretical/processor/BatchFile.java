package com.icehofman.itheoretical.processor;

import com.icehofman.itheoretical.model.Sale.SalesBatch;
import com.icehofman.itheoretical.utils.Parser;
import com.icehofman.itheoretical.utils.Report;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BatchFile {

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

    public static void process(String inputFolderPath, String outputFolderPath, char columnSeparator) throws Exception {

        File inputPath = new File(inputFolderPath);
        SalesBatch salesBatch = null;

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
            salesBatch = Parser.getParser().processInputFile(columnSeparator, new FileInputStream(inputPath));
            inputFiles.add(inputPath);
        }
        if (salesBatch == null) {
            return;
        }
        File outputFolder = new File(outputFolderPath);
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }
        String outputFileName = outputFolderPath + File.separator + "_" + new Date().getTime() + ".done.dat";
        Report.writeToCSVFile(salesBatch, outputFileName, String.valueOf(columnSeparator));
        moveProcessedFiles(inputFiles);
    }

    private static void moveProcessedFiles(List<File> inputFiles) throws IOException {
        for (File file : inputFiles) {
            Path source = FileSystems.getDefault().getPath(file.getAbsolutePath());
            Path target = FileSystems.getDefault().getPath(DEFAULT_PROCESSED_FOLDER_PATH + File.separator + file.getName());
            CopyOption option = StandardCopyOption.REPLACE_EXISTING;
            Files.move(source, target, option);
        }
    }

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

    public static void main(String[] args) {
        run();
    }

    public static void process() throws Exception {
        process(DEFAULT_INPUT_FOLDER_PATH, DEFAULT_OUTPUT_FOLDER_PATH, DEFAULT_COLUMN_SEPARATOR);
    }

    private static class DatFileNameFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return name.endsWith(".dat");
        }
    }
}