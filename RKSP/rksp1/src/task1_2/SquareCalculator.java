package task1_2;


import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SquareCalculator {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число для возведения в квадрат:");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                int number = Integer.parseInt(input);
                Future<Long> future = executor.submit(new SquareTask(number));

                new Thread(() -> {
                    try {
                        Long result = future.get();
                        System.out.printf("Результат: %d в квадрате = %d%n", number, result);
                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("Ошибка при вычислении: " + e.getMessage());
                    }
                }).start();

            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректное число.");
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Программа завершена.");
    }

    static class SquareTask implements Callable<Long> {
        private final int number;

        public SquareTask(int number) {
            this.number = number;
        }

        @Override
        public Long call() throws Exception {
            int delay = 1 + (int) (Math.random() * 5);
            Thread.sleep(delay * 1000);
            return (long) number * number;
        }
    }
}
