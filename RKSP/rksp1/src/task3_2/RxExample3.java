package task3_2;
import rx.Observable;
import java.util.Random;

public class RxExample3 {
    public static void main(String[] args) {
        Random random = new Random();

        Observable.range(0, 10)
                .map(i -> random.nextInt(100)) // Генерируем 10 случайных чисел
                .doOnNext(number -> System.out.println("Сгенерировано: " + number))
                .skip(3) // Пропускаем первые 3 элемента
                .subscribe(
                        number -> System.out.println("Вывод: " + number),
                        Throwable::printStackTrace,
                        () -> System.out.println("Задание 2.3.1 завершено")
                );
    }
}