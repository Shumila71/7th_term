package task1_3;

import java.util.concurrent.BlockingQueue;

class FileHandler implements Runnable {
    private final BlockingQueue<File> queue;
    private final String type;

    public FileHandler(BlockingQueue<File> queue, String type) {
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {
        while (true) {
            try {
                File file = queue.take();
                if (!file.getType().equals(type)) {
                    queue.put(file);
                    continue;
                }

                int processingTime = file.getSize() * 7;
                Thread.sleep(processingTime);
                System.out.printf("Обработан файл: %s, размер: %d, время обработки: %d мс%n",
                        file.getType(), file.getSize(), processingTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
