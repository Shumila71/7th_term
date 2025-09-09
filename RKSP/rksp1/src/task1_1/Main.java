package task1_1;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long startTime = System.currentTimeMillis();
        long sequentialSum = MultiThreadedSum.sum(array);
        long endTime = System.currentTimeMillis();
        long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.printf("Sequential Sum: %d, Time: %d ms, Memory: %d bytes%n",
                sequentialSum, (endTime - startTime), (memoryAfter - memoryBefore));

        memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        startTime = System.currentTimeMillis();
        long multiThreadedSum = MultiThreadedSum.sum(array);
        endTime = System.currentTimeMillis();
        memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.printf("Multi-threaded Sum: %d, Time: %d ms, Memory: %d bytes%n",
                multiThreadedSum, (endTime - startTime), (memoryAfter - memoryBefore));

        memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        startTime = System.currentTimeMillis();
        long forkJoinSum = ForkJoinSum.sum(array);
        endTime = System.currentTimeMillis();
        memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.printf("ForkJoin Sum: %d, Time: %d ms, Memory: %d bytes%n",
                forkJoinSum, (endTime - startTime), (memoryAfter - memoryBefore));
    }
}
