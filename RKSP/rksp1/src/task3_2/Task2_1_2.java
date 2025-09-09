package task3_2;

import rx.Observable;

import java.util.Random;

public class Task2_1_2 {
    public static void main(String[] args) {
        Random random = new Random();

        // Генерация потока из 1000 случайных чисел от 0 до 1000
        Observable<Integer> randomNumbers = Observable.range(0, 1000)
                .map(i -> random.nextInt(1001)); // Случайные числа от 0 до 1000

        // Фильтрация чисел больше 500
        randomNumbers
                .filter(number -> number > 500)
                .subscribe(System.out::println); // Вывод чисел больше 500
    }
}
