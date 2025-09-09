package task3_1;

import rx.Observable;
import rx.Observer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SensorSystem {

    private static final int TEMPERATURE_THRESHOLD = 25;
    private static final int CO2_THRESHOLD = 70;

    public static void main(String[] args) {
        Random random = new Random();

        // Датчик температуры
        Observable<Integer> temperatureSensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> 15 + random.nextInt(16)); // Генерация случайной температуры от 15 до 30

        // Датчик CO2
        Observable<Integer> co2Sensor = Observable.interval(1, TimeUnit.SECONDS)
                .map(tick -> 30 + random.nextInt(71)); // Генерация случайного CO2 от 30 до 100

        // Объединение потоков данных от датчиков
        Observable.combineLatest(temperatureSensor, co2Sensor, (temperature, co2) -> {
            if (temperature > TEMPERATURE_THRESHOLD && co2 > CO2_THRESHOLD) {
                return "ALARM!!!";
            } else if (temperature > TEMPERATURE_THRESHOLD) {
                return "Warning: High Temperature - " + temperature + "°C";
            } else if (co2 > CO2_THRESHOLD) {
                return "Warning: High CO2 Level - " + co2 + " ppm";
            } else {
                return "Temperature: " + temperature + "°C, CO2: " + co2 + " ppm";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onNext(String message) {
                System.out.println(message); // Вывод сообщения
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // Завершение потока
            }
        });

        // Чтобы программа не завершалась сразу
        try {
            Thread.sleep(10000); // Запуск на 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
