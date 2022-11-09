package lab_2;

import lab_1.ArrayList;
import lab_1.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.LongStream;

import static lab_2.Utils.readSample;

public class SortsBenchmark {
    private static final Logger log = LogManager.getLogger(SortsBenchmark.class);
    private static final Comparator<Integer> cmp = Integer::compare;
    private static final int ITERATIONS = 5;
    private static final int WARMUPS = 2;

    public static void main(String[] args) throws IOException {
        List<Integer> samples = new ArrayList<>(new Integer[]{
                1_000, 5_000,
                10_000, 50_000,
                100_000, 500_000,
                1_000_000, 3_000_000, 5_000_000
        });
        List<ReportRow> rows = new ArrayList<>();

        for (int sample = 0; sample < samples.size(); sample++) {
            int sampleSize = samples.get(sample);
            log.debug("#{} Test sample: {} elements", sample + 1, sampleSize);
            Integer[] source = readSample(sampleSize + ".txt");
            ReportRow row = new ReportRow(sampleSize);
            if (sampleSize <= 100_000) {
                row.insertionSort = testSort(source, "insertionSort", l -> InsertionSort.insertionSort(l, cmp));
            }
            row.mergeSort = testSort(source, "mergeSort", l -> MergeSort.mergeSort(l, cmp));
            row.timSort = testSort(source, "timSort", l -> TimSort.timSort(l, cmp));
            rows.add(row);
        }

        try (FileWriter writer = new FileWriter("src/main/resources/benchmark.csv");
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL.builder()
                     .setHeader("elements", "insertionSort", "mergeSort", "timSort")
                     .build())
        ) {
            for (int r = 0; r < rows.size(); r++) {
                ReportRow row = rows.get(r);
                printer.printRecord(row.elements, row.insertionSort, row.mergeSort, row.timSort);
            }
        }
    }

    private static int testSort(Integer[] source, String name, Consumer<List<Integer>> sort) {
        long[] times = new long[ITERATIONS];
        log.debug("Using {}", name);

        for (int iteration = 0; iteration < WARMUPS; iteration++) {
            List<Integer> list = new ArrayList<>(source);
            log.debug("Warmup #{}", iteration + 1);
            sort.accept(list);
        }
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            List<Integer> list = new ArrayList<>(source);
            log.debug("Iteration #{}", iteration + 1);
            long started = System.currentTimeMillis();
            sort.accept(list);
            times[iteration] = System.currentTimeMillis() - started;
        }

        int average = (int) LongStream.of(times).average().orElse(0);
        log.debug("Sorted with {}: {}. Total {} milliseconds in avg", name, times, average);
        return average;
    }

    static class ReportRow {
        int elements;
        int insertionSort;
        int mergeSort;
        int timSort;

        public ReportRow(int elements) {
            this.elements = elements;
        }
    }
}
