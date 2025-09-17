package task3_4;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class FileGenerator {
    private final Random random = new Random();
    private final String[] fileTypes = {"XML", "JSON", "XLS"};

    public Observable<File> generateFiles() {
        return Observable.interval(100, 1000, TimeUnit.MILLISECONDS, Schedulers.computation())
                .map(tick -> {
                    String type = fileTypes[random.nextInt(fileTypes.length)];
                    int size = 10 + random.nextInt(91); // 10-100
                    File file = new File(type, size);
                    System.out.println("Сгенерирован файл: " + file);
                    return file;
                });
    }
}
