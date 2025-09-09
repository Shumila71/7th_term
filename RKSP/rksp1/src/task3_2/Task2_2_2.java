package task3_2;

import rx.Observable;

import java.util.Random;

public class Task2_2_2 {
    public static void main(String[] args) {
        Random random = new Random();

        // Генерация двух потоков по 1000 случайных чисел
        Observable<Integer> firstStream = Observable.range(0, 1000)
                .map(i -> random.nextInt(10)); // Случайные цифры от 0 до 9

        Observable<Integer> secondStream = Observable.range(0, 1000)
                .map(i -> random.nextInt(10)); // Случайные цифры от 0 до 9

        // Объединение потоков последовательно
        Observable.concat(firstStream, secondStream)
                .subscribe(System.out::println); // Вывод объединенного потока
    }
}
