package task3_2;

import rx.Observable;
import java.util.Random;

public class RxExample1 {
    public static void main(String[] args) {
        Random random = new Random();

        Observable.range(0, 1000)
                .map(i -> random.nextInt(1001)) // Генерируем случайные числа 0-1000
                .map(number -> number * number)  // Возводим в квадрат
                .subscribe(
                        square -> System.out.println("Квадрат: " + square),
                        Throwable::printStackTrace,
                        () -> System.out.println("Задание 2.1.1 завершено")
                );
    }
}
