package task3_4;

import rx.Observable;
import rx.Observer;

import java.util.concurrent.TimeUnit;

public class FileProcessingSystem {
    public static void main(String[] args) {
        FileGenerator generator = new FileGenerator();
        FileQueue fileQueue = new FileQueue();
        FileHandler jsonHandler = new FileHandler("JSON");
        FileHandler xmlHandler = new FileHandler("XML");

        // Генерация файлов
        generator.generateFiles()
                .subscribe(new Observer<File>() {
                    @Override
                    public void onNext(File file) {
                        try {
                            fileQueue.addFile(file);
                            System.out.println("Added to queue: " + file);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
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

        // Обработка файлов
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .subscribe(tick -> {
                    try {
                        File file = fileQueue.getFile();
                        if (file != null) {
                            if (file.type.equals("JSON")) {
                                jsonHandler.processFile(file);
                            } else if (file.type.equals("XML")) {
                                xmlHandler.processFile(file);
                            } else {
                                System.out.println("No handler for file type: " + file.type);
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

        // Чтобы программа не завершалась сразу
        try {
            Thread.sleep(10000); // Запуск на 20 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
