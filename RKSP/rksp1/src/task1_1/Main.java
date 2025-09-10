package task1_1;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final int ARRAY_SIZE = 10000;
    private static final int WARMUP_RUNS = 3;
    private static final int MEASUREMENT_RUNS = 2;

    public static void main(String[] args) throws Exception {
        int[] array = generateSequentialArray(ARRAY_SIZE);

        System.out.println("Размер массива: " + ARRAY_SIZE);
        System.out.println("Ядра: " + Runtime.getRuntime().availableProcessors());
        System.out.println();

        System.out.println("Последовательная сумма");
        measureSequential(array);

        System.out.println("\nМногопоточная сумма");
        measureMultiThreaded(array);

        System.out.println("\nForkJoin сумма");
        measureForkJoin(array);
    }

    private static int[] generateSequentialArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private static void measureSequential(int[] array) {
        long totalTime = 0;
        long totalMemory = 0;
        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < WARMUP_RUNS + MEASUREMENT_RUNS; i++) {
            System.gc();
            try {
                Thread.sleep(100); // Даем время для сборки мусора
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.currentTimeMillis();

            long sum = SequentialSum.sum(array);

            long endTime = System.currentTimeMillis();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = memoryAfter - memoryBefore;

            if (i >= WARMUP_RUNS) {
                totalTime += (endTime - startTime);
                totalMemory += memoryUsed;

                System.out.printf("Попытка %d: время = %d мс, память = %.2f KB, сумма = %d%n",
                        i + 1 - WARMUP_RUNS, endTime - startTime, (double) memoryUsed / 1024, sum);
            }
        }

        System.out.printf("Ср. время: %.2f мс%n", (double) totalTime / MEASUREMENT_RUNS);
        System.out.printf("Ср. память: %.2f KB%n", (double) totalMemory / MEASUREMENT_RUNS / 1024);
    }

    private static void measureMultiThreaded(int[] array) throws InterruptedException, ExecutionException {
        long totalTime = 0;
        long totalMemory = 0;
        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < WARMUP_RUNS + MEASUREMENT_RUNS; i++) {
            System.gc();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.currentTimeMillis();

            long sum = MultiThreadedSum.sum(array);

            long endTime = System.currentTimeMillis();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = memoryAfter - memoryBefore;

            if (i >= WARMUP_RUNS) {
                totalTime += (endTime - startTime);
                totalMemory += memoryUsed;

                System.out.printf("Попытка %d: время = %d мс, память = %.2f KB, сумма = %d%n",
                        i + 1 - WARMUP_RUNS, endTime - startTime, (double) memoryUsed / 1024, sum);
            }
        }

        System.out.printf("Ср. время: %.2f мс%n", (double) totalTime / MEASUREMENT_RUNS);
        System.out.printf("Ср. память: %.2f KB%n", (double) totalMemory / MEASUREMENT_RUNS / 1024);
    }

    private static void measureForkJoin(int[] array) {
        long totalTime = 0;
        long totalMemory = 0;
        Runtime runtime = Runtime.getRuntime();

        for (int i = 0; i < WARMUP_RUNS + MEASUREMENT_RUNS; i++) {
            System.gc();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.currentTimeMillis();

            long sum = ForkJoinSum.sum(array);

            long endTime = System.currentTimeMillis();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = memoryAfter - memoryBefore;

            if (i >= WARMUP_RUNS) {
                totalTime += (endTime - startTime);
                totalMemory += memoryUsed;

                System.out.printf("Попытка %d: время = %d мс, память = %.2f KB, сумма = %d%n",
                        i + 1 - WARMUP_RUNS, endTime - startTime, (double) memoryUsed / 1024, sum);
            }
        }

        System.out.printf("Ср. время: %.2f мс%n", (double) totalTime / MEASUREMENT_RUNS);
        System.out.printf("Ср. память: %.2f KB%n", (double) totalMemory / MEASUREMENT_RUNS / 1024);
    }
}