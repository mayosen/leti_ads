package lab_2;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Utils {
    public static void main(String[] args) {
        generateSampleFor(100);
        generateSampleFor(500);
        generateSampleFor(1_000);
        generateSampleFor(5_000);
        generateSampleFor(10_000);
        generateSampleFor(50_000);
        generateSampleFor(100_000);
        generateSampleFor(500_000);
        generateSampleFor(1_000_000);
    }

    public static void generateSampleFor(int elements) {
        String name = String.valueOf(elements);
        int[] array = generateRandomNumbers(elements);
        saveSample(name + ".txt", array);
        Arrays.sort(array);
        saveSample(name + "_sorted.txt", array);
    }

    public static int[] generateRandomNumbers(int count) {
        int max = count / (int) Math.sqrt(count);
        return new Random().ints(count, -max, max + 1).toArray();
    }

    public static void saveSample(String name, int[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(String.format("% 10d", array[i]));
                writer.write(" ");
                if ((i + 1) % 10 == 0) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer[] readSample(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(name))) {
            name = name.substring(name.lastIndexOf("/") + 1);
            name = name.substring(0, name.indexOf(".txt"));
            if (name.endsWith("_sorted")) {
                name = name.substring(0, name.indexOf("_sorted"));
            }
            int values = Integer.parseInt(name);
            Integer[] array = new Integer[values];
            int current = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String part : parts) {
                    if (part.equals("")) {
                        continue;
                    }
                    array[current] = Integer.parseInt(part);
                    current++;
                }
            }

            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
