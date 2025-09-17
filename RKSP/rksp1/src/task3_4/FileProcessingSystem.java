package task3_4;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

public class FileProcessingSystem {
    public static void main(String[] args) throws InterruptedException {

        FileGenerator generator = new FileGenerator();

        // Создаем observable из генератора
        generator.generateFiles()
                .onBackpressureBuffer(5) // Встроенная очередь на 5 элементов
                //Группируем файлы по типу для создания "виртуальных очередей" для каждого обработчика
                .groupBy(file -> file.type)
                // Подписываемся на каждую группу (каждый тип файла)
                .subscribe(groupedObservable -> {
                    String fileType = groupedObservable.getKey();
                    System.out.println("Запущен обработчик для типа файлов: " + fileType);

                    // Подписываемся на файлы в этой группе
                    groupedObservable
                            // Обрабатываем каждый тип в отдельном потоке для параллелизма
                            .observeOn(Schedulers.io())
                            .subscribe(file -> {
                                // Создаем и запускаем обработчик для этого файла
                                FileHandler handler = new FileHandler(fileType);
                                try {
                                    handler.processFile(file);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            });
                });

        // Даем системе время на работу
        Thread.sleep(10000);
        System.out.println("Обработка окончена.");
    }
}
