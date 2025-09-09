package task1_1;

public class SequentialSum {
    public static long sum(int[] array) {
        long sum = 0;
        for (int value : array) {
            sum += value;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return sum;
    }
}
