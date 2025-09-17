package task3_2;

import rx.Observable;
import java.util.Random;

public class RxExample2 {
    public static void main(String[] args) {
        Random random = new Random();

        // Поток случайных букв (A-Z)
        Observable<String> letters = Observable.range(0, 1000)
                .map(i -> {
                    char letter = (char) ('A' + random.nextInt(26));
                    return String.valueOf(letter);
                });

        // Поток случайных цифр (0-9)
        Observable<String> digits = Observable.range(0, 1000)
                .map(i -> String.valueOf(random.nextInt(10)));

        // Объединяем потоки с помощью zip
        Observable.zip(letters, digits,
                        (letter, digit) -> letter + digit) // Объединяем букву и цифру
                .subscribe(
                        combined -> System.out.println("Объединенный: " + combined),
                        Throwable::printStackTrace,
                        () -> System.out.println("Задание 2.2.1 завершено")
                );
    }
}
