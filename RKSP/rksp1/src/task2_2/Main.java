package task2_2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFile = "D:/code/_7/RKSP/rksp1/src/task2_2/largefile.txt";
        String destinationFile = "copy.txt";

        Runtime runtime = Runtime.getRuntime();

        // Метод 1: FileInputStream/FileOutputStream
        long startTime = System.nanoTime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        CopyUsingStreams.copy(sourceFile, destinationFile);
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        long endTime = System.nanoTime();
        System.out.println("FileInputStream/FileOutputStream: " + (endTime - startTime) / 1_000_000 + " ms, Memory used: " + memoryUsed + " bytes");

        // Метод 2: FileChannel
        startTime = System.nanoTime();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        CopyUsingFileChannel.copy(sourceFile, destinationFile);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        memoryUsed = memoryAfter - memoryBefore;
        endTime = System.nanoTime();
        System.out.println("FileChannel: " + (endTime - startTime) / 1_000_000 + " ms, Memory used: " + memoryUsed + " bytes");

        // Метод 3: Apache Commons IO
        startTime = System.nanoTime();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        CopyUsingApacheCommons.copy(sourceFile, destinationFile);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        memoryUsed = memoryAfter - memoryBefore;
        endTime = System.nanoTime();
        System.out.println("Apache Commons IO: " + (endTime - startTime) / 1_000_000 + " ms, Memory used: " + memoryUsed + " bytes");

        // Метод 4: Files class
        startTime = System.nanoTime();
        memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        CopyUsingFiles.copy(sourceFile, destinationFile);
        memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        memoryUsed = memoryAfter - memoryBefore;
        endTime = System.nanoTime();
        System.out.println("Files class: " + (endTime - startTime) / 1_000_000 + " ms, Memory used: " + memoryUsed + " bytes");
    }
}
