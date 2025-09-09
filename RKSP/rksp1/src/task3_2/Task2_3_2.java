package task3_2;

import rx.Observable;

import java.util.Random;

public class Task2_3_2 {
    public static void main(String[] args) {
        Random random = new Random();

        // Генерация потока из 10 случайных чисел
        Observable<Integer> randomNumbers = Observable.range(0, 10)
                .map(i -> random.nextInt(100)); // Случайные числа от 0 до 99

        // Получение первых 5 чисел
        randomNumbers
                .take(5)
                .subscribe(System.out::println); // Вывод первых 5 чисел
    }
}

