package task1_3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class FileGenerator implements Runnable {
    private final BlockingQueue<File> queue;
    private final Random random = new Random();

    public FileGenerator(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int size = random.nextInt(91) + 10;
                String type = getRandomFileType();
                File file = new File(type, size);
                queue.put(file);
                System.out.printf("Сгенерирован файл: %s, размер: %d%n", type, size);

                // Задержка от 100 до 1000 мс
                Thread.sleep(random.nextInt(901) + 100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String getRandomFileType() {
        String[] types = {"XML", "JSON", "XLS"};
        return types[random.nextInt(types.length)];
    }
}
