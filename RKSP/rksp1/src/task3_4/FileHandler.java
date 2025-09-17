package task3_4;

class FileHandler {
    private final String fileType;

    public FileHandler(String fileType) {
        this.fileType = fileType;
    }

    public void processFile(File file) throws InterruptedException {
        // Проверка (хотя groupBy уже гарантирует соответствие типа)
        if (file.type.equals(fileType)) {
            int processingTime = file.size * 7;
            System.out.println("Обработываем " + file + " за " + processingTime + " ms");
            Thread.sleep(processingTime);
            System.out.println("Обработан файл: " + file);
        } else {
            System.out.println("Не тот формат файла");
        }
    }
}
