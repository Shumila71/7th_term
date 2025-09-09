package task3_4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class FileQueue {
    private final BlockingQueue<File> queue = new LinkedBlockingQueue<>(5);

    public void addFile(File file) throws InterruptedException {
        queue.put(file);
    }

    public File getFile() throws InterruptedException {
        return queue.take();
    }
}
