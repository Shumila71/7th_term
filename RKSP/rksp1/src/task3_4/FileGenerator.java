package task3_4;

import rx.Observable;

import java.util.Random;

class FileGenerator {
    private final Random random = new Random();
    private final String[] fileTypes = {"XML", "JSON", "XLS"};

    public Observable<File> generateFiles() {
        return Observable.create(emitter -> {
            while (!emitter.isUnsubscribed()) {
                String type = fileTypes[random.nextInt(fileTypes.length)];
                int size = 10 + random.nextInt(91); // Размер от 10 до 100
                File file = new File(type, size);
                emitter.onNext(file);
                // Задержка от 100 до 1000 мс
                try {
                    Thread.sleep(100 + random.nextInt(901));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }
}
